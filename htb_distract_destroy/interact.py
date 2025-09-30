#!/usr/bin/env python3
from pwn import *

context.log_level = 'info'

try:
    conn = remote('94.237.57.211', 55233, timeout=15)
    
    # Try to receive initial data
    try:
        data = conn.recv(timeout=3)
        if data:
            print("[+] Received:", data.decode())
    except:
        print("[!] No initial data")
    
    # Try sending option 1
    print("[*] Sending option 1...")
    conn.sendline(b'1')
    time.sleep(2)
    
    try:
        resp = conn.recv(timeout=3)
        if resp:
            print("[+] Response to 1:", resp.decode())
            # Save to file
            with open('connection_info.txt', 'w') as f:
                f.write(resp.decode())
    except:
        print("[!] No response to option 1")
    
    # Try sending just enter
    conn.sendline(b'')
    time.sleep(1)
    try:
        resp = conn.recv(timeout=2)
        if resp:
            print("[+] Response to enter:", resp.decode())
    except:
        pass
        
    conn.close()
    
except Exception as e:
    print(f"[-] Error: {e}")
