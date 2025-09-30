#!/usr/bin/env python3
import socket
import time

TARGET = "94.237.49.23"
PORT = 37326

def dump_interaction(send_data, desc):
    print(f"\n{'='*60}")
    print(f"{desc}")
    print(f"{'='*60}")
    print(f"Sending ({len(send_data)} bytes):")
    print(f"  Hex: {send_data.hex()}")
    print(f"  Raw: {send_data}")
    
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.settimeout(5)
    s.connect((TARGET, PORT))
    s.sendall(send_data)
    
    time.sleep(1)
    
    resp = b''
    try:
        resp = s.recv(8192)
    except socket.timeout:
        print("  [Timeout]")
    
    s.close()
    
    print(f"\nReceived ({len(resp)} bytes):")
    print(f"  Hex: {resp.hex()}")
    print(f"  Raw: {resp}")
    if len(resp) > 0:
        print(f"  Ascii: {[chr(b) if 32 <= b < 127 else f'\\x{b:02x}' for b in resp]}")
    print()

# Probar diferentes comandos muy básicos
tests = [
    (b'\x04lp\n', "Command 0x04 with queue 'lp'"),
    (b'\x03lp\n', "Command 0x03 with queue 'lp'"),
    (b'\x01lp\n', "Command 0x01 with queue 'lp'"),
    (b'lp\n', "Just 'lp' as text"),
    (b'\x04\n', "Command 0x04 without queue"),
    (b'GET /flag HTTP/1.0\n\n', "HTTP GET"),
]

for data, desc in tests:
    dump_interaction(data, desc)