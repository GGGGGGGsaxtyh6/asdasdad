#!/usr/bin/env python3

import struct

def solve_handshake_algorithm():
    """Implementa el algoritmo exacto del assembly para encontrar el handshake"""
    
    print("=== SOLUCIONADOR DE ALGORITMO ===")
    print()
    
    # Datos objetivo (24 bytes = 6 * 4 bytes)
    target_bytes = [
        0x6e, 0x41, 0xe4, 0xda, 0xa3, 0x5c, 0x51, 0x78,
        0x59, 0x12, 0x93, 0xa5, 0x3a, 0x1c, 0x75, 0xb3,
        0xba, 0x10, 0x2f, 0x4a, 0x11, 0x15, 0x83, 0xbd
    ]
    
    # Convertir a 6 enteros de 32 bits (little endian)
    target_ints = []
    for i in range(0, 24, 4):
        val = (target_bytes[i+3] << 24) | (target_bytes[i+2] << 16) | (target_bytes[i+1] << 8) | target_bytes[i]
        target_ints.append(val)
    
    print(f"Valores objetivo: {[hex(x) for x in target_ints]}")
    print()
    
    # Intentar diferentes valores para las constantes del algoritmo
    # Basado en el análisis del assembly
    
    # Valores probables para r10, r15, r13_base
    r10_candidates = [0x12345678, 0x87654321, 0xDEADBEEF, 0xCAFEBABE, 0x0, 0x1, 0x100, 0x1000]
    r15_candidates = [7, 8, 9, 10, 11, 12, 13, 14, 15, 16]
    r13_base_candidates = [0x2000, 0x3000, 0x4000, 0x5000, 0x1000, 0x6000]
    
    # Simular memoria en r13 (256 bytes)
    memory_r13 = [i for i in range(256)]
    
    print("Probando diferentes combinaciones de constantes...")
    
    for r10 in r10_candidates:
        for r15 in r15_candidates:
            for r13_base in r13_base_candidates:
                print(f"Probando r10=0x{r10:08x}, r15={r15}, r13_base=0x{r13_base:04x}")
                
                # Simular el algoritmo exacto del assembly
                results = [0] * 6
                
                for i in range(6):
                    # Implementar el algoritmo exacto del assembly
                    rax = i + i * 2 + 1  # lea (%rsi,%rsi,2),%rax; add $0x1,%rax
                    rcx = i & 1          # and $0x1,%ecx
                    rdx = 0              # xor %edx,%edx
                    rdi = r10            # mov %r10d,%edi
                    
                    rax = rax % r15      # div %r15
                    rcx = -rcx           # neg %ecx
                    rax = i + i * 4      # lea (%rsi,%rsi,4),%rax
                    rcx = rcx & 0xb      # and $0xb,%ecx
                    rdi = rdi >> rcx     # shr %cl,%edi
                    rdi = rdi ^ results[i]  # xor (%r9,%rsi,4),%edi
                    
                    rcx = rax % r15      # div %r15
                    rdx = 0
                    cl = memory_r13[(r13_base + rcx) % 256]
                    cl = cl ^ memory_r13[(r13_base + rdx) % 256]
                    
                    rdi = ((rdi << cl) | (rdi >> (32 - cl))) & 0xFFFFFFFF  # rol %cl,%edi
                    
                    eax = 0x12345678  # Valor estimado del stack
                    eax = eax ^ 0xcafebabe  # xor $0xcafebabe,%eax
                    rdi = (rdi + eax) & 0xFFFFFFFF  # add %eax,%edi
                    
                    results[i] = rdi
                
                # Verificar si coincide
                if results == target_ints:
                    print(f"¡ENCONTRADO! r10=0x{r10:08x}, r15={r15}, r13_base=0x{r13_base:04x}")
                    return r10, r15, r13_base
    
    print("No se encontraron valores iniciales válidos.")
    return None, None, None

def reverse_engineer_handshake():
    """Intenta hacer ingeniería inversa del handshake"""
    
    print("\n=== INGENIERÍA INVERSA ===")
    
    # Si encontramos las constantes, podemos intentar hacer ingeniería inversa
    r10, r15, r13_base = solve_handshake_algorithm()
    
    if r10 is not None:
        print(f"Constantes encontradas: r10=0x{r10:08x}, r15={r15}, r13_base=0x{r13_base:04x}")
        
        # Ahora intentar hacer ingeniería inversa para encontrar el handshake
        # Esto es complejo, pero podemos intentar algunos enfoques
        
        # Enfoque 1: Probar que el handshake sea simplemente los valores objetivo
        # convertidos de vuelta a caracteres
        target_bytes = [
            0x6e, 0x41, 0xe4, 0xda, 0xa3, 0x5c, 0x51, 0x78,
            0x59, 0x12, 0x93, 0xa5, 0x3a, 0x1c, 0x75, 0xb3,
            0xba, 0x10, 0x2f, 0x4a, 0x11, 0x15, 0x83, 0xbd
        ]
        
        # Intentar diferentes transformaciones de los bytes objetivo
        transformations = [
            # XOR con diferentes valores
            [x ^ 0x42 for x in target_bytes[:6]],
            [x ^ 0x13 for x in target_bytes[:6]],
            [x ^ 0x37 for x in target_bytes[:6]],
            [x ^ 0x69 for x in target_bytes[:6]],
            [x ^ 0x88 for x in target_bytes[:6]],
            [x ^ 0xAA for x in target_bytes[:6]],
            [x ^ 0xCC for x in target_bytes[:6]],
            [x ^ 0xFF for x in target_bytes[:6]],
            
            # Suma/resta
            [(x + 0x20) & 0xFF for x in target_bytes[:6]],
            [(x - 0x20) & 0xFF for x in target_bytes[:6]],
            
            # Rotación
            [((x << 1) | (x >> 7)) & 0xFF for x in target_bytes[:6]],
            [((x >> 1) | (x << 7)) & 0xFF for x in target_bytes[:6]],
        ]
        
        print("Probando transformaciones de los bytes objetivo...")
        
        for i, transformed in enumerate(transformations):
            # Convertir a string si es posible
            try:
                handshake = ''.join([chr(x) if 32 <= x <= 126 else '.' for x in transformed])
                print(f"Transformación {i+1}: {handshake}")
                
                # Probar este handshake
                if test_handshake(handshake):
                    print(f"¡ÉXITO! Handshake encontrado: '{handshake}'")
                    return handshake
            except:
                pass
        
        print("No se encontró el handshake con transformaciones.")
        return None
    else:
        print("No se pudieron encontrar las constantes del algoritmo.")
        return None

def test_handshake(handshake):
    """Prueba un handshake específico"""
    
    import subprocess
    
    try:
        result = subprocess.run(
            ["./neon_relay_patched"], 
            input=handshake + "\n", 
            text=True, 
            capture_output=True, 
            timeout=3
        )
        
        output = result.stdout + result.stderr
        return "Handshake synchronized" in output
        
    except:
        return False

if __name__ == "__main__":
    result = reverse_engineer_handshake()
    
    if result:
        print(f"\n🎉 HANDSHAKE ENCONTRADO: '{result}'")
    else:
        print("\n❌ No se pudo encontrar el handshake.")