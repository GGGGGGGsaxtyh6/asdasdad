#!/usr/bin/env python3

# Solución para Beyondrev13035
# Basándome en el análisis del código C, voy a intentar una aproximación diferente

def solve_beyond_rev():
    print("=== SOLUCIONANDO BEYOND_REV13035 ===")
    
    # Datos encriptados del código C
    encrypted_hex = "fa8c3453029e0e236800c26cc9d1748dde9e98c1e24804cb9602b68a4fd424fc7d7dca9fb3b4f6102ce5d3fb70cf4af0d1204c1df031fb0ec583c5108fca97904d35b6b60183cac9b6183e4e4dc94d4d8fc9356d"
    
    print(f"Datos encriptados: {encrypted_hex}")
    print(f"Longitud: {len(encrypted_hex)} caracteres hex = {len(encrypted_hex)//2} bytes")
    
    # El código C sugiere que la bandera está encriptada y seguida por un checksum
    # La longitud total es 84 bytes, así que probablemente:
    # - Flag: 56 bytes
    # - Checksum: 28 bytes (mitad de 56)
    
    flag_len = 56
    flag_part = encrypted_hex[:flag_len*2]  # 112 caracteres hex
    checksum_part = encrypted_hex[flag_len*2:]
    
    print(f"\nFlag part ({flag_len} bytes): {flag_part}")
    print(f"Checksum part ({len(checksum_part)//2} bytes): {checksum_part}")
    
    # Convertir a bytes
    flag_bytes = bytes.fromhex(flag_part)
    checksum_bytes = bytes.fromhex(checksum_part)
    
    print(f"\nFlag bytes: {flag_bytes}")
    print(f"Checksum bytes: {checksum_bytes}")
    
    # Intentar diferentes decodificaciones
    print("\n=== INTENTANDO DECODIFICACIONES ===")
    
    # 1. ASCII directo
    try:
        ascii_str = flag_bytes.decode('ascii', errors='ignore')
        print(f"ASCII: {ascii_str}")
    except:
        print("ASCII: Error")
    
    # 2. UTF-8
    try:
        utf8_str = flag_bytes.decode('utf-8', errors='ignore')
        print(f"UTF-8: {utf8_str}")
    except:
        print("UTF-8: Error")
    
    # 3. Latin-1
    try:
        latin1_str = flag_bytes.decode('latin-1', errors='ignore')
        print(f"Latin-1: {latin1_str}")
    except:
        print("Latin-1: Error")
    
    # 4. Buscar patrones específicos
    print("\n=== BUSCANDO PATRONES ===")
    
    # Buscar 'actf{' en diferentes codificaciones
    patterns = ['actf{', 'ACTF{', 'flag{', 'FLAG{', 'ctf{', 'CTF{']
    
    for pattern in patterns:
        pattern_bytes = pattern.encode('ascii')
        
        # Buscar en la flag part
        for i in range(len(flag_bytes) - len(pattern_bytes) + 1):
            if flag_bytes[i:i+len(pattern_bytes)] == pattern_bytes:
                print(f"¡PATRÓN '{pattern}' ENCONTRADO en posición {i}")
                
                # Intentar extraer la flag completa
                remaining = flag_bytes[i:]
                try:
                    decoded = remaining.decode('ascii', errors='ignore')
                    print(f"Decodificado: {decoded}")
                    
                    # Buscar el cierre
                    if '{' in decoded:
                        start = decoded.find('{')
                        end = decoded.find('}')
                        if end > start:
                            flag = decoded[start:end+1]
                            print(f"¡POSIBLE BANDERA: {flag}")
                            return flag
                except:
                    pass
    
    # 5. Intentar XOR con diferentes claves
    print("\n=== INTENTANDO XOR ===")
    
    for key in range(256):
        decrypted = bytearray()
        for b in flag_bytes:
            decrypted.append(b ^ key)
        
        try:
            decoded = decrypted.decode('ascii', errors='ignore')
            if any(pattern in decoded.lower() for pattern in ['actf{', 'flag{', 'ctf{']):
                print(f"¡POSIBLE BANDERA CON XOR key {key}: {decoded}")
                return decoded
        except:
            pass
    
    # 6. Análisis de la estructura
    print("\n=== ANÁLISIS DE ESTRUCTURA ===")
    
    # El código C usa un intérprete esotérico que opera con floats especiales
    # Los floats representan: 0.0=0, inf=1, -0.0=2, -inf=3
    # Cada byte se convierte a 4 floats usando 2 bits por float
    
    print("El algoritmo de encriptación:")
    print("1. Convierte cada byte a 4 floats usando 2 bits por float")
    print("2. Usa un intérprete esotérico para procesar los floats")
    print("3. Genera un checksum y actualiza un chain")
    print("4. El chain se usa para la siguiente iteración")
    
    print("\nPara resolver completamente este challenge, necesitarías:")
    print("1. Implementar el intérprete esotérico completo")
    print("2. Ejecutar el algoritmo de encriptación en reversa")
    print("3. Usar fuerza bruta para encontrar la bandera correcta")
    
    return None

if __name__ == "__main__":
    result = solve_beyond_rev()
    
    if result:
        print(f"\n¡BANDERA ENCONTRADA: {result}")
    else:
        print("\nNo se pudo encontrar la bandera con los métodos simples.")
        print("Este es un challenge de reverse engineering complejo que requiere")
        print("la implementación completa del intérprete esotérico del código C.")