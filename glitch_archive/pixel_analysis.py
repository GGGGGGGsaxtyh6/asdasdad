#!/usr/bin/env python3

import os
import struct

def analyze_video_frames():
    filename = "secret.mp4"
    
    if not os.path.exists(filename):
        with open('pixel_analysis_result.txt', 'w') as f:
            f.write("ERROR: secret.mp4 not found\n")
        return
    
    with open(filename, 'rb') as f:
        data = f.read()
    
    with open('pixel_analysis_result.txt', 'w') as f:
        f.write(f"=== Pixel-based Analysis for ISG (Infinite Storage Glitch) ===\n")
        f.write(f"Video file size: {len(data)} bytes\n\n")
        
        # ISG typically encodes data by using pixel values to represent binary data
        # Each pixel (RGB values) can represent 3 bytes of data
        # Let's try different extraction patterns
        
        f.write("=== Method 1: Direct pixel value extraction ===\n")
        
        # Skip MP4 header and look for frame data
        # MP4 files typically have frame data after the header
        header_size = 1000  # Skip first 1KB of header
        frame_data = data[header_size:]
        
        f.write(f"Frame data size (after header): {len(frame_data)} bytes\n")
        
        # Method 1: Extract every 3rd byte (as if RGB pixels)
        rgb_data = b''
        for i in range(0, len(frame_data), 3):
            if i + 2 < len(frame_data):
                # Take every 3rd byte as potential data
                rgb_data += bytes([frame_data[i + 2]])  # Take B component
        
        f.write(f"Extracted {len(rgb_data)} bytes using RGB method\n")
        
        # Save extracted data
        with open('rgb_extracted.bin', 'wb') as rgb_f:
            rgb_f.write(rgb_data)
        
        # Check for file signatures in extracted data
        signatures = {
            b'PK\x03\x04': 'ZIP file',
            b'\x89PNG': 'PNG file',
            b'%PDF': 'PDF file',
            b'FGTE{': 'FLAG'
        }
        
        for sig, desc in signatures.items():
            pos = rgb_data.find(sig)
            if pos != -1:
                f.write(f"  Found {desc} at position {pos}\n")
                
                if sig == b'PK\x03\x04':
                    # Extract ZIP
                    end_pos = rgb_data.find(b'PK\x05\x06', pos)
                    if end_pos != -1:
                        zip_data = rgb_data[pos:end_pos + 22]
                        
                        with open('rgb_extracted.zip', 'wb') as zip_f:
                            zip_f.write(zip_data)
                        
                        f.write(f"    Extracted ZIP: rgb_extracted.zip ({len(zip_data)} bytes)\n")
                        
                        # Try to read ZIP
                        import zipfile
                        try:
                            with zipfile.ZipFile('rgb_extracted.zip', 'r') as zf:
                                f.write(f"    ZIP contents:\n")
                                for name in zf.namelist():
                                    f.write(f"      - {name}\n")
                                
                                # Extract files
                                os.makedirs('rgb_extracted_files', exist_ok=True)
                                zf.extractall('rgb_extracted_files/')
                                
                                # Read extracted files
                                for name in zf.namelist():
                                    file_path = os.path.join('rgb_extracted_files', name)
                                    if os.path.isfile(file_path):
                                        try:
                                            with open(file_path, 'r', encoding='utf-8', errors='ignore') as txt_f:
                                                content = txt_f.read()
                                                f.write(f"    Content of {name}: {content}\n")
                                                
                                                if 'FGTE{' in content:
                                                    f.write(f"    🎯🎯🎯 FLAG FOUND: {content} 🎯🎯🎯\n")
                                        except:
                                            try:
                                                with open(file_path, 'rb') as bin_f:
                                                    content = bin_f.read()
                                                    if b'FGTE{' in content:
                                                        flag_pos = content.find(b'FGTE{')
                                                        flag_context = content[flag_pos:flag_pos + 100]
                                                        f.write(f"    🎯🎯🎯 FLAG FOUND: {flag_context} 🎯🎯🎯\n")
                                            except:
                                                pass
                        except Exception as e:
                            f.write(f"    Error reading ZIP: {e}\n")
                
                elif sig == b'FGTE{':
                    flag_context = rgb_data[pos:pos + 100]
                    f.write(f"    🎯🎯🎯 FLAG FOUND: {flag_context} 🎯🎯🎯\n")
        
        f.write(f"\n=== Method 2: Block-based extraction ===\n")
        
        # Method 2: Extract data in blocks (common for ISG)
        block_sizes = [8, 16, 32, 64]
        
        for block_size in block_sizes:
            f.write(f"\n--- Block size: {block_size} ---\n")
            
            block_data = b''
            for i in range(0, len(frame_data), block_size):
                if i + block_size <= len(frame_data):
                    block = frame_data[i:i + block_size]
                    # Use the last byte of each block
                    block_data += bytes([block[-1]])
            
            f.write(f"Extracted {len(block_data)} bytes using block method\n")
            
            # Save block data
            with open(f'block_{block_size}_extracted.bin', 'wb') as block_f:
                block_f.write(block_data)
            
            # Check for signatures
            for sig, desc in signatures.items():
                pos = block_data.find(sig)
                if pos != -1:
                    f.write(f"  Found {desc} at position {pos}\n")
                    
                    if sig == b'FGTE{':
                        flag_context = block_data[pos:pos + 100]
                        f.write(f"    🎯🎯🎯 FLAG FOUND: {flag_context} 🎯🎯🎯\n")
        
        f.write(f"\n=== Method 3: Bit-level extraction ===\n")
        
        # Method 3: Extract LSB (Least Significant Bit) from each byte
        lsb_data = b''
        for i in range(0, len(frame_data), 8):  # Every 8 bytes forms 1 output byte
            if i + 7 < len(frame_data):
                byte_val = 0
                for j in range(8):
                    bit = frame_data[i + j] & 1  # Get LSB
                    byte_val |= (bit << (7 - j))  # Build byte from LSBs
                lsb_data += bytes([byte_val])
        
        f.write(f"Extracted {len(lsb_data)} bytes using LSB method\n")
        
        with open('lsb_extracted.bin', 'wb') as lsb_f:
            lsb_f.write(lsb_data)
        
        # Check LSB data for signatures
        for sig, desc in signatures.items():
            pos = lsb_data.find(sig)
            if pos != -1:
                f.write(f"  Found {desc} at position {pos} in LSB data\n")
                
                if sig == b'PK\x03\x04':
                    # Extract ZIP
                    end_pos = lsb_data.find(b'PK\x05\x06', pos)
                    if end_pos != -1:
                        zip_data = lsb_data[pos:end_pos + 22]
                        
                        with open('lsb_extracted.zip', 'wb') as zip_f:
                            zip_f.write(zip_data)
                        
                        f.write(f"    Extracted ZIP: lsb_extracted.zip ({len(zip_data)} bytes)\n")
                        
                        # Try to read ZIP
                        import zipfile
                        try:
                            with zipfile.ZipFile('lsb_extracted.zip', 'r') as zf:
                                f.write(f"    ZIP contents:\n")
                                for name in zf.namelist():
                                    f.write(f"      - {name}\n")
                                
                                # Extract files
                                os.makedirs('lsb_extracted_files', exist_ok=True)
                                zf.extractall('lsb_extracted_files/')
                                
                                # Read extracted files
                                for name in zf.namelist():
                                    file_path = os.path.join('lsb_extracted_files', name)
                                    if os.path.isfile(file_path):
                                        try:
                                            with open(file_path, 'r', encoding='utf-8', errors='ignore') as txt_f:
                                                content = txt_f.read()
                                                f.write(f"    Content of {name}: {content}\n")
                                                
                                                if 'FGTE{' in content:
                                                    f.write(f"    🎯🎯🎯 FLAG FOUND: {content} 🎯🎯🎯\n")
                                        except:
                                            try:
                                                with open(file_path, 'rb') as bin_f:
                                                    content = bin_f.read()
                                                    if b'FGTE{' in content:
                                                        flag_pos = content.find(b'FGTE{')
                                                        flag_context = content[flag_pos:flag_pos + 100]
                                                        f.write(f"    🎯🎯🎯 FLAG FOUND: {flag_context} 🎯🎯🎯\n")
                                            except:
                                                pass
                        except Exception as e:
                            f.write(f"    Error reading ZIP: {e}\n")
                
                elif sig == b'FGTE{':
                    flag_context = lsb_data[pos:pos + 100]
                    f.write(f"    🎯🎯🎯 FLAG FOUND: {flag_context} 🎯🎯🎯\n")
        
        f.write(f"\n=== Pixel analysis complete ===\n")

# Run the analysis
analyze_video_frames()

# Create marker file
with open('pixel_analysis_executed.txt', 'w') as f:
    f.write("Pixel analysis executed successfully")