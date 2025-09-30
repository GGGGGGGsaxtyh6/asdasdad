#!/usr/bin/env python3

from pwn import *

conn = remote('svc.pwnable.xyz', 30004)

conn.recvuntil(b'[y/N]: ')
conn.sendline(b'N')

# Ver qué pasa
data = conn.recvall(timeout=3)
log.info(f"Respuesta: {data}")

if b'FLAG{' in data:
    log.success("¡FLAG encontrada!")

conn.close()