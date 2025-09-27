#!/bin/bash

echo "=== Analyzing i_spy executable ==="
strings i_spy | grep -E "(flag|progress|SDL|image|load|render)" | head -10

echo -e "\n=== Analyzing flag.bin ==="
hexdump -C flag.bin | head -10

echo -e "\n=== File sizes ==="
ls -la flag.bin i_spy

echo -e "\n=== Checking if flag.bin is an image ==="
file flag.bin