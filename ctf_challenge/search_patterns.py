#!/usr/bin/env python3

# Leer el archivo binario
with open('output.txt', 'rb') as f:
    data = f.read()

print(f"File size: {len(data)} bytes")

# Buscar patrones específicos que podrían indicar el inicio de un flag
flag_patterns = [
    b'flag',
    b'FLAG',
    b'ringzer0',
    b'RINGZER0',
    b'ctf',
    b'CTF',
    b'{',
    b'}',
    b'RingZer0',
    b'un1k0d3r',
    b'Un1k0d3r'
]

print("Searching for flag patterns in raw data:")
for pattern in flag_patterns:
    pos = data.find(pattern)
    if pos != -1:
        print(f"Found '{pattern.decode('latin-1')}' at position {pos}")
        # Mostrar contexto
        start = max(0, pos - 20)
        end = min(len(data), pos + len(pattern) + 20)
        context = data[start:end]
        print(f"Context: {context}")
        print(f"Context (hex): {context.hex()}")
        print()

# Buscar patrones en diferentes codificaciones
print("\nTrying different encodings and transformations:")

# Intentar Caesar cipher en todo el rango
for shift in range(1, 26):
    try:
        decoded = bytes([(b + shift) % 256 for b in data])
        decoded_str = decoded.decode('latin-1', errors='ignore')
        if 'flag' in decoded_str.lower() or 'ringzer0' in decoded_str.lower():
            print(f"Caesar cipher with shift {shift} might work:")
            print(decoded_str[:200])
            print()
    except:
        continue

# Intentar buscar en chunks de diferentes tamaños
print("\nSearching in chunks:")
chunk_sizes = [16, 32, 64, 128, 256]
for chunk_size in chunk_sizes:
    for i in range(0, len(data), chunk_size):
        chunk = data[i:i+chunk_size]
        try:
            chunk_str = chunk.decode('utf-8', errors='ignore')
            if len(chunk_str) > 5 and ('flag' in chunk_str.lower() or 'ring' in chunk_str.lower()):
                print(f"Possible text in chunk {i}-{i+chunk_size}: {chunk_str}")
        except:
            continue

# Buscar secuencias que podrían ser ASCII imprimible
print("\nLooking for ASCII sequences:")
ascii_sequences = []
current_seq = b""
for byte in data:
    if 32 <= byte <= 126:  # ASCII imprimible
        current_seq += bytes([byte])
    else:
        if len(current_seq) > 10:  # Secuencias de más de 10 caracteres ASCII
            ascii_sequences.append(current_seq)
        current_seq = b""

if current_seq and len(current_seq) > 10:
    ascii_sequences.append(current_seq)

print(f"Found {len(ascii_sequences)} ASCII sequences longer than 10 chars:")
for i, seq in enumerate(ascii_sequences[:10]):  # Mostrar solo las primeras 10
    print(f"  {i+1}: {seq.decode('ascii')}")
    if 'flag' in seq.decode('ascii').lower() or 'ring' in seq.decode('ascii').lower():
        print(f"    *** This sequence contains potential flag keywords! ***")