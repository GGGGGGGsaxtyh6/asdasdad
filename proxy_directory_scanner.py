#!/usr/bin/env python3
"""
Script que se conecta a través de proxies válidos y escanea directorios comunes
"""

import socket
import sys
import time
from concurrent.futures import ThreadPoolExecutor, as_completed

# Directorios comunes a escanear
COMMON_DIRS = [
    '/',
    '/admin',
    '/login',
    '/api',
    '/wp-admin',
    '/dashboard',
    '/phpmyadmin',
    '/panel',
    '/uploads',
    '/images',
    '/css',
    '/js',
    '/files',
    '/backup',
    '/config',
    '/includes',
    '/vendor',
    '/test',
    '/demo',
    '/static',
    '/assets',
]

def load_proxies(filename='IPS_CONECTADAS.txt'):
    """Carga proxies del archivo de IPs conectadas"""
    proxies = []
    try:
        with open(filename, 'r') as f:
            for line in f:
                line = line.strip()
                if line and not line.startswith('#') and ':' in line:
                    try:
                        ip, port = line.split(':')
                        proxies.append((ip, int(port)))
                    except:
                        pass
        return proxies
    except FileNotFoundError:
        print(f"❌ Error: No se encuentra {filename}")
        return []

def connect_through_proxy(proxy_ip, proxy_port, target_host, target_path='/', timeout=5):
    """
    Conecta a través del proxy y hace petición HTTP
    """
    try:
        # Crear socket
        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        sock.settimeout(timeout)
        
        # Conectar al proxy
        sock.connect((proxy_ip, proxy_port))
        
        # Construir petición HTTP CONNECT o GET directo
        request = f"GET http://{target_host}{target_path} HTTP/1.0\r\n"
        request += f"Host: {target_host}\r\n"
        request += "User-Agent: Mozilla/5.0\r\n"
        request += "Connection: close\r\n\r\n"
        
        # Enviar petición
        sock.send(request.encode())
        
        # Recibir respuesta
        response = b""
        while True:
            try:
                chunk = sock.recv(4096)
                if not chunk:
                    break
                response += chunk
                if len(response) > 50000:  # Límite de 50KB
                    break
            except socket.timeout:
                break
        
        sock.close()
        
        # Decodificar respuesta
        try:
            response_text = response.decode('utf-8', errors='ignore')
        except:
            response_text = response.decode('latin-1', errors='ignore')
        
        # Extraer código de estado
        if response_text:
            first_line = response_text.split('\n')[0]
            if 'HTTP' in first_line:
                parts = first_line.split()
                if len(parts) >= 2:
                    status_code = parts[1]
                    return status_code, len(response_text)
        
        return None, 0
        
    except Exception as e:
        return None, 0

def scan_directory(proxy, target_host, directory):
    """Escanea un directorio específico a través del proxy"""
    proxy_ip, proxy_port = proxy
    proxy_str = f"{proxy_ip}:{proxy_port}"
    
    status, size = connect_through_proxy(proxy_ip, proxy_port, target_host, directory)
    
    if status:
        icon = "✅" if status.startswith('2') else "⚠️" if status.startswith('3') else "❌"
        return {
            'proxy': proxy_str,
            'path': directory,
            'status': status,
            'size': size,
            'success': True,
            'icon': icon
        }
    else:
        return {
            'proxy': proxy_str,
            'path': directory,
            'status': 'TIMEOUT',
            'size': 0,
            'success': False,
            'icon': '❌'
        }

def main():
    if len(sys.argv) < 2:
        print("╔════════════════════════════════════════════════════════════╗")
        print("║        🔍 PROXY DIRECTORY SCANNER                          ║")
        print("╚════════════════════════════════════════════════════════════╝")
        print("")
        print("Uso: python3 proxy_directory_scanner.py <target_host>")
        print("")
        print("Ejemplo:")
        print("  python3 proxy_directory_scanner.py example.com")
        print("  python3 proxy_directory_scanner.py httpbin.org")
        print("")
        sys.exit(1)
    
    target_host = sys.argv[1]
    
    print("╔════════════════════════════════════════════════════════════╗")
    print("║        🔍 PROXY DIRECTORY SCANNER                          ║")
    print("╚════════════════════════════════════════════════════════════╝")
    print("")
    print(f"🎯 Target: {target_host}")
    print("")
    
    # Cargar proxies
    print("📡 Cargando proxies válidos...")
    proxies = load_proxies()
    
    if not proxies:
        print("❌ No se encontraron proxies válidos")
        sys.exit(1)
    
    print(f"✅ {len(proxies)} proxies cargados")
    print("")
    
    # Usar solo los primeros 5 proxies para no saturar
    proxies_to_use = proxies[:5]
    print(f"🔧 Usando {len(proxies_to_use)} proxies para escaneo:")
    for ip, port in proxies_to_use:
        print(f"   • {ip}:{port}")
    print("")
    
    print(f"📂 Escaneando {len(COMMON_DIRS)} directorios comunes...")
    print("━" * 60)
    print("")
    
    results = []
    total_scans = len(proxies_to_use) * len(COMMON_DIRS)
    completed = 0
    
    # Escanear en paralelo
    with ThreadPoolExecutor(max_workers=10) as executor:
        futures = []
        for proxy in proxies_to_use:
            for directory in COMMON_DIRS:
                future = executor.submit(scan_directory, proxy, target_host, directory)
                futures.append(future)
        
        for future in as_completed(futures):
            result = future.result()
            results.append(result)
            completed += 1
            
            if result['success'] and result['status'].startswith('2'):
                print(f"[{completed}/{total_scans}] {result['icon']} {result['proxy']} → {target_host}{result['path']} = {result['status']} ({result['size']} bytes)")
    
    print("")
    print("━" * 60)
    print("📊 RESUMEN DE RESULTADOS:")
    print("━" * 60)
    print("")
    
    # Agrupar por directorio
    dir_results = {}
    for result in results:
        path = result['path']
        if path not in dir_results:
            dir_results[path] = []
        dir_results[path].append(result)
    
    # Mostrar directorios encontrados
    found_dirs = []
    for directory, dir_res in sorted(dir_results.items()):
        successful = [r for r in dir_res if r['status'].startswith('2')]
        if successful:
            found_dirs.append(directory)
            print(f"✅ {directory}")
            for r in successful:
                print(f"    └─ {r['proxy']} → {r['status']} ({r['size']} bytes)")
    
    if not found_dirs:
        print("⚠️  No se encontraron directorios accesibles (200 OK)")
    
    print("")
    print("━" * 60)
    print(f"✅ Escaneo completado: {len(results)} peticiones realizadas")
    print(f"📁 Directorios accesibles: {len(found_dirs)}")
    print("━" * 60)

if __name__ == "__main__":
    main()
