#!/usr/bin/env bash
set -euo pipefail
if [[ $# -lt 2 ]]; then
  echo "Usage: $0 <seconds> <command...>" >&2
  exit 1
fi
secs="$1"; shift
/usr/bin/timeout --preserve-status --signal=INT --kill-after=2s "$secs" "$@"
