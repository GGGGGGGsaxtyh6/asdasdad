#!/bin/bash
TARGET="94.237.49.23"
PORT="37326"

# Crear archivo temporal para la sesión
{
    # Paso 1: Iniciar receive job
    echo -ne "\x02lp\n"
    
    # Esperar ACK (simular con sleep pequeño)
    sleep 0.2
    
    # Paso 2: Enviar control file
    # Formato: 0x02 + size + space + name + \n + contenido + 0x00
    CONTROL="Htest\n"
    SIZE=${#CONTROL}
    echo -ne "\x02${SIZE} cfA001localhost\n${CONTROL}\x00"
    
    sleep 0.2
    
    # Paso 3: Enviar data file
    DATA="test data\n"
    DSIZE=${#DATA}
    echo -ne "\x03${DSIZE} dfA001localhost\n${DATA}\x00"
    
} | timeout 5s nc $TARGET $PORT | xxd