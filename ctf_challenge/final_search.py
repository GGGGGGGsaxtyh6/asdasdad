#!/usr/bin/env python3

# Leer el archivo binario
with open('output.txt', 'rb') as f:
    data = f.read()

print(f"File size: {len(data)} bytes")

# Analizar específicamente el área alrededor de {àd}
print("=== Analyzing area around {àd} pattern ===")

# El patrón {àd} está en la posición 16271-16274
center_pos = 16271
area_start = max(0, center_pos - 50)
area_end = min(len(data), center_pos + 50)
area = data[area_start:area_end]

print(f"Area around position {center_pos}:")
print(f"Hex: {area.hex()}")
print(f"Raw: {area}")

# Probar diferentes interpretaciones del área completa
print(f"\n=== Trying different decodifications of the area ===")

for shift in range(1, 256):
    try:
        shifted = bytes([(b + shift) % 256 for b in area])
        shifted_str = shifted.decode('latin-1', errors='ignore')
        
        if shifted_str.isprintable() and len(shifted_str.strip()) > 10:
            # Buscar patrones de flag
            if any(word in shifted_str.lower() for word in ['flag', 'ringzer0', 'ctf', 'un1k0d3r']):
                print(f"Shift {shift}: {shifted_str}")
                print(f"  *** CONTAINS FLAG KEYWORDS! ***")
            elif '{' in shifted_str and '}' in shifted_str:
                # Extraer contenido entre llaves
                start_idx = shifted_str.find('{')
                end_idx = shifted_str.find('}', start_idx)
                if end_idx > start_idx:
                    content = shifted_str[start_idx:end_idx+1]
                    if len(content) > 3 and content.isprintable():
                        print(f"Shift {shift}: {shifted_str}")
                        print(f"  Flag-like content: {content}")
    except:
        continue

# Buscar patrones específicos en todo el archivo con diferentes codificaciones
print(f"\n=== Searching for flag patterns with different encodings ===")

# Probar ventanas deslizantes con diferentes tamaños
for window_size in [30, 50, 100]:
    print(f"\nTrying window size {window_size}:")
    for i in range(0, len(data) - window_size, window_size // 3):
        window = data[i:i+window_size]
        
        # Probar solo algunos shifts más probables
        for shift in [1, 2, 3, 4, 5, 10, 13, 16, 32, 64, 128, 192, 224, 240, 248, 252, 254, 255]:
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
            except:
                continue

# Buscar secuencias que podrían ser flags en formato hexadecimal
print(f"\n=== Searching for hex-encoded flags ===")

# Buscar patrones que podrían ser hexadecimal
for i in range(0, len(data) - 20, 10):
    chunk = data[i:i+20]
    try:
        chunk_str = chunk.decode('latin-1', errors='ignore')
        # Verificar si es hexadecimal válido
        if all(c in '0123456789abcdefABCDEF' for c in chunk_str):
            try:
                hex_decoded = bytes.fromhex(chunk_str)
                hex_result = hex_decoded.decode('latin-1', errors='ignore')
                if any(word in hex_result.lower() for word in ['flag', 'ringzer0', 'ctf']):
                    print(f"Found hex-encoded flag at position {i}: {hex_result}")
            except:
                pass
    except:
        continue

# Buscar patrones de longitud específica que podrían ser flags
print(f"\n=== Searching for specific length patterns ===")

# Buscar secuencias de 4-50 bytes que contengan { y }
for i in range(len(data) - 4):
    for length in range(4, min(51, len(data) - i)):
        chunk = data[i:i+length]
        if chunk[0] == ord('{') and chunk[-1] == ord('}'):
            # Probar diferentes decodificaciones
            for shift in [1, 2, 3, 4, 5, 10, 13, 16, 32, 64, 128, 192, 224, 240, 248, 252, 254, 255]:
                try:
                    shifted = bytes([(b + shift) % 256 for b in chunk])
                    shifted_str = shifted.decode('latin-1', errors='ignore')
                    
                    if shifted_str.isprintable() and len(shifted_str.strip()) > 3:
                        if any(word in shifted_str.lower() for word in ['flag', 'ringzer0', 'ctf', 'un1k0d3r']):
                            print(f"Found potential flag at position {i} with shift {shift}: {shifted_str}")
                except:
                    continue