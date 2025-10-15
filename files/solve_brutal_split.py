#!/usr/bin/env python3
enc = b"\x7f\xe48\x16'\xcd\x85k\rk\xaf\x89z\x9b\x88\xe3\xa7\xf1o}\x1a\xe0\x9e\xea\x08Q+\x8aX}mP\x7f\xe48\x16'\xcd\x85k\rk\xaf\x89z\x9b\x88\xe3\xa7\xf1\x06\x1en\x86\xe5\xb8M\x15j\xc9\x0c8)-"

import string

printable = set(bytes(string.printable, 'ascii'))

candidates = []
for i in range(1, len(enc)):
    c1 = int.from_bytes(enc[:i], 'big')
    c2 = int.from_bytes(enc[i:], 'big')
    x = c1 ^ c2
    b = x.to_bytes((x.bit_length()+7)//8 or 1, 'big')
    if b.startswith(b'ictf{') and b.find(b'}') != -1:
        if all(ch in printable or ch in (10, 13, 9) for ch in b):
            candidates.append((i, b))

for i, b in candidates:
    print(i, b)
