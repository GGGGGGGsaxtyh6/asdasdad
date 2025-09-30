#!/usr/bin/env python3
"""
Análisis del reto XOR

Del desensamblado veo:
1. Lee 3 valores con scanf("%ld %ld %ld") en rbp-0x20, rbp-0x18, rbp-0x10
2. Verifica que:
   - Los 3 valores no sean 0
   - El tercer valor (rbp-0x10) <= 9
   - scanf retorne 3 (leyó 3 valores correctamente)
3. Hace XOR de los dos primeros valores: rcx = rbp-0x20 XOR rbp-0x18
4. Guarda el resultado en: result[rbp-0x10] = rcx
   donde result es un array global en 0x202200

La clave está en:
- lea rdx,[rax*8+0x0]  ; rax es el índice (tercer valor)
- lea rax,[rip+0x201715] ; dirección de result
- mov [rdx+rax*1],rcx  ; result[índice] = valor XORed

Pregunta: ¿Qué puedo escribir y dónde?
- Puedo escribir cualquier valor (XOR de dos números que controlo)
- En posiciones: result + (índice * 8) donde índice debe ser 1-9

Pero como PIE está habilitado, necesito leak o encontrar algo relativo.
"""

from pwn import *

elf = ELF('./image/challenge/challenge')

print("="*60)
print("ANÁLISIS DEL RETO XOR")
print("="*60)
print()

# Buscar el array result
print("[*] Buscando símbolos importantes...")
if 'result' in elf.symbols:
    print(f"    result array: {hex(elf.symbols['result'])}")
if 'win' in elf.symbols:
    print(f"    win(): {hex(elf.symbols['win'])}")

print()
print("[*] El programa:")
print("    1. Lee 3 números: A B C")
print("    2. Verifica: A!=0, B!=0, C!=0, C<=9, scanf retorna 3")
print("    3. Calcula: result[C] = A XOR B")
print("    4. Imprime: 'Result: <valor>'")
print("    5. Loop infinito")
print()

print("[*] Protecciones:")
print(f"    PIE: {elf.pie} (direcciones randomizadas)")
print(f"    NX: {elf.nx} (no ejecutar stack)")
print(f"    RELRO: {elf.relro} (GOT read-only)")
print(f"    Canary: {elf.canary}")
print()

print("[*] Observaciones:")
print("    - PIE habilitado: necesito leak de direcciones")
print("    - Full RELRO: no puedo sobrescribir GOT")
print("    - Índice 1-9: rango limitado de escritura")
print("    - result está en BSS (lectura/escritura)")
print()

print("[*] Función mprotect detectada en PLT")
print("    Esto podría ser clave para hacer memoria ejecutable")