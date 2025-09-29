#!/usr/bin/env python3

# Cadena secreta del reto
secret_bytes = [0x1b, 0x0f, 0x1d, 0x09, 0x1c, 0x08, 0x66, 0x49, 0x06, 0x01, 0x01, 0x02, 0x0c, 0x08, 0x74, 0x28, 0x12, 0x53, 0x01, 0x18, 0x10, 0x4e, 0x27, 0x37, 0x4c]

# Alfabeto encontrado en el binario
alphabet = "NvoWd.#`/]?iE$@xkf9O42^MSuV=rl>J<|wab-UIF&7CL5_D*qsnhB80)zA6K:gH~3mc}j[pR;Z,ytT+!eGQX1{(PY"

print("=== Análisis Refinado - Username: smurf1337 ===\n")

username = "smurf1337"

# El Método 1 funcionó mejor: usar el username directamente como clave
# Esto significa que cada carácter del username ES la clave directamente
# No hay transformación, simplemente username = clave

print(f"Username: {username}")
print(f"Longitud: {len(username)}\n")

# Analizar byte por byte
print("Análisis byte por byte con username como clave:")
print("="*80)

result_chars = []
for i, byte in enumerate(secret_bytes):
    key_char = username[i % len(username)]
    key_byte = ord(key_char)
    decoded = byte ^ key_byte
    decoded_char = chr(decoded) if 32 <= decoded <= 126 else '?'
    result_chars.append(decoded_char)
    
    print(f"[{i:2d}] 0x{byte:02x} XOR 0x{key_byte:02x} ('{key_char}') = 0x{decoded:02x} ('{decoded_char}')")

result = ''.join(result_chars)
print(f"\nResultado: {result}")

# El resultado tiene "hbh{z9Uz1rlw~nE?!drue<A??" 
# Los caracteres no imprimibles sugieren que podría haber un problema
# Vamos a ver qué caracteres son

print("\n" + "="*80)
print("Análisis de caracteres problemáticos:")
print("="*80)

for i, byte in enumerate(secret_bytes):
    key_char = username[i % len(username)]
    key_byte = ord(key_char)
    decoded = byte ^ key_byte
    
    if decoded < 32 or decoded > 126:
        print(f"[{i:2d}] Problema: 0x{byte:02x} XOR 0x{key_byte:02x} ('{key_char}') = 0x{decoded:02x} (no imprimible)")

# Tal vez el alfabeto se usa de otra manera
# Intentemos usar el alfabeto como tabla de sustitución donde:
# - Posición del carácter en alfabeto estándar -> carácter en el alfabeto custom

print("\n" + "="*80)
print("HIPÓTESIS: El alfabeto es una tabla de sustitución")
print("="*80)

# Crear mapeo de caracteres estándar al alfabeto
standard_chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
if len(alphabet) >= len(standard_chars):
    char_map = {}
    for i, char in enumerate(standard_chars):
        if i < len(alphabet):
            char_map[char] = alphabet[i]
    
    print(f"\nMapeo creado: {len(char_map)} caracteres")
    print("\nTraduciendo username usando el alfabeto:")
    
    translated_key = ""
    for char in username:
        if char in char_map:
            translated_key += char_map[char]
            print(f"  '{char}' -> '{char_map[char]}'")
        else:
            translated_key += char
            print(f"  '{char}' -> '{char}' (sin cambio)")
    
    print(f"\nClave traducida: {translated_key}")
    
    # Probar XOR con la clave traducida
    result_chars = []
    for i, byte in enumerate(secret_bytes):
        key_byte = ord(translated_key[i % len(translated_key)])
        decoded = byte ^ key_byte
        decoded_char = chr(decoded) if 32 <= decoded <= 126 else '?'
        result_chars.append(decoded_char)
    
    result = ''.join(result_chars)
    print(f"\nResultado con clave traducida: {result}")

# Probar interpretación inversa: username ya está en el alfabeto custom
print("\n" + "="*80)
print("HIPÓTESIS INVERSA: Decodificar username del alfabeto custom")
print("="*80)

# Crear mapeo inverso
if len(alphabet) >= len(standard_chars):
    reverse_map = {}
    for i, char in enumerate(standard_chars):
        if i < len(alphabet):
            reverse_map[alphabet[i]] = char
    
    # Intentar decodificar el username
    if all(c in reverse_map or c in standard_chars for c in username):
        decoded_username = ""
        for char in username:
            decoded_username += reverse_map.get(char, char)
        
        print(f"Username decodificado: {decoded_username}")
        
        # Usar el username decodificado como clave
        result_chars = []
        for i, byte in enumerate(secret_bytes):
            key_byte = ord(decoded_username[i % len(decoded_username)])
            decoded = byte ^ key_byte
            decoded_char = chr(decoded) if 32 <= decoded <= 126 else '?'
            result_chars.append(decoded_char)
        
        result = ''.join(result_chars)
        print(f"Resultado: {result}")

print("\n" + "="*80)
print("Probando con el alfabeto como clave directa (primeros N caracteres)")
print("="*80)

key = alphabet[:len(username)]
print(f"Clave: {key}")

result_chars = []
for i, byte in enumerate(secret_bytes):
    key_byte = ord(key[i % len(key)])
    decoded = byte ^ key_byte
    decoded_char = chr(decoded) if 32 <= decoded <= 126 else '?'
    result_chars.append(decoded_char)

result = ''.join(result_chars)
print(f"Resultado: {result}")