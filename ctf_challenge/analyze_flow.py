#!/usr/bin/env python3

import subprocess
import re

def find_string_references():
    """Encuentra referencias a las strings importantes"""
    
    # Direcciones de strings importantes
    strings = {
        0x30f1: "[Neon-Relay] handshake> ",
        0x310a: "Connection dropped.",
        0x311f: "Handshake rejected.",
        0x3134: "Handshake synchronized.",
        0x314d: "[Neon-Relay] triad-command> ",
        0x318d: "Triad directives locked.",
        0x31a7: "[Neon-Relay] final key> "
    }
    
    # Usar objdump para encontrar referencias
    result = subprocess.run(['objdump', '-d', 'neon_relay_patched'], 
                          capture_output=True, text=True)
    
    print("=== ANÁLISIS DE FLUJO ===")
    print()
    
    for addr, string in strings.items():
        print(f"String en 0x{addr:x}: '{string}'")
        
        # Buscar referencias a esta dirección
        pattern = rf'lea\s+0x[0-9a-f]+\(%rip\),\s*%r[si]i\s*#\s*0x{addr:x}'
        matches = re.findall(pattern, result.stdout)
        
        if matches:
            print(f"  Referencias encontradas:")
            for match in matches:
                print(f"    {match}")
        print()

def find_comparisons():
    """Busca comparaciones de strings (memcmp, strcmp)"""
    
    result = subprocess.run(['objdump', '-d', 'neon_relay_patched'], 
                          capture_output=True, text=True)
    
    print("=== COMPARACIONES DE STRINGS ===")
    print()
    
    # Buscar llamadas a memcmp
    memcmp_calls = re.findall(r'call\s+0x[0-9a-f]+\s*<memcmp@plt>', result.stdout)
    if memcmp_calls:
        print("Llamadas a memcmp encontradas:")
        for call in memcmp_calls:
            print(f"  {call}")
        print()
    
    # Buscar comparaciones de strings
    string_cmps = re.findall(r'call\s+0x[0-9a-f]+\s*<.*cmp.*@plt>', result.stdout)
    if string_cmps:
        print("Otras comparaciones encontradas:")
        for cmp in string_cmps:
            print(f"  {cmp}")

if __name__ == "__main__":
    find_string_references()
    find_comparisons()