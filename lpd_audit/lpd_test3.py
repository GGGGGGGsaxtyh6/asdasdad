#!/usr/bin/env python3
import socket

TARGET = "94.237.49.23"
PORT = 37326

def send_and_receive_all(data, delay=0.5):
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.settimeout(15)
    s.connect((TARGET, PORT))
    s.sendall(data)
    
    # Esperar un poco más
    import time
    time.sleep(delay)
    
    # Intentar leer TODO
    s.settimeout(5)
    all_data = b''
    try:
        while True:
            chunk = s.recv(8192)
            if not chunk:
                break
            all_data += chunk
            print(f"[Chunk recibido: {len(chunk)} bytes]")
    except socket.timeout:
        print("[Timeout reading]")
    except Exception as e:
        print(f"[Exception: {e}]")
    
    s.close()
    return all_data

print("=== Test: Long queue con delay largo ===")
resp = send_and_receive_all(b'\x04lp\n', delay=1.0)
print(f"Total bytes: {len(resp)}")
print(f"Hex: {resp.hex()}")
if len(resp) > 1:
    print(f"Después del null: {resp[1:]}")
    try:
        print(f"Text: {resp.decode('utf-8', errors='replace')}")
    except:
        pass
print()

# Probar sin el \n
print("=== Test: Comando sin newline ===")
resp = send_and_receive_all(b'\x04lp', delay=0.5)
print(f"Total bytes: {len(resp)}")
print(f"Hex: {resp.hex()}")
print()