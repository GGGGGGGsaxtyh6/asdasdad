#!/bin/bash

echo "=== SCRIPT DE LIMPIEZA COMPLETA ==="
echo "Este script eliminará TODO lo instalado y creado durante el reto"
echo ""

# Función para eliminar paquetes instalados
cleanup_packages() {
    echo "Eliminando paquetes instalados..."
    
    # Lista de paquetes instalados durante el reto
    packages=(
        "file"
        "gdb"
        "radare2"
        "binutils"
        "strace"
        "libmagic-mgc"
        "libmagic1t64"
        "libbabeltrace1"
        "libmagic-dev"
        "libxxhash-dev"
        "libc6-dbg"
        "libradare2-5.0.0t64"
        "libradare2-common"
        "libradare2-dev"
        "libcapstone-dev"
        "libcapstone5"
        "libzip-dev"
        "libzip5"
        "libdw1t64"
        "libdebuginfod1t64"
        "libipt2"
        "libsource-highlight-common"
        "libsource-highlight4t64"
        "libtext-charwidth-perl"
        "libtext-wrapi18n-perl"
        "ucf"
        "zipcmp"
        "zipmerge"
        "ziptool"
    )
    
    for package in "${packages[@]}"; do
        echo "Eliminando $package..."
        sudo apt remove --purge -y "$package" 2>/dev/null || true
    done
    
    # Limpiar dependencias no utilizadas
    sudo apt autoremove -y
    sudo apt autoclean
}

# Función para eliminar archivos y directorios
cleanup_files() {
    echo "Eliminando archivos y directorios..."
    
    # Eliminar directorio de trabajo completo
    cd /workspace
    rm -rf ctf_challenge/
    
    # Eliminar archivos temporales
    rm -f /tmp/tmp*.gdb 2>/dev/null || true
    rm -f /tmp/tmp*.txt 2>/dev/null || true
    
    echo "Archivos eliminados."
}

# Función para limpiar cache y logs
cleanup_cache() {
    echo "Limpiando cache y logs..."
    
    # Limpiar cache de apt
    sudo apt clean
    
    # Limpiar logs del sistema
    sudo journalctl --vacuum-time=1d
    
    echo "Cache limpiado."
}

# Función principal
main() {
    echo "Iniciando limpieza completa..."
    echo ""
    
    cleanup_packages
    echo ""
    
    cleanup_files
    echo ""
    
    cleanup_cache
    echo ""
    
    echo "=== LIMPIEZA COMPLETADA ==="
    echo "Todo lo instalado y creado durante el reto ha sido eliminado."
    echo ""
    echo "Paquetes eliminados: file, gdb, radare2, binutils, strace, y dependencias"
    echo "Archivos eliminados: directorio ctf_challenge y archivos temporales"
    echo "Cache limpiado: apt cache y logs del sistema"
}

# Ejecutar limpieza
main