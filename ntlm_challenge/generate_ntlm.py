#!/usr/bin/env python3
"""
Generate NTLMv2 hash for demonstration purposes
"""

import hashlib
import hmac
import struct
import os

def generate_ntlmv2_hash(username, domain, password, server_challenge):
    """
    Generate NTLMv2 hash for demonstration
    """
    # Convert password to UTF-16LE
    password_utf16 = password.encode('utf-16le')
    
    # Generate NTLM hash (MD4 of password)
    ntlm_hash = hashlib.new('md4', password_utf16).digest()
    
    # Generate NTLMv2 hash
    # HMAC-MD5(ntlm_hash, username.upper() + domain.upper())
    user_domain = (username.upper() + domain.upper()).encode('utf-16le')
    ntlmv2_hash = hmac.new(ntlm_hash, user_domain, hashlib.md5).digest()
    
    # Generate client challenge (8 bytes)
    client_challenge = os.urandom(8)
    
    # Generate timestamp (8 bytes)
    import time
    timestamp = struct.pack('<Q', int(time.time()))
    
    # Generate blob
    blob = b'\x01\x01\x00\x00'  # Blob signature
    blob += b'\x00\x00\x00\x00'  # Reserved
    blob += timestamp  # Timestamp
    blob += client_challenge  # Client challenge
    blob += b'\x00\x00\x00\x00'  # Reserved
    
    # Generate NTLMv2 response
    # HMAC-MD5(ntlmv2_hash, server_challenge + blob)
    challenge_blob = server_challenge + blob
    ntlmv2_response = hmac.new(ntlmv2_hash, challenge_blob, hashlib.md5).digest()
    
    # Generate NTProofStr (first 16 bytes of NTLMv2 response)
    ntproofstr = ntlmv2_response[:16]
    
    # Full NTLMv2 response (NTProofStr + blob)
    full_response = ntproofstr + blob
    
    return ntproofstr.hex(), full_response.hex()

def main():
    # Demo parameters
    username = "admin"
    domain = "WORKGROUP"
    password = "password123"  # This is what we'll try to crack
    server_challenge = bytes.fromhex("1122334455667788")
    
    print(f"Generating NTLMv2 hash for:")
    print(f"Username: {username}")
    print(f"Domain: {domain}")
    print(f"Password: {password}")
    print(f"Server Challenge: {server_challenge.hex()}")
    print()
    
    ntproofstr, ntlmv2_response = generate_ntlmv2_hash(username, domain, password, server_challenge)
    
    print("Generated NTLMv2 hash:")
    print(f"{username}::{domain}:{server_challenge.hex()}:{ntproofstr}:{ntlmv2_response}")
    
    # Write to file
    with open("ntlm_challenge.txt", "w") as f:
        f.write(f"# NTLM Authentication Challenge\n")
        f.write(f"# Format: Usuario::Dominio:Desafío del servidor:HMAC-MD5 (NTProofStr):NTLMv2Response\n\n")
        f.write(f"{username}::{domain}:{server_challenge.hex()}:{ntproofstr}:{ntlmv2_response}\n")
    
    print(f"\nHash written to ntlm_challenge.txt")

if __name__ == "__main__":
    main()