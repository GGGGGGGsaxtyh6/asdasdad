#!/usr/bin/env python3
import hashlib

# Buscar un path para flag.php que produzca un hash 0e...
# No conocemos $flag, pero podemos buscar patrones que generen 0e

prefixes = [
    "",
    ".",
    "./",
    "../level10/",
    ".//",
    "././",
    "/./",
]

suffixes = [
    "flag.php",
]

for prefix in prefixes:
    for suffix in suffixes:
        for mid in ["", "/", "//", "///", "////", "/" * 10]:
            path = prefix + mid + suffix
            # Probar diferentes flags simulados
            for fake_flag in ["", "X", "WEBSEC{test}", "flag"]:
                test_str = fake_flag + path + fake_flag
                h = hashlib.md5(test_str.encode()).hexdigest()[:8]
                if h[:2] == "0e" and h[2:].isdigit():
                    print(f"ENCONTRADO: path={path}, fake_flag='{fake_flag}', hash={h}")
                    
print("\nBuscando hashes 0e para flag.php con diferentes slashes:")
for num_slashes in range(1, 50):
    path = "/" * num_slashes + "flag.php"
    for fake_flag in ["", "x", "XX"]:
        test_str = fake_flag + path + fake_flag
        h = hashlib.md5(test_str.encode()).hexdigest()[:8]
        if h[:2] == "0e" and h[2:].isdigit():
            print(f"  path={path}, flag='{fake_flag}', hash={h}")