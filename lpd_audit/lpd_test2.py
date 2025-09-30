#!/usr/bin/env python3
import socket
import time

TARGET = "94.237.49.23"
PORT = 37326

def send_raw(data):
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.settimeout(10)
        s.connect((TARGET, PORT))
        s.sendall(data)
        
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

# Probar comando 0x03 y 0x04 con formato completo RFC 1179
# Formato: comando + queue + space + user + space + job_list

tests = [
    (b'\x03lp\n', "Short queue lp"),
    (b'\x04lp\n', "Long queue lp"),
    (b'\x03lp \n', "Short queue con espacio"),
    (b'\x04lp \n', "Long queue con espacio"),
    (b'\x03lp root\n', "Short queue user root"),
    (b'\x04lp root\n', "Long queue user root"),
    (b'\x04lp root 1\n', "Long queue root job 1"),
    (b'\x04lp root 001\n', "Long queue root job 001"),
    (b'\x04lp flag\n', "Long queue user flag"),
    (b'\x04lp /flag.txt\n', "Long queue user /flag.txt"),
]

for data, desc in tests:
    print(f"=== {desc} ===")
    resp = send_raw(data)
    print(f"Len: {len(resp)}")
    if len(resp) > 0:
        print(f"Hex: {resp.hex()}")
        try:
            print(f"Text: {resp.decode('utf-8', errors='replace')}")
        except:
            print(f"Raw: {resp}")
    print()