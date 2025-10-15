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

# INNOVACIÓN 152: Enviar el patrón observado en la captura
print("[*] INNOVACIÓN 152: Using pattern from capture")
s = requests.Session()
s.get(base_url)

# El patrón es: FFFFFFFFFFFFFFFFFFFFFFFFE0000000000000000000000001
pattern = 'FFFFFFFFFFFFFFFFFFFFFFFFE0000000000000000000000001'

# Enviar este patrón
r = s.post(f'{base_url}/transmit', data={
    'freq': '433.92',
    'mod': 'FSK',
    'bits': '1',
    'msg': pattern
})

time.sleep(3)
r = s.get(base_url)
if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 153: Enviar preámbulo FFFFFF + paquetes
print("\n[*] INNOVACIÓN 153: FFFFFF preamble + packets")
s = requests.Session()
s.get(base_url)

preamble = 'FFFFFFFFFFFFFFFFFFFFFFFF'

for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    
    full_msg = preamble + 'E0' + packet
    print(f"  Sending: {full_msg}")
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'FSK', 'bits': '1', 'msg': full_msg})
    time.sleep(0.2)

time.sleep(5)
r = s.get(base_url)
sensor_states = re.findall(r'spinner-grow (text-\w+)', r.text)
print(f"\nStates: {sensor_states}")

if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))
else:
    r = s.get(f'{base_url}/updates')
    if 'HTB{' in r.text:
        print('[SUCCESS in updates]', re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 154: Probar diferentes start bytes
print("\n[*] INNOVACIÓN 154: Different start bytes")
s = requests.Session()
s.get(base_url)

for start_byte in ['E0', 'E1', 'E2', 'E3', 'A1', 'A2', 'A3', 'A4', 'FF', 'AA', '55']:
    data = bytes([0xE1, 0xFF])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    
    full_msg = 'FFFFFFFFFFFF' + start_byte + packet
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'FSK', 'bits': '1', 'msg': full_msg})

time.sleep(3)
r = s.get(base_url)
if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

print("\n[*] Innovaciones 152-154 completadas")
