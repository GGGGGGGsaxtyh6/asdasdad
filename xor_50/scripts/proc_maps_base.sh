#!/usr/bin/env bash
set -euo pipefail
BIN="/workspace/xor_50/image/challenge/challenge"
"$BIN" >/dev/null 2>&1 &
PID=$!
sleep 0.2
base=$(awk "/$(basename "$BIN")/ && /r-xp/ {print \$1}" /proc/$PID/maps | head -n1 | cut -d- -f1)
kill -9 "$PID" >/dev/null 2>&1 || true
if [ -z "$base" ]; then
	echo ""
	exit 1
fi
python3 - << PY
import sys
base = int("$base", 16)
win_off = 0x0a21
printf_got = 0x201fa0
exit_got = 0x201fe8
result_base = 0x202200
print(hex(base), hex(base+win_off))
print("idx_exit:", (exit_got - result_base)//8)
print("idx_printf:", (printf_got - result_base)//8)
PY
