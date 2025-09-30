#!/usr/bin/env python3
import hashlib
import itertools
import string

print("Buscando un path de flag.php que produzca hash 0e...")
print("Probando combinaciones sistemáticas\n")

# Caracteres para probar
chars = string.ascii_letters + string.digits + "/._{}-"

# Probar prefijos/sufijos cortos
tested = 0
found = False

# Estrategia: probar prefijos antes de flag.php
print("Probando prefijos de 1-4 caracteres...")
for length in range(1, 5):
    if found:
        break
    
    print(f"  Longitud {length}...")
    
    # Limitar combinaciones para no tardar demasiado
    if length <= 2:
        combos = itertools.product(chars, repeat=length)
    else:
        # Para longitudes mayores, solo probar algunos caracteres comunes
        common_chars = "./_-0123456789"
        combos = itertools.product(common_chars, repeat=length)
    
    for combo in combos:
        prefix = ''.join(combo)
        path = prefix + "flag.php"
        
        # Probar con varios flags simulados
        for fake_flag in ["", "A", "XX", "abc", "test", "flag", "WEBSEC"]:
            test_str = fake_flag + path + fake_flag
            h = hashlib.md5(test_str.encode()).hexdigest()[:8]
            
            tested += 1
            if tested % 100000 == 0:
                print(f"    Probados: {tested:,}")
            
            # Buscar hashes 0e seguidos de solo dígitos
            if h[:2] == "0e" and h[2:].isdigit():
                print(f"\n¡¡¡ENCONTRADO!!!")
                print(f"  Path: {repr(path)}")
                print(f"  Fake flag usado: {repr(fake_flag)}")
                print(f"  Hash generado: {h}")
                print(f"  Interpretado como: 0")
                found = True
                break
        
        if found:
            break

if not found:
    print(f"\nNo encontrado después de {tested:,} pruebas.")
    print("Probando con sufijos después de flag.php...")
    
    tested2 = 0
    for length in range(1, 4):
        if found:
            break
        
        print(f"  Longitud {length}...")
        common_chars = "./_-0123456789"
        combos = itertools.product(common_chars, repeat=length)
        
        for combo in combos:
            suffix = ''.join(combo)
            path = "flag.php" + suffix
            
            for fake_flag in ["", "A", "XX", "abc"]:
                test_str = fake_flag + path + fake_flag
                h = hashlib.md5(test_str.encode()).hexdigest()[:8]
                
                tested2 += 1
                if tested2 % 50000 == 0:
                    print(f"    Probados: {tested2:,}")
                
                if h[:2] == "0e" and h[2:].isdigit():
                    print(f"\n¡¡¡ENCONTRADO!!!")
                    print(f"  Path: {repr(path)}")
                    print(f"  Fake flag: {repr(fake_flag)}")
                    print(f"  Hash: {h}")
                    found = True
                    break
            
            if found:
                break

print(f"\nTotal probados: {tested + tested2 if 'tested2' in locals() else tested:,}")