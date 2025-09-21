#!/usr/bin/env python3

# Leer el archivo binario
with open('output.txt', 'rb') as f:
    data = f.read()

print(f"File size: {len(data)} bytes")
print(f"First 50 bytes (hex): {data[:50].hex()}")
print(f"Last 50 bytes (hex): {data[-50:].hex()}")

# Análisis de frecuencia de bytes
from collections import Counter
byte_freq = Counter(data)
print(f"\nTop 10 most frequent bytes:")
for byte_val, count in byte_freq.most_common(10):
    print(f"  0x{byte_val:02x} ({chr(byte_val) if 32 <= byte_val <= 126 else '?'}): {count} times")

# Buscar patrones repetitivos
print(f"\nLooking for repeating patterns...")
for pattern_len in [2, 3, 4, 8, 16]:
    patterns = {}
    for i in range(len(data) - pattern_len + 1):
        pattern = data[i:i+pattern_len]
        if pattern in patterns:
            patterns[pattern] += 1
        else:
            patterns[pattern] = 1
    
    # Mostrar patrones que se repiten más de 5 veces
    repeated = {p: c for p, c in patterns.items() if c > 5}
    if repeated:
        print(f"  Patterns of length {pattern_len} (appearing > 5 times):")
        for pattern, count in sorted(repeated.items(), key=lambda x: x[1], reverse=True)[:5]:
            print(f"    {pattern.hex()}: {count} times")

# Intentar decodificar como base64
import base64
try:
    # Convertir a string primero
    data_str = data.decode('latin-1')
    # Intentar decodificar como base64
    decoded_b64 = base64.b64decode(data_str)
    print(f"\nBase64 decode successful! Decoded size: {len(decoded_b64)} bytes")
    print(f"Decoded first 100 bytes: {decoded_b64[:100]}")
    
    # Guardar el resultado decodificado
    with open('decoded_b64.bin', 'wb') as f:
        f.write(decoded_b64)
    print("Saved decoded data to decoded_b64.bin")
    
except Exception as e:
    print(f"\nBase64 decode failed: {e}")

# Intentar ROT13 y otros cifrados simples
import string
def rot_n(text, n):
    result = ""
    for char in text:
        if char.isalpha():
            ascii_offset = 65 if char.isupper() else 97
            result += chr((ord(char) - ascii_offset + n) % 26 + ascii_offset)
        else:
            result += char
    return result

try:
    data_str = data.decode('latin-1')
    for n in [13, 1, 2, 3, 5, 7, 11]:
        rotated = rot_n(data_str, n)
        if 'flag' in rotated.lower() or 'ringzer0' in rotated.lower():
            print(f"\nROT{n} might work:")
            print(rotated[:200])
except:
    pass