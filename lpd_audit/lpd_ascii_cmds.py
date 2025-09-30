#!/usr/bin/env python3
import socket
import time

TARGET = "94.237.49.23"
PORT = 37326

def test_command(cmd):
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.settimeout(10)
        s.connect((TARGET, PORT))
        
        s.sendall(cmd.encode() + b'\n')
        time.sleep(0.5)
        
        resp = b''
        s.settimeout(2)
        try:
            while True:
                chunk = s.recv(4096)
                if not chunk:
                    break
                resp += chunk
        except socket.timeout:
            pass
        
        s.close()
        return resp
    except Exception as e:
        return str(e).encode()

# Comandos ASCII comunes
commands = [
    "HELP",
    "help",
    "STATUS",
    "status",
    "LIST",
    "list",
    "QUEUE",
    "queue",
    "INFO",
    "info",
    "FLAG",
    "flag",
    "SHOW lp",
    "show lp",
    "GET flag",
    "get flag",
    "READ /flag.txt",
    "CAT /flag.txt",
]

for cmd in commands:
    print(f"=== Command: {cmd} ===")
    resp = test_command(cmd)
    print(f"Length: {len(resp)}")
    if len(resp) > 0:
        print(f"Hex: {resp[:100].hex()}")
        try:
            decoded = resp.decode('utf-8', errors='replace')
            if decoded.strip():
                print(f"Text: {decoded}")
        except:
            print(f"Raw: {resp[:100]}")
    print()