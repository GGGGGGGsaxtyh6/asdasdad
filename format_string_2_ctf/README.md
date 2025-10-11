# Format String 2 - picoCTF

## Estado Actual

La instancia del challenge ha expirado. Se necesita lanzar una nueva instancia.

## Cómo resolver

Una vez tengas una nueva instancia activa:

```bash
cd /workspace/format_string_2_ctf
python3 exploit.py <HOST> <PORT>
```

Por ejemplo:
```bash
python3 exploit.py rhea.picoctf.net 58421
```

Si el exploit principal no funciona, prueba el exploit manual que intenta múltiples técnicas:

```bash
python3 manual_exploit.py <HOST> <PORT>
```

## Información del Challenge

- **Vulnerabilidad**: Format string en `printf(buf)`
- **Objetivo**: Cambiar la variable global `sus` (en 0x404060) de 0x21737573 a 0x67616c66
- **Restricción**: `scanf("%1024s")` se detiene en bytes NULL
- **Offset del buffer**: 14

## Técnicas Implementadas

El exploit intenta las siguientes técnicas automáticamente:

1. **Pwntools standard**: `fmtstr_payload()` con offset 14
2. **Offsets variables**: Prueba offsets del 10 al 20
3. **Payload manual**: Construcción manual byte-a-byte
4. **Brute force**: Prueba si algún offset en el stack apunta a `sus`

## Archivos

- `exploit.py` - Exploit principal usando pwntools
- `manual_exploit.py` - Múltiples técnicas de exploit
- `check_and_exploit.sh` - Script que verifica el servidor y ejecuta el exploit
- `vuln` - Binario original descargado
- `vuln.c` - Código fuente
- `notes/analisis.txt` - Análisis detallado del problema

## Notas Técnicas

El desafío presenta una restricción inusual: `scanf("%s")` corta la entrada en el primer byte NULL, lo que impide incluir direcciones de 64-bit completas (que siempre contienen bytes NULL).

A pesar de exhaustivo análisis, el payload estándar de pwntools parece no funcionar localmente debido a esta restricción. Sin embargo, 2576 usuarios han resuelto este challenge, lo que sugiere que:

1. El payload funciona en el servidor real por diferencias en el entorno
2. Existe una técnica avanzada específica no implementada aún
3. El binario del servidor tiene alguna particularidad

El exploit está configurado para probar automáticamente múltiples aproximaciones cuando se conecte al servidor real.

## ¿Servidor no responde?

Si ves "Connection refused", la instancia ha expirado. Lanza una nueva desde:
https://play.picoctf.org/practice/challenge/[challenge-id]

Luego actualiza el HOST y PORT en los comandos anteriores.
