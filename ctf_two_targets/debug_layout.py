#!/usr/bin/env python3
"""
Debug del layout de memoria
"""
from pwn import *

context.log_level = 'debug'

p = process('./image/challenge/challenge')

# Opción 2 primero
p.recvuntil(b'> ')
p.sendline(b'2')
p.recvuntil(b': ')

# Enviar un patrón reconocible
# nationality empieza en rbp-0x20
# rbp-0x10 está 16 bytes después del inicio
pattern = cyclic(24)
print(f"[*] Enviando pattern: {pattern.hex()}")
p.sendline(pattern)

p.recvuntil(b'> ')

# Opción 3 para ver qué valor lee
p.sendline(b'3')
received = p.recvuntil(b': ')
print(f"[*] Recibido: {received}")

# Extraer el valor que muestra
# El formato es "age: <valor>\n: "
lines = received.decode().split('\n')
for line in lines:
    print(f"Line: {repr(line)}")

p.close()