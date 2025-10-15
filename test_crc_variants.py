#!/usr/bin/env python3
import requests
import struct
import time

base_url = 'http://94.237.53.81:59575'

def crc16_variant1(data, poly=0x1021, init=0x1D0F):
    """CRC-16 CCITT con init custom"""
    crc = init
    for byte in data:
        crc ^= (byte << 8)
        for _ in range(8):
            if crc & 0x8000:
                crc = ((crc << 1) ^ poly) & 0xFFFF
            else:
                crc = (crc << 1) & 0xFFFF
    return crc

def crc16_variant2(data, poly=0x1D0F, init=0xFFFF):
    """CRC-16 con poly=0x1D0F e init=0xFFFF"""
    crc = init
    for byte in data:
        crc ^= (byte << 8)
        for _ in range(8):
            if crc & 0x8000:
                crc = ((crc << 1) ^ poly) & 0xFFFF
            else:
                crc = (crc << 1) & 0xFFFF
    return crc

def crc16_variant3(data, poly=0x1D0F, init=0x0000):
    """CRC-16 con XOR final"""
    crc = init
    for byte in data:
        crc ^= (byte << 8)
        for _ in range(8):
            if crc & 0x8000:
                crc = ((crc << 1) ^ poly) & 0xFFFF
            else:
                crc = (crc << 1) & 0xFFFF
    return crc ^ 0xFFFF  # XOR final

# Test con E1 FF
data = bytes([0xE1, 0xFF])

variants = [
    ('Variant 1 (CCITT poly, init=0x1D0F)', crc16_variant1),
    ('Variant 2 (poly=0x1D0F, init=0xFFFF)', crc16_variant2),
    ('Variant 3 (poly=0x1D0F, XOR final)', crc16_variant3),
]

print("[*] Probando variantes de CRC para E1 FF:")
for name, func in variants:
    crc = func(data)
    packet = data + struct.pack('<H', crc)
    print(f"  {name}: {packet.hex().upper()}")

# Probar cada variante
s = requests.Session()
s.get(base_url)

for name, crc_func in variants:
    print(f"\n[*] Probando {name}...")
    
    # Enviar paquetes con esta variante
    for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
        data = bytes([addr, cmd])
        crc = crc_func(data)
        packet = (data + struct.pack('<H', crc)).hex().upper()
        
        s.post(f'{base_url}/transmit', data={
            'freq': '433.92',
            'mod': 'ASK',
            'bits': '1',
            'msg': packet
        })
        time.sleep(0.1)
    
    time.sleep(2)
    
    # Verificar cambios
    r = s.get(base_url)
    import re
    
    # Buscar cambios de estado
    if 'text-danger' in r.text or 'text-secondary' in r.text:
        print("    [+] ¡Cambio de estado detectado!")
        
        # Buscar flag
        flags = re.findall(r'HTB\{[^}]+\}', r.text)
        if flags:
            print(f"\n{'='*60}")
            print(f"[SUCCESS] FLAG CON {name}!")
            print(f"{'='*60}")
            for flag in flags:
                print(flag)
            print(f"{'='*60}")
            break
    else:
        print("    [-] Sin cambios")
