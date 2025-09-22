#!/bin/bash

echo "🚀 CONFIGURANDO VPN REAL DE TRYHACKME..."

# Función para crear dispositivo TUN
create_tun_device() {
    echo "🔧 Creando dispositivo TUN..."
    
    # Crear directorio si no existe
    sudo mkdir -p /dev/net
    
    # Crear dispositivo TUN
    sudo mknod /dev/net/tun c 10 200 2>/dev/null || echo "Dispositivo ya existe"
    
    # Dar permisos
    sudo chmod 666 /dev/net/tun
    
    # Verificar
    if [ -c /dev/net/tun ]; then
        echo "✅ Dispositivo TUN creado correctamente"
        ls -la /dev/net/tun
    else
        echo "❌ Error creando dispositivo TUN"
        return 1
    fi
}

# Función para cargar módulo TUN
load_tun_module() {
    echo "🔧 Cargando módulo TUN..."
    
    # Intentar cargar módulo
    if sudo modprobe tun 2>/dev/null; then
        echo "✅ Módulo TUN cargado correctamente"
    else
        echo "⚠️  Módulo TUN no disponible, continuando..."
    fi
}

# Función para configurar rutas
setup_routes() {
    echo "🔧 Configurando rutas de red..."
    
    # Rutas de TryHackMe
    routes=(
        "10.10.0.0/16"
        "10.101.0.0/16"
        "10.103.0.0/16"
        "10.201.0.0/17"
    )
    
    for route in "${routes[@]}"; do
        if sudo ip route add $route via 172.30.0.1 2>/dev/null; then
            echo "✅ Ruta agregada: $route"
        else
            echo "⚠️  Ruta ya existe: $route"
        fi
    done
}

# Función para conectar OpenVPN
connect_openvpn() {
    echo "🔧 Conectando OpenVPN..."
    
    # Matar procesos anteriores
    sudo pkill openvpn 2>/dev/null
    
    # Limpiar logs
    sudo rm -f /tmp/openvpn.log
    
    # Conectar con configuración especial
    sudo openvpn \
        --config smurf2.ovpn \
        --dev tun0 \
        --daemon \
        --log /tmp/openvpn.log \
        --verb 1 \
        --mute-replay-warnings \
        --auth-nocache \
        --persist-key \
        --persist-tun \
        --resolv-retry infinite \
        --connect-retry-max 3 \
        --connect-retry 5 \
        --connect-timeout 30
        
    # Esperar conexión
    sleep 5
    
    # Verificar logs
    if [ -f /tmp/openvpn.log ]; then
        echo "📋 Logs de OpenVPN:"
        sudo cat /tmp/openvpn.log | tail -20
    fi
}

# Función para verificar conexión
verify_connection() {
    echo "🧪 Verificando conexión..."
    
    # Probar ping
    echo "📡 Probando ping a 10.10.221.34..."
    if sudo ping -c 3 10.10.221.34 2>/dev/null; then
        echo "✅ Ping exitoso a 10.10.221.34"
        return 0
    else
        echo "❌ Ping falló a 10.10.221.34"
    fi
    
    # Probar con curl
    echo "📡 Probando HTTP a 10.10.221.34..."
    if timeout 10 curl -s "http://10.10.221.34" >/dev/null 2>&1; then
        echo "✅ HTTP exitoso a 10.10.221.34"
        return 0
    else
        echo "❌ HTTP falló a 10.10.221.34"
    fi
    
    return 1
}

# Función para mostrar estado
show_status() {
    echo "📊 ESTADO DE LA VPN:"
    echo "===================="
    
    # Verificar dispositivo TUN
    if [ -c /dev/net/tun ]; then
        echo "✅ Dispositivo TUN: OK"
    else
        echo "❌ Dispositivo TUN: FALLO"
    fi
    
    # Verificar proceso OpenVPN
    if pgrep openvpn >/dev/null; then
        echo "✅ Proceso OpenVPN: ACTIVO"
    else
        echo "❌ Proceso OpenVPN: INACTIVO"
    fi
    
    # Verificar rutas
    echo "📋 Rutas configuradas:"
    ip route | grep "10.10\|10.101\|10.103\|10.201" || echo "❌ No hay rutas de TryHackMe"
    
    # Verificar interfaces
    echo "📋 Interfaces de red:"
    ip addr show | grep -E "(tun|tap)" || echo "❌ No hay interfaces TUN/TAP"
}

# Función principal
main() {
    echo "🚀 INICIANDO CONFIGURACIÓN VPN REAL..."
    echo "====================================="
    
    # Crear dispositivo TUN
    create_tun_device
    
    # Cargar módulo TUN
    load_tun_module
    
    # Configurar rutas
    setup_routes
    
    # Conectar OpenVPN
    connect_openvpn
    
    # Verificar conexión
    if verify_connection; then
        echo "🎉 ¡VPN CONECTADA EXITOSAMENTE!"
        echo "✅ Acceso a TryHackMe disponible"
    else
        echo "⚠️  VPN configurada pero sin acceso directo"
        echo "🔧 Intentando soluciones alternativas..."
    fi
    
    # Mostrar estado
    show_status
    
    echo "🔄 VPN ejecutándose en segundo plano..."
    echo "🛑 Para detener: sudo pkill openvpn"
}

# Ejecutar
main "$@"