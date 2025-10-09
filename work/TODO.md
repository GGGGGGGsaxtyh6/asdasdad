# Free Spirit - Estado del Exploit

## Progreso Actual

### ✅ Completado:
1. Análisis del binario
   - Identificada vulnerabilidad en opción 3 (arbitrary write via heap_ptr sobrescrito)
   - Leak de stack con opción 2
   - Función win() en 0x400a3e que ejecuta "cat /flag"

2. Desarrollo de exploit básico
   - Leak stack address
   - Calcular ret_addr = stack_leak + 0x58
   - Sobrescribir heap_ptr para apuntar a ret_addr
   - Escribir win() en ret_addr

### ❌ Problema Actual:
- Opción 0 hace `free(heap_ptr)` antes de return
- Si heap_ptr apunta a dirección de stack (ret_addr), free() crashea con SIGABRT
- Programa termina sin ejecutar return ni win()

## Intentos Realizados

1. **Fake chunks en stack**: free() rechaza por verificaciones de glibc
2. **Fake chunks en .bss**: free() rechaza
3. **heap_ptr = NULL**: llama exit() en lugar de free(), no ejecuta return
4. **ROP chain**: mismo problema, necesita return
5. **Sobrescribir GOT**: Full RELRO, no posible
6. **Multiple opción 3**: heap_ptr cambia y no puedo preparar más datos en heap original

## Posibles Soluciones a Explorar

1. **Versión vieja de glibc en servidor**
   - El servidor podría usar glibc 2.27 o anterior (binario es de 2018)
   - Versiones viejas podrían tener verificaciones más laxas en free()
   - **ACCIÓN**: Seguir probando en servidor remoto

2. **Técnica no convencional**
   - ¿Hay alguna forma de triggear win() sin return?
   - ¿Sobrescribir algo más que cause ejecución?
   - ¿Algún detalle del challenge que no veo?

3. **Fake chunk más sofisticado**
   - Crear metadata completamente válida para engañar a free()
   - Requiere conocimiento profundo de internals de malloc

4. **Bypass alternativo**
   - ¿Hay otra vulnerabilidad que no he visto?
   - ¿El nombre "Free Spirit" es una pista que no entiendo?

## Comandos para Seguir Probando

```bash
# Probar en remoto
python3 exploit.py remote

# Ver core dumps
gdb -batch -ex 'core core' -ex 'bt'

# Analizar con diferentes offsets
# (ya probado: 0x50, 0x58, 0x60, 0x68, 0x70)
```

## Notas

- Exploit funciona PERFECTAMENTE hasta el punto de free()
- Cálculo de direcciones verificado y correcto
- El servidor responde normalmente a otras opciones
- Rate limiting en el servidor después de varios intentos
