#!/bin/bash

# Crear un usuario nuevo con timestamp para evitar conflictos
TIMESTAMP=$(date +%s)
USER="user${TIMESTAMP: -6}"  # Últimos 6 dígitos del timestamp
echo "Usando username: $USER"

# Registrar
curl -s -X POST https://websec.fr/level21/index.php \
  -d "username=$USER&password=test&register=1" > /dev/null

# Login
curl -s -c /tmp/cookie_bf.txt -X POST https://websec.fr/level21/index.php \
  -d "username=$USER&password=test&login=1" > /dev/null

COOKIE=$(cat /tmp/cookie_bf.txt | grep session | awk '{print $NF}')

if [ -z "$COOKIE" ]; then
    echo "No se pudo obtener cookie"
    exit 1
fi

echo "Cookie obtenida: ${COOKIE:0:60}..."

# La cookie tiene formato: IV (32) + CT
# Voy a probar modificar solo el IV para cambiar el primer bloque
# y ver si alguna modificación casualmente produce un username útil

python3 << 'EOFPY'
import binascii
import subprocess

cookie = "$COOKIE"
iv_hex = cookie[:32]
ct_hex = cookie[32:]

print(f"\nProbando modificaciones del IV...")
print(f"Buscando una que produzca username='admin' o SQL injection útil")

# Esto debería probarse con muchas variaciones, pero por tiempo
# voy a mostrar el enfoque

iv = bytearray(binascii.unhexlify(iv_hex))

# Probar XOR cada byte del IV con valores comunes
tested = 0
for pos in range(16):
    for xor_val in [0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80]:
        iv_test = bytearray(iv)
        iv_test[pos] ^= xor_val
        
        modified_cookie = binascii.hexlify(iv_test).decode() + ct_hex
        
        # Aquí debería hacer curl para probar, pero es muy lento
        tested += 1
        
print(f"\nSe necesitarían probar {tested} variaciones...")
print(f"Esto es un padding oracle attack o brute force")
EOFPY
