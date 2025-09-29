#!/usr/bin/env python3

import struct

with open('assets.dmp', 'rb') as f:
    data = f.read()

# El archivo parece empezar con un header
header_size = struct.unpack('<I', data[0:4])[0]
print(f"Header type: {header_size}")

# Leer dimensiones del mapa
pos = 0x14
width = struct.unpack('<I', data[pos:pos+4])[0]
height = struct.unpack('<I', data[pos+4:pos+8])[0]
print(f"Map dimensions: {width}x{height}")

# El mapa parece empezar en offset 0x20
map_start = 0x20
map_data = []

print("\nAnalyzing map data...")
# Leer el mapa (parece ser de 43x21 basado en los valores)
for y in range(height):
    row = []
    for x in range(width):
        offset = map_start + (y * width + x) * 3
        if offset + 3 <= len(data):
            # Cada celda parece tener 3 bytes
            cell = data[offset:offset+3]
            row.append(cell)
    map_data.append(row)

# Imprimir el mapa como ASCII art
print("\nMap visualization:")
for y in range(height):
    line = ""
    for x in range(width):
        cell = map_data[y][x]
        # 01 00 01 parece ser pared, 00 00 02 parece ser espacio vacío
        if cell == b'\x01\x00\x01':
            line += "#"
        elif cell == b'\x00\x00\x02':
            line += " "
        elif cell == b'\x01\x00\x00':
            line += "."
        else:
            # Otros valores podrían ser especiales
            line += "?"
    print(line)

# Buscar patrones inusuales en el mapa
print("\nLooking for special cells...")
for y in range(height):
    for x in range(width):
        cell = map_data[y][x]
        if cell not in [b'\x01\x00\x01', b'\x00\x00\x02', b'\x01\x00\x00']:
            print(f"Special cell at ({x},{y}): {cell.hex()}")