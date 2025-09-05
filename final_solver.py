#!/usr/bin/env python3

import requests
import base64
from PIL import Image
import io

def get_bytes_data():
    """Obtener los datos de bytes del servidor"""
    url = "http://jupiter.challenges.picoctf.org:51400/bytes"
    response = requests.get(url)
    if response.status_code == 200:
        return [int(x) for x in response.text.split()]
    return None

def assemble_png_correct(key, bytes_data):
    """
    Implementar el algoritmo de desencriptación EXACTAMENTE como en JavaScript
    """
    LEN = 16
    
    # Asegurar que la clave tenga 32 caracteres
    if len(key) != 32:
        if len(key) == 16:
            key = key + key
        else:
            key = "00000000000000000000000000000000"
    
    result = []
    
    for i in range(LEN):
        # Obtener el shifter del key (cada dígito hexadecimal)
        # JavaScript: key.slice((i*2),(i*2)+1) - toma UN solo carácter
        shifter = int(key[i*2:(i*2)+1], 16)
        
        for j in range(len(bytes_data) // LEN):
            # Calcular el índice usando la fórmula del JavaScript
            # JavaScript: ((j + shifter) * LEN) % bytes.length + i
            source_index = ((j + shifter) * LEN) % len(bytes_data) + i
            result_index = (j * LEN) + i
            
            if source_index < len(bytes_data) and result_index < len(bytes_data):
                result.append(bytes_data[source_index])
    
    # Remover ceros al final
    while result and result[-1] == 0:
        result.pop()
    
    return bytes(result)

def try_all_single_hex_digits():
    """Probar todas las claves de un solo dígito hexadecimal repetido"""
    print("=== Probando Todas las Claves de Un Solo Dígito ===")
    
    bytes_data = get_bytes_data()
    if not bytes_data:
        return None
    
    png_sig = [137, 80, 78, 71, 13, 10, 26, 10]
    
    for digit in "0123456789abcdef":
        key = digit * 16
        print(f"Probando clave: {key}")
        
        try:
            result = assemble_png_correct(key, bytes_data)
            
            if len(result) >= 8:
                if result[:8] == bytes(png_sig):
                    print(f"\n¡ENCONTRADA IMAGEN PNG VÁLIDA!")
                    print(f"Clave: {key}")
                    
                    filename = f"found_image_{key}.png"
                    with open(filename, "wb") as f:
                        f.write(result)
                    print(f"Imagen guardada: {filename}")
                    
                    # Mostrar información de la imagen
                    try:
                        img = Image.open(io.BytesIO(result))
                        print(f"Dimensiones: {img.size}")
                        print(f"Modo: {img.mode}")
                        return key, result
                    except Exception as e:
                        print(f"Error al procesar imagen: {e}")
                        return key, result
                else:
                    print(f"  No es PNG válido: {result[:8]}")
            else:
                print(f"  Resultado muy corto: {len(result)} bytes")
                
        except Exception as e:
            print(f"  Error: {e}")
    
    return None, None

def try_brute_force_limited():
    """Fuerza bruta limitada con claves más complejas"""
    print("\n=== Fuerza Bruta Limitada ===")
    
    bytes_data = get_bytes_data()
    if not bytes_data:
        return None
    
    png_sig = [137, 80, 78, 71, 13, 10, 26, 10]
    
    # Probar claves con patrones específicos
    test_keys = []
    
    # Claves con un solo dígito diferente
    for i in range(16):
        for digit in "0123456789abcdef":
            key = "0" * i + digit + "0" * (15 - i)
            test_keys.append(key)
    
    # Claves con patrones específicos
    test_keys.extend([
        "0123456789abcdef",
        "fedcba9876543210",
        "1111111111111111",
        "0000000000000000",
        "ffffffffffffffff",
        "1234567890abcdef",
        "abcdef1234567890",
        "0123456789abcdef0123456789abcdef",
        "fedcba9876543210fedcba9876543210",
    ])
    
    print(f"Probando {len(test_keys)} claves...")
    
    for i, key in enumerate(test_keys):
        if i % 50 == 0:
            print(f"Progreso: {i}/{len(test_keys)}")
            
        try:
            result = assemble_png_correct(key, bytes_data)
            
            if len(result) >= 8:
                if result[:8] == bytes(png_sig):
                    print(f"\n¡ENCONTRADA IMAGEN PNG VÁLIDA!")
                    print(f"Clave: {key}")
                    
                    filename = f"found_image_{key}.png"
                    with open(filename, "wb") as f:
                        f.write(result)
                    print(f"Imagen guardada: {filename}")
                    
                    return key, result
                    
        except Exception as e:
            continue
    
    return None, None

def analyze_algorithm_deeply():
    """Análisis profundo del algoritmo"""
    print("\n=== Análisis Profundo del Algoritmo ===")
    
    bytes_data = get_bytes_data()
    if not bytes_data:
        return
    
    print(f"Total de bytes: {len(bytes_data)}")
    print(f"Bloques de 16: {len(bytes_data) // 16}")
    
    # Analizar el algoritmo paso a paso con la clave por defecto
    key = "00000000000000000000000000000000"
    print(f"\nAnalizando con clave: {key}")
    
    result = []
    LEN = 16
    
    for i in range(LEN):
        shifter = int(key[i*2:(i*2)+1], 16)
        print(f"i={i}, shifter={shifter}")
        
        for j in range(min(3, len(bytes_data) // LEN)):  # Solo primeros 3 bloques
            source_index = ((j + shifter) * LEN) % len(bytes_data) + i
            result_index = (j * LEN) + i
            
            if source_index < len(bytes_data) and result_index < len(bytes_data):
                result.append(bytes_data[source_index])
                print(f"  j={j}: source_index={source_index}, result_index={result_index}, value={bytes_data[source_index]}")
    
    print(f"Primeros 16 bytes del resultado: {result[:16]}")
    
    # Buscar patrones en los datos originales
    print("\nBuscando patrones en los datos originales...")
    
    # Buscar la secuencia PNG en diferentes posiciones
    png_sig = [137, 80, 78, 71, 13, 10, 26, 10]
    
    for i in range(len(bytes_data) - 8):
        if bytes_data[i:i+8] == png_sig:
            print(f"¡Firma PNG encontrada en posición {i}!")
            print(f"Bloque: {i // 16}, Posición en bloque: {i % 16}")
            return i
    
    print("No se encontró la firma PNG en los datos originales")
    return None

def try_reverse_engineering_approach():
    """Enfoque de ingeniería inversa"""
    print("\n=== Enfoque de Ingeniería Inversa ===")
    
    bytes_data = get_bytes_data()
    if not bytes_data:
        return None
    
    # La firma PNG debe estar en alguna posición específica
    png_sig = [137, 80, 78, 71, 13, 10, 26, 10]
    
    print("Buscando dónde podría estar la firma PNG...")
    
    # Buscar la firma en los datos originales
    for i in range(len(bytes_data) - 8):
        if bytes_data[i:i+8] == png_sig:
            print(f"¡Firma PNG encontrada en posición {i}!")
            
            # Calcular qué clave podría generar esto
            # La posición i debe corresponder a algún resultado_index
            # resultado_index = (j * LEN) + i % LEN
            # Necesitamos encontrar j y el shifter correspondiente
            
            for j in range(len(bytes_data) // 16):
                for shift in range(16):
                    # Calcular source_index
                    source_index = ((j + shift) * 16) % len(bytes_data) + (i % 16)
                    
                    if source_index == i:
                        print(f"Posible solución: j={j}, shift={shift}, posición={i}")
                        
                        # Construir la clave
                        key = "0" * 32
                        key_list = list(key)
                        key_list[(i % 16) * 2] = f"{shift:x}"
                        key = "".join(key_list)
                        
                        print(f"Clave sugerida: {key}")
                        
                        # Probar esta clave
                        result = assemble_png_correct(key, bytes_data)
                        if len(result) >= 8 and result[:8] == bytes(png_sig):
                            print(f"¡FUNCIONA! Clave: {key}")
                            filename = f"reverse_engineered_{key}.png"
                            with open(filename, "wb") as f:
                                f.write(result)
                            print(f"Imagen guardada: {filename}")
                            return key, result
    
    print("No se encontró la firma PNG en los datos originales")
    return None, None

if __name__ == "__main__":
    print("=== JavaScript Kiddie 2 Final Solver ===")
    
    # Análisis profundo del algoritmo
    analyze_algorithm_deeply()
    
    # Probar todas las claves de un solo dígito
    key, image_data = try_all_single_hex_digits()
    
    if not key:
        # Fuerza bruta limitada
        key, image_data = try_brute_force_limited()
    
    if not key:
        # Enfoque de ingeniería inversa
        key, image_data = try_reverse_engineering_approach()
    
    if key and image_data:
        print(f"\n¡ÉXITO! Clave encontrada: {key}")
        print("La imagen debería contener la flag.")
    else:
        print("\nNo se pudo resolver automáticamente.")
        print("Puede que necesites un enfoque completamente diferente.")