#!/usr/bin/env python3
import subprocess

def analyze_glyph_hint():
    """Analiza la pista del glyph más profundamente"""
    
    hint = "GCoherent map the mods 5 and 3 loop."
    print(f"Pista original: {hint}")
    
    # Análisis de la pista:
    # - "GCoherent" - podría ser G + Coherent, o GC + oherent
    # - "map" - podría referirse a mapping/transformación
    # - "mods 5 and 3" - operaciones modulo 5 y 3
    # - "loop" - bucle o repetición
    
    print("\nPosibles interpretaciones:")
    print("1. GCoherent = G + Coherent (algoritmo G con coherencia)")
    print("2. GCoherent = GC + oherent (Galois Counter?)")
    print("3. Map = mapear valores usando mod 5 y 3")
    print("4. Loop = repetir el proceso")
    
    # Basándome en el análisis del const pool, veo que:
    # Pool[10] = 5, Pool[11] = 3
    # Esto sugiere que el VM usa estos valores específicos
    
    # Intentar crear payloads basados en diferentes interpretaciones
    payloads = []
    
    # Interpretación 1: Mapeo coherente usando mod 5 y 3 en loop
    payload1 = create_coherent_map_payload()
    payloads.append(("Coherent Map", payload1))
    
    # Interpretación 2: Galois Counter con mod 5 y 3
    payload2 = create_galois_counter_payload() 
    payloads.append(("Galois Counter", payload2))
    
    # Interpretación 3: Loop de transformación con mod 5 y 3
    payload3 = create_transform_loop_payload()
    payloads.append(("Transform Loop", payload3))
    
    # Interpretación 4: Programa que combine G (primera letra) con mods
    payload4 = create_g_mod_payload()
    payloads.append(("G-Mod", payload4))
    
    return payloads

def create_coherent_map_payload():
    """Crea un payload basado en mapeo coherente"""
    # Un mapeo coherente podría significar que cada valor se mapea
    # consistentemente usando mod 5 y 3
    payload = []
    for i in range(32):
        base = ord('G') + i  # Comenzar con 'G'
        mapped5 = base % 5
        mapped3 = base % 3  
        payload.extend([base & 0xFF, mapped5, mapped3, 0x00])
    
    return bytes(payload).hex()

def create_galois_counter_payload():
    """Crea un payload basado en contador Galois"""
    # GC podría ser Galois Counter
    # Un contador Galois usa feedback polynomial
    payload = []
    state = ord('G')  # Estado inicial
    
    for i in range(32):
        # Galois LFSR con feedback usando mod 5 y 3
        bit = (state ^ (state >> 1) ^ (state >> 3)) & 1
        state = (state >> 1) | (bit << 7)
        
        payload.extend([state, state % 5, state % 3, 0x00])
    
    return bytes(payload).hex()

def create_transform_loop_payload():
    """Crea un payload basado en loop de transformación"""
    payload = []
    value = ord('G')
    
    for i in range(32):
        # Transformar usando mod 5 y 3 en loop
        new_value = (value * 5 + 3) % 256
        mod5_val = new_value % 5
        mod3_val = new_value % 3
        
        payload.extend([new_value, mod5_val, mod3_val, 0x00])
        value = new_value
    
    return bytes(payload).hex()

def create_g_mod_payload():
    """Crea un payload que combine G con operaciones mod"""
    payload = []
    g_val = ord('G')  # 71
    
    for i in range(32):
        # Combinar G con índice usando mod 5 y 3
        combined = (g_val + i) % 256
        payload.extend([combined, (combined + 5) % 256, (combined + 3) % 256, 0x00])
    
    return bytes(payload).hex()

def test_payload(name, payload):
    """Prueba un payload específico"""
    print(f"\nProbando {name}: {payload[:40]}...")
    
    try:
        input_data = f"NEBULA-GHOST\ndeploy\n{payload}\n"
        result = subprocess.run([
            './ghostrace_node'
        ], input=input_data, text=True, capture_output=True, timeout=10)
        
        if "Resonance stabilized" in result.stdout:
            print(f"*** ÉXITO con {name}! ***")
            print(result.stdout)
            return True
        elif "Sequence rejected" in result.stdout:
            print(f"{name} rechazado.")
            return False
        else:
            print(f"{name} - respuesta inesperada:")
            print(result.stdout[-200:])
            return False
            
    except subprocess.TimeoutExpired:
        print(f"{name} - timeout")
        return False
    except Exception as e:
        print(f"{name} - error: {e}")
        return False

if __name__ == "__main__":
    payloads = analyze_glyph_hint()
    
    print("\n" + "="*60)
    print("PROBANDO INTERPRETACIONES DE LA PISTA...")
    print("="*60)
    
    for name, payload in payloads:
        if test_payload(name, payload):
            break