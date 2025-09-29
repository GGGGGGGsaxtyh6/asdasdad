#!/usr/bin/env bash
set -euo pipefail

# Usage: nc_timeout.sh HOST PORT [TIMEOUT_SECONDS]
HOST="${1:-}"
PORT="${2:-}"
TOUT="${3:-10}"

if [[ -z "$HOST" || -z "$PORT" ]]; then
  echo "Usage: $0 HOST PORT [TIMEOUT_SECONDS]" >&2
  exit 1
fi

# Run netcat with a hard timeout to avoid hanging
timeout "${TOUT}s" bash -lc 'cat | nc -n "$HOST" "$PORT" | cat'

