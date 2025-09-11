#!/usr/bin/env python3

# Solución final basada en el análisis del código C
# El algoritmo usa un intérprete esotérico que opera con floats especiales

def solve_beyond_rev():
    print("=== SOLUCIONANDO BEYOND_REV13035 ===")
    
    # Datos encriptados
    encrypted_hex = "fa8c3453029e0e236800c26cc9d1748dde9e98c1e24804cb9602b68a4fd424fc7d7dca9fb3b4f6102ce5d3fb70cf4af0d1204c1df031fb0ec583c5108fca97904d35b6b60183cac9b6183e4e4dc94d4d8fc9356d"
    encrypted_bytes = bytes.fromhex(encrypted_hex)
    
    print(f"Datos encriptados: {len(encrypted_bytes)} bytes")
    
    # Basándome en el análisis, la bandera probablemente tiene entre 30-40 caracteres
    # y está seguida por un checksum de la mitad de la longitud
    
    # Intentar diferentes longitudes
    for flag_len in range(30, 45):
        if flag_len * 3 // 2 > len(encrypted_bytes):
            break
            
        flag_part = encrypted_bytes[:flag_len]
        checksum_part = encrypted_bytes[flag_len:flag_len + (flag_len // 2)]
        
        if len(checksum_part) != flag_len // 2:
            continue
            
        print(f"\nLongitud de flag: {flag_len}")
        print(f"Flag part: {flag_part.hex()}")
        
        # Buscar caracteres ASCII prometedores
        ascii_chars = []
        for b in flag_part:
            if 32 <= b <= 126:
                ascii_chars.append(chr(b))
            else:
                ascii_chars.append('.')
        
        ascii_str = ''.join(ascii_chars)
        print(f"ASCII: {ascii_str}")
        
        # Buscar patrones de flag
        if '{' in ascii_str and '}' in ascii_str:
            print("¡POSIBLE FORMATO DE FLAG ENCONTRADO!")
            
            # Intentar extraer la flag
            start = ascii_str.find('{')
            end = ascii_str.find('}')
            if start != -1 and end != -1 and end > start:
                possible_flag = ascii_str[start:end+1]
                print(f"Posible flag: {possible_flag}")
                
                # Verificar si tiene el formato correcto
                if possible_flag.startswith('actf{') or possible_flag.startswith('ACTF{'):
                    print(f"¡BANDERA ENCONTRADA: {possible_flag}")
                    return possible_flag

def try_known_patterns():
    """Intentar con patrones conocidos de flags"""
    print("\n=== INTENTANDO CON PATRONES CONOCIDOS ===")
    
    # Patrones típicos de flags
    patterns = [
        "actf{",
        "ACTF{",
        "flag{",
        "FLAG{",
        "ctf{",
        "CTF{"
    ]
    
    encrypted_hex = "fa8c3453029e0e236800c26cc9d1748dde9e98c1e24804cb9602b68a4fd424fc7d7dca9fb3b4f6102ce5d3fb70cf4af0d1204c1df031fb0ec583c5108fca97904d35b6b60183cac9b6183e4e4dc94d4d8fc9356d"
    encrypted_bytes = bytes.fromhex(encrypted_hex)
    
    # Buscar patrones en diferentes posiciones
    for pattern in patterns:
        pattern_bytes = pattern.encode('ascii')
        
        for i in range(len(encrypted_bytes) - len(pattern_bytes) + 1):
            if encrypted_bytes[i:i+len(pattern_bytes)] == pattern_bytes:
                print(f"¡PATRÓN '{pattern}' ENCONTRADO en posición {i}")
                
                # Intentar extraer la flag completa
                remaining = encrypted_bytes[i:]
                try:
                    decoded = remaining.decode('ascii', errors='ignore')
                    print(f"Decodificado desde posición {i}: {decoded}")
                    
                    # Buscar el cierre de la flag
                    if '{' in decoded:
                        start = decoded.find('{')
                        end = decoded.find('}')
                        if end > start:
                            flag = decoded[start:end+1]
                            print(f"¡POSIBLE BANDERA: {flag}")
                            return flag
                except:
                    pass

def analyze_hex_structure():
    """Analizar la estructura de los datos hex"""
    print("\n=== ANÁLISIS DE ESTRUCTURA HEX ===")
    
    encrypted_hex = "fa8c3453029e0e236800c26cc9d1748dde9e98c1e24804cb9602b68a4fd424fc7d7dca9fb3b4f6102ce5d3fb70cf4af0d1204c1df031fb0ec583c5108fca97904d35b6b60183cac9b6183e4e4dc94d4d8fc9356d"
    
    # Dividir en bloques de 32 caracteres (16 bytes)
    blocks = []
    for i in range(0, len(encrypted_hex), 32):
        block = encrypted_hex[i:i+32]
        blocks.append(block)
    
    print("Bloques de 16 bytes:")
    for i, block in enumerate(blocks):
        print(f"Bloque {i}: {block}")
        
        # Intentar decodificar como ASCII
        try:
            block_bytes = bytes.fromhex(block)
            ascii_str = ""
            for b in block_bytes:
                if 32 <= b <= 126:
                    ascii_str += chr(b)
                else:
                    ascii_str += "."
            print(f"  ASCII: {ascii_str}")
        except:
            pass

if __name__ == "__main__":
    result = solve_beyond_rev()
    if not result:
        result = try_known_patterns()
    if not result:
        analyze_hex_structure()
    
    if result:
        print(f"\n¡BANDERA ENCONTRADA: {result}")
    else:
        print("\nNo se pudo encontrar la bandera con los métodos simples.")
        print("Se necesitaría implementar el intérprete esotérico completo para resolver este challenge.")