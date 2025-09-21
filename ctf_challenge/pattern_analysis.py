#!/usr/bin/env python3

# Leer el archivo binario
with open('output.txt', 'rb') as f:
    data = f.read()

print(f"File size: {len(data)} bytes")

# Los patrones que ya encontramos
known_patterns = [
    (7046, b'{\xefy}'),      # {ïy}
    (16271, b'{\xe0d}'),     # {àd}
    (2573, b'{ik\xb0`\xcb9\xab}')  # {ik°`Ë9«}
]

print("=== ANALYZING KNOWN PATTERNS ===")

for pos, pattern in known_patterns:
    print(f"\nPattern at position {pos}: {pattern}")
    print(f"Hex: {pattern.hex()}")
    print(f"Decoded as latin-1: {pattern.decode('latin-1')}")
    
    # Analizar cada byte del patrón
    print("Byte analysis:")
    for i, byte in enumerate(pattern):
        print(f"  Byte {i}: {byte} (0x{byte:02x}) = '{chr(byte) if 32 <= byte <= 126 else '?'}'")
    
    # Probar diferentes interpretaciones
    print("Trying different interpretations:")
    
    # 1. Como ASCII
    try:
        ascii_str = pattern.decode('ascii', errors='ignore')
        print(f"  ASCII: {ascii_str}")
    except:
        pass
    
    # 2. Como UTF-8
    try:
        utf8_str = pattern.decode('utf-8', errors='ignore')
        print(f"  UTF-8: {utf8_str}")
    except:
        pass
    
    # 3. Como hexadecimal
    hex_str = pattern.hex()
    print(f"  As hex: {hex_str}")
    
    # 4. Probar diferentes shifts
    print("  Shifts:")
    for shift in [1, 2, 3, 4, 5, 10, 13, 16, 32, 64, 128, 192, 224, 240, 248, 252, 254, 255]:
        try:
            shifted = bytes([(b + shift) % 256 for b in pattern])
            shifted_str = shifted.decode('latin-1', errors='ignore')
            if shifted_str.isprintable():
                print(f"    Shift {shift}: {shifted_str}")
        except:
            continue
    
    # 5. Probar XOR con diferentes claves
    print("  XOR keys:")
    for key in [1, 2, 3, 4, 5, 10, 13, 16, 32, 64, 128, 192, 224, 240, 248, 252, 254, 255]:
        try:
            xor_data = bytes([b ^ key for b in pattern])
            xor_str = xor_data.decode('latin-1', errors='ignore')
            if xor_str.isprintable():
                print(f"    XOR {key}: {xor_str}")
        except:
            continue

# Buscar patrones similares en todo el archivo
print("\n=== SEARCHING FOR SIMILAR PATTERNS ===")

# Buscar todos los patrones de 4 caracteres entre llaves
print("4-character patterns:")
for i in range(len(data) - 3):
    if data[i] == ord('{') and data[i+3] == ord('}'):
        pattern = data[i:i+4]
        try:
            decoded = pattern.decode('latin-1')
            if decoded.isprintable():
                print(f"  Position {i}: {decoded} (hex: {pattern.hex()})")
        except:
            pass

# Buscar todos los patrones de 5 caracteres entre llaves
print("5-character patterns:")
for i in range(len(data) - 4):
    if data[i] == ord('{') and data[i+4] == ord('}'):
        pattern = data[i:i+5]
        try:
            decoded = pattern.decode('latin-1')
            if decoded.isprintable():
                print(f"  Position {i}: {decoded} (hex: {pattern.hex()})")
        except:
            pass

# Buscar todos los patrones de 6 caracteres entre llaves
print("6-character patterns:")
for i in range(len(data) - 5):
    if data[i] == ord('{') and data[i+5] == ord('}'):
        pattern = data[i:i+6]
        try:
            decoded = pattern.decode('latin-1')
            if decoded.isprintable():
                print(f"  Position {i}: {decoded} (hex: {pattern.hex()})")
        except:
            pass

# Buscar todos los patrones de 7 caracteres entre llaves
print("7-character patterns:")
for i in range(len(data) - 6):
    if data[i] == ord('{') and data[i+6] == ord('}'):
        pattern = data[i:i+7]
        try:
            decoded = pattern.decode('latin-1')
            if decoded.isprintable():
                print(f"  Position {i}: {decoded} (hex: {pattern.hex()})")
        except:
            pass

# Buscar todos los patrones de 8 caracteres entre llaves
print("8-character patterns:")
for i in range(len(data) - 7):
    if data[i] == ord('{') and data[i+7] == ord('}'):
        pattern = data[i:i+8]
        try:
            decoded = pattern.decode('latin-1')
            if decoded.isprintable():
                print(f"  Position {i}: {decoded} (hex: {pattern.hex()})")
        except:
            pass

# Buscar todos los patrones de 9 caracteres entre llaves
print("9-character patterns:")
for i in range(len(data) - 8):
    if data[i] == ord('{') and data[i+8] == ord('}'):
        pattern = data[i:i+9]
        try:
            decoded = pattern.decode('latin-1')
            if decoded.isprintable():
                print(f"  Position {i}: {decoded} (hex: {pattern.hex()})")
        except:
            pass

# Buscar todos los patrones de 10 caracteres entre llaves
print("10-character patterns:")
for i in range(len(data) - 9):
    if data[i] == ord('{') and data[i+9] == ord('}'):
        pattern = data[i:i+10]
        try:
            decoded = pattern.decode('latin-1')
            if decoded.isprintable():
                print(f"  Position {i}: {decoded} (hex: {pattern.hex()})")
        except:
            pass

# Buscar patrones que podrían ser flags codificadas
print("\n=== SEARCHING FOR ENCODED FLAGS ===")

# Probar diferentes codificaciones en ventanas deslizantes
for window_size in [20, 50, 100]:
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
                    print(f"  Found FLAG pattern at position {i} with shift {shift}")
                    print(f"  Content: {shifted_str}")
                    
                    # Extraer flag completa
                    flag_start = shifted_str.find('FLAG{') if 'FLAG{' in shifted_str else shifted_str.find('flag{')
                    flag_end = shifted_str.find('}', flag_start)
                    if flag_end > flag_start:
                        flag = shifted_str[flag_start:flag_end+1]
                        print(f"  *** FLAG FOUND: {flag} ***")
                        
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
                                    break
                            break
                            
            except:
                continue

print("\n=== ANALYSIS COMPLETE ===")