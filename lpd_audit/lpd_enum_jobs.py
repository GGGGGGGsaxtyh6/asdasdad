#!/usr/bin/env python3
import socket
import time

TARGET = "94.237.49.23"
PORT = 37326

def query_queue(queue, user=None, jobs=None):
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.settimeout(10)
        s.connect((TARGET, PORT))
        
        # Formato: 0x04 + queue + [space + user + [space + job1 + space + job2...]]
        data = b'\x04' + queue.encode()
        if user:
            data += b' ' + user.encode()
            if jobs:
                for job in jobs:
                    data += b' ' + str(job).encode()
        data += b'\n'
        
        s.sendall(data)
        time.sleep(0.5)
        
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

# Enumerar posibles job numbers
print("=== Enumerando job numbers (0-100) ===\n")

for job_num in range(0, 101):
    resp = query_queue("lp", "root", [job_num])
    if len(resp) > 1:
        print(f"Job #{job_num}: {len(resp)} bytes")
        print(f"  Hex: {resp[:200].hex()}")
        try:
            text = resp.decode('utf-8', errors='replace')
            print(f"  Text: {text[:500]}")
        except:
            pass
        print()

# Probar con diferentes usuarios
print("\n=== Probando diferentes usuarios ===\n")
users = ["root", "lp", "daemon", "admin", "flag", "htb", "user"]
for user in users:
    resp = query_queue("lp", user)
    if len(resp) > 1:
        print(f"User '{user}': {len(resp)} bytes")
        print(f"  Text: {resp.decode('utf-8', errors='replace')[:500]}")
        print()