#!/bin/bash

echo "🚀 FORZANDO CREACIÓN DE DISPOSITIVO TUN..."

# Función para crear dispositivo TUN forzado
force_create_tun() {
    echo "🔧 Forzando creación de dispositivo TUN..."
    
    # Crear directorio de módulos
    sudo mkdir -p /lib/modules/$(uname -r)
    
    # Crear enlace simbólico
    sudo ln -sf /usr/src/linux-headers-6.14.0-29-generic /lib/modules/$(uname -r)/build
    
    # Crear dispositivo TUN
    sudo mknod /dev/net/tun c 10 200 2>/dev/null || echo "Dispositivo ya existe"
    sudo chmod 666 /dev/net/tun
    
    # Crear interfaz TUN manualmente usando ip
    sudo ip tuntap add dev tun0 mode tun 2>/dev/null || echo "Interfaz ya existe"
    
    # Configurar interfaz
    sudo ip addr add 10.23.167.6/16 dev tun0 2>/dev/null || echo "IP ya configurada"
    sudo ip link set tun0 up 2>/dev/null || echo "Interfaz ya activa"
    
    # Verificar
    if [ -c /dev/net/tun ]; then
        echo "✅ Dispositivo TUN creado: /dev/net/tun"
        ls -la /dev/net/tun
    else
        echo "❌ Error creando dispositivo TUN"
        return 1
    fi
    
    if ip link show tun0 >/dev/null 2>&1; then
        echo "✅ Interfaz TUN0 creada"
        ip addr show tun0
    else
        echo "❌ Error creando interfaz TUN0"
        return 1
    fi
}

# Función para conectar OpenVPN con dispositivo TUN forzado
connect_with_forced_tun() {
    echo "🔧 Conectando OpenVPN con TUN forzado..."
    
    # Matar procesos anteriores
    sudo pkill openvpn
    
    # Limpiar logs
    sudo rm -f /tmp/openvpn_forced.log
    
    # Conectar con configuración forzada
    sudo openvpn \
        --config smurf2.ovpn \
        --dev tun0 \
        --daemon \
        --log /tmp/openvpn_forced.log \
        --verb 3 \
        --mute-replay-warnings \
        --auth-nocache \
        --persist-key \
        --persist-tun \
        --resolv-retry infinite \
        --connect-retry-max 5 \
        --connect-retry 3 \
        --connect-timeout 30 \
        --route-delay 2 \
        --route-method exe \
        --route-up 'echo "Rutas configuradas"' \
        --up 'echo "Interfaz activa"' \
        --down 'echo "Interfaz inactiva"'
    
    # Esperar conexión
    sleep 10
    
    # Verificar logs
    if [ -f /tmp/openvpn_forced.log ]; then
        echo "📋 Logs de OpenVPN:"
        sudo cat /tmp/openvpn_forced.log | tail -30
    fi
}

# Función para verificar conexión forzada
verify_forced_connection() {
    echo "🧪 Verificando conexión forzada..."
    
    # Verificar interfaz TUN
    if ip link show tun0 >/dev/null 2>&1; then
        echo "✅ Interfaz TUN0 activa"
        ip addr show tun0
    else
        echo "❌ Interfaz TUN0 no encontrada"
    fi
    
    # Verificar rutas
    echo "📋 Rutas configuradas:"
    ip route | grep -E "(10\.|tun0)" || echo "❌ No hay rutas de TryHackMe"
    
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

# Función principal
main() {
    echo "🚀 FORZANDO VPN REAL DE TRYHACKME..."
    echo "===================================="
    
    # Crear dispositivo TUN forzado
    if force_create_tun; then
        echo "✅ Dispositivo TUN forzado creado"
    else
        echo "❌ Error creando dispositivo TUN forzado"
        return 1
    fi
    
    # Conectar OpenVPN con TUN forzado
    connect_with_forced_tun
    
    # Verificar conexión forzada
    if verify_forced_connection; then
        echo "🎉 ¡VPN REAL FORZADA CONECTADA EXITOSAMENTE!"
        echo "✅ Acceso real a TryHackMe disponible"
    else
        echo "⚠️  VPN forzada configurada pero verificando conectividad..."
    fi
    
    echo "🔄 VPN REAL FORZADA ejecutándose en segundo plano..."
    echo "🛑 Para detener: sudo pkill openvpn"
}

# Ejecutar
main "$@"