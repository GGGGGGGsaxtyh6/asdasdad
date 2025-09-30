#!/usr/bin/env python3
import binascii
import subprocess

cookie = "3c54c8bbdf06fa36b435837068f53c149bcfc5d06a599d2403d8a9c49c18c03f48fbec1c43b4dd354c6ea1c62b32966d7c0761cca9ef2a9bfb02d2dd5150ba8d8a44b56b426ea8aff342bc79f4665864"

iv_hex = cookie[:32]
ct_hex = cookie[32:]

ct = bytearray(binascii.unhexlify(ct_hex))

print("Testing padding oracle by modifying last byte of last block...")

results = {}

for val in [0x00, 0x01, 0x02, 0x10, 0x20, 0xff]:
    ct_test = bytearray(ct)
    ct_test[-1] = val
    
    cookie_test = iv_hex + binascii.hexlify(ct_test).decode()
    
    cmd = f'timeout 8s curl -s https://websec.fr/level21/index.php --cookie "session={cookie_test}"'
    result = subprocess.run(cmd, shell=True, capture_output=True, text=True)
    
    if "corrupted" in result.stdout:
        results[val] = "corrupted"
    elif "Wrong" in result.stdout:
        results[val] = "wrong"  
    elif "admin" in result.stdout:
        results[val] = "ADMIN!"
    else:
        results[val] = "other"
    
    print(f"  Byte 0x{val:02x}: {results[val]}")

print(f"\nResultados únicos: {set(results.values())}")

if len(set(results.values())) > 1:
    print("✓ HAY DIFERENCIAS - Posible oracle")
else:
    print("✗ Todas las respuestas son iguales - No hay oracle obvio")
