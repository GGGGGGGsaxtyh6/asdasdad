#!/usr/bin/env python3
import requests
import struct
import time
import re

def crc32_stm32(data, poly=0x04C11DB7, init=0x1D0F):
    '''STM32 hardware CRC-32 con init personalizado'''
    crc = init
    for byte in data:
        crc ^= (byte << 24)
        for _ in range(8):
            if crc & 0x80000000:
                crc = ((crc << 1) ^ poly) & 0xFFFFFFFF
            else:
                crc = (crc << 1) & 0xFFFFFFFF
    return crc

base_url = 'http://94.237.53.81:59575'

# INNOVACIÓN 71: Probar con CRC-32 en lugar de CRC-16
print("[*] INNOVACIÓN 71: CRC-32 STM32")
s = requests.Session()
s.get(base_url)

for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc32_stm32(data)
    packet = (data + struct.pack('<I', crc)).hex().upper()
    print(f"  {addr:02X}{cmd:02X}: {packet}")
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    time.sleep(0.1)

time.sleep(3)
r = s.get(base_url)
sensor_states = re.findall(r'spinner-grow (text-\w+)', r.text)
print(f"States: {sensor_states}")

if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))
else:
    print('[-] No flag con CRC-32')
