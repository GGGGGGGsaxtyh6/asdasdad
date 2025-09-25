#!/usr/bin/env python3

def extract_constants():
    """Extrae las constantes del binario"""
    
    with open('ghostrace_node', 'rb') as f:
        data = f.read()
    
    # Direcciones de las constantes (offsets relativos a la base del archivo)
    constants = {
        'kProgramStream': 0x49a0,
        'kEncodedProgram': 0x4ae0, 
        'kProgramSalt': 0x4ac8,
        'kFlagKeyStream': 0x4960,
        'kFlagSpiceStream': 0x4920,
        'kConstPool': 0x4c20
    }
    
    # Tamaños de las constantes
    sizes = {
        'kProgramStream': 0x124,
        'kEncodedProgram': 0x124,
        'kProgramSalt': 0x9,
        'kFlagKeyStream': 0x24,
        'kFlagSpiceStream': 0x24,
        'kConstPool': 0x88
    }
    
    results = {}
    
    for name, offset in constants.items():
        size = sizes[name]
        const_data = data[offset:offset+size]
        results[name] = const_data
        
        print(f"{name} (offset 0x{offset:x}, size 0x{size:x}):")
        print(f"  Hex: {const_data.hex()}")
        print(f"  Bytes: {len(const_data)}")
        
        # Intentar interpretar como string
        try:
            as_str = const_data.decode('ascii', errors='ignore')
            printable_str = ''.join(c if c.isprintable() else '.' for c in as_str)
            if printable_str.strip():
                print(f"  ASCII: {printable_str}")
        except:
            pass
        
        print()
    
    return results

if __name__ == "__main__":
    constants = extract_constants()