#!/usr/bin/env python3
from pwn import *

context.log_level = 'info'

# Bytes de la función main() que se usan para el XOR
main_bytes = bytes([
    0x55, 0x48, 0x89, 0xe5, 0x48, 0x83, 0xec, 0x50,
    0x64, 0x48, 0x8b, 0x04, 0x25, 0x28, 0x00, 0x00,
    0x00, 0x48, 0x89, 0x45, 0xf8, 0x31, 0xc0, 0xe8,
    0x24, 0xfe, 0xff, 0xff, 0x48, 0x8d, 0x45, 0xc0
])

# Target bytes que auth() espera después del XOR
target_bytes = bytes([
    0x11, 0xde, 0xcf, 0x10, 0xdf, 0x75, 0xbb, 0xa5,
    0x43, 0x1e, 0x9d, 0xc2, 0xe3, 0xbf, 0xf5, 0xd6,
    0x96, 0x7f, 0xbe, 0xb0, 0xbf, 0xb7, 0x96, 0x1d,
    0xa8, 0xbb, 0x0a, 0xd9, 0xbf, 0xc9, 0x0d, 0xff
])

# Calcular el input necesario
# La función auth() hace: swapped_byte = (byte >> 4) | (byte << 4)
# Luego: result = swapped_byte XOR main_bytes[i]
# Queremos que result == target_bytes[i]
# Entonces: swapped_byte = target_bytes[i] XOR main_bytes[i]
# Y necesitamos invertir el swap: byte = (swapped >> 4) | (swapped << 4)

def swap_nibbles(byte):
    """Intercambia los nibbles de un byte"""
    return ((byte >> 4) | (byte << 4)) & 0xff

input_bytes = bytearray()
for i in range(32):
    # Calcular qué byte swapped necesitamos
    swapped = target_bytes[i] ^ main_bytes[i]
    # Invertir el swap para obtener el byte original
    original = swap_nibbles(swapped)
    input_bytes.append(original)

print(f"[*] Input calculado ({len(input_bytes)} bytes):")
print(hexdump(bytes(input_bytes)))

# El buffer está organizado así:
# 0x00-0x1f (32 bytes): name (pero scanf usa %32s)
# 0x20-0x37 (24 bytes): nationality (scanf usa %24s)
# 0x38-0x3f (8 bytes): age

# Preparar los valores
name = input_bytes[0:32]
nationality = input_bytes[32:56] if len(input_bytes) > 32 else b''

print(f"[*] Name: {name.hex()}")
print(f"[*] Nationality: {nationality.hex() if nationality else 'N/A'}")

def exploit(target='local'):
    if target == 'local':
        p = process('./image/challenge/challenge')
    else:
        p = remote('svc.pwnable.xyz', 30031)
    
    # Esperar el menú
    p.recvuntil(b'> ')
    
    # Opción 1: Change name (32 bytes)
    p.sendline(b'1')
    p.recvuntil(b': ')
    p.send(name)  # No usar sendline para evitar el \n extra
    p.sendline(b'')
    
    # Esperar menú
    p.recvuntil(b'> ')
    
    # Opción 2: Change nationality (solo si hay más bytes)
    if len(input_bytes) > 32:
        # Necesitamos llenar desde 0x20 en adelante, pero solo tenemos 32 bytes totales
        # No necesitamos esta opción en este caso
        pass
    
    # Opción 4: Get shell
    p.sendline(b'4')
    
    # Recibir la respuesta
    try:
        response = p.recvall(timeout=2)
        print(response.decode())
    except:
        pass
    
    p.close()

if __name__ == '__main__':
    import sys
    target = sys.argv[1] if len(sys.argv) > 1 else 'local'
    exploit(target)