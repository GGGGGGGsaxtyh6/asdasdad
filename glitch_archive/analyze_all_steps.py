#!/usr/bin/env python3

import os
import zipfile

def analyze_all_step_files():
    # List of step files to analyze
    step_files = [
        'step2.bin', 'step4.bin', 'step8.bin', 'step16.bin',
        'extracted_step_2.bin', 'extracted_step_4.bin', 'extracted_step_8.bin', 'extracted_step_16.bin', 'extracted_step_32.bin'
    ]
    
    with open('complete_analysis_result.txt', 'w') as f:
        f.write("=== Complete Analysis of All Step Files ===\n\n")
        
        for step_file in step_files:
            if os.path.exists(step_file):
                f.write(f"=== Analyzing {step_file} ===\n")
                
                # Read file
                try:
                    with open(step_file, 'rb') as sf:
                        data = sf.read()
                    
                    f.write(f"File size: {len(data)} bytes\n")
                    
                    # Look for various signatures
                    signatures = {
                        'ZIP': [b'PK\x03\x04', b'PK\x05\x06'],
                        'PNG': [b'\x89PNG'],
                        'JPEG': [b'\xFF\xD8\xFF'],
                        'PDF': [b'%PDF'],
                        'FLAG': [b'FGTE{'],
                        'TEXT': [b'flag', b'Flag', b'FLAG'],
                        'BASE64': [b'==']
                    }
                    
                    found_signatures = []
                    
                    for sig_name, sig_list in signatures.items():
                        for sig_bytes in sig_list:
                            pos = data.find(sig_bytes)
                            if pos != -1:
                                found_signatures.append((sig_name, sig_bytes, pos))
                                f.write(f"  {sig_name} signature found at position {pos}\n")
                    
                    # If ZIP found, try to extract
                    zip_pos = data.find(b'PK\x03\x04')
                    if zip_pos != -1:
                        f.write(f"  Attempting ZIP extraction from position {zip_pos}...\n")
                        
                        # Look for end of ZIP
                        end_positions = []
                        end_pos = data.find(b'PK\x05\x06', zip_pos)
                        if end_pos != -1:
                            end_positions.append(end_pos + 22)
                        
                        # Also try extracting until end of file
                        end_positions.append(len(data))
                        
                        for i, end_pos in enumerate(end_positions):
                            zip_data = data[zip_pos:end_pos]
                            zip_filename = f'{step_file}_extracted_{i}.zip'
                            
                            try:
                                with open(zip_filename, 'wb') as zf:
                                    zf.write(zip_data)
                                
                                f.write(f"  Created {zip_filename} ({len(zip_data)} bytes)\n")
                                
                                # Try to read as ZIP
                                try:
                                    with zipfile.ZipFile(zip_filename, 'r') as zip_reader:
                                        f.write(f"  Successfully opened {zip_filename} as ZIP\n")
                                        f.write(f"  ZIP contents:\n")
                                        
                                        for name in zip_reader.namelist():
                                            f.write(f"    - {name}\n")
                                        
                                        # Extract files
                                        extract_dir = f'{step_file}_extracted_{i}'
                                        os.makedirs(extract_dir, exist_ok=True)
                                        zip_reader.extractall(extract_dir)
                                        f.write(f"  Files extracted to {extract_dir}/\n")
                                        
                                        # Read extracted files
                                        for name in zip_reader.namelist():
                                            file_path = os.path.join(extract_dir, name)
                                            if os.path.isfile(file_path):
                                                f.write(f"  === Content of {name} ===\n")
                                                
                                                # Try reading as text
                                                try:
                                                    with open(file_path, 'r', encoding='utf-8', errors='ignore') as txt_f:
                                                        content = txt_f.read()
                                                        f.write(f"  {content}\n")
                                                        
                                                        # Check for flag
                                                        if 'FGTE{' in content:
                                                            f.write(f"  🎯🎯🎯 FLAG FOUND: {content} 🎯🎯🎯\n")
                                                except:
                                                    # Try binary
                                                    try:
                                                        with open(file_path, 'rb') as bin_f:
                                                            content = bin_f.read()
                                                            f.write(f"  Binary content ({len(content)} bytes)\n")
                                                            
                                                            # Check for flag in binary
                                                            if b'FGTE{' in content:
                                                                flag_pos = content.find(b'FGTE{')
                                                                flag_context = content[flag_pos:flag_pos + 100]
                                                                f.write(f"  🎯🎯🎯 FLAG FOUND: {flag_context} 🎯🎯🎯\n")
                                                    except:
                                                        f.write(f"  Could not read {name}\n")
                                        
                                        break  # If successful, don't try other end positions
                                        
                                except zipfile.BadZipFile:
                                    f.write(f"  {zip_filename} is not a valid ZIP file\n")
                                except Exception as e:
                                    f.write(f"  Error reading {zip_filename}: {e}\n")
                            
                            except Exception as e:
                                f.write(f"  Error creating {zip_filename}: {e}\n")
                    
                    # If FLAG signature found directly
                    flag_pos = data.find(b'FGTE{')
                    if flag_pos != -1:
                        flag_context = data[flag_pos:flag_pos + 100]
                        f.write(f"  🎯🎯🎯 DIRECT FLAG FOUND: {flag_context} 🎯🎯🎯\n")
                    
                    f.write(f"\n")
                
                except Exception as e:
                    f.write(f"Error reading {step_file}: {e}\n\n")
            else:
                f.write(f"{step_file} does not exist\n\n")
        
        f.write("=== Analysis Complete ===\n")

# Run the analysis
analyze_all_step_files()

# Create marker file
with open('complete_analysis_executed.txt', 'w') as f:
    f.write("Complete analysis executed successfully")