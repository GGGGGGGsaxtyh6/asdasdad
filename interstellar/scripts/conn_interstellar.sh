#!/usr/bin/env bash
host="${1:-94.237.55.43}"
port="${2:-36908}"
secs="${3:-10}"
/ workspace/interstellar/scripts/nc_timeout.sh "$secs" "$host" "$port"
