#!/usr/bin/env python3
import binascii
import subprocess
import sys
import time

request_count = 0
start_time = time.time()

def oracle_test(cookie_hex):
    global request_count
    request_count += 1
    
    cmd = f'timeout 5s curl -s https://websec.fr/level21/index.php --cookie "session={cookie_hex}"'
    result = subprocess.run(cmd, shell=True, capture_output=True, text=True)
    return "corrupted" not in result.stdout.lower()

# Solo voy a descifrar los primeros 8 bytes del intermediate para ahorrar tiempo
# y luego ver si puedo extrapolar

cookie = "3d2d8bf19f0ce326f736a1443b952355b1fad1874477f658622708ae3c2b008c"

iv_hex = cookie[:32]
ct_hex = cookie[32:]

print("Descifrando primeros 8 bytes del intermedio (optimizado)...\n")

intermediate = bytearray(16)

for pad_val in range(1, 9):  # Solo primeros 8 bytes
    pos = 16 - pad_val
    print(f"Byte {pos}...", end='', flush=True)
    
    found = False
    # Optimización: probar valores comunes primero
    common_first = [0x00, 0x20, 0x0a, 0x0d, 0x09] + list(range(32, 127))  # Nulls, espacios, ASCII imprimibles
    
    for guess in common_first + [x for x in range(256) if x not in common_first]:
        test_iv = bytearray(16)
        
        for k in range(1, pad_val):
            test_iv[16 - k] = intermediate[16 - k] ^ pad_val
        
        test_iv[pos] = guess
        
        test_cookie = binascii.hexlify(test_iv).decode() + ct_hex
        
        if oracle_test(test_cookie):
            intermediate[pos] = guess ^ pad_val
            print(f" 0x{intermediate[pos]:02x} ({chr(intermediate[pos]) if 32 <= intermediate[pos] < 127 else '?'})")
            found = True
            break
    
    if not found:
        print(f" FAILED after {request_count} requests")
        break

elapsed = time.time() - start_time
print(f"\nRequests: {request_count}, Time: {elapsed:.1f}s")
print(f"Rate: {elapsed/request_count:.2f}s per request")
print(f"\nIntermedio parcial: {binascii.hexlify(intermediate).decode()}")

