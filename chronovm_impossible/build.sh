#!/bin/bash
echo "Building ChronoVM Impossible..."

gcc -o chronovm_impossible main.c \
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

echo "Build completed"
echo "Binary size: $(stat -f%z chronovm_impossible 2>/dev/null || stat -c%s chronovm_impossible 2>/dev/null) bytes"