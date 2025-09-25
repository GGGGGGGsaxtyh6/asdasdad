#!/usr/bin/env python3
"""
Autonomous video analysis script for Glitch Archive challenge
This script will analyze secret.mp4 to find hidden files without relying on external tools
"""

import os
import zipfile
import sys

def main():
    print("=== Glitch Archive Challenge - Autonomous Extractor ===")
    
    filename = "secret.mp4"
    
    # Check if file exists
    if not os.path.exists(filename):
        print(f"ERROR: {filename} not found!")
        return False
    
    # Get file info
    size = os.path.getsize(filename)
    print(f"File: {filename}")
    print(f"Size: {size} bytes")
    print()
    
    # Read the entire video file
    print("Reading video file...")
    try:
        with open(filename, 'rb') as f:
            data = f.read()
        print(f"Successfully read {len(data)} bytes")
    except Exception as e:
        print(f"ERROR reading file: {e}")
        return False
    
    # Look for embedded file signatures
    print("\n=== Searching for embedded files ===")
    
    signatures_found = []
    
    # 1. Look for ZIP files (most common for challenges)
    zip_pos = data.find(b'PK\x03\x04')
    if zip_pos != -1:
        print(f"✓ ZIP file signature found at position {zip_pos}")
        signatures_found.append(('ZIP', zip_pos))
        
        # Find end of ZIP file
        end_pos = data.find(b'PK\x05\x06', zip_pos)
        if end_pos != -1:
            # Extract ZIP data
            zip_data = data[zip_pos:end_pos + 22]
            
            # Save ZIP file
            with open('extracted.zip', 'wb') as f:
                f.write(zip_data)
            print(f"  Extracted ZIP file ({len(zip_data)} bytes) -> extracted.zip")
            
            # Try to open and extract ZIP contents
            try:
                with zipfile.ZipFile('extracted.zip', 'r') as zf:
                    print("  ZIP file contents:")
                    for name in zf.namelist():
                        print(f"    - {name}")
                    
                    # Extract all files
                    os.makedirs('extracted_files', exist_ok=True)
                    zf.extractall('extracted_files/')
                    print("  ✓ All files extracted to extracted_files/")
                    
                    # Look for flag in extracted files
                    for name in zf.namelist():
                        file_path = os.path.join('extracted_files', name)
                        if os.path.isfile(file_path):
                            try:
                                with open(file_path, 'r', encoding='utf-8', errors='ignore') as f:
                                    content = f.read()
                                    if 'FGTE{' in content or 'flag' in content.lower():
                                        print(f"  🎯 POTENTIAL FLAG FOUND in {name}:")
                                        print(f"     {content}")
                            except:
                                # Try reading as binary
                                try:
                                    with open(file_path, 'rb') as f:
                                        content = f.read()
                                        if b'FGTE{' in content:
                                            print(f"  🎯 POTENTIAL FLAG FOUND in {name}:")
                                            print(f"     {content}")
                                except:
                                    pass
                    
            except Exception as e:
                print(f"  ERROR reading ZIP file: {e}")
    
    # 2. Look for PNG files
    png_pos = data.find(b'\x89PNG\r\n\x1a\n')
    if png_pos != -1:
        print(f"✓ PNG file signature found at position {png_pos}")
        signatures_found.append(('PNG', png_pos))
        
        # Find end of PNG (IEND chunk)
        iend_pos = data.find(b'IEND\xaeB`\x82', png_pos)
        if iend_pos != -1:
            png_data = data[png_pos:iend_pos + 12]
            
            with open('extracted.png', 'wb') as f:
                f.write(png_data)
            print(f"  Extracted PNG file ({len(png_data)} bytes) -> extracted.png")
    
    # 3. Look for PDF files
    pdf_pos = data.find(b'%PDF')
    if pdf_pos != -1:
        print(f"✓ PDF file signature found at position {pdf_pos}")
        signatures_found.append(('PDF', pdf_pos))
        
        # Find end of PDF
        eof_pos = data.find(b'%%EOF', pdf_pos)
        if eof_pos != -1:
            pdf_data = data[pdf_pos:eof_pos + 5]
            
            with open('extracted.pdf', 'wb') as f:
                f.write(pdf_data)
            print(f"  Extracted PDF file ({len(pdf_data)} bytes) -> extracted.pdf")
    
    # 4. Look for text files or other patterns
    fgte_pos = data.find(b'FGTE{')
    if fgte_pos != -1:
        print(f"✓ Flag pattern found at position {fgte_pos}")
        # Extract surrounding text
        start = max(0, fgte_pos - 50)
        end = min(len(data), fgte_pos + 200)
        flag_context = data[start:end]
        print(f"  🎯 FLAG CONTEXT: {flag_context}")
    
    # If no direct signatures found, try extraction methods
    if not signatures_found:
        print("\n=== No direct signatures found, trying extraction methods ===")
        
        # Method 1: Extract every nth byte (common steganography)
        for step in [2, 4, 8, 16, 32]:
            extracted = data[::step]
            output_file = f'extracted_step_{step}.bin'
            
            with open(output_file, 'wb') as f:
                f.write(extracted)
            
            print(f"Step {step}: Extracted {len(extracted)} bytes -> {output_file}")
            
            # Check extracted data for signatures
            for sig_name, sig_bytes in [('ZIP', b'PK\x03\x04'), ('PNG', b'\x89PNG'), ('FLAG', b'FGTE{')]:
                if sig_bytes in extracted:
                    print(f"  🎯 Found {sig_name} signature in extracted data!")
                    if sig_name == 'FLAG':
                        pos = extracted.find(b'FGTE{')
                        flag_context = extracted[pos:pos+100]
                        print(f"     FLAG: {flag_context}")
    
    print("\n=== Analysis Complete ===")
    return True

if __name__ == "__main__":
    success = main()
    if success:
        print("✓ Extraction completed successfully!")
        
        # List all created files
        created_files = []
        for f in os.listdir('.'):
            if f.startswith('extracted') or f == 'hidden.zip' or f == 'hidden.png':
                created_files.append(f)
        
        if created_files:
            print("\nFiles created:")
            for f in created_files:
                print(f"  - {f}")
        
        # Check if extracted_files directory exists
        if os.path.exists('extracted_files'):
            print("\nExtracted files directory contents:")
            for root, dirs, files in os.walk('extracted_files'):
                for file in files:
                    file_path = os.path.join(root, file)
                    print(f"  - {file_path}")
    else:
        print("✗ Extraction failed!")