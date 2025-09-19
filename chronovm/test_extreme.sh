#!/bin/bash

echo "🧪 PROBANDO RETO CHRONOVM EXTREMO"
echo "================================="

# Crear respuestas automáticas
echo "s" > /tmp/responses.txt
echo "1" >> /tmp/responses.txt
echo "Hello, welcome to the challenge!" >> /tmp/responses.txt
echo "4" >> /tmp/responses.txt
echo "1" >> /tmp/responses.txt
echo "ChronoVMSmurf" >> /tmp/responses.txt
echo "s" >> /tmp/responses.txt
echo "s" >> /tmp/responses.txt

echo "📝 Respuestas preparadas:"
cat /tmp/responses.txt

echo ""
echo "🚀 Ejecutando reto con respuestas automáticas..."
echo ""

# Ejecutar el reto con las respuestas
timeout 60s /workspace/chronovm/chronovm_extreme < /tmp/responses.txt

echo ""
echo "✅ Prueba completada"
echo "🧹 Limpiando archivos temporales..."
rm -f /tmp/responses.txt