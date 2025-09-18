#!/bin/bash

# Script para ejecutar mindmaze evitando detección de debugging

# Limpiar variables de entorno que podrían ser detectadas
unset LD_PRELOAD
unset LD_DEBUG
unset LD_DEBUG_OUTPUT
unset MALLOC_TRACE

# Intentar ejecutar el programa
echo "Intentando ejecutar mindmaze..."

# Crear un pipe para enviar entrada
mkfifo /tmp/mindmaze_input

# Ejecutar en background
./mindmaze < /tmp/mindmaze_input &
PID=$!

# Esperar un poco
sleep 0.1

# Enviar entrada de prueba
echo "test" > /tmp/mindmaze_input

# Esperar un poco más
sleep 0.1

# Limpiar
rm -f /tmp/mindmaze_input
kill $PID 2>/dev/null