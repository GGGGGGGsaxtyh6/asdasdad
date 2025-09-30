#!/usr/bin/env python3
import binascii
import subprocess
import sys

request_count = 0

def oracle_test(cookie_hex):
    global request_count
    request_count += 1
    
    cmd = f'curl -s https://websec.fr/level21/index.php --cookie "session={cookie_hex}"'
    result = subprocess.run(cmd, shell=True, capture_output=True, text=True)
    
    # Debug: ver qué respuestas hay
    if request_count == 1:
        print(f"\n[DEBUG primera respuesta]")
        print(f"  Length: {len(result.stdout)}")
        print(f"  Contains 'corrupted': {'corrupted' in result.stdout}")
        print(f"  Contains 'Wrong': {'Wrong' in result.stdout}")
        print("")
    
    # Oracle: True si NO está corrupto
    return "corrupted" not in result.stdout.lower()

# Cookie COMPLETA de testxx (no truncada)
cookie_full = "3d2d8bf19f0ce326f736a1443b952355b1fad1874477f658622708ae3c2b008c833e083ffd1bce3a3d4a8e0094382e536f94603fb7d8989981270cb4a9278477219ea6f7044fb140eee0fe5a22cca247"

print("Probando oracle con cookie completa...")
print(f"Cookie length: {len(cookie_full)} chars\n")

# Voy a modificar el ÚLTIMO bloque para hacer padding oracle
# La cookie tiene múltiples bloques

ct_full = cookie_full[32:]
ct_bytes = binascii.unhexlify(ct_full)

print(f"CT length: {len(ct_bytes)} bytes = {len(ct_bytes)//16} bloques\n")

# Para padding oracle en el último bloque, modifico el penúltimo bloque
# Último bloque empieza en byte: len(ct_bytes) - 16

last_block_start = len(ct_bytes) - 16
second_last_block_start = last_block_start - 16

print(f"Último bloque: bytes {last_block_start}-{len(ct_bytes)-1}")
print(f"Penúltimo bloque: bytes {second_last_block_start}-{last_block_start-1}")

# Probar modificar el último byte del penúltimo bloque
# y ver si afecta el padding del último bloque

ct_test = bytearray(ct_bytes)

original_byte = ct_test[last_block_start - 1]
print(f"\nOriginal último byte del penúltimo bloque: 0x{original_byte:02x}")

print(f"\nProbando primeros 10 valores para detectar oracle:\n")

for val in range(10):
    ct_test = bytearray(ct_bytes)
    ct_test[last_block_start - 1] = val
    
    test_cookie = cookie_full[:32] + binascii.hexlify(ct_test).decode()
    
    is_valid = oracle_test(test_cookie)
    print(f"  0x{val:02x}: {'VALID (no corrupted)' if is_valid else 'INVALID (corrupted)'}")

print(f"\nTotal requests: {request_count}")

