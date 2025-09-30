#!/usr/bin/env python3
import hashlib

# Buscar un nombre de archivo que produzca un hash "0e..." (interpretado como 0)
# Usamos strings que sabemos que producen hashes vulnerables

known_magic_hashes = [
    "240610708",  # md5 = 0e462097431906509019562988736854
    "QNKCDZO",    # md5 = 0e830400451993494058024219903391
    "s878926199a", # md5 = 0e545993274517709034328855841020
    "s155964671a", # md5 = 0e342768416822451524974117254469
    "s1091221200a", # md5 = 0e940624217856561557816327384675
]

print("Hashes mágicos conocidos:")
for val in known_magic_hashes:
    h = hashlib.md5(val.encode()).hexdigest()
    print(f"{val:15} -> {h}")
    if h[:2] == "0e" and h[2:].isdigit():
        print(f"  ✓ Es vulnerable a type juggling!")