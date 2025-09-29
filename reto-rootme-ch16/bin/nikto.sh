#!/usr/bin/env bash
set -euo pipefail
# nikto wrapper (scans can run without timeout per user policy)
exec nikto "$@" | cat
