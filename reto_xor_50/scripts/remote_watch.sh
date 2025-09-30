#!/usr/bin/env bash
set -euo pipefail

HOSTS=("134.209.56.140" "167.71.125.23" "svc.pwnable.xyz")
PORT=30029
ATTEMPT_TIMEOUT="8s"
SLEEP_BETWEEN=2
LOG_DIR="/workspace/reto_xor_50/out"
mkdir -p "$LOG_DIR"

iteration=0
while true; do
	iteration=$((iteration+1))
	for host in "${HOSTS[@]}"; do
		stamp=$(date +%Y%m%d-%H%M%S)
		log="$LOG_DIR/remote_${host}_${PORT}_${stamp}.log"
		{
			printf "[iter %d] trying %s:%d\n" "$iteration" "$host" "$PORT"
			# minimal input to trigger one result write
			echo "1 2 0" | timeout "$ATTEMPT_TIMEOUT" nc -v -n "$host" "$PORT"
		} >"$log" 2>&1 || true
		echo "[iter $iteration] done $host, log: $log"
		sleep "$SLEEP_BETWEEN"
	done
done