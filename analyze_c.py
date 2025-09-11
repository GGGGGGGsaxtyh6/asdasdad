#!/usr/bin/env python3

# Análisis detallado del código C para entender el algoritmo

def analyze_encryption_algorithm():
    print("=== ANÁLISIS DEL ALGORITMO DE ENCRIPTACIÓN ===")
    
    print("\n1. FUNCIONES PRINCIPALES:")
    print("- f2b(): Convierte float a 2 bits (0.0=0, inf=1, -0.0=2, -inf=3)")
    print("- b2f(): Convierte 2 bits a float")
    print("- c2f(): Convierte byte a 4 floats usando 2 bits por float")
    print("- cpop(): Convierte 4 floats del stack a byte")
    
    print("\n2. PROCESO DE ENCRIPTACIÓN:")
    print("- Se toma un par de bytes de la flag")
    print("- Se convierten a floats usando c2f()")
    print("- Se ejecuta el algoritmo 'algo' con el intérprete esotérico")
    print("- Se obtiene un byte de checksum y se actualiza el chain")
    print("- El chain se usa para la siguiente iteración")
    
    print("\n3. ESTRUCTURA DE DATOS:")
    print("- flag[]: La bandera original")
    print("- chainstart: 0x5f3759df (valor inicial del chain)")
    print("- chksum[]: Array de checksums (mitad de la longitud de la flag)")
    
    print("\n4. ALGORITMO 'algo':")
    algo = "aqpb2345^xbhijg6789^xbfeno5432^xbcdlk9876^xbabcd6789^gb42345nopq^gb4^xbijkl2345^gb4efgh6789^gb4^xb2345^al;"
    print(f"Algoritmo: {algo}")
    print("- Usa variables 'a' a 'q' para almacenar el chain")
    print("- Usa funciones como 'gb4', 'xb', 'al' para procesar")
    print("- Al final llama a 'al' que parece ser la función principal")
    
    print("\n5. FUNCIÓN 'al' (función principal):")
    print("- Es una función muy larga con muchas llamadas a 'l'")
    print("- Parece ser una función de encriptación compleja")
    print("- Usa operaciones matemáticas con floats especiales")
    
    print("\n6. ESTRATEGIA DE DESCIFRADO:")
    print("- Necesitamos implementar el intérprete esotérico completo")
    print("- Ejecutar el algoritmo en reversa")
    print("- Usar fuerza bruta para encontrar la bandera correcta")

def try_brute_force():
    """Intentar fuerza bruta con patrones conocidos"""
    print("\n=== FUERZA BRUTA CON PATRONES CONOCIDOS ===")
    
    # Patrones típicos de flags de CTF
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
    
    # Intentar diferentes longitudes y buscar patrones
    for flag_len in range(20, 60):
        if flag_len * 3 // 2 > len(encrypted_bytes):
            break
            
        flag_part = encrypted_bytes[:flag_len]
        
        # Buscar patrones en diferentes posiciones
        for pattern in patterns:
            pattern_bytes = pattern.encode('ascii')
            
            # Buscar el patrón en diferentes posiciones
            for i in range(len(flag_part) - len(pattern_bytes) + 1):
                if flag_part[i:i+len(pattern_bytes)] == pattern_bytes:
                    print(f"¡PATRÓN '{pattern}' ENCONTRADO en posición {i} para longitud {flag_len}")
                    print(f"Flag part: {flag_part.hex()}")
                    print(f"ASCII: {flag_part.decode('ascii', errors='ignore')}")
                    
                    # Intentar completar la flag
                    if i == 0:  # El patrón está al inicio
                        print(f"¡POSIBLE BANDERA INICIANDO CON '{pattern}'!")
                        print(f"Longitud: {flag_len}")
                        print(f"Datos: {flag_part.hex()}")
                        
                        # Intentar decodificar el resto
                        try:
                            decoded = flag_part.decode('ascii', errors='ignore')
                            print(f"Decodificado: {decoded}")
                        except:
                            pass

def analyze_hex_patterns():
    """Analizar patrones en los datos hex"""
    print("\n=== ANÁLISIS DE PATRONES HEX ===")
    
    encrypted_hex = "fa8c3453029e0e236800c26cc9d1748dde9e98c1e24804cb9602b68a4fd424fc7d7dca9fb3b4f6102ce5d3fb70cf4af0d1204c1df031fb0ec583c5108fca97904d35b6b60183cac9b6183e4e4dc94d4d8fc9356d"
    
    print(f"Longitud total: {len(encrypted_hex)} caracteres hex = {len(encrypted_hex)//2} bytes")
    
    # Buscar patrones repetitivos
    print("\nBuscando patrones repetitivos...")
    for length in range(2, 10):
        for i in range(len(encrypted_hex) - length):
            pattern = encrypted_hex[i:i+length]
            count = encrypted_hex.count(pattern)
            if count > 1:
                print(f"Patrón '{pattern}' aparece {count} veces")
    
    # Buscar secuencias que podrían ser ASCII
    print("\nBuscando secuencias ASCII...")
    for i in range(0, len(encrypted_hex), 2):
        if i + 1 < len(encrypted_hex):
            byte_hex = encrypted_hex[i:i+2]
            byte_val = int(byte_hex, 16)
            if 32 <= byte_val <= 126:  # ASCII imprimible
                char = chr(byte_val)
                print(f"Posición {i//2}: {byte_hex} = '{char}'")

if __name__ == "__main__":
    analyze_encryption_algorithm()
    try_brute_force()
    analyze_hex_patterns()