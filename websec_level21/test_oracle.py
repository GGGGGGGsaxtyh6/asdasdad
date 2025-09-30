#!/usr/bin/env python3
import binascii
import requests

# Usar la cookie del usuario user190487
cookie = "6b5ff360b37891144d1df525c59ace8008b4795bb372ffcc922f415b4e450f46f34b6a3c10e45d4a70f03f83fadc06be7f682d9abb5cac5e82e481f5e8ea8a0ba"

iv_hex = cookie[:32]
ct_hex = cookie[32:]

print("Probando si hay padding oracle...")
print("Modificando el último byte del último bloque y viendo las respuestas\n")

ct = bytearray(binascii.unhexlify(ct_hex))
original_last = ct[-1]

responses = {}

for val in range(0, 256, 32):  # Probar cada 32 valores para ir más rápido
    ct_test = bytearray(ct)
    ct_test[-1] = val
    
    cookie_test = iv_hex + binascii.hexlify(ct_test).decode()
    
    try:
        r = requests.get(
            "https://websec.fr/level21/index.php",
            cookies={"session": cookie_test},
            timeout=5
        )
        
        response_type = ""
        if "corrupted" in r.text:
            response_type = "corrupted"
        elif "Wrong login" in r.text:
            response_type = "wrong_login"
        elif "admin" in r.text:
            response_type = "admin!"
        else:
            response_type = "other"
        
        if response_type not in responses:
            responses[response_type] = []
        responses[response_type].append(val)
        
        print(f"Byte {val:02x}: {response_type}")
        
    except Exception as e:
        print(f"Byte {val:02x}: error - {e}")

print(f"\nResumen:")
for resp_type, values in responses.items():
    print(f"  {resp_type}: {len(values)} casos")

if len(responses) > 1:
    print(f"\n✓ HAY DIFERENCIAS - Posible padding oracle")
else:
    print(f"\n✗ No hay diferencias obvias")
