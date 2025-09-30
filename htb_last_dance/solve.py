#!/usr/bin/env python3

"""
Vulnerability: Nonce Reuse in ChaCha20

ChaCha20 is a stream cipher. When you encrypt with a stream cipher:
    ciphertext = plaintext XOR keystream

If the same key and nonce are used twice:
    c1 = m1 XOR keystream
    c2 = m2 XOR keystream
    
Therefore:
    c1 XOR c2 = (m1 XOR keystream) XOR (m2 XOR keystream)
              = m1 XOR m2
    
If we know m1, we can recover m2:
    m2 = m1 XOR c1 XOR c2
"""

# Read the data
with open('crypto_the_last_dance/out.txt', 'r') as f:
    lines = f.read().strip().split('\n')

nonce = bytes.fromhex(lines[0])
encrypted_message = bytes.fromhex(lines[1])
encrypted_flag = bytes.fromhex(lines[2])

print(f"[*] Nonce: {nonce.hex()}")
print(f"[*] Encrypted message length: {len(encrypted_message)}")
print(f"[*] Encrypted flag length: {len(encrypted_flag)}")

# Known plaintext
message = b"Our counter agencies have intercepted your messages and a lot "
message += b"of your agent's identities have been exposed. In a matter of "
message += b"days all of them will be captured"

print(f"[*] Known plaintext length: {len(message)}")
print(f"[*] Known plaintext: {message}")

# XOR operation
def xor_bytes(a, b):
    return bytes([x ^ y for x, y in zip(a, b)])

# Since both messages were encrypted with the same keystream:
# encrypted_message = message XOR keystream
# encrypted_flag = flag XOR keystream
#
# Therefore:
# encrypted_message XOR message = keystream (for the length of message)
# flag = encrypted_flag XOR keystream

# Extract the keystream from the first encryption
keystream = xor_bytes(encrypted_message, message)
print(f"\n[*] Extracted keystream (first {len(keystream)} bytes): {keystream.hex()}")

# Decrypt the flag using the keystream
flag = xor_bytes(encrypted_flag, keystream[:len(encrypted_flag)])

print(f"\n[+] Decrypted flag: {flag}")
print(f"[+] Flag as string: {flag.decode('utf-8', errors='ignore')}")

# Save flag
with open('flag.txt', 'w') as f:
    f.write(flag.decode('utf-8', errors='ignore'))

print("\n[+] ✅ Flag saved to flag.txt")
