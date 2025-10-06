#!/usr/bin/env python3
"""
Escanea TODAS las IPs válidas y muestra qué hay en cada una
"""

import socket
import sys

DIRECTORIES = [
    '/',
    '/robots.txt',
    '/sitemap.xml',
    '/admin',
    '/login',
    '/api',
    '/status',
    '/health',
    '/info',
    '/test',
]

def scan_ip(ip, port):
    """Escanea una IP directamente para ver qué hay"""
    print(f"\n╔{'═'*58}╗")
    print(f"║ 🔍 ESCANEANDO: {ip}:{port:<40} ║")
    print(f"╚{'═'*58}╝\n")
    
    found = []
    
    for path in DIRECTORIES:
        try:
            sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            sock.settimeout(3)
            sock.connect((ip, port))
            
            # Petición HTTP directa a la IP
            request = f"GET {path} HTTP/1.1\r\n"
            request += f"Host: {ip}\r\n"
            request += "User-Agent: Mozilla/5.0\r\n"
            request += "Connection: close\r\n\r\n"
            
            sock.send(request.encode())
            
            response = b""
            while True:
                try:
                    chunk = sock.recv(4096)
                    if not chunk:
                        break
                    response += chunk
                    if len(response) > 20000:
                        break
                except:
                    break
            
            sock.close()
            
            text = response.decode('utf-8', errors='ignore')
            
            if text and 'HTTP' in text[:50]:
                first_line = text.split('\n')[0]
                if '200' in first_line:
                    icon = "✅"
                    size = len(response)
                    
                    # Detectar tipo de contenido
                    content_type = "Desconocido"
                    if '<html' in text.lower() or '<!doctype' in text.lower():
                        content_type = "HTML"
                    elif '{' in text and '"' in text:
                        content_type = "JSON/API"
                    elif 'User-agent:' in text or 'Disallow:' in text:
                        content_type = "robots.txt"
                    elif '<?xml' in text:
                        content_type = "XML"
                    
                    print(f"{icon} {path:<20} → 200 OK ({size} bytes) - {content_type}")
                    found.append((path, size, content_type, text[:500]))
                    
                elif '30' in first_line:
                    print(f"⚠️  {path:<20} → REDIRECT {first_line.split()[1]}")
                elif '401' in first_line or '403' in first_line:
                    print(f"🔒 {path:<20} → {first_line.split()[1]} (Protegido)")
                else:
                    print(f"❌ {path:<20} → {first_line.split()[1] if len(first_line.split()) > 1 else 'ERROR'}")
            else:
                print(f"⏱️  {path:<20} → Sin respuesta HTTP")
                
        except Exception as e:
            print(f"❌ {path:<20} → Error de conexión")
    
    if found:
        print(f"\n📊 RESUMEN {ip}:{port}:")
        print(f"   ✅ {len(found)} recursos accesibles\n")
        for path, size, ctype, preview in found:
            print(f"   📁 {path}")
            print(f"      Tipo: {ctype}, Tamaño: {size} bytes")
            if 'HTML' in ctype:
                # Buscar título
                if '<title>' in preview.lower():
                    title_start = preview.lower().find('<title>') + 7
                    title_end = preview.lower().find('</title>')
                    if title_end > title_start:
                        title = preview[title_start:title_end].strip()
                        print(f"      Título: {title}")
            print()
    else:
        print(f"\n⚠️  No se encontraron recursos accesibles en {ip}:{port}\n")
    
    print("─" * 60)
    return found

def main():
    print("╔════════════════════════════════════════════════════════════╗")
    print("║     🔍 SCANNER COMPLETO - TODAS LAS IPs VÁLIDAS            ║")
    print("╚════════════════════════════════════════════════════════════╝\n")
    
    # Cargar todas las IPs
    proxies = []
    with open('IPS_CONECTADAS.txt', 'r') as f:
        for line in f:
            line = line.strip()
            if line and not line.startswith('#') and ':' in line:
                try:
                    ip, port = line.split(':')
                    proxies.append((ip, int(port)))
                except:
                    pass
    
    print(f"📡 Total de IPs a escanear: {len(proxies)}\n")
    print("🕐 Esto puede tardar varios minutos...\n")
    print("="*60)
    
    all_results = {}
    
    # Escanear TODAS las IPs (limitamos a 20 para no tardar eternamente)
    for i, (ip, port) in enumerate(proxies[:20], 1):
        print(f"\n[{i}/{min(20, len(proxies))}]")
        results = scan_ip(ip, port)
        if results:
            all_results[f"{ip}:{port}"] = results
    
    # RESUMEN FINAL
    print("\n\n" + "="*60)
    print("📊 RESUMEN FINAL DE TODAS LAS IPs")
    print("="*60 + "\n")
    
    if all_results:
        print(f"✅ {len(all_results)} IPs tienen contenido accesible:\n")
        for proxy, items in all_results.items():
            print(f"🌐 {proxy}")
            for path, size, ctype, _ in items:
                print(f"   └─ {path} ({ctype}, {size} bytes)")
            print()
    else:
        print("⚠️  Ninguna IP tiene contenido web accesible directamente")
        print("    (Probablemente son proxies puros, no servidores web)\n")
    
    print("="*60)

if __name__ == "__main__":
    main()
