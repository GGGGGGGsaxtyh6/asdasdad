#!/usr/bin/env bash
set -euo pipefail
DIR=$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)
ROOT="${DIR%/*}"
[ -f "$ROOT/.env" ] && source "$ROOT/.env"
: "${TIMEOUT:=10s}"
: "${TARGET_HOST:=svc.pwnable.xyz}"
: "${TARGET_PORT:=30000}"
exec timeout "$TIMEOUT" nc -nv "$TARGET_HOST" "$TARGET_PORT"
