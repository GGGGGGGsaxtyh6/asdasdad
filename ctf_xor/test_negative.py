#!/usr/bin/env python3
from pwn import *

context.log_level = 'info'

# Test con índice negativo
p = process('./image/challenge/challenge')
p.recvuntil(b'> ')

# Probar con -1 (en signed, pero se interpreta como unsigned)
print("[*] Test: 1 1 -1")
p.sendline(b'1 1 -1')

try:
    response = p.recvall(timeout=2)
    print(response.decode())
except:
    pass

p.close()