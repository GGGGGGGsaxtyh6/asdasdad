#!/usr/bin/env python3

# Leer el archivo binario
with open('output.txt', 'rb') as f:
    data = f.read()

print(f"File size: {len(data)} bytes")

# Buscar todas las posiciones de { y }
open_braces = []
close_braces = []

for i, byte in enumerate(data):
    if byte == ord('{'):
        open_braces.append(i)
    elif byte == ord('}'):
        close_braces.append(i)

print(f"Found {len(open_braces)} '{{' at positions: {open_braces}")
print(f"Found {len(close_braces)} '}}' at positions: {close_braces}")

# Función para probar diferentes decodificaciones
def try_decode(flag_data, start_pos, end_pos):
    print(f"\n=== Analyzing flag candidate between positions {start_pos} and {end_pos} ===")
    print(f"Length: {len(flag_data)} bytes")
    
    # 1. Decodificación directa
    try:
        direct_str = flag_data.decode('latin-1')
        print(f"Direct latin-1: {direct_str}")
        if direct_str.isprintable() and len(direct_str.strip()) > 3:
            print(f"*** PRINTABLE DIRECT: {direct_str} ***")
    except:
        pass
    
    # 2. Caesar cipher con diferentes shifts
    for shift in range(1, 256):
        try:
            shifted = bytes([(b + shift) % 256 for b in flag_data])
            shifted_str = shifted.decode('latin-1', errors='ignore')
            
            if shifted_str.isprintable() and len(shifted_str.strip()) > 5:
                # Buscar patrones de flag
                if any(word in shifted_str.lower() for word in ['flag', 'ringzer0', 'ctf', 'un1k0d3r']):
                    print(f"*** POSSIBLE FLAG with shift {shift}: {shifted_str} ***")
                elif shifted_str.startswith('FLAG{') or shifted_str.startswith('flag{'):
                    print(f"*** LIKELY FLAG with shift {shift}: {shifted_str} ***")
                elif 'ringzer0' in shifted_str.lower():
                    print(f"*** RINGZER0 FLAG with shift {shift}: {shifted_str} ***")
        except:
            continue
    
    # 3. XOR con diferentes claves
    for key in range(1, 256):
        try:
            xor_data = bytes([b ^ key for b in flag_data])
            xor_str = xor_data.decode('latin-1', errors='ignore')
            
            if xor_str.isprintable() and len(xor_str.strip()) > 5:
                if any(word in xor_str.lower() for word in ['flag', 'ringzer0', 'ctf']):
                    print(f"*** POSSIBLE FLAG with XOR key {key}: {xor_str} ***")
        except:
            continue
    
    # 4. ROT13 y variaciones
    try:
        rot13_str = ''.join([chr((ord(c) - 65 + 13) % 26 + 65) if c.isupper() else 
                            chr((ord(c) - 97 + 13) % 26 + 97) if c.islower() else c 
                            for c in flag_data.decode('latin-1', errors='ignore')])
        if 'flag' in rot13_str.lower() or 'ringzer0' in rot13_str.lower():
            print(f"*** ROT13 POSSIBLE: {rot13_str} ***")
    except:
        pass
    
    # 5. Base64 decode
    try:
        import base64
        b64_decoded = base64.b64decode(flag_data)
        b64_str = b64_decoded.decode('latin-1', errors='ignore')
        if b64_str.isprintable() and len(b64_str.strip()) > 5:
            print(f"*** BASE64 DECODED: {b64_str} ***")
    except:
        pass
    
    # 6. Hex decode
    try:
        hex_str = flag_data.decode('latin-1')
        if all(c in '0123456789abcdefABCDEF' for c in hex_str):
            hex_decoded = bytes.fromhex(hex_str)
            hex_result = hex_decoded.decode('latin-1', errors='ignore')
            if hex_result.isprintable() and len(hex_result.strip()) > 5:
                print(f"*** HEX DECODED: {hex_result} ***")
    except:
        pass

# Analizar los candidatos más prometedores
promising_candidates = [
    (131, 199),   # Primer par encontrado
    (2551, 2581), # Candidato corto
    (16271, 16274), # {àd} - muy prometedor
    (15884, 17055), # Candidato largo
]

for start, end in promising_candidates:
    if start < len(data) and end < len(data) and start < end:
        flag_data = data[start:end+1]
        try_decode(flag_data, start, end)

# También buscar patrones específicos en todo el archivo
print(f"\n=== Searching for specific patterns in entire file ===")

# Buscar "FLAG{" o "flag{"
for i in range(len(data) - 10):
    chunk = data[i:i+10]
    try:
        chunk_str = chunk.decode('latin-1', errors='ignore')
        if 'FLAG{' in chunk_str or 'flag{' in chunk_str:
            print(f"Found FLAG pattern at position {i}: {chunk_str}")
            # Extraer hasta el siguiente }
            for j in range(i+5, min(i+100, len(data))):
                if data[j] == ord('}'):
                    full_flag = data[i:j+1]
                    print(f"Full flag candidate: {full_flag.decode('latin-1', errors='ignore')}")
                    break
    except:
        continue

# Buscar "ringzer0" o "RingZer0"
for i in range(len(data) - 20):
    chunk = data[i:i+20]
    try:
        chunk_str = chunk.decode('latin-1', errors='ignore')
        if 'ringzer0' in chunk_str.lower() or 'ringzer0' in chunk_str:
            print(f"Found ringzer0 pattern at position {i}: {chunk_str}")
    except:
        continue