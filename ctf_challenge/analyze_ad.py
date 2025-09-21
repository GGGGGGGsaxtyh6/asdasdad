#!/usr/bin/env python3

# Leer el archivo binario
with open('output.txt', 'rb') as f:
    data = f.read()

print(f"File size: {len(data)} bytes")

# El patrón {àd} está en la posición 16271-16274
pattern_pos = 16271
pattern = data[pattern_pos:pattern_pos+4]
print(f"Pattern at position {pattern_pos}: {pattern}")
print(f"Hex: {pattern.hex()}")
print(f"Decoded as latin-1: {pattern.decode('latin-1')}")

# Analizar el contexto más amplio
context_start = max(0, pattern_pos - 100)
context_end = min(len(data), pattern_pos + 100)
context = data[context_start:context_end]

print(f"\nContext (100 bytes before and after):")
print(f"Hex: {context.hex()}")
print(f"Raw: {context}")

# Buscar otros patrones similares en el archivo
print(f"\n=== Searching for similar patterns ===")

# Buscar todos los patrones que empiecen con { y terminen con }
for i in range(len(data) - 4):
    if data[i] == ord('{') and data[i+3] == ord('}'):
        pattern = data[i:i+4]
        try:
            decoded = pattern.decode('latin-1')
            if decoded.isprintable():
                print(f"Position {i}: {decoded} (hex: {pattern.hex()})")
        except:
            pass

# Buscar patrones de 3 caracteres entre llaves
print(f"\n=== Searching for 3-character patterns between braces ===")
for i in range(len(data) - 4):
    if data[i] == ord('{') and data[i+4] == ord('}'):
        pattern = data[i+1:i+4]
        try:
            decoded = pattern.decode('latin-1')
            if decoded.isprintable():
                print(f"Position {i}: {{{decoded}}} (hex: {pattern.hex()})")
        except:
            pass

# Buscar patrones de 2 caracteres entre llaves
print(f"\n=== Searching for 2-character patterns between braces ===")
for i in range(len(data) - 3):
    if data[i] == ord('{') and data[i+3] == ord('}'):
        pattern = data[i+1:i+3]
        try:
            decoded = pattern.decode('latin-1')
            if decoded.isprintable():
                print(f"Position {i}: {{{decoded}}} (hex: {pattern.hex()})")
        except:
            pass

# Buscar patrones de 1 carácter entre llaves
print(f"\n=== Searching for 1-character patterns between braces ===")
for i in range(len(data) - 2):
    if data[i] == ord('{') and data[i+2] == ord('}'):
        pattern = data[i+1:i+2]
        try:
            decoded = pattern.decode('latin-1')
            if decoded.isprintable():
                print(f"Position {i}: {{{decoded}}} (hex: {pattern.hex()})")
        except:
            pass

# Probar diferentes interpretaciones del patrón {àd}
print(f"\n=== Trying different interpretations of {àd} ===")

# El patrón es: {àd} = 7be0647d
# Probar diferentes codificaciones
pattern_bytes = b'\x7b\xe0\x64\x7d'

# Probar como ASCII con diferentes shifts
for shift in range(1, 256):
    try:
        shifted = bytes([(b + shift) % 256 for b in pattern_bytes])
        shifted_str = shifted.decode('latin-1', errors='ignore')
        if shifted_str.isprintable():
            print(f"Shift {shift}: {shifted_str}")
    except:
        continue

# Probar como hexadecimal
try:
    hex_str = pattern_bytes.hex()
    print(f"As hex: {hex_str}")
    # Probar decodificar como hex
    hex_decoded = bytes.fromhex(hex_str)
    print(f"Hex decoded: {hex_decoded}")
except:
    pass

# Probar como base64
try:
    import base64
    b64_encoded = base64.b64encode(pattern_bytes).decode('ascii')
    print(f"As base64: {b64_encoded}")
    b64_decoded = base64.b64decode(b64_encoded)
    print(f"Base64 decoded: {b64_decoded}")
except:
    pass

# Buscar si {àd} podría ser parte de una flag más larga
print(f"\n=== Searching for extended patterns around {àd} ===")

# Buscar patrones más largos que contengan {àd}
for length in range(5, 50):
    for i in range(max(0, pattern_pos - length), min(len(data) - length, pattern_pos + length)):
        if i <= pattern_pos <= i + length - 1:
            chunk = data[i:i+length]
            if chunk[0] == ord('{') and chunk[-1] == ord('}'):
                try:
                    decoded = chunk.decode('latin-1', errors='ignore')
                    if decoded.isprintable() and '{àd}' in decoded:
                        print(f"Extended pattern at position {i}: {decoded}")
                except:
                    pass