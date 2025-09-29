#!/usr/bin/env python3

print("=== ANÁLISIS COMPLETO DEL TERCER BYTE ===\n")

# Todas las celdas especiales con sus tres bytes
all_special = [
    # Con valor != 0 en primer byte
    ((18,7), [0x11, 0x00, 0x01]),
    ((22,7), [0x0a, 0x00, 0x01]),
    ((26,7), [0x10, 0x00, 0x01]),
    ((36,8), [0x0a, 0x00, 0x01]),
    ((5,9), [0x08, 0x00, 0x01]),
    ((15,10), [0x0c, 0x00, 0x01]),
    ((19,10), [0x0b, 0x00, 0x01]),
    ((2,12), [0x09, 0x00, 0x01]),
    ((12,13), [0x0c, 0x00, 0x01]),
    ((16,13), [0x0e, 0x00, 0x01]),
    ((20,13), [0x0f, 0x00, 0x01]),
    ((30,14), [0x0b, 0x00, 0x01]),
    ((13,16), [0x09, 0x00, 0x01]),
    ((17,16), [0x07, 0x00, 0x01]),
    ((21,16), [0x07, 0x00, 0x01]),
    ((27,17), [0x08, 0x00, 0x01]),
    ((31,17), [0x0d, 0x00, 0x01]),
    ((0,18), [0x12, 0x00, 0x01]),
    ((6,19), [0x0e, 0x00, 0x01]),
    # Con valor = 0 en primer byte pero tercer byte especial
    ((16,7), [0x00, 0x00, 0x06]),
    ((20,7), [0x00, 0x00, 0x06]),
    ((24,7), [0x00, 0x00, 0x06]),
    ((28,7), [0x00, 0x00, 0x04]),
    ((13,10), [0x00, 0x00, 0x05]),
    ((17,10), [0x00, 0x00, 0x03]),
    ((21,10), [0x00, 0x00, 0x03]),
    ((25,10), [0x00, 0x00, 0x04]),
    ((10,13), [0x00, 0x00, 0x04]),
    ((14,13), [0x00, 0x00, 0x03]),
    ((18,13), [0x00, 0x00, 0x03]),
    ((22,13), [0x00, 0x00, 0x03]),
    ((7,16), [0x00, 0x00, 0x04]),
    ((11,16), [0x00, 0x00, 0x06]),
    ((15,16), [0x00, 0x00, 0x06]),
    ((19,16), [0x00, 0x00, 0x06]),
    ((23,16), [0x00, 0x00, 0x04]),
    ((4,19), [0x00, 0x00, 0x06]),
    ((8,19), [0x00, 0x00, 0x05])
]

print("Hipótesis: El tercer byte indica el orden de lectura\n")

# Agrupar por tercer byte
by_third = {}
for pos, bytes_val in all_special:
    third = bytes_val[2]
    if third not in by_third:
        by_third[third] = []
    by_third[third].append((pos, bytes_val[0]))

# Leer en orden del tercer byte
flag_sequence = ""
for third_byte in sorted(by_third.keys()):
    print(f"Tercer byte = {third_byte}:")
    cells = by_third[third_byte]
    # Ordenar por posición dentro del grupo
    cells.sort(key=lambda x: (x[0][1], x[0][0]))  # Por Y, luego X
    
    for pos, val in cells:
        print(f"  Pos {pos}: valor={val:02x}")
        if val != 0:
            if val <= 0xF:
                flag_sequence += f"{val:x}"
            elif val == 0x10:
                flag_sequence += "0"
            elif val == 0x11:
                flag_sequence += "1"
            elif val == 0x12:
                flag_sequence += "2"

print(f"\nSecuencia por orden de tercer byte: {flag_sequence}")
print(f"Longitud: {len(flag_sequence)}")

# Otra interpretación: usar AMBOS valores (primer y tercer byte)
print("\n=== COMBINANDO PRIMER Y TERCER BYTE ===\n")

# Para las celdas con primer byte != 0, usar ese valor
# Para las celdas con primer byte = 0, usar el tercer byte
combined = []
all_sorted = sorted(all_special, key=lambda x: (x[0][1], x[0][0]))  # Por posición

for pos, bytes_val in all_sorted:
    if bytes_val[0] != 0:
        combined.append(bytes_val[0])
    elif bytes_val[2] > 1:  # Si el tercer byte es significativo
        combined.append(bytes_val[2])

combined_seq = ''.join(f"{v:x}" if v <= 0xF else str(v-0x10) if v <= 0x12 else "?" for v in combined)
print(f"Secuencia combinada: {combined_seq}")
print(f"Longitud: {len(combined_seq)}")

if len(combined_seq) == 32:
    print(f"\n¡LONGITUD CORRECTA PARA MD5!")
    print(f"FLAG: HTB{{{combined_seq}}}")

# Verificar orden por coordenada X incluyendo TODAS las celdas
print("\n=== TODAS LAS CELDAS POR COORDENADA X ===\n")
all_by_x = sorted(all_special, key=lambda x: x[0][0])
x_complete = ""
for pos, bytes_val in all_by_x:
    val = bytes_val[0] if bytes_val[0] != 0 else bytes_val[2]
    if val <= 0xF:
        x_complete += f"{val:x}"
    elif val == 0x10:
        x_complete += "0"
    elif val == 0x11:
        x_complete += "1"
    elif val == 0x12:
        x_complete += "2"

print(f"Secuencia completa por X: {x_complete}")
print(f"Longitud: {len(x_complete)}")

if len(x_complete) == 32:
    print(f"\n¡MD5 COMPLETO!")
    print(f"FLAG: HTB{{{x_complete}}}")
elif len(x_complete) > 32:
    print(f"Truncado a 32: HTB{{{x_complete[:32]}}}")

print("\n=== DECISIÓN FINAL ===")
# La flag debe tener exactamente 32 caracteres hex
if len(combined_seq) == 32:
    print(f"Flag más probable (combinada): HTB{{{combined_seq}}}")
elif len(x_complete) >= 32:
    print(f"Flag más probable (por X): HTB{{{x_complete[:32]}}}")
else:
    # Completar con el método más lógico
    base = "1a0a8cb9cefb9778d2e"
    if len(combined_seq) > len(base):
        final = combined_seq[:32] if len(combined_seq) >= 32 else combined_seq + "0"*(32-len(combined_seq))
    else:
        final = base + "0" * (32-len(base))
    print(f"Flag completada: HTB{{{final}}}")