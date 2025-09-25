#!/usr/bin/env python3

import subprocess
import sys
import os

def patch_ptrace():
    """Parchea el binario para bypassear ptrace"""
    # Leer el binario
    with open('NeonRelay/neon_relay', 'rb') as f:
        data = bytearray(f.read())
    
    # Buscar la llamada a ptrace (e8 6b fe ff ff)
    # Esta es la llamada a ptrace en 0x13a0
    ptrace_call = b'\xe8\x6b\xfe\xff\xff'
    
    # Reemplazar con nops
    nops = b'\x90\x90\x90\x90\x90'
    
    if ptrace_call in data:
        data = data.replace(ptrace_call, nops)
        print("Parcheado ptrace exitosamente")
        
        # Guardar binario parcheado
        with open('neon_relay_patched', 'wb') as f:
            f.write(data)
        
        os.chmod('neon_relay_patched', 0o755)
        return True
    else:
        print("No se encontró la llamada a ptrace")
        return False

if __name__ == "__main__":
    if patch_ptrace():
        print("Binario parcheado creado: neon_relay_patched")
    else:
        print("Error al parchear el binario")