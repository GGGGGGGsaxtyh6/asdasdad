#!/usr/bin/env python3

import struct

with open('assets.dmp', 'rb') as f:
    data = f.read()

# Analizar el header
print("Header analysis:")
for i in range(0, 32, 4):
    val = struct.unpack('<I', data[i:i+4])[0]
    print(f"Offset {hex(i)}: {val} ({hex(val)})")

# Los valores en 0x18 y 0x1C parecen ser las dimensiones
width = struct.unpack('<I', data[0x18:0x1C])[0]  # 43
height = struct.unpack('<I', data[0x1C:0x20])[0]  # 21

print(f"\nMap dimensions: {width}x{height}")

# El mapa parece empezar en offset 0x20
map_start = 0x20
map_data = []

# Cada celda del mapa parece tener 3 bytes
bytes_per_cell = 3
map_size = width * height * bytes_per_cell

print(f"Expected map size: {map_size} bytes")
print(f"Map data from {hex(map_start)} to {hex(map_start + map_size)}")

# Leer el mapa completo
for y in range(height):
    row = []
    for x in range(width):
        offset = map_start + (y * width + x) * bytes_per_cell
        if offset + bytes_per_cell <= len(data):
            cell = data[offset:offset+bytes_per_cell]
            row.append(cell)
    map_data.append(row)

# Visualizar el mapa
print("\n=== MAP VISUALIZATION ===")
for y in range(height):
    line = ""
    for x in range(width):
        if y < len(map_data) and x < len(map_data[y]):
            cell = map_data[y][x]
            # Interpretar los diferentes tipos de celdas
            if cell[0] == 0x01 and cell[2] == 0x01:
                line += "#"  # Pared
            elif cell[0] == 0x00 and cell[2] == 0x02:
                line += " "  # Espacio vacío
            elif cell[0] == 0x01 and cell[2] == 0x00:
                line += "."  # Otro tipo
            elif cell[0] == 0x02:
                line += "+"  # Posible elemento especial
            else:
                line += "?"
    print(line)

# Buscar patrones especiales
print("\n=== SPECIAL CELLS ===")
special_count = 0
for y in range(height):
    for x in range(width):
        if y < len(map_data) and x < len(map_data[y]):
            cell = map_data[y][x]
            # Buscar celdas que no sean paredes o espacios normales
            if cell not in [b'\x01\x00\x01', b'\x00\x00\x02']:
                print(f"Position ({x:2},{y:2}): {cell.hex()}")
                special_count += 1

print(f"\nTotal special cells: {special_count}")