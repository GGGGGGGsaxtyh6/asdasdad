#!/usr/bin/env bash
set -euo pipefail
if [ "$#" -lt 2 ]; then
	echo "Usage: $0 HOST PORT [TIMEOUT_S]" >&2
	exit 1
fi
HOST="$1"
PORT="$2"
TIMEOUT_S="${3:-10}"
exec timeout "${TIMEOUT_S}s" nc -v -n -q 1 "$HOST" "$PORT"
