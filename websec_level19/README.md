# WebSec Level 19 - Análisis y Progreso

## Resumen del Desafío
- **Título**: "Completely Automated Public Turing test to please Computers and break Humans Apart"
- **Tipo**: CAPTCHA inverso (diseñado para que máquinas lo resuelvan, no humanos)
- **Puntos**: 2
- **Soluciones**: 105

## Análisis Realizado

### Vulnerabilidad Identificada
El desafío usa `srand(microtime(true))` para generar un CAPTCHA predecible. El código fuente muestra:
- **random.php**: Usa `srand(microtime(true))` como seed
- **captcha.php**: Genera una imagen PNG con el texto del CAPTCHA
- **index.php**: Valida el CAPTCHA enviado contra `$_SESSION['captcha']`

### Información del Servidor
- **Server**: nginx
- **PHP Version**: 5.6.26-1
- **Implicación**: PHP 5.6 usa LCG (Linear Congruential Generator) para `rand()`, no Mersenne Twister

### Enfoques Intentados

#### 1. Generación de CAPTCHA con PHP 8.4 (FALLIDO)
- Instalé PHP 8.4 localmente
- Creé `gen_captcha.php` que replica la lógica del servidor
- **Problema**: PHP 8.4 usa MT (Mersenne Twister), PHP 5.6 usa LCG
- Los captchas generados no coinciden

#### 2. OCR para Leer el CAPTCHA (FALLIDO)
- Instalé Tesseract OCR y pytesseract
- Intenté leer el CAPTCHA de la imagen base64
- **Problema**: El CAPTCHA tiene:
  - 1024 puntos aleatorios
  - 8 líneas aleatorias
  - Caracteres en posiciones aleatorias
  - Filtro blur
- OCR falla casi siempre (solo leyó 2 caracteres de 25 una vez)

#### 3. Implementación LCG de PHP 5.6 en Python (FALLIDO)
- Implementé el algoritmo LCG en Python
- Probé con seeds basados en timestamp del servidor
- **Problema**: La implementación no genera los mismos valores que PHP 5.6
- Valores generados son completamente diferentes

#### 4. Sincronización de Timing (FALLIDO)
- Usé el header `Date` del servidor para obtener timestamp
- Probé con offsets de ±30 segundos
- Todos los captchas fueron rechazados como inválidos

## Archivos Creados

### Scripts de Exploit
- `exploit.py` - Primer intento con Python (problema con LCG)
- `exploit_v2.py` - Usando PHP para generar captchas
- `exploit_v3.py` - Con rango ampliado
- `exploit_final.py` - Con timestamp del servidor
- `exploit_ultra.py` - Con pre-generación de captchas
- `brute.py` - Fuerza bruta simplificada
- `simple.py` - Versión simplificada
- `final_exploit.py` - Versión con output detallado
- `exploit_php56.py` - Con algoritmo LCG
- `ocr_exploit.py` - Usando OCR

### Herramientas
- `gen_captcha.php` - Generador de captcha con PHP (MT)
- `gen_captcha_mt.php` - Usando mt_rand
- `php56_rand.py` - Implementación LCG en Python
- `test_srand.php` - Pruebas de srand()
- `test_lcg.php` - Verificación de LCG
- `test_timing.py` - Análisis de timing del servidor
- `debug_session.py` - Verificación de sesiones
- `manual_test.py` - Prueba manual

## Hallazgos Clave

1. **srand() con float se convierte a int**: `srand(microtime(true))` convierte el float a entero
2. **Diferencia PHP 5.6 vs 8.4**: Los algoritmos `rand()` son completamente diferentes
3. **RTT del servidor**: ~0.7-0.8 segundos
4. **Desfase de tiempo**: ~5 segundos entre mi máquina y el servidor
5. **El captcha es de 25 caracteres**: `255 / 10.0` truncado a int

## Próximos Pasos Sugeridos

1. **Implementar correctamente el LCG de PHP 5.6**:
   - Necesito replicar EXACTAMENTE cómo PHP 5.6 implementa `rand()`
   - Posiblemente necesito instalar PHP 5.6 o encontrar la implementación exacta

2. **Ampliar el rango de búsqueda**:
   - Probar con timestamps mucho más amplios (horas, no segundos)
   - El seed podría no ser exactamente `microtime()` del momento del GET

3. **Análisis del comentario en el código**:
   - El código menciona `https://secure.php.net/manual/en/function.srand.php#90215`
   - Este comentario específico podría tener información crucial

## Comandos Útiles

```bash
# Generar captcha con timestamp
php gen_captcha.php 1759203616

# Generar con Python (LCG)
python3 php56_rand.py 1759203616

# Ejecutar exploit
python3 exploit_php56.py

# Ver header del servidor
curl -sI https://websec.fr/level19/index.php
```

## Conclusiones

El desafío requiere:
1. Replicar exactamente el algoritmo `rand()` de PHP 5.6
2. Sincronizar con precisión el timestamp del servidor
3. Enviar el CAPTCHA correcto antes de que expire la sesión

La vulnerabilidad es clara (seed predecible), pero la explotación requiere replicar perfectamente el comportamiento de una versión antigua de PHP.