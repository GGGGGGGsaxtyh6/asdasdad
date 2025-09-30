#!/usr/bin/env python3
from pwn import *
import time

context.log_level = 'warn'

host = "94.237.57.211"
port = 55233

def get_connection_info():
    try:
        io = remote(host, port)
        time.sleep(1)
        
        # Intentar recibir el menú
        try:
            menu = io.recvline(timeout=2)
            print("Menu received:", menu.decode())
        except:
            print("No menu line received")
        
        # Enviar opción 1
        print("Sending option 1...")
        io.sendline(b'1')
        time.sleep(2)
        
        # Recibir todas las líneas disponibles
        info_lines = []
        for i in range(20):  # Intentar recibir hasta 20 líneas
            try:
                line = io.recvline(timeout=1)
                info_lines.append(line)
                print(f"Line {i}: {line.decode().strip()}")
            except:
                break
        
        io.close()
        
        # Guardar todo
        if info_lines:
            with open('rpc_info.txt', 'wb') as f:
                f.write(b'\n'.join(info_lines))
            return True
        
        return False
        
    except Exception as e:
        print(f"Error: {e}")
        return False

if __name__ == "__main__":
    success = get_connection_info()
    if not success:
        print("\nCould not retrieve connection info from server")
        print("Trying alternative method...")
        
        # Intenta conectar y mostrar lo que sea que recibas
        try:
            io = remote(host, port)
            io.send(b'1\n')
            time.sleep(3)
            data = io.recvall(timeout=5)
            print("Raw data:", data)
            io.close()
        except Exception as e:
            print(f"Alternative also failed: {e}")
