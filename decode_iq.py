#!/usr/bin/env python3
import numpy as np
import struct

# Cargar archivo IQ
with open('/workspace/capture.bin', 'rb') as f:
    data = f.read()

samples = np.frombuffer(data, dtype=np.float32)

# Separar I y Q
I = samples[0::2]
Q = samples[1::2]

# Calcular magnitud y fase
magnitude = np.sqrt(I**2 + Q**2)
phase = np.arctan2(Q, I)

# Demodular ASK: usar magnitud
# Buscar umbral
threshold = np.median(magnitude)
print(f"[*] Threshold: {threshold}")

# Convertir a bits
bits = (magnitude > threshold).astype(int)

print(f"[*] Total bits: {len(bits)}")
print(f"[*] Ones: {np.sum(bits)}, Zeros: {len(bits) - np.sum(bits)}")

# Buscar el inicio de un paquete
# Buscar preámbulo común: 10101010 o 01010101
preamble_1010 = [1,0,1,0,1,0,1,0]
preamble_0101 = [0,1,0,1,0,1,0,1]

# Función para buscar patrón
def find_pattern(bits, pattern):
    pattern_len = len(pattern)
    matches = []
    for i in range(len(bits) - pattern_len):
        if list(bits[i:i+pattern_len]) == pattern:
            matches.append(i)
    return matches

matches_1010 = find_pattern(bits, preamble_1010)
matches_0101 = find_pattern(bits, preamble_0101)

print(f"[*] Matches for 10101010: {len(matches_1010)}")
print(f"[*] Matches for 01010101: {len(matches_0101)}")

if matches_1010 or matches_0101:
    # Analizar datos después del preámbulo
    start_positions = matches_1010[:10] if matches_1010 else matches_0101[:10]
    
    for pos in start_positions:
        # Leer los siguiente 48 bits (6 bytes) después del preámbulo
        if pos + 8 + 48 < len(bits):
            packet_bits = bits[pos+8:pos+8+48]
            # Convertir bits a bytes
            bytes_data = []
            for i in range(0, len(packet_bits), 8):
                if i+8 <= len(packet_bits):
                    byte_val = int(''.join(map(str, packet_bits[i:i+8])), 2)
                    bytes_data.append(byte_val)
            
            if len(bytes_data) >= 3:
                hex_str = ''.join(f'{b:02X}' for b in bytes_data)
                print(f'  Position {pos}: {hex_str}')
                
                # Buscar HTB
                text = ''.join(chr(b) if 32 <= b < 127 else '.' for b in bytes_data)
                if 'HTB' in text or 'flag' in text.lower():
                    print(f'    [!] Found text: {text}')

# También intentar demodular FSK usando fase
phase_diff = np.diff(phase)
fsk_bits = (phase_diff > 0).astype(int)

print(f'\n[*] FSK demodulation:')
print(f'[*] FSK bits: {len(fsk_bits)}')
print(f'[*] FSK Ones: {np.sum(fsk_bits)}')

# Buscar patrones HTB en FSK
fsk_bytes = []
for i in range(0, len(fsk_bits) - 8, 8):
    byte_val = int(''.join(map(str, fsk_bits[i:i+8])), 2)
    fsk_bytes.append(byte_val)

fsk_text = ''.join(chr(b) if 32 <= b < 127 else '.' for b in fsk_bytes[:1000])
if 'HTB' in fsk_text:
    print('[SUCCESS] Found HTB in FSK demodulation!')
    print(fsk_text)
"