#!/bin/bash

echo "🧪 PRUEBA SIMPLE DEL RETO CHRONOVM EXTREMO"
echo "==========================================="

# Crear un archivo de entrada más simple
echo "s" > /tmp/input.txt
echo "1" >> /tmp/input.txt
echo "Hello, welcome to the challenge!" >> /tmp/input.txt
echo "4" >> /tmp/input.txt
echo "1" >> /tmp/input.txt
echo "ChronoVMSmurf" >> /tmp/input.txt
echo "s" >> /tmp/input.txt
echo "s" >> /tmp/input.txt

echo "📝 Archivo de entrada creado:"
cat /tmp/input.txt

echo ""
echo "🚀 Ejecutando reto..."
echo ""

# Ejecutar con expect para manejar la entrada interactiva
timeout 60s expect -c "
spawn /workspace/chronovm/chronovm_extreme
expect \"¿Estás listo para enfrentar la verdad? (s/n):\"
send \"s\r\"
expect \"¿Cuál crees que es el flag real? (1-5):\"
send \"1\r\"
expect \"¿Cuál es el mensaje descifrado?\"
send \"Hello, welcome to the challenge!\r\"
expect \"¿En qué línea está el error? (1-6):\"
send \"4\r\"
expect \"¿Cuál crees que es el flag real? (1-10):\"
send \"1\r\"
expect \"¿Cuál es la clave de validación?\"
send \"ChronoVMSmurf\r\"
expect \"¿Confirmas que tienes el flag real? (s/n):\"
send \"s\r\"
expect \"¿Confirmas que tienes el flag real? (s/n):\"
send \"s\r\"
expect eof
"

echo ""
echo "✅ Prueba completada"