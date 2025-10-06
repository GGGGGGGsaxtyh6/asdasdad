#!/usr/bin/env python3
import socket

def get_full_content(ip, port, path):
    try:
        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        sock.settimeout(5)
        sock.connect((ip, port))
        
        request = f"GET {path} HTTP/1.1\r\nHost: {ip}\r\nConnection: close\r\n\r\n"
        sock.send(request.encode())
        
        response = b""
        while True:
            try:
                chunk = sock.recv(4096)
                if not chunk:
                    break
                response += chunk
            except:
                break
        
        sock.close()
        return response.decode('utf-8', errors='ignore')
    except:
        return None

print("="*60)
print("🔥 IP #1: 54.245.27.232:9993 - vRealize Operations Manager")
print("="*60)

# Ver el /info endpoint
print("\n📍 Contenido de /info:")
content = get_full_content("54.245.27.232", 9993, "/info")
if content:
    lines = content.split('\n')
    in_body = False
    for line in lines:
        if in_body or '{' in line:
            in_body = True
            print(line[:200])
            if '}' in line:
                break

# Ver el /api endpoint  
print("\n📍 Contenido de /api:")
content = get_full_content("54.245.27.232", 9993, "/api")
if content:
    lines = content.split('\n')
    for i, line in enumerate(lines):
        if i > 10 and ('{' in line or '"' in line):
            print(line[:200])
            if i > 20:
                break

print("\n" + "="*60)
print("🔥 IP #2: 152.70.137.18:8888 - Sistema de Gestión de Activos")
print("="*60)

# Ver la página principal
print("\n📍 Contenido de / (primeras líneas):")
content = get_full_content("152.70.137.18", 8888, "/")
if content:
    lines = content.split('\n')
    for i, line in enumerate(lines[10:30]):
        if line.strip():
            print(line[:150])

print("\n📍 Contenido de /test:")
content = get_full_content("152.70.137.18", 8888, "/test")
if content:
    lines = content.split('\n')
    for i, line in enumerate(lines[10:25]):
        if line.strip():
            print(line[:150])

print("\n" + "="*60)
