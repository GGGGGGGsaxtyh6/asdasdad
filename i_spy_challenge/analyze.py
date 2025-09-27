#!/usr/bin/env python3
import os
import struct

print("=== File Analysis ===")

# Check file sizes
flag_size = os.path.getsize('flag.bin')
i_spy_size = os.path.getsize('i_spy')

print(f"flag.bin size: {flag_size} bytes")
print(f"i_spy size: {i_spy_size} bytes")

# Read first bytes of flag.bin
with open('flag.bin', 'rb') as f:
    header = f.read(64)
    print(f"\nflag.bin header (hex): {header[:32].hex()}")
    
    # Check for common file signatures
    if header.startswith(b'\x89PNG'):
        print("flag.bin appears to be a PNG image")
    elif header.startswith(b'\xff\xd8\xff'):
        print("flag.bin appears to be a JPEG image")
    elif header.startswith(b'BM'):
        print("flag.bin appears to be a BMP image")
    elif header.startswith(b'GIF8'):
        print("flag.bin appears to be a GIF image")
    else:
        print("flag.bin doesn't match common image formats")

# Try to extract strings from i_spy
print("\n=== Extracting strings from i_spy ===")
with open('i_spy', 'rb') as f:
    data = f.read()
    strings = []
    current = b''
    
    for byte in data:
        if 32 <= byte <= 126:  # Printable ASCII
            current += bytes([byte])
        else:
            if len(current) >= 4:
                strings.append(current.decode('ascii'))
            current = b''
    
    # Look for interesting strings
    interesting = [s for s in strings if any(keyword in s.lower() for keyword in ['flag', 'progress', 'sdl', 'image', 'load', 'render', 'window', 'texture'])]
    print("Interesting strings found:")
    for s in interesting[:10]:
        print(f"  {s}")