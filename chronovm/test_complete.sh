#!/bin/bash

echo "🧪 PRUEBA COMPLETA DEL RETO CHRONOVM EXTREMO"
echo "============================================="

# Crear respuestas correctas para todas las fases
cat > /tmp/responses.txt << 'EOF'
s
1
Hello, welcome to the challenge!
4
1
ChronoVMSmurf
s
s
EOF

echo "📝 Respuestas preparadas para todas las fases:"
echo "1. s (aceptar desafío)"
echo "2. 1 (seleccionar fake flag - todos son falsos)"
echo "3. Hello, welcome to the challenge! (descifrar ROT13+XOR)"
echo "4. 4 (encontrar error en bytecode - línea 4)"
echo "5. 1 (seleccionar fake flag en memoria - todos son falsos)"
echo "6. ChronoVMSmurf (clave de validación real)"
echo "7. s (confirmar flag real encontrado)"
echo "8. s (confirmar escape exitoso)"
echo ""

echo "🚀 Ejecutando reto completo..."
echo ""

# Ejecutar el reto con las respuestas
timeout 120s /workspace/chronovm/chronovm_extreme < /tmp/responses.txt

echo ""
echo "✅ Prueba completa finalizada"
echo "🧹 Limpiando archivos temporales..."
rm -f /tmp/responses.txt