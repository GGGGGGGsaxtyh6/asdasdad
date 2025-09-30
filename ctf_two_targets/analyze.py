#!/usr/bin/env python3
from pwn import *

# Cargar el binario
elf = ELF('./image/challenge/challenge')

# Ver las protecciones
print("=" * 50)
print("PROTECCIONES DEL BINARIO:")
print("=" * 50)
print(f"RELRO: {elf.relro}")
print(f"Stack Canary: {elf.canary}")
print(f"NX: {elf.nx}")
print(f"PIE: {elf.pie}")
print()

# Direcciones importantes
print("=" * 50)
print("DIRECCIONES IMPORTANTES:")
print("=" * 50)
print(f"win(): {hex(elf.symbols['win'])}")
print(f"auth(): {hex(elf.symbols['auth'])}")
print(f"main(): {hex(elf.symbols['main'])}")
print()

# Buscar strings interesantes
print("=" * 50)
print("STRINGS INTERESANTES:")
print("=" * 50)
for string in elf.search(b"cat flag"):
    print(f"'cat flag' en: {hex(string)}")