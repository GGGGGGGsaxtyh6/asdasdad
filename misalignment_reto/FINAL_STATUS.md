# Estado Final - Reto Misalignment

## Descubrimiento Clave

Al escribir con num1=-6 en offset 8-15, solo valores ≤9 funcionan.
Valores ≥10 causan que el programa termine silenciosamente.

Este límite NO aparece en el código desensamblado.
No hay verificaciones adicionales de los valores escritos.

## Valores Probados con num1=-6

- Valores 1-9: ✓ Funcionan
- Valor 10+: ✗ Fallan

## Conclusión

Para resolver este reto necesito:
1. Escribir 0xb5 (181) en byte 15 → requiere num1=-6 con valor 181
2. Pero valores >9 no funcionan con num1=-6
3. Por lo tanto, no puedo escribir 0xb5 de manera directa

## Posibles Explicaciones

1. Hay una protección o validación que no logré identificar en el assembly
2. El comportamiento está relacionado con alguna característica de libc/scanf que desconozco
3. El enfoque correcto es completamente diferente y no involucra escribir valores grandes
4. Hay un truco o técnica específica de "misalignment" que no estoy aplicando

## Archivos del Reto

Todos los archivos están en `/workspace/misalignment_reto/`:
- `image/challenge/challenge` - Binario
- Varios scripts de análisis y prueba
- Notas detalladas

## Recomendación

Este reto requiere más investigación o un hint. He agotado mis ideas actuales.