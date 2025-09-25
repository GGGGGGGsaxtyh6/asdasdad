#!/usr/bin/env python3

import os
import binascii

def hex_flag_search():
    """
    Search for flag patterns in various encodings
    """
    
    filename = "secret.mp4"
    
    if not os.path.exists(filename):
        with open('hex_flag_search_result.txt', 'w') as f:
            f.write("ERROR: secret.mp4 not found\n")
        return
    
    with open(filename, 'rb') as f:
        data = f.read()
    
    with open('hex_flag_search_result.txt', 'w') as f:
        f.write("=== HEX FLAG SEARCH ===\n")
        f.write(f"Video file size: {len(data)} bytes\n\n")
        
        # Method 1: Search for hex-encoded flag
        # FGTE{ in hex would be: 46474545{7B
        hex_patterns = {
            'FGTE{': '4647544557B',
            'fgte{': '666774657B',
            'Flag{': '466C61677B',
            'flag{': '666C61677B'
        }
        
        f.write("=== Method 1: Direct hex pattern search ===\n")
        for pattern_name, hex_pattern in hex_patterns.items():
            try:
                hex_bytes = binascii.unhexlify(hex_pattern)
                pos = data.find(hex_bytes)
                if pos != -1:
                    f.write(f"Found {pattern_name} hex pattern at position {pos}\n")
                    context = data[pos:pos + 100]
                    f.write(f"Context: {context}\n")
            except:
                pass
        
        # Method 2: Search for ASCII values of FGTE{
        # F=70, G=71, T=84, E=69, {=123
        f.write("\n=== Method 2: ASCII value search ===\n")
        ascii_pattern = bytes([70, 71, 84, 69, 123])  # FGTE{
        pos = data.find(ascii_pattern)
        if pos != -1:
            f.write(f"Found ASCII FGTE{{ pattern at position {pos}\n")
            context = data[pos:pos + 100]
            f.write(f"Context: {context}\n")
        
        # Method 3: Search in all extracted files
        f.write("\n=== Method 3: Search in extracted files ===\n")
        
        extracted_files = [
            'step2.bin', 'step4.bin', 'step8.bin', 'step16.bin',
            'lsb_extracted.bin', 'rgb_extracted.bin',
            'block_8_extracted.bin', 'block_16_extracted.bin', 'block_32_extracted.bin'
        ]
        
        for ext_file in extracted_files:
            if os.path.exists(ext_file):
                try:
                    with open(ext_file, 'rb') as ef:
                        ext_data = ef.read()
                    
                    f.write(f"\n--- Checking {ext_file} ({len(ext_data)} bytes) ---\n")
                    
                    # Search for flag patterns
                    flag_patterns = [b'FGTE{', b'fgte{', b'Flag{', b'flag{']
                    
                    for pattern in flag_patterns:
                        pos = ext_data.find(pattern)
                        if pos != -1:
                            context = ext_data[pos:pos + 100]
                            f.write(f"FLAG FOUND: {context}\n")
                    
                    # Search for hex patterns
                    for pattern_name, hex_pattern in hex_patterns.items():
                        try:
                            hex_bytes = binascii.unhexlify(hex_pattern)
                            pos = ext_data.find(hex_bytes)
                            if pos != -1:
                                f.write(f"Hex pattern {pattern_name} found at position {pos}\n")
                                context = ext_data[pos:pos + 100]
                                f.write(f"Context: {context}\n")
                        except:
                            pass
                    
                    # Search for base64 encoded flags
                    # FGTE{ in base64 would be RkdURXs=
                    import base64
                    flag_b64_patterns = []
                    for flag in ['FGTE{', 'fgte{', 'Flag{', 'flag{']:
                        try:
                            b64_encoded = base64.b64encode(flag.encode())
                            flag_b64_patterns.append((flag, b64_encoded))
                        except:
                            pass
                    
                    for original, b64_pattern in flag_b64_patterns:
                        pos = ext_data.find(b64_pattern)
                        if pos != -1:
                            f.write(f"Base64 pattern for {original} found at position {pos}\n")
                            context = ext_data[pos:pos + 100]
                            f.write(f"Context: {context}\n")
                            
                            # Try to decode surrounding base64 data
                            start = max(0, pos - 50)
                            end = min(len(ext_data), pos + 200)
                            region = ext_data[start:end]
                            
                            try:
                                region_str = region.decode('ascii', errors='ignore')
                                # Look for base64-like strings
                                import re
                                b64_matches = re.findall(r'[A-Za-z0-9+/]{10,}={0,2}', region_str)
                                for b64_match in b64_matches:
                                    try:
                                        decoded = base64.b64decode(b64_match)
                                        if b'FGTE{' in decoded or b'flag' in decoded.lower():
                                            f.write(f"DECODED FLAG: {decoded}\n")
                                    except:
                                        pass
                            except:
                                pass
                
                except Exception as e:
                    f.write(f"Error reading {ext_file}: {e}\n")
        
        # Method 4: ROT13 and Caesar cipher search
        f.write("\n=== Method 4: ROT13/Caesar cipher search ===\n")
        
        # Try ROT13 on common strings that might decode to FGTE{
        rot13_candidates = []
        for i in range(26):
            # Try to find what would ROT to FGTE{
            rotted = ""
            for char in "FGTE{":
                if char.isalpha():
                    if char.isupper():
                        rotted += chr((ord(char) - ord('A') + i) % 26 + ord('A'))
                    else:
                        rotted += chr((ord(char) - ord('a') + i) % 26 + ord('a'))
                else:
                    rotted += char
            rot13_candidates.append((i, rotted))
        
        for shift, rotted in rot13_candidates:
            rotted_bytes = rotted.encode()
            pos = data.find(rotted_bytes)
            if pos != -1:
                f.write(f"Found ROT{shift} candidate '{rotted}' at position {pos}\n")
                context = data[pos:pos + 100]
                f.write(f"Context: {context}\n")
        
        # Method 5: XOR search
        f.write("\n=== Method 5: XOR search ===\n")
        
        # Try XORing data with common keys
        xor_keys = [0x01, 0x42, 0xFF, 0xAA, 0x55, 0x00]
        
        for key in xor_keys:
            f.write(f"Trying XOR key 0x{key:02X}...\n")
            xor_data = bytes([b ^ key for b in data[:10000]])  # Only first 10KB for performance
            
            for pattern in [b'FGTE{', b'fgte{', b'Flag{', b'flag{']:
                pos = xor_data.find(pattern)
                if pos != -1:
                    f.write(f"XOR key 0x{key:02X} reveals flag at position {pos}\n")
                    context = xor_data[pos:pos + 100]
                    f.write(f"FLAG: {context}\n")
        
        f.write("\n=== HEX FLAG SEARCH COMPLETE ===\n")

# Run hex flag search
hex_flag_search()

# Create marker
with open('hex_flag_search_executed.txt', 'w') as f:
    f.write("Hex flag search executed successfully")