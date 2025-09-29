#!/usr/bin/env python3

import struct

print("=== TRAZANDO LA LÓGICA DE EJECUCIÓN ===\n")

# Los valores especiales del mapa y sus posiciones
special_map = {
    (18,7): 0x11, (22,7): 0x0a, (26,7): 0x10, (36,8): 0x0a,
    (5,9): 0x08, (15,10): 0x0c, (19,10): 0x0b, (2,12): 0x09,
    (12,13): 0x0c, (16,13): 0x0e, (20,13): 0x0f, (30,14): 0x0b,
    (13,16): 0x09, (17,16): 0x07, (21,16): 0x07, (27,17): 0x08,
    (31,17): 0x0d, (0,18): 0x12, (6,19): 0x0e
}

print("Posiciones especiales en el mapa:")
# Agrupar por líneas horizontales
lines = {}
for (x,y), val in special_map.items():
    if y not in lines:
        lines[y] = []
    lines[y].append((x, val))

for y in sorted(lines.keys()):
    positions = sorted(lines[y])
    print(f"  Línea Y={y:2}: ", end="")
    for x, val in positions:
        print(f"X={x:2}(val={val:02x}) ", end="")
    print()

# Analizar el patrón de las posiciones
print("\n¿Las posiciones forman algún patrón o palabra?")

# Crear un mini-mapa visual
print("\nMini-mapa de posiciones especiales:")
for y in range(21):
    line = ""
    for x in range(43):
        if (x,y) in special_map:
            val = special_map[(x,y)]
            # Usar el valor como carácter si es imprimible
            if 0x20 <= val <= 0x7e:
                line += chr(val)
            else:
                line += f"{val:x}"
        else:
            line += "."
    print(f"Y{y:02}: {line}")

# Los valores podrían necesitar una transformación
print("\n=== TRANSFORMACIONES DE VALORES ===\n")

values_in_order = [special_map[k] for k in sorted(special_map.keys())]
print(f"Valores en orden de posición: {[hex(v) for v in values_in_order]}")

# Intentar varias transformaciones
print("\n1. Como caracteres ASCII directos:")
ascii_attempt = ""
for v in values_in_order:
    if 0x20 <= v <= 0x7e:
        ascii_attempt += chr(v)
    else:
        ascii_attempt += f"[{v:02x}]"
print(f"   {ascii_attempt}")

print("\n2. Sumando offset para hacerlos ASCII:")
for offset in [0x20, 0x30, 0x40, 0x60]:
    result = ""
    valid = True
    for v in values_in_order:
        new_val = v + offset
        if 0x20 <= new_val <= 0x7e:
            result += chr(new_val)
        else:
            valid = False
            break
    if valid:
        print(f"   Offset +{offset:02x}: {result}")

print("\n3. Como nibbles hexadecimales:")
# Si los valores son índices a una tabla de caracteres hex
hex_table = "0123456789abcdef"
hex_result = ""
for v in values_in_order:
    if v < 16:
        hex_result += hex_table[v]
    elif v == 0x10:
        hex_result += "0"  # 0x10 podría ser 0
    elif v == 0x11:
        hex_result += "1"  # 0x11 podría ser 1
    elif v == 0x12:
        hex_result += "2"  # 0x12 podría ser 2
    else:
        hex_result += "?"
print(f"   {hex_result}")

print("\n4. XOR con diferentes claves:")
for key in [0x40, 0x41, 0x42, 0x50, 0x60, 0x61]:
    result = ""
    valid = True
    for v in values_in_order:
        xored = v ^ key
        if 0x20 <= xored <= 0x7e:
            result += chr(xored)
        else:
            valid = False
            break
    if valid:
        print(f"   XOR con {key:02x}: {result}")

# Verificar si el orden de visita importa
print("\n=== ANÁLISIS DEL ORDEN DE VISITA ===\n")

# Las posiciones podrían necesitar visitarse en un orden específico
# Ordenar por diferentes criterios
print("1. Por distancia desde origen (0,0):")
by_distance = sorted(special_map.items(), key=lambda x: x[0][0]**2 + x[0][1]**2)
dist_values = [v for k,v in by_distance]
print(f"   Valores: {[hex(v) for v in dist_values[:10]]}...")

print("\n2. En espiral desde el centro:")
center_x, center_y = 21, 10
by_spiral = sorted(special_map.items(), 
                  key=lambda x: abs(x[0][0]-center_x) + abs(x[0][1]-center_y))
spiral_values = [v for k,v in by_spiral]
print(f"   Valores: {[hex(v) for v in spiral_values[:10]]}...")

print("\n3. Por valor ascendente:")
by_value = sorted(special_map.items(), key=lambda x: x[1])
ordered_positions = [(k, hex(v)) for k,v in by_value]
print(f"   Primeros 5: {ordered_positions[:5]}")

# El juego podría revelar la flag al visitar todas las posiciones
print("\n=== HIPÓTESIS FINAL ===\n")
print("El juego 'NoClip' probablemente requiere:")
print("1. Activar modo noclip (atravesar paredes)")
print("2. Visitar todas las posiciones especiales del mapa")
print("3. Las posiciones forman un hash MD5 cuando se visitan")
print("4. El orden de visita o la transformación de valores genera la flag")

# La flag más probable basada en el análisis
print("\nFlags candidatas:")
print(f"1. HTB{{{hex_result}}}")
if len(hex_result) == 19:
    # Necesitamos completar a 32 caracteres
    completed = hex_result + "0" * (32 - len(hex_result))
    print(f"2. HTB{{{completed}}}")
    
# Verificar si hay algún patrón en las posiciones
print("\n=== PATRÓN VISUAL DE POSICIONES ===")
# Las posiciones podrían formar letras o números
import numpy as np
grid = np.zeros((21, 43), dtype=int)
for (x,y), val in special_map.items():
    grid[y][x] = 1

# Mostrar solo la región con valores
min_x = min(x for x,y in special_map.keys())
max_x = max(x for x,y in special_map.keys())
min_y = min(y for x,y in special_map.keys())
max_y = max(y for x,y in special_map.keys())

print(f"\nRegión activa: X[{min_x},{max_x}] Y[{min_y},{max_y}]")
for y in range(min_y, max_y+1):
    line = ""
    for x in range(min_x, max_x+1):
        if (x,y) in special_map:
            line += "#"
        else:
            line += " "
    print(f"Y{y:02}: {line}")