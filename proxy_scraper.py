#!/usr/bin/env python3
"""
Scraper de proxies gratuitos y legales
Obtiene IPs válidas de fuentes públicas y las valida
"""

import requests
import re
import socket
import concurrent.futures
from typing import List, Tuple, Set
from dataclasses import dataclass
import time
import json

@dataclass
class Proxy:
    ip: str
    port: int
    country: str = ""
    anonymity: str = ""
    https: bool = False
    
    def __str__(self):
        return f"{self.ip}:{self.port}"
    
    def __hash__(self):
        return hash((self.ip, self.port))
    
    def __eq__(self, other):
        return self.ip == other.ip and self.port == other.port


class ProxyScraper:
    def __init__(self, timeout=5):
        self.timeout = timeout
        self.session = requests.Session()
        self.session.headers.update({
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36'
        })
        
    def scrape_free_proxy_list(self) -> Set[Proxy]:
        """Scrape de free-proxy-list.net"""
        proxies = set()
        try:
            print("📡 Scraping free-proxy-list.net...")
            url = "https://free-proxy-list.net/"
            response = self.session.get(url, timeout=10)
            
            # Buscar tabla de proxies
            pattern = r'<td>(\d+\.\d+\.\d+\.\d+)</td><td>(\d+)</td><td>(\w+)</td><td>(\w+)</td><td>(yes|no)</td>'
            matches = re.findall(pattern, response.text)
            
            for match in matches:
                ip, port, country, anonymity, https = match
                proxy = Proxy(
                    ip=ip,
                    port=int(port),
                    country=country,
                    anonymity=anonymity,
                    https=(https == 'yes')
                )
                proxies.add(proxy)
            
            print(f"  ✓ Encontrados {len(proxies)} proxies")
        except Exception as e:
            print(f"  ✗ Error: {e}")
        
        return proxies
    
    def scrape_sslproxies(self) -> Set[Proxy]:
        """Scrape de sslproxies.org"""
        proxies = set()
        try:
            print("📡 Scraping sslproxies.org...")
            url = "https://www.sslproxies.org/"
            response = self.session.get(url, timeout=10)
            
            pattern = r'<td>(\d+\.\d+\.\d+\.\d+)</td><td>(\d+)</td>'
            matches = re.findall(pattern, response.text)
            
            for ip, port in matches:
                proxy = Proxy(ip=ip, port=int(port), https=True)
                proxies.add(proxy)
            
            print(f"  ✓ Encontrados {len(proxies)} proxies SSL")
        except Exception as e:
            print(f"  ✗ Error: {e}")
        
        return proxies
    
    def scrape_pubproxy_api(self) -> Set[Proxy]:
        """Scrape usando PubProxy API"""
        proxies = set()
        try:
            print("📡 Obteniendo proxies de PubProxy API...")
            url = "http://pubproxy.com/api/proxy?limit=20&format=json&type=http"
            response = self.session.get(url, timeout=10)
            data = response.json()
            
            if 'data' in data:
                for item in data['data']:
                    proxy = Proxy(
                        ip=item.get('ip', ''),
                        port=int(item.get('port', 0)),
                        country=item.get('country', ''),
                        https=item.get('support', {}).get('https', 0) == 1
                    )
                    if proxy.ip and proxy.port:
                        proxies.add(proxy)
            
            print(f"  ✓ Encontrados {len(proxies)} proxies")
        except Exception as e:
            print(f"  ✗ Error: {e}")
        
        return proxies
    
    def scrape_geonode(self) -> Set[Proxy]:
        """Scrape de GeoNode API (gratuita y legal)"""
        proxies = set()
        try:
            print("📡 Obteniendo proxies de GeoNode...")
            url = "https://proxylist.geonode.com/api/proxy-list?limit=100&page=1&sort_by=lastChecked&sort_type=desc"
            response = self.session.get(url, timeout=10)
            data = response.json()
            
            if 'data' in data:
                for item in data['data']:
                    proxy = Proxy(
                        ip=item.get('ip', ''),
                        port=int(item.get('port', 0)),
                        country=item.get('country', ''),
                        anonymity=item.get('anonymityLevel', ''),
                        https=('https' in item.get('protocols', []))
                    )
                    if proxy.ip and proxy.port:
                        proxies.add(proxy)
            
            print(f"  ✓ Encontrados {len(proxies)} proxies")
        except Exception as e:
            print(f"  ✗ Error: {e}")
        
        return proxies
    
    def validate_proxy(self, proxy: Proxy) -> Tuple[Proxy, bool]:
        """Valida si un proxy está activo usando socket"""
        try:
            sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            sock.settimeout(self.timeout)
            result = sock.connect_ex((proxy.ip, proxy.port))
            sock.close()
            return (proxy, result == 0)
        except:
            return (proxy, False)
    
    def validate_proxies(self, proxies: Set[Proxy], max_workers=50) -> List[Proxy]:
        """Valida múltiples proxies en paralelo"""
        print(f"\n🔍 Validando {len(proxies)} proxies...")
        valid_proxies = []
        
        with concurrent.futures.ThreadPoolExecutor(max_workers=max_workers) as executor:
            futures = {executor.submit(self.validate_proxy, proxy): proxy for proxy in proxies}
            
            completed = 0
            for future in concurrent.futures.as_completed(futures):
                proxy, is_valid = future.result()
                completed += 1
                
                if is_valid:
                    valid_proxies.append(proxy)
                    print(f"  ✓ {proxy} válido ({completed}/{len(proxies)})")
                else:
                    print(f"  ✗ {proxy} no responde ({completed}/{len(proxies)})")
        
        return valid_proxies
    
    def scrape_all(self) -> Set[Proxy]:
        """Scrape de todas las fuentes"""
        all_proxies = set()
        
        print("=" * 60)
        print("🌐 PROXY SCRAPER - Fuentes gratuitas y legales")
        print("=" * 60 + "\n")
        
        # Scrape de todas las fuentes
        all_proxies.update(self.scrape_free_proxy_list())
        all_proxies.update(self.scrape_sslproxies())
        all_proxies.update(self.scrape_pubproxy_api())
        all_proxies.update(self.scrape_geonode())
        
        print(f"\n📊 Total de proxies únicos encontrados: {len(all_proxies)}")
        return all_proxies


def save_proxies(proxies: List[Proxy], filename='valid_proxies.txt'):
    """Guarda proxies válidos en archivo de texto"""
    with open(filename, 'w') as f:
        for proxy in proxies:
            f.write(f"{proxy.ip}:{proxy.port}\n")
    print(f"\n💾 Guardados {len(proxies)} proxies válidos en '{filename}'")


def save_proxies_json(proxies: List[Proxy], filename='valid_proxies.json'):
    """Guarda proxies con metadata en JSON"""
    data = []
    for proxy in proxies:
        data.append({
            'ip': proxy.ip,
            'port': proxy.port,
            'country': proxy.country,
            'anonymity': proxy.anonymity,
            'https': proxy.https
        })
    
    with open(filename, 'w') as f:
        json.dump(data, f, indent=2)
    print(f"💾 Guardados detalles en '{filename}'")


def save_nc_examples(proxies: List[Proxy], filename='nc_examples.sh'):
    """Crea archivo con ejemplos de uso con netcat"""
    with open(filename, 'w') as f:
        f.write("#!/bin/bash\n")
        f.write("# Ejemplos de uso con netcat (nc)\n\n")
        
        if proxies:
            proxy = proxies[0]
            f.write(f"# Conectar al proxy con nc:\n")
            f.write(f"nc {proxy.ip} {proxy.port}\n\n")
            
            f.write(f"# Hacer petición HTTP a través del proxy:\n")
            f.write(f"echo -e 'GET http://example.com HTTP/1.0\\r\\n\\r\\n' | nc {proxy.ip} {proxy.port}\n\n")
            
            f.write(f"# Testear conexión:\n")
            f.write(f"nc -zv {proxy.ip} {proxy.port}\n\n")
            
            f.write(f"# Proxies disponibles:\n")
            for p in proxies[:10]:  # Primeros 10
                f.write(f"# {p.ip}:{p.port}\n")
    
    print(f"💾 Guardados ejemplos de nc en '{filename}'")


def main():
    scraper = ProxyScraper(timeout=3)
    
    # Scrape de todas las fuentes
    all_proxies = scraper.scrape_all()
    
    if not all_proxies:
        print("\n❌ No se encontraron proxies")
        return
    
    # Validar proxies
    valid_proxies = scraper.validate_proxies(all_proxies)
    
    if valid_proxies:
        print(f"\n✅ Se encontraron {len(valid_proxies)} proxies válidos")
        
        # Guardar resultados
        save_proxies(valid_proxies)
        save_proxies_json(valid_proxies)
        save_nc_examples(valid_proxies)
        
        print("\n" + "=" * 60)
        print("📋 PROXIES VÁLIDOS ENCONTRADOS:")
        print("=" * 60)
        for proxy in valid_proxies[:20]:  # Mostrar primeros 20
            https_icon = "🔒" if proxy.https else "🔓"
            print(f"{https_icon} {proxy.ip}:{proxy.port} [{proxy.country}]")
        
        if len(valid_proxies) > 20:
            print(f"... y {len(valid_proxies) - 20} más")
    else:
        print("\n❌ No se encontraron proxies válidos")


if __name__ == "__main__":
    main()
