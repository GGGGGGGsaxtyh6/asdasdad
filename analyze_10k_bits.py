#!/usr/bin/env python3
import numpy as np
import re

with open('/workspace/capture.bin', 'rb') as f:
    data = f.read()

samples = np.frombuffer(data, dtype=np.float32)
I = samples[0::2]
Q = samples[1::2]

magnitude = np.sqrt(I**2 + Q**2)

# Encontrar dónde están los 10400 bits de señal
# Buscar regiones con magnitud alta
high_mag_indices = np.where(magnitude > 0.5)[0]

print(f"[*] Indices with high magnitude: {len(high_mag_indices)}")
print(f"[*] First 50: {high_mag_indices[:50]}")
print(f"[*] Last 50: {high_mag_indices[-50:]}")

if len(high_mag_indices) > 0:
    start_idx = high_mag_indices[0]
    end_idx = high_mag_indices[-1]
    
    print(f"[*] Signal region: {start_idx} to {end_idx} ({end_idx - start_idx} samples)")
    
    # Extraer esa región
    signal_region = magnitude[start_idx:end_idx+1]
    print(f"[*] Signal region length: {len(signal_region)}")
    
    # Demodular esta región
    threshold_signal = np.median(signal_region)
    bits_signal = (signal_region > threshold_signal).astype(int)
    
    print(f"[*] Bits in signal region: {len(bits_signal)}")
    print(f"[*] First 100 bits: {''.join(map(str, bits_signal[:100]))}")
    
    # Convertir a bytes
    bytes_list = []
    for i in range(0, len(bits_signal), 8):
        if i + 8 <= len(bits_signal):
            byte_val = int(''.join(map(str, bits_signal[i:i+8])), 2)
            bytes_list.append(byte_val)
    
    print(f"[*] Decoded {len(bytes_list)} bytes")
    
    # Ver como hex
    hex_str = ''.join(f'{b:02X}' for b in bytes_list[:100])
    print(f"[*] Hex (first 100 bytes): {hex_str}")
    
    # Ver como texto
    text = ''.join(chr(b) if 32 <= b < 127 else '.' for b in bytes_list)
    print(f"[*] Text: {text[:200]}")
    
    if 'HTB{' in text:
        print(f"\n[SUCCESS] Found flag in demodulated signal!")
        flag_start = text.find('HTB{')
        print(text[flag_start:flag_start+50])
