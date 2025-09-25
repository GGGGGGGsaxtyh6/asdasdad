#!/usr/bin/env python3
import os

# Basic file analysis
filename = "secret.mp4"

if os.path.exists(filename):
    size = os.path.getsize(filename)
    print(f"File size: {size} bytes")
    
    # Read first 1KB
    with open(filename, 'rb') as f:
        header = f.read(1024)
    
    print(f"Header (first 16 bytes): {header[:16].hex()}")
    
    # Look for PK signature
    if b'PK' in header:
        print("Found ZIP signature in header!")
    
    # Read entire file in small chunks
    with open(filename, 'rb') as f:
        data = b''
        chunk_size = 1024
        while True:
            chunk = f.read(chunk_size)
            if not chunk:
                break
            data += chunk
            if len(data) > 100000:  # Limit to 100KB for now
                break
    
    print(f"Read {len(data)} bytes")
    
    # Look for common signatures
    signatures = [b'PK', b'\x89PNG', b'%PDF', b'\xFF\xD8']
    
    for sig in signatures:
        pos = data.find(sig)
        if pos != -1:
            print(f"Found {sig} at position {pos}")
    
    # Save some data for inspection
    with open('test.bin', 'wb') as f:
        f.write(data[:10000])  # First 10KB
    
    print("Saved first 10KB as test.bin")

else:
    print("File not found")