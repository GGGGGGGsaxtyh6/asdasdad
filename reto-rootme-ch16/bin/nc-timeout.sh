#!/usr/bin/env bash
set -euo pipefail
secs="${1:-10}"
shift || true
/usr/bin/env bash /workspace/reto-rootme-ch16/bin/with-timeout.sh "$secs" nc "$@"
