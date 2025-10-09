# Free Spirit - Estado Final del Análisis

## Resumen Ejecutivo

**Desafío**: Free Spirit (pwnable.xyz)  
**Puntos**: 100  
**Descripción**: "Free is misbehaving"  
**Servidor**: svc.pwnable.xyz:30005  

## Vulnerabilidad Identificada

El binario tiene una vulnerabilidad de **arbitrary write** a través de la opción 3:

```c
// Pseudocódigo de opción 3
void *heap_ptr = malloc(0x40);  // En rsp+0x10

// Opción 3: copia 16 bytes desde *heap_ptr a rsp+0x08
memcpy(rsp+0x08, *heap_ptr, 16);  // Sobrescribe rsp+0x08 a rsp+0x17
                                   // Incluye heap_ptr mismo (rsp+0x10)!
```

## Exploit Desarrollado

### Técnica
1. **Leak de stack**: Opción 2 muestra `heap_ptr_addr` (dirección en stack donde está heap_ptr)
2. **Calcular ret_addr**: `ret_addr = heap_ptr_addr + 0x58` 
3. **Sobrescribir heap_ptr**: Usar opción 3 para hacer `heap_ptr = ret_addr`
4. **Escribir win()**: Usar opción 1 para escribir dirección de `win()` en `ret_addr`
5. **Triggear return**: Opción 0 debería ejecutar return a `win()`

### Verificación del Exploit
- ✅ Leak funciona correctamente
- ✅ Cálculo de direcciones verificado con gdb
- ✅ Sobrescritura de heap_ptr funciona
- ✅ win() se escribe correctamente en ret_addr
- ❌ **PROBLEMA**: `free(heap_ptr)` crashea antes de llegar al `return`

## El Problema de free()

### Flujo de Opción 0
```assembly
400898a9:  mov heap_ptr, %rdi
4008ae:  test %rdi, %rdi
4008b1:  jne free_it
4008b3:  call exit@plt        # Si heap_ptr == NULL
...
4008bd:  call free@plt       # free(heap_ptr)  <- CRASHEA AQUÍ
4008c2:  xor %eax, %eax
4008c4:  # verificar canary
4008d9:  ret                  # <- Nunca llega aquí
```

### Por Qué Crashea
- `heap_ptr` ha sido sobrescrito para apuntar a `ret_addr` (dirección en stack)
- `free(stack_address)` es inválido
- glibc detecta el puntero corrupto y llama `malloc_printerr()` -> `abort()` -> SIGABRT
- El programa termina SIN ejecutar el `return` ni llamar a `win()`

## Intentos de Solución

### 1. Fake Chunks en Stack ❌
- Intenté crear metadata de chunk válida en el stack
- glibc moderna (2.27+) tiene demasiadas verificaciones
- free() rechaza el fake chunk

### 2. Fake Chunks en .bss ❌  
- Intenté usar la variable `limit` en .bss
- Mismo problema: verificaciones de glibc

### 3. heap_ptr = NULL ❌
- Si `heap_ptr == 0`, llama `exit(1)` en lugar de `free()`
- `exit()` NO ejecuta `return`, termina el proceso directamente

### 4. Esperar alarm(60) ❌
- El alarm handler también llama `exit(1)`
- No ejecuta `return`

### 5. ROP Chain ❌
- Necesitaría sobrescribir múltiples ubicaciones en stack
- Mismo problema: free() crashea antes de llegar a los gadgets

### 6. Sobrescribir GOT ❌
- Full RELRO: GOT es de solo lectura

## Archivos Generados

- `/workspace/work/exploit.py` - Exploit completo (funciona hasta free())
- `/workspace/work/retry_exploit.sh` - Script para reintentar automáticamente
- `/workspace/work/TODO.md` - Lista detallada de tareas
- `/workspace/work/core` - Core dump del crash local

## Próximos Pasos

### Hipótesis Pendientes
1. **glibc Vieja en Servidor**: El servidor podría usar glibc 2.17-2.26 con verificaciones más laxas
   - Descargar glibc vieja y probar localmente
   - Seguir intentando en servidor remoto

2. **Detalle No Visto**: Podría haber algún aspecto del desafío que no entiendo
   - Revisar si "Free is misbehaving" es una pista específica
   - Buscar técnicas alternativas de heap exploitation

3. **Approach Diferente**: Tal vez no debo sobrescribir ret_addr
   - Explorar sobrescribir otras estructuras
   - Buscar vulnerabilidades adicionales

## Comando para Continuar

```bash
# Exploited automatizado (corriendo en background)
/workspace/work/retry_exploit.sh

# Ver intentos
tail -f /workspace/work/attempts.log

# Probar manualmente
python3 /workspace/work/exploit.py remote
```

## Conclusión Actual

El exploit está **99% completo** pero bloqueado por el problema de `free()`. 

La solución probablemente involucra:
- Una técnica específica para evitar el crash de free()
- Un detalle de implementación de glibc que no conozco
- Un approach completamente diferente que no he considerado

**Estado**: BLOQUEADO - Requiere más investigación o ayuda externa
