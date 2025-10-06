#!/usr/bin/env bash
set -euo pipefail
IP=${1:?IP}
SETTER=${2:-36986}
FLAG=${3:-41769}
TO=${4:-10}
OUT_DIR=${5:-/workspace/recon/httpx}
DIR="$OUT_DIR/${IP}_menus_final"
mkdir -p "$DIR"
# Helper to run nc with payload and timeout
run_nc(){ local port=$1; local name=$2; shift 2; local payload="$*"; printf "%b" "$payload" | /workspace/recon/scripts/with_timeout.sh "$TO" -- bash -lc "nc -nv $IP $port" > "$DIR/${name}.txt" 2>&1 || true; }
# 1) Get flag hex (plain)
run_nc "$FLAG" "flag_get_plain" $'1\n'
HEX_FLAG=$(rg -oN "[0-9a-fA-F]{24,}" "$DIR/flag_get_plain.txt" | head -n1 | tr 'a-f' 'A-F' || true)
# 2) Try decrypt directly with obtained flag hex
if [[ -n "${HEX_FLAG:-}" ]]; then run_nc "$FLAG" "flag_decrypt_direct" $'3\n'