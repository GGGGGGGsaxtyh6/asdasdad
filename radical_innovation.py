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

# INNOVACIÓN 143: Analizar respuesta del servidor en detalle
print("[*] INNOVACIÓN 143: Analyzing server responses")
s = requests.Session()
r1 = s.get(base_url)

# Enviar un paquete
data = bytes([0xE1, 0xFF])
crc = crc16(data)
packet = (data + struct.pack('<H', crc)).hex().upper()

r2 = s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})

# Comparar cookies antes y después
print("Cookies before POST:", dict(s.cookies))

# Ver headers de respuesta
print("\nPOST response headers:")
for key, val in r2.headers.items():
    print(f"  {key}: {val}")

# Ver si hay Set-Cookie
if 'Set-Cookie' in r2.headers:
    print(f"[+] New cookie set: {r2.headers['Set-Cookie']}")

# Comparar tamaño de respuesta
print(f"\nResponse sizes: GET={len(r1.text)}, POST={len(r2.text)}")

if r1.text == r2.text:
    print("[-] Response identical")
else:
    print("[+] Response changed!")
    
    # Encontrar diferencias
    for i, (c1, c2) in enumerate(zip(r1.text, r2.text)):
        if c1 != c2:
            print(f"  First diff at position {i}: '{c1}' vs '{c2}'")
            print(f"    Context: ...{r1.text[max(0,i-20):i+20]}...")
            break

# INNOVACIÓN 144: ¿Y si los estados están en un archivo JavaScript generado dinámicamente?
print("\n[*] INNOVACIÓN 144: Looking for dynamic JS")

js_urls = re.findall(r'<script[^>]*src=["\'](/[^"\']+\.js)["\']', r2.text)
print(f"JS files: {js_urls}")

for js_url in js_urls:
    try:
        r = s.get(f'{base_url}{js_url}')
        if r.status_code == 200:
            print(f"\n[+] {js_url}:")
            print(r.text[:500])
            if 'HTB{' in r.text:
                print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))
    except:
        pass

# INNOVACIÓN 145: ¿Hay algún archivo de estado/config?
print("\n[*] INNOVACIÓN 145: State/config files")
state_files = [
    '/state.json',
    '/config.json',
    '/sensors.json',
    '/status.json',
    '/devices.json',
]

for file in state_files:
    try:
        r = s.get(f'{base_url}{file}')
        if r.status_code == 200 and len(r.text) > 10:
            print(f'[+] {file}: {r.text}')
            if 'HTB{' in r.text:
                print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))
    except:
        pass

print("\n[*] Innovaciones 143-145 completadas")
