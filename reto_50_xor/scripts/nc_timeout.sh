#!/usr/bin/env bash
set -euo pipefail
if [ "$#" -lt 3 ]; then
  echo "Usage: $0 <host> <port> <timeout_s> [input_file]" >&2
  exit 1
fi
HOST="$1"; PORT="$2"; TOUT="$3"; shift 3
if [ "$#" -ge 1 ]; then
  INPUT_FILE="$1"
  timeout "${TOUT}s" nc -nv "$HOST" "$PORT" < "$INPUT_FILE"
else
  timeout "${TOUT}s" nc -nv "$HOST" "$PORT"
fi
EOF
chmod +x /workspace/reto_50_xor/scripts/nc_timeout.sh && echo CREATED
