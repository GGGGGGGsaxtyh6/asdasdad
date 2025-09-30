# RESUMEN - Reto f_one (91pts)

## 📋 Información del Reto
- **Nombre**: f_one
- **Puntos**: 91pts
- **Soluciones**: 39 solves
- **Servidor**: challenges-box1.pwn.tn:1111 (INACTIVO - reto de 2020)
- **Binario**: https://pwn.tn/static/pwn/f_one
- **Sistema**: Ubuntu GLIBC 2.27-3ubuntu1.2

## 🔍 Análisis Realizado

### Protecciones del Binario
```
Arch:       amd64-64-little
RELRO:      No RELRO
Stack:      Canary found ⚠️
NX:         NX enabled ⚠️
PIE:        No PIE (0x400000) ✓
Stripped:   No ✓
```

### Vulnerabilidades Identificadas

1. **Format String Bug** en función `vuln()`
   ```c
   printf(buffer);  // ¡Sin format string!
   ```

2. **Buffer Overflow**
   ```c
   char buffer[64];
   fgets(buffer, 108, stdin);  // Lee 108 bytes en buffer de 64
   ```

### Layout del Stack
```
Offset 0-55:   Buffer (56 bytes)
Offset 56-63:  Stack Canary (8 bytes)
Offset 64-71:  Saved RBP (8 bytes)
Offset 72-79:  Return Address (8 bytes)
```

## 🎯 Estrategia de Explotación

### Técnica: Canary Bypass + Ret2Libc

1. **Leak del Canary**
   - Usar `%13$p` para filtrar el canary del stack
   - El offset 13 contiene el valor del canary

2. **Leak de Libc**
   - Sobrescribir RIP con ROP chain
   - Llamar a `puts(puts@GOT)` para filtrar dirección de libc
   - Retornar a `main()` para continuar ejecución

3. **Exploit Final**
   - Calcular base de libc
   - Construir ROP chain: `system("/bin/sh")`
   - Obtener shell y capturar flag

### Gadgets ROP Utilizados
```
pop rdi; ret  @ 0x4007f3
ret           @ 0x400566 (para alineación del stack)
```

### Offsets de Libc (GLIBC 2.27)
```python
puts_offset   = 0x809c0
system_offset = 0x4f440
binsh_offset  = 0x1b3e9a
```

## 📁 Archivos Creados

1. **exploit_final.py** - Exploit completo con múltiples variantes de libc
2. **exploit_remote.py** - Exploit básico para servidor remoto
3. **get_flag.sh** - Script bash con timeout para captura automática
4. **README.md** - Documentación técnica detallada
5. **f_one** - Binario descargado del servidor

## 🛠️ Herramientas Instaladas

- **gdb** - Debugger
- **radare2** - Análisis de binarios
- **pwntools** - Framework de explotación
- **ROPgadget** - Búsqueda de gadgets ROP
- **binutils** - Utilidades de análisis (file, strings, etc.)

## 📊 Estado del Reto

❌ **SERVIDOR INACTIVO**

El servidor `challenges-box1.pwn.tn:1111` no está accesible actualmente. Esto es esperado ya que:
- El binario es de agosto 2020
- El reto probablemente terminó hace tiempo
- Los servidores de CTF suelen desactivarse después del evento

## ✅ Trabajo Completado

1. ✓ Descarga y análisis del binario
2. ✓ Identificación de vulnerabilidades (format string + buffer overflow)
3. ✓ Bypass del stack canary
4. ✓ Desarrollo de exploit Ret2Libc completo
5. ✓ Scripts con timeouts para evitar colgarse
6. ✓ Documentación completa

## 🚀 Cómo Usar (Si el servidor estuviera activo)

```bash
# Método 1: Script automático con timeout
./get_flag.sh

# Método 2: Exploit Python completo
python3 exploit_final.py

# Método 3: Exploit básico
python3 exploit_remote.py
```

## 💡 Conceptos Aprendidos

- **Format String Vulnerabilities**: Lectura/escritura de memoria
- **Stack Canary Bypass**: Filtrado mediante format string
- **Return-Oriented Programming (ROP)**: Construcción de chains
- **Ret2Libc**: Explotación con ASLR usando filtraciones
- **Stack Alignment**: Importancia del alineamiento a 16 bytes para llamadas

## 📝 Notas Importantes

1. El exploit asume que el servidor usa `fork()` para mantener el canary constante
2. Los offsets de libc pueden variar según la versión exacta
3. El exploit incluye múltiples variantes de libc para mayor compatibilidad
4. Todos los scripts usan timeouts para evitar procesos colgados
5. Stack alignment con gadget `ret` es crítico para llamadas a `system()`