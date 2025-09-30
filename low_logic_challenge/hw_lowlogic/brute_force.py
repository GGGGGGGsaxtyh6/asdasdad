#!/usr/bin/env python3
import csv
import itertools

# Leer los datos de entrada
with open('input.csv', 'r') as f:
    reader = csv.DictReader(f)
    inputs = []
    for row in reader:
        inputs.append([int(row['in0']), int(row['in1']), int(row['in2']), int(row['in3'])])

print(f"Total inputs: {len(inputs)}")

def try_decode(results, name, both_orders=True):
    """Intenta decodificar resultados como texto ASCII"""
    
    # MSB first
    bytes_msb = []
    for i in range(0, len(results), 8):
        if i + 8 <= len(results):
            byte_str = ''.join([str(results[i+j]) for j in range(8)])
            bytes_msb.append(int(byte_str, 2))
    
    try:
        text_msb = ''.join([chr(b) if 32 <= b <= 126 else '.' for b in bytes_msb])
        
        # Buscar patrones comunes de flags
        if 'HTB{' in text_msb or 'FLAG' in text_msb or 'flag{' in text_msb:
            print(f"\n{'='*70}")
            print(f"🚩 FLAG ENCONTRADA: {name} (MSB first)")
            print(f"{'='*70}")
            full_text = ''.join([chr(b) if 32 <= b <= 126 else f'[{b}]' for b in bytes_msb])
            print(full_text)
            return full_text
        
        # Si tiene varias letras mayúsculas seguidas, podría ser flag
        upper_count = sum(1 for c in text_msb if c.isupper())
        if upper_count >= 5:
            print(f"\n{name} (MSB) - {upper_count} mayúsculas: {text_msb[:80]}")
    except:
        pass
    
    if not both_orders:
        return None
    
    # LSB first
    bytes_lsb = []
    for i in range(0, len(results), 8):
        if i + 8 <= len(results):
            byte_str = ''.join([str(results[i+j]) for j in range(7, -1, -1)])
            bytes_lsb.append(int(byte_str, 2))
    
    try:
        text_lsb = ''.join([chr(b) if 32 <= b <= 126 else '.' for b in bytes_lsb])
        
        if 'HTB{' in text_lsb or 'FLAG' in text_lsb or 'flag{' in text_lsb:
            print(f"\n{'='*70}")
            print(f"🚩 FLAG ENCONTRADA: {name} (LSB first)")
            print(f"{'='*70}")
            full_text = ''.join([chr(b) if 32 <= b <= 126 else f'[{b}]' for b in bytes_lsb])
            print(full_text)
            return full_text
        
        upper_count = sum(1 for c in text_lsb if c.isupper())
        if upper_count >= 5:
            print(f"\n{name} (LSB) - {upper_count} mayúsculas: {text_lsb[:80]}")
    except:
        pass
    
    return None

print("\nProbando todas las combinaciones posibles de operaciones lógicas...\n")

# Probar sumas
for threshold in range(5):
    results = [1 if sum(inp) == threshold else 0 for inp in inputs]
    try_decode(results, f"SUM == {threshold}")
    
    results = [1 if sum(inp) < threshold else 0 for inp in inputs]
    try_decode(results, f"SUM < {threshold}")
    
    results = [1 if sum(inp) <= threshold else 0 for inp in inputs]
    try_decode(results, f"SUM <= {threshold}")
    
    results = [1 if sum(inp) > threshold else 0 for inp in inputs]
    try_decode(results, f"SUM > {threshold}")
    
    results = [1 if sum(inp) >= threshold else 0 for inp in inputs]
    try_decode(results, f"SUM >= {threshold}")

# XOR y variantes
results = [inp[0] ^ inp[1] ^ inp[2] ^ inp[3] for inp in inputs]
try_decode(results, "XOR ALL")

results = [1 - (inp[0] ^ inp[1] ^ inp[2] ^ inp[3]) for inp in inputs]
try_decode(results, "XNOR ALL")

# Probar XOR de pares
results = [(inp[0] ^ inp[1]) & (inp[2] ^ inp[3]) for inp in inputs]
try_decode(results, "(in0 XOR in1) AND (in2 XOR in3)")

results = [(inp[0] ^ inp[1]) | (inp[2] ^ inp[3]) for inp in inputs]
try_decode(results, "(in0 XOR in1) OR (in2 XOR in3)")

# AND/OR/NAND/NOR
results = [inp[0] & inp[1] & inp[2] & inp[3] for inp in inputs]
try_decode(results, "AND ALL")

results = [1 - (inp[0] & inp[1] & inp[2] & inp[3]) for inp in inputs]
try_decode(results, "NAND ALL")

results = [inp[0] | inp[1] | inp[2] | inp[3] for inp in inputs]
try_decode(results, "OR ALL")

results = [1 - (inp[0] | inp[1] | inp[2] | inp[3]) for inp in inputs]
try_decode(results, "NOR ALL")

# Probar operaciones con cada bit negado
for i in range(4):
    results = []
    for inp in inputs:
        modified = inp.copy()
        modified[i] = 1 - modified[i]
        results.append(1 if sum(modified) < 2 else 0)
    try_decode(results, f"LOW with in{i} negated")

print("\n" + "="*70)
print("Búsqueda completada")
print("="*70)
