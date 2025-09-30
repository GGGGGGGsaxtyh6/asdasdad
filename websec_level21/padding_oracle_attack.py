#!/usr/bin/env python3
"""
Padding Oracle Attack implementation
"""
import binascii
import subprocess

def test_padding(cookie_hex):
    """Test si una cookie tiene padding válido"""
    cmd = f'timeout 6s curl -s https://websec.fr/level21/index.php --cookie "session={cookie_hex}"'
    result = subprocess.run(cmd, shell=True, capture_output=True, text=True)
    
    # Diferentes respuestas indican diferentes estados
    # Necesitamos encontrar qué indica padding válido vs inválido
    
    if "corrupted" in result.stdout:
        return "corrupted"
    elif "Wrong" in result.stdout:
        return "wrong"  # Padding válido, credenciales inválidas
    elif len(result.stdout) < 1400:
        return "empty"  # Respuesta cortada
    else:
        return "other"

# Cookie de testxx
cookie = "3d2d8bf19f0ce326f736a1443b952355b1fad1874477f658622708ae3c2b008c833e083ffd1bce3a3d4a8e0094382e53"

print("Implementando Padding Oracle Attack...")
print("Paso 1: Identificar el oracle\n")

# Test con cookie original
print(f"Cookie original: {test_padding(cookie)}")

# Test modificando el último byte del último bloque
iv_hex = cookie[:32]
ct_hex = cookie[32:]
ct = bytearray(binascii.unhexlify(ct_hex))

# El último bloque es el bloque 1 (solo tenemos 2 bloques con truncamiento)
# Modificar el último byte

results = {}
print("\nProbando últimos bytes para detectar padding oracle:")
for val in range(0, 256, 32):  # Sample
    ct_test = bytearray(ct)
    ct_test[-1] = val
    
    test_cookie = iv_hex + binascii.hexlify(ct_test).decode()
    result = test_padding(test_cookie)
    
    if result not in results:
        results[result] = []
    results[result].append(val)
    
    print(f"  0x{val:02x}: {result}")

print(f"\nTipos de respuesta: {list(results.keys())}")

if len(results) == 1:
    print("\n✗ No hay oracle - todas las respuestas son iguales")
    print("El rtrim() está ocultando los errores de padding")
else:
    print(f"\n✓ HAY DIFERENCIAS - Posible oracle")
    print(f"Necesitaría implementar el ataque completo para descifrar...")
    print(f"Esto tomaría cientos/miles de requests")

