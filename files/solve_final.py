#!/usr/bin/env python3
enc = b"\x7f\xe48\x16'\xcd\x85k\rk\xaf\x89z\x9b\x88\xe3\xa7\xf1o}\x1a\xe0\x9e\xea\x08Q+\x8aX}mP\x7f\xe48\x16'\xcd\x85k\rk\xaf\x89z\x9b\x88\xe3\xa7\xf1\x06\x1en\x86\xe5\xb8M\x15j\xc9\x0c8)-"

c1, c2 = enc[:32], enc[32:]
key_int = int.from_bytes(c2, 'big')
pt1_int = int.from_bytes(c1, 'big') ^ key_int
pt1 = pt1_int.to_bytes(32, 'big')
print(pt1.rstrip(b"\x00").decode('utf-8', errors='ignore'))
