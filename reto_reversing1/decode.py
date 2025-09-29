#!/usr/bin/env python3

# Cadena secreta del reto
secret_bytes = [0x1b, 0x0f, 0x1d, 0x09, 0x21, 0x00, 0x7a, 0x51, 0x65, 0x1d, 0x08, 0x03, 0x14, 0x6a, 0x70, 0x34, 0x0a, 0x30, 0x1d, 0x11, 0x2c, 0x5e, 0x64, 0x23, 0x2f]

# Posible clave encontrada en el binario
possible_key = "hbh{G1IbRujko-A}"

print("=== Análisis de decodificación ===\n")
print(f"Cadena secreta (hex): {' '.join([hex(b) for b in secret_bytes])}")
print(f"Longitud: {len(secret_bytes)} bytes\n")

# Probar XOR con la clave encontrada
print("Probando XOR con clave: hbh{G1IbRujko-A}")
key_bytes = [ord(c) for c in possible_key]
print(f"Clave (hex): {' '.join([hex(b) for b in key_bytes])}\n")

# XOR cíclico
result = []
for i, byte in enumerate(secret_bytes):
    key_byte = key_bytes[i % len(key_bytes)]
    decoded = byte ^ key_byte
    result.append(chr(decoded) if 32 <= decoded <= 126 else '?')
    print(f"[{i:2d}] 0x{byte:02x} XOR 0x{key_byte:02x} ({chr(key_byte)}) = 0x{decoded:02x} ({chr(decoded) if 32 <= decoded <= 126 else '?'})")

decoded_string = ''.join(result)
print(f"\nResultado: {decoded_string}")

# Probar solo con "hbh" como clave
print("\n" + "="*50)
print("Probando con clave más corta: 'hbh'")
short_key = "hbh"
key_bytes = [ord(c) for c in short_key]

result = []
for i, byte in enumerate(secret_bytes):
    key_byte = key_bytes[i % len(key_bytes)]
    decoded = byte ^ key_byte
    result.append(chr(decoded) if 32 <= decoded <= 126 else '?')

decoded_string = ''.join(result)
print(f"Resultado: {decoded_string}")

# Probar con la flag completa
print("\n" + "="*50)
print("Probando con clave completa del binario")
full_key = "hbh{G1IbRujko-A}"
key_bytes = [ord(c) for c in full_key]

result = []
for i, byte in enumerate(secret_bytes):
    key_byte = key_bytes[i % len(key_bytes)]
    decoded = byte ^ key_byte
    result.append(chr(decoded) if 32 <= decoded <= 126 else '?')

decoded_string = ''.join(result)
print(f"Resultado: {decoded_string}")

# Análisis de frecuencia - probar claves de 1 byte
print("\n" + "="*50)
print("Probando claves de un solo byte (0x00 a 0xFF)...")
print("\nResultados legibles:")
for key_val in range(256):
    result = []
    valid = True
    for byte in secret_bytes:
        decoded = byte ^ key_val
        if 32 <= decoded <= 126:
            result.append(chr(decoded))
        elif decoded == 0:
            result.append('\0')
        else:
            valid = False
            break
    
    if valid and len(result) == len(secret_bytes):
        decoded_string = ''.join(result)
        if decoded_string.isprintable():
            print(f"Clave 0x{key_val:02x} ({chr(key_val) if 32 <= key_val <= 126 else '?'}): {decoded_string}")