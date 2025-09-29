#!/usr/bin/env python3

import struct

print("=== ANÁLISIS FINAL - RECONSTRUCCIÓN DE FLAG ===\n")

# Cargar el archivo de assets
with open('assets.dmp', 'rb') as f:
    data = f.read()

# Recolectar TODOS los valores especiales con el orden correcto
width = 43
height = 21
map_start = 0x20

# Obtener todas las celdas especiales
special_cells = []
for y in range(height):
    for x in range(width):
        offset = map_start + (y * width + x) * 3
        if offset + 3 <= len(data):
            cell = data[offset:offset+3]
            if cell not in [b'\x01\x00\x01', b'\x00\x00\x02', b'\x01\x00\x00']:
                special_cells.append({
                    'pos': (x, y),
                    'offset': offset,
                    'bytes': cell,
                    'val': cell[0]
                })

print(f"Total de celdas especiales: {len(special_cells)}")

# Ordenar por posición en el mapa (de izquierda a derecha, arriba a abajo)
special_cells.sort(key=lambda c: (c['pos'][1], c['pos'][0]))

print("\nValores en orden de aparición en el mapa:")
values = []
for cell in special_cells:
    val = cell['val']
    if val != 0:  # Ignorar ceros
        values.append(val)
        print(f"  Pos {cell['pos']}: valor={val:02x}")

# Convertir a string hexadecimal
hex_string = ""
for v in values:
    if v <= 0xF:
        hex_string += f"{v:x}"
    elif v == 0x10:
        hex_string += "0"
    elif v == 0x11:
        hex_string += "1"  
    elif v == 0x12:
        hex_string += "2"
    else:
        hex_string += "?"

print(f"\nString hexadecimal: {hex_string}")
print(f"Longitud: {len(hex_string)} caracteres")

# Para un MD5 necesitamos 32 caracteres
if len(hex_string) < 32:
    print(f"\nFaltan {32 - len(hex_string)} caracteres para completar MD5")
    
    # Buscar más datos en el archivo
    print("\nBuscando datos adicionales después del mapa...")
    
    # El mapa termina en 0xab5
    map_end = 0xab5
    
    # Leer los siguientes bytes
    next_data = data[map_end:map_end+100]
    print(f"Siguientes bytes: {next_data[:50].hex()}")
    
    # Buscar valores que podrían completar el hash
    # Los valores 03 00 00 00 al inicio podrían ser relevantes
    additional = []
    i = 0
    while i < len(next_data) and len(hex_string) + len(additional) < 32:
        byte = next_data[i]
        if 0 < byte <= 0x12:
            if byte <= 0xF:
                additional.append(f"{byte:x}")
            elif byte == 0x10:
                additional.append("0")
            elif byte == 0x11:
                additional.append("1")
            elif byte == 0x12:
                additional.append("2")
        i += 1
    
    if additional:
        print(f"Valores adicionales encontrados: {''.join(additional)}")
        hex_string += ''.join(additional)

# Intentar diferentes completaciones
print("\n=== FLAGS CANDIDATAS ===\n")

candidates = [
    hex_string,
    hex_string + "0" * (32 - len(hex_string)) if len(hex_string) < 32 else hex_string,
    hex_string + "f" * (32 - len(hex_string)) if len(hex_string) < 32 else hex_string,
]

# También probar sin los valores 0x10, 0x11, 0x12 convertidos
alt_hex = ""
for v in values:
    if v <= 0xF:
        alt_hex += f"{v:x}"
        
# Duplicar si es exactamente la mitad
if len(alt_hex) == 16:
    candidates.append(alt_hex * 2)

for i, candidate in enumerate(candidates, 1):
    if len(candidate) == 32:
        print(f"{i}. HTB{{{candidate}}}")
    else:
        print(f"{i}. {candidate} (longitud: {len(candidate)})")

# La interpretación más probable
print("\n=== SOLUCIÓN MÁS PROBABLE ===\n")

# Basándome en el patrón, parece que:
# 1. Los valores > 0xF se mapean a 0,1,2
# 2. Los valores <= 0xF se mantienen como hex
final_flag = "1a0a8cb9cefb9778d2e"

# Necesitamos completar a 32 caracteres
# Los valores que se repiten sugieren un patrón
# Mirando los valores: hay duplicación en algunos

# Verificar si algún patrón se repite
print(f"Flag parcial: {final_flag}")
print(f"Longitud: {len(final_flag)}")

# El patrón podría ser que faltan más celdas especiales
# o que necesitamos interpretar de otra forma

# Última verificación: buscar en los datos del binario
print("\nBuscando en el binario mismo...")
with open('noclip', 'rb') as f:
    binary = f.read()
    
# Buscar secuencias que completen nuestro patrón
search = bytes.fromhex("1a0a8c")
pos = binary.find(search)
if pos != -1:
    print(f"Patrón encontrado en binario en {hex(pos)}")
    context = binary[pos:pos+50]
    print(f"Contexto: {context.hex()}")

# Flag final más probable
print("\n" + "="*50)
print("FLAG MÁS PROBABLE:")
print("="*50)

# Basado en todos los análisis, la flag es:
final = "1a0a8cb9cefb9778d2e"

# Completar con el patrón más lógico
# Si miramos los valores, algunos se repiten (0a, 0c, etc)
# El patrón completo podría ser simétrico o tener padding

# Verificar todas las celdas con byte[2] != 1
print("\nCeldas con tercer byte especial (podrían ser parte de la flag):")
for cell in special_cells:
    if cell['bytes'][2] not in [0x01, 0x02]:
        print(f"  Pos {cell['pos']}: bytes={cell['bytes'].hex()}, tercer_byte={cell['bytes'][2]:02x}")
        
# El tercer byte podría indicar el orden o ser parte de la flag
ordered_by_third = {}
for cell in special_cells:
    third = cell['bytes'][2]
    if third not in ordered_by_third:
        ordered_by_third[third] = []
    ordered_by_third[third].append(cell['val'])

print("\nValores agrupados por tercer byte:")
for tb in sorted(ordered_by_third.keys()):
    vals = ordered_by_third[tb]
    if vals != [0] * len(vals):  # Ignorar si todos son ceros
        print(f"  Tercer byte {tb:02x}: valores={[f'{v:02x}' for v in vals]}")

# Construir flag usando el tercer byte como índice
flag_by_index = {}
for cell in special_cells:
    if cell['val'] != 0:
        idx = cell['bytes'][2]
        if idx not in flag_by_index:
            flag_by_index[idx] = []
        flag_by_index[idx].append(cell['val'])

# Si el tercer byte es un índice secuencial
if 1 in flag_by_index:
    print("\nConstruyendo flag con tercer byte = 1:")
    flag_vals = flag_by_index[1]
    flag_hex = ""
    for v in flag_vals:
        if v <= 0xF:
            flag_hex += f"{v:x}"
        elif v == 0x10:
            flag_hex += "0"
        elif v == 0x11:
            flag_hex += "1"
        elif v == 0x12:
            flag_hex += "2"
    print(f"  Flag: {flag_hex}")
    if len(flag_hex) == 32:
        print(f"  ¡MD5 COMPLETO!: HTB{{{flag_hex}}}")