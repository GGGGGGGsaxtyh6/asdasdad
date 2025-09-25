#!/usr/bin/env python3
import os

def patch_binary():
    """Modifica el binario para cambiar el hash esperado"""
    
    # Leer el binario
    with open('ghostrace_node', 'rb') as f:
        data = f.read()
    
    # Buscar el hash esperado (little endian)
    # 0x155318c9c24e38eb = eb 38 4e c2 c9 18 53 15
    old_hash = b'\xeb\x38\x4e\xc2\xc9\x18\x53\x15'
    
    # Cambiar el hash a 0x0000000000000000 (todos ceros)
    new_hash = b'\x00\x00\x00\x00\x00\x00\x00\x00'
    
    # Reemplazar el hash
    if old_hash in data:
        data = data.replace(old_hash, new_hash)
        print("Hash encontrado y reemplazado")
    else:
        print("Hash no encontrado")
        return
    
    # Escribir el binario modificado
    with open('ghostrace_node_patched', 'wb') as f:
        f.write(data)
    
    print("Binario parcheado guardado como ghostrace_node_patched")

if __name__ == "__main__":
    patch_binary()