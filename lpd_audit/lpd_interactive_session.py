#!/usr/bin/env python3
import socket
import time

TARGET = "94.237.49.23"
PORT = 37326

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.settimeout(20)
s.connect((TARGET, PORT))

print("[*] Connected")

# Enviar comando 0x04
s.sendall(b'\x04lp\n')
print("[*] Sent: 0x04lp\\n")

# Leer primer byte
first = s.recv(1)
print(f"[*] Received first byte: 0x{first[0]:02x}")

# Si es 0x00, quizás significa "ready" y necesito enviar algo más
if first == b'\x00':
    print("[*] Got ACK, trying to send continuation...")
    
    # Intentar enviar un ACK de vuelta
    s.sendall(b'\x00')
    time.sleep(0.5)
    
    # Leer más datos
    try:
        more_data = b''
        s.settimeout(3)
        while True:
            chunk = s.recv(8192)
            if not chunk:
                break
            more_data += chunk
            print(f"[*] Received chunk: {len(chunk)} bytes")
    except socket.timeout:
        print("[*] Timeout reading more data")
    
    if len(more_data) > 0:
        print(f"[***] GOT DATA: {len(more_data)} bytes")
        print(f"Hex: {more_data[:200].hex()}")
        try:
            print(f"Text: {more_data.decode('utf-8', errors='replace')}")
        except:
            pass

s.close()