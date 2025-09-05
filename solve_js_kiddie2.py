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
        # El JavaScript usa key.slice((i*2),(i*2)+1) que toma un solo carácter
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

def try_brute_force():
    """Intentar diferentes claves para encontrar la imagen correcta"""
    print("Obteniendo datos de bytes...")
    bytes_data = get_bytes_data()
    
    if not bytes_data:
        print("Error: No se pudieron obtener los datos de bytes")
        return
    
    print(f"Datos obtenidos: {len(bytes_data)} bytes")
    
    # Intentar diferentes claves
    # Empezar con claves simples y patrones comunes
    test_keys = [
        "00000000000000000000000000000000",  # Clave por defecto
        "11111111111111111111111111111111",  # Todos 1s
        "0123456789abcdef0123456789abcdef",  # Secuencia
        "fedcba9876543210fedcba9876543210",  # Secuencia reversa
        "00000000000000000000000000000001",  # Solo el último dígito diferente
        "0000000000000000000000000000000f",  # Solo el último dígito diferente
        "00000000000000000000000000000010",  # Solo el último dígito diferente
    ]
    
    # También intentar con claves de 16 caracteres (no 32)
    test_keys.extend([
        "0000000000000000",
        "1111111111111111", 
        "0123456789abcdef",
        "fedcba9876543210",
        "0000000000000001",
        "000000000000000f",
        "0000000000000010",
    ])
    
    for key in test_keys:
        print(f"\nProbando clave: {key}")
        try:
            result_bytes = assemble_png(key, bytes_data)
            
            # Verificar si los primeros bytes parecen un PNG válido
            if len(result_bytes) >= 8:
                png_header = result_bytes[:8]
                if png_header == b'\x89PNG\r\n\x1a\n':
                    print(f"¡Encontrada imagen PNG válida con clave: {key}")
                    
                    # Guardar la imagen
                    with open(f"decoded_image_{key}.png", "wb") as f:
                        f.write(result_bytes)
                    print(f"Imagen guardada como: decoded_image_{key}.png")
                    
                    # Intentar mostrar información de la imagen
                    try:
                        img = Image.open(io.BytesIO(result_bytes))
                        print(f"Dimensiones de la imagen: {img.size}")
                        print(f"Modo de la imagen: {img.mode}")
                        return key, result_bytes
                    except Exception as e:
                        print(f"Error al procesar imagen: {e}")
                else:
                    print(f"Los primeros bytes no son PNG válido: {png_header}")
            else:
                print(f"Resultado muy corto: {len(result_bytes)} bytes")
                
        except Exception as e:
            print(f"Error procesando clave {key}: {e}")
    
    print("\nNo se encontró una imagen PNG válida con las claves probadas")
    return None, None

def analyze_bytes_pattern():
    """Analizar el patrón de los bytes para encontrar pistas"""
    print("Analizando patrón de bytes...")
    bytes_data = get_bytes_data()
    
    if not bytes_data:
        return
    
    print(f"Total de bytes: {len(bytes_data)}")
    print(f"Primeros 32 bytes: {bytes_data[:32]}")
    print(f"Últimos 32 bytes: {bytes_data[-32:]}")
    
    # Buscar patrones PNG
    png_signature = [137, 80, 78, 71, 13, 10, 26, 10]  # \x89PNG\r\n\x1a\n
    for i in range(len(bytes_data) - 8):
        if bytes_data[i:i+8] == png_signature:
            print(f"¡Encontrada firma PNG en posición {i}!")
            return i

if __name__ == "__main__":
    print("=== JavaScript Kiddie 2 Solver ===")
    
    # Primero analizar los bytes
    png_pos = analyze_bytes_pattern()
    
    # Intentar fuerza bruta
    key, image_data = try_brute_force()
    
    if key and image_data:
        print(f"\n¡Éxito! Clave encontrada: {key}")
        print("La imagen debería contener la flag.")
    else:
        print("\nNo se pudo resolver automáticamente.")
        print("Puede que necesites analizar más a fondo el algoritmo.")