#!/bin/bash
# Script para probar comando LPD - listar cola
# Comando: 0x03 (short queue) + nombre_cola + newline

TARGET="94.237.49.23"
PORT="37326"

# Probar listar cola con nombre vacío
echo -ne "\x03\n" | timeout 5s nc $TARGET $PORT