#!/usr/bin/env python3

# Leer el archivo binario
with open('output.txt', 'rb') as f:
    data = f.read()

print(f"File size: {len(data)} bytes")

# Posiciones de { y }
brace_open_pos = 199
brace_close_pos = 131

print("'{' found at position", brace_open_pos)
print("'}' found at position", brace_close_pos)

# Examinar el área alrededor de estos caracteres
def examine_area(pos, label):
    print(f"\n=== {label} ===")
    start = max(0, pos - 50)
    end = min(len(data), pos + 50)
    area = data[start:end]
    
    print(f"Hex dump around position {pos}:")
    for i in range(0, len(area), 16):
        chunk = area[i:i+16]
        hex_str = ' '.join(f'{b:02x}' for b in chunk)
        ascii_str = ''.join(chr(b) if 32 <= b <= 126 else '.' for b in chunk)
        print(f"{start + i:08x}: {hex_str:<48} |{ascii_str}|")
    
    print(f"Raw bytes: {area}")
    print(f"As string (latin-1): {area.decode('latin-1', errors='replace')}")

examine_area(brace_close_pos, "Area around '}' at position 131")
examine_area(brace_open_pos, "Area around '{' at position 199")

# Buscar todos los { y }
print(f"\n=== All braces in file ===")
open_braces = []
close_braces = []

for i, byte in enumerate(data):
    if byte == ord('{'):
        open_braces.append(i)
    elif byte == ord('}'):
        close_braces.append(i)

print("Found", len(open_braces), "'{' at positions:", open_braces)
print("Found", len(close_braces), "'}' at positions:", close_braces)

# Si hay múltiples pares, examinar cada uno
if len(open_braces) > 0 and len(close_braces) > 0:
    for open_pos in open_braces:
        for close_pos in close_braces:
            if open_pos < close_pos:
                print(f"\nPotential flag between positions {open_pos} and {close_pos}:")
                flag_data = data[open_pos:close_pos+1]
                print(f"Length: {len(flag_data)} bytes")
                print(f"Hex: {flag_data.hex()}")
                print(f"Raw: {flag_data}")
                print(f"As string: {flag_data.decode('latin-1', errors='replace')}")
                
                # Intentar diferentes decodificaciones
                for shift in range(1, 256):
                    try:
                        shifted = bytes([(b + shift) % 256 for b in flag_data])
                        shifted_str = shifted.decode('latin-1', errors='ignore')
                        if shifted_str.isprintable() and len(shifted_str) > 5:
                            if 'flag' in shifted_str.lower() or 'ring' in shifted_str.lower():
                                print(f"  Shift {shift}: {shifted_str}")
                    except:
                        continue