# 🎯 MindMaze - Solución Completa

## **📋 Resumen del Reto**
- **Nombre:** MindMaze
- **Categoría:** Reversing
- **Dificultad:** Insane (6+ horas)
- **Input correcto:** `HTB{mind`
- **Flag final:** `HTB{mindmaze_1_vm_protected_2_fsm_validated_3}`

---

## **🔍 Análisis Realizado**

### **1. Protecciones Identificadas**
- ✅ **Anti-debugging múltiple** (ptrace, LD_PRELOAD, getppid)
- ✅ **Control flow flattening** (máquina de estados)
- ✅ **VM-based obfuscation** (máquina virtual personalizada)
- ✅ **Cifrado dinámico de strings** (AES-256-CBC)
- ✅ **Flag fragmentada en 3 fases**
- ✅ **FSM complejo** (Finite State Machine)
- ✅ **Validación de checksums múltiples**

### **2. Técnicas de Bypass Utilizadas**
- **LD_PRELOAD hooks** para bypass de anti-debugging
- **Análisis estático** con Ghidra/IDA Pro
- **Reverse engineering** de la VM
- **Análisis del FSM** y transiciones de estado
- **Cálculo de checksums** requeridos

---

## **🧩 Estructura del Reto**

### **Fase 1: Polymorphic Reconstruction**
```c
// Función polimórfica que reconstruye la primera parte
volatile int x = 0x1337;
volatile int y = 0xDEAD;
volatile int z = x ^ y;
// Reconstruye: "HTB{mindmaze_1"
```

### **Fase 2: Virtual Machine**
```c
// VM con 28 tipos de instrucciones
// Bytecode embebido en .rodata
// Extrae: "_vm_protected_2"
```

### **Fase 3: Finite State Machine**
```c
// FSM con 10 estados
// Secuencia: H-T-B-{-m-i-n-d
// Checksums: Total=0x5311, FSM=0x2b
// Valida: "_fsm_validated_3"
```

---

## **🎯 Solución Paso a Paso**

### **Paso 1: Bypass Anti-Debugging**
```bash
# Crear hook de bypass
gcc -shared -fPIC -o bypass.so bypass.c
LD_PRELOAD=./bypass.so ./mindmaze
```

### **Paso 2: Análisis de VM**
```python
# Implementar intérprete de VM
vm_bytecode = [0x01, 0x00, 0x10, 0x00, ...]
# Ejecutar bytecode para extraer flag part 2
```

### **Paso 3: Análisis de FSM**
```python
# Calcular checksums requeridos
solution = "HTB{mind"
total_checksum = sum(ord(c) * (i+1)**2 for i,c in enumerate(solution))
fsm_checksum = reduce(xor, [ord(c) for c in solution])
```

### **Paso 4: Solución Final**
```bash
echo "HTB{mind" | ./mindmaze
# Output: Congratulations! Flag: HTB{mindmaze_1_vm_protected_2_fsm_validated_3}
```

---

## **🔧 Herramientas Utilizadas**

- **Ghidra/IDA Pro** - Análisis estático
- **GDB** - Debugging dinámico
- **LD_PRELOAD** - Hook de funciones
- **Python** - Scripts de análisis
- **objdump/strings** - Análisis de binarios

---

## **📊 Métricas del Reto**

- **Tamaño del binario:** 6.8 MB (strippeado)
- **Instrucciones VM:** 28 tipos
- **Estados FSM:** 10 estados
- **Protecciones:** 7 capas
- **Tiempo de resolución:** 6+ horas

---

## **🏆 Flag Final**

```
HTB{mindmaze_1_vm_protected_2_fsm_validated_3}
```

**¡El reto MindMaze ha sido resuelto exitosamente!** 🚀

---

## **💡 Lecciones Aprendidas**

1. **Anti-debugging avanzado** requiere múltiples técnicas de bypass
2. **VM analysis** es crucial para entender la lógica ofuscada
3. **FSM reverse engineering** requiere análisis de transiciones de estado
4. **Checksum validation** puede ser compleja y multi-capa
5. **Control flow flattening** hace el análisis estático más difícil

**Este reto demuestra técnicas avanzadas de reversing que se encuentran en malware real y software protegido comercialmente.**