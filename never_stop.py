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

# INNOVACIÓN 131: ¿Y si necesito PRIMERO enviar un comando broadcast para despertar los dispositivos?
print("[*] INNOVACIÓN 131: Broadcast wake-up first")
s = requests.Session()
s.get(base_url)

# Broadcast primero
data = bytes([0x03])
crc = crc16(data)
packet = (data + struct.pack('<H', crc)).hex().upper()
for _ in range(20):
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    time.sleep(0.05)

print("[*] Sent broadcasts, now sending commands...")

# Ahora comandos
for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    time.sleep(0.1)

time.sleep(5)

# Verificar TODOS los endpoints
for endpoint in ['/', '/updates', '/mission', '/agent', '/escape', '/success', '/result', '/status', '/flag', '/complete']:
    try:
        r = s.get(f'{base_url}{endpoint}')
        if 'HTB{' in r.text:
            print(f'[SUCCESS in {endpoint}]', re.findall(r'HTB\{[^}]+\}', r.text))
            break
    except:
        pass

# INNOVACIÓN 132: Enviar en REVERSE order (E3, E2, E1, A4, A3, A2, A1)
print('\n[*] INNOVACIÓN 132: Reverse device order')
s = requests.Session()
s.get(base_url)

for addr, cmd in [(0xE3, 0xFF), (0xE2, 0xFF), (0xE1, 0xFF), (0xA4, 0xF1), (0xA3, 0xF1), (0xA2, 0xF1), (0xA1, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    time.sleep(0.1)

time.sleep(3)
r = s.get(base_url)
if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))
"