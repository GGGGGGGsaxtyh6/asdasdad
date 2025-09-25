#!/usr/bin/env python3
import os

def main():
    filename = "secret.mp4"
    
    if not os.path.exists(filename):
        print("No file")
        return
    
    size = os.path.getsize(filename)
    print(f"Size: {size}")
    
    with open(filename, 'rb') as f:
        data = f.read()
    
    print(f"Data: {len(data)}")
    
    # Look for ZIP
    zip_pos = data.find(b'PK\x03\x04')
    if zip_pos != -1:
        print(f"ZIP at {zip_pos}")
        
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
    png_pos = data.find(b'\x89PNG')
    if png_pos != -1:
        print(f"PNG at {png_pos}")
        
        iend_pos = data.find(b'IEND\xaeB`\x82', png_pos)
        if iend_pos != -1:
            png_data = data[png_pos:iend_pos + 12]
            
            with open('hidden.png', 'wb') as f:
                f.write(png_data)
            
            print("Saved PNG")
    
    # Look for PDF
    pdf_pos = data.find(b'%PDF')
    if pdf_pos != -1:
        print(f"PDF at {pdf_pos}")
        
        eof_pos = data.find(b'%%EOF', pdf_pos)
        if eof_pos != -1:
            pdf_data = data[pdf_pos:eof_pos + 5]
            
            with open('hidden.pdf', 'wb') as f:
                f.write(pdf_data)
            
            print("Saved PDF")
    
    # If no signatures, try extraction
    if zip_pos == -1 and png_pos == -1 and pdf_pos == -1:
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

if __name__ == "__main__":
    main()