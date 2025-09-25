#!/usr/bin/env python3

def ghostrace_hash_v2(input_str):
    """Implementa el algoritmo de hash del GhostRace basado en análisis más detallado del assembly"""
    hash_val = 0
    r8 = 0xf13c6d29a7b0e4cd
    r14 = 0x2492492492492493
    r10 = 0x9e3779b97f4a7c15
    
    for i, byte in enumerate(input_str.encode()):
        # Cálculo de r9
        r9 = (i + 0x31) * byte
        
        # Cálculo de rcx (mod 7)
        rcx = i
        rax = i
        rax = rax * r14
        rax = rax - (rax >> 1)
        rdx = rax >> 2
        rax = rdx * 8
        rax = rax - rdx
        rcx = rcx - rax
        rcx = rcx + 1
        
        # Cálculo de rax
        rax = 0xd6e8feb86659fd93
        rax = rax >> (rcx & 0x3f)
        
        # XOR operations
        rcx = byte ^ i
        rax = rax ^ r9
        
        # Rotate left
        rax = ((rax << (rcx & 0x3f)) | (rax >> (64 - (rcx & 0x3f)))) & 0xffffffffffffffff
        
        # XOR with r8
        rax = rax ^ r8
        
        # Update r8
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
    "CADENCE",
    "agent",
    "Agent",
    "AGENT",
    "relay",
    "Relay",
    "RELAY",
    "node",
    "Node",
    "NODE",
    "diagnostics",
    "Diagnostics",
    "DIAGNOSTICS"
]

print("Probando tokens con algoritmo v2...")
for token in tokens_to_try:
    hash_result = ghostrace_hash_v2(token)
    print(f"Token: {token:15} -> Hash: 0x{hash_result:016x}")
    if hash_result == expected_hash:
        print(f"*** MATCH FOUND: {token} ***")
        break