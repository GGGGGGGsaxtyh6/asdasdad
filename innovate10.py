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

# INNOVACIÓN 41: Enviar TODOS los paquetes juntos en un solo mensaje largo
print("[*] INNOVACIÓN 41: Un mensaje largo con todos los paquetes concatenados")
s = requests.Session()
s.get(base_url)

all_packets = []
for addr, cmd in [(0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1), (0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    all_packets.append(packet)

mega_packet = ''.join(all_packets)
print(f"[*] Mega packet ({len(mega_packet)} chars): {mega_packet}")

r = s.post(f'{base_url}/transmit', data={
    'freq': '433.92',
    'mod': 'ASK',
    'bits': '1',
    'msg': mega_packet
})

time.sleep(3)
r = s.get(base_url)
if 'HTB{' in r.text:
    print("[SUCCESS]", re.findall(r'HTB\{[^}]+\}', r.text))
else:
    # Ver updates
    r = s.get(f'{base_url}/updates')
    if 'HTB{' in r.text:
        print("[SUCCESS in updates]", re.findall(r'HTB\{[^}]+\}', r.text))
    else:
        print("[-] No flag")

# INNOVACIÓN 42: Probar diferentes separadores entre paquetes
print("\n[*] INNOVACIÓN 42: Paquetes separados por delimitadores")
s = requests.Session()
s.get(base_url)

separators = ['00', 'FF', 'AA', '55', '0000', 'FFFF']
for sep in separators:
    packets_with_sep = sep.join(all_packets)
    r = s.post(f'{base_url}/transmit', data={
        'freq': '433.92',
        'mod': 'ASK',
        'bits': '1',
        'msg': packets_with_sep
    })
    time.sleep(1)
    r = s.get(base_url)
    if 'HTB{' in r.text:
        print(f"[SUCCESS with separator {sep}]", re.findall(r'HTB\{[^}]+\}', r.text))
        break

# INNOVACIÓN 43: Enviar solo las direcciones sin comandos
print("\n[*] INNOVACIÓN 43: Solo direcciones")
s = requests.Session()
s.get(base_url)

for addr in [0xA1, 0xA2, 0xA3, 0xA4, 0xE1, 0xE2, 0xE3]:
    data = bytes([addr])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})

time.sleep(2)
r = s.get(base_url)
if 'HTB{' in r.text:
    print("[SUCCESS]", re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 44: Probar con 3 bytes: ADDR + CMD + EXTRA
print("\n[*] INNOVACIÓN 44: Paquetes de 3 bytes: [ADDR][CMD][00]")
s = requests.Session()
s.get(base_url)

for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
    data = bytes([addr, cmd, 0x00])  # Extra byte
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})

time.sleep(2)
r = s.get(base_url)
if 'HTB{' in r.text:
    print("[SUCCESS]", re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 45: XOR encoding del mensaje
print("\n[*] INNOVACIÓN 45: XOR encoding con clave 0xAA")
s = requests.Session()
s.get(base_url)

for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
    data = bytes([addr ^ 0xAA, cmd ^ 0xAA])  # XOR
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})

time.sleep(2)
r = s.get(base_url)
if 'HTB{' in r.text:
    print("[SUCCESS]", re.findall(r'HTB\{[^}]+\}', r.text))
else:
    print("[-] No flag")

print("\n[*] Innovaciones 41-45 completadas")
