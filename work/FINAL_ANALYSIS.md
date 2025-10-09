# Free Spirit - Análisis Exhaustivo Final

## Challenge Info
- **Nombre**: Free Spirit
- **Puntos**: 100
- **Solves**: 458 personas
- **Servidor**: svc.pwnable.xyz:30005
- **Descripción**: "Free is misbehaving"

## Vulnerabilidad Core

### Opción 3 - Arbitrary Write
```c
// Pseudocódigo
void option_3() {
    if (limit > 1) return;  // Solo una vez
    
    // Copia 16 bytes desde *heap_ptr a stack
    memcpy(rsp+0x08, *heap_ptr, 16);
    
    // Esto sobrescribe:
    // - rsp+0x08 a rsp+0x0f: 8 bytes (no usado)
    // - rsp+0x10 a rsp+0x17: heap_ptr mismo!
}
```

### Stack Layout
```
rsp+0x00 - 0x07: ???
rsp+0x08 - 0x0f: Destino opción 3 (no usado después)
rsp+0x10 - 0x17: heap_ptr (8 bytes)
rsp+0x18 - 0x47: Input buffer (48 bytes)
rsp+0x48 - 0x4f: Stack canary (8 bytes)
rsp+0x50 - 0x57: Saved RBX (8 bytes)
rsp+0x58 - 0x5f: Saved RBP (8 bytes)
rsp+0x60 - 0x67: Saved R12 (8 bytes)
rsp+0x68 - 0x6f: Return address (8 bytes)
```

## Exploit Funcional (Hasta Free)

```python
# 1. Leak stack
opción_2()  # Muestra heap_ptr_addr (dirección de heap_ptr en stack)

# 2. Calcular ret_addr
ret_addr = heap_ptr_addr + 0x58  # (0x68 - 0x10)

# 3. Preparar heap
opción_1(p64(0) + p64(ret_addr))  # Escribir [pad][ret_addr] en heap

# 4. Sobrescribir heap_ptr
opción_3()  # Copia heap a stack, heap_ptr = ret_addr

# 5. Sobrescribir ret_addr
opción_1(p64(WIN_ADDR))  # Escribir win() en ret_addr

# 6. Triggear return
opción_0()  # PROBLEMA: free(ret_addr) crashea aquí!
```

## El Problema Insuperable

### Flujo de Opción 0
```assembly
mov    heap_ptr, %rdi
test   %rdi, %rdi
jne    free_it
call   exit@plt         # Si heap_ptr == NULL

free_it:
call   free@plt         # free(heap_ptr) <- CRASHEA
xor    %eax, %eax
mov    canary, %rdx
# verificar canary
ret                     # <- Nunca llega aquí
```

### Por Qué Crashea
- heap_ptr apunta a ret_addr (dirección en stack)
- glibc detecta que la dirección no es válida para free()
- Llama a `malloc_printerr()` -> `abort()` -> SIGABRT
- El programa termina SIN ejecutar `ret`

## Intentos de Solución (TODOS FALLIDOS)

### 1. Fake Chunk en Stack ❌
- Creé metadata de chunk válida en el stack
- glibc moderna verifica la dirección base
- Rechazado

### 2. Fake Chunk en .bss ❌
- Variable `limit` en 0x60102c
- Misma verificación de glibc
- Rechazado

### 3. heap_ptr = NULL ❌
- Llama `exit(1)` en vez de free()
- `exit()` NO ejecuta return
- No funciona

### 4. Esperar alarm(60) ❌
- Handler también llama `exit(1)`
- No ejecuta return
- No funciona

### 5. ROP Chain ❌
- free() crashea ANTES de llegar a gadgets
- No funciona

### 6. Sobrescribir GOT ❌
- Full RELRO
- GOT es read-only
- Imposible

### 7. Sobrescribir desde saved_rbx ❌
- Puedo sobrescribir ret_addr con 32 bytes desde rsp+0x50
- Pero heap_ptr queda apuntando a saved_rbx
- free(saved_rbx) crashea
- Mismo problema

### 8. Múltiples opción 3 ❌
- Solo puedo usar opción 3 UNA vez
- Variable `limit` lo previene
- No funciona

### 9. Leer heap_ptr original ❌
- No hay primitiva para leerlo
- Opción 2 muestra &heap_ptr, no heap_ptr
- Imposible restaurar

### 10. Buffer Overflow ❌
- No hay overflow útil
- Stack canary protege
- No funciona

## Hipótesis Pendientes

### 1. glibc Vieja en Servidor
**Teoría**: El servidor usa glibc 2.19 o anterior sin verificaciones estrictas
**Probabilidad**: Alta (el binario es de 2018)
**Prueba**: Intentar exploit simple muchas veces

### 2. Condición de Carrera
**Teoría**: Hay alguna race condition que no he visto
**Probabilidad**: Baja
**Prueba**: N/A

### 3. Detalle de Implementación Desconocido
**Teoría**: Hay algo de la implementación de free() que no sé
**Probabilidad**: Media
**Prueba**: Estudiar código fuente de glibc 2.20-2.27

### 4. Solución Completamente Diferente
**Teoría**: No debo sobrescribir ret_addr en absoluto
**Probabilidad**: Baja (no veo otra forma)
**Prueba**: Revisar todo el código otra vez

## Archivos Generados

- `/workspace/work/exploit.py` - Exploit completo (crashea en free)
- `/workspace/work/simple_exploit.py` - Exploit simplificado
- `/workspace/work/retry_exploit.sh` - Script de intentos automáticos
- `/workspace/work/STATUS.md` - Estado detallado
- `/workspace/work/TODO.md` - Lista de tareas
- `/workspace/work/massive_attempts.log` - Log de intentos masivos

## Conclusión

**Estado**: BLOQUEADO al 99%

El exploit funciona PERFECTAMENTE hasta el momento de `free()`. El cálculo de direcciones es correcto, la sobrescritura funciona, todo está bien. 

El ÚNICO problema es que `free(stack_address)` crashea y no llega al `return`.

**Próximos pasos**:
1. Continuar intentos masivos (por si el servidor tiene comportamiento no determinístico)
2. Estudiar código fuente de glibc 2.20-2.27 para encontrar bypasses
3. Probar con glibc 2.19 localmente
4. Considerar que puede haber un detalle que simplemente no veo

**Tiempo invertido**: Varias horas
**Intentos en servidor**: 50+
**Técnicas probadas**: 10+

---

*"Free is misbehaving"* - Y ciertamente lo está...
