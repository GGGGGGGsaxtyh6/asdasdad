#!/usr/bin/env python3
import requests
import time

base_url = "http://94.237.53.81:59575"
session = requests.Session()

# Obtener sesión
session.get(base_url)

# Verificar estado cada segundo durante 30 segundos
print("[*] Monitoreando estado del sistema...")
for i in range(30):
    time.sleep(1)
    
    # Verificar updates
    r = session.get(f"{base_url}/updates")
    print(f"\n[{i+1}] Estado (/updates):")
    print(r.text)
    
    if 'HTB{' in r.text or 'flag' in r.text.lower():
        print(f"\n{'='*60}")
        print("[SUCCESS] FLAG ENCONTRADA EN /updates!")
        print(f"{'='*60}")
        print(r.text)
        break
    
    # Verificar página principal
    r = session.get(base_url)
    if 'HTB{' in r.text or 'flag' in r.text.lower():
        print(f"\n{'='*60}")
        print("[SUCCESS] FLAG ENCONTRADA EN PÁGINA PRINCIPAL!")
        print(f"{'='*60}")
        import re
        flags = re.findall(r'HTB\{[^}]+\}', r.text)
        if flags:
            for flag in flags:
                print(flag)
        else:
            print(r.text)
        break

print("\n[*] Monitoreo completado")
