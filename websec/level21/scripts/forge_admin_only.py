#!/usr/bin/env python3
import sys

def xor_bytes(a: bytes, b: bytes) -> bytes:
    return bytes(x ^ y for x, y in zip(a, b))

def main():
    if len(sys.argv) != 3:
        print("Usage: forge_admin_only.py <username> <session_hex>")
        sys.exit(1)

    username = sys.argv[1]
    session_hex = sys.argv[2].strip()

    raw = bytes.fromhex(session_hex)
    if len(raw) < 32:
        print("Error: session too short")
        sys.exit(2)

    iv = raw[:16]
    c0 = raw[16:32]
    rest = raw[32:]

    username6 = (username[:6]).encode('utf-8')
    if len(username6) < 6:
        print("Error: username must be at least 6 chars")
        sys.exit(3)

    p0_orig = b"user/pass:" + username6
    p0_target = b"user/pass:admin/"  # 16 bytes

    new_iv = xor_bytes(iv, xor_bytes(p0_orig, p0_target))
    forged = new_iv + c0 + rest
    print(forged.hex())

if __name__ == "__main__":
    main()

