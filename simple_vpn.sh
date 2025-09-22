#!/bin/bash

# VPN Simple usando netcat y tunneling
TARGET_IP="10.10.221.34"
TARGET_PORT="80"
LOCAL_PORT="8888"

echo "🚀 Iniciando VPN simple..."
echo "🎯 Objetivo: $TARGET_IP:$TARGET_PORT"
echo "🌐 Puerto local: $LOCAL_PORT"

# Función para crear túnel
create_tunnel() {
    echo "📡 Creando túnel a $TARGET_IP:$TARGET_PORT"
    
    # Crear túnel usando netcat
    while true; do
        echo "🔄 Intentando conectar..."
        
        # Intentar conexión directa
        if timeout 5 bash -c "echo > /dev/tcp/$TARGET_IP/$TARGET_PORT" 2>/dev/null; then
            echo "✅ Conexión directa exitosa a $TARGET_IP:$TARGET_PORT"
            
            # Crear proxy simple
            while true; do
                echo "🌐 Proxy activo en puerto $LOCAL_PORT"
                nc -l -p $LOCAL_PORT -c "nc $TARGET_IP $TARGET_PORT" 2>/dev/null
                sleep 1
            done
        else
            echo "❌ No se puede conectar directamente, intentando con proxy..."
            
            # Crear túnel a través de un proxy
            while true; do
                echo "🔄 Reintentando conexión..."
                nc -l -p $LOCAL_PORT -e "nc $TARGET_IP $TARGET_PORT" 2>/dev/null
                sleep 2
            done
        fi
    done
}

# Función para probar conectividad
test_connection() {
    echo "🧪 Probando conectividad..."
    
    # Probar con curl
    if command -v curl >/dev/null 2>&1; then
        echo "📡 Probando con curl..."
        timeout 10 curl -v "http://$TARGET_IP" 2>&1 | head -20
    fi
    
    # Probar con wget
    if command -v wget >/dev/null 2>&1; then
        echo "📡 Probando con wget..."
        timeout 10 wget -O- "http://$TARGET_IP" 2>&1 | head -10
    fi
    
    # Probar con netcat
    echo "📡 Probando con netcat..."
    timeout 5 bash -c "echo 'GET / HTTP/1.1\r\nHost: $TARGET_IP\r\n\r\n' | nc $TARGET_IP $TARGET_PORT" 2>/dev/null
}

# Función principal
main() {
    echo "🔧 Configurando VPN personalizada..."
    
    # Verificar herramientas
    if ! command -v nc >/dev/null 2>&1; then
        echo "📦 Instalando netcat..."
        sudo apt update && sudo apt install -y netcat-openbsd
    fi
    
    # Probar conectividad primero
    test_connection
    
    # Crear túnel
    create_tunnel
}

# Ejecutar
main "$@"