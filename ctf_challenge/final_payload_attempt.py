#!/usr/bin/env python3
import subprocess

def create_final_payloads():
    """Crea payloads finales basados en el programa real de la VM"""
    
    with open('ghostrace_node', 'rb') as f:
        data = f.read()
    
    # Extraer datos
    program_stream = data[0x49a0:0x49a0+0x124]
    encoded_program = data[0x4ae0:0x4ae0+0x124]
    program_salt = data[0x4ac8:0x4ac8+0x9]
    
    payloads = []
    
    # 1. Usar el program_stream completo
    payloads.append(("Program Stream Full", program_stream.hex()))
    
    # 2. Usar el encoded_program completo  
    payloads.append(("Encoded Program Full", encoded_program.hex()))
    
    # 3. Decodificar el encoded_program con salt
    extended_salt = (program_salt * (len(encoded_program) // len(program_salt) + 1))[:len(encoded_program)]
    decoded_program = bytes(a ^ b for a, b in zip(encoded_program, extended_salt))
    payloads.append(("Decoded Program Full", decoded_program.hex()))
    
    # 4. Solo los primeros N bytes del program_stream
    for n in [16, 32, 64, 128]:
        payloads.append((f"Program Stream {n}B", program_stream[:n].hex()))
    
    # 5. Intentar usar solo el salt
    payloads.append(("Salt Only", program_salt.hex()))
    
    # 6. Salt repetido
    salt_repeated = (program_salt * 16)[:128]
    payloads.append(("Salt Repeated", salt_repeated.hex()))
    
    # 7. Basado en el const pool - crear un programa que use los valores
    const_pool = data[0x4c20:0x4c20+0x88]
    simple_program = []
    # Crear operaciones simples usando const pool indices
    for i in range(32):
        simple_program.extend([i % 17, (i+1) % 17, (i+2) % 17, 0x00])  # 17 valores en const pool
    
    payloads.append(("Const Pool Program", bytes(simple_program).hex()))
    
    return payloads

def test_all_payloads():
    """Prueba todos los payloads generados"""
    payloads = create_final_payloads()
    
    print(f"Generados {len(payloads)} payloads para probar...")
    
    for i, (name, payload) in enumerate(payloads, 1):
        print(f"\n[{i}/{len(payloads)}] Probando {name}...")
        print(f"  Payload length: {len(payload)} chars ({len(payload)//2} bytes)")
        print(f"  Preview: {payload[:60]}...")
        
        try:
            input_data = f"NEBULA-GHOST\ndeploy\n{payload}\n"
            result = subprocess.run([
                './ghostrace_node'
            ], input=input_data, text=True, capture_output=True, timeout=15)
            
            if "Resonance stabilized" in result.stdout:
                print(f"*** ÉXITO CON {name}! ***")
                print("Salida completa:")
                print(result.stdout)
                return payload
            elif "Sequence rejected" in result.stdout:
                print(f"  Resultado: RECHAZADO")
            elif "Flag core materialized" in result.stdout:
                print(f"*** FLAG ENCONTRADA CON {name}! ***")
                print("Salida completa:")
                print(result.stdout)
                return payload
            else:
                print(f"  Resultado: INESPERADO")
                print(f"  Output: {result.stdout[-100:]}")
                
        except subprocess.TimeoutExpired:
            print(f"  Resultado: TIMEOUT")
        except Exception as e:
            print(f"  Resultado: ERROR - {e}")
    
    print("\nNingún payload fue exitoso.")
    return None

if __name__ == "__main__":
    print("INTENTO FINAL DE PAYLOADS")
    print("=" * 50)
    successful_payload = test_all_payloads()
    
    if successful_payload:
        print(f"\nPayload exitoso encontrado: {successful_payload[:100]}...")