#!/usr/bin/env bash
set -euo pipefail
LOGDIR="/workspace/reto_pwnable_xyz/sub_50/logs"
mkdir -p "$LOGDIR"
TS="$(date +%Y%m%d_%H%M%S)"
LOGFILE="$LOGDIR/run_${TS}.log"
echo "[+] $*" | tee -a "$LOGFILE"
"$@" 2>&1 | tee -a "$LOGFILE"
