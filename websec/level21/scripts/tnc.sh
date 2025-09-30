#!/usr/bin/env bash
set -euo pipefail
TIMEOUT="${1:-10s}"
shift || true
if [[ $# -lt 1 ]]; then echo "Usage: $0 [timeout e.g. 10s] <nc-args...>"; exit 1; fi
/usr/bin/timeout "$TIMEOUT" nc "$@"
