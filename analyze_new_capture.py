#!/usr/bin/env python3
import numpy as np
import re

# Analizar la NUEVA captura (después de enviar comandos)
with open('/workspace/capture_after.bin', 'rb') as f:
    data = f.read()

samples = np.frombuffer(data, dtype=np.float32)
I = samples[0::2]
Q = samples[1::2]

complex_signal = I + 1j*Q

print(f'[*] Total samples: {len(complex_signal)}')

# Buscar regiones con señal
magnitude = np.abs(complex_signal)
high_mag = magnitude > 0.5

# Encontrar todas las regiones con señal
signal_regions = []
in_signal = False
start = 0

for i in range(len(high_mag)):
    if high_mag[i] and not in_signal:
        start = i
        in_signal = True
    elif not high_mag[i] and in_signal:
        signal_regions.append((start, i))
        in_signal = False

if in_signal:
    signal_regions.append((start, len(high_mag)))

print(f'[*] Found {len(signal_regions)} signal regions')

for idx, (start, end) in enumerate(signal_regions[:5]):
    print(f'  Region {idx}: samples {start}-{end} (length={end-start})')
    
    # Demodular esta región
    region_signal = complex_signal[start:end]
    phase = np.unwrap(np.angle(region_signal))
    freq_inst = np.diff(phase)
    
    threshold = np.median(freq_inst)
    bits = (freq_inst > threshold).astype(int)
    
    # Convertir a bytes
    bytes_list = []
    for i in range(0, len(bits), 8):
        if i+8 <= len(bits):
            byte_val = int(''.join(map(str, bits[i:i+8])), 2)
            bytes_list.append(byte_val)
    
    text = ''.join(chr(b) if 32 <= b < 127 else '.' for b in bytes_list)
    hex_str = ''.join(f'{b:02X}' for b in bytes_list[:50])
    
    print(f'    Hex: {hex_str}')
    print(f'    Text: {text[:100]}')
    
    if 'HTB{' in text:
        print(f'\n[SUCCESS] Found flag in region {idx}!')
        flag_idx = text.find('HTB{')
        print(text[flag_idx:flag_idx+60])
        break
