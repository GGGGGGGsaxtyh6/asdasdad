#!/usr/bin/env bash
set -euo pipefail

if [[ $# -lt 2 ]]; then
  echo "Usage: $0 <host> <port> [timeout_secs]" >&2
  exit 1
fi

host="$1"; port="$2"; secs="${3:-10}"

/workspace/reto-rootme-ch16/bin/nc-timeout.sh "$secs" -v "$host" "$port"
