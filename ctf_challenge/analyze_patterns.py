#!/usr/bin/env python3

# Leer el archivo binario
with open('output.txt', 'rb') as f:
    data = f.read()

print(f"File size: {len(data)} bytes")

# Los patrones encontrados
patterns = [
    (7046, b'{\xefy}'),  # {ïy}
    (16271, b'{\xe0d}')  # {àd}
]

print("=== Analyzing found patterns ===")

for pos, pattern in patterns:
    print(f"\nPattern at position {pos}: {pattern}")
    print(f"Hex: {pattern.hex()}")
    print(f"Decoded as latin-1: {pattern.decode('latin-1')}")
    
    # Analizar contexto
    context_start = max(0, pos - 20)
    context_end = min(len(data), pos + 20)
    context = data[context_start:context_end]
    print(f"Context: {context}")
    
    # Probar diferentes interpretaciones
    print(f"Trying different shifts:")
    for shift in range(1, 256):
        try:
            shifted = bytes([(b + shift) % 256 for b in pattern])
            shifted_str = shifted.decode('latin-1', errors='ignore')
            if shifted_str.isprintable():
                print(f"  Shift {shift}: {shifted_str}")
        except:
            continue

# Buscar más patrones similares
print(f"\n=== Searching for all 2-character patterns between braces ===")
all_patterns = []
for i in range(len(data) - 3):
    if data[i] == ord('{') and data[i+3] == ord('}'):
        pattern = data[i+1:i+3]
        try:
            decoded = pattern.decode('latin-1')
            if decoded.isprintable():
                all_patterns.append((i, pattern, decoded))
                print(f"Position {i}: {{{decoded}}} (hex: {pattern.hex()})")
        except:
            pass

print(f"\nFound {len(all_patterns)} printable 2-character patterns")

# Buscar patrones de 1 carácter
print(f"\n=== Searching for 1-character patterns between braces ===")
single_char_patterns = []
for i in range(len(data) - 2):
    if data[i] == ord('{') and data[i+2] == ord('}'):
        pattern = data[i+1:i+2]
        try:
            decoded = pattern.decode('latin-1')
            if decoded.isprintable():
                single_char_patterns.append((i, pattern, decoded))
                print(f"Position {i}: {{{decoded}}} (hex: {pattern.hex()})")
        except:
            pass

print(f"\nFound {len(single_char_patterns)} printable 1-character patterns")

# Buscar patrones de 3 caracteres
print(f"\n=== Searching for 3-character patterns between braces ===")
three_char_patterns = []
for i in range(len(data) - 4):
    if data[i] == ord('{') and data[i+4] == ord('}'):
        pattern = data[i+1:i+4]
        try:
            decoded = pattern.decode('latin-1')
            if decoded.isprintable():
                three_char_patterns.append((i, pattern, decoded))
                print(f"Position {i}: {{{decoded}}} (hex: {pattern.hex()})")
        except:
            pass

print(f"\nFound {len(three_char_patterns)} printable 3-character patterns")

# Buscar patrones de 4 caracteres
print(f"\n=== Searching for 4-character patterns between braces ===")
four_char_patterns = []
for i in range(len(data) - 5):
    if data[i] == ord('{') and data[i+5] == ord('}'):
        pattern = data[i+1:i+5]
        try:
            decoded = pattern.decode('latin-1')
            if decoded.isprintable():
                four_char_patterns.append((i, pattern, decoded))
                print(f"Position {i}: {{{decoded}}} (hex: {pattern.hex()})")
        except:
            pass

print(f"\nFound {len(four_char_patterns)} printable 4-character patterns")

# Buscar patrones de 5 caracteres
print(f"\n=== Searching for 5-character patterns between braces ===")
five_char_patterns = []
for i in range(len(data) - 6):
    if data[i] == ord('{') and data[i+6] == ord('}'):
        pattern = data[i+1:i+6]
        try:
            decoded = pattern.decode('latin-1')
            if decoded.isprintable():
                five_char_patterns.append((i, pattern, decoded))
                print(f"Position {i}: {{{decoded}}} (hex: {pattern.hex()})")
        except:
            pass

print(f"\nFound {len(five_char_patterns)} printable 5-character patterns")

# Buscar patrones de 6 caracteres
print(f"\n=== Searching for 6-character patterns between braces ===")
six_char_patterns = []
for i in range(len(data) - 7):
    if data[i] == ord('{') and data[i+7] == ord('}'):
        pattern = data[i+1:i+7]
        try:
            decoded = pattern.decode('latin-1')
            if decoded.isprintable():
                six_char_patterns.append((i, pattern, decoded))
                print(f"Position {i}: {{{decoded}}} (hex: {pattern.hex()})")
        except:
            pass

print(f"\nFound {len(six_char_patterns)} printable 6-character patterns")

# Buscar patrones de 7 caracteres
print(f"\n=== Searching for 7-character patterns between braces ===")
seven_char_patterns = []
for i in range(len(data) - 8):
    if data[i] == ord('{') and data[i+8] == ord('}'):
        pattern = data[i+1:i+8]
        try:
            decoded = pattern.decode('latin-1')
            if decoded.isprintable():
                seven_char_patterns.append((i, pattern, decoded))
                print(f"Position {i}: {{{decoded}}} (hex: {pattern.hex()})")
        except:
            pass

print(f"\nFound {len(seven_char_patterns)} printable 7-character patterns")

# Buscar patrones de 8 caracteres
print(f"\n=== Searching for 8-character patterns between braces ===")
eight_char_patterns = []
for i in range(len(data) - 9):
    if data[i] == ord('{') and data[i+9] == ord('}'):
        pattern = data[i+1:i+9]
        try:
            decoded = pattern.decode('latin-1')
            if decoded.isprintable():
                eight_char_patterns.append((i, pattern, decoded))
                print(f"Position {i}: {{{decoded}}} (hex: {pattern.hex()})")
        except:
            pass

print(f"\nFound {len(eight_char_patterns)} printable 8-character patterns")

# Buscar patrones de 9 caracteres
print(f"\n=== Searching for 9-character patterns between braces ===")
nine_char_patterns = []
for i in range(len(data) - 10):
    if data[i] == ord('{') and data[i+10] == ord('}'):
        pattern = data[i+1:i+10]
        try:
            decoded = pattern.decode('latin-1')
            if decoded.isprintable():
                nine_char_patterns.append((i, pattern, decoded))
                print(f"Position {i}: {{{decoded}}} (hex: {pattern.hex()})")
        except:
            pass

print(f"\nFound {len(nine_char_patterns)} printable 9-character patterns")

# Buscar patrones de 10 caracteres
print(f"\n=== Searching for 10-character patterns between braces ===")
ten_char_patterns = []
for i in range(len(data) - 11):
    if data[i] == ord('{') and data[i+11] == ord('}'):
        pattern = data[i+1:i+11]
        try:
            decoded = pattern.decode('latin-1')
            if decoded.isprintable():
                ten_char_patterns.append((i, pattern, decoded))
                print(f"Position {i}: {{{decoded}}} (hex: {pattern.hex()})")
        except:
            pass

print(f"\nFound {len(ten_char_patterns)} printable 10-character patterns")

# Resumen de todos los patrones encontrados
print(f"\n=== SUMMARY ===")
print(f"1-character patterns: {len(single_char_patterns)}")
print(f"2-character patterns: {len(all_patterns)}")
print(f"3-character patterns: {len(three_char_patterns)}")
print(f"4-character patterns: {len(four_char_patterns)}")
print(f"5-character patterns: {len(five_char_patterns)}")
print(f"6-character patterns: {len(six_char_patterns)}")
print(f"7-character patterns: {len(seven_char_patterns)}")
print(f"8-character patterns: {len(eight_char_patterns)}")
print(f"9-character patterns: {len(nine_char_patterns)}")
print(f"10-character patterns: {len(ten_char_patterns)}")

# Buscar patrones que podrían ser flags
print(f"\n=== Looking for flag-like patterns ===")
all_short_patterns = (single_char_patterns + all_patterns + three_char_patterns + 
                     four_char_patterns + five_char_patterns + six_char_patterns + 
                     seven_char_patterns + eight_char_patterns + nine_char_patterns + 
                     ten_char_patterns)

for pos, pattern, decoded in all_short_patterns:
    if any(word in decoded.lower() for word in ['flag', 'ringzer0', 'ctf', 'un1k0d3r']):
        print(f"*** FLAG-LIKE PATTERN at position {pos}: {{{decoded}}} ***")
    elif decoded.isalnum() and len(decoded) >= 3:
        print(f"Alphanumeric pattern at position {pos}: {{{decoded}}}")