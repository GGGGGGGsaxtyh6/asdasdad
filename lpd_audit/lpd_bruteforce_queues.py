#!/usr/bin/env python3
import socket
import time
import string

TARGET = "94.237.49.23"
PORT = 37326

def test_queue(name):
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.settimeout(5)
        s.connect((TARGET, PORT))
        s.sendall(b'\x04' + name.encode() + b'\n')
        
        resp = s.recv(4096)
        s.close()
        return resp
    except:
        return b''

# Nombres comunes de colas
queues = [
    "lp", "lp0", "lp1", "lp2",
    "printer", "print", "pr",
    "default", "main",
    "flag", "FLAG", "Flag",
    "test", "debug",
    "admin", "root",
    # HTB themed
    "htb", "hackthebox",
    # Single letters
] + list(string.ascii_lowercase)

print("=== Probando nombres de cola comunes ===\n")
for q in queues:
    resp = test_queue(q)
    if len(resp) > 0:
        print(f"Queue '{q}': {len(resp)} bytes - 0x{resp[0]:02x}", end="")
        if len(resp) > 1:
            print(f" *** DATA: {resp[:100]}")
        else:
            print()

# Probar con comando 0x03 también
print("\n=== Probando con comando 0x03 ===\n")
for q in ["lp", "flag", "a", "b", "c"]:
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.settimeout(5)
    s.connect((TARGET, PORT))
    s.sendall(b'\x03' + q.encode() + b'\n')
    resp = s.recv(4096)
    s.close()
    if len(resp) > 1:
        print(f"Queue '{q}': {resp}")