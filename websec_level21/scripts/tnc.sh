#!/usr/bin/env bash
# netcat with timeout wrapper
if [ $# -lt 2 ]; then echo "Usage: $0 <host> <port> [nc args...]"; exit 1; fi
host="$1"; port="$2"; shift 2
timeout 10s nc -v "$host" "$port" "$@"
