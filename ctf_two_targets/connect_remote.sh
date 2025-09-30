#!/bin/bash
# Script para conectar al servidor remoto
timeout 30s python3 -c "
from pwn import *
context.log_level = 'info'

p = remote('svc.pwnable.xyz', 30031)
print('[+] Conectado al servidor remoto')
print(p.recvuntil(b'> ').decode())

# Aquí puedes agregar comandos de prueba
p.interactive()
"