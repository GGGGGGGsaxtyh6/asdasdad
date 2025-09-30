#!/usr/bin/env python3
from pwn import *

context.log_level = 'info'

# Test con varios índices negativos
test_cases = [
    (1, 2, -1, "Índice -1"),
    (1, 2, -5, "Índice -5 (stdin vtable)"),
    (1, 2, -32, "Índice -32 (stdin flags)"),
]

for a, b, idx, desc in test_cases:
    p = process('./image/challenge/challenge')
    p.recvuntil(b'> ')
    
    print(f"\n[*] Test: {desc}")
    print(f"    Enviando: {a} {b} {idx}")
    print(f"    XOR: {a} ^ {b} = {a^b}")
    
    p.sendline(f"{a} {b} {idx}".encode())
    
    try:
        response = p.recv(timeout=1)
        print(f"    Respuesta: {response.decode().strip()}")
    except:
        print("    Sin respuesta (probablemente crasheó)")
    
    p.close()

print("\n[+] Los índices negativos funcionan!")