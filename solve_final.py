#!/usr/bin/env python3
import socket
import time
import re

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect(('rhea.picoctf.net', 63808))

# Leer inicial
data = s.recv(4096)
print("Conectado")

# Enviar 4 comandos 'p' con espera entre cada uno
for i in range(1, 5):
    print(f"Enviando p #{i}")
    s.send(b'p')
    time.sleep(8)  # Esperar a que solve_round termine
    
    # Leer respuesta
    s.settimeout(2)
    try:
        response = s.recv(16384)
        print(f"Respuesta {i} recibida")
    except:
        pass

# Esperar respuesta final
print("Esperando respuesta final...")
time.sleep(5)

# Leer todo
s.settimeout(3)
all_data = b""
try:
    while True:
        chunk = s.recv(8192)
        if not chunk:
            break
        all_data += chunk
except:
    pass

output = all_data.decode('latin-1', errors='ignore')
print("\n" + "="*50)
print(output[-1500:])
print("="*50)

# Buscar flag
flags = re.findall(r'picoCTF\{[^}]+\}', output)
if flags:
    print(f"\n¡¡¡FLAG ENCONTRADA!!!: {flags[0]}")
else:
    print("\nNo encontré la flag aún")

s.close()
