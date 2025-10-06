#!/usr/bin/env python3
"""
Script simple que prueba conexión HTTP a través de cada proxy válido
"""

import socket
import sys

def test_proxy(proxy_ip, proxy_port, test_url='httpbin.org', test_path='/ip'):
    """Prueba un proxy haciendo petición HTTP"""
    try:
        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        sock.settimeout(5)
        sock.connect((proxy_ip, proxy_port))
        
        # Petición HTTP
        request = f"GET http://{test_url}{test_path} HTTP/1.0\r\n"
        request += f"Host: {test_url}\r\n\r\n"
        sock.send(request.encode())
        
        # Recibir respuesta
        response = b""
        while True:
            try:
                chunk = sock.recv(4096)
                if not chunk:
                    break
                response += chunk
            except socket.timeout:
                break
        
        sock.close()
        
        response_text = response.decode('utf-8', errors='ignore')
        
        # Mostrar resultado
        if 'HTTP' in response_text[:50]:
            first_line = response_text.split('\n')[0]
            print(f"✅ {proxy_ip}:{proxy_port}")
            print(f"   Status: {first_line.strip()}")
            
            # Mostrar IP si es httpbin.org/ip
            if 'origin' in response_text.lower():
                for line in response_text.split('\n'):
                    if 'origin' in line.lower():
                        print(f"   {line.strip()}")
            
            print("")
            return True
        else:
            print(f"❌ {proxy_ip}:{proxy_port} - Sin respuesta HTTP válida\n")
            return False
            
    except Exception as e:
        print(f"❌ {proxy_ip}:{proxy_port} - Error: {e}\n")
        return False

def main():
    print("╔════════════════════════════════════════════════════════════╗")
    print("║           🌐 PROXY WEB TESTER                              ║")
    print("╚════════════════════════════════════════════════════════════╝")
    print("")
    print("Probando proxies con httpbin.org/ip...")
    print("")
    
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
    
    print(f"📊 Probando {min(10, len(proxies))} proxies...\n")
    print("━" * 60)
    print("")
    
    successful = 0
    # Probar solo los primeros 10 para no saturar
    for ip, port in proxies[:10]:
        if test_proxy(ip, port):
            successful += 1
    
    print("━" * 60)
    print(f"✅ {successful}/{min(10, len(proxies))} proxies respondieron correctamente")
    print("━" * 60)

if __name__ == "__main__":
    main()
