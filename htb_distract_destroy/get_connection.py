#!/usr/bin/env python3
from pwn import *

context.log_level = 'info'

host = "94.237.57.211"
port = 55233

try:
    io = remote(host, port, timeout=15)
    
    # Recibir todo lo disponible
    io.recvuntil(b'action?', timeout=10)
    print("Sending option 1...")
    
    # Enviar opción 1
    io.sendline(b'1')
    
    # Esperar y recibir respuesta
    data = io.recvall(timeout=10).decode()
    print("\n" + "="*50)
    print("CONNECTION INFO:")
    print("="*50)
    print(data)
    print("="*50)
    
    # Guardar en archivo
    with open('connection_info.txt', 'w') as f:
        f.write(data)
    
    io.close()
    
except EOFError:
    print("Connection closed by server")
except Exception as e:
    print(f"Error: {e}")
    import traceback
    traceback.print_exc()
