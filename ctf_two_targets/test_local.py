#!/usr/bin/env python3
from pwn import *

context.log_level = 'debug'

# Configurar el proceso
p = process('./image/challenge/challenge')

# Ver el menú
print(p.recvuntil(b'> '))

# Opción 1: Change name
p.sendline(b'1')
print(p.recvuntil(b': '))
p.sendline(b'AAAA')

print(p.recvuntil(b'> '))

# Opción 2: Change nationality
p.sendline(b'2')
print(p.recvuntil(b': '))
p.sendline(b'BBBB')

print(p.recvuntil(b'> '))

# Opción 3: Change age
p.sendline(b'3')
print(p.recvuntil(b': '))
p.sendline(b'25')

print(p.recvuntil(b'> '))

# Opción 4: Get shell (llama a auth)
p.sendline(b'4')

p.interactive()