#!/usr/bin/env python3

from pwn import *

context.log_level = 'debug'

conn = remote('svc.pwnable.xyz', 30004)

print("=== Recibiendo prompt ===")
data1 = conn.recvuntil(b': ')
print(f"Recibido: {data1}")

print("\n=== Enviando 'y' ===")
conn.sendline(b'y')

print("=== Recibiendo Name prompt ===")
data2 = conn.recvuntil(b': ')
print(f"Recibido: {data2}")

print("\n=== Enviando 'TestName' ===")
conn.sendline(b'TestName')

print("=== Recibiendo respuesta final ===")
data3 = conn.recvall(timeout=3)
print(f"Recibido: {data3}")
print(f"Hex: {data3.hex()}")

conn.close()