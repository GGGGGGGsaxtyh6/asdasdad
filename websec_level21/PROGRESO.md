# WEBSEC LEVEL21 - En Progreso

## ESTADO: Pendiente de resolución

## LO QUE HE DESCUBIERTO:

### Vulnerabilidades identificadas:

1. **SQL Injection en verify_credentials()**:
   ```php
   $result = $pdo->query("SELECT * FROM users WHERE username='$username' AND password='$password';");
   ```

2. **CBC Mode con AES-128**: La cookie usa CBC que es vulnerable a bit flipping

3. **Filtro bypasseable en registro**: Las mayúsculas pasan el filtro `ctype_alnum()` pero fallan en `preg_match` después de `strtolower()`

### El problema:

- Para explotar el SQL injection, necesito llegar a `verify_credentials()`
- Para llegar ahí, mi cookie debe pasar las validaciones de formato en `auth()`
- Las validaciones requieren que la sesión desencriptada empiece con "user/pass:" y tenga el formato correcto
- Cuando hago CBC bit flipping para modificar el password (donde quiero inyectar SQL), **siempre corrompo bloques anteriores** que contienen:
  - El prefijo "user/pass:" (crítico)
  - El username
  - El separador "/" (crítico)

### Lo que he intentado:

1. **Bit flipping directo**: Corrompe el bloque 0 que tiene "user/pass:"
2. **Usuario largo** (20+ chars): Mueve el password a bloques posteriores, pero el "/" separador sigue en un bloque que se corrompe
3. **Usuario de 37 chars**: Password en bloques 3-4, pero al modificarlos corrompo bloque 2 que tiene el "/"
4. **Null byte truncation**: No funciona en este contexto
5. **Mayúsculas/minúsculas**: No bypasea el filtro de a,d,m,i,n

### Posibles soluciones que NO he explorado completamente:

1. **Padding Oracle Attack**: Descubrir el plaintext exacto y craftear cookie válida
2. **Aprovechar algún bug específico de mcrypt o PHP**: Investigar vulnerabilidades conocidas
3. **Race condition**: Tal vez hay alguna race en el flujo
4. **Otro vector de ataque**: Puede que esté enfocándome en el approach incorrecto

### Comandos útiles:

Registrar usuario sin a,d,m,i,n:
```bash
curl -X POST https://websec.fr/level21/index.php \
  -d "username=bcefgh&password=test123&register=1"
```

Login:
```bash
curl -c cookies.txt -X POST https://websec.fr/level21/index.php \
  -d "username=bcefgh&password=test123&login=1"
```

### Archivos creados:
- codigo_reconstruido.php - Código PHP reconstruido
- exploit*.py - Varios intentos de bit flipping
- cookies*.txt - Cookies de diferentes usuarios

## NECESITA MÁS INVESTIGACIÓN

Este reto requiere una técnica que aún no he identificado correctamente.