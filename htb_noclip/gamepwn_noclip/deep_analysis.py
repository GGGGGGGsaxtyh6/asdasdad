#!/usr/bin/env python3

import struct
import sys

# Analizar más profundamente los valores del mapa
with open('assets.dmp', 'rb') as f:
    data = f.read()

print("=== ANÁLISIS PROFUNDO DEL MAPA ===\n")

# Dimensiones confirmadas
width = 43
height = 21
map_start = 0x20
bytes_per_cell = 3

# Recolectar TODOS los valores especiales con sus posiciones exactas
special_cells = []
for y in range(height):
    for x in range(width):
        offset = map_start + (y * width + x) * bytes_per_cell
        if offset + bytes_per_cell <= len(data):
            cell = data[offset:offset+bytes_per_cell]
            if cell not in [b'\x01\x00\x01', b'\x00\x00\x02', b'\x01\x00\x00']:
                special_cells.append({
                    'x': x, 'y': y,
                    'offset': offset,
                    'raw': cell,
                    'hex': cell.hex(),
                    'bytes': [cell[0], cell[1], cell[2]]
                })

print(f"Total celdas especiales: {len(special_cells)}\n")

# Analizar el tercer byte que no habíamos considerado
print("Análisis del tercer byte (ignorado anteriormente):")
third_bytes = {}
for cell in special_cells:
    tb = cell['bytes'][2]
    if tb not in third_bytes:
        third_bytes[tb] = []
    third_bytes[tb].append(cell)

for tb, cells in sorted(third_bytes.items()):
    print(f"  Tercer byte = {tb:02x}: {len(cells)} celdas")
    
# Los valores con tercer byte diferente podrían ser importantes
print("\nCeldas con tercer byte != 0x01:")
for cell in special_cells:
    if cell['bytes'][2] != 0x01:
        print(f"  Pos({cell['x']:2},{cell['y']:2}): {cell['hex']} - bytes: {cell['bytes']}")

# Intentar diferentes interpretaciones
print("\n=== INTERPRETACIONES ALTERNATIVAS ===\n")

# Interpretación 1: Solo valores del primer byte donde el tercer byte es específico
print("1. Valores donde tercer_byte >= 3:")
filtered = [c for c in special_cells if c['bytes'][2] >= 3]
if filtered:
    values = [c['bytes'][0] for c in filtered]
    hex_str = ''.join(f"{v:02x}" for v in values)
    print(f"   Valores: {values}")
    print(f"   Como hex: {hex_str}")
    if len(hex_str) == 32:
        print(f"   ¡MD5 válido!: {hex_str}")

# Interpretación 2: Ordenar por el tercer byte como índice
print("\n2. Ordenados por tercer byte:")
sorted_cells = sorted(special_cells, key=lambda x: x['bytes'][2])
for cell in sorted_cells[:10]:  # Primeros 10
    print(f"   TB={cell['bytes'][2]:02x}: valor={cell['bytes'][0]:02x} en ({cell['x']:2},{cell['y']:2})")

# Interpretación 3: El tercer byte podría indicar el orden
print("\n3. Usando tercer byte como índice de orden:")
indexed = {}
for cell in special_cells:
    idx = cell['bytes'][2]
    if idx not in indexed:
        indexed[idx] = []
    indexed[idx].append(cell['bytes'][0])

ordered_values = []
for i in sorted(indexed.keys()):
    if i in indexed:
        for v in indexed[i]:
            ordered_values.append(v)
            
if ordered_values:
    hex_ordered = ''.join(f"{v:x}" if v < 16 else f"{v-16:x}" if v <= 18 else "?" for v in ordered_values)
    print(f"   Valores ordenados: {ordered_values[:20]}")
    print(f"   Como hex: {hex_ordered}")

# Buscar en el resto del archivo
print("\n=== BÚSQUEDA EN EL RESTO DEL ARCHIVO ===\n")

# Después del mapa
map_end = map_start + width * height * bytes_per_cell
print(f"El mapa termina en: {hex(map_end)}")
print(f"Bytes después del mapa: {len(data) - map_end}")

# Buscar patrones de 32 bytes que podrían ser MD5
print("\nBuscando secuencias de 32 bytes imprimibles:")
for i in range(map_end, min(map_end + 1000, len(data) - 32)):
    chunk = data[i:i+32]
    # Verificar si todos son caracteres hex válidos
    try:
        text = chunk.decode('ascii')
        if all(c in '0123456789abcdef' for c in text.lower()):
            print(f"  Offset {hex(i)}: {text}")
    except:
        pass

# Analizar la estructura después del mapa
print("\nPrimeros 200 bytes después del mapa:")
post_map = data[map_end:map_end+200]
print(f"  Hex: {post_map[:50].hex()}")

# Buscar strings
import string
printable = set(string.printable.encode('ascii'))
for i in range(map_end, min(map_end + 500, len(data) - 10)):
    if all(b in printable for b in data[i:i+10]):
        try:
            text = data[i:i+50].decode('ascii', errors='ignore').strip()
            if len(text) > 5:
                print(f"  String en {hex(i)}: {text[:40]}")
        except:
            pass