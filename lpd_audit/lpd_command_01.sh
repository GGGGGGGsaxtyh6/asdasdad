#!/bin/bash
TARGET="94.237.49.23"
PORT="37326"

echo "=== Test comando 0x01 (print any waiting jobs) ==="
printf "\x01lp\n" | timeout 5s nc $TARGET $PORT | xxd
echo ""

echo "=== Test comando 0x01 con diferentes colas ==="
for queue in "" "lp" "printer" "test" "flag"; do
    echo "Queue: $queue"
    printf "\x01%s\n" "$queue" | timeout 5s nc $TARGET $PORT | xxd | head -10
    echo "---"
done