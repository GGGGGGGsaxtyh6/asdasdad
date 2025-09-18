#!/usr/bin/env python3
"""
Calculadora de checksum para ChronoVM
Calcula el checksum correcto del binario compilado
"""

import sys
import os

def calculate_checksum(filename):
    """Calcula el checksum del binario"""
    if not os.path.exists(filename):
        print(f"Error: Archivo '{filename}' no encontrado")
        return None
    
    with open(filename, 'rb') as f:
        data = f.read()
    
    checksum = 0
    for byte in data:
        checksum = (checksum << 1) ^ byte
        checksum &= 0xFFFFFFFF
    
    return checksum

def main():
    if len(sys.argv) != 2:
        print("Uso: python3 calculate_checksum.py <binario>")
        sys.exit(1)
    
    filename = sys.argv[1]
    checksum = calculate_checksum(filename)
    
    if checksum is not None:
        print(f"Checksum calculado: 0x{checksum:08X}")
        print(f"Para usar en el código: 0x{checksum:08X}")
    else:
        sys.exit(1)

if __name__ == "__main__":
    main()