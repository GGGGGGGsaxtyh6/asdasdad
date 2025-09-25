#!/usr/bin/env python3
import os
import struct

def analyze_video_file(filename):
    """Analyze video file for hidden data patterns"""
    
    if not os.path.exists(filename):
        print(f"Error: File {filename} not found")
        return
    
    file_size = os.path.getsize(filename)
    print(f"File: {filename}")
    print(f"Size: {file_size} bytes")
    print()
    
    # Read the entire file
    with open(filename, 'rb') as f:
        data = f.read()
    
    print(f"Read {len(data)} bytes from file")
    
    # Look for common file signatures
    signatures = {
        b'PK': 'ZIP/Office file',
        b'\x89PNG': 'PNG image', 
        b'\xFF\xD8\xFF': 'JPEG image',
        b'GIF8': 'GIF image',
        b'%PDF': 'PDF file',
        b'\x50\x4B\x03\x04': 'ZIP file',
        b'RIFF': 'WAV/AVI file',
        b'\x1F\x8B': 'GZIP file',
        b'\x7F\x45\x4C\x46': 'ELF executable',
        b'\x4D\x5A': 'Windows PE executable'
    }
    
    print("\nSearching for file signatures in video data...")
    found_signatures = []
    
    for sig, desc in signatures.items():
        pos = data.find(sig)
        if pos != -1:
            print(f"Found {desc} signature at position {pos}")
            found_signatures.append((pos, sig, desc))
    
    if not found_signatures:
        print("No common file signatures found")
    
    # Look for patterns that might indicate hidden data
    print("\nAnalyzing data patterns...")
    
    # Check for repeating patterns
    chunk_size = 1024
    chunks = [data[i:i+chunk_size] for i in range(0, len(data), chunk_size)]
    
    print(f"Divided into {len(chunks)} chunks of {chunk_size} bytes")
    
    # Look for chunks with unusual entropy or patterns
    for i, chunk in enumerate(chunks):
        if len(chunk) < chunk_size:
            continue
            
        # Check for high entropy (random-looking data)
        unique_bytes = len(set(chunk))
        entropy_ratio = unique_bytes / len(chunk)
        
        if entropy_ratio > 0.8:  # High entropy
            print(f"High entropy chunk at position {i*chunk_size} (ratio: {entropy_ratio:.3f})")
            
            # Save this chunk for analysis
            with open(f"chunk_{i:03d}.bin", 'wb') as f:
                f.write(chunk)
            print(f"  Saved as chunk_{i:03d}.bin")
    
    # Try to extract data using different approaches
    print("\nAttempting data extraction...")
    
    # Method 1: Extract every nth byte (common steganography technique)
    for step in [2, 4, 8, 16]:
        extracted = data[::step]
        if len(extracted) > 100:  # Only if we get meaningful amount of data
            filename = f"extracted_step_{step}.bin"
            with open(filename, 'wb') as f:
                f.write(extracted)
            print(f"Extracted data with step {step} -> {filename}")
            
            # Check if extracted data has file signatures
            for sig, desc in signatures.items():
                if sig in extracted:
                    print(f"  Found {desc} signature in extracted data!")
    
    # Method 2: Look for base64-like patterns
    import base64
    import re
    
    # Find potential base64 strings
    b64_pattern = re.compile(rb'[A-Za-z0-9+/]{20,}={0,2}')
    matches = b64_pattern.findall(data)
    
    if matches:
        print(f"\nFound {len(matches)} potential base64 strings")
        for i, match in enumerate(matches[:5]):  # Show first 5
            try:
                decoded = base64.b64decode(match)
                print(f"Base64 match {i}: {match[:20]}... -> decoded {len(decoded)} bytes")
            except:
                pass
    
    print("\nAnalysis complete!")

if __name__ == "__main__":
    analyze_video_file("secret.mp4")