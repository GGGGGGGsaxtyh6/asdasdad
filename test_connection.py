#!/usr/bin/env python3
from pwn import *
import time

# Conectar
io = remote('94.237.53.81', 59575)

print("[*] Conectado. Esperando datos iniciales...")
time.sleep(2)

# Intentar recibir cualquier dato
try:
    data = io.recv(timeout=2)
    if data:
        print(f"[+] Datos recibidos: {data}")
        print(f"[+] Hex: {data.hex()}")
        print(f"[+] ASCII: {data.decode('latin-1', errors='ignore')}")
except:
    print("[-] No se recibieron datos iniciales")

# Probar enviar datos vacíos
print("\n[*] Enviando newline...")
io.sendline(b'')
time.sleep(1)

try:
    data = io.recv(timeout=2)
    if data:
        print(f"[+] Respuesta: {data}")
except:
    print("[-] Sin respuesta")

# Probar comandos básicos
commands = [b'help', b'?', b'status', b'list']
for cmd in commands:
    print(f"\n[*] Probando comando: {cmd}")
    io.sendline(cmd)
    time.sleep(0.5)
    try:
        data = io.recv(timeout=1)
        if data:
            print(f"[+] Respuesta: {data}")
    except:
        pass

io.close()
