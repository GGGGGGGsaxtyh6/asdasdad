#!/usr/bin/env python3
import socket
import time

try:
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.settimeout(15)
    print("[*] Connecting to server...")
    s.connect(('94.237.57.211', 55233))
    print("[+] Connected!")
    
    time.sleep(1)
    
    # Try to receive menu
    s.setblocking(False)
    try:
        data = b""
        while True:
            chunk = s.recv(1024)
            if not chunk:
                break
            data += chunk
    except:
        pass
    
    s.setblocking(True)
    s.settimeout(15)
    
    if data:
        print(data.decode())
    else:
        print("[!] No initial data received, sending option 1...")
        s.sendall(b'1\n')
        time.sleep(1)
        data = s.recv(4096)
        print(data.decode())
    
    s.close()
except Exception as e:
    print(f"[-] Error: {e}")
    import traceback
    traceback.print_exc()
