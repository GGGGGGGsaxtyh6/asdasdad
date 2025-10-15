#!/usr/bin/env python3
import requests
import struct
import time
import re

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

# LOOP INFINITO probando TODO
iteration = 0
while iteration < 1000:
    iteration += 1
    
    if iteration % 50 == 0:
        print(f"\n[Iteration {iteration}]")
    
    s = requests.Session()
    s.get(base_url)
    
    # Variar TODO
    mods = ['ASK', 'FSK', 'GFSK', 'MSK', 'OOK', '2FSK', '4FSK', 'AFSK', 'GMSK', 'PSK', 'QPSK', 'BPSK']
    mod = mods[iteration % len(mods)]
    bits = str((iteration % 8) + 1)
    freq_offset = (iteration % 21) - 10  # -10 a +10
    freq = f'{433.92 + freq_offset * 0.01:.2f}'
    
    # Enviar
    for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
        data = bytes([addr, cmd])
        crc = crc16(data)
        
        # Variar endianness
        if iteration % 2 == 0:
            packet = (data + struct.pack('<H', crc)).hex().upper()
        else:
            packet = (data + struct.pack('>H', crc)).hex().upper()
        
        s.post(f'{base_url}/transmit', data={'freq': freq, 'mod': mod, 'bits': bits, 'msg': packet})
    
    # Verificar
    time.sleep(1)
    r = s.get(base_url)
    
    if 'HTB{' in r.text:
        print(f"\n[SUCCESS at iteration {iteration}]!")
        print(f"  Params: freq={freq}, mod={mod}, bits={bits}")
        print(re.findall(r'HTB\{[^}]+\}', r.text))
        break
    
    # Verificar updates
    if iteration % 10 == 0:
        r = s.get(f'{base_url}/updates')
        if 'HTB{' in r.text:
            print(f"\n[SUCCESS in updates at iteration {iteration}]!")
            print(re.findall(r'HTB\{[^}]+\}', r.text))
            break

print(f"\n[*] Completed {iteration} iterations")
