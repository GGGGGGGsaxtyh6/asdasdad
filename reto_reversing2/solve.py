#!/usr/bin/env python3
import string

# Cadena secreta del reto
secret_bytes = [0x1b, 0x0f, 0x1d, 0x09, 0x1c, 0x08, 0x66, 0x49, 0x06, 0x01, 0x01, 0x02, 0x0c, 0x08, 0x74, 0x28, 0x12, 0x53, 0x01, 0x18, 0x10, 0x4e, 0x27, 0x37, 0x4c]

print("=== Solución Determinística ===\n")

# Asumiendo que el resultado debe ser "hbh{...}" con todos caracteres imprimibles
# Calculemos la clave exacta necesaria

print("Probando con username de longitud variable...")
print("="*70)

# El patrón "smur" funciona para los primeros 4 caracteres
# Necesitamos encontrar el resto

# Para cada longitud posible de username, intentar fuerza bruta
for length in range(5, 15):
    print(f"\nProbando usernames de longitud {length}...")
    
    # Empezar con "smur" y buscar el resto
    base = "smur"
    
    def try_username(username):
        if len(username) != length:
            return None
        
        result_chars = []
        for i, byte in enumerate(secret_bytes):
            key_char = username[i % len(username)]
            key_byte = ord(key_char)
            decoded = byte ^ key_byte
            
            if 32 <= decoded <= 126:
                result_chars.append(chr(decoded))
            else:
                return None
        
        result = ''.join(result_chars)
        if result.startswith("hbh{") and result.endswith("}"):
            return result
        return None
    
    # Generar caracteres posibles para cada posición
    charset = string.ascii_letters + string.digits + "_-"
    
    # Función recursiva para generar usernames
    def generate(current):
        if len(current) == length:
            result = try_username(current)
            if result:
                print(f"\n*** SOLUCIÓN ENCONTRADA ***")
                print(f"Username: {current}")
                print(f"Flag: {result}")
                return True
            return False
        
        for char in charset:
            if generate(current + char):
                return True
        return False
    
    if generate(base):
        break
    
    # Limitar búsqueda para no tardar mucho
    if length > 9:
        print("  Búsqueda limitada para esta longitud...")

print("\n" + "="*70)
print("Método alternativo: Calcular la clave exacta desde el resultado esperado")
print("="*70)

# Si asumimos que el formato es hbh{...}, podemos calcular algunos caracteres de la clave
# Intentemos deducir el formato completo

# Los bytes problemáticos están en posiciones específicas
# Vamos a probar usernames que sean comunes en CTFs

common_usernames = [
    "smurfette", "smurf123", "smurf1234", "smurf12345",
    "smurfy", "smurfy123", "smurfy1337",
    "smurf_1337", "smurf-1337",
    "blueSmurf", "azraelsmurf",
    "papasmurf", "papa smurf".replace(" ", ""),
    "gargamel"
]

for username in common_usernames:
    result_chars = []
    valid = True
    
    for i, byte in enumerate(secret_bytes):
        key_char = username[i % len(username)]
        key_byte = ord(key_char)
        decoded = byte ^ key_byte
        
        if 32 <= decoded <= 126:
            result_chars.append(chr(decoded))
        else:
            result_chars.append('?')
            valid = False
    
    result = ''.join(result_chars)
    
    if valid and result.startswith("hbh{") and result.endswith("}"):
        print(f"\n*** SOLUCIÓN ENCONTRADA ***")
        print(f"Username: {username}")
        print(f"Flag: {result}")
        break
    elif result.startswith("hbh{"):
        errors = result.count('?')
        if errors <= 5:
            print(f"\nCercano ({errors} errores) - Username: {username}")
            print(f"  Resultado: {result}")