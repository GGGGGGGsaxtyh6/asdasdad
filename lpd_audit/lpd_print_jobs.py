#!/usr/bin/env python3
import socket
import time

TARGET = "94.237.49.23"
PORT = 37326

# Probar comando 0x01 con diferentes colas
queues = ["lp", "lp0", "lp1", "lp2", "flag", "/var/spool/lpd/lp"]

for q in queues:
    print(f"=== Queue: {q} ===")
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.settimeout(10)
    s.connect((TARGET, PORT))
    
    s.sendall(b'\x01' + q.encode() + b'\n')
    time.sleep(1)
    
    all_data = b''
    s.settimeout(3)
    try:
        while True:
            chunk = s.recv(8192)
            if not chunk:
                break
            all_data += chunk
            print(f"  [Chunk: {len(chunk)} bytes]")
    except socket.timeout:
        pass
    
    s.close()
    
    print(f"  Total: {len(all_data)} bytes")
    if len(all_data) > 1:
        print(f"  Hex: {all_data[:200].hex()}")
        try:
            text = all_data.decode('utf-8', errors='replace')
            print(f"  Text: {text}")
        except:
            pass
    print()