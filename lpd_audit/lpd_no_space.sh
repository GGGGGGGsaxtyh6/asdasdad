#!/bin/bash
TARGET="94.237.49.23"
PORT="37326"

echo "=== Enumeración sin espacio extra ==="
for i in {1..9}; do
    echo "Comando 0x0$i:"
    printf "\x0${i}lp\n" | timeout 5s nc $TARGET $PORT | xxd | head -5
    echo "---"
done