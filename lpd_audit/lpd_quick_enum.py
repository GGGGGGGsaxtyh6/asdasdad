#!/usr/bin/env python3
import socket

TARGET = "94.237.49.23"
PORT = 37326

def query(data):
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.settimeout(3)
    s.connect((TARGET, PORT))
    s.sendall(data)
    
    resp = b''
    try:
        resp = s.recv(8192)
    except:
        pass
    s.close()
    return resp

# Probar job numbers del 1 al 20
for i in range(1, 21):
    data = f'\x04lp root {i}\n'.encode('latin1')
    resp = query(data)
    if len(resp) > 1:
        print(f"Job {i}: {resp}")

# Probar usuarios
for user in ["flag", "admin", "lp"]:
    resp = query(f'\x04lp {user}\n'.encode())
    if len(resp) > 1:
        print(f"User {user}: {resp}")