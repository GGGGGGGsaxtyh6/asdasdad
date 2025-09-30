#!/usr/bin/env python3
import binascii
import subprocess
import sys

def oracle(cookie_hex):
    cmd = f'timeout 6s curl -s https://websec.fr/level21/index.php --cookie "session={cookie_hex}"'
    result = subprocess.run(cmd, shell=True, capture_output=True, text=True)
    return "Wrong" in result.stdout

# Simplificado: solo descifraré el valor intermedio del bloque que ya existe
# y luego calcularé el IV necesario

# Cookie testxx
cookie = "3d2d8bf19f0ce326f736a1443b952355b1fad1874477f658622708ae3c2b008c833e083ffd1bce3a3d4a8e0094382e53"

iv_hex = cookie[:32]
ct_hex = cookie[32:]

ct_block1 = ct_hex[:32]  # Primer bloque del ciphertext

# Ya descifré este bloque antes con el oracle:
# D(K, CT_block1) XOR IV = plaintext
# Entonces: D(K, CT_block1) = plaintext XOR IV

# Plaintext conocido del bloque 1 (de la cookie original):
# Con truncamiento, bloque 1 es "/[null padding]" o similar

# En realidad, con truncamiento el bloque 1 no existe o es padding...
# Déjame usar el bloque 0 que sí se desencripta

# Para bloque 0, necesito conocer el plaintext original "user/pass:testxx"
plaintext_b0_original = b"user/pass:testxx"
iv_original = binascii.unhexlify(iv_hex)
ct_b0 = binascii.unhexlify(ct_block1)  # Primer bloque del CT

# Calcular D(K, CT_b0) (valor intermedio)
# D(K, CT_b0) = plaintext XOR IV
intermediate = bytearray(16)
for i in range(16):
    intermediate[i] = plaintext_b0_original[i] ^ iv_original[i]

print(f"Intermediate value calculado: {binascii.hexlify(intermediate).decode()}")

# Ahora, para producir plaintext "user/pass:admin\x00":
target_plain = b"user/pass:admin\x00"
if len(target_plain) < 16:
    target_plain = target_plain + b'\x00' * (16 - len(target_plain))

# IV necesario = intermediate XOR target_plain
iv_needed = bytearray(16)
for i in range(16):
    iv_needed[i] = intermediate[i] ^ target_plain[i]

iv_needed_hex = binascii.hexlify(iv_needed).decode()

print(f"\nIV necesario para 'admin': {iv_needed_hex}")

# Construir cookie completa
cookie_admin = iv_needed_hex + ct_block1

print(f"\nCookie final: {cookie_admin}")
print(f"\nProbando...")

cmd = f'timeout 8s curl -s https://websec.fr/level21/index.php --cookie "session={cookie_admin}"'
result = subprocess.run(cmd, shell=True, capture_output=True, text=True)

import re
flag_match = re.search(r'WEBSEC\{[^}]+\}', result.stdout)
if flag_match:
    print(f"\n★★★ FLAG: {flag_match.group(0)} ★★★")
elif "Hello admin" in result.stdout:
    print(f"\n★★★ Hello admin encontrado! ★★★")
    for line in result.stdout.split('\n'):
        if 'flag' in line.lower():
            print(line.strip())
else:
    print(f"Resultado: {'Wrong' if 'Wrong' in result.stdout else ('Corrupted' if 'corrupted' in result.stdout else 'Otro')}")
    print(f"Length: {len(result.stdout)}")

