#!/usr/bin/env python3

# Leer el archivo binario
with open('output.txt', 'rb') as f:
    data = f.read()

print(f"File size: {len(data)} bytes")

# Buscar patrones específicos de flag
def find_flag_patterns():
    patterns = [
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
    
    for pattern in patterns:
        pos = data.find(pattern)
        if pos != -1:
            print(f"Found '{pattern.decode('latin-1')}' at position {pos}")
            # Mostrar contexto
            start = max(0, pos - 20)
            end = min(len(data), pos + len(pattern) + 50)
            context = data[start:end]
            print(f"Context: {context}")
            print(f"Context (hex): {context.hex()}")
            print()

# Buscar secuencias que podrían ser flags después de decodificación
def find_decoded_flags():
    print("=== Searching for decoded flag patterns ===")
    
    # Probar diferentes shifts en ventanas deslizantes
    for window_size in [10, 20, 50, 100]:
        print(f"\nTrying window size {window_size}:")
        for i in range(0, len(data) - window_size, window_size // 2):
            window = data[i:i+window_size]
            
            # Probar diferentes shifts
            for shift in range(1, 256):
                try:
                    shifted = bytes([(b + shift) % 256 for b in window])
                    shifted_str = shifted.decode('latin-1', errors='ignore')
                    
                    # Buscar patrones de flag
                    if any(word in shifted_str.lower() for word in ['flag{', 'ringzer0', 'ctf{']):
                        print(f"  Found at position {i} with shift {shift}: {shifted_str}")
                        
                        # Si encontramos un patrón, extraer más contexto
                        if '{' in shifted_str and '}' in shifted_str:
                            start_idx = shifted_str.find('{')
                            end_idx = shifted_str.find('}', start_idx)
                            if end_idx > start_idx:
                                potential_flag = shifted_str[start_idx:end_idx+1]
                                print(f"    Potential flag: {potential_flag}")
                except:
                    continue

# Buscar secuencias ASCII imprimibles largas
def find_ascii_sequences():
    print("\n=== Searching for long ASCII sequences ===")
    
    current_ascii = b""
    ascii_sequences = []
    
    for byte in data:
        if 32 <= byte <= 126:  # ASCII imprimible
            current_ascii += bytes([byte])
        else:
            if len(current_ascii) > 20:  # Secuencias de más de 20 caracteres
                ascii_sequences.append(current_ascii)
            current_ascii = b""
    
    if len(current_ascii) > 20:
        ascii_sequences.append(current_ascii)
    
    print(f"Found {len(ascii_sequences)} ASCII sequences longer than 20 chars:")
    for i, seq in enumerate(ascii_sequences[:10]):  # Mostrar solo las primeras 10
        seq_str = seq.decode('ascii')
        print(f"  {i+1}: {seq_str}")
        if any(word in seq_str.lower() for word in ['flag', 'ringzer0', 'ctf']):
            print(f"    *** This sequence contains flag keywords! ***")

# Buscar en chunks específicos alrededor de las llaves
def analyze_brace_areas():
    print("\n=== Analyzing areas around braces ===")
    
    # Encontrar todas las llaves
    open_braces = [i for i, b in enumerate(data) if b == ord('{')]
    close_braces = [i for i, b in enumerate(data) if b == ord('}')]
    
    # Analizar áreas alrededor de cada llave
    for brace_pos in open_braces[:10]:  # Solo las primeras 10
        print(f"\nAnalyzing area around opening brace at position {brace_pos}:")
        
        # Extraer área alrededor de la llave
        start = max(0, brace_pos - 10)
        end = min(len(data), brace_pos + 100)
        area = data[start:end]
        
        # Probar diferentes decodificaciones
        for shift in range(1, 256):
            try:
                shifted = bytes([(b + shift) % 256 for b in area])
                shifted_str = shifted.decode('latin-1', errors='ignore')
                
                if shifted_str.isprintable() and len(shifted_str.strip()) > 10:
                    if any(word in shifted_str.lower() for word in ['flag', 'ringzer0', 'ctf']):
                        print(f"  Shift {shift}: {shifted_str}")
            except:
                continue

# Ejecutar todas las búsquedas
find_flag_patterns()
find_decoded_flags()
find_ascii_sequences()
analyze_brace_areas()

# Buscar específicamente el patrón {àd} y variaciones
print("\n=== Analyzing {àd} pattern specifically ===")
# El patrón {àd} está en la posición 16271-16274
if 16271 < len(data) and 16274 < len(data):
    pattern = data[16271:16275]
    print(f"Original pattern: {pattern}")
    print(f"Hex: {pattern.hex()}")
    
    # Probar diferentes interpretaciones
    for shift in range(1, 256):
        try:
            shifted = bytes([(b + shift) % 256 for b in pattern])
            shifted_str = shifted.decode('latin-1', errors='ignore')
            if shifted_str.isprintable():
                print(f"Shift {shift}: {shifted_str}")
        except:
            continue