#!/usr/bin/env python3
from pwn import *

# Conectar al servicio
io = remote('94.237.53.81', 59575)

# Recibir datos iniciales
try:
    data = io.recvuntil(b'\n', timeout=5)
    print(f"Received: {data}")
except:
    print("No initial data")

# Interactuar
io.interactive()
