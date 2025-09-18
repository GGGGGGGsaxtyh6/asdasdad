#!/bin/bash

echo "🎯 Prueba Final del Reto ChronoVM"
echo "================================="

# Limpiar fragmentos anteriores
rm -f /dev/shm/chrono_frag_*

echo "1. Información del binario:"
file chronovm
ls -la chronovm
echo ""

echo "2. Análisis de secciones:"
readelf -S chronovm | head -10
echo ""

echo "3. Strings ofuscados:"
strings chronovm | grep -E "(Chrono|VM|Flag|HTB|Error|Validacion)" | head -10
echo ""

echo "4. Prueba con clave incorrecta:"
echo "test123" | ./chronovm --enable-fragments
echo ""

echo "5. Prueba con clave correcta:"
echo "ChronoVM2024" | ./chronovm --enable-fragments
echo ""

echo "6. Verificación de fragmentos:"
ls -la /dev/shm/chrono_frag_* 2>/dev/null
for frag in /dev/shm/chrono_frag_*; do
    if [ -f "$frag" ]; then
        echo "   $frag: $(cat $frag)"
    fi
done
echo ""

echo "7. Análisis de funciones principales:"
objdump -d chronovm | grep -A 10 -B 5 "main\|vm_\|validate" | head -20
echo ""

echo "8. Verificación de protecciones:"
echo "   - Binario strippeado: $(file chronovm | grep -q stripped && echo "Sí" || echo "No")"
echo "   - Tamaño del binario: $(ls -lh chronovm | awk '{print $5}')"
echo "   - Secciones ofuscadas: $(readelf -S chronovm | grep -c "chrono\|temporal\|vm_")"
echo ""

echo "9. Prueba de resistencia a debugging:"
echo "   (Intentando ejecutar con gdb - esto debería fallar o comportarse diferente)"
timeout 5 gdb -batch -ex "run" -ex "quit" ./chronovm 2>/dev/null || echo "   GDB no pudo ejecutar el binario correctamente"
echo ""

echo "10. Verificación final de la flag:"
echo "ChronoVM2024" | ./chronovm --enable-fragments | grep "Flag:" | head -1
echo ""

echo "✅ Prueba final completada"
echo "🎉 El reto ChronoVM está listo para HackTheBox!"