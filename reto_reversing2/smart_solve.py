#!/usr/bin/env python3

# Cadena secreta del reto
secret_bytes = [0x1b, 0x0f, 0x1d, 0x09, 0x1c, 0x08, 0x66, 0x49, 0x06, 0x01, 0x01, 0x02, 0x0c, 0x08, 0x74, 0x28, 0x12, 0x53, 0x01, 0x18, 0x10, 0x4e, 0x27, 0x37, 0x4c]

print("=== Solución Inteligente ===\n")

# Sabemos que "smurf1337" casi funciona
# Los problemas están en las posiciones 15, 23, 24
# Para un username de longitud 9, estas posiciones mapean a índices:
#   pos 15 % 9 = 6  (carácter '3')
#   pos 23 % 9 = 5  (carácter '1')
#   pos 24 % 9 = 6  (carácter '3')

username = "smurf1337"
print(f"Username base: {username}")
print(f"Longitud: {len(username)}\n")

# Veamos qué carácter necesitamos en cada posición problemática
print("Analizando posiciones problemáticas:")
print("="*70)

problematic_positions = [15, 23, 24]
expected_chars_guesses = ['3', '7', '}']  # Estimaciones razonables para una flag

for pos, expected in zip(problematic_positions, expected_chars_guesses):
    secret_byte = secret_bytes[pos]
    expected_byte = ord(expected)
    needed_key_byte = secret_byte ^ expected_byte
    needed_key_char = chr(needed_key_byte) if 32 <= needed_key_byte <= 126 else f"0x{needed_key_byte:02x}"
    
    username_idx = pos % len(username)
    current_key_char = username[username_idx]
    
    print(f"Pos {pos} (username[{username_idx}]='{current_key_char}'):")
    print(f"  Secreto: 0x{secret_byte:02x}")
    print(f"  Para obtener '{expected}': necesito clave 0x{needed_key_byte:02x} ('{needed_key_char}')")
    print()

# Intentemos construir el username correcto
print("="*70)
print("Construyendo username correcto...\n")

# Primeros 4 caracteres DEBEN ser "smur" para obtener "hbh{"
# Probemos diferentes longitudes y caracteres

import string

def test_username_smart(username):
    """Prueba un username y retorna el resultado si es válido"""
    result_chars = []
    
    for i, byte in enumerate(secret_bytes):
        key_char = username[i % len(username)]
        key_byte = ord(key_char)
        decoded = byte ^ key_byte
        
        if 32 <= decoded <= 126:
            result_chars.append(chr(decoded))
        else:
            return None, None
    
    result = ''.join(result_chars)
    return result, result.startswith("hbh{") and result.endswith("}")

# Dado que sabemos los primeros 4 deben ser "smur", probemos diferentes sufijos
charset = string.ascii_letters + string.digits

# Probar longitudes de 5 a 10
for length in range(5, 11):
    print(f"Probando longitud {length}...")
    
    # Para cada longitud, generar candidatos inteligentemente
    # Focus en variaciones de "smurf" + números comunes
    
    if length == 9:
        # Para longitud 9, sabemos que casi funciona con "smurf1337"
        # Probemos variaciones cambiando los caracteres en las posiciones críticas
        
        base = list("smurf1337")
        
        # Posiciones críticas en el username para las posiciones problemáticas:
        # pos 15 % 9 = 6 -> base[6] = '3'
        # pos 23 % 9 = 5 -> base[5] = '1'  
        # pos 24 % 9 = 6 -> base[6] = '3'
        
        # Probar diferentes caracteres en las posiciones 5 y 6
        for c5 in charset:
            for c6 in charset:
                for c7 in charset:
                    for c8 in charset:
                        test_user = ''.join(base[:5]) + c5 + c6 + c7 + c8
                        result, is_valid = test_username_smart(test_user)
                        
                        if result and is_valid:
                            print(f"\n*** SOLUCIÓN ENCONTRADA ***")
                            print(f"Username: {test_user}")
                            print(f"Flag: {result}")
                            exit(0)

print("\nNo se encontró solución con este método.")
print("Probando aproximación diferente...")

# Tal vez el alfabeto se usa para transformar el username
alphabet = "NvoWd.#`/]?iE$@xkf9O42^MSuV=rl>J<|wab-UIF&7CL5_D*qsnhB80)zA6K:gH~3mc}j[pR;Z,ytT+!eGQX1{(PY"

print(f"\n Alfabeto: {alphabet}")
print(f"Longitud: {len(alphabet)}\n")

# ¿Y si el username "smurf1337" debe transformarse usando el alfabeto?
# Probar diferentes transformaciones

def transform_username(username, alphabet):
    """Transforma el username usando el alfabeto como lookup table"""
    # Mapeo estándar
    std_chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    
    transformed = ""
    for char in username:
        if char in std_chars:
            idx = std_chars.index(char)
            if idx < len(alphabet):
                transformed += alphabet[idx]
            else:
                transformed += char
        else:
            transformed += char
    
    return transformed

# Probar transformación inversa
print("Probando transformación inversa del username...")

# Crear mapeo inverso
std_chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
reverse_map = {}
for i, c in enumerate(std_chars):
    if i < len(alphabet):
        reverse_map[alphabet[i]] = c

# Intentar "decodificar" posibles usernames desde el alfabeto
possible_usernames = []

# Generar combinaciones usando el alfabeto
for length in [9]:  # Focus en longitud 9
    # Probar con caracteres del alfabeto
    test_chars = alphabet[:20]  # Probar con los primeros caracteres
    
    for c1 in "smur":  # Primeros 4 DEBEN ser "smur"
        continue

print("\nIntentando deducir desde los bytes directamente...")

# Último intento: asumir que algunos caracteres del resultado son conocidos
# y calcular la clave exacta
known_result = "hbh{" + "?"*17 + "}"
key_deduced = ""

for i, (secret_byte, result_char) in enumerate(zip(secret_bytes, known_result)):
    if result_char != '?':
        key_byte = secret_byte ^ ord(result_char)
        key_char = chr(key_byte) if 32 <= key_byte <= 126 else '?'
        key_deduced += key_char
        print(f"[{i:2d}] Clave: '{key_char}' (0x{key_byte:02x})")

print(f"\nClave deducida (parcial): {key_deduced}")