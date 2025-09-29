#!/usr/bin/env bash
set -euo pipefail

# Usage: encrypt_hex.sh HOST PORT HEXDATA [TIMEOUT_SECONDS]
HOST="${1:-}"
PORT="${2:-}"
HEXDATA="${3:-}"
TOUT="${4:-10}"

if [[ -z "$HOST" || -z "$PORT" || -z "$HEXDATA" ]]; then
  echo "Usage: $0 HOST PORT HEXDATA [TIMEOUT_SECONDS]" >&2
  exit 1
fi

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

{
  printf '2\n'
  printf '%s\n' "$HEXDATA"
} | "$SCRIPT_DIR/nc_timeout.sh" "$HOST" "$PORT" "$TOUT" | awk '/^[0-9a-fA-F]+$/{print; exit}'