#!/usr/bin/env python3
import os

filename = "secret.mp4"

if os.path.exists(filename):
    size = os.path.getsize(filename)
    print(f"Size: {size}")
    
    with open(filename, 'rb') as f:
        data = f.read()
    
    print(f"Data: {len(data)}")
    
    # Look for ZIP
    if b'PK\x03\x04' in data:
        print("ZIP found")
        
        zip_pos = data.find(b'PK\x03\x04')
        end_pos = data.find(b'PK\x05\x06', zip_pos)
        
        if end_pos != -1:
            zip_data = data[zip_pos:end_pos + 22]
            
            with open('hidden.zip', 'wb') as f:
                f.write(zip_data)
            
            print("Saved ZIP")
            
            import zipfile
            try:
                with zipfile.ZipFile('hidden.zip', 'r') as zf:
                    print("ZIP contents:")
                    for name in zf.namelist():
                        print(f"  {name}")
                    
                    zf.extractall('hidden/')
                    print("Extracted")
                    
            except Exception as e:
                print(f"Error: {e}")
    
    # Look for PNG
    if b'\x89PNG' in data:
        print("PNG found")
        
        png_pos = data.find(b'\x89PNG')
        iend_pos = data.find(b'IEND\xaeB`\x82', png_pos)
        
        if iend_pos != -1:
            png_data = data[png_pos:iend_pos + 12]
            
            with open('hidden.png', 'wb') as f:
                f.write(png_data)
            
            print("Saved PNG")
    
    # Look for PDF
    if b'%PDF' in data:
        print("PDF found")
        
        pdf_pos = data.find(b'%PDF')
        eof_pos = data.find(b'%%EOF', pdf_pos)
        
        if eof_pos != -1:
            pdf_data = data[pdf_pos:eof_pos + 5]
            
            with open('hidden.pdf', 'wb') as f:
                f.write(pdf_data)
            
            print("Saved PDF")
    
    # If no signatures, try extraction
    if b'PK\x03\x04' not in data and b'\x89PNG' not in data and b'%PDF' not in data:
        print("No signatures, trying extraction...")
        
        # Extract every 4th byte
        extracted = data[::4]
        with open('step4.bin', 'wb') as f:
            f.write(extracted)
        print(f"Step 4: {len(extracted)}")
        
        # Extract every 8th byte
        extracted = data[::8]
        with open('step8.bin', 'wb') as f:
            f.write(extracted)
        print(f"Step 8: {len(extracted)}")
        
        # Extract every 16th byte
        extracted = data[::16]
        with open('step16.bin', 'wb') as f:
            f.write(extracted)
        print(f"Step 16: {len(extracted)}")
        
        # Check extracted data
        for step in [4, 8, 16]:
            filename = f'step{step}.bin'
            if os.path.exists(filename):
                with open(filename, 'rb') as f:
                    extracted_data = f.read()
                
                if b'PK' in extracted_data:
                    print(f"ZIP in {filename}")
                if b'\x89PNG' in extracted_data:
                    print(f"PNG in {filename}")
                if b'%PDF' in extracted_data:
                    print(f"PDF in {filename}")

else:
    print("No file")