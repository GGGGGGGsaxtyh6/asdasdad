#!/usr/bin/env bash
set -euo pipefail

# Usage: grab_banner.sh <host> <port> [seconds]

if [[ $# -lt 2 ]]; then
  echo "Usage: $0 <host> <port> [seconds]" >&2
  exit 1
fi

host=$1; port=$2; seconds=${3:-5s}

"$(dirname "$0")/nc_timeout.sh" "$seconds" "$host" "$port" 2>&1 | tee \
  "/workspace/reto_xor_50/out/banner_${host}_${port}.log"

