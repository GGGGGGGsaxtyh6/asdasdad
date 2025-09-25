#!/usr/bin/env python3

import os
import zipfile
import re

def comprehensive_analysis():
    # List all extracted files
    extracted_files = [
        'lsb_extracted.bin', 'rgb_extracted.bin',
        'block_8_extracted.bin', 'block_16_extracted.bin', 'block_32_extracted.bin', 'block_64_extracted.bin',
        'step2.bin', 'step4.bin', 'step8.bin', 'step16.bin',
        'extracted_step_2.bin', 'extracted_step_4.bin', 'extracted_step_8.bin', 'extracted_step_16.bin', 'extracted_step_32.bin'
    ]
    
    with open('final_comprehensive_result.txt', 'w') as f:
        f.write("=== FINAL COMPREHENSIVE ANALYSIS ===\n")
        f.write("Analyzing all extracted files for hidden data\n\n")
        
        for filename in extracted_files:
            if os.path.exists(filename):
                f.write(f"=== Analyzing {filename} ===\n")
                
                try:
                    with open(filename, 'rb') as file:
                        data = file.read()
                    
                    f.write(f"File size: {len(data)} bytes\n")
                    
                    # Check for all possible signatures
                    signatures = {
                        'ZIP': b'PK\x03\x04',
                        'PNG': b'\x89PNG',
                        'JPEG': b'\xFF\xD8\xFF',
                        'PDF': b'%PDF',
                        'GIF': b'GIF8',
                        'RAR': b'Rar!',
                        'ELF': b'\x7FELF',
                        'FLAG': b'FGTE{',
                        'FLAG_ALT1': b'fgte{',
                        'FLAG_ALT2': b'Flag{',
                        'FLAG_ALT3': b'flag{',
                        'BASE64_END': b'==',
                        'TEXT': b'password',
                        'TEXT2': b'secret',
                        'TEXT3': b'key'
                    }
                    
                    found_signatures = []
                    
                    for sig_name, sig_bytes in signatures.items():
                        positions = []
                        start = 0
                        while True:
                            pos = data.find(sig_bytes, start)
                            if pos == -1:
                                break
                            positions.append(pos)
                            start = pos + 1
                        
                        if positions:
                            found_signatures.append((sig_name, sig_bytes, positions))
                            f.write(f"  {sig_name}: Found at positions {positions[:10]}{'...' if len(positions) > 10 else ''}\n")
                    
                    # Special handling for potential flags
                    for sig_name, sig_bytes, positions in found_signatures:
                        if 'FLAG' in sig_name:
                            for pos in positions[:5]:  # Check first 5 occurrences
                                context = data[pos:pos + 100]
                                f.write(f"  🎯 POTENTIAL FLAG at {pos}: {context}\n")
                    
                    # If ZIP signature found, try extraction
                    zip_positions = [pos for sig_name, sig_bytes, positions in found_signatures if sig_name == 'ZIP' for pos in positions]
                    
                    for i, zip_pos in enumerate(zip_positions[:3]):  # Try first 3 ZIP signatures
                        f.write(f"  Attempting ZIP extraction from position {zip_pos}...\n")
                        
                        # Try different end positions
                        end_candidates = []
                        
                        # Look for ZIP end signature
                        end_pos = data.find(b'PK\x05\x06', zip_pos)
                        if end_pos != -1:
                            end_candidates.append(end_pos + 22)
                        
                        # Try different sizes
                        for size in [1000, 5000, 10000, len(data) - zip_pos]:
                            if zip_pos + size <= len(data):
                                end_candidates.append(zip_pos + size)
                        
                        for j, end_pos in enumerate(end_candidates[:3]):
                            zip_data = data[zip_pos:end_pos]
                            zip_filename = f'{filename}_zip_{i}_{j}.zip'
                            
                            try:
                                with open(zip_filename, 'wb') as zip_file:
                                    zip_file.write(zip_data)
                                
                                # Test if it's a valid ZIP
                                with zipfile.ZipFile(zip_filename, 'r') as zf:
                                    f.write(f"    ✓ Successfully created {zip_filename}\n")
                                    f.write(f"    ZIP contents:\n")
                                    
                                    for name in zf.namelist():
                                        f.write(f"      - {name}\n")
                                    
                                    # Extract all files
                                    extract_dir = f'{filename}_zip_{i}_{j}_extracted'
                                    os.makedirs(extract_dir, exist_ok=True)
                                    zf.extractall(extract_dir)
                                    
                                    f.write(f"    Files extracted to {extract_dir}/\n")
                                    
                                    # Read each extracted file
                                    for name in zf.namelist():
                                        file_path = os.path.join(extract_dir, name)
                                        if os.path.isfile(file_path):
                                            f.write(f"    === Content of {name} ===\n")
                                            
                                            # Try reading as text
                                            try:
                                                with open(file_path, 'r', encoding='utf-8', errors='ignore') as txt_file:
                                                    content = txt_file.read()
                                                    f.write(f"    {content}\n")
                                                    
                                                    # Check for flag patterns
                                                    flag_patterns = ['FGTE{', 'fgte{', 'Flag{', 'flag{'}
                                                    for pattern in flag_patterns:
                                                        if pattern in content:
                                                            f.write(f"    🎯🎯🎯 FLAG FOUND: {content} 🎯🎯🎯\n")
                                                            break
                                            except:
                                                # Try reading as binary
                                                try:
                                                    with open(file_path, 'rb') as bin_file:
                                                        content = bin_file.read()
                                                        f.write(f"    Binary file ({len(content)} bytes)\n")
                                                        
                                                        # Check for binary flag patterns
                                                        flag_patterns = [b'FGTE{', b'fgte{', b'Flag{', b'flag{']
                                                        for pattern in flag_patterns:
                                                            pos = content.find(pattern)
                                                            if pos != -1:
                                                                flag_context = content[pos:pos + 100]
                                                                f.write(f"    🎯🎯🎯 FLAG FOUND: {flag_context} 🎯🎯🎯\n")
                                                                break
                                                except:
                                                    f.write(f"    Could not read {name}\n")
                                    
                                    # If we found a valid ZIP, stop trying other end positions
                                    break
                                    
                            except zipfile.BadZipFile:
                                # Clean up invalid ZIP file
                                if os.path.exists(zip_filename):
                                    os.remove(zip_filename)
                            except Exception as e:
                                f.write(f"    Error with {zip_filename}: {e}\n")
                                if os.path.exists(zip_filename):
                                    os.remove(zip_filename)
                    
                    # Look for text patterns that might be flags
                    try:
                        text_data = data.decode('utf-8', errors='ignore')
                        
                        # Look for flag-like patterns
                        flag_pattern = re.compile(r'[Ff][Gg][Tt][Ee]\{[^}]+\}')
                        matches = flag_pattern.findall(text_data)
                        
                        if matches:
                            f.write(f"  🎯 Found flag patterns in text: {matches}\n")
                            for match in matches:
                                f.write(f"    🎯🎯🎯 FLAG CANDIDATE: {match} 🎯🎯🎯\n")
                    except:
                        pass
                    
                    f.write(f"\n")
                
                except Exception as e:
                    f.write(f"Error reading {filename}: {e}\n\n")
            else:
                f.write(f"{filename} does not exist\n\n")
        
        f.write("=== FINAL COMPREHENSIVE ANALYSIS COMPLETE ===\n")

# Run the comprehensive analysis
comprehensive_analysis()

# Create marker
with open('final_comprehensive_executed.txt', 'w') as f:
    f.write("Final comprehensive analysis executed successfully")