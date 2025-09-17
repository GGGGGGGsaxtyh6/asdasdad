#!/usr/bin/env python3
"""
Script para analizar el archivo flag.jpg generado
"""

import numpy as np
import matplotlib.pyplot as plt
from PIL import Image
import struct

def analyze_flag_file():
    """Analizar el archivo flag.jpg generado"""
    print("=== ANALIZANDO ARCHIVO FLAG ===")
    
    # Leer el archivo como bytes
    with open("flag.jpg", "rb") as f:
        data = f.read()
    
    print(f"Tamaño del archivo: {len(data)} bytes")
    print(f"Primeros 32 bytes: {data[:32]}")
    print(f"Últimos 32 bytes: {data[-32:]}")
    
    # Verificar si es un JPEG válido
    if data[:2] == b'\xff\xd8':
        print("✓ Archivo tiene header JPEG válido")
    else:
        print("✗ Archivo NO tiene header JPEG válido")
    
    # Buscar patrones de texto
    print("\n=== BUSCANDO PATRONES DE TEXTO ===")
    text_data = data.decode('latin-1', errors='ignore')
    
    # Buscar patrones comunes de flags
    flag_patterns = ['HTB{', 'flag{', 'CTF{', 'hackthebox']
    for pattern in flag_patterns:
        if pattern.lower() in text_data.lower():
            print(f"✓ Encontrado patrón: {pattern}")
            # Encontrar la posición
            pos = text_data.lower().find(pattern.lower())
            print(f"  Posición: {pos}")
            print(f"  Contexto: {text_data[max(0, pos-20):pos+50]}")
        else:
            print(f"✗ No encontrado: {pattern}")
    
    # Buscar caracteres imprimibles
    printable_chars = ''.join([c for c in text_data if c.isprintable()])
    print(f"\nCaracteres imprimibles encontrados: {len(printable_chars)}")
    print(f"Primeros 100 caracteres imprimibles: {printable_chars[:100]}")
    
    # Intentar interpretar como imagen
    print("\n=== INTENTANDO INTERPRETAR COMO IMAGEN ===")
    try:
        # Intentar diferentes dimensiones
        for width in [256, 512, 1024, 131527//256, 131527//512]:
            height = 131527 // width
            if height * width == 131527:
                print(f"Intentando dimensiones: {width}x{height}")
                try:
                    # Crear array de bytes
                    img_data = np.frombuffer(data, dtype=np.uint8)
                    img_data = img_data.reshape((height, width))
                    
                    # Guardar como imagen
                    img = Image.fromarray(img_data, mode='L')
                    img.save(f"flag_analysis_{width}x{height}.png")
                    print(f"  ✓ Imagen guardada como flag_analysis_{width}x{height}.png")
                    
                except Exception as e:
                    print(f"  ✗ Error: {e}")
    except Exception as e:
        print(f"Error al procesar como imagen: {e}")
    
    # Análisis estadístico de bytes
    print("\n=== ANÁLISIS ESTADÍSTICO ===")
    data_array = np.frombuffer(data, dtype=np.uint8)
    byte_counts = np.bincount(data_array)
    print(f"Distribución de bytes:")
    print(f"  Bytes únicos: {len(byte_counts)}")
    print(f"  Rango: {data_array.min()} - {data_array.max()}")
    print(f"  Media: {np.mean(data_array):.2f}")
    print(f"  Desviación estándar: {np.std(data_array):.2f}")
    
    # Buscar secuencias repetidas
    print("\n=== BUSCANDO SECUENCIAS REPETIDAS ===")
    for length in [2, 4, 8]:
        sequences = {}
        for i in range(len(data) - length + 1):
            seq = data[i:i+length]
            if seq in sequences:
                sequences[seq] += 1
            else:
                sequences[seq] = 1
        
        # Mostrar secuencias más comunes
        common_seqs = sorted(sequences.items(), key=lambda x: x[1], reverse=True)[:5]
        print(f"Secuencias de {length} bytes más comunes:")
        for seq, count in common_seqs:
            if count > 1:
                print(f"  {seq.hex()}: {count} veces")

if __name__ == "__main__":
    analyze_flag_file()