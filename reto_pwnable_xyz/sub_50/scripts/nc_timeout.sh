#!/usr/bin/env bash
set -euo pipefail
HOST="$1"; PORT="$2"; shift 2
DUR="${1:-10s}"; shift || true
exec timeout "$DUR" nc -v "$HOST" "$PORT" "$@"
