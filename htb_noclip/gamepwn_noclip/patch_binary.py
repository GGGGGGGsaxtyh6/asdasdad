#!/usr/bin/env python3

import sys

# Leer el binario
with open('noclip', 'rb') as f:
    binary = bytearray(f.read())

print("Analyzing binary for collision checks...")

# Buscar instrucciones de salto condicional (JE, JNE, etc.)
# que podrían estar relacionadas con detección de colisiones

# Patrones comunes para detección de colisiones:
# - Comparaciones seguidas de saltos condicionales
# - Llamadas a funciones de colisión

# La función raycast en 0x2060 maneja las colisiones
# Buscar referencias a esta función

raycast_addr = 0x2060

# Buscar CALL instructions to raycast
call_opcode = b'\xe8'  # CALL rel32

patches = []

# Buscar en el código
for i in range(len(binary) - 5):
    if binary[i] == 0xe8:  # CALL instruction
        # Leer el offset relativo
        offset = int.from_bytes(binary[i+1:i+5], 'little', signed=True)
        # Calcular la dirección de destino
        target = i + 5 + offset
        
        # Ver si apunta a raycast o cerca
        if abs(target - raycast_addr) < 0x100:
            print(f"Found potential raycast call at {hex(i)}")
            patches.append(i)

# También buscar comparaciones con el mapa (tiles)
# Los tiles de pared son 0x01 0x00 0x01

# Buscar CMP instructions
cmp_patterns = [
    b'\x83\xf8\x01',  # CMP EAX, 1
    b'\x83\xf9\x01',  # CMP ECX, 1
    b'\x83\xfa\x01',  # CMP EDX, 1
    b'\x3c\x01',      # CMP AL, 1
    b'\x80\xf9\x01',  # CMP CL, 1
]

print("\nSearching for collision comparisons...")
for pattern in cmp_patterns:
    pos = 0
    while True:
        pos = binary.find(pattern, pos)
        if pos == -1:
            break
        print(f"Found comparison at {hex(pos)}: {binary[pos:pos+10].hex()}")
        
        # Buscar el salto condicional siguiente
        for j in range(pos, min(pos + 20, len(binary) - 2)):
            # JE (74), JNE (75), JZ (74), JNZ (75)
            if binary[j] in [0x74, 0x75]:
                print(f"  -> Conditional jump at {hex(j)}: {binary[j:j+2].hex()}")
                # Podríamos parchear esto a NOP o cambiar la condición
                
        pos += 1

# Buscar referencias a "noclip" o modo debug
debug_strings = [b'debug', b'noclip', b'god', b'cheat']
for s in debug_strings:
    pos = binary.find(s)
    if pos != -1:
        print(f"\nFound '{s.decode()}' at {hex(pos)}")

print("\n" + "="*60)
print("Creating patched binary...")

# Crear una copia parcheada que siempre permita atravesar paredes
patched = binary.copy()

# Opción 1: Parchear los saltos condicionales después de comparaciones de colisión
# Cambiar JE/JNE a JMP (salto incondicional) o NOP

# Buscar la función que verifica colisiones y parcheada
# En muchos juegos, hay una verificación como:
# if (tile == WALL) { stop_movement(); }
# Podemos cambiar esto a:
# if (tile == WALL) { /* do nothing */ }

# Intentar un patch simple: cambiar todos los JE después de CMP con 1
patch_count = 0
for pattern in cmp_patterns:
    pos = 0
    while True:
        pos = patched.find(pattern, pos)
        if pos == -1:
            break
        
        # Buscar el JE/JNE siguiente
        for j in range(pos, min(pos + 10, len(patched) - 2)):
            if patched[j] == 0x74:  # JE
                print(f"Patching JE at {hex(j)} to JMP")
                patched[j] = 0xEB  # Cambiar a JMP (salto incondicional)
                patch_count += 1
                break
            elif patched[j] == 0x75:  # JNE
                print(f"Patching JNE at {hex(j)} to NOP")
                patched[j] = 0x90  # NOP
                patched[j+1] = 0x90  # NOP
                patch_count += 1
                break
        
        pos += 1
        if patch_count > 5:  # No parchear demasiado
            break

print(f"\nApplied {patch_count} patches")

# Guardar el binario parcheado
with open('noclip_patched', 'wb') as f:
    f.write(patched)

print("Patched binary saved as 'noclip_patched'")
print("Make it executable with: chmod +x noclip_patched")