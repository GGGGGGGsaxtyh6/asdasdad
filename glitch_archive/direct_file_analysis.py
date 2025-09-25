#!/usr/bin/env python3

import os
import zipfile

def analyze_video():
    filename = "secret.mp4"
    
    if not os.path.exists(filename):
        with open('analysis_result.txt', 'w') as f:
            f.write("ERROR: secret.mp4 not found\n")
        return
    
    # Read file
    with open(filename, 'rb') as f:
        data = f.read()
    
    size = len(data)
    
    # Write analysis results to file
    with open('analysis_result.txt', 'w') as f:
        f.write(f"=== Glitch Archive Analysis ===\n")
        f.write(f"File: {filename}\n")
        f.write(f"Size: {size} bytes\n\n")
        
        # Look for ZIP signature
        zip_pos = data.find(b'PK\x03\x04')
        if zip_pos != -1:
            f.write(f"ZIP signature found at position {zip_pos}\n")
            
            # Find end of ZIP
            end_pos = data.find(b'PK\x05\x06', zip_pos)
            if end_pos != -1:
                zip_data = data[zip_pos:end_pos + 22]
                
                # Save ZIP file
                with open('hidden.zip', 'wb') as zip_f:
                    zip_f.write(zip_data)
                
                f.write(f"Extracted ZIP file: hidden.zip ({len(zip_data)} bytes)\n")
                
                # Try to extract ZIP contents
                try:
                    with zipfile.ZipFile('hidden.zip', 'r') as zf:
                        f.write("ZIP contents:\n")
                        for name in zf.namelist():
                            f.write(f"  - {name}\n")
                        
                        # Extract all files
                        os.makedirs('extracted', exist_ok=True)
                        zf.extractall('extracted/')
                        f.write("Files extracted to extracted/ directory\n")
                        
                        # Look for flags in extracted files
                        for name in zf.namelist():
                            file_path = os.path.join('extracted', name)
                            if os.path.isfile(file_path):
                                try:
                                    with open(file_path, 'r', encoding='utf-8', errors='ignore') as txt_f:
                                        content = txt_f.read()
                                        if 'FGTE{' in content:
                                            f.write(f"FLAG FOUND in {name}: {content}\n")
                                except:
                                    try:
                                        with open(file_path, 'rb') as bin_f:
                                            content = bin_f.read()
                                            if b'FGTE{' in content:
                                                f.write(f"FLAG FOUND in {name}: {content}\n")
                                    except:
                                        pass
                
                except Exception as e:
                    f.write(f"Error reading ZIP: {e}\n")
        
        # Look for PNG signature
        png_pos = data.find(b'\x89PNG')
        if png_pos != -1:
            f.write(f"PNG signature found at position {png_pos}\n")
            
            iend_pos = data.find(b'IEND\xaeB`\x82', png_pos)
            if iend_pos != -1:
                png_data = data[png_pos:iend_pos + 12]
                
                with open('hidden.png', 'wb') as png_f:
                    png_f.write(png_data)
                
                f.write(f"Extracted PNG file: hidden.png ({len(png_data)} bytes)\n")
        
        # Look for PDF signature
        pdf_pos = data.find(b'%PDF')
        if pdf_pos != -1:
            f.write(f"PDF signature found at position {pdf_pos}\n")
            
            eof_pos = data.find(b'%%EOF', pdf_pos)
            if eof_pos != -1:
                pdf_data = data[pdf_pos:eof_pos + 5]
                
                with open('hidden.pdf', 'wb') as pdf_f:
                    pdf_f.write(pdf_data)
                
                f.write(f"Extracted PDF file: hidden.pdf ({len(pdf_data)} bytes)\n")
        
        # Look for direct flag
        flag_pos = data.find(b'FGTE{')
        if flag_pos != -1:
            flag_context = data[flag_pos:flag_pos + 100]
            f.write(f"FLAG PATTERN FOUND at position {flag_pos}: {flag_context}\n")
        
        # If no signatures found, try extraction methods
        if zip_pos == -1 and png_pos == -1 and pdf_pos == -1:
            f.write("No file signatures found, trying extraction methods...\n")
            
            for step in [2, 4, 8, 16]:
                extracted = data[::step]
                output_file = f'step{step}.bin'
                
                with open(output_file, 'wb') as step_f:
                    step_f.write(extracted)
                
                f.write(f"Extracted every {step}th byte: {output_file} ({len(extracted)} bytes)\n")
                
                # Check for signatures in extracted data
                if b'PK' in extracted:
                    f.write(f"  ZIP signature found in {output_file}\n")
                if b'\x89PNG' in extracted:
                    f.write(f"  PNG signature found in {output_file}\n")
                if b'FGTE{' in extracted:
                    flag_pos = extracted.find(b'FGTE{')
                    flag_context = extracted[flag_pos:flag_pos + 100]
                    f.write(f"  FLAG FOUND in {output_file}: {flag_context}\n")
        
        f.write("\nAnalysis complete!\n")

# Run the analysis
analyze_video()

# Create a simple marker file to show the script ran
with open('script_executed.txt', 'w') as f:
    f.write("Script executed successfully")