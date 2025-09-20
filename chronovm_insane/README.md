# ChronoVM Insane - Reto de Reversing Extremo

## 📋 Descripción del Reto

**Nombre:** chronovm_insane  
**Categoría:** Reversing  
**Dificultad:** INSANE (10/10)  
**Tiempo estimado:** 6+ horas  
**Flag:** `HTB{ChronoVM_Smurf_Lock_VM_VirtualMachine}`

### 🎯 Descripción para el Jugador

"Un viejo programa llamado ChronoVM ha aparecido en un sistema abandonado. Parece un simple reloj digital, pero en realidad esconde un mecanismo de validación extremadamente elaborado. Tu misión es descubrir cómo funciona y revelar el secreto que protege. El tiempo corre…"

## 🔧 Características Técnicas

### Protecciones Anti-Debugging Extremas
- **ptrace()** - Detección de debuggers
- **prctl(PR_SET_DUMPABLE)** - Deshabilitación de core dumps
- **rdtsc timing checks** - Verificación de velocidad de ejecución
- **Self-integrity checks** - Verificación de integridad del binario
- **Breakpoints falsos** - Trampas para confundir debuggers
- **Virtualización detection** - Detección de VMs
- **Memory protection** - Protección de memoria
- **Stack canary** - Protección contra overflow
- **Process tracing** - Detección de procesos trazados
- **Code modification** - Detección de modificaciones

### Ofuscación Múltiple
- **Control flow flattening** - Aplanamiento del flujo de control
- **Secciones .text mezcladas** - Código disperso con trampas
- **Código polimórfico** - Reescritura en runtime
- **Strings ofuscados** - Strings cifrados con XOR
- **Self-modifying code** - Código que se modifica a sí mismo

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

### Criptografía Híbrida Multi-Capa
1. **SHA1 Modificado** - Hash personalizado con constantes alteradas
2. **Caja S Personalizada** - Sustitución no lineal de 512 bytes
3. **Autómata Celular** - Regla 30 para generación de checksum final
4. **AES Modificado** - Cifrado de bloque con S-box personalizado
5. **RC4 Modificado** - Cifrado de flujo con clave personalizada
6. **DES Modificado** - Cifrado de bloque con S-box personalizado
7. **RSA Modificado** - Cifrado asimétrico con claves personalizadas
8. **Algoritmo Híbrido** - Combinación de todos los anteriores
9. **Cifrado Personalizado** - Algoritmo único de 10 capas
10. **Validación Final** - Sistema de validación independiente

### Sistema de Fases Interactivas
- **25 fases progresivas** con desafíos únicos
- **Sistema de vidas** (3 vidas total)
- **Sistema de puntuación** (máximo 50,000 puntos)
- **Tiempo límite** (6 horas)
- **Sistema de conocimiento** que afecta el progreso
- **Múltiples flags falsos** para confundir
- **Narrativa inmersiva** con decisiones

## 🚀 Instalación y Uso

### Compilación
```bash
chmod +x build.sh
./build.sh
```

### Ejecución
```bash
# Reto principal
./chronovm_insane

# Validador (solo después de completar el reto)
./validator
```

## 🔍 Ruta de Explotación Esperada

### Fase 1: Neutralizar Protecciones (2-3h)
1. Localizar y parchear checks de integridad
2. Bypass de protecciones anti-debugging
3. Análisis de self-integrity checks
4. Bypass de detección de virtualización
5. Bypass de detección de breakpoints

### Fase 2: Mapear la VM (2h)
1. Extraer y documentar instrucciones custom
2. Analizar bytecode disperso
3. Reconstruir flujo de ejecución real
4. Entender el sistema de registros
5. Analizar el sistema de memoria

### Fase 3: Deofuscar Bytecode (2h)
1. Encontrar bloques cifrados con XOR+RC4
2. Derivar clave de la hora del sistema
3. Descifrar y reconstruir bytecode
4. Analizar el algoritmo de cifrado personalizado
5. Entender el sistema de key schedule

### Fase 4: Ingeniería Inversa Criptográfica (3h)
1. Entender SHA1 modificado
2. Analizar caja S personalizada
3. Modelar autómata celular (regla 30)
4. Entender AES modificado
5. Analizar RC4 modificado
6. Entender DES modificado
7. Analizar RSA modificado
8. Entender algoritmo híbrido
9. Descifrar todas las capas
10. Validar con el sistema independiente

### Fase 5: Reconstrucción de Flag (1h)
1. Identificar fragmentos en memoria
2. Descubrir secuencia de entrada correcta
3. Ensamblar flag final
4. Validar con el sistema independiente

## 🎯 Solución

### Clave de Validación
```
ChronoVMSmurf
```

### Flag Final
```
HTB{ChronoVM_Smurf_Lock_VM_VirtualMachine}
```

### Checksum Esperado
```
0x40008000
```

## 📁 Estructura del Proyecto

```
chronovm_insane/
├── chronovm_main.c    # Código fuente principal
├── validator.c        # Sistema de validación independiente
├── build.sh          # Script de compilación
└── README.md         # Esta documentación
```

## 🛠️ Herramientas Recomendadas

- **GDB** - Debugger principal
- **Ghidra** - Análisis estático
- **IDA Pro** - Análisis avanzado
- **Radare2** - Análisis de binarios
- **objdump** - Desensamblador
- **strings** - Extracción de strings
- **hexdump** - Análisis hexadecimal
- **strace** - Análisis de llamadas al sistema
- **ltrace** - Análisis de llamadas a librerías
- **valgrind** - Análisis de memoria

## ⚠️ Notas Importantes

- El binario está compilado como estático y strippeado
- Las protecciones anti-debugging están activas
- El bytecode está cifrado y disperso
- El algoritmo criptográfico es híbrido y personalizado
- El reto es interactivo y tiene múltiples fases
- El sistema de validación es independiente
- El flag NO está visible en strings
- Se requieren conocimientos avanzados en reversing

## 🏆 Nivel de Dificultad

**INSANE (10/10)** - Requiere conocimientos extremos en:
- Reversing de binarios ELF
- Bypass de protecciones anti-debugging
- Análisis de máquinas virtuales
- Criptografía personalizada
- Ingeniería inversa de algoritmos complejos
- Análisis de memoria
- Explotación de vulnerabilidades
- Análisis de side channels

**Tiempo estimado:** 6+ horas de trabajo intensivo

## 🎮 Características del Juego

- **25 fases interactivas** con desafíos únicos
- **Sistema de vidas** (3 vidas total)
- **Sistema de puntuación** (máximo 50,000 puntos)
- **Tiempo límite** (6 horas)
- **Sistema de conocimiento** que afecta el progreso
- **Múltiples flags falsos** para confundir
- **Narrativa inmersiva** con decisiones
- **Sistema de validación independiente**
- **Protecciones anti-debugging extremas**
- **Criptografía multi-capa**
- **Máquina virtual personalizada**
- **Sistema de memoria complejo**
- **Algoritmos de cifrado únicos**
- **Sistema de validación independiente**

## 🚨 Advertencias

- Este reto es extremadamente difícil
- Requiere conocimientos avanzados en reversing
- El flag NO está visible en strings
- Se requieren múltiples técnicas de bypass
- El sistema de validación es independiente
- El reto puede tomar 6+ horas completar
- Se requieren conocimientos en criptografía
- Se requieren conocimientos en análisis de memoria
- Se requieren conocimientos en explotación
- Se requieren conocimientos en side channels

## 🏆 Recompensas

- **Puntuación máxima:** 50,000 puntos
- **Flag real:** HTB{ChronoVM_Smurf_Lock_VM_VirtualMachine}
- **Validación independiente:** Sistema de validación separado
- **Reconocimiento:** Hacker de élite
- **Conocimiento:** Técnicas avanzadas de reversing
- **Experiencia:** Análisis de sistemas complejos
- **Satisfacción:** Completar el reto más difícil