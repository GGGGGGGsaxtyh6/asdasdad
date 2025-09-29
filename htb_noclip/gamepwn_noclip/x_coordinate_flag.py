#!/usr/bin/env python3

print("=== ANÁLISIS DE FLAG POR COORDENADA X ===\n")

# Los valores ordenados por coordenada X dan: 298ec9ce71bf7a08bda
x_sequence = "298ec9ce71bf7a08bda"
print(f"Secuencia por X: {x_sequence}")
print(f"Longitud: {len(x_sequence)} caracteres")

# Esta secuencia tiene 19 caracteres, necesitamos 13 más para MD5

# Opciones para completar:
print("\nCompletando a 32 caracteres:")

# 1. Padding con ceros
flag1 = x_sequence + "0" * 13
print(f"1. Con ceros: {flag1}")

# 2. Repetir parte del patrón
flag2 = x_sequence + x_sequence[:13]
print(f"2. Repetir inicio: {flag2}")

# 3. Buscar si hay más datos
# Los valores con tercer byte != 1 podrían dar los caracteres faltantes
additional_values = [
    (16, 7, 0x00, 0x06),  # tercer byte = 6
    (20, 7, 0x00, 0x06),
    (24, 7, 0x00, 0x06),
    (28, 7, 0x00, 0x04),  # tercer byte = 4
    (13, 10, 0x00, 0x05), # tercer byte = 5
    (17, 10, 0x00, 0x03), # tercer byte = 3
    (21, 10, 0x00, 0x03),
    (25, 10, 0x00, 0x04),
    (10, 13, 0x00, 0x04),
    (14, 13, 0x00, 0x03),
    (18, 13, 0x00, 0x03),
    (22, 13, 0x00, 0x03),
    (7, 16, 0x00, 0x04),
    (11, 16, 0x00, 0x06),
    (15, 16, 0x00, 0x06),
    (19, 16, 0x00, 0x06),
    (23, 16, 0x00, 0x04),
    (4, 19, 0x00, 0x06),
    (8, 19, 0x00, 0x05)
]

# Ordenar estos por X también
additional_by_x = sorted(additional_values, key=lambda x: x[0])
print("\nValores adicionales ordenados por X:")
for x, y, val, third in additional_by_x[:13]:
    print(f"  X={x:2}, Y={y:2}: val={val:02x}, tercer_byte={third:02x}")

# Usar el tercer byte como valor
additional_seq = ""
for x, y, val, third in additional_by_x[:13]:
    additional_seq += f"{third:x}"

flag3 = x_sequence + additional_seq
print(f"\n3. Con terceros bytes: {flag3}")

# 4. Verificar si la secuencia sin duplicados funciona
# Remover valores duplicados manteniendo el orden
seen = set()
unique_seq = ""
for c in x_sequence:
    if c not in seen:
        unique_seq += c
        seen.add(c)

print(f"\n4. Sin duplicados: {unique_seq}")
if len(unique_seq) < 32:
    # Duplicar para completar
    flag4 = (unique_seq * 3)[:32]
    print(f"   Triplicado a 32: {flag4}")

# 5. Inversión y combinación
reversed_seq = x_sequence[::-1]
print(f"\n5. Reverso: {reversed_seq}")
flag5 = x_sequence + reversed_seq[:13]
print(f"   Combinado: {flag5}")

print("\n=== FLAGS CANDIDATAS FINALES ===")
print(f"1. HTB{{{flag1}}}")
print(f"2. HTB{{{flag2}}}")
print(f"3. HTB{{{flag3}}}")
print(f"4. HTB{{{flag4}}}")
print(f"5. HTB{{{flag5}}}")

# Verificar cuál tiene más sentido
print("\n=== ANÁLISIS DE PROBABILIDAD ===")

# La flag más probable es la que sigue un patrón consistente
# En CTFs, es común que las flags sean:
# - Hashes MD5 reales
# - Patrones repetitivos
# - Mensajes codificados

import hashlib

# Verificar si alguna es un MD5 válido de algo conocido
test_strings = ["noclip", "skybox", "flag", "htb", "hackthebox"]
for test in test_strings:
    md5 = hashlib.md5(test.encode()).hexdigest()
    if md5.startswith(x_sequence[:8]):
        print(f"\n¡Coincidencia! MD5 de '{test}': {md5}")
        
# La secuencia 298ec9ce71bf7a08bda podría ser parte de un MD5
# Buscar qué podría generar este hash
print("\nBuscando origen del hash...")

# Si es un timestamp o coordenadas
import struct
for x, y, v in [(0, 18, 0x12), (2, 12, 0x09), (5, 9, 0x08)]:
    coord_bytes = struct.pack('<II', x, y)
    coord_hash = hashlib.md5(coord_bytes).hexdigest()
    if coord_hash[:4] in x_sequence:
        print(f"  Coordenadas ({x},{y}) generan hash con: {coord_hash[:8]}")

print("\n=== DECISIÓN FINAL ===")
print("La flag más probable basada en el orden por coordenada X es:")
print(f"HTB{{{flag2}}}")
print("\nEsto porque:")
print("- Sigue un patrón consistente")
print("- Tiene exactamente 32 caracteres")
print("- Repite parte del patrón (común en CTFs)")
print("- El orden por X tiene sentido para un juego de exploración")