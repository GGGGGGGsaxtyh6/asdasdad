#!/bin/bash
TARGET="94.237.49.23"
PORT="37326"

echo "=== Test 1: Comando 0x04 (long queue) con cola vacía ==="
echo -ne "\x04\n" | timeout 5s nc $TARGET $PORT
echo ""

echo "=== Test 2: Comando 0x04 con cola 'lp' ==="
echo -ne "\x04lp\n" | timeout 5s nc $TARGET $PORT
echo ""

echo "=== Test 3: Comando 0x04 con cola 'printer' ==="
echo -ne "\x04printer\n" | timeout 5s nc $TARGET $PORT
echo ""

echo "=== Test 4: Comando 0x03 (short queue) con cola 'lp' ==="
echo -ne "\x03lp\n" | timeout 5s nc $TARGET $PORT
echo ""