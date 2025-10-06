#!/usr/bin/env bash
set -euo pipefail
IP=${1:?IP}
PORT_PARAMS=${2:-50419}
PORT_ELG=${3:-56357}
TO=${4:-10}
OUT_DIR=${5:-/workspace/recon/httpx}
DIR="$OUT_DIR/${IP}_elgamal_cap"
mkdir -p "$DIR"
# 1) Fetch parameters (service 50419)
( printf "1\n" ) | /workspace/recon/scripts/with_timeout.sh "$TO" -- bash -lc "nc -nv $IP $PORT_PARAMS" > "$DIR/params.txt" 2>&1 || true
# 2) Get flag pairs (service 56357)
( printf "1\n" ) | /workspace/recon/scripts/with_timeout.sh "$TO" -- bash -lc "nc -nv $IP $PORT_ELG" > "$DIR/flag_pairs.txt" 2>&1 || true
# 3) Encrypt m=1 to get session secret pairs
( printf "2\n"; sleep 0.3; printf "1\n" ) | /workspace/recon/scripts/with_timeout.sh "$TO" -- bash -lc "nc -nv $IP $PORT_ELG" > "$DIR/enc1_pairs.txt" 2>&1 || true
# 4) For reference, also get 50419 encrypted hex flag
( printf "3\n" ) | /workspace/recon/scripts/with_timeout.sh "$TO" -- bash -lc "nc -nv $IP $PORT_PARAMS" > "$DIR/flag_hex_50419.txt" 2>&1 || true
# Print heads
sed -n '1,80p' "$DIR/params.txt" || true
sed -n '1,40p' "$DIR/flag_pairs.txt" || true
sed -n '1,40p' "$DIR/enc1_pairs.txt" || true
sed -n '1,40p' "$DIR/flag_hex_50419.txt" || true
