#!/usr/bin/env python3
import requests
import struct
import time
import re
import numpy as np

def crc16(data, poly=0x1D0F):
    crc = 0x0000
    for byte in data:
        crc ^= (byte << 8)
        for _ in range(8):
            if crc & 0x8000:
                crc = ((crc << 1) ^ poly) & 0xFFFF
            else:
                crc = (crc << 1) & 0xFFFF
    return crc

base_url = 'http://94.237.53.81:59575'

# INNOVACIÓN 165: Enviar comandos PERFECTOS y descargar capture inmediatamente
print("[*] INNOVACIÓN 165: Perfect commands + immediate capture download")

s = requests.Session()
s.get(base_url)

# Enviar en el orden correcto con FSK
print("  Sending with FSK...")
for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'FSK', 'bits': '1', 'msg': packet})

# Descargar capture inmediatamente
print("  Downloading capture...")
r = s.get(f'{base_url}/capture')

# Guardar
with open('/workspace/capture_fsk.bin', 'wb') as f:
    f.write(r.content)

# Decodificar
samples = np.frombuffer(r.content, dtype=np.float32)
I = samples[0::2]
Q = samples[1::2]

# Buscar TODAS las regiones del archivo, no solo el inicio
print(f'\n[*] Analyzing entire capture ({len(samples)} samples)')

# Buscar en chunks de 10000 samples
for chunk_start in range(0, len(I), 10000):
    chunk_end = min(chunk_start + 10000, len(I))
    
    I_chunk = I[chunk_start:chunk_end]
    Q_chunk = Q[chunk_start:chunk_end]
    
    complex_chunk = I_chunk + 1j*Q_chunk
    
    # Demodular FSK
    if len(complex_chunk) > 1:
        phase = np.unwrap(np.angle(complex_chunk))
        freq = np.diff(phase)
        
        if len(freq) > 0:
            threshold = np.median(freq)
            bits = (freq > threshold).astype(int)
            
            # Convertir a bytes
            bytes_list = []
            for i in range(0, len(bits), 8):
                if i+8 <= len(bits):
                    byte_val = int(''.join(map(str, bits[i:i+8])), 2)
                    bytes_list.append(byte_val)
            
            if bytes_list:
                text = ''.join(chr(b) if 32 <= b < 127 else '.' for b in bytes_list)
                
                if 'HTB{' in text:
                    print(f'\n[SUCCESS] Found flag in chunk {chunk_start}-{chunk_end}!')
                    flag_idx = text.find('HTB{')
                    print(text[flag_idx:flag_idx+60])
                    exit(0)

print("\n[*] No flag found in any chunk")
