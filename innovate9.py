#!/usr/bin/env python3
import requests
import struct
import time
import re
import itertools

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

# INNOVACIÓN 35: Bruteforce todas las combinaciones de 2 bytes + CRC
print("[*] INNOVACIÓN 35: Bruteforce paquetes de 2 bytes importantes")
s = requests.Session()
s.get(base_url)

# Probar combinaciones específicas que podrían ser importantes
important_bytes = [0x00, 0x01, 0x03, 0xA0, 0xA1, 0xA2, 0xA3, 0xA4, 
                   0xE1, 0xE2, 0xE3, 0xEE, 0xF1, 0xFF, 0x53, 0x91]

tested = set()
for byte1, byte2 in itertools.product(important_bytes, important_bytes):
    if (byte1, byte2) in tested:
        continue
    tested.add((byte1, byte2))
    
    data = bytes([byte1, byte2])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})

print(f"[*] Enviadas {len(tested)} combinaciones")
time.sleep(3)
r = s.get(base_url)
if 'HTB{' in r.text:
    print("[SUCCESS]", re.findall(r'HTB\{[^}]+\}', r.text))
else:
    print("[-] No flag con bruteforce")

# INNOVACIÓN 36: Enviar paquete vacío
print("\n[*] INNOVACIÓN 36: Paquetes vacíos y especiales")
s = requests.Session()
s.get(base_url)

special_msgs = ['', '00', '0000', 'FFFFFFFF', '00000000']
for msg in special_msgs:
    r = s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': msg})
    if 'HTB{' in r.text:
        print(f"[SUCCESS with msg={msg}]", re.findall(r'HTB\{[^}]+\}', r.text))
        break

# INNOVACIÓN 37: Replay attack - capturar y reenviar
print("\n[*] INNOVACIÓN 37: Descargar y analizar capture nuevamente")
r = s.get(f'{base_url}/capture')
# Buscar patrones hex en los primeros bytes
capture_hex = r.content[:1000].hex().upper()
print(f"[*] Capture hex (first 200 chars): {capture_hex[:200]}")

# Buscar secuencias que parezcan paquetes
patterns = re.findall(r'([A-F0-9]{8})', capture_hex)
if patterns:
    print(f"[*] Patrones encontrados: {patterns[:10]}")

# INNOVACIÓN 38: Intentar GET en /transmit
print("\n[*] INNOVACIÓN 38: GET en /transmit")
try:
    r = s.get(f'{base_url}/transmit')
    print(f"[*] GET /transmit: {r.status_code}")
    if 'HTB{' in r.text:
        print("[SUCCESS]", re.findall(r'HTB\{[^}]+\}', r.text))
    else:
        print(r.text[:200])
except Exception as e:
    print(f"[-] Error: {e}")

# INNOVACIÓN 39: Enviar request con parámetros en URL
print("\n[*] INNOVACIÓN 39: Parámetros en URL")
params = {
    'freq': '433.92',
    'mod': 'ASK',
    'bits': '1',
    'msg': 'E1FF217DA1F184FB'
}
r = s.get(f'{base_url}/transmit', params=params)
if 'HTB{' in r.text:
    print("[SUCCESS]", re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 40: JSON payload
print("\n[*] INNOVACIÓN 40: JSON payload")
import json
json_data = {
    'freq': '433.92',
    'mod': 'ASK',
    'bits': '1',
    'msg': 'E1FF217DA1F184FB'
}
r = s.post(f'{base_url}/transmit', json=json_data)
if 'HTB{' in r.text:
    print("[SUCCESS]", re.findall(r'HTB\{[^}]+\}', r.text))

print("\n[*] Innovaciones 35-40 completadas")
