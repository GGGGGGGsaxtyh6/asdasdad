#!/usr/bin/env python3
import subprocess
import sys
import time

def try_token(token):
    """Prueba un token específico"""
    try:
        result = subprocess.run([
            './ghostrace_node'
        ], input=token + '\n', text=True, capture_output=True, timeout=5)
        
        if "Handshake aligned. Welcome, Nebula-Ghost agent." in result.stdout:
            return True
        return False
        
    except subprocess.TimeoutExpired:
        return False
    except Exception as e:
        return False

def bruteforce_tokens():
    """Fuerza bruta de tokens basada en el contexto del reto"""
    
    tokens_to_try = [
        # Basados en el contexto del reto
        "Nebula-Ghost",
        "nebula-ghost",
        "NEBULA-GHOST",
        "NebulaGhost",
        "nebulaGhost", 
        "NEBULAGHOST",
        "Nebula_Ghost",
        "nebula_ghost",
        "NEBULA_GHOST",
        
        # Basados en GhostRace
        "ghostrace",
        "GhostRace", 
        "GHOSTRACE",
        "ghost-race",
        "Ghost-Race",
        "GHOST-RACE",
        "ghost_race",
        "Ghost_Race",
        "GHOST_RACE",
        
        # Basados en Eclipse
        "eclipse",
        "Eclipse",
        "ECLIPSE",
        "eclipse-lab",
        "Eclipse-Lab",
        "ECLIPSE-LAB",
        "eclipse_lab",
        "Eclipse_Lab",
        "ECLIPSE_LAB",
        
        # Basados en CVEs
        "CVE-2024-3094",
        "CVE-2024-2193",
        "CVE-2024-29745",
        "cve-2024-3094",
        "cve-2024-2193", 
        "cve-2024-29745",
        
        # Basados en términos del reto
        "spectral",
        "Spectral",
        "SPECTRAL",
        "cadence",
        "Cadence", 
        "CADENCE",
        "spectral-cadence",
        "Spectral-Cadence",
        "SPECTRAL-CADENCE",
        "spectralcadence",
        "SpectralCadence",
        "SPECTRALCADENCE",
        
        # Basados en diagnósticos
        "diagnostics",
        "Diagnostics",
        "DIAGNOSTICS",
        "relay",
        "Relay",
        "RELAY",
        "node",
        "Node", 
        "NODE",
        "diagnostics-relay",
        "Diagnostics-Relay",
        "DIAGNOSTICS-RELAY",
        "relay-node",
        "Relay-Node",
        "RELAY-NODE",
        
        # Basados en agente
        "agent",
        "Agent",
        "AGENT",
        "nebula-agent",
        "Nebula-Agent",
        "NEBULA-AGENT",
        "ghost-agent",
        "Ghost-Agent",
        "GHOST-AGENT",
        
        # Basados en backdoor/xz
        "backdoor",
        "Backdoor",
        "BACKDOOR", 
        "xz-backdoor",
        "XZ-Backdoor",
        "XZ-BACKDOOR",
        "xz",
        "XZ",
        
        # Basados en WebAssembly
        "wasm",
        "WASM",
        "WebAssembly",
        "webassembly",
        "WEBASSEMBLY",
        
        # Basados en términos técnicos
        "speculative",
        "Speculative",
        "SPECULATIVE",
        "window",
        "Window",
        "WINDOW",
        "pivot",
        "Pivot",
        "PIVOT",
        "residue",
        "Residue",
        "RESIDUE",
        
        # Basados en timing
        "timing",
        "Timing",
        "TIMING",
        "race",
        "Race",
        "RACE",
        "speculation",
        "Speculation",
        "SPECULATION",
        
        # Basados en términos comunes
        "admin",
        "Administrator",
        "ADMIN",
        "root",
        "Root",
        "ROOT",
        "system",
        "System",
        "SYSTEM",
        "service",
        "Service",
        "SERVICE",
        
        # Basados en fechas/años
        "2024",
        "3094",
        "2193",
        "29745",
        
        # Combinaciones específicas
        "ghost-race-2024",
        "Ghost-Race-2024",
        "GHOST-RACE-2024",
        "eclipse-2024",
        "Eclipse-2024",
        "ECLIPSE-2024",
        "nebula-2024",
        "Nebula-2024",
        "NEBULA-2024",
        
        # Términos del contexto del lab
        "telemetry",
        "Telemetry",
        "TELEMETRY",
        "mitigation",
        "Mitigation",
        "MITIGATION",
        "pipeline",
        "Pipeline",
        "PIPELINE",
        
        # Términos de activación
        "activation",
        "Activation",
        "ACTIVATION",
        "sequence",
        "Sequence",
        "SEQUENCE",
        "activation-sequence",
        "Activation-Sequence",
        "ACTIVATION-SEQUENCE",
        
        # VM/bytecode related
        "vm",
        "VM",
        "bytecode",
        "Bytecode",
        "BYTECODE",
        "spectral-vm",
        "Spectral-VM",
        "SPECTRAL-VM",
        
        # Key/lattice terms
        "key",
        "Key",
        "KEY",
        "lattice",
        "Lattice",
        "LATTICE",
        "key-lattice",
        "Key-Lattice",
        "KEY-LATTICE",
        
        # Hardened terms
        "hardened",
        "Hardened",
        "HARDENED",
        "xz-hardened",
        "XZ-Hardened",
        "XZ-HARDENED",
    ]
    
    print(f"Probando {len(tokens_to_try)} tokens...")
    
    for i, token in enumerate(tokens_to_try):
        print(f"[{i+1}/{len(tokens_to_try)}] Probando: {token}")
        
        if try_token(token):
            print(f"\n*** TOKEN ENCONTRADO: {token} ***")
            return token
            
        time.sleep(0.1)  # Pequeña pausa para evitar saturar
    
    print("\nNo se encontró el token correcto.")
    return None

if __name__ == "__main__":
    result = bruteforce_tokens()
    if result:
        print(f"\nToken correcto: {result}")
    else:
        print("\nFallo al encontrar el token.")