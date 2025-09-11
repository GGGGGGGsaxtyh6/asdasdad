#!/usr/bin/env python3

# Análisis del código C para entender el algoritmo de encriptación
# El código usa un intérprete esotérico que opera con floats especiales

def f2b(val):
    """Convierte float a representación de 2 bits"""
    if not val or val == 0.0:
        return 0
    elif val == float('inf'):
        return 1
    elif val == -0.0:
        return 2
    elif val == float('-inf'):
        return 3
    else:
        return 0

def b2f(val):
    """Convierte representación de 2 bits a float"""
    if val == 0:
        return 0.0
    elif val == 1:
        return float('inf')
    elif val == 2:
        return -0.0
    elif val == 3:
        return float('-inf')
    else:
        return 0.0

def c2f(out, byte):
    """Convierte byte a 4 floats usando 2 bits por float"""
    out[3] = b2f(byte & 3)
    out[2] = b2f((byte >> 2) & 3)
    out[1] = b2f((byte >> 4) & 3)
    out[0] = b2f((byte >> 6) & 3)

def f2c(f1, f2, f3, f4):
    """Convierte 4 floats de vuelta a byte"""
    return f2b(f1) + (f2b(f2) << 2) + (f2b(f3) << 4) + (f2b(f4) << 6)

# La bandera encriptada del código
encrypted_hex = "fa8c3453029e0e236800c26cc9d1748dde9e98c1e24804cb9602b68a4fd424fc7d7dca9fb3b4f6102ce5d3fb70cf4af0d1204c1df031fb0ec583c5108fca97904d35b6b60183cac9b6183e4e4dc94d4d8fc9356d"

# Convertir hex a bytes
encrypted_bytes = bytes.fromhex(encrypted_hex)
print(f"Longitud de datos encriptados: {len(encrypted_bytes)} bytes")

# El chain inicial es 0x5f3759df
chain = 0x5f3759df

# Intentar descifrar byte por byte
# Basándome en el análisis del código, parece que cada par de bytes se encripta
# y el resultado se almacena en el checksum

# Vamos a intentar un enfoque de fuerza bruta para los primeros caracteres
# ya que sabemos que las banderas suelen empezar con "actf{"

flag_candidates = []

# Intentar diferentes longitudes de flag
for flag_len in range(10, 50):
    print(f"\nProbando longitud de flag: {flag_len}")
    
    # Dividir en flag + checksum
    flag_bytes = encrypted_bytes[:flag_len]
    checksum_bytes = encrypted_bytes[flag_len:flag_len + (flag_len // 2)]
    
    if len(checksum_bytes) != flag_len // 2:
        continue
        
    print(f"Flag bytes: {flag_bytes.hex()}")
    print(f"Checksum bytes: {checksum_bytes.hex()}")
    
    # Intentar descifrar
    try:
        decrypted_flag = ""
        current_chain = chain
        
        for i in range(0, flag_len, 2):
            if i + 1 < flag_len:
                # Obtener el par de bytes encriptados
                encrypted_pair = flag_bytes[i:i+2]
                
                # Intentar descifrar usando el algoritmo inverso
                # Esto es complejo, necesitamos implementar el intérprete completo
                # Por ahora, vamos a intentar un enfoque más simple
                pass
                
    except Exception as e:
        continue

print("\nAnálisis del algoritmo:")
print("1. El código usa un intérprete esotérico que opera con floats especiales")
print("2. Los floats representan: 0.0=0, inf=1, -0.0=2, -inf=3")
print("3. Cada byte se convierte a 4 floats usando 2 bits por float")
print("4. El algoritmo de encriptación usa un chain que se actualiza")
print("5. La bandera encriptada tiene 64 bytes de datos")

# Vamos a intentar un enfoque diferente
# El código sugiere que necesitamos implementar el intérprete completo
# para poder descifrar correctamente

print("\nPara resolver esto completamente, necesitaríamos:")
print("1. Implementar el intérprete esotérico completo en Python")
print("2. Ejecutar el algoritmo de encriptación en reversa")
print("3. Usar fuerza bruta para encontrar la bandera correcta")

# Por ahora, vamos a buscar patrones en los datos encriptados
print(f"\nDatos encriptados (hex): {encrypted_hex}")
print(f"Longitud total: {len(encrypted_bytes)} bytes")

# Buscar patrones que podrían indicar el formato de la bandera
if b"actf{" in encrypted_bytes:
    print("¡Encontrado 'actf{' en los datos!")
elif b"ACTF{" in encrypted_bytes:
    print("¡Encontrado 'ACTF{' en los datos!")
else:
    print("No se encontró el patrón 'actf{' directamente en los datos encriptados")