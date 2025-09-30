#!/usr/bin/env python3
"""
Análisis de qué puedo sobrescribir con índices negativos

result está en 0x202200
Con índice -1: 0x202200 + (-1 * 8) = 0x202200 - 8 = 0x2021f8

Símbolos cerca:
- 0x202000: __data_start
- 0x202008: __dso_handle  
- 0x202010: __TMC_END__ / _edata / __bss_start
- 0x202020: _IO_2_1_stdout_
- 0x202100: _IO_2_1_stdin_
- 0x2021e0: completed.7561
- 0x202200: result

Con índice -1: accedo a 0x2021f8 (dentro de stdin?)
Con índice -2: accedo a 0x2021f0
...

_IO_2_1_stdin_ va de 0x202100 a 0x2021e0 (0xe0 bytes)
Justo antes de result!

Si puedo corromper la estructura FILE de stdin, 
podría hacer cosas interesantes.

Pero espera... el reto se llama "XOR" y dice 
"What can you access and what are you going to write?"

Tal vez necesito pensar diferente. Déjame ver qué 
pasa si uso índices muy grandes.
"""

from pwn import *

elf = ELF('./image/challenge/challenge')

result_addr = 0x202200
stdin_addr = 0x202100  
stdout_addr = 0x202020

print("="*60)
print("ANÁLISIS DE OFFSETS")
print("="*60)
print()
print(f"result:  0x{result_addr:x}")
print(f"stdin:   0x{stdin_addr:x} (size: 0xe0)")
print(f"stdout:  0x{stdout_addr:x} (size: 0xe0)")
print()

# Calcular qué índices acceden a qué
for idx in [-4, -3, -2, -1, 0, 1, 2, 9, 10, 100]:
    addr = result_addr + (idx * 8)
    print(f"Índice {idx:3d}: 0x{addr:x}", end="")
    
    if 0x202100 <= addr < 0x2021e0:
        offset_stdin = addr - stdin_addr
        print(f" -> stdin+0x{offset_stdin:x}")
    elif 0x202020 <= addr < 0x202100:
        offset_stdout = addr - stdout_addr
        print(f" -> stdout+0x{offset_stdout:x}")
    elif 0x202200 <= addr < 0x202250:
        offset_result = addr - result_addr
        print(f" -> result[{offset_result//8}]")
    else:
        print()

print()
print("[*] Estructura FILE (_IO_FILE):")
print("    Offset 0x00: flags")
print("    Offset 0x08: _IO_read_ptr")
print("    Offset 0x10: _IO_read_end")
print("    Offset 0x18: _IO_read_base")
print("    Offset 0x20: _IO_write_base")
print("    Offset 0x28: _IO_write_ptr")
print("    Offset 0x30: _IO_write_end")
print("    ...")
print("    Offset 0xd8: vtable pointer")
print()
print("[*] Si puedo sobrescribir stdin, podría:")
print("    - Manipular buffers de lectura")
print("    - Cambiar el vtable pointer")
print()

# Miremos la dirección de win
win_offset = elf.symbols['win']
print(f"[*] win() está en offset: 0x{win_offset:x}")
print("    Con PIE, será: base + 0x{:x}".format(win_offset))