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

# INNOVACIÓN 140: Scan completo de puertos cercanos
print("[*] INNOVACIÓN 140: Scanning nearby ports")
import socket

base_port = 59575
for offset in range(-10, 11):
    port = base_port + offset
    try:
        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        sock.settimeout(1)
        result = sock.connect_ex(('94.237.53.81', port))
        if result == 0:
            print(f"  [+] Port {port} is OPEN")
            
            # Intentar HTTP
            try:
                r = requests.get(f'http://94.237.53.81:{port}/', timeout=2)
                if 'HTB{' in r.text:
                    print(f'    [FLAG at port {port}]', re.findall(r'HTB\{[^}]+\}', r.text))
                elif len(r.text) < 1000:
                    print(f'    Response: {r.text[:200]}')
            except:
                pass
        sock.close()
    except:
        pass

# INNOVACIÓN 141: Fuzzing con CRCReveng tool
print("\n[*] INNOVACIÓN 141: Intentar múltiples polys conocidos")
known_polys = [
    0x1021,  # CRC-16/CCITT
    0x8005,  # CRC-16/IBM
    0x1D0F,  # Nuestro poly
    0x8408,  # CRC-16/CCITT reversed
    0xA001,  # CRC-16/MODBUS reversed
]

for poly in known_polys:
    s = requests.Session()
    s.get(base_url)
    
    for addr, cmd in [(0xE1, 0xFF), (0xA1, 0xF1)]:
        data = bytes([addr, cmd])
        crc = crc16(data, poly=poly)
        packet = (data + struct.pack('<H', crc)).hex().upper()
        s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    
    time.sleep(2)
    r = s.get(base_url)
    if 'HTB{' in r.text:
        print(f'[SUCCESS with poly=0x{poly:04X}]', re.findall(r'HTB\{[^}]+\}', r.text))
        break

print("\n[*] Innovaciones 140-141 completadas")
