#!/usr/bin/env python3
import base64
import re

def decode_base64_layers(data):
    """Decode base64 layers until we find the flag or can't decode anymore"""
    current_data = data
    layer = 0
    
    while True:
        layer += 1
        print(f"Layer {layer}:")
        
        try:
            # Try to decode as base64
            decoded = base64.b64decode(current_data)
            
            # Check if it's still base64-like
            try:
                # Try to decode again to see if it's still base64
                test_decode = base64.b64decode(decoded)
                print(f"  Still base64, continuing...")
                current_data = decoded
            except:
                # Not base64 anymore, check if it contains the flag
                decoded_str = decoded.decode('utf-8', errors='ignore')
                print(f"  Decoded to text: {decoded_str[:100]}...")
                
                # Look for flag pattern
                flag_match = re.search(r'CTFlearn\{[^}]+\}', decoded_str)
                if flag_match:
                    print(f"  FOUND FLAG: {flag_match.group()}")
                    return flag_match.group()
                
                # If no flag found, this might be the final layer
                print(f"  No flag found in this layer")
                return decoded_str
                
        except Exception as e:
            print(f"  Cannot decode as base64: {e}")
            # Try to decode as text
            try:
                text = current_data.decode('utf-8', errors='ignore')
                print(f"  Final text: {text}")
                return text
            except:
                print(f"  Cannot decode as text either")
                return current_data

def main():
    # Read the encrypted file
    with open('encrypted_file_real', 'rb') as f:
        data = f.read()
    
    print(f"Original file size: {len(data)} bytes")
    print("Starting base64 decoding layers...")
    print()
    
    result = decode_base64_layers(data)
    print(f"\nFinal result: {result}")

if __name__ == "__main__":
    main()