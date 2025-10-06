#!/usr/bin/env python3
"""
Script que se conecta a través del proxy funcional y escanea directorios
"""

import socket
import sys

# Proxy verificado que funciona
WORKING_PROXY = ("51.79.99.237", 4601)

# Directorios comunes a probar
DIRECTORIES = [
    '/',
    '/robots.txt',
    '/sitemap.xml',
    '/api',
    '/admin',
    '/wp-admin',
    '/phpmyadmin',
    '/login',
    '/dashboard',
    '/static',
    '/images',
    '/js',
    '/css',
    '/assets',
    '/uploads',
    '/files',
    '/backup',
    '/config',
    '/test',
    '/demo',
]

def scan_url(proxy_ip, proxy_port, target_host, path):
    """Escanea una URL específica a través del proxy"""
    try:
        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        sock.settimeout(5)
        sock.connect((proxy_ip, proxy_port))
        
        # Petición HTTP
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
                if len(response) > 50000:
                    break
            except socket.timeout:
                break
        
        sock.close()
        
        text = response.decode('utf-8', errors='ignore')
        
        if text:
            first_line = text.split('\n')[0]
            if 'HTTP' in first_line:
                parts = first_line.split()
                if len(parts) >= 2:
                    status = parts[1]
                    size = len(response)
                    return status, size, text[:500]
        
        return None, 0, None
        
    except Exception as e:
        return None, 0, None

def main():
    if len(sys.argv) < 2:
        print("╔════════════════════════════════════════════════════════════╗")
        print("║      🔍 DIRECTORY SCANNER - Usando Proxy Funcional         ║")
        print("╚════════════════════════════════════════════════════════════╝")
        print("")
        print(f"Proxy: {WORKING_PROXY[0]}:{WORKING_PROXY[1]}")
        print("")
        print("Uso: python3 scan_with_working_proxy.py <target>")
        print("")
        print("Ejemplos:")
        print("  python3 scan_with_working_proxy.py example.com")
        print("  python3 scan_with_working_proxy.py httpbin.org")
        print("  python3 scan_with_working_proxy.py testhtml5.vulnweb.com")
        print("")
        sys.exit(1)
    
    target = sys.argv[1]
    
    print("╔════════════════════════════════════════════════════════════╗")
    print("║      🔍 DIRECTORY SCANNER - Usando Proxy Funcional         ║")
    print("╚════════════════════════════════════════════════════════════╝")
    print("")
    print(f"🎯 Target: {target}")
    print(f"🔌 Proxy: {WORKING_PROXY[0]}:{WORKING_PROXY[1]}")
    print(f"📂 Escaneando {len(DIRECTORIES)} directorios...")
    print("")
    print("━" * 60)
    print("")
    
    results = []
    
    for i, path in enumerate(DIRECTORIES, 1):
        status, size, preview = scan_url(WORKING_PROXY[0], WORKING_PROXY[1], target, path)
        
        if status:
            icon = "✅" if status.startswith('2') else "⚠️" if status.startswith('3') else "❌"
            print(f"[{i}/{len(DIRECTORIES)}] {icon} {target}{path:<20} → {status} ({size} bytes)")
            
            if status.startswith('2'):
                results.append({
                    'path': path,
                    'status': status,
                    'size': size,
                    'preview': preview
                })
        else:
            print(f"[{i}/{len(DIRECTORIES)}] ⏱️  {target}{path:<20} → TIMEOUT")
    
    print("")
    print("━" * 60)
    print("📊 RESUMEN:")
    print("━" * 60)
    print("")
    
    if results:
        print(f"✅ {len(results)} directorios accesibles (200 OK):\n")
        for r in results:
            print(f"   📁 {r['path']}")
            print(f"      Status: {r['status']}, Size: {r['size']} bytes")
            if 'html' in r['preview'].lower() or '<' in r['preview']:
                print(f"      Tipo: HTML/Página web")
            elif 'json' in r['preview'].lower() or '{' in r['preview']:
                print(f"      Tipo: JSON/API")
            print("")
    else:
        print("⚠️  No se encontraron directorios accesibles (200 OK)")
    
    print("━" * 60)
    print("✅ Escaneo completado")
    print("━" * 60)

if __name__ == "__main__":
    main()
