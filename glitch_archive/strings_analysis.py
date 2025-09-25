#!/usr/bin/env python3

import os
import re

def strings_analysis():
    """
    Extract all readable strings from the video file and analyze them
    """
    
    filename = "secret.mp4"
    
    if not os.path.exists(filename):
        with open('strings_analysis_result.txt', 'w') as f:
            f.write("ERROR: secret.mp4 not found\n")
        return
    
    with open(filename, 'rb') as f:
        data = f.read()
    
    with open('strings_analysis_result.txt', 'w') as f:
        f.write("=== STRINGS ANALYSIS ===\n")
        f.write(f"Video file size: {len(data)} bytes\n\n")
        
        # Extract all printable strings (ASCII 32-126)
        strings = []
        current_string = ""
        
        for byte in data:
            if 32 <= byte <= 126:  # Printable ASCII
                current_string += chr(byte)
            else:
                if len(current_string) >= 4:  # Only keep strings of 4+ chars
                    strings.append(current_string)
                current_string = ""
        
        # Add final string if valid
        if len(current_string) >= 4:
            strings.append(current_string)
        
        f.write(f"Found {len(strings)} strings of 4+ characters\n\n")
        
        # Look for interesting strings
        interesting_strings = []
        
        keywords = [
            'flag', 'Flag', 'FLAG', 'FGTE', 'fgte',
            'password', 'Password', 'PASSWORD',
            'key', 'Key', 'KEY',
            'secret', 'Secret', 'SECRET',
            'hidden', 'Hidden', 'HIDDEN',
            'glitch', 'Glitch', 'GLITCH',
            'archive', 'Archive', 'ARCHIVE',
            'base64', 'Base64', 'BASE64',
            'zip', 'Zip', 'ZIP',
            'decode', 'Decode', 'DECODE'
        ]
        
        f.write("=== INTERESTING STRINGS ===\n")
        for string in strings:
            # Check if string contains any keywords
            for keyword in keywords:
                if keyword in string:
                    interesting_strings.append(string)
                    f.write(f"KEYWORD '{keyword}': {string}\n")
                    break
            
            # Check for flag-like patterns
            if re.search(r'[Ff][Gg][Tt][Ee]\{.*?\}', string):
                f.write(f"*** POTENTIAL FLAG: {string} ***\n")
            
            # Check for base64-like patterns
            if re.match(r'^[A-Za-z0-9+/]{20,}={0,2}$', string):
                f.write(f"BASE64 CANDIDATE: {string}\n")
                
                # Try to decode
                import base64
                try:
                    decoded = base64.b64decode(string)
                    decoded_str = decoded.decode('utf-8', errors='ignore')
                    f.write(f"  DECODED: {decoded_str}\n")
                    
                    if 'FGTE{' in decoded_str:
                        f.write(f"  *** FLAG FOUND IN BASE64: {decoded_str} ***\n")
                except:
                    pass
        
        # Show all strings for manual review
        f.write(f"\n=== ALL STRINGS (first 200) ===\n")
        for i, string in enumerate(strings[:200]):
            f.write(f"{i+1:3d}: {string}\n")
        
        if len(strings) > 200:
            f.write(f"\n... and {len(strings) - 200} more strings\n")
        
        # Look for strings in specific ranges of the file
        f.write(f"\n=== STRINGS IN DIFFERENT FILE REGIONS ===\n")
        
        regions = [
            (0, 1000, "Header"),
            (len(data)//4, len(data)//4 + 1000, "First Quarter"),
            (len(data)//2, len(data)//2 + 1000, "Middle"),
            (3*len(data)//4, 3*len(data)//4 + 1000, "Third Quarter"),
            (len(data) - 1000, len(data), "Footer")
        ]
        
        for start, end, region_name in regions:
            if start < len(data) and end > start:
                end = min(end, len(data))
                region_data = data[start:end]
                
                # Extract strings from this region
                region_strings = []
                current_string = ""
                
                for byte in region_data:
                    if 32 <= byte <= 126:
                        current_string += chr(byte)
                    else:
                        if len(current_string) >= 4:
                            region_strings.append(current_string)
                        current_string = ""
                
                if len(current_string) >= 4:
                    region_strings.append(current_string)
                
                f.write(f"\n--- {region_name} ({start}-{end}) ---\n")
                for string in region_strings[:10]:  # Show first 10
                    f.write(f"  {string}\n")
                    
                    # Check for flags in this region
                    if re.search(r'[Ff][Gg][Tt][Ee]\{.*?\}', string):
                        f.write(f"  *** FLAG FOUND: {string} ***\n")
        
        # Try different character encodings
        f.write(f"\n=== TRYING DIFFERENT ENCODINGS ===\n")
        
        encodings = ['utf-8', 'latin1', 'ascii', 'utf-16', 'utf-32']
        
        for encoding in encodings:
            try:
                decoded_text = data.decode(encoding, errors='ignore')
                
                # Look for flag patterns
                flag_matches = re.findall(r'[Ff][Gg][Tt][Ee]\{[^}]+\}', decoded_text)
                if flag_matches:
                    f.write(f"{encoding.upper()} encoding found flags: {flag_matches}\n")
                
            except Exception as e:
                f.write(f"{encoding.upper()} encoding failed: {e}\n")
        
        f.write(f"\n=== STRINGS ANALYSIS COMPLETE ===\n")

# Run strings analysis
strings_analysis()

# Create marker
with open('strings_analysis_executed.txt', 'w') as f:
    f.write("Strings analysis executed successfully")