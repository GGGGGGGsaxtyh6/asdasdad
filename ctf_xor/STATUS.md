# Estado del Reto XOR

## Análisis Completado
- ✅ Binario ELF 64-bit con PIE habilitado
- ✅ Función `win()` que ejecuta "cat flag"
- ✅ Vulnerabilidad: escritura arbitraria mediante índices negativos en array `result`
- ✅ Puedo escribir valores XOR en `result[index]` donde index puede ser negativo
- ✅ Con índices negativos puedo sobrescribir `stdin` (estructura FILE)
- ✅ Función `__do_global_ctors_aux` usa `mprotect` para hacer código ejecutable

## Problema Principal
- ❌ PIE habilitado: no sé la dirección de `win()` en runtime
- ❌ Full RELRO: no puedo sobrescribir GOT
- ❌ No hay forma obvia de obtener leak de direcciones
- ❌ No tengo control directo sobre RIP

## Enfoques Intentados
1. Sobrescribir stdin vtable -> Requiere conocer direcciones
2. Escribir shellcode en result -> Requiere forma de ejecutarlo
3. FILE structure exploitation -> Requiere leaks para apuntar a win()

## Lo Que Falta
- Encontrar cómo hacer leak de direcciones, O
- Encontrar una forma de ejecutar código sin conocer direcciones absolutas, O
- Descubrir si el binario remoto es diferente (sin PIE)

## Próximos Pasos
- Re-analizar completamente el reto con enfoque fresh
- Buscar si hay alguna técnica de FILE exploitation que no requiera leaks
- Considerar si hay alguna forma de ROP relativo
- Verificar si hay funciones ocultas o comportamientos especiales