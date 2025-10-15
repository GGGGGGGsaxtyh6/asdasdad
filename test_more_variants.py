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

# INNOVACIÓN 72: Probar con SOLO el CRC, sin datos
print("[*] INNOVACIÓN 72: Solo CRC")
s = requests.Session()
s.get(base_url)

for addr, cmd in [(0xE1, 0xFF), (0xA1, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    # Solo enviar el CRC
    packet = struct.pack('<H', crc).hex().upper()
    print(f"  CRC only for {addr:02X}{cmd:02X}: {packet}")
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})

time.sleep(3)
r = s.get(base_url)
if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 73: Probar diferentes voltage values si es un parámetro
print("\n[*] INNOVACIÓN 73: Añadir voltage parameter")
s = requests.Session()
s.get(base_url)

for volt in ['1.8', '3.3', '5.0', '5.5']:
    data = bytes([0xE1, 0xFF])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    r = s.post(f'{base_url}/transmit', data={
        'freq': '433.92',
        'mod': 'ASK',
        'bits': '1',
        'msg': packet,
        'voltage': volt
    })

time.sleep(2)
r = s.get(base_url)
if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 74: Probar parámetros de radio comunes
print("\n[*] INNOVACIÓN 74: Parámetros de radio adicionales")
s = requests.Session()
s.get(base_url)

data = bytes([0xE1, 0xFF])
crc = crc16(data)
packet = (data + struct.pack('<H', crc)).hex().upper()

params_list = [
    {'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet, 'power': '10'},
    {'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet, 'bandwidth': '200'},
    {'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet, 'deviation': '5'},
    {'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet, 'preamble': 'AA'},
    {'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet, 'syncword': '2DD4'},
]

for params in params_list:
    r = s.post(f'{base_url}/transmit', data=params)

time.sleep(3)
r = s.get(base_url)
if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 75: Probar diferentes sample rates
print("\n[*] INNOVACIÓN 75: Sample rate")
s = requests.Session()
s.get(base_url)

data = bytes([0xE1, 0xFF])
crc = crc16(data)
packet = (data + struct.pack('<H', crc)).hex().upper()

for rate in ['250000', '500000', '1000000', '2000000']:
    r = s.post(f'{base_url}/transmit', data={
        'freq': '433.92',
        'mod': 'ASK',
        'bits': '1',
        'msg': packet,
        'samplerate': rate
    })

time.sleep(2)
r = s.get(base_url)
if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

print("\n[*] Innovaciones 72-75 completadas")
