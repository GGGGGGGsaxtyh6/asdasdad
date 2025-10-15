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

# INNOVACIÓN 68: Probar con delay específico de 0.05 segundos (mencionado en el documento)
print("[*] INNOVACIÓN 68: Delay de 0.05s entre broadcasts")
s = requests.Session()
s.get(base_url)

# Enviar broadcasts primero
data = bytes([0x03])
crc = crc16(data)
packet = (data + struct.pack('<H', crc)).hex().upper()
print(f"Broadcast packet: {packet}")

for i in range(20):
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    time.sleep(0.05)  # Delay exacto mencionado

time.sleep(2)

# Ahora enviar comandos
for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    time.sleep(0.05)

time.sleep(3)
r = s.get(base_url)
if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 69: Probar bytereverse de todo
print("\n[*] INNOVACIÓN 69: Byte-reversed packets")
s = requests.Session()
s.get(base_url)

for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet_bytes = data + struct.pack('<H', crc)
    # Reverse todos los bytes
    packet_reversed = packet_bytes[::-1]
    packet = packet_reversed.hex().upper()
    print(f'  Reversed: {packet}')
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})

time.sleep(3)
r = s.get(base_url)
if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 70: Bit-reversed
print("\n[*] INNOVACIÓN 70: Bit-reversed bytes")
s = requests.Session()
s.get(base_url)

def reverse_bits(byte_val):
    result = 0
    for i in range(8):
        result = (result << 1) | (byte_val & 1)
        byte_val >>= 1
    return result

for addr, cmd in [(0xE1, 0xFF), (0xA1, 0xF1)]:
    addr_rev = reverse_bits(addr)
    cmd_rev = reverse_bits(cmd)
    data = bytes([addr_rev, cmd_rev])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    print(f'  Bit-reversed {addr:02X}{cmd:02X} -> {packet}')
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})

time.sleep(3)
r = s.get(base_url)
if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

print('\n[*] Innovaciones 68-70 completadas')
"