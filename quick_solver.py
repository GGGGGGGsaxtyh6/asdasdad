#!/usr/bin/env python3

# Análisis rápido del algoritmo de encriptación
# Basándome en el código C, voy a implementar una versión simplificada

def analyze_encryption():
    # La bandera encriptada
    encrypted_hex = "fa8c3453029e0e236800c26cc9d1748dde9e98c1e24804cb9602b68a4fd424fc7d7dca9fb3b4f6102ce5d3fb70cf4af0d1204c1df031fb0ec583c5108fca97904d35b6b60183cac9b6183e4e4dc94d4d8fc9356d"
    encrypted_bytes = bytes.fromhex(encrypted_hex)
    
    print(f"Datos encriptados: {len(encrypted_bytes)} bytes")
    print(f"Hex: {encrypted_hex}")
    
    # El algoritmo parece dividir los datos en:
    # - Primera parte: flag encriptada
    # - Segunda parte: checksum
    
    # Vamos a probar diferentes divisiones
    for flag_len in range(20, 60):
        if flag_len * 3 // 2 > len(encrypted_bytes):
            break
            
        flag_part = encrypted_bytes[:flag_len]
        checksum_part = encrypted_bytes[flag_len:flag_len + (flag_len // 2)]
        
        if len(checksum_part) != flag_len // 2:
            continue
            
        print(f"\nLongitud de flag: {flag_len}")
        print(f"Flag part: {flag_part.hex()}")
        print(f"Checksum part: {checksum_part.hex()}")
        
        # Buscar patrones ASCII en la parte de la flag
        ascii_chars = []
        for b in flag_part:
            if 32 <= b <= 126:  # Caracteres ASCII imprimibles
                ascii_chars.append(chr(b))
            else:
                ascii_chars.append('.')
        
        ascii_str = ''.join(ascii_chars)
        print(f"ASCII: {ascii_str}")
        
        # Buscar patrones conocidos
        if 'actf' in ascii_str.lower():
            print("¡POSIBLE PATRÓN 'actf' ENCONTRADO!")
        if '{' in ascii_str and '}' in ascii_str:
            print("¡POSIBLE FORMATO DE FLAG ENCONTRADO!")
        
        # Si encontramos algo prometedor, intentar decodificar
        if 'actf' in ascii_str.lower() or ('{' in ascii_str and '}' in ascii_str):
            print(f"LONGITUD PROMETEDORA: {flag_len}")
            print(f"Flag part: {flag_part}")
            print(f"Checksum part: {checksum_part}")
            
            # Intentar diferentes codificaciones
            try:
                # UTF-8
                decoded = flag_part.decode('utf-8', errors='ignore')
                print(f"UTF-8: {decoded}")
            except:
                pass
            
            try:
                # Latin-1
                decoded = flag_part.decode('latin-1', errors='ignore')
                print(f"Latin-1: {decoded}")
            except:
                pass

def try_xor_decryption():
    """Intentar descifrado XOR simple"""
    encrypted_hex = "fa8c3453029e0e236800c26cc9d1748dde9e98c1e24804cb9602b68a4fd424fc7d7dca9fb3b4f6102ce5d3fb70cf4af0d1204c1df031fb0ec583c5108fca97904d35b6b60183cac9b6183e4e4dc94d4d8fc9356d"
    encrypted_bytes = bytes.fromhex(encrypted_hex)
    
    print("\n=== INTENTANDO DESCIFRADO XOR ===")
    
    # Probar diferentes claves XOR
    for key in range(256):
        decrypted = bytearray()
        for b in encrypted_bytes:
            decrypted.append(b ^ key)
        
        try:
            decoded = decrypted.decode('ascii', errors='ignore')
            if 'actf{' in decoded.lower() and '}' in decoded:
                print(f"¡POSIBLE BANDERA CON XOR key {key}: {decoded}")
        except:
            pass

def try_rot_decryption():
    """Intentar descifrado ROT13/ROT47"""
    encrypted_hex = "fa8c3453029e0e236800c26cc9d1748dde9e98c1e24804cb9602b68a4fd424fc7d7dca9fb3b4f6102ce5d3fb70cf4af0d1204c1df031fb0ec583c5108fca97904d35b6b60183cac9b6183e4e4dc94d4d8fc9356d"
    encrypted_bytes = bytes.fromhex(encrypted_hex)
    
    print("\n=== INTENTANDO DESCIFRADO ROT ===")
    
    for rot in range(1, 26):
        decrypted = bytearray()
        for b in encrypted_bytes:
            if 65 <= b <= 90:  # A-Z
                decrypted.append(((b - 65 + rot) % 26) + 65)
            elif 97 <= b <= 122:  # a-z
                decrypted.append(((b - 97 + rot) % 26) + 97)
            else:
                decrypted.append(b)
        
        try:
            decoded = decrypted.decode('ascii', errors='ignore')
            if 'actf{' in decoded.lower() and '}' in decoded:
                print(f"¡POSIBLE BANDERA CON ROT{rot}: {decoded}")
        except:
            pass

if __name__ == "__main__":
    analyze_encryption()
    try_xor_decryption()
    try_rot_decryption()