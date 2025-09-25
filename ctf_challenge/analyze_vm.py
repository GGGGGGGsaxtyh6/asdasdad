#!/usr/bin/env python3

def analyze_vm_program():
    """Analiza el programa de la VM"""
    
    with open('ghostrace_node', 'rb') as f:
        data = f.read()
    
    # Extraer los datos
    program_stream = data[0x49a0:0x49a0+0x124]
    encoded_program = data[0x4ae0:0x4ae0+0x124] 
    program_salt = data[0x4ac8:0x4ac8+0x9]
    flag_key_stream = data[0x4960:0x4960+0x24]
    flag_spice_stream = data[0x4920:0x4920+0x24]
    const_pool = data[0x4c20:0x4c20+0x88]
    
    print("Programa VM - Análisis de constantes")
    print("=" * 50)
    
    print(f"Program Salt: {program_salt.hex()}")
    print(f"Program Stream length: {len(program_stream)}")
    print(f"Encoded Program length: {len(encoded_program)}")
    
    # Intentar decodificar usando XOR con salt
    print("\nIntentando decodificar programa...")
    
    # Expandir salt para cubrir todo el programa
    extended_salt = (program_salt * (len(program_stream) // len(program_salt) + 1))[:len(program_stream)]
    
    # XOR para decodificar
    decoded_program = bytes(a ^ b for a, b in zip(encoded_program, extended_salt))
    
    print(f"Programa decodificado: {decoded_program[:50].hex()}...")
    
    # Analizar const pool
    print(f"\nConst Pool analysis:")
    const_pool_values = []
    for i in range(0, len(const_pool), 8):
        value = int.from_bytes(const_pool[i:i+8], byteorder='little')
        const_pool_values.append(value)
        print(f"  Pool[{i//8}]: 0x{value:016x} ({value})")
    
    # Analizar el programa como bytecode
    print(f"\nProgram Stream bytecode analysis:")
    for i in range(0, min(50, len(program_stream)), 4):
        opcode = program_stream[i]
        arg1 = program_stream[i+1] if i+1 < len(program_stream) else 0
        arg2 = program_stream[i+2] if i+2 < len(program_stream) else 0
        arg3 = program_stream[i+3] if i+3 < len(program_stream) else 0
        
        print(f"  [{i:3d}] opcode={opcode:02x} args=({arg1:02x}, {arg2:02x}, {arg3:02x})")
    
    # Basado en la pista "mods 5 and 3", intentar crear un payload
    print(f"\nCreando payload basado en pista 'mods 5 and 3 loop':")
    
    # Crear un programa simple que use mod 5 y 3
    # Esto es especulativo basado en la pista
    simple_program = []
    
    # Operaciones que podrían usar mod 5 y 3
    for i in range(10):
        simple_program.extend([
            0x01, i % 5, (i+1) % 3, 0x00  # Operación con mod 5 y 3
        ])
    
    payload = bytes(simple_program)
    print(f"Payload generado: {payload.hex()}")
    
    return {
        'program_stream': program_stream,
        'encoded_program': encoded_program,
        'decoded_program': decoded_program,
        'program_salt': program_salt,
        'flag_key_stream': flag_key_stream,
        'flag_spice_stream': flag_spice_stream,
        'const_pool': const_pool,
        'const_pool_values': const_pool_values,
        'suggested_payload': payload
    }

if __name__ == "__main__":
    result = analyze_vm_program()