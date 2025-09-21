#!/usr/bin/env python3

# Read the binary data
with open('binary_data.txt', 'r') as f:
    binary_data = f.read().strip()

# Split into 8-bit chunks and convert to ASCII
result = ""
for i in range(0, len(binary_data), 8):
    chunk = binary_data[i:i+8]
    if len(chunk) == 8:
        ascii_char = chr(int(chunk, 2))
        result += ascii_char

print("Decoded message:")
print(result)