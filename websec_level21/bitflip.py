# CBC Bit Flipping para cambiar el plaintext

# Cookie actual desencripta a algo como:
# "user/pass:bcefgh/cc03e747a6afbbcbf8be7668acfebee5"  (MD5 de test123)

# Queremos cambiar la parte del password a un payload SQL
# Target: "user/pass:bcefgh/' OR username='admin'-- "

original_pass = "cc03e747a6afbbcbf8be7668acfebee5"
target_pass = "' OR username='admin'-- "

print(f"Original pass: {original_pass}")
print(f"Target pass:   {target_pass}")
print(f"Original len: {len(original_pass)}")
print(f"Target len:   {len(target_pass)}")

# El password empieza en la posición después de "user/pass:bcefgh/"
prefix = "user/pass:bcefgh/"
print(f"\nPrefix len: {len(prefix)}")

# En CBC, para modificar el plaintext del bloque N, necesitamos
# modificar el ciphertext del bloque N-1 (o el IV si es el bloque 0)

# AES block size = 16 bytes
# Veamos en qué bloque está el password

print(f"\nBlock 0 (bytes 0-15):  '{prefix[:16]}'")
if len(prefix) > 16:
    print(f"Block 1 (bytes 16-31): '{prefix[16:32]}'")

# El prefix "user/pass:bcefgh/" tiene 18 caracteres
# Bloque 0: "user/pass:bcefgh" (16 bytes)
# Bloque 1 empieza con: "h/"  seguido del password

# Para cambiar "h/" + inicio_password, necesito modificar el IV
# Para cambiar el resto del password en bloque 1, necesito modificar bloque 0 del ciphertext

print("\nPlan de ataque:")
print("1. El password empieza en byte 18 (bloque 1, posición 2)")
print("2. Necesitamos modificar el byte 2-3 del IV para cambiar 'h/'")
print("3. Necesitamos modificar bytes del ciphertext bloque 0 para el resto")

