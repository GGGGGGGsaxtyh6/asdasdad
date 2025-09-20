#!/bin/bash
echo "🔧 Compilando ChronoVM Insane..."

# Compilar con protecciones extremas
gcc -o chronovm_insane chronovm_insane.c \
    -static \
    -O3 \
    -fno-stack-protector \
    -fno-pie \
    -no-pie \
    -s \
    -Wl,--strip-all \
    -D_FORTIFY_SOURCE=0 \
    -fno-ident \
    -fno-asynchronous-unwind-tables \
    -fno-plt \
    -fno-pic \
    -fno-pie \
    -ffreestanding \
    -fno-builtin \
    -fno-stack-protector \
    -z noexecstack \
    -z now

echo "✅ Compilación completada"
echo "📊 Tamaño del binario: $(stat -f%z chronovm_insane 2>/dev/null || stat -c%s chronovm_insane 2>/dev/null) bytes"