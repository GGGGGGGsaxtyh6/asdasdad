#!/usr/bin/env python3
"""
Script de ofuscación avanzada para ChronoVM
Aplica técnicas de ofuscación adicionales al binario compilado
"""

import struct
import random
import sys
import os

def read_binary(filename):
    """Lee el binario completo"""
    with open(filename, 'rb') as f:
        return bytearray(f.read())

def write_binary(filename, data):
    """Escribe el binario modificado"""
    with open(filename, 'wb') as f:
        f.write(data)

def find_text_section(data):
    """Encuentra la sección .text"""
    # Buscar header ELF
    if data[:4] != b'\x7fELF':
        return None, None
    
    # Leer header ELF
    elf_class = data[4]
    if elf_class == 1:  # 32-bit
        shoff = struct.unpack('<I', data[32:36])[0]
        shentsize = struct.unpack('<H', data[46:48])[0]
        shnum = struct.unpack('<H', data[48:50])[0]
    else:  # 64-bit
        shoff = struct.unpack('<Q', data[40:48])[0]
        shentsize = struct.unpack('<H', data[58:60])[0]
        shnum = struct.unpack('<H', data[60:62])[0]
    
    # Buscar sección .text
    for i in range(shnum):
        offset = shoff + i * shentsize
        if elf_class == 1:
            sh_type = struct.unpack('<I', data[offset+4:offset+8])[0]
            sh_offset = struct.unpack('<I', data[offset+16:offset+20])[0]
            sh_size = struct.unpack('<I', data[offset+20:offset+24])[0]
            sh_name = struct.unpack('<I', data[offset:offset+4])[0]
        else:
            sh_type = struct.unpack('<I', data[offset+4:offset+8])[0]
            sh_offset = struct.unpack('<Q', data[offset+24:offset+32])[0]
            sh_size = struct.unpack('<Q', data[offset+32:offset+40])[0]
            sh_name = struct.unpack('<I', data[offset:offset+4])[0]
        
        if sh_type == 1:  # SHT_PROGBITS
            # Verificar si es .text (simplificado)
            if sh_offset < len(data) and sh_size > 0:
                return sh_offset, sh_size
    
    return None, None

def apply_control_flow_flattening(data, text_start, text_size):
    """Aplica control flow flattening a la sección .text"""
    print("   Aplicando control flow flattening...")
    
    # Crear trampas con instrucciones NOP y saltos
    nop_patterns = [
        b'\x90',  # NOP
        b'\x66\x90',  # NOP 16-bit
        b'\x0f\x1f\x00',  # NOP 3-byte
        b'\x0f\x1f\x40\x00',  # NOP 4-byte
        b'\x0f\x1f\x44\x00\x00',  # NOP 5-byte
    ]
    
    # Insertar trampas aleatorias
    for i in range(0, text_size, 16):
        if i + 4 < text_size:
            pattern = random.choice(nop_patterns)
            for j in range(len(pattern)):
                if i + j < text_size:
                    data[text_start + i + j] = pattern[j]

def apply_polymorphic_code(data, text_start, text_size):
    """Aplica código polimórfico"""
    print("   Aplicando código polimórfico...")
    
    # Patrones de instrucciones equivalentes
    equivalent_instructions = {
        b'\x90': [b'\x90', b'\x66\x90', b'\x0f\x1f\x00'],  # NOP
        b'\x31\xc0': [b'\x31\xc0', b'\x33\xc0', b'\x29\xc0'],  # XOR EAX, EAX
        b'\x40': [b'\x40', b'\xff\xc0', b'\x83\xc0\x01'],  # INC EAX
    }
    
    # Reemplazar instrucciones con equivalentes
    for i in range(text_start, text_start + text_size - 2):
        for original, equivalents in equivalent_instructions.items():
            if data[i:i+len(original)] == original:
                replacement = random.choice(equivalents)
                if i + len(replacement) <= text_start + text_size:
                    data[i:i+len(replacement)] = replacement
                    break

def apply_string_obfuscation(data):
    """Ofusca strings en el binario"""
    print("   Aplicando ofuscación de strings...")
    
    # Strings conocidos a ofuscar
    strings_to_obfuscate = [
        b"ChronoVM",
        b"TimeLock",
        b"VirtualMachine",
        b"HTB{",
        b"Validation successful",
        b"Invalid key",
        b"Debugger detected",
        b"integrity check failed"
    ]
    
    for string in strings_to_obfuscate:
        # Buscar y reemplazar con XOR
        key = random.randint(1, 255)
        obfuscated = bytes(b ^ key for b in string)
        
        # Buscar todas las ocurrencias
        pos = 0
        while True:
            pos = data.find(string, pos)
            if pos == -1:
                break
            
            # Reemplazar con versión ofuscada
            data[pos:pos+len(string)] = obfuscated
            pos += len(string)

def apply_section_mixing(data):
    """Mezcla secciones para confundir análisis"""
    print("   Aplicando mezcla de secciones...")
    
    # Buscar secciones de datos
    data_sections = []
    for i in range(0, len(data) - 4, 4):
        # Buscar patrones de datos
        if data[i:i+4] == b'\x00\x00\x00\x00':
            data_sections.append(i)
    
    # Intercambiar bloques de datos
    if len(data_sections) > 1:
        for i in range(0, len(data_sections) - 1, 2):
            if i + 1 < len(data_sections):
                start1 = data_sections[i]
                start2 = data_sections[i + 1]
                size = min(16, len(data) - start2)
                
                if start1 + size < len(data) and start2 + size < len(data):
                    # Intercambiar bloques
                    temp = data[start1:start1+size]
                    data[start1:start1+size] = data[start2:start2+size]
                    data[start2:start2+size] = temp

def apply_anti_analysis(data):
    """Aplica técnicas anti-análisis"""
    print("   Aplicando técnicas anti-análisis...")
    
    # Insertar instrucciones de trampa
    trap_instructions = [
        b'\xcc',  # INT3 (breakpoint)
        b'\xcd\x03',  # INT 3
        b'\x0f\x0b',  # UD2 (undefined instruction)
    ]
    
    # Insertar trampas en lugares aleatorios
    for i in range(0, len(data) - 1, 64):
        if random.random() < 0.1:  # 10% de probabilidad
            trap = random.choice(trap_instructions)
            if i + len(trap) < len(data):
                data[i:i+len(trap)] = trap

def calculate_checksum(data):
    """Calcula checksum del binario"""
    checksum = 0
    for byte in data:
        checksum = (checksum << 1) ^ byte
    return checksum & 0xFFFFFFFF

def main():
    if len(sys.argv) != 2:
        print("Uso: python3 advanced_obfuscation.py <binario>")
        sys.exit(1)
    
    filename = sys.argv[1]
    
    if not os.path.exists(filename):
        print(f"Error: Archivo '{filename}' no encontrado")
        sys.exit(1)
    
    print(f"🎭 Aplicando ofuscación avanzada a '{filename}'...")
    
    # Leer binario
    data = read_binary(filename)
    original_size = len(data)
    print(f"   Tamaño original: {original_size} bytes")
    
    # Encontrar sección .text
    text_start, text_size = find_text_section(data)
    if text_start is None:
        print("   ⚠️  No se pudo encontrar sección .text")
        text_start = 0
        text_size = len(data) // 2
    
    print(f"   Sección .text: offset={text_start}, size={text_size}")
    
    # Aplicar técnicas de ofuscación
    apply_control_flow_flattening(data, text_start, text_size)
    apply_polymorphic_code(data, text_start, text_size)
    apply_string_obfuscation(data)
    apply_section_mixing(data)
    apply_anti_analysis(data)
    
    # Escribir binario modificado
    write_binary(filename, data)
    
    # Calcular checksum
    checksum = calculate_checksum(data)
    print(f"   Checksum calculado: 0x{checksum:08X}")
    
    print(f"✅ Ofuscación completada!")
    print(f"   Tamaño final: {len(data)} bytes")
    print(f"   Cambio: {len(data) - original_size:+d} bytes")

if __name__ == "__main__":
    main()