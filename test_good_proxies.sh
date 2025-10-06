#!/bin/bash

echo "╔════════════════════════════════════════════════════════════╗"
echo "║     🔥 PROBANDO PROXIES QUE SÍ FUNCIONAN                   ║"
echo "╚════════════════════════════════════════════════════════════╝"
echo ""

# Proxies que mostraron 200 OK antes
PROXIES=(
    "51.79.99.237:4601"
    "162.0.234.251:8080"
    "138.197.68.35:4857"
    "31.210.50.6:3128"
    "158.69.185.37:3129"
)

for proxy in "${PROXIES[@]}"; do
    IP=$(echo $proxy | cut -d: -f1)
    PORT=$(echo $proxy | cut -d: -f2)
    
    echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
    echo "🔌 Probando: $IP:$PORT"
    echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
    
    python3 -c "
import socket
import sys

def test_proxy(ip, port):
    try:
        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        sock.settimeout(5)
        sock.connect((ip, int(port)))
        
        request = 'GET http://httpbin.org/ip HTTP/1.0\r\nHost: httpbin.org\r\n\r\n'
        sock.send(request.encode())
        
        response = b''
        while True:
            try:
                chunk = sock.recv(4096)
                if not chunk:
                    break
                response += chunk
            except socket.timeout:
                break
        
        sock.close()
        text = response.decode('utf-8', errors='ignore')
        
        print('📥 Respuesta:')
        lines = text.split('\n')
        for i, line in enumerate(lines[:15]):
            print(f'   {line}')
        
        if '200 OK' in text:
            print('\n✅ PROXY FUNCIONAL - Responde 200 OK')
        elif '407' in text:
            print('\n⚠️  Requiere autenticación (407)')
        else:
            print(f'\n⚠️  Estado: {lines[0] if lines else \"desconocido\"}')
        
    except Exception as e:
        print(f'❌ Error: {e}')

test_proxy('$IP', '$PORT')
    "
    
    echo ""
done

echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo "✅ Pruebas completadas"
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
