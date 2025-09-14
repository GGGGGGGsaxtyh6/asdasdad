#!/usr/bin/env python3

import struct

# Read the binary
with open('shellcode', 'rb') as f:
    data = f.read()

# Find the .text section (starts at 0x8049000)
text_start = 0x8049000
text_offset = text_start - 0x8048000  # Adjust for ELF header

# Extract shellcode bytes
shellcode = data[text_offset:text_offset+0x4f]  # 0x4f bytes of shellcode

print("Shellcode bytes:")
print(''.join(f'\\x{b:02x}' for b in shellcode))

print("\nShellcode in hex:")
print(' '.join(f'{b:02x}' for b in shellcode))

print(f"\nLength: {len(shellcode)} bytes")

# Write to file
with open('shellcode.bin', 'wb') as f:
    f.write(shellcode)