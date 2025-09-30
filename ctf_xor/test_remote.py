#!/usr/bin/env python3
from pwn import *

context.log_level = 'info'

# Conectar al servidor remoto
p = remote('svc.pwnable.xyz', 30029)

print(p.recvuntil(b'> '))

# Test básico
print("[*] Test 1: Operación XOR simple")
p.sendline(b'5 3 1')
response = p.recvuntil(b'> ')
print(response.decode())

# Test con índice negativo
print("[*] Test 2: Índice negativo -1")
p.sendline(b'10 20 -1')
response = p.recvuntil(b'> ', timeout=2)
print(response.decode() if response else "Sin respuesta")

p.close()