#!/usr/bin/env python3
import socket
import string

TARGET = "94.237.49.23"
PORT = 37326

def test(data):
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.settimeout(2)
        s.connect((TARGET, PORT))
        s.sendall(data + b'\n')
        resp = s.recv(1024)
        s.close()
        return resp
    except:
        return b''

print("Testing single letters:")
for letter in string.ascii_lowercase:
    resp = test(letter.encode())
    if len(resp) > 0:
        print(f"  '{letter}': {resp.hex()}")

print("\nTesting single bytes 0x00-0xff:")
interesting = []
for byte in range(256):
    resp = test(bytes([byte]))
    if len(resp) > 0:
        interesting.append((byte, resp))

print(f"Found {len(interesting)} bytes with response:")
for b, r in interesting[:20]:  # Show first 20
    print(f"  0x{b:02x} ({chr(b) if 32 <= b < 127 else '?'}): {r.hex()}")