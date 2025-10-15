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

# INNOVACIÓN 58: Enviar paquetes correctos pero esperar MUCHO más tiempo
print("[*] INNOVACIÓN 58: Esperar 30 segundos después de enviar")
s = requests.Session()
s.get(base_url)

for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    print(f"Sent: {packet}")

print("[*] Esperando 30 segundos...")
for i in range(30):
    time.sleep(1)
    r = s.get(f'{base_url}/updates')
    
    # Buscar cualquier cambio interesante
    if i % 5 == 0:
        print(f"  [{i}s] {r.text[:150]}")
    
    if 'HTB{' in r.text:
        print(f"\n[SUCCESS at {i}s]")
        print(re.findall(r'HTB\{[^}]+\}', r.text))
        break
    
    # También verificar la página principal
    if i % 10 == 0:
        r2 = s.get(base_url)
        if 'HTB{' in r2.text:
            print(f"\n[SUCCESS in main page at {i}s]")
            print(re.findall(r'HTB\{[^}]+\}', r2.text))
            break

# INNOVACIÓN 59: Descargar capture después de enviar comandos
print("\n[*] INNOVACIÓN 59: Descargar y analizar nueva captura")
r = s.get(f'{base_url}/capture')
print(f"Capture size: {len(r.content)} bytes")

# Buscar patrones HTB en la captura
capture_text = r.content.hex().upper()
if 'HTB' in capture_text or '485442' in capture_text:  # HTB en hex
    print("[+] HTB pattern found in capture!")
    
# Buscar como texto también
try:
    text = r.content.decode('latin-1', errors='ignore')
    flags = re.findall(r'HTB\{[^}]+\}', text)
    if flags:
        print(f"[SUCCESS] Flags in capture: {flags}")
except:
    pass

print("\n[*] Quick tests done")
