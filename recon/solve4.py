import socket, time, re

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect(('saturn.picoctf.net', 59375))
time.sleep(0.3)
s.recv(8192)

print("Nueva estrategia: (0, -4) = 0*90 + (-4) = -4\n")

# Ir a (0, 4) primero
print("Paso 1: Subir a row=0...")
for i in range(4):
    s.send(b'w\n')
    time.sleep(0.2)

time.sleep(0.5)
data = s.recv(8192).decode(errors='ignore')
for line in data.split('\n'):
    if 'Player position: 0' in line:
        print(f"  {line}")
        break

# Comando 'l' + carácter no-nulo
print("\nPaso 2: Comando 'l' + 'Y'...")
s.send(b'l')
time.sleep(0.2)
s.send(b'Y')
time.sleep(0.2)

# Mover izquierda 8 veces para llegar a col=-4
print("\nPaso 3: Mover izquierda 8 veces (col: 4 -> -4)...")
for i in range(8):
    s.send(b'a\n')
    time.sleep(0.2)

time.sleep(1)

data = s.recv(8192).decode(errors='ignore')

if 'flag: 1' in data or 'Player has flag: 1' in data:
    print("\n¡¡¡FLAG ACTIVADA CON (0, -4)!!!")
    
    s.send(b'p\n')
    time.sleep(3)
    
    final = b''
    for i in range(15):
        try:
            s.settimeout(0.5)
            final += s.recv(8192)
        except:
            break
    
    text = final.decode(errors='ignore')
    match = re.search(r'picoCTF\{[^}]+\}', text)
    if match:
        print(f"\n{'='*60}")
        print(f"FLAG: {match.group()}")
        print(f"{'='*60}")
    else:
        print("Autopilot terminó pero no se encontró flag")
        print(text[-300:])
else:
    print("\nNo se activó. Estado:")
    for line in data.split('\n'):
        if 'Player' in line or 'flag' in line:
            print(line)

s.close()
