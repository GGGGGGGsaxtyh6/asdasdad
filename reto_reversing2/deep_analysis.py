#!/usr/bin/env python3

# Cadena secreta del reto
secret_bytes = [0x1b, 0x0f, 0x1d, 0x09, 0x1c, 0x08, 0x66, 0x49, 0x06, 0x01, 0x01, 0x02, 0x0c, 0x08, 0x74, 0x28, 0x12, 0x53, 0x01, 0x18, 0x10, 0x4e, 0x27, 0x37, 0x4c]

# Alfabeto encontrado en el binario
alphabet = "NvoWd.#`/]?iE$@xkf9O42^MSuV=rl>J<|wab-UIF&7CL5_D*qsnhB80)zA6K:gH~3mc}j[pR;Z,ytT+!eGQX1{(PY"

print("=== Análisis Profundo - Algoritmo de Generación de Clave ===\n")

# El alfabeto tiene 90 caracteres y parece estar desordenado a propósito
# Esto sugiere que se usa como una tabla de sustitución

# Hipótesis: El username se usa para indexar el alfabeto de forma especial
# Probemos diferentes métodos de indexación

def test_key_generation(username, method_name, key_gen_func):
    """Prueba un método de generación de clave"""
    try:
        key = key_gen_func(username, alphabet)
        if not key:
            return
        
        # Probar XOR
        result = []
        valid = True
        for i, byte in enumerate(secret_bytes):
            key_byte = ord(key[i % len(key)])
            decoded = byte ^ key_byte
            if 32 <= decoded <= 126 or decoded == 0:
                result.append(chr(decoded) if decoded != 0 else '\0')
            else:
                result.append('?')
                if i < 10:  # Solo contar los primeros caracteres
                    valid = False
        
        decoded_string = ''.join(result)
        
        # Mostrar resultados prometedores
        if valid or decoded_string.count('?') < 8:
            print(f"\n{method_name}")
            print(f"  Username: {username}")
            print(f"  Clave: {key}")
            print(f"  Resultado: {decoded_string}")
            if 'hbh{' in decoded_string.lower():
                print(f"  *** POSIBLE SOLUCIÓN ***")
    except Exception as e:
        pass

# Método 1: Usar el índice del carácter en el alfabeto estándar
def method1(username, alphabet):
    key = ""
    for char in username:
        # Buscar el carácter en el alfabeto
        if char in alphabet:
            idx = alphabet.index(char)
            key += alphabet[idx]
        else:
            # Si no está, usar posición ASCII
            idx = ord(char) % len(alphabet)
            key += alphabet[idx]
    return key

# Método 2: Usar posición en el alfabeto como índice rotado
def method2(username, alphabet):
    key = ""
    for i, char in enumerate(username):
        idx = (ord(char) + i) % len(alphabet)
        key += alphabet[idx]
    return key

# Método 3: Usar el valor ASCII directamente para indexar
def method3(username, alphabet):
    key = ""
    for char in username:
        idx = ord(char) % len(alphabet)
        key += alphabet[idx]
    return key

# Método 4: Sumar valores ASCII acumulativamente
def method4(username, alphabet):
    key = ""
    cumsum = 0
    for char in username:
        cumsum += ord(char)
        idx = cumsum % len(alphabet)
        key += alphabet[idx]
    return key

# Método 5: Cada carácter se indexa multiplicando por su posición
def method5(username, alphabet):
    key = ""
    for i, char in enumerate(username, 1):
        idx = (ord(char) * i) % len(alphabet)
        key += alphabet[idx]
    return key

# Método 6: Usar el alfabeto como mapeo de posiciones
def method6(username, alphabet):
    # Mapear a-zA-Z0-9 a posiciones en el alfabeto
    key = ""
    for char in username:
        if 'a' <= char <= 'z':
            idx = ord(char) - ord('a')
        elif 'A' <= char <= 'Z':
            idx = 26 + ord(char) - ord('A')
        elif '0' <= char <= '9':
            idx = 52 + ord(char) - ord('0')
        else:
            idx = ord(char)
        
        if idx < len(alphabet):
            key += alphabet[idx]
        else:
            key += alphabet[idx % len(alphabet)]
    return key

# Lista de usernames a probar
usernames = [
    "smurf1337",
    "admin",
    "test",
    "drake",
    "hacker",
    "user",
    "root",
    "username",
    "challenge",
    "reverse"
]

print("Probando diferentes métodos de generación de clave...")
print("="*70)

for username in usernames:
    test_key_generation(username, f"Método 1 - Índice en alfabeto", method1)
    test_key_generation(username, f"Método 2 - ASCII + posición rotado", method2)
    test_key_generation(username, f"Método 3 - ASCII mod", method3)
    test_key_generation(username, f"Método 4 - Suma acumulativa", method4)
    test_key_generation(username, f"Método 5 - ASCII * posición", method5)
    test_key_generation(username, f"Método 6 - Mapeo estándar", method6)

print("\n" + "="*70)
print("Analizando el alfabeto en busca de patrones...")
print("="*70)

# Analizar el alfabeto
print(f"\nAlfabeto: {alphabet}")
print(f"Longitud: {len(alphabet)}")

# Verificar si hay algún patrón en el alfabeto
print("\nPrimeros 26 caracteres (posible mapeo a-z):")
print(alphabet[:26])

print("\nSiguientes 26 caracteres (posible mapeo A-Z):")
if len(alphabet) >= 52:
    print(alphabet[26:52])

print("\nSiguientes caracteres (posible mapeo 0-9):")
if len(alphabet) >= 62:
    print(alphabet[52:62] if len(alphabet) >= 62 else alphabet[52:])

# Intentar decodificar asumiendo que el alfabeto es una sustitución directa
print("\n" + "="*70)
print("Probando alfabeto como sustitución directa de a-zA-Z0-9...")
print("="*70)