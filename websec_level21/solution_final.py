#!/usr/bin/env python3
import binascii
import subprocess
import re

# Cookie truncada de testxx
cookie = "3d2d8bf19f0ce326f736a1443b952355b1fad1874477f658622708ae3c2b008c833e083ffd1bce3a3d4a8e0094382e53"

iv_hex = cookie[:32]
ct_hex = cookie[32:]

iv = bytearray(binascii.unhexlify(iv_hex))

# Block 0: "user/pass:testxx"
# Quiero:  "user/pass:admin "

actual_b0 = b"user/pass:testxx"
target_b0 = b"user/pass:admin "

# Modificar IV para cambiar bloque 0
for i in range(16):
    iv[i] = iv[i] ^ actual_b0[i] ^ target_b0[i]

cookie_modified = binascii.hexlify(iv).decode() + ct_hex

print(f"Username cambiado de 'testxx' a 'admin '")
print(f"Cookie modificada: {cookie_modified}\n")

cmd = f'timeout 8s curl -s https://websec.fr/level21/index.php --cookie "session={cookie_modified}"'
result = subprocess.run(cmd, shell=True, capture_output=True, text=True)

# Buscar flag
flag_match = re.search(r'WEBSEC\{[^}]+\}', result.stdout)
if flag_match:
    print(f"★★★ FLAG ENCONTRADA! ★★★")
    print(f"{flag_match.group(0)}")
elif "Hello admin" in result.stdout:
    print(f"★★★ Hello admin encontrado! ★★★")
    # Buscar flag en el texto
    lines = result.stdout.split('\n')
    for line in lines:
        if 'flag' in line.lower() or 'admin' in line.lower():
            print(line.strip())
else:
    if "Wrong" in result.stdout:
        print("Wrong login or password - admin no encontrado en DB")
    elif "corrupted" in result.stdout:
        print("Session corrupted")
    else:
        print(f"Otro resultado. Length: {len(result.stdout)}")
        print("Primeras líneas relevantes:")
        if "Welcome" in result.stdout:
            match = re.search(r'Welcome <b>([^<]+)</b>', result.stdout)
            if match:
                print(f"  Welcome: {match.group(1)}")
