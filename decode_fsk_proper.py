#!/usr/bin/env python3
import numpy as np
from scipy import signal as scipy_signal

with open('/workspace/capture.bin', 'rb') as f:
    data = f.read()

samples = np.frombuffer(data, dtype=np.float32)
I = samples[0::2]
Q = samples[1::2]

complex_signal = I + 1j*Q

print(f'[*] Total complex samples: {len(complex_signal)}')

# Tomar solo la región con señal (primeros 10400)
signal_region = complex_signal[:10400]

# Demodular FSK usando phase
phase = np.unwrap(np.angle(signal_region))
freq_inst = np.diff(phase)

print(f'[*] Instantaneous frequency range: [{freq_inst.min():.4f}, {freq_inst.max():.4f}]')
print(f'[*] Mean: {freq_inst.mean():.4f}, Std: {freq_inst.std():.4f}')

# Para FSK binario, hay dos frecuencias
# Encontrar el threshold
threshold = np.median(freq_inst)
print(f'[*] Threshold: {threshold:.4f}')

bits = (freq_inst > threshold).astype(int)
print(f'[*] Decoded {len(bits)} bits')
print(f'[*] Ones: {np.sum(bits)}, Zeros: {len(bits) - np.sum(bits)}')

# Convertir a bytes
bytes_list = []
for i in range(0, len(bits), 8):
    if i+8 <= len(bits):
        byte_val = int(''.join(map(str, bits[i:i+8])), 2)
        bytes_list.append(byte_val)

print(f'\n[*] Decoded {len(bytes_list)} bytes')

# Ver como hex
hex_str = ''.join(f'{b:02X}' for b in bytes_list)
print(f'[*] Hex (first 200): {hex_str[:200]}')

# Ver como texto
text = ''.join(chr(b) if 32 <= b < 127 else '.' for b in bytes_list)
print(f'[*] Text (first 200): {text[:200]}')

# Buscar HTB
if 'HTB{' in text:
    print(f'\n[SUCCESS] Found flag in FSK demodulation!')
    flag_idx = text.find('HTB{')
    print(text[flag_idx:flag_idx+100])
else:
    print('\n[-] No flag in FSK demodulation')
    
    # Probar con threshold diferente
    print('\n[*] Trying different thresholds...')
    for percentile in [25, 50, 75]:
        threshold_alt = np.percentile(freq_inst, percentile)
        bits_alt = (freq_inst > threshold_alt).astype(int)
        
        bytes_alt = []
        for i in range(0, len(bits_alt), 8):
            if i+8 <= len(bits_alt):
                byte_val = int(''.join(map(str, bits_alt[i:i+8])), 2)
                bytes_alt.append(byte_val)
        
        text_alt = ''.join(chr(b) if 32 <= b < 127 else '.' for b in bytes_alt)
        
        if 'HTB{' in text_alt:
            print(f'[SUCCESS] Found flag with {percentile}th percentile threshold!')
            flag_idx = text_alt.find('HTB{')
            print(text_alt[flag_idx:flag_idx+100])
            break
