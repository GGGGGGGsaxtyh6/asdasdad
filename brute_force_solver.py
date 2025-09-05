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

def assemble_png(key, bytes_data):
    """
    Implementar el algoritmo de desencriptación basado en el JavaScript
    """
    LEN = 16
    
    # Ajustar la longitud de la clave si es necesario
    if len(key) != 32:  # 32 caracteres = 16 bytes en hex
        if len(key) == 16:
            # Si es de 16 caracteres, duplicar para hacer 32
            key = key + key
        else:
            key = "00000000000000000000000000000000"
    
    result = []
    
    for i in range(LEN):
        # Obtener el shifter del key (cada dígito hexadecimal)
        shifter = int(key[i*2:(i*2)+1], 16)
        
        for j in range(len(bytes_data) // LEN):
            # Calcular el índice usando la fórmula del JavaScript
            source_index = ((j + shifter) * LEN) % len(bytes_data) + i
            result_index = (j * LEN) + i
            
            if source_index < len(bytes_data) and result_index < len(bytes_data):
                result.append(bytes_data[source_index])
    
    # Remover ceros al final
    while result and result[-1] == 0:
        result.pop()
    
    return bytes(result)

def brute_force_all_single_digits():
    """Fuerza bruta con todas las claves de un solo dígito repetido"""
    print("=== Fuerza Bruta - Un Solo Dígito ===")
    
    bytes_data = get_bytes_data()
    if not bytes_data:
        return None
    
    png_sig = [137, 80, 78, 71, 13, 10, 26, 10]
    
    # Probar cada dígito hexadecimal (0-f) repetido 16 veces
    for digit in "0123456789abcdef":
        key = digit * 16
        print(f"Probando clave: {key}")
        
        try:
            result = assemble_png(key, bytes_data)
            
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

def brute_force_two_digits():
    """Fuerza bruta con claves de dos dígitos diferentes"""
    print("\n=== Fuerza Bruta - Dos Dígitos ===")
    
    bytes_data = get_bytes_data()
    if not bytes_data:
        return None
    
    png_sig = [137, 80, 78, 71, 13, 10, 26, 10]
    
    # Probar combinaciones de dos dígitos
    for d1 in "0123456789abcdef":
        for d2 in "0123456789abcdef":
            # Patrón: d1d1d1d1d1d1d1d1d2d2d2d2d2d2d2d2
            key = d1 * 8 + d2 * 8
            print(f"Probando clave: {key}")
            
            try:
                result = assemble_png(key, bytes_data)
                
                if len(result) >= 8:
                    if result[:8] == bytes(png_sig):
                        print(f"\n¡ENCONTRADA IMAGEN PNG VÁLIDA!")
                        print(f"Clave: {key}")
                        
                        filename = f"found_image_{key}.png"
                        with open(filename, "wb") as f:
                            f.write(result)
                        print(f"Imagen guardada: {filename}")
                        
                        return key, result
                    else:
                        print(f"  No es PNG válido: {result[:8]}")
                else:
                    print(f"  Resultado muy corto: {len(result)} bytes")
                    
            except Exception as e:
                print(f"  Error: {e}")
    
    return None, None

def try_specific_patterns():
    """Probar patrones específicos que podrían funcionar"""
    print("\n=== Patrones Específicos ===")
    
    bytes_data = get_bytes_data()
    if not bytes_data:
        return None
    
    png_sig = [137, 80, 78, 71, 13, 10, 26, 10]
    
    # Patrones específicos que podrían funcionar
    patterns = [
        "0123456789abcdef",  # Secuencia
        "fedcba9876543210",  # Secuencia reversa
        "1111111111111111",  # Todos 1s
        "0000000000000000",  # Todos 0s
        "ffffffffffffffff",  # Todos fs
        "1234567890abcdef",  # Otra secuencia
        "abcdef1234567890",  # Secuencia mezclada
        "0123456789abcdef0123456789abcdef",  # 32 caracteres
        "fedcba9876543210fedcba9876543210",  # 32 caracteres reversa
    ]
    
    for pattern in patterns:
        print(f"Probando patrón: {pattern}")
        
        try:
            result = assemble_png(pattern, bytes_data)
            
            if len(result) >= 8:
                if result[:8] == bytes(png_sig):
                    print(f"\n¡ENCONTRADA IMAGEN PNG VÁLIDA!")
                    print(f"Clave: {pattern}")
                    
                    filename = f"found_image_{pattern}.png"
                    with open(filename, "wb") as f:
                        f.write(result)
                    print(f"Imagen guardada: {filename}")
                    
                    return pattern, result
                else:
                    print(f"  No es PNG válido: {result[:8]}")
            else:
                print(f"  Resultado muy corto: {len(result)} bytes")
                
        except Exception as e:
            print(f"  Error: {e}")
    
    return None, None

def analyze_data_structure():
    """Analizar la estructura de los datos más profundamente"""
    print("\n=== Análisis de Estructura ===")
    
    bytes_data = get_bytes_data()
    if not bytes_data:
        return
    
    print(f"Total de bytes: {len(bytes_data)}")
    print(f"Divisible por 16: {len(bytes_data) % 16 == 0}")
    print(f"Número de bloques de 16: {len(bytes_data) // 16}")
    
    # Mostrar los primeros bloques
    print("\nPrimeros 5 bloques de 16 bytes:")
    for i in range(min(5, len(bytes_data) // 16)):
        start = i * 16
        end = start + 16
        block = bytes_data[start:end]
        print(f"Bloque {i}: {block}")
    
    # Buscar patrones en los datos
    print("\nBuscando patrones...")
    
    # Buscar la secuencia PNG en diferentes posiciones
    png_sig = [137, 80, 78, 71, 13, 10, 26, 10]
    
    for i in range(len(bytes_data) - 8):
        if bytes_data[i:i+8] == png_sig:
            print(f"¡Firma PNG encontrada en posición {i}!")
            print(f"Bloque: {i // 16}, Posición en bloque: {i % 16}")
            return i
    
    print("No se encontró la firma PNG en los datos originales")
    return None

if __name__ == "__main__":
    print("=== JavaScript Kiddie 2 Brute Force Solver ===")
    
    # Analizar estructura
    analyze_data_structure()
    
    # Fuerza bruta con un solo dígito
    key, image_data = brute_force_all_single_digits()
    
    if not key:
        # Fuerza bruta con dos dígitos
        key, image_data = brute_force_two_digits()
    
    if not key:
        # Probar patrones específicos
        key, image_data = try_specific_patterns()
    
    if key and image_data:
        print(f"\n¡ÉXITO! Clave encontrada: {key}")
        print("La imagen debería contener la flag.")
    else:
        print("\nNo se pudo resolver automáticamente.")
        print("Puede que necesites un enfoque completamente diferente.")