#!/usr/bin/env python3
import hashlib
import string

# El truco REAL: encontrar dos archivos diferentes donde:
# md5($flag + file1 + $flag)[:8] == md5($flag + file2 + $flag)[:8]
# 
# Pero como no conozco $flag, busco archivos donde SIN FLAG también colisionan

print("Buscando archivos que produzcan el MISMO hash parcial...")
print("Esto podría permitir usar hash de index.php para abrir flag.php\n")

hashes_seen = {}

# Generar muchas variaciones de nombres de archivo
candidates = ["flag.php", "index.php"]

# Agregar variaciones con paths
for base in ["flag.php", "index.php"]:
    for prefix in ["", ".", "./", "../", "///", ".//", "././"]:
        for suffix in ["", "/", "//"]:
            candidates.append(f"{prefix}{base}{suffix}")

# Agregar nombres con padding
for base in ["flag.php", "index.php"]:
    for pad in ["\x00", " ", "\t", "\n"]:
        candidates.append(f"{base}{pad}")
        candidates.append(f"{pad}{base}")

print(f"Probando {len(candidates)} candidatos...\n")

for filename in candidates:
    # Calcular hash SIN flag (solo para comparar patrones)
    h = hashlib.md5(filename.encode()).hexdigest()[:8]
    
    if h in hashes_seen:
        print(f"¡COLISIÓN ENCONTRADA!")
        print(f"  Archivo 1: {repr(hashes_seen[h])}")
        print(f"  Archivo 2: {repr(filename)}")
        print(f"  Hash común: {h}")
        print()
    else:
        hashes_seen[h] = filename

print(f"\nTotal archivos únicos: {len(hashes_seen)}")
print("No se encontraron colisiones simples.")

# Ahora buscar si algún path de flag.php genera el MISMO hash que index.php
print("\n" + "="*60)
print("Buscando paths de flag.php que tengan el mismo hash que index.php...")
print("="*60 + "\n")

target_hash = "b4382d64"
print(f"Hash objetivo (index.php): {target_hash}\n")

# Generar MUCHAS variaciones de flag.php
flag_variations = []

for i in range(0, 100):
    flag_variations.append("/" * i + "flag.php")
    flag_variations.append("." * i + "/flag.php")
    flag_variations.append("." + "/" * i + "flag.php")

for prefix in ["", ".", "./", "../", "../../", "level10/", "/level10/"]:
    for mid in ["", "/", "//", "///", "." * 5]:
        flag_variations.append(f"{prefix}{mid}flag.php")

print(f"Probando {len(flag_variations)} variaciones...")

found = False
for i, path in enumerate(flag_variations):
    if i % 100 == 0 and i > 0:
        print(f"  Progreso: {i}/{len(flag_variations)}")
    
    # Probar con diferentes flags simulados
    for fake_flag in ["", "X", "XX", "WEBSEC", "flag", "abc", "test"]:
        test_str = fake_flag + path + fake_flag
        h = hashlib.md5(test_str.encode()).hexdigest()[:8]
        
        if h == target_hash:
            print(f"\n¡¡¡MATCH ENCONTRADO!!!")
            print(f"  Path: {repr(path)}")
            print(f"  Fake flag: {repr(fake_flag)}")
            print(f"  Hash: {h}")
            found = True
            break
    
    if found:
        break

if not found:
    print("\nNo se encontró match directo. El hash depende de $flag desconocido.")