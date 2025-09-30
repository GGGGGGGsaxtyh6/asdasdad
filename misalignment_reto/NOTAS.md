# Reto Misalignment - Notas de Progreso

## Análisis del Binario

- Buffer `var_a0h` en `[rbp - 0xa0]`, tamaño 152 bytes (0x98)
- Canario en offset 15 (0xf) desde inicio del buffer
- Valor inicial: `0x00000000deadbeef`
- Valor target: `0x0000000b000000b5`

## Vulnerabilidad

El programa permite escribir en:
```
[rbp + (num1+6)*8 - 0x98] = num2 + num3
```

Offsets alcanzables (desde var_a0h):
- num1=-7: offset 0-7
- num1=-6: offset 8-15 (sobrescribe byte 15 del canario)
- num1=-5: offset 16-23 (sobrescribe bytes 16-22 del canario)

## Problema Encontrado

- Escribir con num1=-6 solo funciona con valores muy pequeños (≤1)
- Valores >= 10 causan que el programa termine sin output
- NO es por overflow, NO es por scanf, NO es por segfault
- El programa simplemente termina limpiamente

## Hipótesis

Hay algo en offset 8-14 del buffer que cuando se sobrescribe con valores >1,
causa que el programa termine. Pero memset inicializa todo a 0, así que
no debería haber nada importante ahí originalmente.

## Lo que Funciona

- Múltiples writes con valores pequeños (ej: -7 0 1, -5 0 1)
- num1 en rango [-7, 9]
- Escribir en offsets que NO sean 8-15

## Próximos Pasos a Investigar

1. ¿Hay alguna verificación o validación que pase por alto?
2. ¿El "misalignment" se refiere a algo más que offsets?
3. ¿Puedo construir el canario correcto SOLO con -5 y otros offsets (no -6)?
4. ¿Hay un truco con el formato de los números o la forma de enviarlos?
5. ¿Debo considerar el stack canary de GCC (var_8h) en lugar del canario del reto?