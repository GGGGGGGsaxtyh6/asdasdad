#!/usr/bin/env bash
set -euo pipefail

# Not executed automatically; prepared per request.

host="${1:-svc.pwnable.xyz}"
ports="${2:-30029}"

echo "nmap -Pn -sS -sV -p ${ports} ${host}"
echo "nmap -Pn -sC -sV -p ${ports} ${host}"

