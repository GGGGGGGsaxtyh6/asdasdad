#!/usr/bin/env python3
import numpy as np
import re

with open('/workspace/capture.bin', 'rb') as f:
    data = f.read()

samples = np.frombuffer(data, dtype=np.float32)
I = samples[0::2]
Q = samples[1::2]

complex_signal = I + 1j*Q
signal_region = complex_signal[:10400]

# Probar MUCHAS formas de demodular
print("[*] Trying multiple demodulation methods...")

# Método 1: Phase differential
phase = np.unwrap(np.angle(signal_region))
freq_inst = np.diff(phase)

# Probar múltiples thresholds
for percentile in [10, 20, 30, 40, 50, 60, 70, 80, 90]:
    threshold = np.percentile(freq_inst, percentile)
    bits = (freq_inst > threshold).astype(int)
    
    # Convertir a bytes
    bytes_list = []
    for i in range(0, min(len(bits), 8000), 8):
        byte_val = int(''.join(map(str, bits[i:i+8])), 2)
        bytes_list.append(byte_val)
    
    text = ''.join(chr(b) if 32 <= b < 127 else '.' for b in bytes_list)
    
    if 'HTB{' in text:
        print(f'\n[SUCCESS] Found with {percentile}% threshold!')
        flag_idx = text.find('HTB{')
        print(text[flag_idx:flag_idx+60])
        break
    
    # Buscar secuencias reconocibles
    hex_str = ''.join(f'{b:02X}' for b in bytes_list[:50])
    if any(pattern in hex_str for pattern in ['E1FF', 'A1F1', 'E2FF', 'A2F1']):
        print(f'  [{percentile}%] Found known patterns: {hex_str[:100]}')

# Método 2: Magnitude-based (AM)
magnitude = np.abs(signal_region)
for percentile in [10, 20, 30, 40, 50, 60, 70, 80, 90]:
    threshold = np.percentile(magnitude, percentile)
    bits = (magnitude > threshold).astype(int)
    
    bytes_list = []
    for i in range(0, min(len(bits), 8000), 8):
        byte_val = int(''.join(map(str, bits[i:i+8])), 2)
        bytes_list.append(byte_val)
    
    text = ''.join(chr(b) if 32 <= b < 127 else '.' for b in bytes_list)
    
    if 'HTB{' in text:
        print(f'\n[SUCCESS] Found with AM {percentile}% threshold!')
        flag_idx = text.find('HTB{')
        print(text[flag_idx:flag_idx+60])
        break

print("\n[*] Demodulation attempts completed")
