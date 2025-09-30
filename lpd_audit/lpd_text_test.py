#!/usr/bin/env python3
import socket

TARGET = "94.237.49.23"
PORT = 37326

def test(data):
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.settimeout(3)
    s.connect((TARGET, PORT))
    s.sendall(data)
    try:
        resp = s.recv(4096)
    except:
        resp = b''
    s.close()
    return resp

# Probar diferentes cadenas con "lp"
tests = [
    b"lp\n",
    b"help\n",
    b"lpstat\n",
    b"lpq\n",
    b"lpr\n",
    b"lpc\n",
    b"lpadmin\n",
]

for t in tests:
    resp = test(t)
    print(f"{t}: {len(resp)} bytes - {resp.hex()}")