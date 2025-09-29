#!/usr/bin/env bash
set -euo pipefail
# curl with default timeout 10s unless overridden
secs="${1:-10}"
shift || true
/usr/bin/env bash /workspace/reto-rootme-ch16/bin/with-timeout.sh "$secs" curl -fsSL "$@" | cat
