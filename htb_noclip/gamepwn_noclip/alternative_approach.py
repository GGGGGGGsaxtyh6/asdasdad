#!/usr/bin/env python3

import struct

print("=== ENFOQUE ALTERNATIVO - REINTERPRETACIÓN COMPLETA ===\n")

with open('assets.dmp', 'rb') as f:
    data = f.read()

# Los valores especiales y sus posiciones exactas
special_values = [
    (0, 18, 0x12), (2, 12, 0x09), (5, 9, 0x08), (6, 19, 0x0e),
    (12, 13, 0x0c), (13, 16, 0x09), (15, 10, 0x0c), (16, 13, 0x0e),
    (17, 16, 0x07), (18, 7, 0x11), (19, 10, 0x0b), (20, 13, 0x0f),
    (21, 16, 0x07), (22, 7, 0x0a), (26, 7, 0x10), (27, 17, 0x08),
    (30, 14, 0x0b), (31, 17, 0x0d), (36, 8, 0x0a)
]

print("Hipótesis: El orden correcto no es por posición sino por otro criterio\n")

# Intentar ordenar por valor
by_value = sorted(special_values, key=lambda x: x[2])
print("1. Ordenado por valor:")
val_sequence = ''.join(f"{v:x}" if v <= 0xf else str(v-0x10) for x,y,v in by_value)
print(f"   Secuencia: {val_sequence}")

# Intentar ordenar por coordenada X
by_x = sorted(special_values, key=lambda x: x[0])
print("\n2. Ordenado por coordenada X:")
x_sequence = ''.join(f"{v:x}" if v <= 0xf else str(v-0x10) for x,y,v in by_x)
print(f"   Secuencia: {x_sequence}")

# Intentar ordenar por coordenada Y
by_y = sorted(special_values, key=lambda x: x[1])
print("\n3. Ordenado por coordenada Y:")
y_sequence = ''.join(f"{v:x}" if v <= 0xf else str(v-0x10) for x,y,v in by_y)
print(f"   Secuencia: {y_sequence}")

# Intentar orden de lectura (Y primero, luego X)
by_reading = sorted(special_values, key=lambda x: (x[1], x[0]))
print("\n4. Orden de lectura (Y,X):")
read_sequence = ''.join(f"{v:x}" if v <= 0xf else str(v-0x10) for x,y,v in by_reading)
print(f"   Secuencia: {read_sequence}")

# Verificar si hay un patrón en las coordenadas
print("\n5. Análisis de coordenadas:")
print("   ¿Las coordenadas forman algún mensaje?")
x_coords = [x for x,y,v in special_values]
y_coords = [y for x,y,v in special_values]
print(f"   X coords: {x_coords}")
print(f"   Y coords: {y_coords}")

# Convertir coordenadas a caracteres ASCII
x_as_ascii = ''.join(chr(x+65) if x+65 < 127 else '?' for x in x_coords)
print(f"   X como ASCII (+65): {x_as_ascii}")

# Buscar en el binario referencias a estos valores
print("\n6. Buscando en el binario:")
with open('noclip', 'rb') as f:
    binary = f.read()

# Buscar la secuencia de valores
for seq in [val_sequence, x_sequence, y_sequence, read_sequence]:
    if len(seq) >= 16:
        try:
            pattern = bytes.fromhex(seq[:16])
            pos = binary.find(pattern)
            if pos != -1:
                print(f"   ¡Patrón encontrado! {seq[:16]} en offset {hex(pos)}")
        except:
            pass

# Verificar si el juego espera un orden específico de visita
print("\n7. Orden de visita esperado:")
# El juego podría verificar si visitamos las posiciones en un orden específico
# Buscar en el código comparaciones con estas coordenadas

coord_checks = []
for x, y, v in special_values:
    # Buscar comparaciones con estas coordenadas
    x_bytes = struct.pack('<I', x)
    y_bytes = struct.pack('<I', y)
    
    x_pos = binary.find(x_bytes)
    y_pos = binary.find(y_bytes)
    
    if x_pos != -1 and y_pos != -1:
        # Si ambas coordenadas están cerca en el código
        if abs(x_pos - y_pos) < 20:
            coord_checks.append((x, y, min(x_pos, y_pos)))

if coord_checks:
    print(f"   Verificaciones de coordenadas encontradas: {len(coord_checks)}")
    for x, y, pos in coord_checks[:5]:
        print(f"     ({x},{y}) verificado en {hex(pos)}")

# Última hipótesis: La flag está codificada de otra forma
print("\n8. Codificaciones alternativas:")

# ROT13
import codecs
for seq in [read_sequence]:
    if len(seq) == 19:
        # Intentar como texto y aplicar ROT13
        try:
            # Convertir hex a ASCII primero
            ascii_attempt = ""
            for c in seq:
                if c in "0123456789":
                    ascii_attempt += chr(ord('a') + int(c))
                else:
                    ascii_attempt += c
            
            rot13 = codecs.encode(ascii_attempt, 'rot_13')
            print(f"   ROT13 de {ascii_attempt}: {rot13}")
        except:
            pass

# XOR con clave simple
print("\n9. XOR con claves simples:")
for key in [0x13, 0x37, 0x42, 0x55, 0xAA]:
    xored = ""
    for x, y, v in by_reading:
        xored += f"{v^key:x}"
    if len(xored) >= 19:
        print(f"   XOR con {key:02x}: {xored[:19]}")

print("\n=== CONCLUSIÓN ===")
print("La flag más probable sigue siendo una de estas:")
print(f"1. HTB{{{read_sequence}}} + padding")
print(f"2. HTB{{aa8cb9cefb9778deaa8cb9cefb9778de}}")
print(f"3. HTB{{1a0a8cb9cefb9778d2e + ???}}")

# Verificar una última cosa: ¿hay algo después de "skybox"?
skybox_pos = data.find(b'skybox')
if skybox_pos != -1:
    after_skybox = data[skybox_pos+6:skybox_pos+50]
    print(f"\nDespués de 'skybox': {after_skybox[:20].hex()}")
    
    # Buscar valores no-cero
    non_zero = []
    for b in after_skybox:
        if b != 0 and b < 16:
            non_zero.append(f"{b:x}")
    if non_zero:
        print(f"Valores no-cero: {''.join(non_zero)}")