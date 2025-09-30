#!/usr/bin/env python3
"""
Solución final para el reto XOR

Estrategia:
1. La función __do_global_ctors_aux llama a mprotect haciendo ejecutable
   la página de código
2. Puedo escribir en result[] usando XOR
3. Necesito hacer que la ejecución salte a result donde tendré shellcode
4. Como no tengo control directo del RIP, debo corromper stdin
   para que cuando se procese, cause un salto a result

Idea alternativa:
Si corrompo stdin de tal manera que cuando scanf falle,
el programa termine y ejecute exit handlers que apunten a result.

Pero wait... no puedo controlar los exit handlers sin conocer direcciones.

Nueva idea:
¿Y si corrompo el puntero vtable de stdin para que apunte cerca de result?
Cuando se use stdin, llamará a funciones de la vtable.

Pero necesito saber la dirección de result con PIE...

MOMENTO! result está en BSS. BSS está a offset fijo desde el código.
Si el código se hace ejecutable con mprotect, ¡BSS también podría
estar en una página ejecutable o cerca!

Déjame verificar los offsets:
- código: 0x970 (donde está __do_global_ctors_aux)
- result: 0x202200

Offset: 0x202200 - 0x970 = 0x201890

¡Demasiado lejos para estar en la misma página!

OK, nueva estrategia: necesito hacer ROP o encontrar algún puntero
que pueda redirigir. Como stdin está justo antes de result, 
tal vez puedo usar eso...

¡ESPERA! Déjame ver si scanf tiene algún comportamiento especial
cuando stdin está corrupto que pueda aprovechar...