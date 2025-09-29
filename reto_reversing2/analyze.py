#!/usr/bin/env python3

# Cadena secreta del reto
secret_bytes = [0x1b, 0x0f, 0x1d, 0x09, 0x1c, 0x08, 0x66, 0x49, 0x06, 0x01, 0x01, 0x02, 0x0c, 0x08, 0x74, 0x28, 0x12, 0x53, 0x01, 0x18, 0x10, 0x4e, 0x27, 0x37, 0x4c]

# String encontrada en el binario - probablemente un alfabeto para generar la clave
alphabet = "NvoWd.#`/]?iE$@xkf9O42^MSuV=rl>J<|wab-UIF&7CL5_D*qsnhB80)zA6K:gH~3mc}j[pR;Z,ytT+!eGQX1{(PY"

print("=== Análisis Reverse Engineering 2 ===\n")
print(f"Cadena secreta (hex): {' '.join([hex(b) for b in secret_bytes])}")
print(f"Longitud: {len(secret_bytes)} bytes\n")
print(f"Alfabeto encontrado: {alphabet}")
print(f"Longitud del alfabeto: {len(alphabet)} caracteres\n")

# El programa toma un username como argumento
# Posiblemente usa el username para generar la clave usando el alfabeto

# Hipótesis 1: Cada carácter del username se mapea al alfabeto usando su posición/valor
print("="*70)
print("HIPÓTESIS 1: Mapeo basado en valores de caracteres del username")
print("="*70)

# Probar con username común: "smurf1337" (del reto anterior)
test_usernames = ["smurf1337", "admin", "test", "user", "hacker"]

for username in test_usernames:
    print(f"\nProbando username: {username}")
    
    # Generar clave usando diferentes métodos
    # Método 1: índice del carácter en el alfabeto
    key_chars = []
    for i, char in enumerate(username):
        # Usar el valor ASCII del carácter como índice en el alfabeto
        idx = ord(char) % len(alphabet)
        key_chars.append(alphabet[idx])
    
    key = ''.join(key_chars)
    print(f"  Clave generada (método 1 - ASCII mod): {key}")
    
    # Probar XOR
    result = []
    for i, byte in enumerate(secret_bytes):
        key_byte = ord(key[i % len(key)])
        decoded = byte ^ key_byte
        result.append(chr(decoded) if 32 <= decoded <= 126 else '?')
    
    decoded_string = ''.join(result)
    if decoded_string.count('?') < 5:  # Si tiene pocos caracteres no imprimibles
        print(f"  Decodificación: {decoded_string}")

print("\n" + "="*70)
print("HIPÓTESIS 2: El alfabeto es la clave generada directamente")
print("="*70)

# Probar usando el alfabeto directamente como clave
key_bytes = [ord(c) for c in alphabet]
result = []
for i, byte in enumerate(secret_bytes):
    key_byte = key_bytes[i % len(key_bytes)]
    decoded = byte ^ key_byte
    result.append(chr(decoded) if 32 <= decoded <= 126 else '?')

decoded_string = ''.join(result)
print(f"Resultado: {decoded_string}")

print("\n" + "="*70)
print("HIPÓTESIS 3: Análisis detallado de la cadena")
print("="*70)

# Analizar los bytes de la cadena secreta
print("\nAnálisis de bytes:")
for i, byte in enumerate(secret_bytes):
    print(f"[{i:2d}] 0x{byte:02x} ({byte:3d}) = '{chr(byte) if 32 <= byte <= 126 else '?'}'")