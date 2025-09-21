#!/usr/bin/env python3

# Leer el archivo binario
with open('output.txt', 'rb') as f:
    data = f.read()

print(f"File size: {len(data)} bytes")

# Función para buscar patrones de flag en ventanas deslizantes
def find_flag_patterns():
    print("=== Searching for flag patterns in sliding windows ===")
    
    # Probar diferentes tamaños de ventana
    for window_size in [10, 20, 30, 50, 100]:
        print(f"\nTrying window size {window_size}:")
        for i in range(0, len(data) - window_size, window_size // 2):
            window = data[i:i+window_size]
            
            # Probar diferentes shifts
            for shift in range(1, 256):
                try:
                    shifted = bytes([(b + shift) % 256 for b in window])
                    shifted_str = shifted.decode('latin-1', errors='ignore')
                    
                    # Buscar patrones específicos
                    if 'FLAG{' in shifted_str or 'flag{' in shifted_str:
                        print(f"  Found FLAG pattern at position {i} with shift {shift}: {shifted_str}")
                    elif 'ringzer0' in shifted_str.lower():
                        print(f"  Found ringzer0 at position {i} with shift {shift}: {shifted_str}")
                    elif 'ctf{' in shifted_str.lower():
                        print(f"  Found ctf pattern at position {i} with shift {shift}: {shifted_str}")
                    elif 'un1k0d3r' in shifted_str.lower():
                        print(f"  Found un1k0d3r at position {i} with shift {shift}: {shifted_str}")
                except:
                    continue

# Función para buscar patrones específicos de longitud
def find_specific_length_patterns():
    print("\n=== Searching for specific length patterns ===")
    
    # Buscar patrones de diferentes longitudes
    for length in range(5, 51):
        print(f"\nSearching for {length}-character patterns:")
        patterns_found = 0
        
        for i in range(len(data) - length):
            chunk = data[i:i+length]
            
            # Probar diferentes shifts
            for shift in range(1, 256):
                try:
                    shifted = bytes([(b + shift) % 256 for b in chunk])
                    shifted_str = shifted.decode('latin-1', errors='ignore')
                    
                    if shifted_str.isprintable() and len(shifted_str.strip()) > 3:
                        if any(word in shifted_str.lower() for word in ['flag', 'ringzer0', 'ctf', 'un1k0d3r']):
                            print(f"  Found at position {i} with shift {shift}: {shifted_str}")
                            patterns_found += 1
                            if patterns_found > 10:  # Limitar output
                                print(f"  ... (showing first 10 matches)")
                                break
                except:
                    continue
            
            if patterns_found > 10:
                break

# Función para buscar patrones que empiecen y terminen con llaves
def find_brace_patterns():
    print("\n=== Searching for brace patterns ===")
    
    # Encontrar todas las llaves
    open_braces = [i for i, b in enumerate(data) if b == ord('{')]
    close_braces = [i for i, b in enumerate(data) if b == ord('}')]
    
    print(f"Found {len(open_braces)} opening braces and {len(close_braces)} closing braces")
    
    # Buscar patrones entre llaves
    for open_pos in open_braces:
        for close_pos in close_braces:
            if close_pos > open_pos and close_pos - open_pos <= 50:  # Patrones de hasta 50 caracteres
                pattern = data[open_pos:close_pos+1]
                
                # Probar diferentes shifts
                for shift in range(1, 256):
                    try:
                        shifted = bytes([(b + shift) % 256 for b in pattern])
                        shifted_str = shifted.decode('latin-1', errors='ignore')
                        
                        if shifted_str.isprintable() and len(shifted_str.strip()) > 3:
                            if any(word in shifted_str.lower() for word in ['flag', 'ringzer0', 'ctf', 'un1k0d3r']):
                                print(f"  Found at positions {open_pos}-{close_pos} with shift {shift}: {shifted_str}")
                    except:
                        continue

# Función para buscar patrones hexadecimales
def find_hex_patterns():
    print("\n=== Searching for hex patterns ===")
    
    # Buscar secuencias que podrían ser hexadecimal
    for i in range(0, len(data) - 20, 10):
        chunk = data[i:i+20]
        try:
            chunk_str = chunk.decode('latin-1', errors='ignore')
            # Verificar si es hexadecimal válido
            if all(c in '0123456789abcdefABCDEF' for c in chunk_str):
                try:
                    hex_decoded = bytes.fromhex(chunk_str)
                    hex_result = hex_decoded.decode('latin-1', errors='ignore')
                    if any(word in hex_result.lower() for word in ['flag', 'ringzer0', 'ctf', 'un1k0d3r']):
                        print(f"Found hex-encoded flag at position {i}: {hex_result}")
                except:
                    pass
        except:
            continue

# Función para buscar patrones base64
def find_base64_patterns():
    print("\n=== Searching for base64 patterns ===")
    
    import base64
    import re
    
    # Buscar secuencias que podrían ser base64
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

# Función para buscar patrones ROT13
def find_rot13_patterns():
    print("\n=== Searching for ROT13 patterns ===")
    
    # Buscar secuencias que podrían ser ROT13
    for i in range(0, len(data) - 20, 10):
        chunk = data[i:i+20]
        try:
            chunk_str = chunk.decode('latin-1', errors='ignore')
            if chunk_str.isalpha():
                # Aplicar ROT13
                rot13_str = ''.join([chr((ord(c) - 65 + 13) % 26 + 65) if c.isupper() else 
                                    chr((ord(c) - 97 + 13) % 26 + 97) if c.islower() else c 
                                    for c in chunk_str])
                if any(word in rot13_str.lower() for word in ['flag', 'ringzer0', 'ctf', 'un1k0d3r']):
                    print(f"Found ROT13-encoded flag at position {i}: {rot13_str}")
        except:
            continue

# Ejecutar todas las búsquedas
find_flag_patterns()
find_specific_length_patterns()
find_brace_patterns()
find_hex_patterns()
find_base64_patterns()
find_rot13_patterns()

# Buscar patrones específicos que ya encontramos
print("\n=== Analyzing known patterns ===")

known_patterns = [
    (7046, b'{\xefy}'),      # {ïy}
    (16271, b'{\xe0d}'),     # {àd}
    (2573, b'{ik\xb0`\xcb9\xab}')  # {ik°`Ë9«}
]

for pos, pattern in known_patterns:
    print(f"\nPattern at position {pos}: {pattern}")
    print(f"Hex: {pattern.hex()}")
    print(f"Decoded as latin-1: {pattern.decode('latin-1')}")
    
    # Probar diferentes interpretaciones
    print("Trying different shifts:")
    for shift in [1, 2, 3, 4, 5, 10, 13, 16, 32, 64, 128, 192, 224, 240, 248, 252, 254, 255]:
        try:
            shifted = bytes([(b + shift) % 256 for b in pattern])
            shifted_str = shifted.decode('latin-1', errors='ignore')
            if shifted_str.isprintable():
                print(f"  Shift {shift}: {shifted_str}")
                if any(word in shifted_str.lower() for word in ['flag', 'ringzer0', 'ctf', 'un1k0d3r']):
                    print(f"    *** CONTAINS FLAG KEYWORDS! ***")
        except:
            continue