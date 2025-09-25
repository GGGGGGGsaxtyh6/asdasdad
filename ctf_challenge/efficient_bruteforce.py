#!/usr/bin/env python3

import subprocess
import itertools
import string
import time

def test_handshake(handshake):
    """Prueba un handshake específico"""
    
    try:
        # Ejecutar el binario con el handshake
        result = subprocess.run(
            ["./neon_relay_patched"], 
            input=handshake + "\n", 
            text=True, 
            capture_output=True, 
            timeout=3
        )
        
        output = result.stdout + result.stderr
        
        if "Handshake synchronized" in output:
            return True, output
        elif "Handshake rejected" in output:
            return False, output
        else:
            return False, output
            
    except subprocess.TimeoutExpired:
        return False, "Timeout"
    except Exception as e:
        return False, f"Error: {e}"

def efficient_bruteforce():
    """Búsqueda eficiente del handshake"""
    
    print("=== BÚSQUEDA EFICIENTE DEL HANDSHAKE ===")
    print()
    
    # Lista de palabras más probables basadas en el tema
    high_probability_words = [
        "NEON", "RELAY", "SYNC", "CHRON", "TEMPO", "PHASE", "LOCK", "KEY",
        "CODE", "AUTH", "LOGIN", "ACCESS", "SECRET", "TOKEN", "FLAG", "CRONO",
        "TEMPOR", "PHASER", "CHRONO", "TEMPORAL", "PHASIC", "NEONIC", "RELAYER"
    ]
    
    # Caracteres más comunes para rellenar
    common_chars = "0123456789!@#$%^&*()_+-=[]{}|;:,.<>?"
    
    # Generar candidatos prioritarios
    candidates = []
    
    # 1. Palabras exactas de 6 caracteres
    for word in high_probability_words:
        if len(word) == 6:
            candidates.append(word)
    
    # 2. Palabras + números (más probables)
    for word in high_probability_words:
        if len(word) < 6:
            remaining = 6 - len(word)
            # Probar solo los números más comunes
            for fill in itertools.product("0123456789", repeat=remaining):
                candidates.append(word + ''.join(fill))
    
    # 3. Patrones específicos del tema
    theme_patterns = [
        "NEON01", "NEON02", "NEON03", "RELAY1", "RELAY2", "RELAY3",
        "SYNC01", "SYNC02", "SYNC03", "CHRON1", "CHRON2", "CHRON3",
        "TEMPO1", "TEMPO2", "TEMPO3", "PHASE1", "PHASE2", "PHASE3",
        "LOCK01", "LOCK02", "LOCK03", "KEY001", "KEY002", "KEY003",
        "CODE01", "CODE02", "CODE03", "AUTH01", "AUTH02", "AUTH03",
        "LOGIN1", "LOGIN2", "LOGIN3", "ACCESS", "SECRET", "TOKEN1",
        "FLAG01", "FLAG02", "FLAG03", "CRONO1", "CRONO2", "CRONO3",
        "TEMPOR", "PHASER", "CHRONO", "NEONIC", "RELAYER"
    ]
    candidates.extend(theme_patterns)
    
    # 4. Patrones hexadecimales comunes
    hex_patterns = [
        "DEADBE", "CAFEBE", "BEEFCA", "FEEDBA", "BABECA", "CABEBA",
        "0xDEAD", "0xCAFE", "0xBEEF", "0xFEED", "0xBABE", "0xCABE"
    ]
    candidates.extend(hex_patterns)
    
    # 5. Patrones numéricos
    numeric_patterns = [
        "000000", "111111", "123456", "654321", "000001", "000010",
        "100000", "200000", "300000", "400000", "500000", "600000",
        "700000", "800000", "900000", "123123", "456456", "789789"
    ]
    candidates.extend(numeric_patterns)
    
    # 6. Patrones de letras
    letter_patterns = [
        "AAAAAA", "BBBBBB", "CCCCCC", "DDDDDD", "EEEEEE", "FFFFFF",
        "ABCDEF", "FEDCBA", "ABCDEF", "FEDCBA", "ABCDEF", "FEDCBA"
    ]
    candidates.extend(letter_patterns)
    
    # Eliminar duplicados y ordenar por probabilidad
    candidates = list(set(candidates))
    
    print(f"Probando {len(candidates)} candidatos prioritarios...")
    print()
    
    count = 0
    for candidate in candidates:
        count += 1
        print(f"[{count:3d}/{len(candidates)}] Probando: '{candidate}'", end=" ")
        
        success, output = test_handshake(candidate)
        
        if success:
            print("✓ ¡ÉXITO!")
            print(f"Handshake encontrado: '{candidate}'")
            print(f"Output: {output}")
            return candidate
        else:
            print("✗")
        
        # Pausa pequeña para no sobrecargar
        if count % 20 == 0:
            time.sleep(0.1)
    
    print("\nNo se encontró el handshake con candidatos prioritarios.")
    return None

def extended_bruteforce():
    """Búsqueda extendida con más patrones"""
    
    print("\n=== BÚSQUEDA EXTENDIDA ===")
    
    # Patrones adicionales
    extended_patterns = [
        # Patrones con caracteres especiales
        "NEON!!", "RELAY!", "SYNC!!", "CHRON!", "TEMPO!", "PHASE!",
        "NEON**", "RELAY*", "SYNC**", "CHRON*", "TEMPO*", "PHASE*",
        "NEON##", "RELAY#", "SYNC##", "CHRON#", "TEMPO#", "PHASE#",
        
        # Patrones con guiones y guiones bajos
        "NEON-1", "RELAY_", "SYNC-1", "CHRON_", "TEMPO-", "PHASE_",
        "NEON_1", "RELAY-", "SYNC_1", "CHRON-", "TEMPO_", "PHASE-",
        
        # Patrones con paréntesis
        "NEON(1)", "RELAY(1)", "SYNC(1)", "CHRON(1)", "TEMPO(1)", "PHASE(1)",
        
        # Patrones con corchetes
        "NEON[1]", "RELAY[1]", "SYNC[1]", "CHRON[1]", "TEMPO[1]", "PHASE[1]",
        
        # Patrones con llaves
        "NEON{1}", "RELAY{1}", "SYNC{1}", "CHRON{1}", "TEMPO{1}", "PHASE{1}",
        
        # Patrones con dos puntos
        "NEON:1", "RELAY:1", "SYNC:1", "CHRON:1", "TEMPO:1", "PHASE:1",
        
        # Patrones con punto y coma
        "NEON;1", "RELAY;1", "SYNC;1", "CHRON;1", "TEMPO;1", "PHASE;1",
        
        # Patrones con comas
        "NEON,1", "RELAY,1", "SYNC,1", "CHRON,1", "TEMPO,1", "PHASE,1",
        
        # Patrones con puntos
        "NEON.1", "RELAY.1", "SYNC.1", "CHRON.1", "TEMPO.1", "PHASE.1",
        
        # Patrones con barras
        "NEON/1", "RELAY/1", "SYNC/1", "CHRON/1", "TEMPO/1", "PHASE/1",
        
        # Patrones con barras invertidas
        "NEON\\1", "RELAY\\1", "SYNC\\1", "CHRON\\1", "TEMPO\\1", "PHASE\\1",
        
        # Patrones con pipes
        "NEON|1", "RELAY|1", "SYNC|1", "CHRON|1", "TEMPO|1", "PHASE|1",
        
        # Patrones con tildes
        "NEON~1", "RELAY~1", "SYNC~1", "CHRON~1", "TEMPO~1", "PHASE~1",
        
        # Patrones con comillas
        "NEON'1", "RELAY'1", "SYNC'1", "CHRON'1", "TEMPO'1", "PHASE'1",
        
        # Patrones con comillas dobles
        'NEON"1', 'RELAY"1', 'SYNC"1', 'CHRON"1', 'TEMPO"1', 'PHASE"1',
        
        # Patrones con acentos
        "NEON`1", "RELAY`1", "SYNC`1", "CHRON`1", "TEMPO`1", "PHASE`1",
        
        # Patrones con signos de interrogación
        "NEON?1", "RELAY?1", "SYNC?1", "CHRON?1", "TEMPO?1", "PHASE?1",
        
        # Patrones con signos de exclamación
        "NEON!1", "RELAY!1", "SYNC!1", "CHRON!1", "TEMPO!1", "PHASE!1",
        
        # Patrones con signos de igual
        "NEON=1", "RELAY=1", "SYNC=1", "CHRON=1", "TEMPO=1", "PHASE=1",
        
        # Patrones con signos de más
        "NEON+1", "RELAY+1", "SYNC+1", "CHRON+1", "TEMPO+1", "PHASE+1",
        
        # Patrones con signos de menos
        "NEON-1", "RELAY-1", "SYNC-1", "CHRON-1", "TEMPO-1", "PHASE-1",
        
        # Patrones con signos de multiplicación
        "NEON*1", "RELAY*1", "SYNC*1", "CHRON*1", "TEMPO*1", "PHASE*1",
        
        # Patrones con signos de división
        "NEON/1", "RELAY/1", "SYNC/1", "CHRON/1", "TEMPO/1", "PHASE/1",
        
        # Patrones con signos de porcentaje
        "NEON%1", "RELAY%1", "SYNC%1", "CHRON%1", "TEMPO%1", "PHASE%1",
        
        # Patrones con signos de ampersand
        "NEON&1", "RELAY&1", "SYNC&1", "CHRON&1", "TEMPO&1", "PHASE&1",
        
        # Patrones con signos de hash
        "NEON#1", "RELAY#1", "SYNC#1", "CHRON#1", "TEMPO#1", "PHASE#1",
        
        # Patrones con signos de dólar
        "NEON$1", "RELAY$1", "SYNC$1", "CHRON$1", "TEMPO$1", "PHASE$1",
        
        # Patrones con signos de arroba
        "NEON@1", "RELAY@1", "SYNC@1", "CHRON@1", "TEMPO@1", "PHASE@1",
        
        # Patrones con signos de potencia
        "NEON^1", "RELAY^1", "SYNC^1", "CHRON^1", "TEMPO^1", "PHASE^1"
    ]
    
    print(f"Probando {len(extended_patterns)} patrones extendidos...")
    
    for i, pattern in enumerate(extended_patterns, 1):
        print(f"[{i:3d}/{len(extended_patterns)}] Probando: '{pattern}'", end=" ")
        
        success, output = test_handshake(pattern)
        
        if success:
            print("✓ ¡ÉXITO!")
            print(f"Handshake encontrado: '{pattern}'")
            print(f"Output: {output}")
            return pattern
        else:
            print("✗")
    
    print("\nNo se encontró el handshake con patrones extendidos.")
    return None

if __name__ == "__main__":
    # Primero búsqueda eficiente
    result = efficient_bruteforce()
    
    if result is None:
        # Si no se encuentra, probar búsqueda extendida
        result = extended_bruteforce()
    
    if result:
        print(f"\n🎉 HANDSHAKE ENCONTRADO: '{result}'")
    else:
        print("\n❌ No se pudo encontrar el handshake.")