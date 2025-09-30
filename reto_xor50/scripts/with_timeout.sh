#!/usr/bin/env bash
# run with timeout wrapper
set -euo pipefail
if [[ $# -lt 1 ]]; then echo "usage: $0 <timeout> <cmd...>"; exit 1; fi
TO=$1; shift
/usr/bin/timeout "$TO" "$@"
