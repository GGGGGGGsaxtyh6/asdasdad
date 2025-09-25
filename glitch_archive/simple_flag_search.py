#!/usr/bin/env python3

import os

# Simple flag search in all binary files
result_text = "=== SIMPLE FLAG SEARCH ===\n"

extracted_files = [
    'lsb_extracted.bin', 'rgb_extracted.bin',
    'block_8_extracted.bin', 'block_16_extracted.bin', 'block_32_extracted.bin', 'block_64_extracted.bin',
    'step2.bin', 'step4.bin', 'step8.bin', 'step16.bin',
    'extracted_step_2.bin', 'extracted_step_4.bin', 'extracted_step_8.bin', 'extracted_step_16.bin', 'extracted_step_32.bin'
]

for filename in extracted_files:
    if os.path.exists(filename):
        result_text += f"\n=== {filename} ===\n"
        result_text += f"Size: {os.path.getsize(filename)} bytes\n"
        
        try:
            with open(filename, 'rb') as f:
                data = f.read()
            
            # Look for flag patterns
            flag_patterns = [b'FGTE{', b'fgte{', b'Flag{', b'flag{']
            
            for pattern in flag_patterns:
                pos = data.find(pattern)
                if pos != -1:
                    context = data[pos:pos + 100]
                    result_text += f"FLAG FOUND: {context}\n"
            
            # Look for ZIP
            zip_pos = data.find(b'PK\x03\x04')
            if zip_pos != -1:
                result_text += f"ZIP signature at position {zip_pos}\n"
                
                # Try to extract small ZIP
                for size in [1000, 5000, 10000]:
                    if zip_pos + size <= len(data):
                        zip_data = data[zip_pos:zip_pos + size]
                        zip_filename = f'{filename}_{size}.zip'
                        
                        try:
                            with open(zip_filename, 'wb') as zf:
                                zf.write(zip_data)
                            
                            # Test ZIP
                            import zipfile
                            try:
                                with zipfile.ZipFile(zip_filename, 'r') as zf:
                                    result_text += f"Valid ZIP created: {zip_filename}\n"
                                    for name in zf.namelist():
                                        result_text += f"  - {name}\n"
                                    
                                    # Extract and read files
                                    extract_dir = f'{filename}_{size}_extracted'
                                    os.makedirs(extract_dir, exist_ok=True)
                                    zf.extractall(extract_dir)
                                    
                                    for name in zf.namelist():
                                        file_path = os.path.join(extract_dir, name)
                                        if os.path.isfile(file_path):
                                            try:
                                                with open(file_path, 'r') as txt_f:
                                                    content = txt_f.read()
                                                    result_text += f"Content of {name}: {content}\n"
                                                    if 'FGTE{' in content:
                                                        result_text += f"*** FLAG FOUND: {content} ***\n"
                                            except:
                                                try:
                                                    with open(file_path, 'rb') as bin_f:
                                                        content = bin_f.read()
                                                        if b'FGTE{' in content:
                                                            flag_pos = content.find(b'FGTE{')
                                                            flag_context = content[flag_pos:flag_pos + 100]
                                                            result_text += f"*** FLAG FOUND: {flag_context} ***\n"
                                                except:
                                                    pass
                                    
                                    break  # Stop if successful
                            except:
                                os.remove(zip_filename)
                        except:
                            pass
            
        except Exception as e:
            result_text += f"Error: {e}\n"

# Write results to file
with open('simple_flag_search_result.txt', 'w') as f:
    f.write(result_text)

# Create marker
with open('simple_flag_search_executed.txt', 'w') as f:
    f.write("Simple flag search executed")