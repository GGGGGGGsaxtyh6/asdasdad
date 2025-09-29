#!/usr/bin/env bash
set -euo pipefail
# nmap wrapper (scans can run without timeout per user policy)
exec nmap "$@" | cat
