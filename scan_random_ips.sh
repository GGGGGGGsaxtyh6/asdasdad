#!/bin/bash

# Script para conectarse a IPs aleatorias usando netcat con timeout
# Puerto común para netcat/servicios (puedes cambiarlo)
PORT=${1:-22}  # Por defecto puerto 22 (SSH), o el que se pase como argumento
TIMEOUT=${2:-2}  # Timeout en segundos (por defecto 2)
NUM_IPS=${3:-10}  # Número de IPs a probar (por defecto 10)

echo "=== Escaneando IPs aleatorias ==="
echo "Puerto: $PORT"
echo "Timeout: ${TIMEOUT}s"
echo "Número de intentos: $NUM_IPS"
echo "================================"
echo ""

# Función para generar una IP aleatoria
generate_random_ip() {
    echo "$((RANDOM % 256)).$((RANDOM % 256)).$((RANDOM % 256)).$((RANDOM % 256))"
}

# Contador de conexiones exitosas
success_count=0

# Probar conexiones
for i in $(seq 1 $NUM_IPS); do
    ip=$(generate_random_ip)
    echo -n "[$i/$NUM_IPS] Probando $ip:$PORT ... "
    
    # Usar nc con timeout
    if timeout $TIMEOUT nc -zv $ip $PORT 2>&1 | grep -q "succeeded\|open"; then
        echo "✓ ABIERTO"
        ((success_count++))
    else
        echo "✗ Cerrado/Sin respuesta"
    fi
done

echo ""
echo "================================"
echo "Resumen: $success_count/$NUM_IPS conexiones exitosas"
echo "================================"
