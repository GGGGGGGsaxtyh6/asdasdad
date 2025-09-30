#!/usr/bin/env python3
import socket

try:
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.settimeout(10)
    s.connect(('94.237.57.211', 55233))
    
    # Receive initial menu
    data = s.recv(4096).decode()
    print(data)
    
    # Send option 1 to get connection info
    s.sendall(b'1\n')
    
    # Receive connection info
    info = s.recv(4096).decode()
    print(info)
    
    s.close()
except Exception as e:
    print(f"Error: {e}")
