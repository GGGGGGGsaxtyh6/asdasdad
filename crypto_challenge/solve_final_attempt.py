#!/usr/bin/env python3

from hashlib import sha1
from Crypto.Util.number import bytes_to_long, long_to_bytes
from ecdsa import ellipticcurve
from ecdsa.ecdsa import curve_256, generator_256, Public_key, Private_key
import random

# Curve parameters
G = generator_256
q = G.order()
p = curve_256.p()

# Given data from output.txt
hidden_flag = (16807196250009982482930925323199249441776811719221084165690521045921016398804, 72892323560996016030675756815328265928288098939353836408589138718802282948311)
pubkey_point = (48780765048182146279105449292746800142985733726316629478905429239240156048277, 74172919609718191102228451394074168154654001177799772446328904575002795731796)

# Signatures
signatures = [
    {
        'msg': 'I have hidden the secret flag as a point of an elliptic curve using my private key.',
        'r': 0x91f66ac7557233b41b3044ab9daf0ad891a8ffcaf99820c3cd8a44fc709ed3ae,
        's': 0x1dd0a378454692eb4ad68c86732404af3e73c6bf23a8ecc5449500fcab05208d
    },
    {
        'msg': 'The discrete logarithm problem is very hard to solve, so it will remain a secret forever.',
        'r': 0xe8875e56b79956d446d24f06604b7705905edac466d5469f815547dea7a3171c,
        's': 0x582ecf967e0e3acf5e3853dbe65a84ba59c3ec8a43951bcff08c64cb614023f8
    },
    {
        'msg': 'Good luck!',
        'r': 0x566ce1db407edae4f32a20defc381f7efb63f712493c3106cf8e85f464351ca6,
        's': 0x9e4304a36d2c83ef94e19a60fb98f659fa874bfb999712ceb58382e2ccda26ba
    }
]

def get_nonce(privkey_d, msg):
    """Generate nonce using the same method as in the challenge"""
    hsh = sha1(msg.encode()).digest()
    nonce = sha1(long_to_bytes(privkey_d) + hsh).digest()
    return bytes_to_long(nonce)

def pollard_rho_discrete_log(base, target, order):
    """Pollard's rho algorithm for discrete logarithm"""
    print("Attempting Pollard's rho algorithm...")
    
    def f(x, a, b, order):
        # Partition function for Pollard's rho
        if x % 3 == 0:
            return (x * x) % order, (2 * a) % order, (2 * b) % order
        elif x % 3 == 1:
            return (base * x) % order, (a + 1) % order, b
        else:
            return (target * x) % order, a, (b + 1) % order
    
    # Initialize
    x1, a1, b1 = 1, 0, 0
    x2, a2, b2 = 1, 0, 0
    
    for i in range(int(order**0.5) + 1):
        if i % 100000 == 0:
            print(f"Pollard's rho iteration {i}")
        
        # Tortoise
        x1, a1, b1 = f(x1, a1, b1, order)
        
        # Hare
        x2, a2, b2 = f(x2, a2, b2, order)
        x2, a2, b2 = f(x2, a2, b2, order)
        
        if x1 == x2:
            # Found collision
            r = (a1 - a2) % order
            s = (b2 - b1) % order
            
            if s == 0:
                continue  # Try again
            
            try:
                s_inv = pow(s, -1, order)
                result = (r * s_inv) % order
                
                # Verify
                if pow(base, result, order) == target % order:
                    return result
            except:
                continue
    
    return None

def baby_step_giant_step(base, target, order, max_steps=2**20):
    """Baby-step Giant-step algorithm"""
    print("Attempting Baby-step Giant-step algorithm...")
    
    m = int(max_steps**0.5) + 1
    print(f"Using m = {m}")
    
    # Baby steps
    baby_steps = {}
    gamma = 1
    for j in range(m):
        if j % 10000 == 0:
            print(f"Baby step {j}")
        baby_steps[gamma] = j
        gamma = (gamma * base) % order
    
    # Giant steps
    factor = pow(base, -m, order)  # base^(-m)
    y = target
    
    for i in range(m):
        if i % 10000 == 0:
            print(f"Giant step {i}")
        if y in baby_steps:
            result = i * m + baby_steps[y]
            if result < order:
                return result
        y = (y * factor) % order
    
    return None

def solve_discrete_log():
    """Try to solve the discrete logarithm problem"""
    print("Attempting to solve discrete logarithm problem...")
    
    # We know that pubkey = d * G
    # So we need to find d such that d * G = pubkey
    
    # Convert public key to point
    pubkey_x, pubkey_y = pubkey_point
    pubkey = ellipticcurve.Point(curve_256, pubkey_x, pubkey_y)
    
    # Try Pollard's rho first
    print("Trying Pollard's rho...")
    # This is complex for elliptic curves, let's skip for now
    
    # Try baby-step giant-step with smaller range
    print("Trying baby-step giant-step with brute force verification...")
    
    # Since this is a CTF, the private key might be in a reasonable range
    # Let's try a more targeted approach
    
    max_range = 2**24  # Reasonable for CTF
    step = 1000
    
    for start in range(0, max_range, step):
        if start % 100000 == 0:
            print(f"Checking range {start} to {start + step}")
        
        for d in range(start, min(start + step, max_range)):
            try:
                test_point = d * G
                if (int(test_point.x()), int(test_point.y())) == pubkey_point:
                    print(f"Found private key: d = {d}")
                    return d
            except:
                continue
    
    return None

def recover_flag_point(privkey_d):
    """Recover the flag point using the private key"""
    print(f"Recovering flag point with private key d = {privkey_d}")
    
    T_x, T_y = hidden_flag
    T = ellipticcurve.Point(curve_256, T_x, T_y)
    
    # Calculate Q = (1/d) * T
    d_inv = pow(privkey_d, -1, q)
    Q = d_inv * T
    
    print(f"Recovered flag point: Q = ({int(Q.x())}, {int(Q.y())})")
    return int(Q.x()), int(Q.y())

def extract_flag_from_point(x, y):
    """Extract flag from the point coordinates"""
    print(f"Extracting flag from point ({x}, {y})")
    
    flag_bytes = long_to_bytes(x)
    
    try:
        flag = flag_bytes.decode('utf-8')
        print(f"Extracted flag: {flag}")
        return flag
    except:
        print("Could not decode flag as UTF-8")
        return None

def main():
    print("=== ECDSA Nonce Vulnerability Challenge ===")
    print()
    
    # Since brute force is not working, let's try a different approach
    # Maybe the vulnerability is not what I think it is
    
    # Let me try to understand the problem better
    print("Challenge analysis:")
    print(f"Public key: {pubkey_point}")
    print(f"Hidden flag: {hidden_flag}")
    print(f"Number of signatures: {len(signatures)}")
    
    # Try to solve using discrete logarithm algorithms
    privkey_d = solve_discrete_log()
    
    if privkey_d is None:
        print("Failed to recover private key using discrete log algorithms")
        return
    
    # Step 2: Recover flag point
    flag_x, flag_y = recover_flag_point(privkey_d)
    
    # Step 3: Extract flag
    flag = extract_flag_from_point(flag_x, flag_y)
    
    if flag:
        print(f"\n=== FLAG FOUND ===")
        print(f"crypto{{{flag}}}")
    else:
        print("Failed to extract flag")

if __name__ == "__main__":
    main()