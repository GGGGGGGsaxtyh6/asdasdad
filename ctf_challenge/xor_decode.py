#!/usr/bin/env python3

# Leer el archivo binario
with open('output.txt', 'rb') as f:
    data = f.read()

print(f"File size: {len(data)} bytes")

# Intentar XOR con diferentes claves
xor_keys = [
    0x01, 0x02, 0x03, 0x04, 0x05, 0x10, 0x20, 0x30, 0x40, 0x50,
    0x60, 0x70, 0x80, 0x90, 0xa0, 0xb0, 0xc0, 0xd0, 0xe0, 0xf0,
    0xff, 0xaa, 0x55, 0x11, 0x22, 0x33, 0x44, 0x66, 0x77, 0x88, 0x99
]

for key in xor_keys:
    decoded = bytes([b ^ key for b in data])
    decoded_str = decoded.decode('latin-1', errors='ignore')
    
    # Buscar patrones que indiquen que hemos encontrado texto
    if 'flag' in decoded_str.lower() or 'ringzer0' in decoded_str.lower() or 'ctf' in decoded_str.lower():
        print(f"Possible match with XOR key 0x{key:02x}:")
        print(decoded_str[:500])
        print("=" * 50)
    
    # También buscar patrones comunes de texto
    if decoded_str.count(' ') > len(decoded_str) * 0.1 and decoded_str.isprintable():
        print(f"Printable text found with XOR key 0x{key:02x}:")
        print(decoded_str[:200])
        print("=" * 50)

# También intentar con claves multi-byte
multi_keys = [b'key', b'flag', b'ctf', b'un1k0d3r', b'password']

for key in multi_keys:
    decoded = bytes([data[i] ^ key[i % len(key)] for i in range(len(data))])
    decoded_str = decoded.decode('latin-1', errors='ignore')
    
    if 'flag' in decoded_str.lower() or 'ringzer0' in decoded_str.lower():
        print(f"Possible match with multi-byte key {key}:")
        print(decoded_str[:500])
        print("=" * 50)