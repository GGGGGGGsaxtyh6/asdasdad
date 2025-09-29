#!/usr/bin/env python3

import struct

with open('assets.dmp', 'rb') as f:
    data = f.read()

# Dimensiones del mapa
width = 43
height = 21
map_start = 0x20
bytes_per_cell = 3

# Recolectar todas las celdas especiales en orden
special_cells = []

for y in range(height):
    for x in range(width):
        offset = map_start + (y * width + x) * bytes_per_cell
        if offset + bytes_per_cell <= len(data):
            cell = data[offset:offset+bytes_per_cell]
            # Las celdas especiales parecen tener valores diferentes en el byte del medio
            if cell not in [b'\x01\x00\x01', b'\x00\x00\x02', b'\x01\x00\x00']:
                # El byte del medio parece contener el valor importante
                special_cells.append({
                    'x': x,
                    'y': y,
                    'value': cell[1],
                    'full': cell.hex()
                })

print("Special cells found:")
print("=" * 50)

# Agrupar por línea para ver patrones
lines = {}
for cell in special_cells:
    y = cell['y']
    if y not in lines:
        lines[y] = []
    lines[y].append(cell)

# Ordenar por línea y luego por posición x
for y in sorted(lines.keys()):
    print(f"\nLine {y}:")
    line_cells = sorted(lines[y], key=lambda x: x['x'])
    chars = []
    for cell in line_cells:
        val = cell['value']
        print(f"  Position ({cell['x']:2},{cell['y']:2}): value={val:02x} ({val}) full={cell['full']}")
        # Intentar convertir a ASCII si está en rango
        if 32 <= val <= 126:
            chars.append(chr(val))
        else:
            chars.append(f"[{val:02x}]")
    if chars:
        print(f"  Characters: {''.join(chars)}")

# Intentar extraer la flag de diferentes formas
print("\n" + "=" * 50)
print("Attempting to decode flag:")
print("=" * 50)

# Método 1: Todos los valores del byte medio en orden
method1 = []
for cell in special_cells:
    val = cell['value']
    if val != 0:
        method1.append(val)

print(f"\nMethod 1 - Middle byte values: {method1}")
try:
    flag1 = ''.join(chr(v) for v in method1 if 32 <= v <= 126)
    print(f"As ASCII: {flag1}")
except:
    pass

# Método 2: Valores únicos del byte medio
unique_vals = sorted(set(cell['value'] for cell in special_cells if cell['value'] != 0))
print(f"\nMethod 2 - Unique values: {unique_vals}")
print(f"As hex: {' '.join(f'{v:02x}' for v in unique_vals)}")

# Método 3: Buscar patrones HTB
print("\nMethod 3 - Looking for HTB pattern...")
# Los valores podrían representar índices o offsets
for cell in special_cells:
    if cell['value'] in range(0x06, 0x13):  # Rango de valores especiales
        print(f"Special value {cell['value']:02x} at ({cell['x']},{cell['y']})")

# Método 4: Interpretar como índices de caracteres
print("\nMethod 4 - Values as character indices:")
# Mapear valores a caracteres posibles
char_map = "0123456789abcdef"  # Hexadecimal
decoded = []
for cell in special_cells:
    val = cell['value']
    if 0 < val <= 16:
        decoded.append(char_map[val-1])

if decoded:
    print(f"Decoded: {''.join(decoded)}")
    # Agrupar de a 2 para formar bytes hex
    hex_pairs = [''.join(decoded[i:i+2]) for i in range(0, len(decoded), 2)]
    print(f"As hex pairs: {' '.join(hex_pairs)}")
    
    # Intentar decodificar como hex
    try:
        hex_string = ''.join(hex_pairs)
        bytes_data = bytes.fromhex(hex_string)
        print(f"As bytes: {bytes_data}")
        try:
            print(f"As ASCII: {bytes_data.decode('ascii')}")
        except:
            pass
    except:
        pass