#!/usr/bin/env python3

from pwn import *

conn = remote('svc.pwnable.xyz', 30004)

# Recibir prompt
log.info("Recibiendo...")
data = conn.recvuntil(b'[y/N]: ', timeout=3)
log.info(f"Recibido: {data}")

# Responder y
conn.sendline(b'y')

# Recibir "Name:"
data = conn.recvuntil(b'Name: ', timeout=3)
log.info(f"Recibido: {data}")

# Enviar nombre
conn.sendline(b'test')

# Recibir respuesta
data = conn.recvall(timeout=3)
log.info(f"Respuesta final: {data}")

conn.close()