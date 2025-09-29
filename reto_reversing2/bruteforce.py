#!/usr/bin/env python3
import string

# Cadena secreta del reto
secret_bytes = [0x1b, 0x0f, 0x1d, 0x09, 0x1c, 0x08, 0x66, 0x49, 0x06, 0x01, 0x01, 0x02, 0x0c, 0x08, 0x74, 0x28, 0x12, 0x53, 0x01, 0x18, 0x10, 0x4e, 0x27, 0x37, 0x4c]

print("=== Búsqueda del Username Correcto ===\n")

# Sabemos que el resultado debe empezar con "hbh{" y probablemente terminar con "}"
# Trabajemos hacia atrás para encontrar la clave

print("Calculando la clave necesaria para obtener 'hbh{'...")
print("="*70)

# Los primeros 4 bytes deben decodificar a "hbh{"
expected_start = "hbh{"
key_start = ""

for i, expected_char in enumerate(expected_start):
    secret_byte = secret_bytes[i]
    expected_byte = ord(expected_char)
    key_byte = secret_byte ^ expected_byte
    key_char = chr(key_byte)
    key_start += key_char
    print(f"[{i}] 0x{secret_byte:02x} XOR 0x{expected_byte:02x} ('{expected_char}') = 0x{key_byte:02x} ('{key_char}')")

print(f"\nClave necesaria para 'hbh{{': {key_start}")

# El último byte probablemente debe ser "}"
print("\n" + "="*70)
print("Calculando el último carácter de la clave...")
print("="*70)

last_byte = secret_bytes[-1]
expected_last = ord('}')
key_last_byte = last_byte ^ expected_last
key_last_char = chr(key_last_byte) if 32 <= key_last_byte <= 126 else f"0x{key_last_byte:02x}"

print(f"Último byte: 0x{last_byte:02x} XOR 0x{expected_last:02x} ('}}') = 0x{key_last_byte:02x} ('{key_last_char}')")

# Probar variaciones del username basado en "smurf" + números/caracteres
print("\n" + "="*70)
print("Probando variaciones de username...")
print("="*70)

base_usernames = ["smurf", "smurfy", "smurff", "smurf0", "smurfy1337"]
suffixes = ["", "0", "1", "2", "3", "7", "9", "01", "13", "37", "133", "1337", "31337", "1234", "0123"]

test_usernames = []
for base in base_usernames:
    test_usernames.append(base)
    for suffix in suffixes:
        test_usernames.append(base + suffix)

# También probar con el key_start que encontramos
test_usernames.append(key_start)
test_usernames.extend([key_start + c for c in string.printable if c.isprintable()])

found_solutions = []

for username in test_usernames:
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
    
    # Buscar resultados que empiecen con "hbh{" y terminen con "}"
    if result.startswith("hbh{") and result.endswith("}") and valid:
        found_solutions.append((username, result))
        print(f"\n*** SOLUCIÓN ENCONTRADA ***")
        print(f"Username: {username}")
        print(f"Resultado: {result}")
    elif result.startswith("hbh{") and result.count('?') <= 3:
        # Mostrar resultados prometedores
        print(f"\nPrometedor - Username: {username}")
        print(f"  Resultado: {result}")

if not found_solutions:
    print("\n" + "="*70)
    print("Intentando fuerza bruta sobre los caracteres problemáticos...")
    print("="*70)
    
    # Sabemos que los problemas están en las posiciones 15, 23, 24
    # que corresponden a los índices 6, 5, 6 del username (mod 9)
    # Para un username de 9 caracteres: posiciones [6, 7, 8]
    
    # Probar todas las combinaciones de caracteres imprimibles para esas posiciones
    base = "smurf1"
    
    for c1 in string.printable:
        if not c1.isprintable() or c1.isspace():
            continue
        for c2 in string.printable:
            if not c2.isprintable() or c2.isspace():
                continue
            for c3 in string.printable:
                if not c3.isprintable() or c3.isspace():
                    continue
                
                username = base + c1 + c2 + c3
                
                result_chars = []
                valid = True
                
                for i, byte in enumerate(secret_bytes):
                    key_char = username[i % len(username)]
                    key_byte = ord(key_char)
                    decoded = byte ^ key_byte
                    
                    if 32 <= decoded <= 126:
                        result_chars.append(chr(decoded))
                    else:
                        valid = False
                        break
                
                if valid:
                    result = ''.join(result_chars)
                    if result.startswith("hbh{") and result.endswith("}"):
                        print(f"\n*** SOLUCIÓN ENCONTRADA ***")
                        print(f"Username: {username}")
                        print(f"Resultado: {result}")
                        found_solutions.append((username, result))
                        break
            if found_solutions:
                break
        if found_solutions:
            break

if found_solutions:
    print("\n" + "="*70)
    print("SOLUCIONES FINALES:")
    print("="*70)
    for username, result in found_solutions:
        print(f"\nUsername: {username}")
        print(f"Flag: {result}")