#!/usr/bin/env python3
import binascii
import subprocess
import re

cookie = "3d2d8bf19f0ce326f736a1443b952355b1fad1874477f658622708ae3c2b008c833e083ffd1bce3a3d4a8e0094382e53"

iv_hex = cookie[:32]
ct_hex = cookie[32:]

actual = b"user/pass:testxx"

# Probar admin (5 chars) + diferententes 6tos caracteres
# En SQL, el query sería: WHERE username='admin[char]' 

# Pero mejor: ¿qué tal "admins" (plural)?
alternatives = [
    b"user/pass:admins",  # admin + s
]

for target in alternatives:
    iv_test = bytearray(binascii.unhexlify(iv_hex))
    
    for i in range(16):
        iv_test[i] = iv_test[i] ^ actual[i] ^ target[i]
    
    cookie_test = binascii.hexlify(iv_test).decode() + ct_hex
    
    cmd = f'timeout 8s curl -s https://websec.fr/level21/index.php --cookie "session={cookie_test}"'
    result = subprocess.run(cmd, shell=True, capture_output=True, text=True)
    
    # Buscar flag
    flag_match = re.search(r'WEBSEC\{[^}]+\}', result.stdout)
    if flag_match:
        print(f"FLAG: {flag_match.group(0)}")
        break
    
    # "admins" contiene a,d,m,i que están bloqueadas, así que no puede registrarse
    # Pero podría existir en la DB por otro medio
    
    if "Hello admin" in result.stdout:
        print("Hello admin encontrado!")
        break
    else:
        print(f"admins: Wrong")

