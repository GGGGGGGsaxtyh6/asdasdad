#!/usr/bin/env python3
# decrypt.py - Decrypt the flip-flop cipher

def decode(ciphertext: str) -> str:
    """
    Decode the flip-flop cipher by reversing the bit order of each character
    """
    out = []
    for c in ciphertext:
        # Convert character to 8-bit binary
        bits = f"{ord(c):08b}"
        # Reverse the bits (undo the flip)
        rev = bits[::-1]
        # Convert back to character
        out.append(chr(int(rev, 2)))
    return "".join(out)

if __name__ == "__main__":
    # Read the ciphertext file
    with open("cipher.txt", "rb") as f:
        cipher_data = f.read()
    
    # Decode using latin-1 to preserve all byte values
    ciphertext = cipher_data.decode("latin-1")
    
    # Decrypt
    plaintext = decode(ciphertext)
    
    print("Decrypted message:")
    print(plaintext)