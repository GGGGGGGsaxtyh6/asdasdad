#!/usr/bin/env python3
import hashlib

def solve_b4sh33():
    # Initial version is "0.0.0.0"
    initial_version = "0.0.0.0"
    
    # Calculate MD5 hash
    hash_obj = hashlib.md5(initial_version.encode())
    hash_hex = hash_obj.hexdigest()
    print(f"Initial version: {initial_version}")
    print(f"MD5 hash: {hash_hex}")
    
    # Convert hash to bytes
    hash_bytes = []
    for i in range(0, 32, 2):
        hex_byte = hash_hex[i:i+2]
        hash_bytes.append(int(hex_byte, 16))
    
    print(f"Hash bytes: {hash_bytes}")
    
    # The input is padded with X's to make it 23 characters long
    # We need to find the correct characters at specific positions
    
    # All the validation conditions from the script
    conditions = [
        (0, 0x56, 3),   # position 0, XOR with 0x56, compare with hash_bytes[3]
        (5, 0x7, 7),    # position 5, XOR with 0x7, compare with hash_bytes[7]
        (12, 0x25, 11), # position 12, XOR with 0x25, compare with hash_bytes[11]
        (3, 0xe2, 15),  # position 3, XOR with 0xe2, compare with hash_bytes[15]
        (8, 0x4b, 2),   # position 8, XOR with 0x4b, compare with hash_bytes[2]
        (1, 0xda, 6),   # position 1, XOR with 0xda, compare with hash_bytes[6]
        (15, 0x42, 10), # position 15, XOR with 0x42, compare with hash_bytes[10]
        (7, 0xa6, 14),  # position 7, XOR with 0xa6, compare with hash_bytes[14]
        (4, 0xdc, 1),   # position 4, XOR with 0xdc, compare with hash_bytes[1]
        (11, 0x2e, 5),  # position 11, XOR with 0x2e, compare with hash_bytes[5]
        (18, 0xff, 9),  # position 18, XOR with 0xff, compare with hash_bytes[9]
        (2, 0xd6, 13),  # position 2, XOR with 0xd6, compare with hash_bytes[13]
        (9, 0xdc, 0),   # position 9, XOR with 0xdc, compare with hash_bytes[0]
        (14, 0xae, 4),  # position 14, XOR with 0xae, compare with hash_bytes[4]
        (6, 0x5, 8),    # position 6, XOR with 0x5, compare with hash_bytes[8]
        (13, 0x65, 12), # position 13, XOR with 0x65, compare with hash_bytes[12]
        (16, 0xc1, 1),  # position 16, XOR with 0xc1, compare with hash_bytes[1]
        (10, 0x2e, 5),  # position 10, XOR with 0x2e, compare with hash_bytes[5]
        (19, 0xa8, 9),  # position 19, XOR with 0xa8, compare with hash_bytes[9]
        (17, 0xda, 13), # position 17, XOR with 0xda, compare with hash_bytes[13]
    ]
    
    # Initialize result string with X's
    result = ['X'] * 23
    
    # Solve each condition
    for pos, xor_val, hash_idx in conditions:
        # Calculate the correct character: char_val ^ xor_val = hash_bytes[hash_idx]
        # So: char_val = hash_bytes[hash_idx] ^ xor_val
        char_val = hash_bytes[hash_idx] ^ xor_val
        char = chr(char_val)
        result[pos] = char
        print(f"Position {pos}: {char} (ASCII {char_val}, XOR {xor_val:02x} = {hash_bytes[hash_idx]:02x})")
    
    # Join the result
    solution = ''.join(result)
    print(f"\nSolution: {solution}")
    
    # Extract the flag part (first 20 characters, before the X padding)
    flag_part = solution[:20]
    print(f"Flag part: {flag_part}")
    
    return flag_part

if __name__ == "__main__":
    solve_b4sh33()