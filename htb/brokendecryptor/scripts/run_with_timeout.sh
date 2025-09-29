#!/usr/bin/env bash
set -Eeuo pipefail

# Generic wrapper to run a command with timeout
# Usage: run_with_timeout.sh [timeout] <command> [args...]
# Default timeout can be set via DEFAULT_TIMEOUT env var (e.g., 20s). Fallback: 20s

DEFAULT_TIMEOUT="${DEFAULT_TIMEOUT:-20s}"

if [[ $# -lt 1 ]]; then
    echo "Usage: $0 [timeout] <command> [args...]" >&2
    exit 2
fi

timeout_arg="$DEFAULT_TIMEOUT"
first_arg="${1:-}"
if [[ "$first_arg" =~ ^[0-9]+[smh]$ ]]; then
    timeout_arg="$first_arg"
    shift
fi

exec timeout --foreground "$timeout_arg" "$@"

