#!/usr/bin/env python3
import requests
import struct
import time
import re
import itertools

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

# INNOVACIÓN 91: ¿Y si el formato es completamente diferente?
# Tal vez necesito enviar TODOS los 7 dispositivos en UN SOLO paquete
print("[*] INNOVACIÓN 91: Multi-device packet format")
s = requests.Session()
s.get(base_url)

# Formato: [NumDevices][Dev1_Addr][Dev1_Cmd][Dev2_Addr][Dev2_Cmd]...[CRC]
all_devices = [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]

multi_packet_data = bytes([len(all_devices)])
for addr, cmd in all_devices:
    multi_packet_data += bytes([addr, cmd])

crc = crc16(multi_packet_data)
multi_packet = (multi_packet_data + struct.pack('<H', crc)).hex().upper()

print(f"Multi-device packet: {multi_packet}")
s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': multi_packet})

time.sleep(3)
r = s.get(base_url)
if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 92: Tal vez necesito enviar un comando de "inicio" primero
print("\n[*] INNOVACIÓN 92: Comando de inicio/handshake")
s = requests.Session()
s.get(base_url)

# Probar comandos de control comunes
control_cmds = [
    bytes([0x00, 0x01]),  # START
    bytes([0xFF, 0x00]),  # INIT
    bytes([0x01, 0x00]),  # BEGIN
    bytes([0xAA, 0x55]),  # SYNC
    bytes([0x55, 0xAA]),  # SYNC2
]

for ctrl_data in control_cmds:
    crc = crc16(ctrl_data)
    packet = (ctrl_data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})

# Ahora enviar comandos normales
for addr, cmd in [(0xE1, 0xFF), (0xA1, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    packet = (data + struct.pack('<H', crc)).hex().upper()
    s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})

time.sleep(3)
r = s.get(base_url)
if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

# INNOVACIÓN 93: ¿Y si el CRC se calcula sobre TODOS los datos incluyendo freq y mod?
print("\n[*] INNOVACIÓN 93: CRC sobre parámetros completos")
s = requests.Session()
s.get(base_url)

freq_str = '433.92'
mod_str = 'ASK'
bits_str = '1'

# Crear mensaje completo
full_msg = f'{freq_str}{mod_str}{bits_str}'.encode()
full_msg += bytes([0xE1, 0xFF])

crc = crc16(full_msg)
packet = bytes([0xE1, 0xFF]) + struct.pack('<H', crc)
packet_hex = packet.hex().upper()

print(f"Packet with full CRC: {packet_hex}")
s.post(f'{base_url}/transmit', data={'freq': freq_str, 'mod': mod_str, 'bits': bits_str, 'msg': packet_hex})

time.sleep(2)
r = s.get(base_url)
if 'HTB{' in r.text:
    print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))

print("\n[*] Innovaciones 91-93 completadas")
