#!/usr/bin/env bash
set -Eeuo pipefail

# Netcat wrapper with timeout (mandatory for any NC usage)
# Usage: nc_timeout.sh [timeout] <host> <port> [nc args...]

DEFAULT_TIMEOUT="${DEFAULT_TIMEOUT:-15s}"

if [[ $# -lt 2 ]]; then
    echo "Usage: $0 [timeout] <host> <port> [nc args...]" >&2
    exit 2
fi

timeout_arg="$DEFAULT_TIMEOUT"
first_arg="${1:-}"
if [[ "$first_arg" =~ ^[0-9]+[smh]$ ]]; then
    timeout_arg="$first_arg"
    shift
fi

host="$1"; port="$2"; shift 2

exec timeout --foreground "$timeout_arg" nc -nv "$host" "$port" "$@"

