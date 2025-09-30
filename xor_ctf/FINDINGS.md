# Hallazgos del Reto XOR

## Vulnerabilidad
- El programa permite índices NEGATIVOS en el array result[]
- Validación: c != 0 && c <= 9 (permite -9 a -1 y 1 a 9)
- result[c] = a XOR b

## Memoria Accesible
```
result[-9] = 0x2021b8 (stdin+184)
result[-8] = 0x2021c0 (stdin+192) - CAUSA CRASH CUANDO SE USA
result[-7] = 0x2021c8 (stdin+200)
result[-6] = 0x2021d0 (stdin+208) - CAUSA CRASH EN COMBINACION
result[-5] = 0x2021d8 (stdin+216) - VTABLE POINTER! Causa crash en siguiente input
result[-4] = 0x2021e0 (justo después de stdin, en el gap)
result[-3] = 0x2021e8 (gap)
result[-2] = 0x2021f0 (gap)
result[-1] = 0x2021f8 (gap)
result[0] = 0x202200 (inicio de result[])
result[1-9] = dentro de result[]
```

## stdin Structure
- Base: 0x202100
- Tamaño: 224 bytes (struct _IO_FILE)
- Offset 216 (0x2021d8): vtable pointer
- result[-5] apunta EXACTAMENTE al vtable pointer

## Función Objetivo
- win() en 0xa21
- Llama a system("cat flag")
- NUNCA se llama desde el código normal

## Comportamiento Observado

### Sobrescrituras que NO crashean:
- Solo offset -9
- Solo offset -7
- Solo offset -6
- Combo [-9, -7]
- Combo [-9, -6]

### Sobrescrituras que SÍ crashean:
- Solo offset -5 + cualquier input adicional
- Combo [-8, XXX] + input adicional
- Combo [-9, -8, -6, -7] (crashea al escribir -5)

### El Crash
- Ocurre típicamente al enviar el siguiente input después de sobrescribir
- NO produce output visible
- Probablemente crashea ANTES de ejecutar código útil

## Teorías

### Teoría 1: FSOP (File Structure Oriented Programming)
- Sobrescribir stdin->vtable con fake vtable en result[]
- PROBLEMA: glibc moderno tiene vtable integrity checks
- BLOQUEADO por verificaciones de seguridad

### Teoría 2: Crash controlado
- El crash ejecuta algún destructor/handler
- PROBLEMA: No hay output del crash

### Teoría 3: Manipular stdin para leer desde otra ubicación
- Cambiar _IO_read_ptr/_IO_read_base
- PROBLEMA: Esos campos están en offsets bajos (8-24), inaccesibles

### Teoría 4: El desafío es diferente
- Quizás no se trata de ejecutar win()
- "What can you access and what are you going to write?"
- Quizás hay que escribir valores específicos que revelan algo

## Intentos Realizados
1. ✗ Sobrescribir GOT (fuera de rango)
2. ✗ Fake vtable con múltiples punteros a win
3. ✗ Sobrescribir múltiples campos de stdin
4. ✗ Triggerar crashes con diferentes inputs
5. ✗ Usar fake vtable apuntando a result[]

## Próximos Pasos
- Investigar si glibc en el servidor es VIEJA (sin vtable checks)
- Probar con vtable apuntando a direcciones específicas de libc
- Investigar si hay técnicas de bypass de vtable check
- Considerar que maybe el objetivo NO es ejecutar código