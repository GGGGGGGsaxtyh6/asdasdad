#!/usr/bin/env python3

# Leer el archivo binario
with open('output.txt', 'rb') as f:
    data = f.read()

print(f"File size: {len(data)} bytes")

# Mostrar los primeros y últimos bytes en diferentes formatos
print("\n=== FIRST 100 BYTES ===")
print(f"Hex: {data[:100].hex()}")
print(f"Raw: {data[:100]}")
print(f"ASCII (errors=ignore): {data[:100].decode('ascii', errors='ignore')}")
print(f"Latin-1: {data[:100].decode('latin-1', errors='ignore')}")

print("\n=== LAST 100 BYTES ===")
print(f"Hex: {data[-100:].hex()}")
print(f"Raw: {data[-100:]}")
print(f"ASCII (errors=ignore): {data[-100:].decode('ascii', errors='ignore')}")
print(f"Latin-1: {data[-100:].decode('latin-1', errors='ignore')}")

# Buscar secuencias que podrían ser flags en formato hexadecimal
print("\n=== SEARCHING FOR HEX-ENCODED FLAGS ===")

# Convertir todo el archivo a hex y buscar patrones
hex_data = data.hex()
print(f"Hex data length: {len(hex_data)}")

# Buscar patrones comunes de flag en hex
flag_patterns_hex = [
    '464c4147',  # FLAG en hex
    '666c6167',  # flag en hex
    '72696e677a657230',  # ringzer0 en hex
    '637466',  # ctf en hex
    '756e316b30643372'  # un1k0d3r en hex
]

for pattern in flag_patterns_hex:
    pos = hex_data.find(pattern)
    if pos != -1:
        print(f"Found hex pattern {pattern} at position {pos}")
        # Extraer contexto
        start = max(0, pos - 20)
        end = min(len(hex_data), pos + 50)
        context = hex_data[start:end]
        print(f"Context: {context}")
        
        # Intentar decodificar como hex
        try:
            decoded = bytes.fromhex(context)
            decoded_str = decoded.decode('latin-1', errors='ignore')
            print(f"Decoded: {decoded_str}")
        except:
            pass

# Buscar patrones que empiecen con 7b (que es '{' en ASCII) y terminen con 7d (que es '}' en ASCII)
print("\n=== SEARCHING FOR BRACE PATTERNS IN HEX ===")

# Buscar 7b...7d en el hex
i = 0
while i < len(hex_data) - 4:
    if hex_data[i:i+2] == '7b':  # Encontrar {
        # Buscar el } correspondiente
        for j in range(i+2, min(len(hex_data), i+200), 2):  # Buscar hasta 100 bytes
            if hex_data[j:j+2] == '7d':  # Encontrar }
                # Extraer el patrón
                pattern_hex = hex_data[i:j+2]
                try:
                    pattern_bytes = bytes.fromhex(pattern_hex)
                    pattern_str = pattern_bytes.decode('latin-1', errors='ignore')
                    if pattern_str.isprintable() and len(pattern_str) > 2:
                        print(f"Hex pattern: {pattern_hex}")
                        print(f"Decoded: {pattern_str}")
                        
                        # Buscar si contiene palabras clave
                        if any(word in pattern_str.lower() for word in ['flag', 'ringzer0', 'ctf', 'un1k0d3r']):
                            print(f"*** FLAG CANDIDATE: {pattern_str} ***")
                except:
                    pass
                break
        i += 2
    else:
        i += 2

# Buscar patrones específicos en el archivo binario
print("\n=== SEARCHING FOR SPECIFIC BINARY PATTERNS ===")

# Buscar secuencias que podrían ser flags codificadas
for i in range(len(data) - 20):
    chunk = data[i:i+20]
    
    # Probar diferentes interpretaciones
    try:
        # Como ASCII
        ascii_str = chunk.decode('ascii', errors='ignore')
        if any(word in ascii_str.lower() for word in ['flag', 'ringzer0', 'ctf', 'un1k0d3r']):
            print(f"ASCII pattern at {i}: {ascii_str}")
            
        # Como latin-1
        latin_str = chunk.decode('latin-1', errors='ignore')
        if any(word in latin_str.lower() for word in ['flag', 'ringzer0', 'ctf', 'un1k0d3r']):
            print(f"Latin-1 pattern at {i}: {latin_str}")
            
    except:
        pass

# Buscar patrones que empiecen y terminen con llaves
print("\n=== SEARCHING FOR BRACE PATTERNS IN BINARY ===")

open_braces = [i for i, b in enumerate(data) if b == ord('{')]
close_braces = [i for i, b in enumerate(data) if b == ord('}')]

print(f"Found {len(open_braces)} opening braces and {len(close_braces)} closing braces")

# Buscar patrones entre llaves
for open_pos in open_braces:
    for close_pos in close_braces:
        if close_pos > open_pos and close_pos - open_pos <= 50:
            pattern = data[open_pos:close_pos+1]
            
            # Probar diferentes decodificaciones
            try:
                # Decodificación directa
                direct_str = pattern.decode('latin-1', errors='ignore')
                if direct_str.isprintable():
                    print(f"Direct at {open_pos}-{close_pos}: {direct_str}")
                    
                    # Buscar palabras clave
                    if any(word in direct_str.lower() for word in ['flag', 'ringzer0', 'ctf', 'un1k0d3r']):
                        print(f"*** FLAG CANDIDATE: {direct_str} ***")
                        
                # Probar con shift
                for shift in [1, 2, 3, 4, 5, 10, 13, 16, 32, 64, 128, 192, 224, 240, 248, 252, 254, 255]:
                    try:
                        shifted = bytes([(b + shift) % 256 for b in pattern])
                        shifted_str = shifted.decode('latin-1', errors='ignore')
                        if shifted_str.isprintable():
                            if any(word in shifted_str.lower() for word in ['flag', 'ringzer0', 'ctf', 'un1k0d3r']):
                                print(f"*** FLAG CANDIDATE with shift {shift}: {shifted_str} ***")
                    except:
                        continue
                        
                # Probar con XOR
                for xor_key in [1, 2, 3, 4, 5, 10, 13, 16, 32, 64, 128, 192, 224, 240, 248, 252, 254, 255]:
                    try:
                        xor_data = bytes([b ^ xor_key for b in pattern])
                        xor_str = xor_data.decode('latin-1', errors='ignore')
                        if xor_str.isprintable():
                            if any(word in xor_str.lower() for word in ['flag', 'ringzer0', 'ctf', 'un1k0d3r']):
                                print(f"*** FLAG CANDIDATE with XOR {xor_key}: {xor_str} ***")
                    except:
                        continue
                        
            except:
                pass

# Buscar secuencias que podrían ser base64
print("\n=== SEARCHING FOR BASE64 PATTERNS ===")

import base64
import re

# Buscar secuencias que parezcan base64
for i in range(0, len(data) - 20, 10):
    chunk = data[i:i+20]
    try:
        chunk_str = chunk.decode('latin-1', errors='ignore')
        # Verificar si parece base64
        if re.match(r'^[A-Za-z0-9+/]+=*$', chunk_str):
            try:
                b64_decoded = base64.b64decode(chunk_str)
                b64_result = b64_decoded.decode('latin-1', errors='ignore')
                if any(word in b64_result.lower() for word in ['flag', 'ringzer0', 'ctf', 'un1k0d3r']):
                    print(f"Found base64-encoded flag at position {i}: {b64_result}")
            except:
                pass
    except:
        continue

print("\n=== ANALYSIS COMPLETE ===")