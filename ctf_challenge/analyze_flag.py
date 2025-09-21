#!/usr/bin/env python3

import sys
import binascii
import zlib
import gzip
import base64

def analyze_binary_data():
    # Read the flag.txt file
    try:
        with open('flag.txt', 'rb') as f:
            data = f.read()
    except FileNotFoundError:
        print("flag.txt not found")
        return

    print(f"File size: {len(data)} bytes")
    print(f"First 20 bytes: {data[:20]}")
    print(f"Last 20 bytes: {data[-20:]}")

    # Try different decoding methods
    try:
        # Try base64 decode
        decoded = base64.b64decode(data)
        print(f"Base64 decoded size: {len(decoded)}")
        try:
            text = decoded.decode('utf-8')
            if any(c in text for c in 'FLAG{'):
                print(f"Found potential flag in base64: {text[:200]}")
        except:
            pass
    except:
        pass

    # Try zlib decompress
    try:
        decompressed = zlib.decompress(data)
        print(f"Zlib decompressed size: {len(decompressed)}")
        try:
            text = decompressed.decode('utf-8')
            if any(c in text for c in 'FLAG{'):
                print(f"Found potential flag in zlib: {text[:200]}")
        except:
            pass
    except:
        pass

    # Try gzip decompress
    try:
        decompressed = gzip.decompress(data)
        print(f"Gzip decompressed size: {len(decompressed)}")
        try:
            text = decompressed.decode('utf-8')
            if any(c in text for c in 'FLAG{'):
                print(f"Found potential flag in gzip: {text[:200]}")
        except:
            pass
    except:
        pass

    # Look for common file signatures
    signatures = {
        b'\x50\x4B\x03\x04': 'ZIP',
        b'\x50\x4B\x05\x06': 'ZIP (empty)',
        b'\x50\x4B\x07\x08': 'ZIP (spanned)',
        b'\x37\x7A\xBC\xAF\x27\x1C': '7-ZIP',
        b'\x52\x61\x72\x21\x1A\x07\x00': 'RAR v1.50+',
        b'\x52\x61\x72\x21\x1A\x07\x01\x00': 'RAR v5.0+',
        b'\x42\x5A\x68': 'BZIP2',
        b'\x1F\x8B\x08': 'GZIP',
        b'\xFD\x37\x7A\x58\x5A\x00': 'XZ',
        b'\x04\x22\x4D\x18': 'LZ4',
    }

    for sig, name in signatures.items():
        if data.startswith(sig):
            print(f"Detected file type: {name}")
            break

    # Try to find printable strings
    printable = ''.join(chr(c) if 32 <= c <= 126 else '.' for c in data)
    print(f"Printable chars: {printable[:100]}")

if __name__ == "__main__":
    analyze_binary_data()