#!/usr/bin/env python3
"""
Buscando la segunda solución...

Mirando el desensamblado:
- Opción 1: scanf("%32s", rbp-0x40) -> Puede escribir 32 bytes + null
- Opción 2: scanf("%24s", rbp-0x20) -> Puede escribir 24 bytes + null  
- Opción 3: scanf("%ld", rbp-0x10) -> Escribe 8 bytes

Layout del stack:
rbp-0x50 a rbp-0x48 (8 bytes): ?
rbp-0x48 a rbp-0x40 (8 bytes): ?
rbp-0x40 a rbp-0x20 (32 bytes): name buffer
rbp-0x20 a rbp-0x08 (24 bytes): nationality buffer
rbp-0x10 a rbp-0x08 (8 bytes): age variable
rbp-0x08 (8 bytes): stack canary
rbp (8 bytes): saved rbp
rbp+0x08: return address

La segunda solución podría ser:
1. Buffer overflow en name: "%32s" permite 32 caracteres + null byte
   Esto sobrescribirá el primer byte de nationality
   
2. El scanf de nationality en rbp-0x20 podría sobrescribir age en rbp-0x10
   si enviamos 16 bytes, llegaríamos hasta rbp-0x10
   
3. O podría haber un ret2win directo...

Vamos a revisar si hay forma de sobrescribir el return address sin tocar el canary.
"""

from pwn import *

elf = ELF('./image/challenge/challenge')

print("Analizando posible segunda solución...")
print()
print("La primera solución usa la función auth() para verificar el input correcto")
print("La segunda solución probablemente explote un buffer overflow")
print()
print(f"Dirección de win(): {hex(elf.symbols['win'])}")
print()

# Veamos el tamaño real de los buffers
print("Revisando el código assembly...")
print()
print("Opción 1: scanf con formato '%32s'")
print("  - Lee hasta 32 caracteres + null terminator")
print("  - Destino: rbp-0x40")
print()
print("Opción 2: scanf con formato '%24s'")  
print("  - Lee hasta 24 caracteres + null terminator")
print("  - Destino: rbp-0x20")
print()
print("Opción 3: scanf con formato '%ld' (o similar)")
print("  - Lee un long (8 bytes)")
print("  - Destino: rbp-0x10")
print()

# La clave es que name puede escribir 33 bytes (32 + null)
# Esto sobrescribirá el primer byte de nationality
# Luego nationality puede escribir 25 bytes (24 + null)
# Esto desde rbp-0x20 alcanza hasta rbp-0x07

print("Observación importante:")
print("El buffer 'name' (32 bytes) va de rbp-0x40 a rbp-0x20")
print("Si scanf lee 32 caracteres, agregará null en rbp-0x1f")
print()
print("El buffer 'nationality' (24 bytes nominalmente) empieza en rbp-0x20")
print("Si leemos 24 chars + null, llegaríamos hasta rbp-0x07")
print()
print("Pero el stack canary está en rbp-0x08...")
print()

# Otra idea: quizás podemos usar la opción 3 para sobrescribir algo
print("Idea alternativa:")
print("La opción 3 escribe un 'long' en rbp-0x10")
print("Esto podría usarse para sobrescribir parte de nationality")
print("o manipular algún puntero...")
print()

# Revisemos si hay otra función vulnerable
print("Funciones disponibles:")
for func in ['win', 'auth', 'main', 'setup', 'handler', 'read_int32', 'print_menu']:
    if func in elf.symbols:
        print(f"  {func}: {hex(elf.symbols[func])}")