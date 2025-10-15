#!/usr/bin/env python3
import numpy as np
import re

# Decodificar la captura con FSK de forma más inteligente
with open('/workspace/capture.bin', 'rb') as f:
    data = f.read()

samples = np.frombuffer(data, dtype=np.float32)
I = samples[0::2]
Q = samples[1::2]

complex_signal = I + 1j*Q
signal_region = complex_signal[:10400]

# Demodular FSK
phase = np.unwrap(np.angle(signal_region))
freq_inst = np.diff(phase)

# Buscar automáticamente los dos niveles de frecuencia
from scipy.cluster.vq import kmeans2

# Clustering para encontrar los dos niveles
valid_freq = freq_inst[~np.isnan(freq_inst)]
centroids, labels = kmeans2(valid_freq.reshape(-1, 1), 2)

print(f'[*] FSK frequency levels: {centroids.flatten()}')

# El bit es 1 si está en el centroide más alto
high_centroid_idx = np.argmax(centroids)
bits = (labels == high_centroid_idx).astype(int)

print(f'[*] Bits: {len(bits)}, Ones: {np.sum(bits)}, Zeros: {len(bits) - np.sum(bits)}')

# Buscar sincronización/preámbulo
# Buscar secuencias de 1s o 0s largas
max_ones = 0
max_zeros = 0
current_ones = 0
current_zeros = 0

for bit in bits:
    if bit == 1:
        current_ones += 1
        current_zeros = 0
        max_ones = max(max_ones, current_ones)
    else:
        current_zeros += 1
        current_ones = 0
        max_zeros = max(max_zeros, current_zeros)

print(f'[*] Max consecutive ones: {max_ones}')
print(f'[*] Max consecutive zeros: {max_zeros}')

# Convertir a bytes
bytes_list = []
for i in range(0, len(bits), 8):
    if i+8 <= len(bits):
        byte_val = int(''.join(map(str, bits[i:i+8])), 2)
        bytes_list.append(byte_val)

hex_str = ''.join(f'{b:02X}' for b in bytes_list)
text = ''.join(chr(b) if 32 <= b < 127 else '.' for b in bytes_list)

print(f'\n[*] Decoded hex (first 300): {hex_str[:300]}')
print(f'[*] Decoded text (first 200): {text[:200]}')

# Buscar flag
flags = re.findall(r'HTB\{[^}]+\}', text)
if flags:
    print(f'\n[SUCCESS] Found flags:')
    for flag in flags:
        print(flag)
else:
    print('\n[-] No flag found')
    
    # Buscar patrones conocidos
    if 'E1FF' in hex_str or 'A1F1' in hex_str:
        print('[+] Found known packet patterns!')
