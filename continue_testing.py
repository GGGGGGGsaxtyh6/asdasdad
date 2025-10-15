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

# INNOVACIÓN 94: Enviar paquetes en lowercase
print("[*] INNOVACIÓN 94: Lowercase hex")
s = requests.Session()
s.get(base_url)

for addr, cmd in [(0xE1, 0xFF), (0xA1, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().lower()  # LOWERCASE
    print(f"  {packet}")
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})

time.sleep(2)
r = s.get(base_url)
if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 95: Probar con espacios entre bytes
print("\n[*] INNOVACIÓN 95: Espacios entre bytes")
s = requests.Session()
s.get(base_url)

data = bytes([0xE1, 0xFF])
crc = crc16(data)
packet = (data + struct.pack('<H', crc)).hex().upper()
spaced_packet = ' '.join([packet[i:i+2] for i in range(0, len(packet), 2)])
print(f"  Spaced: {spaced_packet}")

s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': spaced_packet})

time.sleep(2)
r = s.get(base_url)
if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 96: Probar NRZ encoding (sin retorno a zero)
print("\n[*] INNOVACIÓN 96: Diferentes encodings de bits")
encodings = {
    'NRZ': lambda b: '1' if b else '0',
    'NRZI': lambda b, prev: str(int(b != prev)),
    'Manchester': lambda b: '10' if b else '01',
}

for enc_name, enc_func in encodings.items():
    if enc_name == 'NRZI':
        continue  # Skip for now
        
    print(f"\n  Testing {enc_name}")
    s = requests.Session()
    s.get(base_url)
    
    data = bytes([0xE1, 0xFF])
    crc = crc16(data)
    packet_bytes = data + struct.pack('<H', crc)
    
    # Convertir a bits
    bits_str = ''.join(f'{b:08b}' for b in packet_bytes)
    
    # Aplicar encoding
    if enc_name == 'Manchester':
        encoded = ''.join(enc_func(int(b)) for b in bits_str)
        # Convertir de vuelta a hex
        encoded_bytes = [int(encoded[i:i+8], 2) for i in range(0, len(encoded), 8)]
        encoded_hex = ''.join(f'{b:02X}' for b in encoded_bytes)
        print(f"    Encoded: {encoded_hex[:40]}")
        
        s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': encoded_hex})
    
    time.sleep(2)
    r = s.get(base_url)
    if 'HTB{' in r.text:
        print(f'[SUCCESS with {enc_name}]', re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 97: Probar con diferentes formatos numéricos
print("\n[*] INNOVACIÓN 97: Formato decimal en lugar de hex")
s = requests.Session()
s.get(base_url)

data = bytes([0xE1, 0xFF])
crc = crc16(data)
packet_bytes = data + struct.pack('<H', crc)

# Como decimales separados por comas
decimal_msg = ','.join(str(b) for b in packet_bytes)
print(f"  Decimal: {decimal_msg}")

r = s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': decimal_msg})
if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

print("\n[*] Innovaciones 94-97 completadas")
