#!/usr/bin/env python3

# Leer el archivo binario
with open('output.txt', 'rb') as f:
    data = f.read()

print(f"File size: {len(data)} bytes")

# Función para buscar patrones específicos de flag en todo el archivo
def find_flag_patterns():
    print("=== SEARCHING FOR FLAG PATTERNS ===")
    
    # Patrones comunes de flag
    flag_patterns = [
        b'FLAG{',
        b'flag{',
        b'Flag{',
        b'FLAG',
        b'flag',
        b'Flag',
        b'ringzer0',
        b'RingZer0',
        b'RINGZER0',
        b'ctf',
        b'CTF',
        b'un1k0d3r',
        b'Un1k0d3r',
        b'UN1K0D3R'
    ]
    
    for pattern in flag_patterns:
        pos = data.find(pattern)
        if pos != -1:
            print(f"Found '{pattern.decode('latin-1', errors='ignore')}' at position {pos}")
            # Mostrar contexto
            start = max(0, pos - 20)
            end = min(len(data), pos + len(pattern) + 50)
            context = data[start:end]
            print(f"Context: {context}")
            print()

# Función para buscar patrones con diferentes codificaciones
def find_encoded_patterns():
    print("=== SEARCHING FOR ENCODED PATTERNS ===")
    
    # Probar diferentes ventanas y shifts
    for window_size in [20, 50, 100, 200]:
        print(f"\nTrying window size {window_size}:")
        for i in range(0, len(data) - window_size, window_size // 4):
            window = data[i:i+window_size]
            
            # Probar diferentes shifts
            for shift in range(1, 256):
                try:
                    shifted = bytes([(b + shift) % 256 for b in window])
                    shifted_str = shifted.decode('latin-1', errors='ignore')
                    
                    # Buscar patrones específicos
                    if 'FLAG{' in shifted_str or 'flag{' in shifted_str:
                        print(f"  Found FLAG pattern at position {i} with shift {shift}")
                        print(f"  Content: {shifted_str}")
                        
                        # Extraer flag completa
                        flag_start = shifted_str.find('FLAG{') if 'FLAG{' in shifted_str else shifted_str.find('flag{')
                        flag_end = shifted_str.find('}', flag_start)
                        if flag_end > flag_start:
                            flag = shifted_str[flag_start:flag_end+1]
                            print(f"  *** FLAG FOUND: {flag} ***")
                            return flag
                            
                    elif 'ringzer0' in shifted_str.lower():
                        print(f"  Found ringzer0 at position {i} with shift {shift}")
                        print(f"  Content: {shifted_str}")
                        
                        # Buscar flag completa
                        pos = shifted_str.lower().find('ringzer0')
                        for j in range(pos, max(0, pos-50), -1):
                            if shifted_str[j] == '{':
                                for k in range(pos, min(len(shifted_str), pos+50)):
                                    if shifted_str[k] == '}':
                                        flag = shifted_str[j:k+1]
                                        print(f"  *** FLAG FOUND: {flag} ***")
                                        return flag
                                        break
                                break
                                
                    elif 'ctf{' in shifted_str.lower():
                        print(f"  Found ctf pattern at position {i} with shift {shift}")
                        print(f"  Content: {shifted_str}")
                        
                        # Extraer flag completa
                        flag_start = shifted_str.find('ctf{')
                        flag_end = shifted_str.find('}', flag_start)
                        if flag_end > flag_start:
                            flag = shifted_str[flag_start:flag_end+1]
                            print(f"  *** FLAG FOUND: {flag} ***")
                            return flag
                            
                    elif 'un1k0d3r' in shifted_str.lower():
                        print(f"  Found un1k0d3r at position {i} with shift {shift}")
                        print(f"  Content: {shifted_str}")
                        
                        # Buscar flag completa
                        pos = shifted_str.lower().find('un1k0d3r')
                        for j in range(pos, max(0, pos-50), -1):
                            if shifted_str[j] == '{':
                                for k in range(pos, min(len(shifted_str), pos+50)):
                                    if shifted_str[k] == '}':
                                        flag = shifted_str[j:k+1]
                                        print(f"  *** FLAG FOUND: {flag} ***")
                                        return flag
                                        break
                                break
                                
                except:
                    continue
    
    return None

# Función para buscar patrones entre llaves
def find_brace_patterns():
    print("\n=== SEARCHING FOR BRACE PATTERNS ===")
    
    # Encontrar todas las llaves
    open_braces = [i for i, b in enumerate(data) if b == ord('{')]
    close_braces = [i for i, b in enumerate(data) if b == ord('}')]
    
    print(f"Found {len(open_braces)} opening braces and {len(close_braces)} closing braces")
    
    # Buscar patrones entre llaves
    for open_pos in open_braces:
        for close_pos in close_braces:
            if close_pos > open_pos and close_pos - open_pos <= 100:
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
    print("\n=== SEARCHING FOR HEX PATTERNS ===")
    
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
    print("\n=== SEARCHING FOR BASE64 PATTERNS ===")
    
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
    print("\n=== SEARCHING FOR ROT13 PATTERNS ===")
    
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

# Función para buscar patrones específicos de longitud
def find_length_patterns():
    print("\n=== SEARCHING FOR LENGTH-SPECIFIC PATTERNS ===")
    
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
                            if patterns_found > 5:
                                print(f"  ... (showing first 5 matches)")
                                break
                except:
                    continue
            
            if patterns_found > 5:
                break

# Función para buscar patrones específicos que ya encontramos
def analyze_known_patterns():
    print("\n=== ANALYZING KNOWN PATTERNS ===")
    
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

# Ejecutar todos los análisis
print("Starting comprehensive analysis...")

# 1. Buscar patrones específicos
find_flag_patterns()

# 2. Buscar patrones codificados
flag = find_encoded_patterns()
if flag:
    print(f"\n*** FINAL FLAG FOUND: {flag} ***")
    exit()

# 3. Buscar patrones entre llaves
find_brace_patterns()

# 4. Buscar patrones hexadecimales
find_hex_patterns()

# 5. Buscar patrones base64
find_base64_patterns()

# 6. Buscar patrones ROT13
find_rot13_patterns()

# 7. Buscar patrones de longitud específica
find_length_patterns()

# 8. Analizar patrones conocidos
analyze_known_patterns()

print("\n*** COMPREHENSIVE ANALYSIS COMPLETE ***")
print("No standard flag patterns found. The flag might be encoded in a non-standard way.")

# Buscar patrones que podrían ser flags en formato específico
print("\n=== SEARCHING FOR SPECIFIC FLAG FORMATS ===")

# Buscar patrones que empiecen con { y terminen con }
for i in range(len(data) - 4):
    if data[i] == ord('{') and data[i+3] == ord('}'):
        pattern = data[i:i+4]
        try:
            decoded = pattern.decode('latin-1')
            if decoded.isprintable():
                print(f"Position {i}: {decoded} (hex: {pattern.hex()})")
        except:
            pass

# Buscar patrones de 5 caracteres
for i in range(len(data) - 4):
    if data[i] == ord('{') and data[i+4] == ord('}'):
        pattern = data[i:i+5]
        try:
            decoded = pattern.decode('latin-1')
            if decoded.isprintable():
                print(f"Position {i}: {decoded} (hex: {pattern.hex()})")
        except:
            pass

# Buscar patrones de 6 caracteres
for i in range(len(data) - 5):
    if data[i] == ord('{') and data[i+5] == ord('}'):
        pattern = data[i:i+6]
        try:
            decoded = pattern.decode('latin-1')
            if decoded.isprintable():
                print(f"Position {i}: {decoded} (hex: {pattern.hex()})")
        except:
            pass

# Buscar patrones de 7 caracteres
for i in range(len(data) - 6):
    if data[i] == ord('{') and data[i+6] == ord('}'):
        pattern = data[i:i+7]
        try:
            decoded = pattern.decode('latin-1')
            if decoded.isprintable():
                print(f"Position {i}: {decoded} (hex: {pattern.hex()})")
        except:
            pass

# Buscar patrones de 8 caracteres
for i in range(len(data) - 7):
    if data[i] == ord('{') and data[i+7] == ord('}'):
        pattern = data[i:i+8]
        try:
            decoded = pattern.decode('latin-1')
            if decoded.isprintable():
                print(f"Position {i}: {decoded} (hex: {pattern.hex()})")
        except:
            pass

# Buscar patrones de 9 caracteres
for i in range(len(data) - 8):
    if data[i] == ord('{') and data[i+8] == ord('}'):
        pattern = data[i:i+9]
        try:
            decoded = pattern.decode('latin-1')
            if decoded.isprintable():
                print(f"Position {i}: {decoded} (hex: {pattern.hex()})")
        except:
            pass

# Buscar patrones de 10 caracteres
for i in range(len(data) - 9):
    if data[i] == ord('{') and data[i+9] == ord('}'):
        pattern = data[i:i+10]
        try:
            decoded = pattern.decode('latin-1')
            if decoded.isprintable():
                print(f"Position {i}: {decoded} (hex: {pattern.hex()})")
        except:
            pass

print("\n*** FINAL ANALYSIS COMPLETE ***")