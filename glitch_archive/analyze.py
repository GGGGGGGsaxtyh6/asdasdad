#!/usr/bin/env python3
import os
import binascii

def main():
    filename = "secret.mp4"
    
    if not os.path.exists(filename):
        print("File not found")
        return
    
    size = os.path.getsize(filename)
    print(f"File size: {size} bytes")
    
    # Read file
    with open(filename, 'rb') as f:
        data = f.read()
    
    print(f"Read {len(data)} bytes")
    
    # Look for ZIP signature
    zip_pos = data.find(b'PK\x03\x04')
    if zip_pos != -1:
        print(f"Found ZIP file at position {zip_pos}")
        
        # Try to extract ZIP
        # Look for end of ZIP
        end_pos = data.find(b'PK\x05\x06', zip_pos)
        if end_pos != -1:
            zip_data = data[zip_pos:end_pos + 22]
            
            with open('extracted.zip', 'wb') as f:
                f.write(zip_data)
            
            print("Extracted ZIP file as extracted.zip")
            
            # Try to read ZIP contents
            import zipfile
            try:
                with zipfile.ZipFile('extracted.zip', 'r') as zf:
                    print("ZIP contents:")
                    for name in zf.namelist():
                        print(f"  - {name}")
                    
                    # Extract all files
                    zf.extractall('extracted/')
                    print("Extracted all files to extracted/ directory")
                    
            except Exception as e:
                print(f"Error reading ZIP: {e}")
    
    # Look for PNG signature
    png_pos = data.find(b'\x89PNG')
    if png_pos != -1:
        print(f"Found PNG file at position {png_pos}")
        
        # Look for PNG end
        iend_pos = data.find(b'IEND\xaeB`\x82', png_pos)
        if iend_pos != -1:
            png_data = data[png_pos:iend_pos + 12]
            
            with open('extracted.png', 'wb') as f:
                f.write(png_data)
            
            print("Extracted PNG file as extracted.png")
    
    # Look for PDF signature
    pdf_pos = data.find(b'%PDF')
    if pdf_pos != -1:
        print(f"Found PDF file at position {pdf_pos}")
        
        # Look for PDF end
        eof_pos = data.find(b'%%EOF', pdf_pos)
        if eof_pos != -1:
            pdf_data = data[pdf_pos:eof_pos + 5]
            
            with open('extracted.pdf', 'wb') as f:
                f.write(pdf_data)
            
            print("Extracted PDF file as extracted.pdf")
    
    # If no signatures found, try extraction methods
    if zip_pos == -1 and png_pos == -1 and pdf_pos == -1:
        print("No file signatures found, trying extraction methods...")
        
        # Method 1: Extract every 4th byte
        extracted = data[::4]
        with open('extracted_step4.bin', 'wb') as f:
            f.write(extracted)
        print(f"Extracted every 4th byte: {len(extracted)} bytes")
        
        # Method 2: Extract every 8th byte
        extracted = data[::8]
        with open('extracted_step8.bin', 'wb') as f:
            f.write(extracted)
        print(f"Extracted every 8th byte: {len(extracted)} bytes")
        
        # Method 3: Extract every 16th byte
        extracted = data[::16]
        with open('extracted_step16.bin', 'wb') as f:
            f.write(extracted)
        print(f"Extracted every 16th byte: {len(extracted)} bytes")
        
        # Check extracted data for signatures
        for step in [4, 8, 16]:
            filename = f'extracted_step{step}.bin'
            if os.path.exists(filename):
                with open(filename, 'rb') as f:
                    extracted_data = f.read()
                
                if b'PK' in extracted_data:
                    print(f"Found ZIP signature in {filename}")
                if b'\x89PNG' in extracted_data:
                    print(f"Found PNG signature in {filename}")
                if b'%PDF' in extracted_data:
                    print(f"Found PDF signature in {filename}")

if __name__ == "__main__":
    main()