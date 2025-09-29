#!/usr/bin/env python3

import struct

with open('assets.dmp', 'rb') as f:
    data = f.read()

# Dimensiones del mapa
width = 43
height = 21
map_start = 0x20
bytes_per_cell = 3

# Recolectar todas las celdas especiales
special_cells = []

for y in range(height):
    for x in range(width):
        offset = map_start + (y * width + x) * bytes_per_cell
        if offset + bytes_per_cell <= len(data):
            cell = data[offset:offset+bytes_per_cell]
            # Las celdas especiales tienen valores diferentes
            if cell not in [b'\x01\x00\x01', b'\x00\x00\x02', b'\x01\x00\x00']:
                special_cells.append({
                    'x': x,
                    'y': y,
                    'byte0': cell[0],
                    'byte1': cell[1],
                    'byte2': cell[2],
                    'full': cell.hex()
                })

print("Analyzing special cells:")
print("=" * 60)

# Extraer valores únicos del primer byte (donde están los valores especiales)
values = []
for cell in special_cells:
    # El primer byte parece contener los valores importantes
    if cell['byte0'] not in [0x00, 0x01]:
        values.append(cell['byte0'])
        print(f"Position ({cell['x']:2},{cell['y']:2}): byte0={cell['byte0']:02x} full={cell['full']}")

print(f"\nExtracted values: {[hex(v) for v in values]}")

# Intentar decodificar como ASCII
print("\nDecoding attempts:")
print("-" * 40)

# Método 1: Directo como ASCII
ascii_chars = []
for v in values:
    if 32 <= v <= 126:
        ascii_chars.append(chr(v))
    else:
        ascii_chars.append(f"[{v:02x}]")
print(f"Direct ASCII: {''.join(ascii_chars)}")

# Método 2: Como hex string (HTB flags suelen ser hex)
hex_string = ''.join(f"{v:02x}" for v in values)
print(f"As hex string: {hex_string}")

# Intentar decodificar el hex string
try:
    decoded = bytes.fromhex(hex_string).decode('ascii', errors='ignore')
    print(f"Hex decoded to ASCII: {decoded}")
except:
    pass

# Método 3: Los valores podrían ser índices o offsets
# Los valores van de 0x06 a 0x12, podrían ser índices
print("\nValues as indices (0-based):")
indexed = []
for v in values:
    # Restar un offset base si es necesario
    indexed.append(v)
    
# Convertir a caracteres hex (0-9, a-f)
hex_chars = "0123456789abcdef"
decoded_hex = []
for v in values:
    if v < 16:
        decoded_hex.append(hex_chars[v])
        
if decoded_hex:
    result = ''.join(decoded_hex)
    print(f"As hex characters: {result}")
    
    # Agrupar de a 2 para formar bytes
    if len(result) % 2 == 0:
        byte_pairs = [result[i:i+2] for i in range(0, len(result), 2)]
        print(f"Grouped as bytes: {' '.join(byte_pairs)}")
        
        # Decodificar los bytes
        try:
            final = bytes.fromhex(''.join(byte_pairs))
            print(f"Final decoded: {final}")
            if all(32 <= b <= 126 for b in final):
                print(f"As ASCII: {final.decode('ascii')}")
        except:
            pass

# Buscar patrones HTB
print("\n" + "=" * 60)
print("Looking for HTB flag pattern...")

# El flag podría estar en otro lugar del archivo
# Buscar "HTB{" en el archivo completo
htb_pos = data.find(b'HTB{')
if htb_pos != -1:
    flag_end = data.find(b'}', htb_pos)
    if flag_end != -1:
        flag = data[htb_pos:flag_end+1]
        print(f"Found flag in file: {flag.decode('ascii')}")
else:
    print("No direct HTB flag found in file")
    
# Los valores especiales del mapa podrían formar el contenido del flag
# Valores encontrados: 11, 0a, 10, 0a, 08, 0c, 0b, 09, 0c, 0e, 0f, 0b, 09, 07, 07, 08, 0d, 12, 0e
print(f"\nSpecial map values in order: {' '.join(f'{v:02x}' for v in values)}")

# Intentar como caracteres desplazados
shifted = []
for v in values:
    # Si restamos 7 a cada valor...
    if v >= 7:
        shifted.append(chr(v - 7 + ord('0')))
        
if shifted:
    print(f"Shifted by -7 + '0': {''.join(shifted)}")