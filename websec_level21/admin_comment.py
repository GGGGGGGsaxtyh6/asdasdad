#!/usr/bin/env python3
import binascii
import subprocess
import re

cookie = "3d2d8bf19f0ce326f736a1443b952355b1fad1874477f658622708ae3c2b008c833e083ffd1bce3a3d4a8e0094382e53"

iv_hex = cookie[:32]
ct_hex = cookie[32:]

iv = bytearray(binascii.unhexlify(iv_hex))

# Probar diferentes variaciones de admin + algo
targets = [
    b"user/pass:admin--",  # admin con comentario SQL
    b"user/pass:admin#+",   # admin con # comment
    b"user/pass:admin/*",   # admin con  /* comment
]

for target in targets:
    iv_test = bytearray(binascii.unhexlify(iv_hex))
    actual = b"user/pass:testxx"
    
    for i in range(16):
        iv_test[i] = iv_test[i] ^ actual[i] ^ target[i]
    
    cookie_test = binascii.hexlify(iv_test).decode() + ct_hex
    
    cmd = f'timeout 8s curl -s https://websec.fr/level21/index.php --cookie "session={cookie_test}"'
    result = subprocess.run(cmd, shell=True, capture_output=True, text=True)
    
    flag_match = re.search(r'WEBSEC\{[^}]+\}', result.stdout)
    if flag_match:
        print(f"★★★ FLAG con {target[10:]}: {flag_match.group(0)} ★★★")
        exit(0)
    
    if "Hello admin" in result.stdout:
        print(f"★★★ Hello admin con {target[10:]}! ★★★")
        exit(0)
    
    print(f"Target {target[10:]}: {'Wrong' if 'Wrong' in result.stdout else 'Otro'}")

print("\nNinguno funcionó")
