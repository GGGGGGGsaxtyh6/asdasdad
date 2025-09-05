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

def assemble_png_exact(key, bytes_data):
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

def try_comprehensive_brute_force():
    """Fuerza bruta comprehensiva"""
    print("=== Fuerza Bruta Comprehensiva ===")
    
    bytes_data = get_bytes_data()
    if not bytes_data:
        return None
    
    png_sig = [137, 80, 78, 71, 13, 10, 26, 10]
    
    # Probar todas las combinaciones de 2 dígitos hexadecimales
    print("Probando todas las combinaciones de 2 dígitos...")
    
    for d1 in "0123456789abcdef":
        for d2 in "0123456789abcdef":
            # Patrón: d1d1d1d1d1d1d1d1d2d2d2d2d2d2d2d2
            key = d1 * 8 + d2 * 8
            print(f"Probando clave: {key}")
            
            try:
                result = assemble_png_exact(key, bytes_data)
                
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

def try_systematic_approach():
    """Enfoque sistemático con análisis de patrones"""
    print("\n=== Enfoque Sistemático ===")
    
    bytes_data = get_bytes_data()
    if not bytes_data:
        return None
    
    png_sig = [137, 80, 78, 71, 13, 10, 26, 10]
    
    # Analizar los datos para encontrar patrones
    print("Analizando patrones en los datos...")
    
    # Buscar la secuencia PNG en diferentes posiciones
    for i in range(len(bytes_data) - 8):
        if bytes_data[i:i+8] == png_sig:
            print(f"¡Firma PNG encontrada en posición {i}!")
            print(f"Bloque: {i // 16}, Posición en bloque: {i % 16}")
            
            # Calcular qué clave podría generar esto
            for j in range(len(bytes_data) // 16):
                for shift in range(16):
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
                        result = assemble_png_exact(key, bytes_data)
                        if len(result) >= 8 and result[:8] == bytes(png_sig):
                            print(f"¡FUNCIONA! Clave: {key}")
                            filename = f"systematic_{key}.png"
                            with open(filename, "wb") as f:
                                f.write(result)
                            print(f"Imagen guardada: {filename}")
                            return key, result
    
    print("No se encontró la firma PNG en los datos originales")
    return None, None

def try_alternative_algorithm():
    """Probar un algoritmo alternativo"""
    print("\n=== Algoritmo Alternativo ===")
    
    bytes_data = get_bytes_data()
    if not bytes_data:
        return None
    
    png_sig = [137, 80, 78, 71, 13, 10, 26, 10]
    
    # Probar diferentes interpretaciones del algoritmo
    test_keys = [
        "0123456789abcdef",
        "fedcba9876543210",
        "1111111111111111",
        "0000000000000000",
        "ffffffffffffffff",
        "1234567890abcdef",
        "abcdef1234567890",
    ]
    
    for key in test_keys:
        print(f"Probando clave: {key}")
        
        try:
            # Probar con el algoritmo original
            result = assemble_png_exact(key, bytes_data)
            
            if len(result) >= 8:
                if result[:8] == bytes(png_sig):
                    print(f"\n¡ENCONTRADA IMAGEN PNG VÁLIDA!")
                    print(f"Clave: {key}")
                    
                    filename = f"alternative_{key}.png"
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

def try_web_interface():
    """Probar usando la interfaz web directamente"""
    print("\n=== Interfaz Web ===")
    
    # Probar diferentes claves usando la interfaz web
    test_keys = [
        "0123456789abcdef",
        "fedcba9876543210",
        "1111111111111111",
        "0000000000000000",
        "ffffffffffffffff",
        "1234567890abcdef",
        "abcdef1234567890",
    ]
    
    for key in test_keys:
        print(f"Probando clave en interfaz web: {key}")
        
        try:
            # Hacer una petición POST a la interfaz web
            url = "http://jupiter.challenges.picoctf.org:51400"
            data = {"user_in": key}
            
            response = requests.post(url, data=data)
            
            if response.status_code == 200:
                print(f"  Respuesta recibida para clave: {key}")
                # Aquí podrías analizar la respuesta para ver si contiene una imagen PNG
                
        except Exception as e:
            print(f"  Error con clave {key}: {e}")
    
    return None, None

if __name__ == "__main__":
    print("=== JavaScript Kiddie 2 Ultimate Solver ===")
    
    # Fuerza bruta comprehensiva
    key, image_data = try_comprehensive_brute_force()
    
    if not key:
        # Enfoque sistemático
        key, image_data = try_systematic_approach()
    
    if not key:
        # Algoritmo alternativo
        key, image_data = try_alternative_algorithm()
    
    if not key:
        # Interfaz web
        key, image_data = try_web_interface()
    
    if key and image_data:
        print(f"\n¡ÉXITO! Clave encontrada: {key}")
        print("La imagen debería contener la flag.")
    else:
        print("\nNo se pudo resolver automáticamente.")
        print("Puede que necesites un enfoque completamente diferente.")