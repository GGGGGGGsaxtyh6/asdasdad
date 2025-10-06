#!/usr/bin/env bash
set -euo pipefail
IP=${1:?IP}
PORT_SETTER=${2:?SETTER_PORT}   # e.g., 36986
PORT_FLAG=${3:?FLAG_PORT}       # e.g., 41769
TO=${4:-8}
OUT_DIR=${5:-/workspace/recon/httpx}
DIR="$OUT_DIR/${IP}_menus"
mkdir -p "$DIR"
# Helper to run nc with timeout and feed payload
run_nc(){ local port=$1; local name=$2; local payload=$3; printf "%b" "$payload" | /workspace/recon/scripts/with_timeout.sh "$TO" -- bash -lc "nc -nv $IP $port" > "$DIR/${name}.txt" 2>&1 || true; }
# 1) Update key to 1 on setter service
run_nc "$PORT_SETTER" "setter_update_key_1" $'2\n1\n4\n'
# 2) Get flag from flag service
run_nc "$PORT_FLAG" "flag_get_after_key1" $'1\n'
# 3) Capture hex from get flag (first hex-looking token)
HEX=$(rg -oN "[0-9a-fA-F]{16,}" "$DIR/flag_get_after_key1.txt" | head -n1 || true)
printf "%s" "$HEX" > "$DIR/flag_hex.txt" || true
# 4) Try decrypt menu with captured hex on flag service
if [[ -s "$DIR/flag_hex.txt" ]]; then
  run_nc "$PORT_FLAG" "flag_decrypt_hex" $'3\n'"$HEX"$'\n'
fi
# 5) As fallback, set key to 0 and retry
run_nc "$PORT_SETTER" "setter_update_key_0" $'2\n0\n4\n'
run_nc "$PORT_FLAG" "flag_get_after_key0" $'1\n'
# 6) Try decrypt again with latest hex
HEX2=$(rg -oN "[0-9a-fA-F]{16,}" "$DIR/flag_get_after_key0.txt" | head -n1 || true)
if [[ -n "$HEX2" ]]; then
  printf "%s" "$HEX2" > "$DIR/flag_hex2.txt"
  run_nc "$PORT_FLAG" "flag_decrypt_hex2" $'3\n'"$HEX2"$'\n'
fi
# 7) Grep for HTB flags in transcripts
rg -n "HTB\{[^}]+\}" -S "$DIR" | tee "$DIR/htb_hits.txt" || true
sed -n '1,200p' "$DIR/htb_hits.txt" || true
