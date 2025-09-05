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

def debug_assemble_png(key, bytes_data, debug=False):
    """
    Implementar el algoritmo de desencriptación con debug
    """
    LEN = 16
    
    # Ajustar la longitud de la clave si es necesario
    if len(key) != 32:  # 32 caracteres = 16 bytes en hex
        if len(key) == 16:
            # Si es de 16 caracteres, duplicar para hacer 32
            key = key + key
        else:
            key = "00000000000000000000000000000000"
    
    if debug:
        print(f"Clave procesada: {key}")
        print(f"Longitud de bytes: {len(bytes_data)}")
    
    result = []
    
    for i in range(LEN):
        # Obtener el shifter del key (cada dígito hexadecimal)
        shifter = int(key[i*2:(i*2)+1], 16)
        
        if debug and i < 4:  # Solo debug para los primeros 4
            print(f"i={i}, shifter={shifter}")
        
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

def analyze_algorithm():
    """Analizar el algoritmo paso a paso"""
    print("=== Análisis del Algoritmo ===")
    
    bytes_data = get_bytes_data()
    if not bytes_data:
        return
    
    print(f"Total de bytes: {len(bytes_data)}")
    print(f"LEN = 16, por lo que hay {len(bytes_data) // 16} bloques")
    
    # Probar con la clave por defecto y ver qué pasa
    key = "00000000000000000000000000000000"
    result = debug_assemble_png(key, bytes_data, debug=True)
    
    print(f"Resultado con clave por defecto: {len(result)} bytes")
    print(f"Primeros 16 bytes: {result[:16]}")
    
    # Buscar patrones en los datos originales
    print("\nBuscando patrones en los datos originales...")
    
    # Buscar la secuencia PNG en diferentes posiciones
    png_sig = [137, 80, 78, 71, 13, 10, 26, 10]
    
    for offset in range(16):
        print(f"\nProbando offset {offset}:")
        for i in range(0, len(bytes_data) - 8, 16):
            chunk = bytes_data[i:i+16]
            if len(chunk) == 16:
                # Verificar si algún byte en esta posición podría ser PNG
                for j in range(16 - 8 + 1):
                    if chunk[j:j+8] == png_sig:
                        print(f"  ¡Encontrada firma PNG en posición {i+j}!")
                        print(f"  Chunk: {chunk}")
                        return i + j

def try_different_approaches():
    """Probar diferentes enfoques"""
    print("\n=== Probando Diferentes Enfoques ===")
    
    bytes_data = get_bytes_data()
    if not bytes_data:
        return
    
    # Enfoque 1: Probar claves que podrían generar la firma PNG
    print("Enfoque 1: Buscar claves que generen firma PNG")
    
    png_sig = [137, 80, 78, 71, 13, 10, 26, 10]
    
    # Probar claves sistemáticamente
    for key_val in range(256):  # 0-255
        key_hex = f"{key_val:02x}"
        key = key_hex * 16  # Repetir para hacer 32 caracteres
        
        result = debug_assemble_png(key, bytes_data)
        
        if len(result) >= 8:
            if result[:8] == bytes(png_sig):
                print(f"¡ENCONTRADA! Clave: {key}")
                filename = f"found_image_{key}.png"
                with open(filename, "wb") as f:
                    f.write(result)
                print(f"Imagen guardada: {filename}")
                return key, result
    
    # Enfoque 2: Probar claves con patrones específicos
    print("\nEnfoque 2: Patrones específicos")
    
    test_keys = [
        "0123456789abcdef0123456789abcdef",
        "fedcba9876543210fedcba9876543210",
        "11111111111111111111111111111111",
        "00000000000000000000000000000000",
        "ffffffffffffffffffffffffffffffff",
        "1234567890abcdef1234567890abcdef",
    ]
    
    for key in test_keys:
        result = debug_assemble_png(key, bytes_data)
        
        if len(result) >= 8:
            if result[:8] == bytes(png_sig):
                print(f"¡ENCONTRADA! Clave: {key}")
                filename = f"found_image_{key}.png"
                with open(filename, "wb") as f:
                    f.write(result)
                print(f"Imagen guardada: {filename}")
                return key, result
    
    return None, None

def try_reverse_engineering():
    """Intentar ingeniería inversa del algoritmo"""
    print("\n=== Ingeniería Inversa ===")
    
    bytes_data = get_bytes_data()
    if not bytes_data:
        return
    
    # La firma PNG debe estar en alguna posición específica
    png_sig = [137, 80, 78, 71, 13, 10, 26, 10]
    
    print("Buscando dónde podría estar la firma PNG en los datos originales...")
    
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
                        result = debug_assemble_png(key, bytes_data)
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
    print("=== JavaScript Kiddie 2 Debug Solver ===")
    
    # Análisis del algoritmo
    analyze_algorithm()
    
    # Probar diferentes enfoques
    key, image_data = try_different_approaches()
    
    if not key:
        # Intentar ingeniería inversa
        key, image_data = try_reverse_engineering()
    
    if key and image_data:
        print(f"\n¡ÉXITO! Clave encontrada: {key}")
        print("La imagen debería contener la flag.")
    else:
        print("\nNo se pudo resolver automáticamente.")
        print("Puede que necesites un enfoque completamente diferente.")