#!/bin/bash

echo "🧹 Limpieza y Verificación Final de ChronoVM"
echo "============================================"

# Limpiar fragmentos temporales
echo "1. Limpiando fragmentos temporales..."
rm -f /dev/shm/chrono_frag_*
echo "   ✅ Fragmentos limpiados"

# Verificar archivos del proyecto
echo -e "\n2. Verificando archivos del proyecto:"
ls -la | grep -E "(chronovm|\.py|\.sh|\.md|\.c)$"

# Verificar que el binario funciona
echo -e "\n3. Verificación final del binario:"
if [ -f "chronovm" ]; then
    echo "   ✅ Binario encontrado"
    echo "   📊 Tamaño: $(ls -lh chronovm | awk '{print $5}')"
    echo "   🔒 Permisos: $(ls -l chronovm | awk '{print $1}')"
    
    # Prueba rápida
    echo -e "\n4. Prueba rápida de funcionalidad:"
    echo "ChronoVM2024" | ./chronovm --enable-fragments | grep -E "(Validación|Flag)" | head -2
    
    echo -e "\n5. Verificación de fragmentos:"
    ls -la /dev/shm/chrono_frag_* 2>/dev/null | wc -l | xargs -I {} echo "   📁 Fragmentos generados: {}"
    
else
    echo "   ❌ Binario no encontrado"
fi

echo -e "\n6. Resumen del reto:"
echo "   🎯 Nombre: chronovm"
echo "   📂 Categoría: Reversing"
echo "   ⭐ Dificultad: Insane"
echo "   🔑 Clave: ChronoVM2024"
echo "   🏁 Flag: HTB{ChronoVM_TimeLock_VirtualMachine}"
echo "   ⏱️  Tiempo estimado: 6-8 horas"

echo -e "\n✅ Limpieza completada"
echo "🎉 El reto ChronoVM está listo para ser subido a HackTheBox!"