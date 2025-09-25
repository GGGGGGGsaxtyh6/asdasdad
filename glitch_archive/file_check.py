#!/usr/bin/env python3
import os

# Simple file check
filename = "secret.mp4"

if os.path.exists(filename):
    size = os.path.getsize(filename)
    print(f"Size: {size}")
    
    with open(filename, 'rb') as f:
        data = f.read()
    
    print(f"Read: {len(data)}")
    
    # Check for ZIP
    if b'PK' in data:
        print("ZIP found!")
    
    # Check for PNG
    if b'\x89PNG' in data:
        print("PNG found!")
    
    # Check for PDF
    if b'%PDF' in data:
        print("PDF found!")
    
    # Save first 1KB
    with open('first_1k.bin', 'wb') as f:
        f.write(data[:1024])
    
    print("Saved first 1KB")

else:
    print("No file")