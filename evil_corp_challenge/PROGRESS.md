# Evil Corp - Estado Actual del Exploit

## Trabajo Realizado
1. ✅ Análisis completo del binario
2. ✅ Identificación de vulnerabilidades
3. ✅ Exploit funciona localmente (control de RIP confirmado)
4. ❌ Exploit no funciona remotamente

## Vulnerabilidades Confirmadas
- **Buffer Overflow en ContactSupport()**: Confirmado localmente
  - fgetws lee hasta 4096 wide chars (16384 bytes)
  - Buffer de solo 16000 bytes
  - Overflow de 384 bytes permite sobrescribir RIP
  - Offset a RIP: 4002 caracteres Unicode

- **Memoria RWX en 0x11000**: AssemblyTestPage con permisos de ejecución

## Problema Principal
El shellcode escrito a través de wcharToChar16 tiene bytes null intermedios:
- Cada byte del shellcode se convierte a char16 (2 bytes)
- Formato en memoria: `XX 00 YY 00 ZZ 00 ...`
- Esto rompe la ejecución del shellcode

## Intentos Realizados
1. Shellcode directo -> Falla por null bytes
2. Shellcode con JMP para saltar nulls -> Complejo
3. ROP chain -> Mismo problema de encoding
4. INT3 para verificar ejecución -> Sin respuesta
5. Payloads varios contra remoto -> Servidor cierra conexión

## Próximos Pasos Sugeridos
1. Crear exploit ROP puro que use funciones existentes
2. Investigar si hay técnicas de "polyglot shellcode"
3. Verificar si hay diferencias entre binario local y remoto
4. Considerar ataque de múltiples etapas

## Información del Servidor
- Host: 83.136.250.244:56528
- Credenciales: eliot/4007
- Comportamiento: Cierra conexión después del payload sin crash visible
