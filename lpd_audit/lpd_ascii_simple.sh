#!/bin/bash
TARGET="94.237.49.23"
PORT="37326"

commands=("ls" "cat" "read" "get" "fetch" "show" "print" "file" "flag" "test")

for cmd in "${commands[@]}"; do
    echo "=== Command: $cmd ==="
    echo "$cmd" | timeout 2s nc $TARGET $PORT | xxd | head -5
    echo ""
done