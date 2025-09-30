#!/usr/bin/env bash
set -euo pipefail
DUR="${1:-10s}"; shift || true
exec timeout "$DUR" "$@"
