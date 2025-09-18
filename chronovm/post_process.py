#!/usr/bin/env python3
"""
Post-procesamiento de secciones para ChronoVM
Aplica modificaciones finales al binario compilado
"""

import struct
import sys
import os
import random

def read_binary(filename):
    """Lee el binario completo"""
    with open(filename, 'rb') as f:
        return bytearray(f.read())

def write_binary(filename, data):
    """Escribe el binario modificado"""
    with open(filename, 'wb') as f:
        f.write(data)

def modify_elf_header(data):
    """Modifica el header ELF para confundir análisis"""
    print("   Modificando header ELF...")
    
    # Cambiar algunos campos del header para confundir herramientas
    if len(data) >= 16:
        # Cambiar e_ident[EI_OSABI] (offset 7)
        data[7] = random.randint(0, 255)
        
        # Cambiar e_ident[EI_ABIVERSION] (offset 8)
        data[8] = random.randint(0, 255)
    
    # Modificar algunos campos del header program
    if len(data) >= 32:
        # Cambiar e_flags (offset 24-27)
        flags = struct.unpack('<I', data[24:28])[0]
        flags ^= 0x12345678
        data[24:28] = struct.pack('<I', flags)

def add_fake_sections(data):
    """Añade secciones falsas para confundir análisis"""
    print("   Añadiendo secciones falsas...")
    
    # Buscar el final del binario
    end_pos = len(data) - 1
    
    # Añadir datos falsos al final
    fake_data = b'\x00' * 1024
    fake_data += b'FAKE_SECTION_1\x00'
    fake_data += b'FAKE_SECTION_2\x00'
    fake_data += b'FAKE_SECTION_3\x00'
    
    # Insertar datos falsos
    data.extend(fake_data)

def obfuscate_strings(data):
    """Ofusca strings adicionales"""
    print("   Ofuscando strings adicionales...")
    
    # Strings adicionales a ofuscar
    additional_strings = [
        b"GCC:",
        b"GNU",
        b"glibc",
        b"libc",
        b"ld-linux",
        b"__libc_start_main",
        b"__gmon_start__",
        b"libgcc_s.so.1",
        b"libc.so.6"
    ]
    
    for string in additional_strings:
        # Buscar y reemplazar con datos aleatorios
        pos = 0
        while True:
            pos = data.find(string, pos)
            if pos == -1:
                break
            
            # Reemplazar con bytes aleatorios
            replacement = bytes(random.randint(0, 255) for _ in range(len(string)))
            data[pos:pos+len(string)] = replacement
            pos += len(string)

def add_anti_disassembly(data):
    """Añade técnicas anti-desensamblaje"""
    print("   Añadiendo técnicas anti-desensamblaje...")
    
    # Insertar instrucciones que confunden desensambladores
    anti_disasm = [
        b'\x0f\x0b',  # UD2
        b'\xcc',      # INT3
        b'\xcd\x03',  # INT 3
        b'\xf4',      # HLT
    ]
    
    # Insertar en lugares aleatorios
    for i in range(0, len(data) - 2, 32):
        if random.random() < 0.05:  # 5% de probabilidad
            instruction = random.choice(anti_disasm)
            if i + len(instruction) < len(data):
                data[i:i+len(instruction)] = instruction

def modify_imports(data):
    """Modifica las importaciones para confundir análisis"""
    print("   Modificando importaciones...")
    
    # Buscar y modificar strings de importación
    import_strings = [
        b"ptrace",
        b"prctl",
        b"time",
        b"printf",
        b"fgets",
        b"strlen",
        b"strcmp",
        b"malloc",
        b"free",
        b"exit"
    ]
    
    for string in import_strings:
        pos = 0
        while True:
            pos = data.find(string, pos)
            if pos == -1:
                break
            
            # Reemplazar con versión ofuscada
            obfuscated = bytes(b ^ 0xAA for b in string)
            data[pos:pos+len(string)] = obfuscated
            pos += len(string)

def add_checksum_validation(data):
    """Añade validación de checksum al binario"""
    print("   Añadiendo validación de checksum...")
    
    # Calcular checksum actual
    checksum = 0
    for byte in data:
        checksum = (checksum << 1) ^ byte
    checksum &= 0xFFFFFFFF
    
    # Buscar lugar para insertar checksum
    # Insertar al final del binario
    data.extend(b'CHKSUM:')
    data.extend(struct.pack('<I', checksum))
    
    print(f"   Checksum calculado: 0x{checksum:08X}")

def add_metadata(data):
    """Añade metadata falsa al binario"""
    print("   Añadiendo metadata falsa...")
    
    # Añadir metadata falsa al final
    metadata = b'\x00' * 64
    metadata += b'ChronoVM v2.0 - TimeLock Virtual Machine\x00'
    metadata += b'Compiled with GCC 9.4.0\x00'
    metadata += b'Build date: 2024-01-15\x00'
    metadata += b'Author: HTB Team\x00'
    metadata += b'License: Proprietary\x00'
    
    data.extend(metadata)

def main():
    if len(sys.argv) != 2:
        print("Uso: python3 post_process.py <binario>")
        sys.exit(1)
    
    filename = sys.argv[1]
    
    if not os.path.exists(filename):
        print(f"Error: Archivo '{filename}' no encontrado")
        sys.exit(1)
    
    print(f"🔧 Aplicando post-procesamiento a '{filename}'...")
    
    # Leer binario
    data = read_binary(filename)
    original_size = len(data)
    print(f"   Tamaño original: {original_size} bytes")
    
    # Aplicar modificaciones
    modify_elf_header(data)
    add_fake_sections(data)
    obfuscate_strings(data)
    add_anti_disassembly(data)
    modify_imports(data)
    add_checksum_validation(data)
    add_metadata(data)
    
    # Escribir binario modificado
    write_binary(filename, data)
    
    print(f"✅ Post-procesamiento completado!")
    print(f"   Tamaño final: {len(data)} bytes")
    print(f"   Cambio: {len(data) - original_size:+d} bytes")
    print(f"   Archivo modificado: {filename}")

if __name__ == "__main__":
    main()