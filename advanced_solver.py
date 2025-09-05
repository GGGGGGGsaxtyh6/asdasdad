#!/usr/bin/env python3

import requests
import base64
from PIL import Image
import io
import itertools

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

def generate_keys():
    """Generar claves para probar"""
    keys = []
    
    # Claves simples
    for i in range(16):
        key = "0" * i + "1" + "0" * (15 - i)
        keys.append(key)
    
    # Claves con diferentes valores
    for val in "0123456789abcdef":
        key = val * 16
        keys.append(key)
    
    # Claves con patrones
    keys.extend([
        "0123456789abcdef",
        "fedcba9876543210", 
        "1111111111111111",
        "0000000000000000",
        "ffffffffffffffff",
        "1234567890abcdef",
        "abcdef1234567890",
    ])
    
    # Claves de 32 caracteres
    for key in keys[:]:
        keys.append(key + key)
    
    return keys

def analyze_png_structure():
    """Analizar la estructura de un PNG para entender mejor el problema"""
    print("Analizando estructura PNG...")
    
    # PNG signature: 89 50 4E 47 0D 0A 1A 0A
    png_sig = [137, 80, 78, 71, 13, 10, 26, 10]
    print(f"Firma PNG esperada: {png_sig}")
    
    bytes_data = get_bytes_data()
    if not bytes_data:
        return
    
    print(f"Total de bytes: {len(bytes_data)}")
    
    # Buscar la firma PNG en los datos originales
    for i in range(len(bytes_data) - 8):
        if bytes_data[i:i+8] == png_sig:
            print(f"¡Encontrada firma PNG en posición {i}!")
            return i
    
    print("No se encontró la firma PNG en los datos originales")
    return None

def try_systematic_approach():
    """Enfoque sistemático para encontrar la clave"""
    print("=== Enfoque Sistemático ===")
    
    bytes_data = get_bytes_data()
    if not bytes_data:
        return None
    
    print(f"Datos obtenidos: {len(bytes_data)} bytes")
    
    # Generar claves para probar
    keys = generate_keys()
    print(f"Probando {len(keys)} claves...")
    
    for i, key in enumerate(keys):
        if i % 10 == 0:
            print(f"Progreso: {i}/{len(keys)}")
            
        try:
            result_bytes = assemble_png(key, bytes_data)
            
            # Verificar si los primeros bytes parecen un PNG válido
            if len(result_bytes) >= 8:
                png_header = result_bytes[:8]
                if png_header == b'\x89PNG\r\n\x1a\n':
                    print(f"\n¡ENCONTRADA IMAGEN PNG VÁLIDA!")
                    print(f"Clave: {key}")
                    
                    # Guardar la imagen
                    filename = f"decoded_image_{key}.png"
                    with open(filename, "wb") as f:
                        f.write(result_bytes)
                    print(f"Imagen guardada como: {filename}")
                    
                    # Mostrar información de la imagen
                    try:
                        img = Image.open(io.BytesIO(result_bytes))
                        print(f"Dimensiones: {img.size}")
                        print(f"Modo: {img.mode}")
                        
                        # Intentar leer texto de la imagen
                        print("Contenido de la imagen:")
                        img.show() if hasattr(img, 'show') else print("No se puede mostrar la imagen")
                        
                        return key, result_bytes
                    except Exception as e:
                        print(f"Error al procesar imagen: {e}")
                        return key, result_bytes
                        
        except Exception as e:
            print(f"Error con clave {key}: {e}")
    
    print("\nNo se encontró una imagen PNG válida")
    return None, None

def try_brute_force_small():
    """Fuerza bruta con claves más pequeñas"""
    print("=== Fuerza Bruta Pequeña ===")
    
    bytes_data = get_bytes_data()
    if not bytes_data:
        return None
    
    # Probar todas las combinaciones de 4 dígitos hexadecimales
    print("Probando todas las combinaciones de 4 dígitos...")
    
    for combo in itertools.product("0123456789abcdef", repeat=4):
        key = "".join(combo) + "0" * 12  # Completar con ceros
        try:
            result_bytes = assemble_png(key, bytes_data)
            
            if len(result_bytes) >= 8:
                png_header = result_bytes[:8]
                if png_header == b'\x89PNG\r\n\x1a\n':
                    print(f"\n¡ENCONTRADA IMAGEN PNG VÁLIDA!")
                    print(f"Clave: {key}")
                    
                    filename = f"decoded_image_{key}.png"
                    with open(filename, "wb") as f:
                        f.write(result_bytes)
                    print(f"Imagen guardada como: {filename}")
                    
                    return key, result_bytes
                    
        except Exception as e:
            continue
    
    print("No se encontró con fuerza bruta pequeña")
    return None, None

if __name__ == "__main__":
    print("=== JavaScript Kiddie 2 Advanced Solver ===")
    
    # Analizar estructura PNG
    png_pos = analyze_png_structure()
    
    # Enfoque sistemático
    key, image_data = try_systematic_approach()
    
    if not key:
        # Si no funciona, intentar fuerza bruta pequeña
        key, image_data = try_brute_force_small()
    
    if key and image_data:
        print(f"\n¡ÉXITO! Clave encontrada: {key}")
        print("La imagen debería contener la flag.")
    else:
        print("\nNo se pudo resolver automáticamente.")
        print("Puede que necesites un enfoque diferente.")