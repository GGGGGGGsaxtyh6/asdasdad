#!/bin/bash

echo "=== Intentando ejecutar MindMaze evitando detección de debugging ==="

# Método 1: Ejecutar con timeout muy corto
echo "Método 1: Timeout muy corto"
timeout 0.1 ./mindmaze

# Método 2: Ejecutar en un subshell aislado
echo "Método 2: Subshell aislado"
(exec ./mindmaze) &
sleep 0.1
kill %1 2>/dev/null

# Método 3: Ejecutar con diferentes variables de entorno limpias
echo "Método 3: Variables de entorno limpias"
env -i ./mindmaze

# Método 4: Ejecutar con entrada predefinida
echo "Método 4: Con entrada predefinida"
echo "test123" | timeout 1 ./mindmaze

# Método 5: Ejecutar en background con kill rápido
echo "Método 5: Background con kill rápido"
./mindmaze &
PID=$!
sleep 0.05
kill $PID 2>/dev/null

echo "=== Fin de intentos ==="