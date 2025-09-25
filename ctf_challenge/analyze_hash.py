#!/usr/bin/env python3

def ghostrace_hash(input_str):
    """Implementa el algoritmo de hash del GhostRace basado en el análisis del assembly"""
    hash_val = 0
    r8 = 0xf13c6d29a7b0e4cd
    r14 = 0x2492492492492493
    r10 = 0x9e3779b97f4a7c15
    
    for i, byte in enumerate(input_str.encode()):
        r9 = (i + 0x31) * byte
        rcx = i
        rax = i
        rax = rax * r14
        rax = rax - (rax >> 1)
        rdx = rax >> 2
        rax = rdx * 8
        rax = rax - rdx
        rcx = rcx - rax
        rcx = rcx + 1
        
        rax = 0xd6e8feb86659fd93
        rax = rax >> (rcx & 0x3f)
        
        rcx = byte ^ i
        rax = rax ^ r9
        rax = rax << (rcx & 0x3f)
        rax = rax ^ r8
        r8 = (rax + r10) & 0xffffffffffffffff
        r8 = ((r8 << 11) | (r8 >> 53)) & 0xffffffffffffffff
        
        hash_val = rax
    
    return hash_val

# Hash esperado del assembly
expected_hash = 0x155318c9c24e38eb

# Probar diferentes tokens
tokens_to_try = [
    "Nebula-Ghost",
    "nebula-ghost", 
    "NEBULA-GHOST",
    "Nebula_Ghost",
    "nebula_ghost",
    "NebulaGhost",
    "nebulaGhost",
    "NEBULAGHOST",
    "ghostrace",
    "GhostRace",
    "GHOSTRACE",
    "eclipse",
    "Eclipse",
    "ECLIPSE",
    "spectral",
    "Spectral",
    "SPECTRAL",
    "cadence",
    "Cadence",
    "CADENCE"
]

print("Probando tokens...")
for token in tokens_to_try:
    hash_result = ghostrace_hash(token)
    print(f"Token: {token:15} -> Hash: 0x{hash_result:016x}")
    if hash_result == expected_hash:
        print(f"*** MATCH FOUND: {token} ***")
        break