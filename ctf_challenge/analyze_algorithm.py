#!/usr/bin/env python3

def analyze_assembly_algorithm():
    """Analiza el algoritmo assembly paso a paso"""
    
    print("=== ANÁLISIS DEL ALGORITMO ASSEMBLY ===")
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
    
    # Analizar el bucle assembly
    print("=== BUCLE DE TRANSFORMACIÓN ===")
    print("for i in range(6):")
    print("  rax = i + i*2 + 1  # lea (%rsi,%rsi,2),%rax; add $0x1,%rax")
    print("  rcx = i & 1        # and $0x1,%ecx")
    print("  rdx = 0            # xor %edx,%edx")
    print("  rdi = r10          # mov %r10d,%edi")
    print("  rax = rax % r15    # div %r15")
    print("  rcx = -rcx         # neg %ecx")
    print("  rax = i + i*4      # lea (%rsi,%rsi,4),%rax")
    print("  rcx = rcx & 0xb    # and $0xb,%ecx")
    print("  rdi = rdi >> rcx   # shr %cl,%edi")
    print("  rdi = rdi ^ array[i]  # xor (%r9,%rsi,4),%edi")
    print("  rcx = rax % r15    # div %r15")
    print("  cl = memory[r13 + rcx]  # movzbl 0x0(%r13,%rcx,1),%ecx")
    print("  cl = cl ^ memory[r13 + rdx]  # xor 0x0(%r13,%rdx,1),%cl")
    print("  rdi = rol(rdi, cl) # rol %cl,%edi")
    print("  eax = memory[rsp + rdx*4 + 0xb0]  # mov 0xb0(%rsp,%rdx,4),%eax")
    print("  eax = eax ^ 0xcafebabe  # xor $0xcafebabe,%eax")
    print("  rdi = rdi + eax    # add %eax,%edi")
    print("  array[i] = rdi     # mov %edi,(%r9,%rsi,4)")
    print()
    
    # Intentar encontrar valores iniciales
    print("=== BÚSQUEDA DE VALORES INICIALES ===")
    
    # Probar diferentes valores para r10, r15, r13
    for r10 in [0x12345678, 0x87654321, 0xDEADBEEF, 0xCAFEBABE, 0x0]:
        for r15 in [7, 8, 9, 10, 11, 12, 13, 14, 15]:
            for r13_base in [0x2000, 0x3000, 0x4000, 0x5000]:
                print(f"Probando r10=0x{r10:08x}, r15={r15}, r13_base=0x{r13_base:04x}")
                
                # Simular memoria en r13
                memory_r13 = [i for i in range(256)]  # Simular memoria
                
                # Simular el algoritmo
                results = [0] * 6
                
                for i in range(6):
                    rax = i + i * 2 + 1
                    rcx = i & 1
                    rdx = 0
                    rdi = r10
                    
                    rax = rax % r15
                    rcx = -rcx
                    rax = i + i * 4
                    rcx = rcx & 0xb
                    rdi = rdi >> rcx
                    rdi = rdi ^ results[i]
                    
                    rcx = rax % r15
                    rdx = 0
                    cl = memory_r13[(r13_base + rcx) % 256]
                    cl = cl ^ memory_r13[(r13_base + rdx) % 256]
                    
                    rdi = ((rdi << cl) | (rdi >> (32 - cl))) & 0xFFFFFFFF
                    
                    eax = 0x12345678  # Valor estimado
                    eax = eax ^ 0xcafebabe
                    rdi = (rdi + eax) & 0xFFFFFFFF
                    
                    results[i] = rdi
                
                # Verificar si coincide
                if results == target_ints:
                    print(f"¡ENCONTRADO! r10=0x{r10:08x}, r15={r15}, r13_base=0x{r13_base:04x}")
                    return r10, r15, r13_base
    
    print("No se encontraron valores iniciales válidos.")
    return None, None, None

def brute_force_handshake():
    """Búsqueda exhaustiva del handshake"""
    
    print("=== BÚSQUEDA EXHAUSTIVA ===")
    
    import itertools
    import string
    
    # Caracteres más probables
    chars = string.ascii_uppercase + string.digits + "!@#$%^&*()_+-=[]{}|;:,.<>?"
    
    # Probar combinaciones de 6 caracteres
    count = 0
    for combo in itertools.product(chars, repeat=6):
        handshake = ''.join(combo)
        count += 1
        
        if count % 10000 == 0:
            print(f"Probadas {count} combinaciones...")
        
        # Aquí implementaríamos la verificación
        # Por ahora solo probamos algunas
        if count > 100000:
            print("Búsqueda limitada alcanzada.")
            break
    
    return None

if __name__ == "__main__":
    # Analizar algoritmo
    r10, r15, r13_base = analyze_assembly_algorithm()
    
    if r10 is not None:
        print(f"Valores encontrados: r10=0x{r10:08x}, r15={r15}, r13_base=0x{r13_base:04x}")
    else:
        print("No se encontraron valores iniciales. Probando búsqueda exhaustiva...")
        brute_force_handshake()