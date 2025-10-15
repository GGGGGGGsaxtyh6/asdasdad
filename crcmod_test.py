#!/usr/bin/env python3
import requests
import struct
import time
import re
import crcmod

base_url = 'http://94.237.53.81:59575'

# INNOVACIÓN 101: Usar crcmod con diferentes configuraciones estándar
print("[*] INNOVACIÓN 101: Testing standard CRC algorithms")

# Probar diferentes CRCs estándar
crc_funcs = {
    'CRC-16': crcmod.mkCrcFun(0x18005, initCrc=0x0000, xorOut=0x0000),
    'CRC-16/CCITT-FALSE': crcmod.mkCrcFun(0x11021, initCrc=0xFFFF, xorOut=0x0000),
    'CRC-16/XMODEM': crcmod.mkCrcFun(0x11021, initCrc=0x0000, xorOut=0x0000),
    'CRC-16/MODBUS': crcmod.mkCrcFun(0x18005, initCrc=0xFFFF, xorOut=0x0000),
    'CRC-16/USB': crcmod.mkCrcFun(0x18005, initCrc=0xFFFF, xorOut=0xFFFF),
    'CRC-16-DNP': crcmod.mkCrcFun(0x13D65, initCrc=0x0000, xorOut=0xFFFF),
}

# Añadir nuestra variante custom
crc_funcs['CRC-16/CUSTOM-1D0F'] = crcmod.mkCrcFun(0x11D0F, initCrc=0x0000, xorOut=0x0000)
crc_funcs['CRC-16/CUSTOM-1D0F-INIT'] = crcmod.mkCrcFun(0x11021, initCrc=0x1D0F, xorOut=0x0000)

data = bytes([0xE1, 0xFF])

print("\nTest packet E1 FF with different CRCs:")
for name, crc_func in crc_funcs.items():
    crc = crc_func(data)
    packet = data + struct.pack('<H', crc)
    print(f"  {name:25s}: {packet.hex().upper()}")

# Probar cada uno
for name, crc_func in crc_funcs.items():
    print(f"\n[*] Testing {name}")
    s = requests.Session()
    s.get(base_url)
    
    for addr, cmd in [(0xE1, 0xFF), (0xE2, 0xFF), (0xE3, 0xFF), (0xA1, 0xF1), (0xA2, 0xF1), (0xA3, 0xF1), (0xA4, 0xF1)]:
        data = bytes([addr, cmd])
        crc = crc_func(data)
        packet = (data + struct.pack('<H', crc)).hex().upper()
        s.post(f'{base_url}/transmit', data={'freq': '433.92', 'mod': 'ASK', 'bits': '1', 'msg': packet})
    
    time.sleep(2)
    r = s.get(base_url)
    sensor_states = re.findall(r'spinner-grow (text-\w+)', r.text)
    
    if sensor_states != ['text-warning'] * 4 + ['text-success'] * 3:
        print(f"  [!!!] STATE CHANGED with {name}!")
        print(f"       States: {sensor_states}")
        
    if 'HTB{' in r.text:
        print(f"  [SUCCESS] Flag found with {name}!")
        print(re.findall(r'HTB\{[^}]+\}', r.text))
        break

print("\n[*] CRCmod testing completed")
