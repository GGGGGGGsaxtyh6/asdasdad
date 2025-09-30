#!/usr/bin/env python3
from pwn import *

context.log_level = 'info'

p = process('./image/challenge/challenge')

print(p.recvuntil(b'> '))

# Test 1: Enviar 3 números válidos
print("[*] Test 1: 5 3 1 (5 XOR 3 = 6, guardar en result[1])")
p.sendline(b'5 3 1')
response = p.recvuntil(b'> ')
print(response.decode())

# Test 2: Ver si imprime result[1]
print("[*] Test 2: 0 0 1 (debería fallar por valores 0)")
p.sendline(b'0 0 1')
response = p.recvuntil(b'> ', timeout=1)
print(response.decode() if response else "Sin respuesta")

p.close()