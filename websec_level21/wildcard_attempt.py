#!/usr/bin/env python3
import binascii
import subprocess
import re

cookie = "3d2d8bf19f0ce326f736a1443b952355b1fad1874477f658622708ae3c2b008c833e083ffd1bce3a3d4a8e0094382e53"

iv_hex = cookie[:32]
ct_hex = cookie[32:]

iv = bytearray(binascii.unhexlify(iv_hex))

# Block 0: "user/pass:testxx"
# Target:  "user/pass:admin%"

actual = b"user/pass:testxx"
target = b"user/pass:admin%"

for i in range(16):
    iv[i] = iv[i] ^ actual[i] ^ target[i]

cookie_modified = binascii.hexlify(iv).decode() + ct_hex

cmd = f'timeout 8s curl -s https://websec.fr/level21/index.php --cookie "session={cookie_modified}"'
result = subprocess.run(cmd, shell=True, capture_output=True, text=True)

flag_match = re.search(r'WEBSEC\{[^}]+\}', result.stdout)
if flag_match:
    print(f"FLAG: {flag_match.group(0)}")
    exit(0)

if "Hello admin" in result.stdout:
    print("Hello admin encontrado!")
    exit(0)

print("No funcionó con admin%")
