#!/usr/bin/env bash
set -euo pipefail

# Usage: get_flag_hex.sh HOST PORT [TIMEOUT_SECONDS]
HOST="${1:-}"
PORT="${2:-}"
TOUT="${3:-10}"

if [[ -z "$HOST" || -z "$PORT" ]]; then
  echo "Usage: $0 HOST PORT [TIMEOUT_SECONDS]" >&2
  exit 1
fi

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

printf '1\n' | "${SCRIPT_DIR}/nc_timeout.sh" "$HOST" "$PORT" "$TOUT" | sed -n 's/^[0-9a-fA-F]\{8,\}$/&/p' | head -n 1

