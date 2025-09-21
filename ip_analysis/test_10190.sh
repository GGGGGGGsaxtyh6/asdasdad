#!/bin/bash

echo "Testing port 10190 TCP with different approaches..."

# Test 1: Basic connection
echo "Test 1: Basic connection"
timeout 3 nc -v 5.161.142.77 10190
echo

# Test 2: HTTP request
echo "Test 2: HTTP request"
timeout 3 bash -c 'echo -e "GET / HTTP/1.1\r\nHost: 5.161.142.77\r\n\r\n" | nc 5.161.142.77 10190'
echo

# Test 3: HTTPS request
echo "Test 3: HTTPS request"
timeout 3 bash -c 'echo -e "GET / HTTP/1.1\r\nHost: 5.161.142.77\r\n\r\n" | openssl s_client -connect 5.161.142.77:10190 -quiet 2>/dev/null'
echo

# Test 4: SSH-like connection
echo "Test 4: SSH-like connection"
timeout 3 bash -c 'echo "SSH-2.0-test" | nc 5.161.142.77 10190'
echo

# Test 5: Telnet-like connection
echo "Test 5: Telnet-like connection"
timeout 3 bash -c 'echo -e "\xff\xfd\x01\xff\xfd\x03" | nc 5.161.142.77 10190'
echo

echo "Testing completed."