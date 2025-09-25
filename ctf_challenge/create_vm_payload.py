#!/usr/bin/env python3

def create_vm_payload():
    """Crea un payload válido para la VM basado en el análisis del programa real"""
    
    with open('ghostrace_node', 'rb') as f:
        data = f.read()
    
    # Extraer el programa real
    program_stream = data[0x49a0:0x49a0+0x124]
    
    print("Analizando programa VM real...")
    print(f"Program Stream hex: {program_stream[:40].hex()}")
    
    # Analizar el patrón del programa
    # Basándome en la pista "GCoherent map the mods 5 and 3 loop"
    # "GCoherent" podría referirse a "G-Coherent" o "GC-oherent" 
    # Esto sugiere un algoritmo específico
    
    # Intentar diferentes enfoques para el payload:
    
    # Enfoque 1: Usar el programa stream directamente (primeros bytes)
    payload1 = program_stream[:32].hex()
    print(f"Payload 1 (primeros 32 bytes): {payload1}")
    
    # Enfoque 2: Crear un programa que use mod 5 y 3 de manera coherente
    payload2_bytes = []
    for i in range(16):
        # Opcode que podría representar operaciones mod
        payload2_bytes.extend([0x05, i % 5, i % 3, 0x00])
    payload2 = bytes(payload2_bytes).hex()
    print(f"Payload 2 (mod 5,3 pattern): {payload2}")
    
    # Enfoque 3: Basado en const pool values (usar índices 10 y 11 que son 5 y 3)
    payload3_bytes = []
    for i in range(16):
        # Usar índices del const pool 
        payload3_bytes.extend([0x0a, 0x0b, i % 8, 0x00])  # 0x0a=10, 0x0b=11 (pool indices for 5,3)
    payload3 = bytes(payload3_bytes).hex()
    print(f"Payload 3 (const pool indices): {payload3}")
    
    # Enfoque 4: Pattern basado en "coherent"
    # Coherent podría significar una secuencia matemática específica
    payload4_bytes = []
    for i in range(16):
        val = (i * 5 + 3) % 256  # Combinación de 5 y 3
        payload4_bytes.extend([val, (val + 1) % 5, (val + 2) % 3, 0x00])
    payload4 = bytes(payload4_bytes).hex()
    print(f"Payload 4 (coherent math): {payload4}")
    
    # Enfoque 5: Usar los primeros bytes del programa decodificado
    program_salt = data[0x4ac8:0x4ac8+0x9]
    encoded_program = data[0x4ae0:0x4ae0+0x124]
    extended_salt = (program_salt * (len(encoded_program) // len(program_salt) + 1))[:len(encoded_program)]
    decoded_program = bytes(a ^ b for a, b in zip(encoded_program, extended_salt))
    payload5 = decoded_program[:32].hex()
    print(f"Payload 5 (decoded program): {payload5}")
    
    return [payload1, payload2, payload3, payload4, payload5]

def test_payloads(payloads):
    """Prueba los payloads generados"""
    import subprocess
    
    for i, payload in enumerate(payloads, 1):
        print(f"\nProbando payload {i}: {payload[:40]}...")
        
        try:
            input_data = f"NEBULA-GHOST\ndeploy\n{payload}\n"
            result = subprocess.run([
                './ghostrace_node'
            ], input=input_data, text=True, capture_output=True, timeout=10)
            
            if "Resonance stabilized" in result.stdout:
                print(f"*** ÉXITO con payload {i}! ***")
                print(result.stdout)
                return payload
            elif "Sequence rejected" in result.stdout:
                print(f"Payload {i} rechazado.")
            else:
                print(f"Payload {i} - respuesta inesperada:")
                print(result.stdout[-200:])  # Últimos 200 chars
                
        except subprocess.TimeoutExpired:
            print(f"Payload {i} - timeout")
        except Exception as e:
            print(f"Payload {i} - error: {e}")
    
    return None

if __name__ == "__main__":
    payloads = create_vm_payload()
    print("\n" + "="*60)
    print("PROBANDO PAYLOADS...")
    print("="*60)
    successful_payload = test_payloads(payloads)