#!/usr/bin/env python3
import csv

# Leer los datos de entrada
with open('input.csv', 'r') as f:
    reader = csv.DictReader(f)
    inputs = []
    for row in reader:
        inputs.append([int(row['in0']), int(row['in1']), int(row['in2']), int(row['in3'])])

def test_logic(inputs, func, name):
    print(f"\n{'='*60}")
    print(f"Probando: {name}")
    print(f"{'='*60}")
    
    results = [func(inp) for inp in inputs]
    
    # Mostrar primeros resultados
    print("\nPrimeros 10 resultados:")
    for i in range(min(10, len(inputs))):
        print(f"{i}: {inputs[i]} -> {results[i]}")
    
    # Intentar decodificar como ASCII
    ascii_chars = []
    for i in range(0, len(results), 8):
        if i + 8 <= len(results):
            byte = results[i:i+8]
            byte_str = ''.join([str(b) for b in byte])
            value = int(byte_str, 2)
            if 32 <= value <= 126:
                ascii_chars.append(chr(value))
            else:
                ascii_chars.append(f'[{value}]')
    
    result_text = ''.join(ascii_chars)
    print(f"\nResultado ASCII: {result_text}")
    
    # Si parece contener texto legible, mostrarlo en detalle
    if any(c.isalpha() for c in result_text):
        print(f"\n¡POSIBLE FLAG ENCONTRADA!")
        print(f"Texto completo: {result_text}")
        return True
    return False

# Probar diferentes funciones lógicas

# XOR de todas las entradas
test_logic(inputs, lambda inp: inp[0] ^ inp[1] ^ inp[2] ^ inp[3], "XOR (in0 XOR in1 XOR in2 XOR in3)")

# XNOR (negación de XOR)
test_logic(inputs, lambda inp: 1 - (inp[0] ^ inp[1] ^ inp[2] ^ inp[3]), "XNOR (NOT(XOR))")

# Paridad par (salida 1 si número par de 1s)
test_logic(inputs, lambda inp: 1 if sum(inp) % 2 == 0 else 0, "Paridad PAR")

# Paridad impar
test_logic(inputs, lambda inp: 1 if sum(inp) % 2 == 1 else 0, "Paridad IMPAR")

# AND de todas las entradas
test_logic(inputs, lambda inp: inp[0] & inp[1] & inp[2] & inp[3], "AND (in0 AND in1 AND in2 AND in3)")

# NAND
test_logic(inputs, lambda inp: 1 - (inp[0] & inp[1] & inp[2] & inp[3]), "NAND")

# OR de todas las entradas
test_logic(inputs, lambda inp: inp[0] | inp[1] | inp[2] | inp[3], "OR (in0 OR in1 OR in2 OR in3)")

# NOR
test_logic(inputs, lambda inp: 1 - (inp[0] | inp[1] | inp[2] | inp[3]), "NOR")

# Mayoría (más de 2 bits en 1)
test_logic(inputs, lambda inp: 1 if sum(inp) > 2 else 0, "Mayoría (> 2 bits)")

# Mayoría (>= 2 bits)
test_logic(inputs, lambda inp: 1 if sum(inp) >= 2 else 0, "Mayoría (>= 2 bits)")

# Minoría (< 2 bits)
test_logic(inputs, lambda inp: 1 if sum(inp) < 2 else 0, "Minoría (< 2 bits)")

# Minoría (<= 2 bits)
test_logic(inputs, lambda inp: 1 if sum(inp) <= 2 else 0, "Minoría (<= 2 bits)")

# Solo un bit en 1
test_logic(inputs, lambda inp: 1 if sum(inp) == 1 else 0, "Exactamente 1 bit")

# Exactamente 2 bits
test_logic(inputs, lambda inp: 1 if sum(inp) == 2 else 0, "Exactamente 2 bits")

# Exactamente 3 bits
test_logic(inputs, lambda inp: 1 if sum(inp) == 3 else 0, "Exactamente 3 bits")
