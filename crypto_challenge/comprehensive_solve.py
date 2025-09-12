#!/usr/bin/env python3

from hashlib import sha1
from Crypto.Util.number import bytes_to_long, long_to_bytes
from ecdsa import ellipticcurve
from ecdsa.ecdsa import curve_256, generator_256, Public_key, Private_key
import itertools

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

def comprehensive_search():
    """Comprehensive search with multiple strategies"""
    print("Starting comprehensive search...")
    
    # Get message hashes
    h1 = bytes_to_long(sha1(signatures[0]['msg'].encode()).digest())
    h2 = bytes_to_long(sha1(signatures[1]['msg'].encode()).digest())
    h3 = bytes_to_long(sha1(signatures[2]['msg'].encode()).digest())
    
    r1, s1 = signatures[0]['r'], signatures[0]['s']
    r2, s2 = signatures[1]['r'], signatures[1]['s']
    r3, s3 = signatures[2]['r'], signatures[2]['s']
    
    print(f"Signatures:")
    print(f"(r1, s1) = ({r1:x}, {s1:x})")
    print(f"(r2, s2) = ({r2:x}, {s2:x})")
    print(f"(r3, s3) = ({r3:x}, {s3:x})")
    
    # Strategy 1: Try very systematic ranges
    ranges_to_try = [
        (1, 1000),
        (1000, 10000),
        (10000, 100000),
        (100000, 1000000),
        (1000000, 10000000),
    ]
    
    for start, end in ranges_to_try:
        print(f"\nSearching range {start} to {end}...")
        for d in range(start, min(end, q)):
            if d % max(1, (end - start) // 10) == 0:
                print(f"  Testing d = {d}")
            
            try:
                # Quick check: does this generate the right public key?
                test_pubkey = Public_key(G, d * G)
                if (int(test_pubkey.point.x()), int(test_pubkey.point.y())) == pubkey_point:
                    print(f"Found matching public key for d = {d}!")
                    
                    # Generate nonces and verify signatures
                    k1 = get_nonce(d, signatures[0]['msg'])
                    k2 = get_nonce(d, signatures[1]['msg'])
                    k3 = get_nonce(d, signatures[2]['msg'])
                    
                    print(f"Generated nonces: k1={k1:x}, k2={k2:x}, k3={k3:x}")
                    
                    # Verify signatures
                    valid = 0
                    if (s1 * k1) % q == (h1 + r1 * d) % q:
                        valid += 1
                        print("Signature 1 verified")
                    if (s2 * k2) % q == (h2 + r2 * d) % q:
                        valid += 1
                        print("Signature 2 verified")
                    if (s3 * k3) % q == (h3 + r3 * d) % q:
                        valid += 1
                        print("Signature 3 verified")
                    
                    if valid == 3:
                        print(f"All signatures verified! Private key found: d = {d}")
                        return d
                    else:
                        print(f"Only {valid}/3 signatures verified for d = {d}")
            except Exception as e:
                continue
        
        print(f"Range {start}-{end} completed, no key found")
    
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
        print(f"Raw bytes: {flag_bytes}")
        return None

def main():
    print("=== ECDSA Nonce Vulnerability Challenge ===")
    print("Final comprehensive attempt...")
    print()
    
    # Try comprehensive search
    privkey_d = comprehensive_search()
    
    if privkey_d is None:
        print("Failed to recover private key with comprehensive search")
        print("The private key might be very large or there might be a different approach needed")
        return
    
    # Recover flag point
    flag_x, flag_y = recover_flag_point(privkey_d)
    
    # Extract flag
    flag = extract_flag_from_point(flag_x, flag_y)
    
    if flag:
        print(f"\n=== FLAG FOUND ===")
        print(f"crypto{{{flag}}}")
    else:
        print("Failed to extract flag")

if __name__ == "__main__":
    main()