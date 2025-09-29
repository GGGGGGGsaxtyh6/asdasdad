#!/usr/bin/env bash
set -euo pipefail

# Usage: nc_check.sh HOST PORT [TIMEOUT_SECONDS]
HOST="${1:-}"
PORT="${2:-}"
TOUT="${3:-5}"

if [[ -z "$HOST" || -z "$PORT" ]]; then
  echo "Usage: $0 HOST PORT [TIMEOUT_SECONDS]" >&2
  exit 1
fi

timeout "${TOUT}s" nc -z -v -w "${TOUT}" "$HOST" "$PORT" || exit $?

