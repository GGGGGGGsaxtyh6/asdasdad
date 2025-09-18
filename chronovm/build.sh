#!/bin/bash

echo "🔧 Compilando ChronoVM - Reto de Reversing Insane"
echo "================================================"

# Compilar con optimizaciones y ofuscación
gcc -o chronovm chronovm.c \
    -O3 \
    -fno-stack-protector \
    -fno-pie \
    -no-pie \
    -static \
    -s \
    -Wl,--strip-all \
    -Wl,--build-id=none \
    -Wl,--hash-style=sysv \
    -fno-ident \
    -fno-asynchronous-unwind-tables \
    -fno-unwind-tables \
    -fno-plt \
    -fno-pic \
    -fno-pie \
    -ffunction-sections \
    -fdata-sections \
    -Wl,--gc-sections \
    -Wl,--strip-all \
    -Wl,--build-id=none

if [ $? -eq 0 ]; then
    echo "✅ Compilación exitosa"
    
    # Crear script de post-procesamiento para renombrar secciones
    cat > post_process.py << 'EOF'
#!/usr/bin/env python3
import struct
import sys

def rename_sections(binary_path):
    with open(binary_path, 'rb') as f:
        data = bytearray(f.read())
    
    # Buscar la tabla de secciones
    # ELF header está en offset 0
    elf_header = struct.unpack('<16sHHIIIIIHHHHHH', data[:52])
    
    if data[:4] != b'\x7fELF':
        print("Error: No es un archivo ELF válido")
        return False
    
    # Obtener información del header
    e_shoff = elf_header[6]  # Offset de la tabla de secciones
    e_shentsize = elf_header[9]  # Tamaño de cada entrada de sección
    e_shnum = elf_header[10]  # Número de secciones
    e_shstrndx = elf_header[11]  # Índice de la tabla de strings de secciones
    
    print(f"Secciones encontradas: {e_shnum}")
    print(f"Offset tabla secciones: 0x{e_shoff:x}")
    
    # Leer tabla de strings de secciones
    shstrtab_offset = e_shoff + e_shstrndx * e_shentsize
    shstrtab_header = struct.unpack('<IIIIIIIIIIIIIIII', data[shstrtab_offset:shstrtab_offset + 64])
    shstrtab_data_offset = shstrtab_header[4]
    shstrtab_size = shstrtab_header[5]
    
    shstrtab = data[shstrtab_data_offset:shstrtab_data_offset + shstrtab_size]
    
    # Renombrar secciones comunes
    section_renames = {
        b'.text': b'.chrono_vm',
        b'.data': b'.temporal_data',
        b'.rodata': b'.encrypted_bytecode',
        b'.bss': b'.vm_memory',
        b'.init': b'.vm_init',
        b'.fini': b'.vm_cleanup',
        b'.plt': b'.vm_dispatch',
        b'.got': b'.vm_globals',
        b'.got.plt': b'.vm_imports',
        b'.dynamic': b'.vm_config',
        b'.dynsym': b'.vm_symbols',
        b'.dynstr': b'.vm_strings',
        b'.rela.dyn': b'.vm_relocs',
        b'.rela.plt': b'.vm_plt_relocs',
        b'.eh_frame': b'.vm_exceptions',
        b'.eh_frame_hdr': b'.vm_exception_hdr',
        b'.comment': b'.vm_metadata',
        b'.note.gnu.build-id': b'.vm_build_info',
        b'.gnu.hash': b'.vm_hash',
        b'.gnu.version': b'.vm_versions',
        b'.gnu.version_r': b'.vm_version_requires',
        b'.gnu.version_d': b'.vm_version_defines'
    }
    
    # Procesar cada sección
    modified = False
    for i in range(e_shnum):
        section_offset = e_shoff + i * e_shentsize
        section_header = struct.unpack('<IIIIIIIIIIIIIIII', data[section_offset:section_offset + 64])
        
        # Obtener nombre de la sección
        name_offset = section_header[0]
        if name_offset < len(shstrtab):
            section_name = shstrtab[name_offset:].split(b'\x00')[0]
            
            # Renombrar si está en nuestra lista
            if section_name in section_renames:
                new_name = section_renames[section_name]
                print(f"Renombrando sección: {section_name.decode()} -> {new_name.decode()}")
                
                # Encontrar el final del string actual
                end_offset = shstrtab.find(b'\x00', name_offset)
                if end_offset == -1:
                    end_offset = len(shstrtab)
                
                # Verificar si hay espacio suficiente
                if end_offset - name_offset >= len(new_name):
                    # Sobrescribir el nombre
                    shstrtab[name_offset:name_offset + len(new_name)] = new_name
                    # Rellenar con nulls si es necesario
                    if len(new_name) < end_offset - name_offset:
                        shstrtab[name_offset + len(new_name):end_offset] = b'\x00'
                    modified = True
                else:
                    print(f"  Advertencia: No hay espacio suficiente para renombrar {section_name.decode()}")
    
    if modified:
        # Escribir los cambios de vuelta
        data[shstrtab_data_offset:shstrtab_data_offset + shstrtab_size] = shstrtab
        
        # Crear backup
        with open(binary_path + '.backup', 'wb') as f:
            f.write(data)
        
        # Escribir archivo modificado
        with open(binary_path, 'wb') as f:
            f.write(data)
        
        print("✅ Secciones renombradas exitosamente")
        return True
    else:
        print("ℹ️  No se encontraron secciones para renombrar")
        return False

if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("Uso: python3 post_process.py <archivo_binario>")
        sys.exit(1)
    
    rename_sections(sys.argv[1])
EOF

    chmod +x post_process.py
    
    # Aplicar post-procesamiento
    echo "🔧 Aplicando ofuscación de secciones..."
    python3 post_process.py chronovm
    
    # Verificar el resultado
    echo "📋 Verificando secciones del binario:"
    readelf -S chronovm | head -20
    
    # Crear script de prueba
    cat > test_challenge.sh << 'EOF'
#!/bin/bash

echo "🧪 Probando ChronoVM Challenge"
echo "=============================="

# Probar sin argumentos
echo "1. Ejecutando sin argumentos:"
echo "   (Esto debería mostrar el reloj digital básico)"
./chronovm << EOF
test123
EOF

echo -e "\n2. Ejecutando con argumentos (para activar fragmentos de flag):"
echo "   (Esto debería escribir fragmentos en /dev/shm)"
./chronovm --enable-fragments << EOF
test123
EOF

echo -e "\n3. Verificando fragmentos en /dev/shm:"
ls -la /dev/shm/chrono_frag_* 2>/dev/null || echo "   No se encontraron fragmentos"

echo -e "\n4. Información del binario:"
file chronovm
ls -la chronovm

echo -e "\n5. Secciones del binario:"
readelf -S chronovm | grep -E "(chrono|temporal|encrypted|vm_)"

echo -e "\n6. Strings en el binario:"
strings chronovm | grep -i "chrono\|vm\|flag" | head -10
EOF

    chmod +x test_challenge.sh
    
    echo "✅ Build completado exitosamente"
    echo "📁 Archivos generados:"
    echo "   - chronovm (binario principal)"
    echo "   - chronovm.backup (backup original)"
    echo "   - post_process.py (script de ofuscación)"
    echo "   - test_challenge.sh (script de prueba)"
    echo ""
    echo "🚀 Para probar el reto:"
    echo "   ./test_challenge.sh"
    
else
    echo "❌ Error en la compilación"
    exit 1
fi