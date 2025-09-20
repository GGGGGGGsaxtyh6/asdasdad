#!/bin/bash
echo "🔧 Compilando ChronoVM Insane..."

# Compilar con protecciones extremas
gcc -o chronovm_insane chronovm_main.c \
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

# Compilar validador
gcc -o validator validator.c \
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
echo "📊 Tamaño del binario principal: $(stat -f%z chronovm_insane 2>/dev/null || stat -c%s chronovm_insane 2>/dev/null) bytes"
echo "📊 Tamaño del validador: $(stat -f%z validator 2>/dev/null || stat -c%s validator 2>/dev/null) bytes"