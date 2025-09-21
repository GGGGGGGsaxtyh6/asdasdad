#!/usr/bin/env python3

# Read the HTML file
with open('challenge.html', 'r') as f:
    content = f.read()

# Extract all binary sequences
import re
binary_sequences = re.findall(r'[01]{8}', content)

print(f"Found {len(binary_sequences)} binary sequences")

# Convert each 8-bit chunk to ASCII
result = ""
for chunk in binary_sequences:
    ascii_char = chr(int(chunk, 2))
    result += ascii_char

print("Full decoded message:")
print(result)

# Also try to look for any patterns that might be flags
print("\nLooking for flag patterns...")
if "FLAG" in result.upper():
    print("Found FLAG in message")
if "RINGZER0" in result.upper():
    print("Found RINGZER0 in message")
if "CTF" in result.upper():
    print("Found CTF in message")