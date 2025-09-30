#!/usr/bin/env bash
set -euo pipefail

# Usage: run_with_timeout.sh <seconds> <command...>
# Enforces a max timeout with 'timeout'. Defaults to 10s if not provided.

if [[ $# -lt 1 ]]; then
  echo "Usage: $0 <seconds> <command...>" >&2
  exit 1
fi

seconds=$1; shift || true
if [[ -z "${seconds:-}" || ! "$seconds" =~ ^[0-9]+[smh]?$ ]]; then
  seconds=10s
fi

if [[ $# -eq 0 ]]; then
  echo "No command provided" >&2
  exit 1
fi

exec timeout "$seconds" "$@"

