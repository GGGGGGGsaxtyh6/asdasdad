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

# Probar diferentes configuraciones
configs = [
    {'freq': '433.92', 'mod': 'FSK', 'bits': '2'},
    {'freq': '433.92', 'mod': 'ASK', 'bits': '8'},
    {'freq': '433.92', 'mod': 'OOK', 'bits': '1'},
]

# Probar con big-endian CRC
packets = []
for addr, cmd in [(0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1), (0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    # Big-endian CRC
    packet_be = (data + struct.pack('>H', crc)).hex().upper()
    packets.append(packet_be)

all_msg = ''.join(packets)

for config in configs:
    print(f"\n[*] Probando config: {config}")
    s = requests.Session()
    s.get(base_url)
    
    # Enviar
    payload = config.copy()
    payload['msg'] = all_msg
    r = s.post(f'{base_url}/transmit', data=payload)
    
    time.sleep(3)
    
    # Verificar
    r = s.get(base_url)
    flags = re.findall(r'HTB\{[^}]+\}', r.text)
    if flags:
        print(f"[SUCCESS] FLAG ENCONTRADA!")
        for flag in flags:
            print(flag)
        break
    
    if 'text-danger' in r.text or 'text-secondary' in r.text:
        print("    [+] Cambio detectado!")
        # Buscar en updates
        r2 = s.get(f'{base_url}/updates')
        flags2 = re.findall(r'HTB\{[^}]+\}', r2.text)
        if flags2:
            print(f"[SUCCESS] FLAG EN /updates!")
            for flag in flags2:
                print(flag)
            break
    else:
        print("    [-] Sin cambios")

print("\n[*] Pruebas completadas")
