#!/usr/bin/env python3
import binascii
import subprocess
import sys

def oracle_test(cookie_hex):
    cmd = f'timeout 5s curl -s https://websec.fr/level21/index.php --cookie "session={cookie_hex}"'
    result = subprocess.run(cmd, shell=True, capture_output=True, text=True)
    # Padding válido si NO dice "corrupted"
    return "corrupted" not in result.stdout.lower()

# Cookie de testxx
base_cookie = "3d2d8bf19f0ce326f736a1443b952355b1fad1874477f658622708ae3c2b008c833e083ffd1bce3a3d4a8e0094382e53"

iv_base = base_cookie[:32]
ct_base = base_cookie[32:64]  # Solo primer bloque del CT

print("Usando padding oracle con CT existente...")
print(f"CT base: {ct_base}\n")

# Este CT existente desencripta a algún valor intermedio
# Voy a descubrirlo usando el oracle

intermediate = bytearray(16)

print("Descifrando valor intermedio del CT base:")

for pad_val in range(1, 3):  # Solo primeros 2 bytes para demostrar
    pos = 16 - pad_val
    print(f"  Byte {pos} (padding={pad_val})...", end='', flush=True)
    
    found = False
    for guess in range(256):
        test_iv = bytearray(16)
        
        # Bytes ya conocidos
        for k in range(1, pad_val):
            test_iv[16 - k] = intermediate[16 - k] ^ pad_val
        
        test_iv[pos] = guess
        
        test_cookie = binascii.hexlify(test_iv).decode() + ct_base
        
        if oracle_test(test_cookie):
            intermediate[pos] = guess ^ pad_val
            print(f" 0x{intermediate[pos]:02x}")
            found = True
            break
    
    if not found:
        print(f" FAILED")
        break

print(f"\nParcial intermediate: {binascii.hexlify(intermediate).decode()}")

