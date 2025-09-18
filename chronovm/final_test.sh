#!/bin/bash

# Prueba final completa para ChronoVM
# Simula el escenario real del reto

set -e

echo "🎯 Prueba Final - ChronoVM Challenge"
echo "====================================="
echo ""

# Verificar que el binario existe
if [ ! -f "chronovm" ]; then
    echo "❌ Error: Binario 'chronovm' no encontrado"
    echo "   Ejecuta './build.sh' primero"
    exit 1
fi

# Función para mostrar banner
show_banner() {
    echo "╔══════════════════════════════════════════════════════════════╗"
    echo "║                    ChronoVM Challenge                       ║"
    echo "║                                                              ║"
    echo "║  Un viejo programa llamado ChronoVM ha aparecido en un      ║"
    echo "║  sistema abandonado. Parece un simple reloj digital, pero   ║"
    echo "║  en realidad esconde un mecanismo de validación muy         ║"
    echo "║  elaborado. Tu misión es descubrir cómo funciona y          ║"
    echo "║  revelar el secreto que protege. El tiempo corre...         ║"
    echo "║                                                              ║"
    echo "║  Categoría: Reversing                                       ║"
    echo "║  Dificultad: Insane                                         ║"
    echo "║  Tiempo estimado: 6-8 horas                                 ║"
    echo "╚══════════════════════════════════════════════════════════════╝"
    echo ""
}

# Función para probar diferentes claves
test_key() {
    local key="$1"
    local description="$2"
    
    echo "🔑 Probando clave: $description"
    echo "   Valor: '$key'"
    
    # Ejecutar con timeout
    timeout 10s bash -c "echo '$key' | ./chronovm" > /tmp/chronovm_test 2>&1
    local exit_code=$?
    
    if [ $exit_code -eq 124 ]; then
        echo "   ⏰ Timeout - posible loop infinito"
        return 1
    fi
    
    if grep -q "Validation successful" /tmp/chronovm_test; then
        echo "   ✅ ¡ÉXITO! Clave válida encontrada"
        echo "   🏆 Flag: HTB{ChronoVM_TimeLock_VirtualMachine}"
        return 0
    elif grep -q "Invalid key" /tmp/chronovm_test; then
        echo "   ❌ Clave inválida"
        return 1
    else
        echo "   ⚠️  Comportamiento inesperado"
        return 1
    fi
}

# Función para probar modo fragmentos
test_fragments_mode() {
    echo ""
    echo "🔍 Probando modo fragmentos..."
    echo "   Ejecutando: ./chronovm --enable-fragments"
    
    timeout 10s bash -c "echo 'test' | ./chronovm --enable-fragments" > /tmp/chronovm_fragments 2>&1
    local exit_code=$?
    
    if [ $exit_code -eq 124 ]; then
        echo "   ⏰ Timeout en modo fragmentos"
        return 1
    fi
    
    # Verificar fragmentos creados
    local fragments_found=0
    for i in {0..2}; do
        if [ -f "/dev/shm/chrono_frag_$i" ]; then
            local content=$(cat /dev/shm/chrono_frag_$i)
            echo "   📄 Fragmento $i: '$content'"
            fragments_found=$((fragments_found + 1))
        fi
    done
    
    if [ $fragments_found -gt 0 ]; then
        echo "   ✅ Fragmentos encontrados: $fragments_found/3"
        return 0
    else
        echo "   ❌ No se encontraron fragmentos"
        return 1
    fi
}

# Función para mostrar información del binario
show_binary_info() {
    echo ""
    echo "📊 Información del binario:"
    echo "   Archivo: chronovm"
    echo "   Tamaño: $(stat -c%s chronovm) bytes"
    echo "   Tipo: $(file chronovm)"
    echo ""
    echo "🔍 Análisis de strings:"
    strings chronovm | grep -E "(ChronoVM|TimeLock|VirtualMachine|HTB)" | head -5
    echo ""
    echo "📋 Secciones principales:"
    readelf -S chronovm | grep -E "(text|data|rodata|bss)" | head -5
    echo ""
}

# Función para mostrar pistas
show_hints() {
    echo ""
    echo "💡 Pistas para resolver el reto:"
    echo ""
    echo "1. 🔧 Protecciones Anti-Debugging:"
    echo "   - ptrace() para detectar debuggers"
    echo "   - prctl() para deshabilitar core dumps"
    echo "   - Verificación de velocidad de ejecución"
    echo "   - Verificación de integridad del binario"
    echo ""
    echo "2. 🎭 Ofuscación:"
    echo "   - Control flow flattening"
    echo "   - Código polimórfico"
    echo "   - Strings cifrados con XOR"
    echo "   - Secciones .text mezcladas"
    echo ""
    echo "3. 🖥️  Máquina Virtual:"
    echo "   - 15 instrucciones personalizadas"
    echo "   - Bytecode cifrado y disperso"
    echo "   - Registros y memoria virtual"
    echo "   - Stack de llamadas"
    echo ""
    echo "4. 🔐 Criptografía:"
    echo "   - SHA1 modificado con constantes alteradas"
    echo "   - Caja S personalizada de 256 bytes"
    echo "   - Autómata celular (regla 30)"
    echo "   - Clave derivada del tiempo del sistema"
    echo ""
    echo "5. 🧩 Fragmentos de Flag:"
    echo "   - Escritos en /dev/shm/chrono_frag_*"
    echo "   - Solo con --enable-fragments"
    echo "   - Ensamblaje dinámico en memoria"
    echo ""
}

# Ejecutar prueba final
show_banner

echo "🚀 Iniciando prueba final..."
echo ""

# Mostrar información del binario
show_binary_info

# Probar modo fragmentos
test_fragments_mode

# Probar diferentes claves
echo ""
echo "🔑 Probando claves comunes..."

# Lista de claves a probar
keys=(
    "ChronoVM2024:Clave correcta"
    "ChronoVM:Variación 1"
    "chronovm2024:Variación 2"
    "CHRONOVM2024:Variación 3"
    "ChronoVM:Variación 4"
    "TimeLock:Variación 5"
    "VirtualMachine:Variación 6"
    "HTB:Variación 7"
    "chronovm:Variación 8"
    "admin:Variación 9"
    "password:Variación 10"
)

# Probar cada clave
for key_info in "${keys[@]}"; do
    IFS=':' read -r key description <<< "$key_info"
    if test_key "$key" "$description"; then
        echo ""
        echo "🎉 ¡RETO RESUELTO!"
        echo "   La clave correcta es: '$key'"
        echo "   Flag: HTB{ChronoVM_TimeLock_VirtualMachine}"
        echo ""
        echo "🏆 ¡Felicidades! Has completado el reto ChronoVM"
        exit 0
    fi
    echo ""
done

# Si llegamos aquí, no se encontró la clave
echo "❌ No se encontró la clave correcta en las pruebas automáticas"
echo ""
echo "🔍 Para resolver manualmente:"
echo "   1. Usa GDB, Ghidra o IDA Pro para analizar el binario"
echo "   2. Bypass las protecciones anti-debugging"
echo "   3. Encuentra la función de validación"
echo "   4. Analiza el algoritmo criptográfico"
echo "   5. Deriva la clave correcta"
echo ""

# Mostrar pistas
show_hints

# Limpiar archivos temporales
rm -f /tmp/chronovm_* /dev/shm/chrono_frag_*

echo "🏁 Prueba final completada"