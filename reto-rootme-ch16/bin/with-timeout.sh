#!/usr/bin/env bash
set -euo pipefail
if [[ $# -lt 1 ]]; then echo "Usage: $0 <seconds> <cmd...>"; exit 1; fi
secs="$1"; shift
timeout "${secs}s" "$@"
