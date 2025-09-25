#!/usr/bin/env python3

import subprocess

def test_handshake(handshake):
    """Prueba un handshake específico"""
    try:
        result = subprocess.run(
            ["./neon_relay_patched"], 
            input=handshake + "\n", 
            text=True, 
            capture_output=True, 
            timeout=3
        )
        output = result.stdout + result.stderr
        return "Handshake synchronized" in output, output
    except:
        return False, "Error"

def main():
    # Combinaciones específicas que podrían ser el handshake
    candidates = [
        "CHRONO", "TEMPOR", "PHASER", "NEONIC", "RELAYER",
        "SYNC01", "NEON01", "RELAY1", "CHRON1", "TEMPO1", "PHASE1",
        "LOCK01", "KEY001", "CODE01", "AUTH01", "LOGIN1",
        "ACCESS", "SECRET", "TOKEN1", "FLAG01", "CRONO1",
        "NEON!!", "RELAY!", "SYNC!!", "CHRON!", "TEMPO!", "PHASE!",
        "NEON**", "RELAY*", "SYNC**", "CHRON*", "TEMPO*", "PHASE*",
        "DEADBE", "CAFEBE", "BEEFCA", "FEEDBA", "BABECA", "CABEBA",
        "000000", "111111", "123456", "654321", "000001", "000010",
        "AAAAAA", "BBBBBB", "CCCCCC", "DDDDDD", "EEEEEE", "FFFFFF",
        "ABCDEF", "FEDCBA", "123123", "456456", "789789",
        "NEON-1", "RELAY_", "SYNC-1", "CHRON_", "TEMPO-", "PHASE_",
        "NEON_1", "RELAY-", "SYNC_1", "CHRON-", "TEMPO_", "PHASE-",
        "NEON:1", "RELAY:1", "SYNC:1", "CHRON:1", "TEMPO:1", "PHASE:1",
        "NEON;1", "RELAY;1", "SYNC;1", "CHRON;1", "TEMPO;1", "PHASE;1",
        "NEON,1", "RELAY,1", "SYNC,1", "CHRON,1", "TEMPO,1", "PHASE,1",
        "NEON.1", "RELAY.1", "SYNC.1", "CHRON.1", "TEMPO.1", "PHASE.1",
        "NEON/1", "RELAY/1", "SYNC/1", "CHRON/1", "TEMPO/1", "PHASE/1",
        "NEON\\1", "RELAY\\1", "SYNC\\1", "CHRON\\1", "TEMPO\\1", "PHASE\\1",
        "NEON|1", "RELAY|1", "SYNC|1", "CHRON|1", "TEMPO|1", "PHASE|1",
        "NEON~1", "RELAY~1", "SYNC~1", "CHRON~1", "TEMPO~1", "PHASE~1",
        "NEON'1", "RELAY'1", "SYNC'1", "CHRON'1", "TEMPO'1", "PHASE'1",
        'NEON"1', 'RELAY"1', 'SYNC"1', 'CHRON"1', 'TEMPO"1', 'PHASE"1',
        "NEON`1", "RELAY`1", "SYNC`1", "CHRON`1", "TEMPO`1", "PHASE`1",
        "NEON?1", "RELAY?1", "SYNC?1", "CHRON?1", "TEMPO?1", "PHASE?1",
        "NEON!1", "RELAY!1", "SYNC!1", "CHRON!1", "TEMPO!1", "PHASE!1",
        "NEON=1", "RELAY=1", "SYNC=1", "CHRON=1", "TEMPO=1", "PHASE=1",
        "NEON+1", "RELAY+1", "SYNC+1", "CHRON+1", "TEMPO+1", "PHASE+1",
        "NEON-1", "RELAY-1", "SYNC-1", "CHRON-1", "TEMPO-1", "PHASE-1",
        "NEON*1", "RELAY*1", "SYNC*1", "CHRON*1", "TEMPO*1", "PHASE*1",
        "NEON/1", "RELAY/1", "SYNC/1", "CHRON/1", "TEMPO/1", "PHASE/1",
        "NEON%1", "RELAY%1", "SYNC%1", "CHRON%1", "TEMPO%1", "PHASE%1",
        "NEON&1", "RELAY&1", "SYNC&1", "CHRON&1", "TEMPO&1", "PHASE&1",
        "NEON#1", "RELAY#1", "SYNC#1", "CHRON#1", "TEMPO#1", "PHASE#1",
        "NEON$1", "RELAY$1", "SYNC$1", "CHRON$1", "TEMPO$1", "PHASE$1",
        "NEON@1", "RELAY@1", "SYNC@1", "CHRON@1", "TEMPO@1", "PHASE@1",
        "NEON^1", "RELAY^1", "SYNC^1", "CHRON^1", "TEMPO^1", "PHASE^1"
    ]
    
    print(f"Probando {len(candidates)} candidatos...")
    
    for i, candidate in enumerate(candidates, 1):
        print(f"[{i:3d}/{len(candidates)}] Probando: '{candidate}'", end=" ")
        
        success, output = test_handshake(candidate)
        
        if success:
            print("✓ ¡ÉXITO!")
            print(f"Handshake encontrado: '{candidate}'")
            print(f"Output: {output}")
            return candidate
        else:
            print("✗")
    
    print("\nNo se encontró el handshake.")
    return None

if __name__ == "__main__":
    result = main()
    if result:
        print(f"\n🎉 HANDSHAKE ENCONTRADO: '{result}'")
    else:
        print("\n❌ No se pudo encontrar el handshake.")