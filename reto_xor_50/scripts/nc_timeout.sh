#!/usr/bin/env bash
set -euo pipefail

# Usage: nc_timeout.sh <seconds> <host> <port>

if [[ $# -lt 3 ]]; then
  echo "Usage: $0 <seconds> <host> <port>" >&2
  exit 1
fi

seconds=$1; host=$2; port=$3

exec timeout "$seconds" bash -c "exec nc -v $host $port" | cat

