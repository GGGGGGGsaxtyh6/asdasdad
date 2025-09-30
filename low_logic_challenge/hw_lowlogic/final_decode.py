#!/usr/bin/env python3
import csv

# Leer los datos de entrada
with open('input.csv', 'r') as f:
    reader = csv.DictReader(f)
    inputs = []
    for row in reader:
        inputs.append([int(row['in0']), int(row['in1']), int(row['in2']), int(row['in3'])])

print(f"Total inputs: {len(inputs)}")
print(f"Total bytes posibles: {len(inputs) // 8}")

# Dado el nombre "Low Logic", la función más probable es:
# Output = 1 si el número de 1s es "bajo" (< 2)

results = [1 if sum(inp) < 2 else 0 for inp in inputs]

print(f"\nResultados (primeros 40 bits): {''.join(map(str, results[:40]))}")

# Decodificar como bytes (MSB first)
output_bytes = []
for i in range(0, len(results), 8):
    if i + 8 <= len(results):
        byte_str = ''.join([str(results[i+j]) for j in range(8)])
        output_bytes.append(int(byte_str, 2))

print(f"\nBytes (primeros 20): {output_bytes[:20]}")
print(f"\nIntentando decodificar como texto:")

# Mostrar como texto con todos los caracteres
full_text = []
for b in output_bytes:
    if 32 <= b <= 126:
        full_text.append(chr(b))
    else:
        full_text.append(f'\\x{b:02x}')

print(''.join(full_text))

# Guardar en archivo binario
with open('output.bin', 'wb') as f:
    f.write(bytes(output_bytes))

print(f"\n✓ Output guardado en output.bin ({len(output_bytes)} bytes)")

# Verificar si es un formato conocido
print(f"\nPrimeros 4 bytes (hex): {' '.join(f'{b:02x}' for b in output_bytes[:4])}")

# Intentar como LSB first también
output_bytes_lsb = []
for i in range(0, len(results), 8):
    if i + 8 <= len(results):
        byte_str = ''.join([str(results[i+j]) for j in range(7, -1, -1)])
        output_bytes_lsb.append(int(byte_str, 2))

full_text_lsb = []
for b in output_bytes_lsb:
    if 32 <= b <= 126:
        full_text_lsb.append(chr(b))
    else:
        full_text_lsb.append(f'\\x{b:02x}')

print(f"\n--- LSB First ---")
print(''.join(full_text_lsb))

with open('output_lsb.bin', 'wb') as f:
    f.write(bytes(output_bytes_lsb))

print(f"\n✓ Output LSB guardado en output_lsb.bin ({len(output_bytes_lsb)} bytes)")

# Buscar patrones que podrían ser la flag
print("\n" + "="*70)
print("Buscando patrones de flag...")
print("="*70)

# Probar diferentes encodings
for encoding in ['utf-8', 'latin-1', 'ascii']:
    try:
        text = bytes(output_bytes).decode(encoding, errors='ignore')
        if 'HTB' in text or 'FLAG' in text or 'flag' in text or any(c.isupper() and c.isalpha() for c in text[:50]):
            print(f"\n{encoding}: {text}")
    except:
        pass

# Tal vez el output mismo ES la flag en formato hex
flag_hex = ''.join(f'{b:02x}' for b in output_bytes)
print(f"\nOutput completo en hex: {flag_hex[:100]}...")

# O en binario
flag_bin = ''.join(map(str, results))
print(f"\nOutput completo en binario: {flag_bin[:100]}...")

# Probar interpretarlo como HTB{...}
# El formato típico: HTB{...}
# H = 72 = 0100 1000
# T = 84 = 0101 0100  
# B = 66 = 0100 0010

print("\n" + "="*70)
print("Buscando 'HTB{' en diferentes posiciones...")
print("="*70)

target = [72, 84, 66, 123]  # HTB{
for i in range(len(output_bytes) - 3):
    if output_bytes[i:i+4] == target:
        print(f"¡Encontrado HTB{{ en posición {i}!")
        # Buscar el cierre }
        for j in range(i+4, min(i+100, len(output_bytes))):
            if output_bytes[j] == 125:  # }
                flag = bytes(output_bytes[i:j+1]).decode('ascii', errors='ignore')
                print(f"FLAG: {flag}")
                break

# Lo mismo para LSB
for i in range(len(output_bytes_lsb) - 3):
    if output_bytes_lsb[i:i+4] == target:
        print(f"¡Encontrado HTB{{ en posición {i} (LSB)!")
        for j in range(i+4, min(i+100, len(output_bytes_lsb))):
            if output_bytes_lsb[j] == 125:
                flag = bytes(output_bytes_lsb[i:j+1]).decode('ascii', errors='ignore')
                print(f"FLAG (LSB): {flag}")
                break
