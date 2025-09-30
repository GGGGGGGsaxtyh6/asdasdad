#!/usr/bin/env python3
from pwn import *

context.log_level = 'debug'

host = "94.237.57.211"
port = 55233

try:
    io = remote(host, port, timeout=15)
    
    # Recibir menú completo con más tiempo
    print("Waiting for menu...")
    time.sleep(2)
    
    try:
        data = io.recv(timeout=3)
        print(f"Received {len(data)} bytes")
        print(data.decode())
    except:
        pass
    
    # Enviar opción 1
    print("\nSending option 1...")
    io.sendline(b'1')
    
    time.sleep(2)
    
    # Recibir respuesta
    print("Receiving response...")
    try:
        response = io.recv(timeout=5)
        print(f"\nReceived {len(response)} bytes")
        print(response.decode())
        
        with open('connection_data.txt', 'wb') as f:
            f.write(response)
    except:
        pass
    
    io.close()
    
except Exception as e:
    print(f"Error: {e}")
    import traceback
    traceback.print_exc()
