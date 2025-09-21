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

# Examinar cada combinación posible
for open_pos in open_braces:
    for close_pos in close_braces:
        if open_pos < close_pos:
            flag_data = data[open_pos:close_pos+1]
            print(f"\nFlag candidate between positions {open_pos} and {close_pos}:")
            print(f"Length: {len(flag_data)} bytes")
            print(f"Raw: {flag_data}")
            
            # Intentar decodificar como texto
            try:
                flag_str = flag_data.decode('latin-1')
                print(f"As latin-1: {flag_str}")
                
                # Verificar si contiene caracteres imprimibles
                if flag_str.isprintable():
                    print(f"*** PRINTABLE FLAG CANDIDATE: {flag_str} ***")
            except:
                pass
            
            # Intentar diferentes shifts para Caesar cipher
            for shift in range(1, 256):
                try:
                    shifted = bytes([(b + shift) % 256 for b in flag_data])
                    shifted_str = shifted.decode('latin-1', errors='ignore')
                    
                    # Buscar patrones de flag comunes
                    if shifted_str.isprintable() and len(shifted_str.strip()) > 10:
                        if any(word in shifted_str.lower() for word in ['flag', 'ringzer0', 'ctf', 'un1k0d3r']):
                            print(f"  *** POSSIBLE FLAG with shift {shift}: {shifted_str} ***")
                        elif shifted_str.startswith('FLAG{') or shifted_str.startswith('flag{'):
                            print(f"  *** LIKELY FLAG with shift {shift}: {shifted_str} ***")
                except:
                    continue