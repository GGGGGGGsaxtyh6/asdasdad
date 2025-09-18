#!/bin/bash

echo "🧪 Probando ChronoVM Challenge"
echo "=============================="

# Instalar herramientas necesarias
sudo apt install -y file binutils

echo "1. Información del binario:"
file chronovm
ls -la chronovm

echo -e "\n2. Secciones del binario:"
readelf -S chronovm | head -15

echo -e "\n3. Strings en el binario:"
strings chronovm | grep -i "chrono\|vm\|flag\|htb" | head -10

echo -e "\n4. Probando ejecución básica:"
echo "   (Ingresando 'test123' como input)"
echo "test123" | ./chronovm

echo -e "\n5. Probando con argumentos (para activar fragmentos):"
echo "   (Ingresando 'test123' como input)"
echo "test123" | ./chronovm --enable-fragments

echo -e "\n6. Verificando fragmentos en /dev/shm:"
ls -la /dev/shm/chrono_frag_* 2>/dev/null || echo "   No se encontraron fragmentos"

echo -e "\n7. Contenido de fragmentos (si existen):"
for frag in /dev/shm/chrono_frag_*; do
    if [ -f "$frag" ]; then
        echo "   $frag: $(cat $frag)"
    fi
done

echo -e "\n8. Análisis de símbolos:"
nm chronovm 2>/dev/null | grep -i "vm\|chrono" | head -5 || echo "   Binario strippeado (sin símbolos)"

echo -e "\n9. Análisis de funciones:"
objdump -d chronovm | grep -A 5 -B 5 "main\|vm_" | head -20

echo -e "\n✅ Prueba completada"