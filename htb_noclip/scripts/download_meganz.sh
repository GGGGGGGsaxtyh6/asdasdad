#!/usr/bin/env bash
set -euo pipefail
if [[ $# -lt 2 ]]; then
  echo "Usage: $0 <seconds> <mega_url>" >&2
  exit 1
fi
secs="$1"; shift
mega_url="$1"; shift || true
cd /workspace/htb_noclip/downloads
/usr/bin/timeout --preserve-status --signal=INT --kill-after=2s "$secs" megadl "$mega_url"
