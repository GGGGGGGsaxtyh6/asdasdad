#!/usr/bin/env python3

import os

def isg_decode():
    """
    ISG (Infinite Storage Glitch) decoder
    ISG typically encodes data in video frames using specific patterns
    """
    
    filename = "secret.mp4"
    
    if not os.path.exists(filename):
        with open('isg_decode_result.txt', 'w') as f:
            f.write("ERROR: secret.mp4 not found\n")
        return
    
    with open(filename, 'rb') as f:
        data = f.read()
    
    with open('isg_decode_result.txt', 'w') as f:
        f.write("=== ISG (Infinite Storage Glitch) Decoder ===\n")
        f.write(f"Video file size: {len(data)} bytes\n\n")
        
        # ISG method 1: Skip video headers and extract data patterns
        # MP4 files usually have video data after initial headers
        
        # Try different starting positions to skip headers
        start_positions = [0, 100, 500, 1000, 2000, 5000]
        
        for start_pos in start_positions:
            f.write(f"=== Starting from position {start_pos} ===\n")
            
            if start_pos >= len(data):
                continue
            
            video_data = data[start_pos:]
            f.write(f"Data size from position {start_pos}: {len(video_data)} bytes\n")
            
            # Method 1: Extract every nth byte (common ISG pattern)
            for step in [2, 3, 4, 6, 8, 12, 16]:
                extracted = b''
                for i in range(0, len(video_data), step):
                    if i < len(video_data):
                        extracted += bytes([video_data[i]])
                
                # Check if extracted data has file signatures
                if b'PK\x03\x04' in extracted:
                    f.write(f"  Step {step}: ZIP signature found!\n")
                    zip_pos = extracted.find(b'PK\x03\x04')
                    
                    # Try to extract ZIP
                    for zip_size in [1000, 2000, 5000, 10000, len(extracted) - zip_pos]:
                        if zip_pos + zip_size <= len(extracted):
                            zip_data = extracted[zip_pos:zip_pos + zip_size]
                            zip_filename = f'isg_pos_{start_pos}_step_{step}_size_{zip_size}.zip'
                            
                            with open(zip_filename, 'wb') as zf:
                                zf.write(zip_data)
                            
                            # Test ZIP validity
                            import zipfile
                            try:
                                with zipfile.ZipFile(zip_filename, 'r') as zf:
                                    f.write(f"    Valid ZIP: {zip_filename}\n")
                                    f.write(f"    Contents:\n")
                                    
                                    for name in zf.namelist():
                                        f.write(f"      - {name}\n")
                                    
                                    # Extract files
                                    extract_dir = f'isg_pos_{start_pos}_step_{step}_extracted'
                                    os.makedirs(extract_dir, exist_ok=True)
                                    zf.extractall(extract_dir)
                                    
                                    # Read extracted files
                                    for name in zf.namelist():
                                        file_path = os.path.join(extract_dir, name)
                                        if os.path.isfile(file_path):
                                            try:
                                                with open(file_path, 'r', encoding='utf-8', errors='ignore') as txt_f:
                                                    content = txt_f.read()
                                                    f.write(f"    {name}: {content}\n")
                                                    
                                                    if 'FGTE{' in content:
                                                        f.write(f"    *** FLAG FOUND: {content} ***\n")
                                            except:
                                                try:
                                                    with open(file_path, 'rb') as bin_f:
                                                        content = bin_f.read()
                                                        if b'FGTE{' in content:
                                                            flag_pos = content.find(b'FGTE{')
                                                            flag_context = content[flag_pos:flag_pos + 100]
                                                            f.write(f"    *** FLAG FOUND: {flag_context} ***\n")
                                                except:
                                                    pass
                                    
                                    break  # If successful, don't try other sizes
                            except:
                                os.remove(zip_filename)
                
                if b'FGTE{' in extracted:
                    f.write(f"  Step {step}: FLAG signature found!\n")
                    flag_pos = extracted.find(b'FGTE{')
                    flag_context = extracted[flag_pos:flag_pos + 100]
                    f.write(f"    *** FLAG: {flag_context} ***\n")
                
                if b'\x89PNG' in extracted:
                    f.write(f"  Step {step}: PNG signature found!\n")
                    png_pos = extracted.find(b'\x89PNG')
                    # Try to extract PNG
                    iend_pos = extracted.find(b'IEND\xaeB`\x82', png_pos)
                    if iend_pos != -1:
                        png_data = extracted[png_pos:iend_pos + 12]
                        png_filename = f'isg_pos_{start_pos}_step_{step}.png'
                        with open(png_filename, 'wb') as pf:
                            pf.write(png_data)
                        f.write(f"    Extracted PNG: {png_filename}\n")
            
            f.write(f"\n")
        
        # Method 2: Look for specific ISG patterns
        f.write("=== Method 2: ISG Pattern Recognition ===\n")
        
        # ISG sometimes uses specific byte patterns to encode data
        # Look for repeating patterns that might indicate encoded data
        
        for pattern_size in [16, 32, 64, 128]:
            f.write(f"Looking for patterns of size {pattern_size}...\n")
            
            patterns = {}
            for i in range(0, len(data) - pattern_size, pattern_size):
                pattern = data[i:i + pattern_size]
                if pattern in patterns:
                    patterns[pattern] += 1
                else:
                    patterns[pattern] = 1
            
            # Look for patterns that appear multiple times (might be encoded data blocks)
            repeated_patterns = [(pattern, count) for pattern, count in patterns.items() if count > 5]
            repeated_patterns.sort(key=lambda x: x[1], reverse=True)
            
            if repeated_patterns:
                f.write(f"  Found {len(repeated_patterns)} repeated patterns\n")
                
                # Try to decode using the most common pattern
                most_common_pattern, count = repeated_patterns[0]
                f.write(f"  Most common pattern appears {count} times\n")
                
                # Extract data based on this pattern
                pattern_data = b''
                for i in range(0, len(data) - pattern_size, pattern_size):
                    if data[i:i + pattern_size] != most_common_pattern:
                        # This block is different - might contain data
                        pattern_data += data[i:i + pattern_size]
                
                if len(pattern_data) > 100:
                    pattern_filename = f'pattern_decoded_{pattern_size}.bin'
                    with open(pattern_filename, 'wb') as pf:
                        pf.write(pattern_data)
                    
                    f.write(f"  Extracted pattern data: {pattern_filename} ({len(pattern_data)} bytes)\n")
                    
                    # Check for signatures in pattern data
                    if b'PK\x03\x04' in pattern_data:
                        f.write(f"    ZIP signature found in pattern data!\n")
                    if b'FGTE{' in pattern_data:
                        flag_pos = pattern_data.find(b'FGTE{')
                        flag_context = pattern_data[flag_pos:flag_pos + 100]
                        f.write(f"    *** FLAG FOUND: {flag_context} ***\n")
        
        f.write("\n=== ISG Decoding Complete ===\n")

# Run ISG decoder
isg_decode()

# Create marker
with open('isg_decode_executed.txt', 'w') as f:
    f.write("ISG decode executed successfully")