#!/usr/bin/env python3
import string

# Cadena secreta del reto
secret_bytes = [0x1b, 0x0f, 0x1d, 0x09, 0x1c, 0x08, 0x66, 0x49, 0x06, 0x01, 0x01, 0x02, 0x0c, 0x08, 0x74, 0x28, 0x12, 0x53, 0x01, 0x18, 0x10, 0x4e, 0x27, 0x37, 0x4c]

print("=== Solución Final - Enfoque Determinístico ===\n")

# Asumamos que el resultado es "hbh{....}" donde todo es imprimible
# Calculemos la clave EXACTA necesaria

print("Suponiendo que el resultado completo es imprimible y termina en '}'...")
print("="*70 + "\n")

# Método: Para cada posición, intentar todos los caracteres imprimibles como resultado
# y ver cuál genera una clave consistente

def find_key_for_result(target_result):
    """Calcula la clave necesaria para obtener target_result"""
    key = ""
    for i, (secret_byte, result_char) in enumerate(zip(secret_bytes, target_result)):
        key_byte = secret_byte ^ ord(result_char)
        if 32 <= key_byte <= 126:
            key += chr(key_byte)
        else:
            return None
    return key

# Generar posibles resultados válidos
# Sabemos que empieza con "hbh{" y term ina con "}"
# El formato típico de flags es hbh{XXXXXXXXXXXX}

# Caracteres comunes en flags
flag_chars = string.ascii_letters + string.digits + "_-!@#$%^&*()"

# Probar diferentes longitudes de flag
for flag_length in [25]:  # Sabemos que son 25 bytes
    prefix = "hbh{"
    suffix = "}"
    middle_length = flag_length - len(prefix) - len(suffix)
    
    print(f"Buscando flags de longitud {flag_length}...")
    print(f"Formato: hbh{{...{middle_length} caracteres...}}\n")
    
    # Generar el "middle" de forma inteligente
    # Basándonos en el resultado parcial que obtuvimos con "smurf1337":
    # "hbh{z9Uz1rlw~nE?!drue<A??"
    # Podemos ver que el patrón es bastante legible hasta cierto punto
    
    # Probemos completar esto de forma inteligente
    partial_known = "hbh{z9Uz1rlw~nE"  # 15 caracteres que sabemos que son correctos
    
    # Los últimos caracteres basándonos en el patrón
    # Probar diferentes terminaciones
    possible_endings = [
        "!Dru3<Ag3nT}",
        "3!Dru3-Ag3n}",
        "3!Drake_Alg}",
        "3!Dr4ke_Key}",
        "3_DrakeAlgo}",
        "y!drakealg0}",
    ]
    
    for ending in possible_endings:
        test_result = partial_known + ending
        if len(test_result) == 25:
            key = find_key_for_result(test_result)
            if key:
                print(f"Posible solución encontrada!")
                print(f"  Resultado: {test_result}")
                print(f"  Clave (username): {key}")
                
                # Verificar que la clave es razonable (debe ser alfanumérica principalmente)
                if all(c in string.printable and not c.isspace() for c in key):
                    print(f"  *** Clave válida! ***\n")

print("\n" + "="*70)
print("Enfoque 2: Bruteforce enfocado en los últimos caracteres")
print("="*70 + "\n")

# Sabemos que con "smurf1337" obtenemos "hbh{z9Uz1rlw~nE?!drue<A??"
# Solo necesitamos ajustar 3 caracteres problemáticos

base_username = "smurf1337"
partial_result = "hbh{z9Uz1rlw~nE"

# Los caracteres problemáticos están en las posiciones 15, 23, 24
# Necesitamos encontrar qué deberían ser en el resultado

# Generar todas las combinaciones para los 10 últimos caracteres del resultado
for c1 in "!0123456789":  # pos 15
    for chars in ["d","D","_","3"]:  # pos 17
        for c2 in "rake":  # varios
            for c3 in "e3_":  # pos 20
                for c4 in "Alg":
                    for c5 in "!0_":
                        test_result = partial_known + c1 + chars + "r" + c2 + c3 + c4 + c5 + "}"
                        
                        if len(test_result) != 25:
                            continue
                        
                        key = find_key_for_result(test_result)
                        if key and len(key) == 25:
                            # Verificar que la clave cuando se usa cíclicamente es un username válido
                            # La clave debe repetirse en un patrón
                            
                            # Intentar encontrar la longitud del patrón
                            for pattern_len in range(5, 15):
                                pattern = key[:pattern_len]
                                # Verificar si este patrón se repite
                                reconstructed = (pattern * (25 // pattern_len + 1))[:25]
                                
                                if reconstructed == key:
                                    # ¡Encontramos un patrón válido!
                                    if all(c in string.ascii_letters + string.digits + "_-" for c in pattern):
                                        print(f"\n*** SOLUCIÓN ENCONTRADA ***")
                                        print(f"Username: {pattern}")
                                        print(f"Flag: {test_result}")
                                        print(f"Clave completa: {key}")
                                        print(f"Patrón se repite cada {pattern_len} caracteres\n")
                                        
                                        # Verificar
                                        verify = []
                                        for i, byte in enumerate(secret_bytes):
                                            k_byte = ord(pattern[i % len(pattern)])
                                            verify.append(chr(byte ^ k_byte))
                                        verify_result = ''.join(verify)
                                        print(f"Verificación: {verify_result}")
                                        
                                        if verify_result == test_result:
                                            print("*** VERIFICADO CORRECTAMENTE ***")
                                            exit(0)

print("\nNo se encontró solución con este enfoque.")