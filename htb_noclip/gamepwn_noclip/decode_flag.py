#!/usr/bin/env python3

import struct
import hashlib

with open('assets.dmp', 'rb') as f:
    data = f.read()

# Los valores especiales encontrados en el mapa
values = [0x11, 0x0a, 0x10, 0x0a, 0x08, 0x0c, 0x0b, 0x09, 
          0x0c, 0x0e, 0x0f, 0x0b, 0x09, 0x07, 0x07, 0x08, 
          0x0d, 0x12, 0x0e]

print("Special values from map:")
print(f"Hex: {' '.join(f'{v:02x}' for v in values)}")
print(f"Decimal: {values}")

print("\n" + "="*60)
print("Decoding attempts:")
print("="*60)

# Método 1: Como dígitos hexadecimales (quitando valores > 0xf)
print("\n1. As hex digits (filtering >0xF):")
hex_digits = []
for v in values:
    if v <= 0xF:
        hex_digits.append(f"{v:x}")
result = ''.join(hex_digits)
print(f"   Hex string: {result}")

# Si tenemos 32 caracteres, podría ser un MD5
if len(result) == 32:
    print(f"   Could be MD5 hash: {result}")
elif len(result) == 16:
    # Duplicar para hacer 32
    result2 = result + result
    print(f"   Doubled (32 chars): {result2}")

# Método 2: Sustituir valores especiales
print("\n2. Substituting special values:")
# 0x10 = 0, 0x11 = 1, 0x12 = 2
substituted = []
for v in values:
    if v == 0x10:
        substituted.append('0')
    elif v == 0x11:
        substituted.append('1')
    elif v == 0x12:
        substituted.append('2')
    elif v <= 0xF:
        substituted.append(f"{v:x}")
print(f"   Result: {''.join(substituted)}")

# Método 3: Como caracteres offset desde '0'
print("\n3. As offset from '0':")
offset_chars = []
for v in values:
    if v <= 0x0F:
        # Para valores 0-F, usar directamente como hex
        offset_chars.append(f"{v:x}")
    elif v == 0x10:
        offset_chars.append('g')  # 0x10 = 16 = 'g' en hex extendido
    elif v == 0x11:
        offset_chars.append('h')
    elif v == 0x12:
        offset_chars.append('i')
print(f"   Result: {''.join(offset_chars)}")

# Método 4: Reordenar por posición en el mapa
print("\n4. Analyzing positions in map:")
# Las posiciones especiales forman un patrón
positions = [
    (18,7), (22,7), (26,7), (36,8), (5,9),
    (15,10), (19,10), (2,12), (12,13), (16,13),
    (20,13), (30,14), (13,16), (17,16), (21,16),
    (27,17), (31,17), (0,18), (6,19)
]

# Agrupar por líneas donde hay múltiples valores
line_groups = {
    7: [0x11, 0x0a, 0x10],  # línea 7
    10: [0x0c, 0x0b],        # línea 10
    13: [0x0c, 0x0e, 0x0f],  # línea 13
    16: [0x09, 0x07, 0x07],  # línea 16
    17: [0x08, 0x0d],        # línea 17
}

# Método 5: Como hash MD5
print("\n5. Attempting as MD5 hash:")
# Remover valores > 0xF y reemplazar
md5_chars = []
for v in values:
    if v <= 0xF:
        md5_chars.append(f"{v:x}")
    elif v == 0x10:
        md5_chars.append("0")  # 10 hex podría ser 0
    elif v == 0x11:
        md5_chars.append("1")  # 11 hex podría ser 1  
    elif v == 0x12:
        md5_chars.append("2")  # 12 hex podría ser 2

result_md5 = ''.join(md5_chars)
print(f"   MD5 candidate: {result_md5}")

# Si es exactamente 32 caracteres, es probablemente un MD5
if len(result_md5) == 32:
    print(f"   Valid MD5 length! Hash: {result_md5}")
    print(f"   HTB flag: HTB{{{result_md5}}}")
else:
    # Intentar completar a 32 caracteres
    print(f"   Length: {len(result_md5)} (need 32 for MD5)")
    
# Método 6: Los valores podrían indicar posiciones en otro lugar
print("\n6. Values as indices into asset data:")
for i, v in enumerate(values):
    # Multiplicar por algún factor o usar como offset
    offset = v * 100  # prueba
    if offset < len(data):
        chunk = data[offset:offset+4]
        print(f"   Value {v:02x} -> offset {offset:04x}: {chunk.hex()}")

print("\n" + "="*60)
print("FINAL ATTEMPT - Building MD5:")
print("="*60)

# Los valores parecen formar un hash MD5
# 11 -> 1, 0a -> a, 10 -> 0, etc.
final_hash = "1a0a8cb9cefb9778d2e"  # Basado en los valores

# Necesitamos 32 caracteres para MD5
# Podríamos necesitar más datos o los valores se repiten
print(f"Partial hash: {final_hash}")
print(f"Length: {len(final_hash)}")

# El flag probablemente es:
if len(result_md5) >= 30:  # Cerca de 32
    print(f"\nMost likely flag: HTB{{{result_md5}}}")