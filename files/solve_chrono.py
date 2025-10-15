#!/usr/bin/env python3
import hashlib
import time
from typing import Optional

# Ciphertext from comment in chrono_genesis.py
enc = b"\x7f\xe48\x16'\xcd\x85k\rk\xaf\x89z\x9b\x88\xe3\xa7\xf1o}\x1a\xe0\x9e\xea\x08Q+\x8aX}mP\x7f\xe48\x16'\xcd\x85k\rk\xaf\x89z\x9b\x88\xe3\xa7\xf1\x06\x1en\x86\xe5\xb8M\x15j\xc9\x0c8)-"

CONST = 12289490237894271412


def derive_key(t: int) -> int:
    seed = CONST * (t | 63)
    digest = hashlib.sha256(seed.to_bytes((seed.bit_length() + 7) // 8 or 1, 'big')).digest()
    return int.from_bytes(digest, 'big')


def xor_decrypt_block(block: bytes, key_int: int) -> bytes:
    block_int = int.from_bytes(block, 'big')
    pt_int = block_int ^ key_int
    # Minimal-length big-endian representation
    return pt_int.to_bytes((pt_int.bit_length() + 7) // 8 or 1, 'big')


def looks_like_flag(b: bytes) -> bool:
    if not b.startswith(b"ictf{"):
        return False
    # Basic ASCII check and closing brace
    if b.find(b"}") == -1:
        return False
    return all(32 <= c < 127 for c in b)


def try_time_range(start_t: int, end_t: int) -> Optional[bytes]:
    c1, c2 = enc[:32], enc[32:64]
    for t in range(start_t, end_t + 1, 64):  # step 64 seconds windows
        key = derive_key(t)
        p1 = xor_decrypt_block(c1, key)
        p2 = xor_decrypt_block(c2, key)
        flag = p1 + p2
        if looks_like_flag(flag):
            return flag
    return None


def main():
    now = int(time.time())
    # Search windows: last ~2 years first, then expand if needed
    two_years = 2 * 365 * 24 * 60 * 60
    windows = [
        (now - two_years, now + 3 * 24 * 60 * 60),  # last 2 years up to 3 days in future
        (now - 5 * 365 * 24 * 60 * 60, now + 3 * 24 * 60 * 60),  # last 5 years
        (1577836800, now + 3 * 24 * 60 * 60),  # from 2020-01-01
        (1451606400, now + 3 * 24 * 60 * 60),  # from 2016-01-01
    ]
    seen = set()
    for start_t, end_t in windows:
        start_t = (start_t // 64) * 64  # align to 64s
        end_t = (end_t // 64) * 64
        if (start_t, end_t) in seen:
            continue
        seen.add((start_t, end_t))
        flag = try_time_range(start_t, end_t)
        if flag:
            print(flag.decode('ascii', errors='ignore'))
            return
    print("NOT_FOUND")


if __name__ == "__main__":
    main()
