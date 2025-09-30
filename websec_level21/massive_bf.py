#!/usr/bin/env python3
import binascii
import subprocess
import sys

cookie = "3c54c8bbdf06fa36b435837068f53c149bcfc5d06a599d2403d8a9c49c18c03f48fbec1c43b4dd354c6ea1c62b32966d7c0761cca9ef2a9bfb02d2dd5150ba8d8a44b56b426ea8aff342bc79f4665864"

iv_hex = cookie[:32]
ct_hex = cookie[32:]

ct_orig = bytearray(binascii.unhexlify(ct_hex))

print(f"Ciphertext length: {len(ct_orig)} bytes")
print(f"Probando modificar cada byte con XOR 0x01...")
print(f"(Esto probará {len(ct_orig)} variaciones)\n")

for pos in range(len(ct_orig)):
    ct_test = bytearray(ct_orig)
    ct_test[pos] ^= 0x01  # Flip de 1 bit
    
    cookie_test = iv_hex + binascii.hexlify(ct_test).decode()
    
    cmd = f'timeout 6s curl -s https://websec.fr/level21/index.php --cookie "session={cookie_test}"'
    result = subprocess.run(cmd, shell=True, capture_output=True, text=True)
    
    if "WEBSEC{" in result.stdout:
        print(f"\n★★★ POSICIÓN {pos}: FLAG ENCONTRADA! ★★★")
        import re
        flag = re.search(r'WEBSEC\{[^}]+\}', result.stdout)
        if flag:
            print(f"Flag: {flag.group(0)}")
            sys.exit(0)
    
    if "Hello admin" in result.stdout:
        print(f"\n★★★ POSICIÓN {pos}: HELLO ADMIN! ★★★")
        sys.exit(0)
    
    if pos % 8 == 0:
        sys.stdout.write(f"\rProbado: {pos}/{len(ct_orig)}")
        sys.stdout.flush()

print(f"\n\nCompletado. No encontrado con XOR 0x01")
