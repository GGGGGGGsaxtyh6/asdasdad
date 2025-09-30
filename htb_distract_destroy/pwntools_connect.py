#!/usr/bin/env python3
from pwn import *

context.log_level = 'debug'

host = "94.237.57.211"
port = 55233

try:
    io = remote(host, port, timeout=10)
    
    # Recibir menú
    menu = io.recvuntil(b'>', timeout=5)
    print("MENU:")
    print(menu.decode())
    
    # Enviar opción 1
    io.sendline(b'1')
    
    # Recibir información
    info = io.recvall(timeout=5)
    print("\nINFO:")
    print(info.decode())
    
    # Guardar en archivo
    with open('rpc_info.txt', 'wb') as f:
        f.write(menu + info)
    
    io.close()
    
except Exception as e:
    print(f"Error: {e}")
    import traceback
    traceback.print_exc()
