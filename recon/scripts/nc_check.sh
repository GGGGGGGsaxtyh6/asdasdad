#!/usr/bin/env bash
set -euo pipefail
# Minimal nc connection test with timeout reset policy
HOST="$1"; PORT="$2"; TO_MIN="${3:-8}"; TO_MAX="${4:-40}"
# First attempt with minimal timeout
/workspace/recon/scripts/with_timeout.sh "$TO_MIN" -- bash -lc "nc -vz $HOST $PORT" || {
  # Escalate timeout only if needed
  NEXT_TO=$(( TO_MIN * 2 )); if (( NEXT_TO > TO_MAX )); then NEXT_TO=$TO_MAX; fi
  /workspace/recon/scripts/with_timeout.sh "$NEXT_TO" -- bash -lc "nc -vz $HOST $PORT"
}
# After command, timeouts are not persistent; subsequent calls use default small timeout
