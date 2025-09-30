#!/usr/bin/env python3
import csv

# Leer los datos de entrada
with open('input.csv', 'r') as f:
    reader = csv.DictReader(f)
    inputs = []
    for row in reader:
        inputs.append([int(row['in0']), int(row['in1']), int(row['in2']), int(row['in3'])])

print(f"Total de entradas: {len(inputs)}")
print("\nPrimeras 10 entradas:")
for i, inp in enumerate(inputs[:10]):
    print(f"{i}: {inp}")

# Voy a probar diferentes funciones lógicas y ver cuál podría ser
print("\n" + "="*60)
print("Analizando patrones...")
print("="*60)

# Contar bits en cada entrada
print("\nContando bits 1 en cada entrada:")
for i, inp in enumerate(inputs[:20]):
    count = sum(inp)
    print(f"{i}: {inp} -> {count} bits en 1")

# Probar "Low Logic" - cuando el número de bits en 1 es bajo (0, 1 o 2)
print("\n" + "="*60)
print("Probando: Low Logic (menos de 2 bits en 1)")
print("="*60)
results_low = []
for inp in inputs:
    count = sum(inp)
    output = 1 if count < 2 else 0
    results_low.append(output)

print("\nPrimeros 20 resultados (low < 2):")
for i in range(20):
    count = sum(inputs[i])
    print(f"{i}: {inputs[i]} -> sum={count} -> output={results_low[i]}")

# Convertir resultados a caracteres ASCII
output_str = ''.join([str(bit) for bit in results_low])
print(f"\nOutput como string binario (primeros 100): {output_str[:100]}")

# Intentar convertir grupos de 8 bits a ASCII
print("\n" + "="*60)
print("Intentando decodificar como ASCII (8 bits por caracter):")
print("="*60)
ascii_chars = []
for i in range(0, len(results_low), 8):
    if i + 8 <= len(results_low):
        byte = results_low[i:i+8]
        byte_str = ''.join([str(b) for b in byte])
        value = int(byte_str, 2)
        if 32 <= value <= 126:  # Caracteres imprimibles
            ascii_chars.append(chr(value))
        else:
            ascii_chars.append(f'[{value}]')

result_text = ''.join(ascii_chars)
print(f"Resultado: {result_text}")

# Probar otras interpretaciones de "Low Logic"
print("\n" + "="*60)
print("Probando: Low Logic (0 o 1 bit en 1)")
print("="*60)
results_very_low = []
for inp in inputs:
    count = sum(inp)
    output = 1 if count <= 1 else 0
    results_very_low.append(output)

print("\nPrimeros 20 resultados (low <= 1):")
for i in range(20):
    count = sum(inputs[i])
    print(f"{i}: {inputs[i]} -> sum={count} -> output={results_very_low[i]}")

ascii_chars2 = []
for i in range(0, len(results_very_low), 8):
    if i + 8 <= len(results_very_low):
        byte = results_very_low[i:i+8]
        byte_str = ''.join([str(b) for b in byte])
        value = int(byte_str, 2)
        if 32 <= value <= 126:
            ascii_chars2.append(chr(value))
        else:
            ascii_chars2.append(f'[{value}]')

result_text2 = ''.join(ascii_chars2)
print(f"\nResultado: {result_text2}")

# Probar <= 2
print("\n" + "="*60)
print("Probando: Low Logic (<= 2 bits en 1)")
print("="*60)
results_le2 = []
for inp in inputs:
    count = sum(inp)
    output = 1 if count <= 2 else 0
    results_le2.append(output)

ascii_chars3 = []
for i in range(0, len(results_le2), 8):
    if i + 8 <= len(results_le2):
        byte = results_le2[i:i+8]
        byte_str = ''.join([str(b) for b in byte])
        value = int(byte_str, 2)
        if 32 <= value <= 126:
            ascii_chars3.append(chr(value))
        else:
            ascii_chars3.append(f'[{value}]')

result_text3 = ''.join(ascii_chars3)
print(f"\nResultado: {result_text3}")
