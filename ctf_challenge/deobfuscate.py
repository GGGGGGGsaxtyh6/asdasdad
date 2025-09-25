#!/usr/bin/env python3

import struct

def deobfuscate_data():
    """Intenta deobfuscar los datos en 0x31e0"""
    
    # Datos ofuscados en 0x31e0 (24 bytes)
    obfuscated = [
        0x6e, 0x41, 0xe4, 0xda, 0xa3, 0x5c, 0x51, 0x78,
        0x59, 0x12, 0x93, 0xa5, 0x3a, 0x1c, 0x75, 0xb3,
        0xba, 0x10, 0x2f, 0x4a, 0x11, 0x15, 0x83, 0xbd
    ]
    
    print("=== ANÁLISIS DE DATOS OFUSCADOS ===")
    print(f"Datos originales: {[hex(x) for x in obfuscated]}")
    print()
    
    # Intentar diferentes transformaciones
    transformations = [
        ("XOR con 0x42", [x ^ 0x42 for x in obfuscated]),
        ("XOR con 0x13", [x ^ 0x13 for x in obfuscated]),
        ("XOR con 0x37", [x ^ 0x37 for x in obfuscated]),
        ("XOR con 0x69", [x ^ 0x69 for x in obfuscated]),
        ("XOR con 0x88", [x ^ 0x88 for x in obfuscated]),
        ("XOR con 0xAA", [x ^ 0xAA for x in obfuscated]),
        ("XOR con 0xCC", [x ^ 0xCC for x in obfuscated]),
        ("XOR con 0xFF", [x ^ 0xFF for x in obfuscated]),
        ("Suma 0x20", [(x + 0x20) & 0xFF for x in obfuscated]),
        ("Resta 0x20", [(x - 0x20) & 0xFF for x in obfuscated]),
        ("Rotación izquierda 1", [((x << 1) | (x >> 7)) & 0xFF for x in obfuscated]),
        ("Rotación derecha 1", [((x >> 1) | (x << 7)) & 0xFF for x in obfuscated]),
    ]
    
    for name, transformed in transformations:
        # Convertir a string si es posible
        try:
            # Intentar como ASCII
            ascii_str = ''.join([chr(x) if 32 <= x <= 126 else '.' for x in transformed])
            print(f"{name:20}: {ascii_str}")
            
            # Si parece texto legible, mostrarlo
            if any(32 <= x <= 126 for x in transformed[:6]):
                print(f"  Bytes: {[hex(x) for x in transformed[:6]]}")
                print()
        except:
            pass

def analyze_handshake_logic():
    """Analiza la lógica del handshake"""
    
    print("=== LÓGICA DEL HANDSHAKE ===")
    print("1. El programa espera exactamente 6 caracteres")
    print("2. Compara con datos ofuscados en 0x31e0 (24 bytes)")
    print("3. Si la comparación falla, muestra 'Handshake rejected.'")
    print("4. Si es exitosa, muestra 'Handshake synchronized.'")
    print()
    
    # Buscar patrones en el código
    print("=== PATRONES ENCONTRADOS ===")
    print("- Comparación de longitud: cmp $0x6,%rsi")
    print("- Comparación de datos: memcmp con 24 bytes")
    print("- Los datos están ofuscados con transformaciones")
    print()

if __name__ == "__main__":
    deobfuscate_data()
    print()
    analyze_handshake_logic()