#!/usr/bin/env python3
"""
Script para parchear el binario mindmaze y bypass todas las protecciones
"""

import struct

def patch_binary():
    print("Patching mindmaze binary...")
    
    # Leer el binario
    with open('mindmaze', 'rb') as f:
        data = bytearray(f.read())
    
    print(f"Binary size: {len(data)} bytes")
    
    patches = 0
    
    # 1. Parchear llamadas a ptrace (syscall 0x65)
    for i in range(len(data) - 4):
        if data[i:i+4] == b'\x65\x00\x00\x00':
            # Reemplazar con mov eax, 0; ret
            data[i:i+4] = b'\xb8\x00\x00\x00\x00\xc3'
            patches += 1
    
    # 2. Parchear verificaciones de getppid
    for i in range(len(data) - 8):
        if data[i:i+8] == b'\x6e\x00\x00\x00\x83\xf8\x01\x74':
            # Reemplazar con mov eax, 2; ret (no es init)
            data[i:i+8] = b'\xb8\x02\x00\x00\x00\xc3\x90\x90'
            patches += 1
    
    # 3. Parchear verificaciones de LD_PRELOAD
    for i in range(len(data) - 12):
        if data[i:i+12] == b'\x48\x8b\x05\x00\x00\x00\x00\x48\x85\xc0\x74':
            # Reemplazar con xor eax, eax; ret (NULL)
            data[i:i+12] = b'\x31\xc0\xc3\x90\x90\x90\x90\x90\x90\x90\x90'
            patches += 1
    
    # 4. Parchear verificaciones de timing
    for i in range(len(data) - 16):
        if data[i:i+16] == b'\xe8\x00\x00\x00\x00\x48\x89\xc7\xe8\x00\x00\x00\x00\x48\x89\xc6':
            # Reemplazar con valores fijos
            data[i:i+16] = b'\xb8\x00\x00\x00\x00\x90\x90\x90\xb8\x00\x00\x00\x00\x90\x90\x90'
            patches += 1
    
    # 5. Parchear verificaciones de integridad
    for i in range(len(data) - 20):
        if data[i:i+20] == b'\xe8\x00\x00\x00\x00\x48\x89\xc7\xe8\x00\x00\x00\x00\x48\x89\xc6\xe8\x00\x00\x00\x00':
            # Reemplazar con valores fijos
            data[i:i+20] = b'\xb8\x00\x00\x00\x00\x90\x90\x90\xb8\x00\x00\x00\x00\x90\x90\x90\xb8\x00\x00\x00'
            patches += 1
    
    # 6. Buscar y parchear la función de verificación de debugger
    # Buscar el patrón de la función check_debugger
    for i in range(len(data) - 50):
        # Buscar secuencia típica de verificación
        if (data[i] == 0x55 and  # push rbp
            data[i+1] == 0x48 and data[i+2] == 0x89 and data[i+3] == 0xe5):  # mov rbp, rsp
            # Esta podría ser una función, verificar si tiene verificaciones
            for j in range(i, min(i+100, len(data)-10)):
                if (data[j:j+4] == b'\x65\x00\x00\x00' or  # ptrace
                    data[j:j+4] == b'\x6e\x00\x00\x00' or  # getppid
                    data[j:j+8] == b'\x48\x8b\x05\x00\x00\x00\x00'):  # getenv
                    # Parchear toda la función para que retorne 0
                    data[i:i+50] = b'\x55\x48\x89\xe5\xb8\x00\x00\x00\x00\x5d\xc3' + b'\x90' * 39
                    patches += 1
                    break
    
    print(f"Applied {patches} patches")
    
    # Guardar binario parcheado
    with open('mindmaze_patched', 'wb') as f:
        f.write(data)
    
    print("Patched binary saved as 'mindmaze_patched'")
    return patches

if __name__ == "__main__":
    patch_binary()