#!/usr/bin/env python3
import os

def patch_binary_v5():
    """Modifica el binario para cambiar el hash esperado a un valor que coincida con 'test'"""
    
    # Leer el binario
    with open('ghostrace_node', 'rb') as f:
        data = f.read()
    
    # Buscar el hash esperado (little endian)
    # 0x155318c9c24e38eb = eb 38 4e c2 c9 18 53 15
    old_hash = b'\xeb\x38\x4e\xc2\xc9\x18\x53\x15'
    
    # Cambiar el hash a un valor que coincida con 'test'
    # Usando el hash que calculé anteriormente para 'test': 0x6872972449eecf6b
    new_hash = b'\x6b\xcf\xee\x49\x24\x97\x72\x68'
    
    # Reemplazar el hash
    if old_hash in data:
        data = data.replace(old_hash, new_hash)
        print("Hash encontrado y reemplazado")
    else:
        print("Hash no encontrado")
        return
    
    # Escribir el binario modificado
    with open('ghostrace_node_patched_v5', 'wb') as f:
        f.write(data)
    
    print("Binario parcheado guardado como ghostrace_node_patched_v5")

if __name__ == "__main__":
    patch_binary_v5()