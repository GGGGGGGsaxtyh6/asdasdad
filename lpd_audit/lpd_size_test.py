#!/usr/bin/env python3
import socket

TARGET = "94.237.49.23"
PORT = 37326

def test_size(size):
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.settimeout(3)
        s.connect((TARGET, PORT))
        s.sendall(b'\x04lp' + b'A'*size + b'\n')
        resp = s.recv(4096)
        s.close()
        return (True, len(resp), resp)
    except Exception as e:
        return (False, 0, str(e))

# Binary search para encontrar el tamaño límite
sizes = [0, 10, 50, 100, 500, 1000, 5000, 10000]

for size in sizes:
    success, resp_len, resp = test_size(size)
    print(f"Size {size:5d}: {'OK' if success else 'FAIL'} - Response: {resp_len} bytes")
    if success and resp_len > 1:
        print(f"  Data: {resp[:100]}")