#!/bin/bash
TARGET="94.237.49.23"
PORT="37326"

# Comando 0x02 + nombre de cola
# Formato: \x02<queue_name>\n

echo "=== Test Receive Job: cola lp ==="
echo -ne "\x02lp\n" | timeout 5s nc $TARGET $PORT | xxd
echo ""

echo "=== Test Receive Job: cola printer ==="
echo -ne "\x02printer\n" | timeout 5s nc $TARGET $PORT | xxd
echo ""

echo "=== Test Receive Job: cola test ==="
echo -ne "\x02test\n" | timeout 5s nc $TARGET $PORT | xxd
echo ""

echo "=== Test Receive Job: cola vacía ==="
echo -ne "\x02\n" | timeout 5s nc $TARGET $PORT | xxd
echo ""