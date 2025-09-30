#!/usr/bin/env python3
import csv

# Leer los datos de entrada
with open('input.csv', 'r') as f:
    reader = csv.DictReader(f)
    inputs = []
    for row in reader:
        inputs.append([int(row['in0']), int(row['in1']), int(row['in2']), int(row['in3'])])

def test_and_show(inputs, func, name):
    print(f"\n{'='*70}")
    print(f"{name}")
    print(f"{'='*70}")
    
    results = [func(inp) for inp in inputs]
    
    # MSB first (normal)
    bytes_normal = []
    for i in range(0, len(results), 8):
        if i + 8 <= len(results):
            byte = results[i:i+8]
            byte_str = ''.join([str(b) for b in byte])
            value = int(byte_str, 2)
            bytes_normal.append(value)
    
    # LSB first (invertido)
    bytes_lsb = []
    for i in range(0, len(results), 8):
        if i + 8 <= len(results):
            byte = results[i:i+8]
            byte_rev = byte[::-1]
            byte_str = ''.join([str(b) for b in byte_rev])
            value = int(byte_str, 2)
            bytes_lsb.append(value)
    
    # Mostrar raw bytes
    print(f"\nBytes (MSB first): {bytes_normal[:20]}")
    print(f"Bytes (LSB first): {bytes_lsb[:20]}")
    
    # Intento de decodificar como texto
    try:
        text_normal = bytes(bytes_normal).decode('ascii', errors='ignore')
        text_lsb = bytes(bytes_lsb).decode('ascii', errors='ignore')
        
        print(f"\nTexto MSB: {repr(text_normal)}")
        print(f"Texto LSB: {repr(text_lsb)}")
        
        # Buscar patrones de flag
        if "HTB{" in text_normal or "FLAG" in text_normal:
            print(f"\n🚩🚩🚩 FLAG ENCONTRADA (MSB)! 🚩🚩🚩")
            print(f"FLAG: {text_normal}")
            return text_normal
        
        if "HTB{" in text_lsb or "FLAG" in text_lsb:
            print(f"\n🚩🚩🚩 FLAG ENCONTRADA (LSB)! 🚩🚩🚩")
            print(f"FLAG: {text_lsb}")
            return text_lsb
            
    except:
        pass
    
    return None

# Probar todas las funciones
test_and_show(inputs, lambda inp: 1 if sum(inp) < 2 else 0, "LOW (< 2 bits)")
test_and_show(inputs, lambda inp: 1 if sum(inp) <= 1 else 0, "LOW (<= 1 bit)")
test_and_show(inputs, lambda inp: 1 if sum(inp) <= 2 else 0, "LOW (<= 2 bits)")
test_and_show(inputs, lambda inp: 1 if sum(inp) == 0 else 0, "ZERO (== 0 bits)")
test_and_show(inputs, lambda inp: 1 if sum(inp) == 1 else 0, "ONE (== 1 bit)")
test_and_show(inputs, lambda inp: 1 if sum(inp) == 2 else 0, "TWO (== 2 bits)")
test_and_show(inputs, lambda inp: inp[0] ^ inp[1] ^ inp[2] ^ inp[3], "XOR")
test_and_show(inputs, lambda inp: 1 - (inp[0] ^ inp[1] ^ inp[2] ^ inp[3]), "XNOR / PARITY EVEN")
