#!/usr/bin/env bash
# curl with timeout wrapper
if [ -z "$1" ]; then echo "Usage: $0 <url> [curl args...]"; exit 1; fi
URL="$1"; shift
timeout 10s curl -fsSL -m 9 --connect-timeout 4 "$URL" "$@"
