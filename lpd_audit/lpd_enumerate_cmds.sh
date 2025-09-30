#!/bin/bash
TARGET="94.237.49.23"
PORT="37326"

echo "=== Enumeración de todos los comandos LPD ==="
for i in {1..9}; do
    echo "Comando 0x0$i con cola lp:"
    printf "\x0%d lp\n" "$i" | timeout 5s nc $TARGET $PORT | xxd | head -5
    echo "---"
done

echo ""
echo "=== Comando 0x04 sin newline ==="
printf "\x04lp" | timeout 5s nc $TARGET $PORT | xxd
echo ""

echo "=== Comando 0x04 con doble newline ==="
printf "\x04lp\n\n" | timeout 5s nc $TARGET $PORT | xxd
echo ""