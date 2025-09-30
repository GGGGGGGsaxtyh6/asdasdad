#!/usr/bin/env python3
import csv

# Leer los datos de entrada
with open('input.csv', 'r') as f:
    reader = csv.DictReader(f)
    inputs = []
    for row in reader:
        inputs.append([int(row['in0']), int(row['in1']), int(row['in2']), int(row['in3'])])

def test_logic_with_reverse(inputs, func, name):
    print(f"\n{'='*70}")
    print(f"Probando: {name}")
    print(f"{'='*70}")
    
    results = [func(inp) for inp in inputs]
    
    # Intentar con orden normal (MSB primero)
    print("\n--- Orden normal (MSB first) ---")
    ascii_chars = []
    for i in range(0, len(results), 8):
        if i + 8 <= len(results):
            byte = results[i:i+8]
            byte_str = ''.join([str(b) for b in byte])
            value = int(byte_str, 2)
            if 32 <= value <= 126:
                ascii_chars.append(chr(value))
            else:
                ascii_chars.append('.')
    
    result_text = ''.join(ascii_chars)
    print(f"Resultado: {result_text}")
    
    # Intentar con orden invertido (LSB primero)
    print("\n--- Orden invertido (LSB first) ---")
    ascii_chars_rev = []
    for i in range(0, len(results), 8):
        if i + 8 <= len(results):
            byte = results[i:i+8]
            byte.reverse()
            byte_str = ''.join([str(b) for b in byte])
            value = int(byte_str, 2)
            if 32 <= value <= 126:
                ascii_chars_rev.append(chr(value))
            else:
                ascii_chars_rev.append('.')
    
    result_text_rev = ''.join(ascii_chars_rev)
    print(f"Resultado: {result_text_rev}")
    
    # Verificar si hay texto legible
    if any(c.isalpha() and c.isupper() for c in result_text) or "HTB" in result_text or "FLAG" in result_text:
        print(f"\n🚩 ¡FLAG POTENCIAL ENCONTRADA! (orden normal)")
        print(f"Texto: {result_text}")
        return True
    
    if any(c.isalpha() and c.isupper() for c in result_text_rev) or "HTB" in result_text_rev or "FLAG" in result_text_rev:
        print(f"\n🚩 ¡FLAG POTENCIAL ENCONTRADA! (orden invertido)")
        print(f"Texto: {result_text_rev}")
        return True
    
    return False

# Probar las funciones más prometedoras
print("Probando diferentes funciones lógicas con orden de bits normal e invertido...")

test_logic_with_reverse(inputs, lambda inp: 1 if sum(inp) < 2 else 0, "LOW LOGIC (< 2 bits)")
test_logic_with_reverse(inputs, lambda inp: 1 if sum(inp) <= 1 else 0, "LOW LOGIC (<= 1 bit)")
test_logic_with_reverse(inputs, lambda inp: inp[0] ^ inp[1] ^ inp[2] ^ inp[3], "XOR")
test_logic_with_reverse(inputs, lambda inp: 1 - (inp[0] ^ inp[1] ^ inp[2] ^ inp[3]), "XNOR")
test_logic_with_reverse(inputs, lambda inp: 1 if sum(inp) % 2 == 0 else 0, "Paridad PAR")
test_logic_with_reverse(inputs, lambda inp: 1 if sum(inp) > 2 else 0, "Mayoría (> 2)")
test_logic_with_reverse(inputs, lambda inp: 1 if sum(inp) >= 2 else 0, "Mayoría (>= 2)")
test_logic_with_reverse(inputs, lambda inp: 1 if sum(inp) == 1 else 0, "Exactamente 1 bit")
test_logic_with_reverse(inputs, lambda inp: 1 if sum(inp) == 2 else 0, "Exactamente 2 bits")

# También probar inversiones de cada entrada
print("\n" + "="*70)
print("Probando con in0 negado...")
print("="*70)
test_logic_with_reverse(inputs, lambda inp: 1 if (1-inp[0]) + inp[1] + inp[2] + inp[3] < 2 else 0, "LOW LOGIC con in0 negado")
