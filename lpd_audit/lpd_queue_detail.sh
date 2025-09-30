#!/bin/bash
TARGET="94.237.49.23"
PORT="37326"

echo "=== Test 1: Long queue sin argumentos ==="
printf "\x04lp\n" | timeout 5s nc $TARGET $PORT
echo -e "\n---"

echo "=== Test 2: Long queue con espacio al final ==="
printf "\x04lp \n" | timeout 5s nc $TARGET $PORT
echo -e "\n---"

echo "=== Test 3: Short queue ==="
printf "\x03lp\n" | timeout 5s nc $TARGET $PORT
echo -e "\n---"

echo "=== Test 4: Long queue con usuario root ==="
printf "\x04lp root\n" | timeout 5s nc $TARGET $PORT
echo -e "\n---"

echo "=== Test 5: Long queue con usuario y job ==="
printf "\x04lp root 001\n" | timeout 5s nc $TARGET $PORT
echo -e "\n---"