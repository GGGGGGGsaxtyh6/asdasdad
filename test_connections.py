#!/usr/bin/env python3
"""
Script para probar conexiones a proxies con timeout
Se ejecuta en paralelo para cada archivo de IPs
"""

import sys
import socket
import time
from datetime import datetime

def test_proxy(ip, port, timeout=3):
    """Testea conexión a un proxy con timeout"""
    try:
        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        sock.settimeout(timeout)
        start = time.time()
        result = sock.connect_ex((ip, port))
        elapsed = time.time() - start
        sock.close()
        
        return result == 0, elapsed
    except Exception as e:
        return False, 0

def main():
    if len(sys.argv) < 3:
        print("Uso: python3 test_connections.py <archivo_ips> <id_terminal>")
        sys.exit(1)
    
    filename = sys.argv[1]
    terminal_id = sys.argv[2]
    
    print(f"╔{'═'*60}╗")
    print(f"║ TERMINAL {terminal_id} - Probando: {filename:<42} ║")
    print(f"╚{'═'*60}╝")
    
    try:
        with open(filename, 'r') as f:
            proxies = [line.strip() for line in f if line.strip()]
    except FileNotFoundError:
        print(f"❌ Error: No se encuentra {filename}")
        sys.exit(1)
    
    if not proxies:
        print(f"⚠️  Archivo vacío: {filename}")
        return
    
    print(f"\n🔍 Total de proxies a probar: {len(proxies)}\n")
    
    successful = 0
    failed = 0
    
    for i, proxy in enumerate(proxies, 1):
        try:
            ip, port = proxy.split(':')
            port = int(port)
            
            print(f"[{i}/{len(proxies)}] Probando {ip}:{port}... ", end='', flush=True)
            
            is_connected, elapsed = test_proxy(ip, port, timeout=5)
            
            if is_connected:
                print(f"✅ CONECTADO ({elapsed:.2f}s)")
                successful += 1
            else:
                print(f"❌ TIMEOUT/RECHAZADO")
                failed += 1
                
        except ValueError:
            print(f"❌ FORMATO INVÁLIDO: {proxy}")
            failed += 1
        except KeyboardInterrupt:
            print(f"\n\n⚠️  Interrumpido por usuario")
            break
    
    print(f"\n{'='*60}")
    print(f"📊 RESUMEN TERMINAL {terminal_id}:")
    print(f"   ✅ Conectados: {successful}")
    print(f"   ❌ Fallidos: {failed}")
    print(f"   📈 Tasa de éxito: {(successful/(successful+failed)*100) if (successful+failed) > 0 else 0:.1f}%")
    print(f"{'='*60}\n")

if __name__ == "__main__":
    main()
