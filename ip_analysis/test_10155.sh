#!/bin/bash

echo "Testing port 10155 UDP with different payloads..."

# Test 1: Basic text
echo "Test 1: Basic text"
echo "test" | timeout 3 nc -u 5.161.142.77 10155
echo

# Test 2: Binary data
echo "Test 2: Binary data"
echo -e "\x00\x01\x02\x03\x04\x05" | timeout 3 nc -u 5.161.142.77 10155
echo

# Test 3: JSON-like data
echo "Test 3: JSON-like data"
echo '{"test": "data"}' | timeout 3 nc -u 5.161.142.77 10155
echo

# Test 4: HTTP-like request
echo "Test 4: HTTP-like request"
echo -e "GET / HTTP/1.1\r\nHost: 5.161.142.77\r\n\r\n" | timeout 3 nc -u 5.161.142.77 10155
echo

# Test 5: DNS-like query
echo "Test 5: DNS-like query"
echo -e "\x00\x01\x01\x00\x00\x01\x00\x00\x00\x00\x00\x00\x07example\x03com\x00\x00\x01\x00\x01" | timeout 3 nc -u 5.161.142.77 10155
echo

# Test 6: NTP-like packet
echo "Test 6: NTP-like packet"
echo -e "\x1b\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00" | timeout 3 nc -u 5.161.142.77 10155
echo

echo "Testing completed."