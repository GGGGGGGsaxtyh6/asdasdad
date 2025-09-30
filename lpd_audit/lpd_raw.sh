#!/bin/bash
TARGET="94.237.49.23"
PORT="37326"

echo "=== Test RAW: enviar comando 0x04lp ==="
echo -ne "\x04lp\n" | timeout 5s nc $TARGET $PORT | xxd
echo ""

echo "=== Test RAW: solo conectar ==="
timeout 5s nc $TARGET $PORT < /dev/null | xxd
echo ""

echo "=== Test RAW: comando 0x01 (print) ==="
echo -ne "\x01\n" | timeout 5s nc $TARGET $PORT | xxd
echo ""