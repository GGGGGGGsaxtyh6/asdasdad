#!/bin/bash
# Script de limpieza completa para el reto f_one
# Ejecutar con: sudo bash CLEANUP.sh

echo "============================================"
echo "  LIMPIEZA COMPLETA - Reto f_one"
echo "============================================"
echo ""

# Desinstalar paquetes instalados
echo "[1/3] Desinstalando paquetes..."
apt-get remove -y gdb radare2 python3-pip \
    libbabeltrace1 libc6-dbg libcapstone-dev libcapstone5 \
    libdebuginfod-common libdebuginfod1t64 libdw1t64 libipt2 \
    libmagic-dev libradare2-5.0.0t64 libradare2-common libradare2-dev \
    libsource-highlight-common libsource-highlight4t64 \
    libuv1-dev libxxhash-dev liblz4-dev libzip-dev libzip5 \
    zipcmp zipmerge ziptool file 2>/dev/null

echo ""
echo "[2/3] Limpiando dependencias y cache..."
apt-get autoremove -y
apt-get autoclean
apt-get clean

# Desinstalar pwntools y dependencias de pip
echo ""
echo "[3/3] Desinstalando pwntools y dependencias..."
pip3 uninstall -y pwntools paramiko mako pyelftools capstone ropgadget \
    pyserial requests pysocks python-dateutil packaging psutil \
    intervaltree sortedcontainers unicorn rpyc colored_traceback \
    unix-ar zstandard MarkupSafe bcrypt cryptography invoke pynacl \
    certifi charset_normalizer idna urllib3 plumbum cffi pycparser \
    six 2>/dev/null

echo ""
echo "============================================"
echo "  Limpieza de paquetes completada"
echo "============================================"
echo ""
echo "Archivos del reto aún presentes en:"
echo "  /workspace/f_one_pwn/"
echo ""
echo "Para eliminar también los archivos ejecuta:"
echo "  rm -rf /workspace/f_one_pwn"
echo ""