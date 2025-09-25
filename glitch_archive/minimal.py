#!/usr/bin/env python3
import os

filename = "secret.mp4"

if os.path.exists(filename):
    size = os.path.getsize(filename)
    print(f"Size: {size}")
    
    with open(filename, 'rb') as f:
        data = f.read()
    
    print(f"Data: {len(data)}")
    
    if b'PK' in data:
        print("ZIP")
    
    if b'\x89PNG' in data:
        print("PNG")
    
    if b'%PDF' in data:
        print("PDF")

else:
    print("No file")