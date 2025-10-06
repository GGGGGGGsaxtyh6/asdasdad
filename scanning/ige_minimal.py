#!/usr/bin/env python3
import requests
import json
from binascii import unhexlify
import sys

url_base = "https://aes.cryptohack.org/paper_plane"

with open("/workspace/scanning/encrypted_flag.txt", "r") as f:
    encrypted_data = json.load(f)

ct = unhexlify(encrypted_data["ciphertext"])
m0 = unhexlify(encrypted_data["m0"])
c0 = unhexlify(encrypted_data["c0"])

blocks = [ct[i:i+16] for i in range(0, len(ct), 16)]

def check(ct_hex, m0_hex, c0_hex):
    try:
        r = requests.get(f"{url_base}/send_msg/{ct_hex}/{m0_hex}/{c0_hex}/", timeout=2)
        return "error" not in r.json() or "Can't decrypt" not in r.json().get("error", "")
    except:
        return False

# Optimización extrema: orden de búsqueda inteligente
def search_order():
    # Padding values primero (muy probable en últimos bytes)
    order = list(range(1, 17))
    # Luego caracteres comunes en crypto flags
    order += [ord(c) for c in 'abcdefghijklmnopqrstuvwxyz_0123456789{}']
    order += [ord(c) for c in 'ABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()']
    # Resto
    order += [i for i in range(256) if i not in order]
    return order

# BLOQUE 0
print("B0:", end='', flush=True)
b0 = bytearray(16)
for pv in range(1, 17):
    bp = 16 - pv
    base = bytearray(c0)
    for i in range(bp + 1, 16):
        base[i] = c0[i] ^ b0[i] ^ pv
    
    for g in search_order():
        base[bp] = c0[bp] ^ g ^ pv
        if check(blocks[0].hex(), m0.hex(), base.hex()):
            b0[bp] = g
            sys.stdout.write(chr(g) if 32 <= g < 127 else f'\\x{g:02x}')
            sys.stdout.flush()
            break

print(f" [{b0.hex()}]")

# BLOQUE 1
print("B1:", end='', flush=True)
b1 = bytearray(16)
for pv in range(1, 17):
    bp = 16 - pv
    base = bytearray(blocks[0])
    for i in range(bp + 1, 16):
        base[i] = blocks[0][i] ^ b1[i] ^ pv
    
    for g in search_order():
        base[bp] = blocks[0][bp] ^ g ^ pv
        if check(blocks[1].hex(), b0.hex(), base.hex()):
            b1[bp] = g
            sys.stdout.write(chr(g) if 32 <= g < 127 else f'\\x{g:02x}')
            sys.stdout.flush()
            break

print(f" [{b1.hex()}]")

full = bytes(b0 + b1)
print(f"\nFull: {full}")

# Quitar padding
pl = full[-1]
if 1 <= pl <= 16:
    flag = full[:-pl]
    print(f"FLAG: {flag.decode('utf-8', errors='replace')}")
    with open("/workspace/scanning/flag.txt", "w") as f:
        f.write(flag.decode('utf-8', errors='replace'))
else:
    print(f"Sin padding válido: {full}")
