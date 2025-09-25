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
            timeout=5
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

def smart_bruteforce():
    """Búsqueda inteligente del handshake"""
    
    print("=== BÚSQUEDA INTELIGENTE DEL HANDSHAKE ===")
    print()
    
    # Lista de palabras relacionadas con el tema
    theme_words = [
        "NEON", "RELAY", "SYNC", "CHRON", "TEMPO", "PHASE", "LOCK", "KEY",
        "CODE", "AUTH", "LOGIN", "ACCESS", "SECRET", "TOKEN", "FLAG", "CRONO",
        "TEMPOR", "PHASER", "CHRONO", "TEMPORAL", "PHASIC", "NEONIC", "RELAYER"
    ]
    
    # Caracteres comunes para rellenar
    fill_chars = "0123456789!@#$%^&*()_+-=[]{}|;:,.<>?"
    
    # Generar combinaciones de 6 caracteres
    candidates = []
    
    # 1. Palabras de 6 caracteres exactos
    for word in theme_words:
        if len(word) == 6:
            candidates.append(word)
    
    # 2. Palabras + números
    for word in theme_words:
        if len(word) < 6:
            remaining = 6 - len(word)
            for fill in itertools.product(fill_chars, repeat=remaining):
                candidates.append(word + ''.join(fill))
    
    # 3. Combinaciones de caracteres relacionados
    related_chars = "NEONRELAYCHRONTEMPO"
    for combo in itertools.product(related_chars, repeat=6):
        candidates.append(''.join(combo))
    
    # 4. Patrones comunes
    patterns = [
        "NEON01", "RELAY1", "SYNC01", "CHRON1", "TEMPO1", "PHASE1",
        "LOCK01", "KEY001", "CODE01", "AUTH01", "LOGIN1", "ACCESS",
        "SECRET", "TOKEN1", "FLAG01", "CRONO1", "TEMPOR", "PHASER"
    ]
    candidates.extend(patterns)
    
    # Eliminar duplicados
    candidates = list(set(candidates))
    
    print(f"Probando {len(candidates)} candidatos...")
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
        if count % 10 == 0:
            time.sleep(0.1)
    
    print("\nNo se encontró el handshake con la búsqueda inteligente.")
    return None

def extended_bruteforce():
    """Búsqueda extendida con más patrones"""
    
    print("\n=== BÚSQUEDA EXTENDIDA ===")
    
    # Patrones más específicos
    extended_patterns = [
        # Patrones numéricos
        "000000", "111111", "123456", "654321", "000001", "000010",
        
        # Patrones de letras
        "AAAAAA", "BBBBBB", "CCCCCC", "DDDDDD", "EEEEEE", "FFFFFF",
        
        # Patrones mixtos
        "A1B2C3", "1A2B3C", "ABC123", "123ABC", "A1A1A1", "1A1A1A",
        
        # Patrones relacionados con el tema
        "NEON01", "NEON02", "NEON03", "RELAY1", "RELAY2", "RELAY3",
        "SYNC01", "SYNC02", "SYNC03", "CHRON1", "CHRON2", "CHRON3",
        "TEMPO1", "TEMPO2", "TEMPO3", "PHASE1", "PHASE2", "PHASE3",
        
        # Patrones de caracteres especiales
        "NEON!!", "RELAY!", "SYNC!!", "CHRON!", "TEMPO!", "PHASE!",
        "NEON**", "RELAY*", "SYNC**", "CHRON*", "TEMPO*", "PHASE*",
        
        # Patrones hexadecimales
        "DEADBE", "CAFEBE", "BEEFCA", "FEEDBA", "BABECA", "CABEBA"
    ]
    
    print(f"Probando {len(extended_patterns)} patrones extendidos...")
    
    for i, pattern in enumerate(extended_patterns, 1):
        print(f"[{i:2d}/{len(extended_patterns)}] Probando: '{pattern}'", end=" ")
        
        success, output = test_handshake(pattern)
        
        if success:
            print("✓ ¡ÉXITO!")
            print(f"Handshake encontrado: '{pattern}'")
            print(f"Output: {output}")
            return pattern
        else:
            print("✗")
    
    print("\nNo se encontró el handshake con la búsqueda extendida.")
    return None

if __name__ == "__main__":
    # Primero búsqueda inteligente
    result = smart_bruteforce()
    
    if result is None:
        # Si no se encuentra, probar búsqueda extendida
        result = extended_bruteforce()
    
    if result:
        print(f"\n🎉 HANDSHAKE ENCONTRADO: '{result}'")
    else:
        print("\n❌ No se pudo encontrar el handshake.")