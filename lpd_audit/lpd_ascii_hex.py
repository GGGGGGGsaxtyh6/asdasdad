#!/usr/bin/env python3
import socket
import time

TARGET = "94.237.49.23"
PORT = 37326

def test_raw(data):
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.settimeout(10)
        s.connect((TARGET, PORT))
        
        s.sendall(data)
        time.sleep(0.5)
        
        resp = b''
        s.settimeout(2)
        try:
            while True:
                chunk = s.recv(4096)
                if not chunk:
                    break
                resp += chunk
        except socket.timeout:
            pass
        
        s.close()
        return resp
    except Exception as e:
        return str(e).encode()

# Probar todos los bytes posibles como comando
print("=== Enumerando todos los bytes como comandos ===")
interesting = []

for i in range(256):
    data = bytes([i]) + b'lp\n'
    resp = test_raw(data)
    if len(resp) > 0 and resp != b'\x00':
        print(f"Byte 0x{i:02x} ({chr(i) if 32 <= i < 127 else '?'}): {len(resp)} bytes - {resp[:50].hex()}")
        interesting.append((i, resp))
        if len(resp) > 1:
            try:
                print(f"  Text: {resp.decode('utf-8', errors='replace')[:200]}")
            except:
                pass

print(f"\n=== Found {len(interesting)} interesting responses ===")
for byte_val, resp in interesting:
    print(f"0x{byte_val:02x}: {len(resp)} bytes")