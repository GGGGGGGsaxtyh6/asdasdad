#!/usr/bin/env python3
import os

# Simple analysis without complex operations
filename = "secret.mp4"

if os.path.exists(filename):
    size = os.path.getsize(filename)
    print(f"File size: {size} bytes")
    
    with open(filename, 'rb') as f:
        data = f.read()
    
    print(f"Read {len(data)} bytes")
    
    # Look for PK signature (ZIP files)
    pk_pos = data.find(b'PK')
    if pk_pos != -1:
        print(f"Found ZIP signature at position {pk_pos}")
    
    # Look for PNG signature
    png_pos = data.find(b'\x89PNG')
    if png_pos != -1:
        print(f"Found PNG signature at position {png_pos}")
    
    # Look for PDF signature  
    pdf_pos = data.find(b'%PDF')
    if pdf_pos != -1:
        print(f"Found PDF signature at position {pdf_pos}")
        
    # Save first 1KB for inspection
    with open('header.bin', 'wb') as f:
        f.write(data[:1024])
    print("Saved first 1KB as header.bin")
    
    # Save last 1KB for inspection
    with open('footer.bin', 'wb') as f:
        f.write(data[-1024:])
    print("Saved last 1KB as footer.bin")
    
else:
    print("File not found")