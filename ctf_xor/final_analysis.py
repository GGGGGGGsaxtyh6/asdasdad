#!/usr/bin/env python3
"""
Análisis final del reto

Datos clave:
1. Puedo escribir A XOR B en result[C] donde C puede ser negativo
2. PIE habilitado - direcciones randomizadas
3. Full RELRO - no puedo sobrescribir GOT
4. No hay stack canary
5. Hay función win() que ejecuta "cat flag"

El problema: ¿Cómo llamo a win() si no sé su dirección?

Espera... ¿y si no necesito llamar a win()?
El código de win() es simplemente: system("cat flag")

¿Puedo ejecutar "cat flag" de otra manera?

Idea: Si corrompo stdin de tal manera que cuando scanf lee,
interprete algo como un comando...

No, eso no tiene sentido.

Otra idea: ¿Y si el servidor remoto NO tiene PIE habilitado?
A veces los binarios se compilan diferente para el servidor.

Déjame verificar eso.
"""

from pwn import *

elf = ELF('./image/challenge/challenge')

print("="*60)
print("VERIFICACIÓN DE HIPÓTESIS")
print("="*60)
print()

print("[*] El binario local:")
print(f"    PIE: {elf.pie}")
print(f"    win() offset: 0x{elf.symbols['win']:x}")
print()

print("[*] Si el servidor NO tiene PIE:")
print("    win() estaría en: 0x400000 + 0xa21 = 0x400a21")
print("    (dirección fija)")
print()

print("[*] Pero no puedo sobrescribir el return address")
print("    porque está en el stack y result está en BSS")
print()

print("[*] ¿Qué tal si hay un buffer overflow en scanf?")
print("    scanf lee 3 valores long... no parece haber overflow")
print()

print("[!] Necesito re-pensar completamente el enfoque")