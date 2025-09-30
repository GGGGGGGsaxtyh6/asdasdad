#!/usr/bin/env python3
import socket
import time

host = "svc.pwnable.xyz"
port = 30029

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((host, port))

# Recibir banner
data = s.recv(1024)
print("Banner:", data.decode())

# Probar con valores válidos simples
print("\nTest 1: XOR simple con offset 0")
payload = "5 3 1\n"  # 5 XOR 3 = 6, guardar en result[1]
print(f"Enviando: {payload.strip()}")
s.sendall(payload.encode())

time.sleep(1)
resp = s.recv(1024)
print(f"Respuesta: {resp.decode()}")

# Otro test
print("\nTest 2: XOR con offset 2")
payload = "10 7 2\n"  # 10 XOR 7 = 13, guardar en result[2]
print(f"Enviando: {payload.strip()}")
s.sendall(payload.encode())

time.sleep(1)
resp = s.recv(1024)
print(f"Respuesta: {resp.decode()}")

s.close()