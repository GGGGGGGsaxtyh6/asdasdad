#!/bin/bash

echo "🚀 CREANDO DISPOSITIVO TUN REAL..."

# Función para crear dispositivo TUN funcional
create_functional_tun() {
    echo "🔧 Creando dispositivo TUN funcional..."
    
    # Crear directorio de módulos
    sudo mkdir -p /lib/modules/$(uname -r)
    
    # Crear enlace simbólico
    sudo ln -sf /usr/src/linux-headers-6.14.0-29-generic /lib/modules/$(uname -r)/build
    
    # Crear dispositivo TUN
    sudo mknod /dev/net/tun c 10 200 2>/dev/null || echo "Dispositivo ya existe"
    sudo chmod 666 /dev/net/tun
    
    # Crear interfaz TUN manualmente
    sudo ip tuntap add dev tun0 mode tun 2>/dev/null || echo "Interfaz ya existe"
    
    # Configurar interfaz
    sudo ip addr add 10.23.167.6/16 dev tun0 2>/dev/null || echo "IP ya configurada"
    sudo ip link set tun0 up 2>/dev/null || echo "Interfaz ya activa"
    
    echo "✅ Dispositivo TUN creado"
}

# Función para configurar OpenVPN real
setup_real_openvpn() {
    echo "🔧 Configurando OpenVPN real..."
    
    # Crear configuración personalizada
    cat > /tmp/thm_real.conf << 'EOF'
client
dev tun0
proto udp
remote 52.16.156.56 1194
resolv-retry infinite
nobind
persist-key
persist-tun
ca [inline]
cert [inline]
key [inline]
verb 3
cipher AES-256-CBC
auth SHA256
comp-lzo
EOF

    # Extraer certificados del archivo original
    awk '/<ca>/,/<\/ca>/' smurf2.ovpn | sed '1d;$d' >> /tmp/thm_real.conf
    echo "</ca>" >> /tmp/thm_real.conf
    
    awk '/<cert>/,/<\/cert>/' smurf2.ovpn | sed '1d;$d' >> /tmp/thm_real.conf
    echo "</cert>" >> /tmp/thm_real.conf
    
    awk '/<key>/,/<\/key>/' smurf2.ovpn | sed '1d;$d' >> /tmp/thm_real.conf
    echo "</key>" >> /tmp/thm_real.conf
    
    echo "✅ Configuración OpenVPN creada"
}

# Función para conectar OpenVPN real
connect_real_openvpn() {
    echo "🔧 Conectando OpenVPN real..."
    
    # Matar procesos anteriores
    sudo pkill openvpn
    
    # Limpiar logs
    sudo rm -f /tmp/openvpn_real.log
    
    # Conectar con configuración real
    sudo openvpn \
        --config /tmp/thm_real.conf \
        --daemon \
        --log /tmp/openvpn_real.log \
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
    if [ -f /tmp/openvpn_real.log ]; then
        echo "📋 Logs de OpenVPN:"
        sudo cat /tmp/openvpn_real.log | tail -30
    fi
}

# Función para verificar conexión real
verify_real_connection() {
    echo "🧪 Verificando conexión real..."
    
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
    echo "🚀 CONFIGURANDO VPN REAL DE TRYHACKME..."
    echo "======================================="
    
    # Crear dispositivo TUN funcional
    create_functional_tun
    
    # Configurar OpenVPN real
    setup_real_openvpn
    
    # Conectar OpenVPN real
    connect_real_openvpn
    
    # Verificar conexión real
    if verify_real_connection; then
        echo "🎉 ¡VPN REAL CONECTADA EXITOSAMENTE!"
        echo "✅ Acceso real a TryHackMe disponible"
    else
        echo "⚠️  VPN configurada pero verificando conectividad..."
    fi
    
    echo "🔄 VPN REAL ejecutándose en segundo plano..."
    echo "🛑 Para detener: sudo pkill openvpn"
}

# Ejecutar
main "$@"