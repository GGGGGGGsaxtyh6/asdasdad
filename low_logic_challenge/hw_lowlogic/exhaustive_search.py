#!/usr/bin/env python3
import csv

# Leer los datos de entrada
with open('input.csv', 'r') as f:
    reader = csv.DictReader(f)
    inputs = []
    for row in reader:
        inputs.append([int(row['in0']), int(row['in1']), int(row['in2']), int(row['in3'])])

def test_function(func, name):
    """Aplica una función lógica y convierte a hex"""
    results = [func(inp) for inp in inputs]
    
    # MSB first
    output_bytes = []
    for i in range(0, len(results), 8):
        if i + 8 <= len(results):
            byte_str = ''.join([str(results[i+j]) for j in range(8)])
            output_bytes.append(int(byte_str, 2))
    
    flag_hex = ''.join(f'{b:02x}' for b in output_bytes)
    print(f"{name:50s} HTB{{{flag_hex}}}")
    
    # LSB first
    output_bytes_lsb = []
    for i in range(0, len(results), 8):
        if i + 8 <= len(results):
            byte_str = ''.join([str(results[i+j]) for j in range(7, -1, -1)])
            output_bytes_lsb.append(int(byte_str, 2))
    
    flag_hex_lsb = ''.join(f'{b:02x}' for b in output_bytes_lsb)
    if flag_hex != flag_hex_lsb:
        print(f"{name + ' (LSB)':50s} HTB{{{flag_hex_lsb}}}")

print("="*100)
print("TODAS LAS POSIBLES FUNCIONES LÓGICAS")
print("="*100)

# Funciones basadas en suma de bits
test_function(lambda inp: 1 if sum(inp) == 0 else 0, "sum == 0")
test_function(lambda inp: 1 if sum(inp) == 1 else 0, "sum == 1")
test_function(lambda inp: 1 if sum(inp) == 2 else 0, "sum == 2")
test_function(lambda inp: 1 if sum(inp) == 3 else 0, "sum == 3")
test_function(lambda inp: 1 if sum(inp) == 4 else 0, "sum == 4")

test_function(lambda inp: 1 if sum(inp) < 1 else 0, "sum < 1")
test_function(lambda inp: 1 if sum(inp) < 2 else 0, "sum < 2")
test_function(lambda inp: 1 if sum(inp) < 3 else 0, "sum < 3")
test_function(lambda inp: 1 if sum(inp) < 4 else 0, "sum < 4")

test_function(lambda inp: 1 if sum(inp) <= 1 else 0, "sum <= 1")
test_function(lambda inp: 1 if sum(inp) <= 2 else 0, "sum <= 2")
test_function(lambda inp: 1 if sum(inp) <= 3 else 0, "sum <= 3")

test_function(lambda inp: 1 if sum(inp) > 0 else 0, "sum > 0")
test_function(lambda inp: 1 if sum(inp) > 1 else 0, "sum > 1")
test_function(lambda inp: 1 if sum(inp) > 2 else 0, "sum > 2")
test_function(lambda inp: 1 if sum(inp) > 3 else 0, "sum > 3")

test_function(lambda inp: 1 if sum(inp) >= 1 else 0, "sum >= 1")
test_function(lambda inp: 1 if sum(inp) >= 2 else 0, "sum >= 2")
test_function(lambda inp: 1 if sum(inp) >= 3 else 0, "sum >= 3")
test_function(lambda inp: 1 if sum(inp) >= 4 else 0, "sum >= 4")

# Paridad
test_function(lambda inp: sum(inp) % 2, "sum % 2 (odd parity)")
test_function(lambda inp: 1 - (sum(inp) % 2), "1 - (sum % 2) (even parity)")

# XOR
test_function(lambda inp: inp[0] ^ inp[1] ^ inp[2] ^ inp[3], "in0 XOR in1 XOR in2 XOR in3")
test_function(lambda inp: 1 - (inp[0] ^ inp[1] ^ inp[2] ^ inp[3]), "NOT(in0 XOR in1 XOR in2 XOR in3)")

# AND/OR/NAND/NOR
test_function(lambda inp: inp[0] & inp[1] & inp[2] & inp[3], "in0 AND in1 AND in2 AND in3")
test_function(lambda inp: 1 - (inp[0] & inp[1] & inp[2] & inp[3]), "NOT(in0 AND in1 AND in2 AND in3)")
test_function(lambda inp: inp[0] | inp[1] | inp[2] | inp[3], "in0 OR in1 OR in2 OR in3")
test_function(lambda inp: 1 - (inp[0] | inp[1] | inp[2] | inp[3]), "NOT(in0 OR in1 OR in2 OR in3)")

# Operaciones con entradas individuales
test_function(lambda inp: inp[0], "in0")
test_function(lambda inp: inp[1], "in1")
test_function(lambda inp: inp[2], "in2")
test_function(lambda inp: inp[3], "in3")
test_function(lambda inp: 1 - inp[0], "NOT in0")
test_function(lambda inp: 1 - inp[1], "NOT in1")
test_function(lambda inp: 1 - inp[2], "NOT in2")
test_function(lambda inp: 1 - inp[3], "NOT in3")

# Combinaciones de pares
test_function(lambda inp: inp[0] & inp[1], "in0 AND in1")
test_function(lambda inp: inp[0] | inp[1], "in0 OR in1")
test_function(lambda inp: inp[0] ^ inp[1], "in0 XOR in1")
test_function(lambda inp: inp[2] & inp[3], "in2 AND in3")
test_function(lambda inp: inp[2] | inp[3], "in2 OR in3")
test_function(lambda inp: inp[2] ^ inp[3], "in2 XOR in3")

test_function(lambda inp: (inp[0] & inp[1]) | (inp[2] & inp[3]), "(in0 AND in1) OR (in2 AND in3)")
test_function(lambda inp: (inp[0] | inp[1]) & (inp[2] | inp[3]), "(in0 OR in1) AND (in2 OR in3)")
test_function(lambda inp: (inp[0] ^ inp[1]) & (inp[2] ^ inp[3]), "(in0 XOR in1) AND (in2 XOR in3)")
test_function(lambda inp: (inp[0] ^ inp[1]) | (inp[2] ^ inp[3]), "(in0 XOR in1) OR (in2 XOR in3)")
test_function(lambda inp: (inp[0] ^ inp[1]) ^ (inp[2] ^ inp[3]), "(in0 XOR in1) XOR (in2 XOR in3)")

# Mayoría/Minoría con diferentes umbrales
test_function(lambda inp: 1 if sum(inp) != 0 else 0, "sum != 0")
test_function(lambda inp: 1 if sum(inp) != 1 else 0, "sum != 1")
test_function(lambda inp: 1 if sum(inp) != 2 else 0, "sum != 2")
test_function(lambda inp: 1 if sum(inp) != 3 else 0, "sum != 3")
test_function(lambda inp: 1 if sum(inp) != 4 else 0, "sum != 4")

print("\n" + "="*100)
print("BÚSQUEDA COMPLETA FINALIZADA")
print("="*100)
