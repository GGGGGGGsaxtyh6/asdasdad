#!/bin/bash

# Script de compilación para ChronoVM
# Compila el binario con protecciones y ofuscación

set -e

echo "🔧 Compilando ChronoVM..."

# Limpiar compilaciones anteriores
rm -f chronovm chronovm_stripped

# Compilar con optimizaciones y protecciones
gcc -o chronovm chronovm.c \
    -static \
    -O2 \
    -fno-stack-protector \
    -fno-pie \
    -no-pie \
    -Wl,--strip-all \
    -s \
    -D_FORTIFY_SOURCE=0 \
    -fno-ident \
    -fno-asynchronous-unwind-tables \
    -fno-unwind-tables \
    -fno-plt \
    -fno-pic \
    -fno-pie \
    -Wl,-z,relro,-z,now \
    -Wl,--build-id=none \
    -Wl,--hash-style=sysv

# Aplicar ofuscación adicional
echo "🎭 Aplicando ofuscación..."

# Crear copia para procesamiento
cp chronovm chronovm_stripped

# Ejecutar script de ofuscación si existe
if [ -f "advanced_obfuscation.py" ]; then
    python3 advanced_obfuscation.py chronovm_stripped
fi

# Ejecutar post-procesamiento si existe
if [ -f "post_process.py" ]; then
    python3 post_process.py chronovm_stripped
fi

# Reemplazar binario original
mv chronovm_stripped chronovm

# Hacer ejecutable
chmod +x chronovm

# Verificar compilación
if [ -f "chronovm" ]; then
    echo "✅ Compilación exitosa!"
    echo "📁 Archivo: chronovm"
    echo "📊 Tamaño: $(stat -c%s chronovm) bytes"
    
    # Mostrar información del binario
    echo ""
    echo "🔍 Información del binario:"
    file chronovm
    echo ""
    echo "📋 Secciones:"
    readelf -S chronovm | head -10
    echo ""
    echo "🚀 Listo para ejecutar!"
    echo "   ./chronovm                    # Modo normal"
    echo "   ./chronovm --enable-fragments # Modo con fragmentos"
else
    echo "❌ Error en la compilación!"
    exit 1
fi