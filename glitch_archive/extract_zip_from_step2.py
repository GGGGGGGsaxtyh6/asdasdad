#!/usr/bin/env python3

import os
import zipfile

def extract_zip_from_step2():
    # Read step2.bin file
    step2_file = "step2.bin"
    
    if not os.path.exists(step2_file):
        with open('zip_extraction_result.txt', 'w') as f:
            f.write("ERROR: step2.bin not found\n")
        return
    
    with open(step2_file, 'rb') as f:
        data = f.read()
    
    # Write results to file
    with open('zip_extraction_result.txt', 'w') as f:
        f.write(f"=== ZIP Extraction from step2.bin ===\n")
        f.write(f"Step2.bin size: {len(data)} bytes\n\n")
        
        # Look for ZIP signature
        zip_pos = data.find(b'PK\x03\x04')
        if zip_pos != -1:
            f.write(f"ZIP signature found at position {zip_pos}\n")
            
            # Find end of ZIP file
            end_pos = data.find(b'PK\x05\x06', zip_pos)
            if end_pos != -1:
                # Extract ZIP data
                zip_data = data[zip_pos:end_pos + 22]
                
                # Save ZIP file
                with open('hidden_archive.zip', 'wb') as zip_f:
                    zip_f.write(zip_data)
                
                f.write(f"Extracted ZIP file: hidden_archive.zip ({len(zip_data)} bytes)\n\n")
                
                # Try to extract ZIP contents
                try:
                    with zipfile.ZipFile('hidden_archive.zip', 'r') as zf:
                        f.write("ZIP file contents:\n")
                        for name in zf.namelist():
                            f.write(f"  - {name}\n")
                        
                        # Extract all files
                        os.makedirs('final_extracted', exist_ok=True)
                        zf.extractall('final_extracted/')
                        f.write("\nFiles extracted to final_extracted/ directory\n\n")
                        
                        # Read each extracted file looking for flags
                        for name in zf.namelist():
                            file_path = os.path.join('final_extracted', name)
                            if os.path.isfile(file_path):
                                f.write(f"=== Content of {name} ===\n")
                                
                                # Try reading as text first
                                try:
                                    with open(file_path, 'r', encoding='utf-8', errors='ignore') as txt_f:
                                        content = txt_f.read()
                                        f.write(content)
                                        f.write("\n")
                                        
                                        # Check for flag
                                        if 'FGTE{' in content:
                                            f.write(f"🎯 FLAG FOUND: {content}\n")
                                except:
                                    # Try reading as binary
                                    try:
                                        with open(file_path, 'rb') as bin_f:
                                            content = bin_f.read()
                                            f.write(f"Binary content ({len(content)} bytes): {content[:200]}...\n")
                                            
                                            # Check for flag in binary
                                            if b'FGTE{' in content:
                                                flag_pos = content.find(b'FGTE{')
                                                flag_context = content[flag_pos:flag_pos + 100]
                                                f.write(f"🎯 FLAG FOUND: {flag_context}\n")
                                    except Exception as e:
                                        f.write(f"Error reading file: {e}\n")
                                
                                f.write("\n" + "="*50 + "\n")
                
                except Exception as e:
                    f.write(f"Error reading ZIP file: {e}\n")
            else:
                f.write("Could not find end of ZIP file\n")
        else:
            f.write("No ZIP signature found in step2.bin\n")

# Run the extraction
extract_zip_from_step2()

# Create marker file
with open('zip_extraction_executed.txt', 'w') as f:
    f.write("ZIP extraction script executed successfully")