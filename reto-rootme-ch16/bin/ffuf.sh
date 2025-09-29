#!/usr/bin/env bash
set -euo pipefail
# ffuf wrapper (scans can run without timeout per user policy)
exec ffuf "$@"
