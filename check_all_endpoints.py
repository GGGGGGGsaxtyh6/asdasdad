#!/usr/bin/env python3
import requests
import re

base_url = 'http://94.237.53.81:59575'
s = requests.Session()
r = s.get(base_url)

# Extraer todos los posibles endpoints del HTML
urls = re.findall(r'(?:href|action|url)=["\'](/[^"\']+)["\']', r.text)
urls = list(set(urls))

print(f"[*] Found {len(urls)} unique endpoints")
for url in urls:
    print(f"  {url}")

# Probar cada uno
for url in urls:
    try:
        r = s.get(f'{base_url}{url}')
        if r.status_code == 200 and len(r.text) < 10000:
            print(f"\n[+] {url}: {r.status_code} ({len(r.text)} bytes)")
            if 'HTB{' in r.text:
                print(f"  [SUCCESS]", re.findall(r'HTB\{[^}]+\}', r.text))
                break
    except:
        pass
