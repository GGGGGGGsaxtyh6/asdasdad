#!/usr/bin/env python3
import hashlib
import string

# Buscar un string que cuando se hashee como md5(X + 'flag.php' + X)[:8]
# produzca un hash que empiece con "0e" seguido de dígitos

# Probar con strings cortos
chars = string.ascii_letters + string.digits + "{}_"

print("Buscando colisión MD5 para flag.php...")
print("Probando strings de 1-4 caracteres...")

tested = 0
for len1 in range(0, 5):
    if len1 == 0:
        candidates = [""]
    elif len1 == 1:
        candidates = list(chars)
    else:
        # Para longitudes mayores, probar solo algunas combinaciones
        import itertools
        candidates = [''.join(p) for p in itertools.product(chars, repeat=len1)]
        if len(candidates) > 10000:
            candidates = candidates[:10000]  # Limitar para no tardar mucho
    
    for candidate in candidates:
        test_str = candidate + "flag.php" + candidate
        h = hashlib.md5(test_str.encode()).hexdigest()[:8]
        tested += 1
        
        if tested % 10000 == 0:
            print(f"  Probados: {tested}, último: '{candidate}'")
        
        # Buscar hashes que empiecen con 0e seguido de solo dígitos
        if h[:2] == "0e" and h[2:].isdigit():
            print(f"\n¡ENCONTRADO!")
            print(f"  String: '{candidate}'")
            print(f"  Hash: {h}")
            print(f"  Test: md5('{test_str}') = {hashlib.md5(test_str.encode()).hexdigest()}")
            break
    else:
        continue
    break

print(f"\nTotal probados: {tested}")