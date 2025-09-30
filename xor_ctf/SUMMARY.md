# Resumen Completo del Reto XOR

## Reto
- **Nombre**: xor (challenge 50)
- **Server**: svc.pwnable.xyz:30029
- **Descripción**: "What can you access and what are you going to write?"
- **Puntos**: 50

## Binario
- ELF 64-bit, no PIE
- GLIBC 2.7 (scanf), GLIBC 2.2.5 (resto)
- Stack canary habilitado
- No NX (memoria ejecutable por mprotect en __do_global_ctors_aux)

## Vulnerabilidad
```c
scanf("%ld %ld %ld", &a, &b, &c);
result[c] = a XOR b;
```
- Validaciones: a != 0, b != 0, c != 0, c <= 9
- **PERMITE c NEGATIVO**: offsets -9 a 9 (excluyendo 0)

## Memoria Accesible
```
result[-9] = 0x2021b8 (stdin+184)
result[-8] = 0x2021c0 (stdin+192) _mode
result[-7] = 0x2021c8 (stdin+200) _unused2
result[-6] = 0x2021d0 (stdin+208) _unused2
result[-5] = 0x2021d8 (stdin+216) VTABLE POINTER
result[-4] = 0x2021e0 (completed flag)
result[-3 a -1] = gap entre stdin y result
result[1-9] = dentro de result[]
```

## Objetivo
- Ejecutar win() en 0xa21
- win() llama a system("cat flag")

## Intentos Realizados (TODOS FALLIDOS)

### 1. FILE Structure - Vtable Corruption
- Llenar result[] con punteros a win
- Sobrescribir stdin->vtable (offset -5) con result_base
- **Resultado**: Crashea sin output al triggerar scanf

### 2. Diferentes direcciones en vtable
- win (0xa21)
- win+4 (0xa25, sin prólogo)
- system@plt (0x7f8)
- Apuntar a .init_array
- Apuntar a stdin mismo con offsets
- **Resultado**: Todos crashean sin output

### 3. Corromper otros campos de stdin
- _mode (offset -8)
- _unused2 (offsets -7, -6)
- **Resultado**: Causa inestabilidad pero no ejecución

### 4. Sobrescribir campos en el gap
- completed flag (offset -4)
- Offsets -3, -2, -1
- **Resultado**: No tiene efecto útil

### 5. Buscar GOT/fini_array
- **Resultado**: Fuera de rango (offsets < -9)

## Problema Fundamental

Cuando se corrompe stdin->vtable y se llama scanf:
- El programa TERMINA/CRASHEA inmediatamente
- NO hay output
- NO se ejecuta win()

Posibles causas:
1. ~~Vtable check en glibc~~ (GLIBC 2.7 no tiene checks)
2. Double indirection incorrecta
3. Stack alignment issues
4. Algo en el mecanismo de vtable que no entiendo

## Lo que NO Funciona

- ✗ Alcanzar GOT (offset -67 fuera de rango)
- ✗ Alcanzar .fini_array (offset -140 fuera de rango)
- ✗ Alcanzar stdout (offset -33 fuera de rango)
- ✗ Manipular punteros de lectura de stdin (offsets 1-3 fuera de rango)
- ✗ ROP chains (no hay forma de redirigir stack)
- ✗ Shellcode (sin forma de ejecutarlo)

## Estado Actual

COMPLETAMENTE ATASCADO. He intentado TODO enfoque de binary exploitation que conozco y ninguno funciona debido a:
1. Rango de offsets muy limitado (-9 a 9)
2. Vtable corruption crashea sin ejecutar código
3. No puedo alcanzar GOT, fini_array, u otras estructuras críticas

## Hipótesis Pendientes

1. ¿Hay alguna técnica específica de FILE exploitation que no conozco?
2. ¿El reto requiere algo NO relacionado con binary exploitation tradicional?
3. ¿Hay algún detalle del binario que me perdí?
4. ¿La pregunta "What can you access and what are you going to write?" tiene un significado más profundo?