#!/usr/bin/env python3
"""
Si escribo X en una posición que ya contiene Y:
- El nuevo valor será X
- Pero luego leo X, no Y

Para leer sin modificar, necesitaría:
A XOR B = valor_actual

Pero no sé el valor_actual...

Alternativa: escribir valores conocidos y ver qué pasa
Si escribo un valor pequeño (como 1) y el programa crashea,
significa que esa posición era importante.

Voy a probar escribir en diferentes índices negativos
y ver cuáles causan crash.
"""

from pwn import *

context.log_level = 'warn'

def test_index(idx, remote=False):
    try:
        if remote:
            p = remote('svc.pwnable.xyz', 30029, level='error')
        else:
            p = process('./image/challenge/challenge', level='error')
        
        p.recvuntil(b'> ', timeout=2)
        p.sendline(f'1 0 {idx}'.encode())  # Escribir 1
        
        # Intentar segunda operación
        response = p.recvuntil(b'> ', timeout=1)
        if b'Result' in response:
            p.sendline(b'2 3 1')  # Operación simple
            response2 = p.recvuntil(b'> ', timeout=1)
            if b'Result' in response2:
                p.close()
                return "OK"
        
        p.close()
        return "CRASH"
    except:
        return "ERROR"

print("="*60)
print("PROBANDO ÍNDICES NEGATIVOS")
print("="*60)
print()

# Probar varios índices
for idx in range(-1, -35, -1):
    result = test_index(idx, remote=False)
    print(f"Índice {idx:3d}: {result}")
    if idx % 5 == 0:
        time.sleep(0.1)  # Para no saturar

print("\n[*] Índices que causan CRASH son críticos para el funcionamiento")