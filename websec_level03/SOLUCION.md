# SOLUCIÓN WEBSEC LEVEL03 - ChaChaCha

## FLAG
```
WEBSEC{Please_Do_not_combine_rAw_hash_functions_mi}
```

## RESUMEN DE LA VULNERABILIDAD

La aplicación PHP tiene un **typo crítico** en el código:
- Usa `fa1se` (con número 1) en lugar de `false` (con letra l)
- PHP interpreta `fa1se` como una constante no definida, convirtiéndola al string "fa1se"
- Cuando se pasa a `sha1()`, cualquier string no vacío se evalúa como `TRUE`
- Esto hace que `sha1()` devuelva el hash en formato **BINARIO** (20 bytes) en lugar de hexadecimal

## EXPLOTACIÓN

### Paso 1: Identificar el problema
```php
$h2 = password_hash(sha1($_POST['c'], fa1se), PASSWORD_BCRYPT);
```
y
```php
if (password_verify(sha1($flag, fa1se), $h2) === true)
```

### Paso 2: Obtener el hash de la flag
Enviando cualquier valor, obtenemos:
```
Hash SHA1: 7c00249d409a91ab84e3f421c193520d9fb3674b
```

### Paso 3: Identificar byte nulo
Al convertir el hash hex a binario:
```
Byte 0: 0x7c
Byte 1: 0x00  ← BYTE NULO
```

### Paso 4: Vulnerabilidad de truncamiento
`password_hash()` trunca la entrada en el primer byte nulo (`\0`). 
Esto significa que solo necesitamos que nuestro input tenga un `sha1(input, TRUE)` 
que empiece con `0x7c 0x00`.

### Paso 5: Buscar colisión parcial
Ejecutando un brute-force simple encontramos:
```
Input: 104610
SHA1 binario: 7c00f12c6e5cc9bd7239209971d5997c6953aba4
Primeros 2 bytes: 7c00 ✓
```

### Paso 6: Explotar
```bash
curl -X POST https://websec.fr/level03/index.php -d "c=104610"
```

Resultado: ¡Flag obtenida!

## LECCIÓN

**Nunca combines funciones de hash raw con password_hash/password_verify**

El mensaje de la flag lo confirma: "Please_Do_not_combine_rAw_hash_functions_mi"

La vulnerabilidad ocurre porque:
1. Los hashes binarios pueden contener bytes nulos
2. `password_hash()` (que usa bcrypt) trunca en bytes nulos
3. Esto reduce drásticamente el espacio de colisiones necesarias

En este caso, solo necesitábamos coincidir 2 bytes (16 bits), lo que tiene una probabilidad de 1 en 65,536, fácilmente brute-forceable.

## ARCHIVOS GENERADOS
- `analisis.txt` - Análisis inicial de la vulnerabilidad
- `exploit_analysis.php` - Script para analizar el hash binario
- `find_collision.php` - Script para encontrar la colisión
- `FLAG.txt` - La flag obtenida
- `SOLUCION.md` - Este archivo