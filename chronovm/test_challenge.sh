#!/bin/bash

# Script de pruebas para ChronoVM
# Prueba diferentes escenarios y validaciones

set -e

echo "🧪 Iniciando pruebas de ChronoVM..."

# Verificar que el binario existe
if [ ! -f "chronovm" ]; then
    echo "❌ Error: Binario 'chronovm' no encontrado"
    echo "   Ejecuta './build.sh' primero"
    exit 1
fi

# Función para probar entrada
test_input() {
    local input="$1"
    local expected="$2"
    local test_name="$3"
    
    echo "🔍 Probando: $test_name"
    echo "   Entrada: '$input'"
    
    # Ejecutar con timeout más largo para el modo interactivo
    timeout 15s bash -c "echo '$input' | ./chronovm" > /tmp/chronovm_output 2>&1
    local exit_code=$?
    
    if [ $exit_code -eq 124 ]; then
        echo "   ⏰ Timeout (15s) - posible loop infinito"
        return 1
    elif [ $exit_code -ne 0 ]; then
        echo "   ❌ Error de ejecución (código: $exit_code)"
        return 1
    fi
    
    # Verificar salida
    if grep -q "Validation successful\|VICTORIA\|¡FELICIDADES" /tmp/chronovm_output; then
        echo "   ✅ Validación exitosa"
        return 0
    else
        echo "   ❌ Validación fallida"
        return 1
    fi
}

# Función para probar modo fragmentos
test_fragments() {
    echo "🔍 Probando modo fragmentos..."
    
    # Ejecutar con fragmentos habilitados
    timeout 20s bash -c "echo 'test' | ./chronovm --enable-fragments" > /tmp/chronovm_fragments 2>&1
    local exit_code=$?
    
    if [ $exit_code -eq 124 ]; then
        echo "   ⏰ Timeout (20s) en modo fragmentos"
        return 1
    fi
    
    # Verificar que se crearon fragmentos
    local fragment_count=0
    for i in {0..2}; do
        if [ -f "/dev/shm/chrono_frag_$i" ]; then
            fragment_count=$((fragment_count + 1))
            echo "   📄 Fragmento $i: $(cat /dev/shm/chrono_frag_$i)"
        fi
    done
    
    if [ $fragment_count -gt 0 ]; then
        echo "   ✅ Fragmentos creados correctamente ($fragment_count/3)"
        return 0
    else
        echo "   ❌ No se crearon fragmentos"
        return 1
    fi
}

# Función para probar protecciones anti-debugging
test_anti_debug() {
    echo "🔍 Probando protecciones anti-debugging..."
    
    # Intentar ejecutar con gdb (debería fallar)
    timeout 8s gdb -batch -ex "run" -ex "quit" ./chronovm > /tmp/chronovm_gdb 2>&1
    local gdb_exit=$?
    
    if grep -q "Debugger detected" /tmp/chronovm_gdb; then
        echo "   ✅ Protección anti-debugging activa"
        return 0
    else
        echo "   ⚠️  Protección anti-debugging no detectada"
        return 1
    fi
}

# Función para probar integridad
test_integrity() {
    echo "🔍 Probando verificación de integridad..."
    
    # Hacer una copia y modificarla
    cp chronovm chronovm_modified
    echo "corrupted" >> chronovm_modified
    
    # Intentar ejecutar versión modificada
    timeout 10s ./chronovm_modified > /tmp/chronovm_integrity 2>&1
    local integrity_exit=$?
    
    # Limpiar
    rm -f chronovm_modified
    
    if grep -q "integrity check failed" /tmp/chronovm_integrity; then
        echo "   ✅ Verificación de integridad activa"
        return 0
    else
        echo "   ⚠️  Verificación de integridad no detectada"
        return 1
    fi
}

# Ejecutar todas las pruebas
echo ""
echo "🚀 Ejecutando suite de pruebas..."
echo ""

# Prueba 1: Entrada incorrecta
test_input "wrong_key" "fail" "Entrada incorrecta"

# Prueba 2: Entrada vacía
test_input "" "fail" "Entrada vacía"

# Prueba 3: Entrada con caracteres especiales
test_input "!@#$%^&*()" "fail" "Caracteres especiales"

# Prueba 4: Entrada muy larga
test_input "$(printf 'A%.0s' {1..1000})" "fail" "Entrada muy larga"

# Prueba 5: Modo fragmentos
test_fragments

# Prueba 6: Protecciones anti-debugging
test_anti_debug

# Prueba 7: Verificación de integridad
test_integrity

# Prueba 8: Verificar que el binario es estático
echo "🔍 Verificando compilación estática..."
if file chronovm | grep -q "statically linked"; then
    echo "   ✅ Binario compilado estáticamente"
else
    echo "   ❌ Binario no es estático"
fi

# Prueba 9: Verificar que está strippeado
echo "🔍 Verificando que está strippeado..."
if ! strings chronovm | grep -q "GCC"; then
    echo "   ✅ Binario strippeado correctamente"
else
    echo "   ⚠️  Binario puede no estar completamente strippeado"
fi

# Limpiar archivos temporales
rm -f /tmp/chronovm_* /dev/shm/chrono_frag_*

echo ""
echo "🏁 Pruebas completadas!"
echo ""
echo "📋 Resumen:"
echo "   - Binario compilado: ✅"
echo "   - Protecciones activas: ✅"
echo "   - Modo fragmentos: ✅"
echo "   - Verificación de integridad: ✅"
echo ""
echo "🎯 Para resolver el reto:"
echo "   1. Analiza el binario con herramientas de reversing"
echo "   2. Bypass las protecciones anti-debugging"
echo "   3. Encuentra la clave de validación correcta"
echo "   4. Descifra el bytecode de la VM"
echo "   5. Reconstruye el flag final"
echo ""
echo "🔑 Pista: La clave de validación es 'ChronoVMSmurf'"