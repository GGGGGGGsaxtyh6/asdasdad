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

# INNOVACIÓN 76: Probar absolutamente TODAS las combinaciones de freq, mod, bits
print("[*] INNOVACIÓN 76: Todas las combinaciones de parámetros RF")

mods = ['ASK', 'FSK', 'GFSK', 'OOK', 'MSK', 'PSK', 'QPSK', 'BPSK']
bits_list = ['1', '2', '4', '8']
freqs = ['433.92', '434.00', '433.50']

count = 0
for mod in mods:
    for bits in bits_list:
        for freq in freqs:
            s = requests.Session()
            s.get(base_url)
            
            # Enviar un paquete de test
            data = bytes([0xE1, 0xFF])
            crc = crc16(data)
            packet = (data + struct.pack('<H', crc)).hex().upper()
            
            r = s.post(f'{base_url}/transmit', data={
                'freq': freq,
                'mod': mod,
                'bits': bits,
                'msg': packet
            })
            
            count += 1
            if count % 10 == 0:
                print(f"  [{count}] Tested {mod}/{bits}/{freq}")
            
            # Verificar
            r = s.get(base_url)
            sensor_states = re.findall(r'spinner-grow (text-\w+)', r.text)
            
            if sensor_states != ['text-warning', 'text-warning', 'text-warning', 'text-warning', 'text-success', 'text-success', 'text-success']:
                print(f"\n[!!!] STATE CHANGED with {mod}/{bits}/{freq}!")
                print(f"      States: {sensor_states}")
                
            if 'HTB{' in r.text:
                print(f"\n[SUCCESS] with {mod}/{bits}/{freq}")
                print(re.findall(r'HTB\{[^}]+\}', r.text))
                break

print(f"\n[*] Tested {count} combinations")
