# ChronoVM - Reto de Reversing Insane

## 📋 Descripción del Reto

**Nombre:** chronovm  
**Categoría:** Reversing  
**Dificultad:** Insane (~6–8 horas)  
**Flag:** `HTB{ChronoVM_TimeLock_VirtualMachine}`

### 🎯 Descripción para el Jugador

"Un viejo programa llamado ChronoVM ha aparecido en un sistema abandonado. Parece un simple reloj digital, pero en realidad esconde un mecanismo de validación muy elaborado. Tu misión es descubrir cómo funciona y revelar el secreto que protege. El tiempo corre…"

## 🔧 Características Técnicas

### Protecciones Anti-Debugging
- **ptrace()** - Detección de debuggers
- **prctl(PR_SET_DUMPABLE)** - Deshabilitación de core dumps
- **rdtsc timing checks** - Verificación de velocidad de ejecución
- **Self-integrity checks** - Verificación de integridad del binario
- **Breakpoints falsos** - Trampas para confundir debuggers

### Ofuscación Múltiple
- **Control flow flattening** - Aplanamiento del flujo de control
- **Secciones .text mezcladas** - Código disperso con trampas
- **Código polimórfico** - Reescritura en runtime
- **Strings ofuscados** - Strings cifrados con XOR

### Máquina Virtual Interna
- **15 instrucciones personalizadas:**
  - `VM_NOP` (0x00) - No operation
  - `VM_LOAD` (0x01) - Cargar valor inmediato
  - `VM_STORE` (0x02) - Almacenar en memoria
  - `VM_ADD` (0x03) - Suma de registros
  - `VM_SUB` (0x04) - Resta de registros
  - `VM_MUL` (0x05) - Multiplicación
  - `VM_XOR` (0x06) - XOR lógico
  - `VM_SHL` (0x07) - Shift izquierda
  - `VM_SHR` (0x08) - Shift derecha
  - `VM_CMP` (0x09) - Comparación
  - `VM_JMP` (0x0A) - Salto incondicional
  - `VM_JZ` (0x0B) - Salto si cero
  - `VM_CALL` (0x0C) - Llamada a función
  - `VM_RET` (0x0D) - Retorno
  - `VM_HALT` (0x0E) - Parada

### Criptografía Híbrida
1. **SHA1 Modificado** - Hash personalizado con constantes alteradas
2. **Caja S Personalizada** - Sustitución no lineal de 256 bytes
3. **Autómata Celular** - Regla 30 para generación de checksum final

### Sistema de Fragmentos
- Fragmentos de flag escritos en `/dev/shm/chrono_frag_*`
- Solo se activan con argumentos específicos
- Ensamblaje dinámico en memoria volátil

## 🚀 Instalación y Uso

### Compilación
```bash
chmod +x build.sh
./build.sh
```

### Ejecución
```bash
# Ejecución básica
./chronovm

# Con fragmentos de flag
./chronovm --enable-fragments
```

### Pruebas
```bash
chmod +x test_challenge.sh
./test_challenge.sh
```

## 🔍 Ruta de Explotación Esperada

### Fase 1: Neutralizar Protecciones (1-2h)
1. Localizar y parchear checks de integridad
2. Bypass de protecciones anti-debugging
3. Análisis de self-integrity checks

### Fase 2: Mapear la VM (2h)
1. Extraer y documentar instrucciones custom
2. Analizar bytecode disperso
3. Reconstruir flujo de ejecución real

### Fase 3: Deofuscar Bytecode (2h)
1. Encontrar bloques cifrados con XOR+RC4
2. Derivar clave de la hora del sistema
3. Descifrar y reconstruir bytecode

### Fase 4: Ingeniería Inversa Criptográfica (2h)
1. Entender SHA1 modificado
2. Analizar caja S personalizada
3. Modelar autómata celular (regla 30)

### Fase 5: Reconstrucción de Flag (1-2h)
1. Identificar fragmentos en /dev/shm
2. Descubrir secuencia de entrada correcta
3. Ensamblar flag final

## 🎯 Solución

### Clave de Validación
```
ChronoVM2024
```

### Flag Final
```
HTB{ChronoVM_TimeLock_VirtualMachine}
```

### Checksum Esperado
```
0x42A433D3
```

## 📁 Estructura del Proyecto

```
chronovm/
├── chronovm.c              # Código fuente principal
├── build.sh                # Script de compilación
├── test_challenge.sh       # Script de pruebas
├── final_test.sh          # Prueba final completa
├── calculate_flag.py      # Calculadora de flag
├── advanced_obfuscation.py # Script de ofuscación
├── post_process.py        # Post-procesamiento de secciones
├── chronovm               # Binario compilado
└── README.md              # Esta documentación
```

## 🛠️ Herramientas Recomendadas

- **GDB** - Debugger principal
- **Ghidra** - Análisis estático
- **IDA Pro** - Análisis avanzado
- **Radare2** - Análisis de binarios
- **objdump** - Desensamblador
- **strings** - Extracción de strings
- **hexdump** - Análisis hexadecimal

## ⚠️ Notas Importantes

- El binario está compilado como estático y strippeado
- Las protecciones anti-debugging están activas
- El bytecode está cifrado y disperso
- Los fragmentos de flag solo aparecen con argumentos específicos
- El algoritmo criptográfico es híbrido y personalizado

## 🏆 Nivel de Dificultad

**Insane** - Requiere conocimientos avanzados en:
- Reversing de binarios ELF
- Bypass de protecciones anti-debugging
- Análisis de máquinas virtuales
- Criptografía personalizada
- Ingeniería inversa de algoritmos complejos

**Tiempo estimado:** 6-8 horas de trabajo intensivo