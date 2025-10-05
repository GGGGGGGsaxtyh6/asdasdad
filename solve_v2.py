#!/usr/bin/env python3
import socket
import time
import re

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect(('rhea.picoctf.net', 63808))
s.settimeout(60)

# Leer inicial
data = s.recv(4096)
print("Conectado")

# Enviar todos los 'p' de una vez
print("Enviando 4 comandos 'p'...")
s.sendall(b'pppp')

# Esperar mucho tiempo para que se procesen todos
print("Esperando procesamiento (30 segundos)...")
time.sleep(30)

# Leer toda la respuesta
print("Leyendo respuesta...")
all_data = b""
try:
    while True:
        chunk = s.recv(8192)
        if not chunk:
            break
        all_data += chunk
        if len(all_data) > 1000000:  # Safety limit
            break
except socket.timeout:
    pass
except:
    pass

output = all_data.decode('latin-1', errors='ignore')

# Buscar flag
flags = re.findall(r'picoCTF\{[^}]+\}', output)
if flags:
    print(f"\n¡FLAG!: {flags[0]}")
else:
    print("\nBuscando en output...")
    # Mostrar las últimas líneas
    lines = output.split('\n')
    for line in lines[-50:]:
        if 'pico' in line.lower() or 'flag' in line.lower() or 'win' in line.lower():
            print(line)

s.close()
