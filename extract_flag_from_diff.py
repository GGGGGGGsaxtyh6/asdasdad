#!/usr/bin/env python3
import numpy as np
import re

print("[*] Analyzing BOTH captures to find the flag")

for filename in ['/workspace/capture.bin', '/workspace/capture_after.bin']:
    print(f"\n[*] Analyzing {filename}")
    
    with open(filename, 'rb') as f:
        data = f.read()
    
    samples = np.frombuffer(data, dtype=np.float32)
    
    # Buscar TODAS las regiones con señal, no solo la primera
    I = samples[0::2]
    Q = samples[1::2]
    
    magnitude = np.sqrt(I**2 + Q**2)
    
    # Encontrar TODAS las regiones con magnitud > 0.3
    high_regions = np.where(magnitude > 0.3)[0]
    
    if len(high_regions) > 0:
        print(f"  Signal regions: {len(high_regions)} samples")
        print(f"  First: {high_regions[0]}, Last: {high_regions[-1]}")
        
        # Agrupar en regiones continuas
        regions = []
        if len(high_regions) > 0:
            region_start = high_regions[0]
            prev = high_regions[0]
            
            for idx in high_regions[1:]:
                if idx - prev > 100:  # Gap mayor a 100 samples
                    regions.append((region_start, prev))
                    region_start = idx
                prev = idx
            
            regions.append((region_start, prev))
        
        print(f"  Continuous regions: {len(regions)}")
        
        # Analizar cada región
        for i, (start, end) in enumerate(regions[:10]):
            print(f"\n  Region {i}: {start}-{end} ({end-start} samples)")
            
            # Extraer señal
            complex_region = I[start:end+1] + 1j*Q[start:end+1]
            
            # Demodular FSK
            phase = np.unwrap(np.angle(complex_region))
            freq = np.diff(phase)
            
            if len(freq) > 0:
                threshold = np.median(freq)
                bits = (freq > threshold).astype(int)
                
                # Convertir a bytes
                bytes_list = []
                for j in range(0, len(bits), 8):
                    if j+8 <= len(bits):
                        byte_val = int(''.join(map(str, bits[j:j+8])), 2)
                        bytes_list.append(byte_val)
                
                if bytes_list:
                    hex_str = ''.join(f'{b:02X}' for b in bytes_list)
                    text = ''.join(chr(b) if 32 <= b < 127 else '.' for b in bytes_list)
                    
                    print(f'    Hex: {hex_str[:100]}')
                    print(f'    Text: {text[:80]}')
                    
                    if 'HTB{' in text:
                        print(f'\n[SUCCESS] Found flag in region {i} of {filename}!')
                        flag_idx = text.find('HTB{')
                        print(text[flag_idx:flag_idx+60])
