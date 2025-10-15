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

# INNOVACIÓN 77: Descargar capture múltiples veces y comparar
print("[*] INNOVACIÓN 77: Comparar múltiples capturas")
s = requests.Session()
s.get(base_url)

capture1 = s.get(f'{base_url}/capture').content
time.sleep(2)
capture2 = s.get(f'{base_url}/capture').content

print(f"Capture 1 size: {len(capture1)}")
print(f"Capture 2 size: {len(capture2)}")
print(f"Are equal: {capture1 == capture2}")

# Si son diferentes, hay señales cambiando
if capture1 != capture2:
    print("[+] Captures are different - signals are changing!")

# INNOVACIÓN 78: Enviar comandos y descargar capture inmediatamente
print("\n[*] INNOVACIÓN 78: Enviar y capturar inmediatamente")
s = requests.Session()
s.get(base_url)

# Enviar comando
data = bytes([0xE1, 0xFF])
crc = crc16(data)
packet = (data + struct.pack('<H', crc)).hex().upper()
s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})

# Capturar inmediatamente
capture_after = s.get(f'{base_url}/capture').content

# Comparar
if capture_after != capture1:
    print("[+] Capture changed after transmit!")
    
    # Analizar diferencias
    diff_positions = []
    for i in range(min(len(capture1), len(capture_after))):
        if capture1[i] != capture_after[i]:
            diff_positions.append(i)
    
    if diff_positions:
        print(f"[+] Found {len(diff_positions)} different bytes")
        print(f"    First 10 positions: {diff_positions[:10]}")

# INNOVACIÓN 79: Probar con todos los parámetros vacíos
print("\n[*] INNOVACIÓN 79: Parámetros vacíos")
s = requests.Session()
s.get(base_url)

r = s.post(f'{base_url}/transmit', data={
    'freq': '',
    'mod': '',
    'bits': '',
    'msg': 'E1FF217D'
})
print(f"Empty params response: {r.text[:200]}")

# INNOVACIÓN 80: Overflow en freq parameter
print("\n[*] INNOVACIÓN 80: Integer overflow tests")
s = requests.Session()
s.get(base_url)

overflow_values = [
    '999999999999',
    '-1',
    '2147483647',
    '-2147483648',
    '0',
    '0.0',
]

for val in overflow_values:
    r = s.post(f'{base_url}/transmit', data={
        'freq': val,
        'mod': 'ASK',
        'bits': '1',
        'msg': 'TEST'
    })
    if 'error' in r.text.lower() and len(r.text) < 1000:
        print(f"  {val}: {r.text[:100]}")
    if 'HTB{' in r.text:
        print(f"[SUCCESS] {val}")
        print(re.findall(r'HTB\{[^}]+\}', r.text))

print("\n[*] Innovaciones 77-80 completadas")
