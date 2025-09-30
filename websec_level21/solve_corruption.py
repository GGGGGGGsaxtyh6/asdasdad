#!/usr/bin/env python3
import binascii

# Si modifico ciphertext block 0 para cambiar block 1,
# el block 0 plaintext se corrompe de forma impredecible.

# PERO, puedo calcular QUÉ modificación en CT block 0 necesito
# para que block 1 tenga mi payload

# Y LUEGO, puedo calcular QUÉ IV necesito para que block 0 
# SIGA siendo "user/pass:..." a pesar de la corrupción del CT

# Esto se llama "prefix-preserving bit flipping"

# Block 0 plaintext: "user/pass:zyxwv"
# Block 1 plaintext: "utsrqpolkjhgfec/"

# Paso 1: Modificar CT block 0 para que block 1 sea nuestro payload
# Paso 2: Modificar IV para que block 0 SIGA siendo correcto

# Pero esto require conocer el plaintext exacto Y la key AES...
# Lo cual no tengo.

# WAIT - ¡no necesito la key!

# En CBC:
# P0 = D(K, C0) XOR IV
# P1 = D(K, C1) XOR C0

# Si modifico C0 a C0', entonces:
# P0' = D(K, C0') XOR IV  (corrupto, impredecible sin conocer K)
# P1' = D(K, C1) XOR C0' (controlable!)

# Para que P1' sea mi target, necesito:
# C0' = D(K, C1) XOR P1_target
# Pero D(K, C1) = P1_original XOR C0_original

# Entonces: C0' = P1_original XOR C0_original XOR P1_target

# ¡Esto es el bit flipping normal!

# Ahora, para arreglar P0:
# P0' = D(K, C0') XOR IV

# Quiero que P0' = P0_target = "user/pass:zyxwv"
# D(K, C0') = P0' XOR IV = P0_target XOR IV

# Pero D(K, C0') es desconocido...

# UNLESS... puedo calcularlo si sé P0_original!
# P0_original = D(K, C0_original) XOR IV_original
# D(K, C0_original) = P0_original XOR IV_original

# Pero D(K, C0') != D(K, C0_original) porque modifiqué C0

# Estoy en un bucle de nuevo. Sin conocer la key K, no puedo calcular
# las desencriptaciones intermedias.

print("Conclusión: Sin conocer la key AES, no puedo hacer")
print("prefix-preserving bit flipping de forma determinística.")
print("")
print("NECESITO OTRA TÉCNICA.")
