#!/usr/bin/env python3
from pwn import *
import time

context.log_level = 'debug'

try:
    print("[*] Connecting to server...")
    conn = remote('94.237.57.211', 55233, timeout=10)
    
    # Receive menu
    print("[*] Receiving menu...")
    menu = conn.recvuntil(b'>', timeout=5)
    print(menu.decode())
    
    # Send option 1 to get connection info
    print("[*] Sending option 1...")
    conn.sendline(b'1')
    
    # Receive connection info
    info = conn.recvall(timeout=5)
    print(info.decode())
    
    conn.close()
    
except Exception as e:
    print(f"[-] Error: {e}")
    import traceback
    traceback.print_exc()
