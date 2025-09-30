#!/usr/bin/env python3
import binascii
import subprocess

def oracle(cookie_hex):
    """Retorna True si el padding es válido"""
    cmd = f'timeout 6s curl -s https://websec.fr/level21/index.php --cookie "session={cookie_hex}"'
    result = subprocess.run(cmd, shell=True, capture_output=True, text=True)
    
    # "wrong" indica padding válido
    # "corrupted" indica padding inválido
    return "Wrong" in result.stdout or len(result.stdout) < 1400

def decrypt_block(iv_hex, ct_block_hex, previous_block_hex):
    """Descifra un bloque usando padding oracle"""
    iv = bytearray(binascii.unhexlify(iv_hex))
    ct_block = binascii.unhexlify(ct_block_hex)
    prev_block = bytearray(binascii.unhexlify(previous_block_hex))
    
    plaintext = bytearray(16)
    
    print(f"Descifrando bloque...")
    
    # Para cada byte del bloque (de derecha a izquierda)
    for pad_val in range(1, 17):
        print(f"  Byte {16-pad_val} (padding {pad_val})...", end='', flush=True)
        
        # Probar cada valor posible para este byte
        for guess in range(256):
            # Construir IV de prueba
            test_prev = bytearray(prev_block)
            
            # Setear bytes ya conocidos para producir el padding correcto
            for k in range(1, pad_val):
                test_prev[16 - k] = prev_block[16 - k] ^ plaintext[16 - k] ^ pad_val
            
            # Setear el byte actual
            test_prev[16 - pad_val] = guess
            
            # Construir cookie de prueba
            test_cookie = binascii.hexlify(test_prev).decode() + ct_block_hex
            
            # Probar
            if oracle(test_cookie):
                # Encontrado! El padding es válido
                plaintext[16 - pad_val] = guess ^ prev_block[16 - pad_val] ^ pad_val
                print(f" found! (0x{plaintext[16 - pad_val]:02x} = '{chr(plaintext[16 - pad_val]) if 32 <= plaintext[16 - pad_val] < 127 else '?'}')")
                break
        else:
            print(f" FAILED")
            return None
    
    return bytes(plaintext)

# Cookie de testxx truncada
cookie = "3d2d8bf19f0ce326f736a1443b952355b1fad1874477f658622708ae3c2b008c833e083ffd1bce3a3d4a8e0094382e53"

iv_hex = cookie[:32]
ct_hex = cookie[32:]

print("=== PADDING ORACLE ATTACK ===\n")
print("Esto puede tomar MUCHO tiempo (cientos de requests por byte)...")
print("Descifrando bloque 1...\n")

# Descifrar bloque 1 (el primero del ciphertext)
# Previous block = IV
block1_plain = decrypt_block(iv_hex, ct_hex[:32], iv_hex)

if block1_plain:
    print(f"\n✓ Bloque 1 descifrado: {block1_plain}")
    print(f"   Hex: {binascii.hexlify(block1_plain).decode()}")
    print(f"   Text: {block1_plain.decode('latin1')}")
else:
    print(f"\n✗ Fallo al descifrar")

