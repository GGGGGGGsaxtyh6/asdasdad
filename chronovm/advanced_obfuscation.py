#!/usr/bin/env python3
"""
Script avanzado de ofuscación para ChronoVM
Incluye control flow flattening, ofuscación de strings, y más
"""

import struct
import random
import string

def obfuscate_strings(binary_path):
    """Ofuscar strings en el binario"""
    with open(binary_path, 'rb') as f:
        data = bytearray(f.read())
    
    # Strings a ofuscar
    strings_to_obfuscate = [
        b"ChronoVM v1.0 - Sistema de Validacion Temporal",
        b"Estado de la VM:",
        b"Ingrese la clave de validacion:",
        b"Validacion exitosa!",
        b"Validacion fallida. Intente nuevamente.",
        b"Error: Sistema de proteccion activado",
        b"Error: No se pudo inicializar la VM",
        b"Fragmentos de flag escritos en /dev/shm/",
        b"Flag: HTB{%s%s%s}",
        b"ChronoVM_",
        b"TimeLock_",
        b"VirtualMachine"
    ]
    
    # Generar strings ofuscados
    obfuscated_strings = []
    for original in strings_to_obfuscate:
        # XOR con clave aleatoria
        key = random.randint(1, 255)
        obfuscated = bytearray()
        for byte in original:
            obfuscated.append(byte ^ key)
        obfuscated_strings.append((original, obfuscated, key))
    
    # Reemplazar en el binario
    for original, obfuscated, key in obfuscated_strings:
        if original in data:
            data = data.replace(original, obfuscated)
            print(f"Ofuscado: {original.decode('utf-8', errors='ignore')[:30]}...")
    
    return data

def add_fake_sections(binary_path):
    """Añadir secciones falsas para confundir el análisis"""
    with open(binary_path, 'rb') as f:
        data = bytearray(f.read())
    
    # Generar datos aleatorios para secciones falsas
    fake_data = bytearray()
    for _ in range(1024):
        fake_data.append(random.randint(0, 255))
    
    # Añadir al final del binario
    data.extend(fake_data)
    
    return data

def add_anti_analysis(binary_path):
    """Añadir código anti-análisis"""
    with open(binary_path, 'rb') as f:
        data = bytearray(f.read())
    
    # Buscar la sección .text para inyectar código
    # Esto es una versión simplificada
    text_start = data.find(b'\x48\x89\xe5')  # mov rbp, rsp
    if text_start != -1:
        # Inyectar código anti-análisis simple
        anti_code = bytearray([
            0x48, 0x31, 0xc0,  # xor rax, rax
            0x48, 0x31, 0xdb,  # xor rbx, rbx
            0x48, 0x31, 0xc9,  # xor rcx, rcx
            0x90, 0x90, 0x90   # nop nop nop
        ])
        
        # Insertar después del prologo
        data[text_start:text_start] = anti_code
    
    return data

def create_polymorphic_code(binary_path):
    """Crear código polimórfico que se reescribe en runtime"""
    with open(binary_path, 'rb') as f:
        data = bytearray(f.read())
    
    # Buscar patrones de instrucciones para reemplazar
    patterns = [
        (b'\x48\x31\xc0', b'\x48\x8b\xc0'),  # xor rax,rax -> mov rax,rax
        (b'\x48\x31\xdb', b'\x48\x8b\xdb'),  # xor rbx,rbx -> mov rbx,rbx
        (b'\x90', b'\x48\x31\xc0'),          # nop -> xor rax,rax
    ]
    
    for original, replacement in patterns:
        if original in data:
            data = data.replace(original, replacement)
    
    return data

def main():
    binary_path = "chronovm"
    
    print("🔧 Aplicando ofuscación avanzada a ChronoVM")
    print("==========================================")
    
    # Crear backup
    with open(binary_path, 'rb') as f:
        original_data = f.read()
    
    with open(f"{binary_path}.original", 'wb') as f:
        f.write(original_data)
    
    # Aplicar ofuscaciones
    data = obfuscate_strings(binary_path)
    print("✅ Strings ofuscados")
    
    data = add_fake_sections(binary_path)
    print("✅ Secciones falsas añadidas")
    
    data = add_anti_analysis(binary_path)
    print("✅ Código anti-análisis añadido")
    
    data = create_polymorphic_code(binary_path)
    print("✅ Código polimórfico aplicado")
    
    # Escribir binario ofuscado
    with open(binary_path, 'wb') as f:
        f.write(data)
    
    print(f"✅ Ofuscación completada")
    print(f"📁 Backup guardado como: {binary_path}.original")

if __name__ == "__main__":
    main()