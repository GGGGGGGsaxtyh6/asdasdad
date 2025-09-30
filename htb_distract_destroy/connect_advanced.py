#!/usr/bin/env python3
from pwn import *

context.log_level = 'debug'

host = "94.237.57.211"
port = 55233

io = remote(host, port)

# Intentar diferentes estrategias para recibir datos
print("\n=== Strategy 1: recv with timeout ===")
try:
    data = io.recv(4096, timeout=3)
    print(f"Received: {repr(data)}")
except:
    print("Failed")

print("\n=== Strategy 2: recvuntil specific text ===")
try:
    io.sendline(b'1')
    data = io.recvuntil([b'rpc', b'RPC', b'http', b'0x'], timeout=5)
    print(f"Received: {repr(data)}")
except:
    print("Failed")

print("\n=== Strategy 3: recvrepeat ===")
try:
    data = io.recvrepeat(timeout=3)
    print(f"Received: {repr(data)}")
except:
    print("Failed")

print("\n=== Strategy 4: clean ===")
try:
    data = io.clean(timeout=3)
    print(f"Received: {repr(data)}")
except:
    print("Failed")

io.close()
