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

# INNOVACIÓN 167: Probar enviar SOLO el mensaje sin otros parámetros
print("[*] INNOVACIÓN 167: Only msg parameter")
s = requests.Session()
s.get(base_url)

for addr, cmd in [(0xE1, 0xFF), (0xA1, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    
    # Solo msg
    r = s.post(f'{base_url}/transmit', data={'msg': packet})
    print(f"  Only msg {addr:02X}{cmd:02X}: {r.status_code}")

# INNOVACIÓN 168: Reverse byte order completamente
print("\n[*] INNOVACIÓN 168: Completely reversed bytes")
s = requests.Session()
s.get(base_url)

for addr, cmd in [(0xE1, 0xFF), (0xA1, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet_bytes = data + struct.pack('<H', crc)
    
    # Reverse TODOS los bytes
    reversed_bytes = packet_bytes[::-1]
    packet_hex = reversed_bytes.hex().upper()
    
    print(f"  Reversed {addr:02X}{cmd:02X}: {packet_hex}")
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet_hex})

time.sleep(3)
r = s.get(base_url)
if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 169: ¿Y si msg debe ser un array de bytes?
print("\n[*] INNOVACIÓN 169: msg as byte array")
s = requests.Session()
s.get(base_url)

data = bytes([0xE1, 0xFF])
crc = crc16(data)
packet_bytes = data + struct.pack('<H', crc)

# Como array JSON
import json
byte_array = list(packet_bytes)
msg_array = json.dumps(byte_array)

r = s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': msg_array})
print(f"Array msg: {r.status_code}")

if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 170: Enviar como base64 decoded bytes
print("\n[*] INNOVACIÓN 170: Base64 encoding")
import base64

packet_b64 = base64.b64encode(packet_bytes).decode()
r = s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet_b64})

time.sleep(2)
r = s.get(base_url)
if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

print("\n[*] Innovaciones 167-170 completadas")
