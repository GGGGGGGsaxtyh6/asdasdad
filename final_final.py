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

# INNOVACIÓN 166: Probar con parámetros EXACTOS de dispositivos RF reales
print("[*] INNOVACIÓN 166: Real RF device parameters")

# Parámetros típicos para 433 MHz RF
configs = [
    {'freq': '433.920', 'mod': 'ASK', 'bits': '8', 'deviation': '0'},
    {'freq': '433.920', 'mod': 'FSK', 'bits': '1', 'deviation': '25'},
    {'freq': '433.920', 'mod': 'FSK', 'bits': '1', 'deviation': '50'},
    {'freq': '433.920', 'mod': 'GFSK', 'bits': '1', 'deviation': '25'},
    {'freq': '433.920', 'mod': 'OOK', 'bits': '1'},
    {'freq': '433.92', 'mod': '2-FSK', 'bits': '1'},
]

for config in configs:
    print(f"\n  Testing {config}")
    s = requests.Session()
    s.get(base_url)
    
    for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
        data = bytes([addr, cmd])
        crc = crc16(data)
        packet = (data + struct.pack('<H', crc)).hex().upper()
        
        payload = config.copy()
        payload['msg'] = packet
        
        s.post(f'{base_url}/transmit', data=payload)
    
    time.sleep(3)
    r = s.get(base_url)
    sensor_states = re.findall(r'spinner-grow (text-\w+)', r.text)
    
    if sensor_states != ['text-warning'] * 4 + ['text-success'] * 3:
        print(f"    [!!!] STATE CHANGED!")
        print(f"         States: {sensor_states}")
    
    if 'HTB{' in r.text:
        print(f"    [SUCCESS]")
        print(re.findall(r'HTB\{[^}]+\}', r.text))
        break

print("\n[*] Innovación 166 completada")
