#!/usr/bin/env python3

import os
import base64
import re

def extract_base64_data():
    # Analyze step4.bin for base64 data
    step_file = "step4.bin"
    
    if not os.path.exists(step_file):
        with open('base64_extraction_result.txt', 'w') as f:
            f.write("ERROR: step4.bin not found\n")
        return
    
    with open(step_file, 'rb') as f:
        data = f.read()
    
    with open('base64_extraction_result.txt', 'w') as f:
        f.write(f"=== Base64 Extraction from {step_file} ===\n")
        f.write(f"File size: {len(data)} bytes\n\n")
        
        # Look for the base64 signature around position 15184
        start_pos = max(0, 15184 - 100)
        end_pos = min(len(data), 15184 + 1000)
        
        region = data[start_pos:end_pos]
        f.write(f"Data around position 15184 ({start_pos}-{end_pos}):\n")
        f.write(f"Raw bytes: {region[:200]}...\n")
        f.write(f"As text: {region.decode('utf-8', errors='ignore')[:200]}...\n\n")
        
        # Try to find base64 patterns in the entire file
        # Convert to string for regex search
        try:
            text_data = data.decode('utf-8', errors='ignore')
            
            # Look for base64 patterns (alphanumeric + / + = padding)
            base64_patterns = re.findall(r'[A-Za-z0-9+/]{20,}={0,2}', text_data)
            
            f.write(f"Found {len(base64_patterns)} potential base64 strings:\n\n")
            
            for i, pattern in enumerate(base64_patterns):
                f.write(f"Pattern {i+1}: {pattern[:50]}{'...' if len(pattern) > 50 else ''}\n")
                f.write(f"Length: {len(pattern)} characters\n")
                
                # Try to decode
                try:
                    # Make sure pattern has correct padding
                    padded_pattern = pattern
                    while len(padded_pattern) % 4 != 0:
                        padded_pattern += '='
                    
                    decoded = base64.b64decode(padded_pattern)
                    f.write(f"Decoded ({len(decoded)} bytes): {decoded[:100]}{'...' if len(decoded) > 100 else ''}\n")
                    
                    # Check if decoded data has file signatures
                    if decoded.startswith(b'PK'):
                        f.write("  -> Decoded data appears to be a ZIP file!\n")
                        
                        # Save as ZIP
                        zip_filename = f"decoded_{i+1}.zip"
                        with open(zip_filename, 'wb') as zf:
                            zf.write(decoded)
                        f.write(f"  -> Saved as {zip_filename}\n")
                        
                        # Try to extract ZIP
                        import zipfile
                        try:
                            with zipfile.ZipFile(zip_filename, 'r') as zf:
                                f.write(f"  -> ZIP contents:\n")
                                for name in zf.namelist():
                                    f.write(f"       - {name}\n")
                                
                                # Extract files
                                extract_dir = f"decoded_{i+1}_extracted"
                                os.makedirs(extract_dir, exist_ok=True)
                                zf.extractall(extract_dir)
                                f.write(f"  -> Files extracted to {extract_dir}/\n")
                                
                                # Read extracted files
                                for name in zf.namelist():
                                    file_path = os.path.join(extract_dir, name)
                                    if os.path.isfile(file_path):
                                        f.write(f"  -> Content of {name}:\n")
                                        
                                        try:
                                            with open(file_path, 'r', encoding='utf-8', errors='ignore') as txt_f:
                                                content = txt_f.read()
                                                f.write(f"       {content}\n")
                                                
                                                # Check for flag
                                                if 'FGTE{' in content:
                                                    f.write(f"  🎯🎯🎯 FLAG FOUND: {content} 🎯🎯🎯\n")
                                        except:
                                            try:
                                                with open(file_path, 'rb') as bin_f:
                                                    content = bin_f.read()
                                                    f.write(f"       Binary content ({len(content)} bytes)\n")
                                                    
                                                    # Check for flag
                                                    if b'FGTE{' in content:
                                                        flag_pos = content.find(b'FGTE{')
                                                        flag_context = content[flag_pos:flag_pos + 100]
                                                        f.write(f"  🎯🎯🎯 FLAG FOUND: {flag_context} 🎯🎯🎯\n")
                                            except:
                                                f.write(f"       Could not read {name}\n")
                        
                        except Exception as e:
                            f.write(f"  -> Error reading ZIP: {e}\n")
                    
                    elif decoded.startswith(b'\x89PNG'):
                        f.write("  -> Decoded data appears to be a PNG file!\n")
                        png_filename = f"decoded_{i+1}.png"
                        with open(png_filename, 'wb') as pf:
                            pf.write(decoded)
                        f.write(f"  -> Saved as {png_filename}\n")
                    
                    elif b'FGTE{' in decoded:
                        f.write("  -> FLAG FOUND in decoded data!\n")
                        flag_pos = decoded.find(b'FGTE{')
                        flag_context = decoded[flag_pos:flag_pos + 100]
                        f.write(f"  🎯🎯🎯 FLAG: {flag_context} 🎯🎯🎯\n")
                    
                    else:
                        # Try as text
                        try:
                            text_decoded = decoded.decode('utf-8', errors='ignore')
                            f.write(f"  -> As text: {text_decoded[:200]}{'...' if len(text_decoded) > 200 else ''}\n")
                            
                            if 'FGTE{' in text_decoded:
                                f.write(f"  🎯🎯🎯 FLAG FOUND: {text_decoded} 🎯🎯🎯\n")
                        except:
                            f.write(f"  -> Could not decode as text\n")
                
                except Exception as e:
                    f.write(f"Failed to decode: {e}\n")
                
                f.write("\n" + "-"*50 + "\n")
        
        except Exception as e:
            f.write(f"Error processing file as text: {e}\n")
        
        # Also try extracting from raw binary patterns
        f.write("\n=== Looking for binary base64 patterns ===\n")
        
        # Look for sequences that might be base64
        for chunk_size in [64, 128, 256, 512, 1024]:
            for start in range(0, len(data) - chunk_size, chunk_size):
                chunk = data[start:start + chunk_size]
                
                # Check if chunk looks like base64 (mostly alphanumeric + some special chars)
                try:
                    chunk_str = chunk.decode('utf-8', errors='ignore')
                    if re.match(r'^[A-Za-z0-9+/=\n\r\s]+$', chunk_str) and len(chunk_str.strip()) > 20:
                        clean_chunk = ''.join(chunk_str.split())  # Remove whitespace
                        
                        # Try to decode
                        try:
                            # Ensure proper padding
                            while len(clean_chunk) % 4 != 0:
                                clean_chunk += '='
                            
                            decoded = base64.b64decode(clean_chunk)
                            if len(decoded) > 10:  # Only consider if we get meaningful data
                                f.write(f"Potential base64 at position {start} (chunk size {chunk_size}):\n")
                                f.write(f"  Decoded {len(decoded)} bytes: {decoded[:50]}...\n")
                                
                                # Check for flag
                                if b'FGTE{' in decoded:
                                    flag_pos = decoded.find(b'FGTE{')
                                    flag_context = decoded[flag_pos:flag_pos + 100]
                                    f.write(f"  🎯🎯🎯 FLAG FOUND: {flag_context} 🎯🎯🎯\n")
                        except:
                            pass
                except:
                    pass
        
        f.write("\n=== Base64 extraction complete ===\n")

# Run the extraction
extract_base64_data()

# Create marker file
with open('base64_extraction_executed.txt', 'w') as f:
    f.write("Base64 extraction executed successfully")