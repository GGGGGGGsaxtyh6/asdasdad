#!/usr/bin/env python3

import struct
import subprocess

print("=== ANÁLISIS DEL BINARIO Y SU LÓGICA ===\n")

# Usar objdump para analizar el binario
print("1. Analizando función main y referencias a datos:")
result = subprocess.run(['objdump', '-d', '-M', 'intel', 'noclip'], 
                       capture_output=True, text=True)

# Buscar referencias a offsets de datos
lines = result.stdout.split('\n')
data_refs = []
for i, line in enumerate(lines):
    if 'mov' in line and ('0x5' in line or '0x4' in line):
        # Posibles referencias a sección de datos
        if i > 0:
            print(f"  {lines[i-1]}")
        print(f"  {line}")
        if i < len(lines)-1:
            print(f"  {lines[i+1]}")
        print()

# Analizar secciones del binario
print("\n2. Secciones del binario:")
result = subprocess.run(['readelf', '-S', 'noclip'], capture_output=True, text=True)
for line in result.stdout.split('\n'):
    if '.data' in line or '.rodata' in line or '.bss' in line:
        print(f"  {line}")

# Extraer la sección .rodata (datos de solo lectura)
print("\n3. Extrayendo sección .rodata:")
result = subprocess.run(['objcopy', '--dump-section', '.rodata=rodata.bin', 'noclip'],
                       capture_output=True, text=True, stderr=subprocess.DEVNULL)

try:
    with open('rodata.bin', 'rb') as f:
        rodata = f.read()
    print(f"  Tamaño de .rodata: {len(rodata)} bytes")
    
    # Buscar strings en rodata
    print("\n  Strings en .rodata:")
    import string
    printable = set(string.printable.encode('ascii'))
    
    i = 0
    while i < len(rodata):
        if rodata[i] in printable:
            j = i
            while j < len(rodata) and rodata[j] in printable:
                j += 1
            if j - i > 4:
                try:
                    text = rodata[i:j].decode('ascii').strip()
                    if text:
                        print(f"    {hex(i)}: {text}")
                except:
                    pass
            i = j
        else:
            i += 1
            
    # Buscar secuencias de 32 bytes hex
    print("\n  Buscando posibles MD5 en .rodata:")
    for i in range(len(rodata) - 32):
        chunk = rodata[i:i+32]
        try:
            text = chunk.decode('ascii')
            if all(c in '0123456789abcdefABCDEF' for c in text):
                print(f"    MD5 candidato en {hex(i)}: {text}")
        except:
            pass
            
except FileNotFoundError:
    print("  No se pudo extraer .rodata")

# Analizar la lógica del juego
print("\n4. Analizando lógica de colisiones:")

with open('noclip', 'rb') as f:
    binary = f.read()

# Buscar la función que verifica si el jugador llegó a algún lugar especial
# Típicamente involucra comparaciones de coordenadas
print("  Buscando comparaciones de coordenadas:")

# Patrones de comparación de coordenadas (x,y)
coord_patterns = [
    b'\x3d',  # CMP EAX, imm32
    b'\x81\xf9',  # CMP ECX, imm32
    b'\x81\xfa',  # CMP EDX, imm32
]

for pattern in coord_patterns:
    pos = 0
    while pos < len(binary) - 10:
        pos = binary.find(pattern, pos)
        if pos == -1:
            break
        # Leer el valor inmediato
        if pattern == b'\x3d':
            value = struct.unpack('<I', binary[pos+1:pos+5])[0]
            if 0 < value < 50:  # Coordenadas del mapa
                print(f"    CMP EAX, {value} en offset {hex(pos)}")
        elif pattern in [b'\x81\xf9', b'\x81\xfa']:
            value = struct.unpack('<I', binary[pos+2:pos+6])[0]
            if 0 < value < 50:
                print(f"    CMP reg, {value} en offset {hex(pos)}")
        pos += 1

# Buscar referencias a funciones de victoria o flag
print("\n5. Buscando funciones de victoria:")
victory_patterns = [b'win', b'flag', b'success', b'complete', b'goal']
for pattern in victory_patterns:
    pos = binary.find(pattern)
    if pos != -1:
        print(f"  Encontrado '{pattern.decode()}' en {hex(pos)}")
        # Ver contexto
        context = binary[max(0, pos-20):min(len(binary), pos+20)]
        print(f"    Contexto: {context.hex()}")

print("\n6. Análisis de la función que carga assets:")
# La función load_assets podría revelar cómo se interpreta el mapa
result = subprocess.run(['gdb', '-batch', '-ex', 'file noclip', 
                        '-ex', 'disas load_assets', '-ex', 'quit'],
                       capture_output=True, text=True, stderr=subprocess.DEVNULL)

# Buscar valores específicos en la función
for line in result.stdout.split('\n'):
    if 'mov' in line and ('0x' in line):
        # Valores inmediatos que podrían ser importantes
        if any(x in line for x in ['0x2b', '0x15', '0x20']):  # 43, 21, 32
            print(f"  {line}")

print("\n7. Verificando si hay una condición de victoria oculta:")
# El juego podría verificar si el jugador llegó a todas las celdas especiales
# o si las visitó en un orden específico

# Buscar contadores o acumuladores
print("  Buscando incrementos de contadores:")
inc_patterns = [b'\xff\xc0',  # INC EAX
                b'\xff\xc1',  # INC ECX
                b'\x83\xc0\x01',  # ADD EAX, 1
                b'\x83\xc1\x01']  # ADD ECX, 1

for pattern in inc_patterns:
    count = binary.count(pattern)
    if count > 0:
        print(f"    Patrón {pattern.hex()}: {count} ocurrencias")