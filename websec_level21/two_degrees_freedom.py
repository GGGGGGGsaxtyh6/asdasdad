#!/usr/bin/env python3
import binascii

# Username de 20 chars
USER20 = "zyxwvutsrqpolkjhgfec"

# Cookie truncada (esto la obtengo del login)
cookie = "74c2bfa851c5b243cc8c34538adc200bbc82a7a31f32b3c9d60058f66b2fdd3b7002e3887906b49c4be54b826bf560f6"

iv_hex = cookie[:32]
ct_hex = cookie[32:]

iv = bytearray(binascii.unhexlify(iv_hex))
ct = bytearray(binascii.unhexlify(ct_hex))

# Plaintext actual:
# Block 0: "user/pass:zyxwv"
# Block 1: "utsrqpolkjhgfec/"

# Quiero:
# Block 0: "user/pass:zyxwv" (MANTENER IGUAL - esto es crítico)
# Block 1: "' OR username='admin'--/" (SQL injection)

# Pero block 1 target es muy largo...
# "' OR username='admin'--/" = 24 chars, necesito solo 16

# Payload más corto:
# Block 1: "x'OR+'1'='1'--+/"  (16 chars)

actual_b0 = b"user/pass:zyxwv"
target_b0 = b"user/pass:zyxwv"  # MANTENER IGUAL

actual_b1 = b"utsrqpolkjhgfec/"
target_b1 = b"x'OR+'1'='1'--+/"

# Para mantener block 0 igual necesito:
# iv_new XOR ct0_new = iv_old XOR ct0_old
# iv_new = iv_old XOR ct0_old XOR ct0_new

# Para cambiar block 1 necesito:
# ct0_new = ct0_old XOR actual_b1 XOR target_b1

# Calculemos ct0_new primero
for i in range(16):
    ct[i] = ct[i] ^ actual_b1[i] ^ target_b1[i]

# Ahora calculemos iv_new para que block 0 siga igual
# Necesito: iv_new = algo que haga que P0 = target_b0

# P0 = D(K, C0_new) XOR IV_new
# Queremos: P0 = target_b0
# Entonces: IV_new = D(K, C0_new) XOR target_b0

# Pero no conozco D(K, C0_new)...

# SIN EMBARGO, sé que originalmente:
# P0_original = D(K, C0_original) XOR IV_original

# Si C0 cambia de C0_original a C0_new, entonces D(K, ...) también cambia
# y no puedo calcularlo sin la key.

# CONCLUSIÓN: No puedo hacer esto sin conocer K.

print("❌ No puedo preservar block 0 y modificar block 1 simultáneamente")
print("   sin conocer la key AES.")
print("")
print("Necesito buscar una solución completamente diferente...")
