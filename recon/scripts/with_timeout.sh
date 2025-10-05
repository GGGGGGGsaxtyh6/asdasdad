#!/usr/bin/env bash
set -euo pipefail
# Usage: with_timeout <seconds> -- <command...>
# Example: with_timeout 10 -- bash -lc "nc -vz 1.2.3.4 22"
if [[ $# -lt 3 ]]; then
  echo "Usage: $0 <seconds> -- <command...>" >&2
  exit 1
fi
TO=$1; shift
if [[ $1 != "--" ]]; then echo "missing -- separator" >&2; exit 1; fi; shift
# Use timeout; kill after TO seconds; also fail if hangs
exec timeout --foreground --signal=KILL "$TO"s "$@"
