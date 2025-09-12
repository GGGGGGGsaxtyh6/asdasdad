#!/usr/bin/env python3

from hashlib import sha1
from Crypto.Util.number import bytes_to_long, long_to_bytes
from ecdsa import ellipticcurve
from ecdsa.ecdsa import curve_256, generator_256, Public_key, Private_key

# Curve parameters
G = generator_256
q = G.order()
p = curve_256.p()

# Given data from output.txt
hidden_flag = (16807196250009982482930925323199249441776811719221084165690521045921016398804, 72892323560996016030675756815328265928288098939353836408589138718802282948311)
pubkey_point = (48780765048182146279105449292746800142985733726316629478905429239240156048277, 74172919609718191102228451394074168154654001177799772446328904575002795731796)

def try_direct_flag_recovery():
    """Try to recover the flag directly without finding the private key"""
    print("Attempting direct flag recovery...")
    
    T_x, T_y = hidden_flag
    print(f"Hidden flag point: ({T_x}, {T_y})")
    
    # The flag is hidden as: T = d * Q where Q is the flag point
    # So: Q = (1/d) * T
    
    # But we don't know d. However, maybe we can try different approaches
    
    # Let's see if the flag point itself has some structure
    print(f"Trying to extract flag directly from coordinates...")
    
    # Try x-coordinate
    try:
        flag_bytes = long_to_bytes(T_x)
        flag = flag_bytes.decode('utf-8')
        print(f"Flag from x-coordinate: {flag}")
        if flag.startswith('crypto{'):
            return flag
    except:
        print("Could not decode x-coordinate as UTF-8")
    
    # Try y-coordinate
    try:
        flag_bytes = long_to_bytes(T_y)
        flag = flag_bytes.decode('utf-8')
        print(f"Flag from y-coordinate: {flag}")
        if flag.startswith('crypto{'):
            return flag
    except:
        print("Could not decode y-coordinate as UTF-8")
    
    # Maybe the flag is encoded differently
    # Let's try some common transformations
    
    # Try XOR with common values
    common_xor_keys = [0x1337, 0x31337, 0xdeadbeef, 0xcafebabe]
    
    for key in common_xor_keys:
        try:
            x_xor = T_x ^ key
            flag_bytes = long_to_bytes(x_xor)
            flag = flag_bytes.decode('utf-8')
            print(f"Flag from x XOR {key:x}: {flag}")
            if flag.startswith('crypto{'):
                return flag
        except:
            pass
        
        try:
            y_xor = T_y ^ key
            flag_bytes = long_to_bytes(y_xor)
            flag = flag_bytes.decode('utf-8')
            print(f"Flag from y XOR {key:x}: {flag}")
            if flag.startswith('crypto{'):
                return flag
        except:
            pass
    
    # Maybe the flag is encoded as x + y or x - y
    try:
        sum_val = (T_x + T_y) % p
        flag_bytes = long_to_bytes(sum_val)
        flag = flag_bytes.decode('utf-8')
        print(f"Flag from x + y: {flag}")
        if flag.startswith('crypto{'):
            return flag
    except:
        pass
    
    try:
        diff_val = (T_x - T_y) % p
        flag_bytes = long_to_bytes(diff_val)
        flag = flag_bytes.decode('utf-8')
        print(f"Flag from x - y: {flag}")
        if flag.startswith('crypto{'):
            return flag
    except:
        pass
    
    # Maybe we need to try different private key values
    # Let's try some very specific values that might be used in CTFs
    
    ctf_values = [
        1, 2, 3, 4, 5, 7, 11, 13, 17, 19, 23, 31, 37, 41, 43, 47,
        64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536,
        1337, 31337, 13371337, 133713371337,
        0x1337, 0x31337, 0x1337c0de, 0xdeadbeef, 0xcafebabe, 0xfeedface,
        123456, 654321, 123456789, 987654321,
        2**16 - 1, 2**20 - 1, 2**24 - 1,
    ]
    
    print("Trying common CTF private key values...")
    
    for d in ctf_values:
        if d >= q:
            continue
        
        try:
            # Calculate Q = (1/d) * T
            T = ellipticcurve.Point(curve_256, T_x, T_y)
            d_inv = pow(d, -1, q)
            Q = d_inv * T
            
            # Try to extract flag from Q
            Q_x, Q_y = int(Q.x()), int(Q.y())
            
            # Try x-coordinate
            try:
                flag_bytes = long_to_bytes(Q_x)
                flag = flag_bytes.decode('utf-8')
                if flag.startswith('crypto{'):
                    print(f"Found flag with d = {d}: {flag}")
                    return flag
            except:
                pass
            
            # Try y-coordinate
            try:
                flag_bytes = long_to_bytes(Q_y)
                flag = flag_bytes.decode('utf-8')
                if flag.startswith('crypto{'):
                    print(f"Found flag with d = {d}: {flag}")
                    return flag
            except:
                pass
            
        except:
            continue
    
    return None

def main():
    print("=== ECDSA Nonce Vulnerability Challenge ===")
    print("Attempting direct flag recovery without private key...")
    print()
    
    flag = try_direct_flag_recovery()
    
    if flag:
        print(f"\n=== FLAG FOUND ===")
        print(f"crypto{{{flag}}}")
    else:
        print("Failed to recover flag directly")
        print("The challenge likely requires finding the actual private key")

if __name__ == "__main__":
    main()