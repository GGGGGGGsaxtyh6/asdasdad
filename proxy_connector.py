#!/usr/bin/env python3
"""
Script que se conecta a través de proxies y lista información de conexión
"""

import socket
import sys

def send_http_request(proxy_ip, proxy_port, target_host, path='/'):
    """Envía petición HTTP a través del proxy"""
    try:
        # Conectar al proxy
        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        sock.settimeout(5)
        sock.connect((proxy_ip, proxy_port))
        
        # Construir petición HTTP
        request = f"GET http://{target_host}{path} HTTP/1.1\r\n"
        request += f"Host: {target_host}\r\n"
        request += "User-Agent: Mozilla/5.0\r\n"
        request += "Accept: */*\r\n"
        request += "Connection: close\r\n\r\n"
        
        sock.send(request.encode())
        
        # Recibir respuesta
        response = b""
        while True:
            try:
                chunk = sock.recv(4096)
                if not chunk:
                    break
                response += chunk
                if len(response) > 10000:
                    break
            except socket.timeout:
                break
        
        sock.close()
        return response.decode('utf-8', errors='ignore')
        
    except Exception as e:
        return None

def list_proxy_info(proxy_ip, proxy_port):
    """Obtiene información del proxy"""
    print(f"╔{'═'*58}╗")
    print(f"║ PROXY: {proxy_ip}:{proxy_port:<43} ║")
    print(f"╚{'═'*58}╝")
    
    # Test 1: httpbin.org/ip
    print("\n📍 Test 1: Obteniendo IP pública...")
    response = send_http_request(proxy_ip, proxy_port, 'httpbin.org', '/ip')
    if response:
        if '200 OK' in response or '"origin"' in response:
            print("✅ Proxy funcional")
            for line in response.split('\n'):
                if 'origin' in line.lower():
                    print(f"   IP vista: {line.strip()}")
        else:
            status = response.split('\n')[0] if response else 'Sin respuesta'
            print(f"⚠️  {status}")
    else:
        print("❌ No respondió")
    
    # Test 2: httpbin.org/headers
    print("\n📋 Test 2: Verificando headers...")
    response = send_http_request(proxy_ip, proxy_port, 'httpbin.org', '/headers')
    if response and '200 OK' in response:
        print("✅ Puede hacer peticiones GET")
        # Buscar User-Agent en la respuesta
        for line in response.split('\n'):
            if 'User-Agent' in line:
                print(f"   {line.strip()}")
    else:
        print("⚠️  Respuesta limitada")
    
    # Test 3: example.com
    print("\n🌐 Test 3: Conectando a example.com...")
    response = send_http_request(proxy_ip, proxy_port, 'example.com', '/')
    if response:
        if '200 OK' in response:
            print("✅ Acceso a sitios web: OK")
            size = len(response)
            print(f"   Tamaño respuesta: {size} bytes")
        else:
            status = response.split('\n')[0] if response else ''
            print(f"⚠️  {status}")
    else:
        print("❌ Sin respuesta")
    
    print("\n" + "─"*60 + "\n")

def main():
    print("╔════════════════════════════════════════════════════════════╗")
    print("║       🔌 PROXY CONNECTOR - Información de Conexión         ║")
    print("╚════════════════════════════════════════════════════════════╝\n")
    
    # Cargar proxies
    proxies = []
    try:
        with open('IPS_CONECTADAS.txt', 'r') as f:
            for line in f:
                line = line.strip()
                if line and not line.startswith('#') and ':' in line:
                    try:
                        ip, port = line.split(':')
                        proxies.append((ip, int(port)))
                    except:
                        pass
    except FileNotFoundError:
        print("❌ No se encuentra IPS_CONECTADAS.txt")
        sys.exit(1)
    
    if not proxies:
        print("❌ No se encontraron proxies")
        sys.exit(1)
    
    # Probar los primeros 5 proxies
    num_to_test = min(5, len(proxies))
    print(f"🔍 Probando los primeros {num_to_test} proxies...\n")
    print("="*60 + "\n")
    
    for i, (ip, port) in enumerate(proxies[:num_to_test], 1):
        print(f"[{i}/{num_to_test}]")
        list_proxy_info(ip, port)
    
    print("="*60)
    print("✅ Pruebas completadas")
    print("="*60)

if __name__ == "__main__":
    main()
