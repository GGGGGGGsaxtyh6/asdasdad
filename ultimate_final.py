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

# INNOVACIÓN 161: Probar con GFSK (Gaussian FSK)
print("[*] INNOVACIÓN 161: GFSK modulation")
s = requests.Session()
s.get(base_url)

for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'GFSK', 'bits': '1', 'msg': packet})
    time.sleep(0.1)

time.sleep(5)

# Verificar TODO
for endpoint in ['/', '/updates', '/flag', '/escape']:
    try:
        r = s.get(f'{base_url}{endpoint}')
        if 'HTB{' in r.text:
            print(f'[SUCCESS in {endpoint}]', re.findall(r'HTB\{[^}]+\}', r.text))
            break
    except:
        pass

# INNOVACIÓN 162: Probar MSK (Minimum Shift Keying)
print("\n[*] INNOVACIÓN 162: MSK modulation")
s = requests.Session()
s.get(base_url)

for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'MSK', 'bits': '1', 'msg': packet})
    time.sleep(0.1)

time.sleep(5)
r = s.get(base_url)
if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 163: Probar GMSK (Gaussian MSK)
print("\n[*] INNOVACIÓN 163: GMSK modulation")
s = requests.Session()
s.get(base_url)

for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'GMSK', 'bits': '1', 'msg': packet})
    time.sleep(0.1)

time.sleep(5)
r = s.get(base_url)
if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

print("\n[*] Innovaciones 161-163 completadas")
