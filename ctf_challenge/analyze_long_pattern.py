#!/usr/bin/env python3

# Leer el archivo binario
with open('output.txt', 'rb') as f:
    data = f.read()

print(f"File size: {len(data)} bytes")

# El patrón largo encontrado
pattern_pos = 2573
pattern = data[pattern_pos:pattern_pos+9]  # {ik°`Ë9«}
print(f"Long pattern at position {pattern_pos}: {pattern}")
print(f"Hex: {pattern.hex()}")
print(f"Decoded as latin-1: {pattern.decode('latin-1')}")

# Analizar contexto
context_start = max(0, pattern_pos - 50)
context_end = min(len(data), pattern_pos + 50)
context = data[context_start:context_end]
print(f"\nContext (50 bytes before and after):")
print(f"Hex: {context.hex()}")
print(f"Raw: {context}")

# Probar diferentes interpretaciones del patrón largo
print(f"\n=== Trying different interpretations of the long pattern ===")

# Probar diferentes shifts
for shift in range(1, 256):
    try:
        shifted = bytes([(b + shift) % 256 for b in pattern])
        shifted_str = shifted.decode('latin-1', errors='ignore')
        if shifted_str.isprintable():
            print(f"Shift {shift}: {shifted_str}")
            # Buscar patrones de flag
            if any(word in shifted_str.lower() for word in ['flag', 'ringzer0', 'ctf', 'un1k0d3r']):
                print(f"  *** CONTAINS FLAG KEYWORDS! ***")
    except:
        continue

# Probar XOR con diferentes claves
print(f"\n=== Trying XOR with different keys ===")
for key in range(1, 256):
    try:
        xor_data = bytes([b ^ key for b in pattern])
        xor_str = xor_data.decode('latin-1', errors='ignore')
        if xor_str.isprintable():
            print(f"XOR key {key}: {xor_str}")
            if any(word in xor_str.lower() for word in ['flag', 'ringzer0', 'ctf', 'un1k0d3r']):
                print(f"  *** CONTAINS FLAG KEYWORDS! ***")
    except:
        continue

# Probar como hexadecimal
print(f"\n=== Trying as hexadecimal ===")
try:
    hex_str = pattern.hex()
    print(f"As hex: {hex_str}")
    # Probar decodificar como hex
    hex_decoded = bytes.fromhex(hex_str)
    print(f"Hex decoded: {hex_decoded}")
    hex_result = hex_decoded.decode('latin-1', errors='ignore')
    print(f"Hex decoded as string: {hex_result}")
except Exception as e:
    print(f"Error with hex: {e}")

# Probar como base64
print(f"\n=== Trying as base64 ===")
try:
    import base64
    b64_encoded = base64.b64encode(pattern).decode('ascii')
    print(f"As base64: {b64_encoded}")
    b64_decoded = base64.b64decode(b64_encoded)
    print(f"Base64 decoded: {b64_decoded}")
    b64_result = b64_decoded.decode('latin-1', errors='ignore')
    print(f"Base64 decoded as string: {b64_result}")
except Exception as e:
    print(f"Error with base64: {e}")

# Buscar si este patrón podría ser parte de una flag más larga
print(f"\n=== Searching for extended patterns around the long pattern ===")

# Buscar patrones más largos que contengan este patrón
for length in range(10, 100):
    for i in range(max(0, pattern_pos - length), min(len(data) - length, pattern_pos + length)):
        if i <= pattern_pos <= i + length - 1:
            chunk = data[i:i+length]
            if chunk[0] == ord('{') and chunk[-1] == ord('}'):
                try:
                    decoded = chunk.decode('latin-1', errors='ignore')
                    if decoded.isprintable() and '{ik°`Ë9«}' in decoded:
                        print(f"Extended pattern at position {i}: {decoded}")
                except:
                    pass

# Buscar patrones similares en todo el archivo
print(f"\n=== Searching for similar patterns in the entire file ===")

# Buscar todos los patrones de 7 caracteres entre llaves
seven_char_patterns = []
for i in range(len(data) - 8):
    if data[i] == ord('{') and data[i+8] == ord('}'):
        pattern = data[i+1:i+8]
        try:
            decoded = pattern.decode('latin-1')
            if decoded.isprintable():
                seven_char_patterns.append((i, pattern, decoded))
        except:
            pass

print(f"Found {len(seven_char_patterns)} printable 7-character patterns:")
for pos, pattern, decoded in seven_char_patterns:
    print(f"Position {pos}: {{{decoded}}} (hex: {pattern.hex()})")
    
    # Probar diferentes shifts para cada patrón
    for shift in [1, 2, 3, 4, 5, 10, 13, 16, 32, 64, 128, 192, 224, 240, 248, 252, 254, 255]:
        try:
            shifted = bytes([(b + shift) % 256 for b in pattern])
            shifted_str = shifted.decode('latin-1', errors='ignore')
            if shifted_str.isprintable():
                if any(word in shifted_str.lower() for word in ['flag', 'ringzer0', 'ctf', 'un1k0d3r']):
                    print(f"  *** FLAG-LIKE with shift {shift}: {shifted_str} ***")
        except:
            continue

# Buscar patrones de 8 caracteres también
print(f"\n=== Searching for 8-character patterns ===")
eight_char_patterns = []
for i in range(len(data) - 9):
    if data[i] == ord('{') and data[i+9] == ord('}'):
        pattern = data[i+1:i+9]
        try:
            decoded = pattern.decode('latin-1')
            if decoded.isprintable():
                eight_char_patterns.append((i, pattern, decoded))
        except:
            pass

print(f"Found {len(eight_char_patterns)} printable 8-character patterns:")
for pos, pattern, decoded in eight_char_patterns:
    print(f"Position {pos}: {{{decoded}}} (hex: {pattern.hex()})")
    
    # Probar diferentes shifts para cada patrón
    for shift in [1, 2, 3, 4, 5, 10, 13, 16, 32, 64, 128, 192, 224, 240, 248, 252, 254, 255]:
        try:
            shifted = bytes([(b + shift) % 256 for b in pattern])
            shifted_str = shifted.decode('latin-1', errors='ignore')
            if shifted_str.isprintable():
                if any(word in shifted_str.lower() for word in ['flag', 'ringzer0', 'ctf', 'un1k0d3r']):
                    print(f"  *** FLAG-LIKE with shift {shift}: {shifted_str} ***")
        except:
            continue