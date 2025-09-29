#!/usr/bin/env bash
set -euo pipefail

# Usage: nmap_basic.sh HOST [PORTS]
HOST="${1:-}"
PORTS="${2:-}"

if [[ -z "$HOST" ]]; then
  echo "Usage: $0 HOST [PORTS]" >&2
  exit 1
fi

if [[ -n "$PORTS" ]]; then
  nmap -sC -sV -Pn -p "$PORTS" "$HOST" | cat
else
  nmap -sC -sV -Pn -p- --min-rate 2000 "$HOST" | cat
fi

