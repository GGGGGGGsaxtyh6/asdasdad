#!/usr/bin/env python3
import requests
import struct
import time
import re

# STM32 usa CRC-32 con polinomio 0x04C11DB7
def crc32_stm32_proper(data, init=0x1D0F):
    '''STM32 hardware CRC-32 real'''
    crc = init
    poly = 0x04C11DB7
    
    # STM32 procesa palabra por palabra (32-bit)
    # Pero aquí solo tenemos bytes
    for i in range(0, len(data), 4):
        word_bytes = data[i:i+4]
        # Pad con zeros si es necesario
        while len(word_bytes) < 4:
            word_bytes += b'\x00'
        
        # Big-endian word
        word = struct.unpack('>I', word_bytes)[0]
        
        crc ^= word
        for _ in range(32):
            if crc & 0x80000000:
                crc = ((crc << 1) ^ poly) & 0xFFFFFFFF
            else:
                crc = (crc << 1) & 0xFFFFFFFF
    
    return crc

base_url = 'http://94.237.53.81:59575'

# Test
data = bytes([0xE1, 0xFF])
crc = crc32_stm32_proper(data)
print(f"STM32 CRC-32 for E1FF: 0x{crc:08X}")

# INNOVACIÓN 129: STM32 CRC-32 real con init=0x1D0F
print("\n[*] INNOVACIÓN 129: STM32 CRC-32 proper implementation")
s = requests.Session()
s.get(base_url)

for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc32_stm32_proper(data)
    packet = (data + struct.pack('<I', crc)).hex().upper()
    print(f"  {addr:02X}{cmd:02X}: {packet}")
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    time.sleep(0.1)

time.sleep(3)
r = s.get(base_url)
sensor_states = re.findall(r'spinner-grow (text-\w+)', r.text)
print(f"\nStates: {sensor_states}")

if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))
else:
    print("[-] No flag")

# INNOVACIÓN 130: STM32 CRC-32 con init=0xFFFFFFFF y XOR final
def crc32_stm32_variant(data, init=0x1D0F, xorout=0xFFFFFFFF):
    crc = init
    poly = 0x04C11DB7
    
    for i in range(0, len(data), 4):
        word_bytes = data[i:i+4]
        while len(word_bytes) < 4:
            word_bytes += b'\x00'
        word = struct.unpack('>I', word_bytes)[0]
        
        crc ^= word
        for _ in range(32):
            if crc & 0x80000000:
                crc = ((crc << 1) ^ poly) & 0xFFFFFFFF
            else:
                crc = (crc << 1) & 0xFFFFFFFF
    
    return crc ^ xorout

print("\n[*] INNOVACIÓN 130: STM32 CRC-32 with XOR final")
s = requests.Session()
s.get(base_url)

for addr, cmd in [(0xE1, 0xFF), (0xA1, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc32_stm32_variant(data)
    packet = (data + struct.pack('<I', crc)).hex().upper()
    print(f"  {addr:02X}{cmd:02X}: {packet}")
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})

time.sleep(3)
r = s.get(base_url)
if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

print("\n[*] Innovaciones 129-130 completadas")
