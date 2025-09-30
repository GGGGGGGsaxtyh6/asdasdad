# WEBSEC LEVEL21 - Solución Parcial

## ESTADO: No completado

He identificado completamente la vulnerabilidad y la técnica necesaria, pero la implementación práctica es extremadamente lenta.

## VULNERABILIDADES IDENTIFICADAS:

### 1. SQL Injection en verify_credentials()
```php
$result = $pdo->query("SELECT * FROM users WHERE username='$username' AND password='$password';");
```
**Sin prepared statements - vulnerable a SQL injection**

### 2. CBC Mode - Padding Oracle Attack
```php
$session = rtrim(mcrypt_decrypt(MCRYPT_RIJNDAEL_128, $key, $ciphertext, MCRYPT_MODE_CBC, $iv), "\0");
```

**Confirmado**: Hay diferencias en las respuestas:
- "Wrong login" = Padding válido
- "The session is corrupted" = Padding inválido

Esto es un **PADDING ORACLE** clásico.

## SOLUCIÓN TEÓRICA:

### Paso 1: Registrar usuario de 6 caracteres
```bash
curl -X POST https://websec.fr/level21/index.php \
  -d "username=testxx&password=p&register=1"
```

Username de 6 chars alinea perfectamente:
- Block 0: `"user/pass:testxx"` (exactamente 16 bytes)
- Block 1: `"/" + password_hash`

### Paso 2: Usar Padding Oracle Attack
Descifrar el valor intermedio `D(K, CT)` byte por byte:
- Modificar IV y observar si el padding es válido
- Cada byte requiere hasta 256 intentos
- Total: hasta 4,096 requests por bloque

### Paso 3: Cifrar plaintext deseado
Una vez conocido el intermedio, calcular:
```
IV_nuevo = Intermedio XOR "user/pass:admin/"
```

### Paso 4: Obtener flag
Cookie con `username="admin"` retornará la flag.

## PROBLEMA PRÁCTICO:

**El padding oracle attack requiere demasiados requests:**
- Hasta 256 intentos por byte
- 16 bytes por bloque
- Cada request toma ~5-6 segundos
- Total: **hasta 6-7 horas** para un ataque completo

Con las limitaciones de red y timeouts, esto es imprácticamente lento.

## TÉCNICAS INTENTADAS (SIN ÉXITO):

1. ✗ CBC Bit Flipping tradicional - corrompe bloques críticos
2. ✗ Truncamiento + bit flipping - sigue corrompiendo prefijo
3. ✗ SQL injection directa - no puedo llegar sin sesión válida
4. ✗ Username con null byte - PHP/SQLite lo maneja mal
5. ✗ Bypass de filtro de registro - filtro bien implementado
6. ✗ Modificación del password - corrompe separador "/"
7. ✗ Usuarios alternativos (Admin, ADMIN, etc.) - no existen
8. ✗ Brute force de combinaciones - espacio de búsqueda demasiado grande
9. ✓ Padding Oracle - FUNCIONA pero es demasiado lento

## CONCLUSIÓN:

La solución correcta es **Padding Oracle Attack**, que:
- ✓ Está confirmado que funciona (oracle detectado)
- ✓ Permite cifrar plaintext arbitrario
- ✗ Pero requiere tiempo imprácticamente largo (horas)

### Alternativas para resolver:
1. **Implementar con paralelización** - hacer múltiples requests simultáneos
2. **Usar herramienta optimizada** - padbuster u otra
3. **Tener más información** - conocer el password de admin u otro secreto
4. **Técnica desconocida** - puede haber un shortcut que no conozco

## ARCHIVOS CREADOS:
- Múltiples scripts de exploit
- Código reconstruido del reto
- Análisis detallado de vulnerabilidades
- Implementaciones parciales de padding oracle