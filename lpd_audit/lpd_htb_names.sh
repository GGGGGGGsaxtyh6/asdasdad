#!/bin/bash
TARGET="94.237.49.23"
PORT="37326"

names=("hackthebox" "htb" "HTB" "admin" "backdoor" "secret" "password" "debug" "root" "shell" "cmd" "exec" "system" "line")

for name in "${names[@]}"; do
    echo "=== Name: $name ==="
    echo -ne "\x04$name\n" | timeout 2s nc $TARGET $PORT | xxd | head -3
    echo ""
done