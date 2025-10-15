#!/usr/bin/env python3
from pwn import *
import struct
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

def crc8(data):
    crc = 0x00
    for byte in data:
        crc ^= byte
        for _ in range(8):
            if crc & 0x80:
                crc = ((crc << 1) ^ 0x07) & 0xFF
            else:
                crc = (crc << 1) & 0xFF
    return crc

# Probar diferentes formatos
formats = []

# Formato 1: Solo comando + CRC16
for cmd in [0xFF, 0xF1]:
    data = bytes([cmd])
    crc = crc16(data)
    formats.append(("solo_cmd_crc16_le", data + struct.pack('<H', crc)))
    formats.append(("solo_cmd_crc16_be", data + struct.pack('>H', crc)))

# Formato 2: Dirección + Comando + CRC16
for addr, cmd in [(0xE1, 0xFF), (0xA1, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc16(data)
    formats.append((f"addr{addr:02x}_cmd_crc16_le", data + struct.pack('<H', crc)))
    formats.append((f"addr{addr:02x}_cmd_crc16_be", data + struct.pack('>H', crc)))

# Formato 3: Comando + Dirección + CRC16 (orden inverso)
for cmd, addr in [(0xFF, 0xE1), (0xF1, 0xA1)]:
    data = bytes([cmd, addr])
    crc = crc16(data)
    formats.append((f"cmd_addr{addr:02x}_crc16_le", data + struct.pack('<H', crc)))

# Formato 4: Con CRC8
for cmd in [0xFF, 0xF1]:
    data = bytes([cmd])
    crc = crc8(data)
    formats.append(("cmd_crc8", data + bytes([crc])))

for addr, cmd in [(0xE1, 0xFF), (0xA1, 0xF1)]:
    data = bytes([addr, cmd])
    crc = crc8(data)
    formats.append((f"addr{addr:02x}_cmd_crc8", data + bytes([crc])))

# Probar cada formato
print(f"[*] Probando {len(formats)} formatos diferentes...")

for name, packet in formats[:10]:  # Probar los primeros 10
    print(f"\n[*] Probando formato: {name}")
    print(f"    Paquete: {packet.hex()}")
    
    try:
        io = remote('94.237.53.81', 59575, level='error')
        time.sleep(0.2)
        
        # Enviar paquete
        io.send(packet)
        time.sleep(0.5)
        
        # Intentar recibir respuesta
        try:
            response = io.recv(timeout=2)
            if response and len(response) > 0:
                print(f"    [+] RESPUESTA: {response.hex()}")
                print(f"        ASCII: {response.decode('latin-1', errors='ignore')}")
                
                if b'HTB{' in response:
                    print(f"\n{'='*60}")
                    print(f"[SUCCESS] FLAG ENCONTRADA con formato: {name}")
                    print(f"Paquete: {packet.hex()}")
                    print(f"Flag: {response.decode()}")
                    print(f"{'='*60}")
                    io.close()
                    exit(0)
        except:
            pass
        
        io.close()
        
    except Exception as e:
        print(f"    [-] Error: {e}")
        continue

print("\n[-] Ningún formato produjo resultados")
