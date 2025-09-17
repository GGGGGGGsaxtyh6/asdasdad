#!/usr/bin/env python3
"""
Script para resolver el reto de comunicación estelar
El reto consiste en categorizar estrellas en 256 clusters basándose en muestras conocidas
"""

import numpy as np
import matplotlib.pyplot as plt
from sklearn.cluster import KMeans
from sklearn.preprocessing import StandardScaler
from sklearn.metrics import accuracy_score
import warnings
warnings.filterwarnings('ignore')

def load_data():
    """Cargar los datos de estrellas conocidas y desconocidas"""
    print("Cargando datos...")
    
    # Cargar datos conocidos (256 muestras, una por cluster)
    # Los archivos parecen ser de texto, no .npy binarios
    try:
        core_data = np.loadtxt("known_samples.npy")
    except:
        # Si falla, intentar cargar como texto plano
        with open("known_samples.npy", "r") as f:
            lines = f.readlines()
        core_data = np.array([list(map(float, line.strip().split())) for line in lines])
    
    print(f"Datos conocidos: {core_data.shape}")
    
    # Cargar datos desconocidos (el resto de estrellas)
    try:
        data = np.loadtxt("data.npy")
    except:
        # Si falla, intentar cargar como texto plano
        with open("data.npy", "r") as f:
            lines = f.readlines()
        data = np.array([list(map(float, line.strip().split())) for line in lines])
    
    print(f"Datos desconocidos: {data.shape}")
    
    # Combinar todos los datos
    all_data = np.vstack((core_data, data))
    print(f"Total de datos: {all_data.shape}")
    
    return core_data, data, all_data

def analyze_data(core_data, data):
    """Analizar las características de los datos"""
    print("\n=== ANÁLISIS DE DATOS ===")
    print(f"Dimensión de características: {core_data.shape[1]}")
    print(f"Rango de valores en datos conocidos: [{core_data.min():.3f}, {core_data.max():.3f}]")
    print(f"Rango de valores en datos desconocidos: [{data.min():.3f}, {data.max():.3f}]")
    
    # Verificar si los datos están normalizados
    print(f"Media de datos conocidos: {core_data.mean():.3f}")
    print(f"Desviación estándar de datos conocidos: {core_data.std():.3f}")
    print(f"Media de datos desconocidos: {data.mean():.3f}")
    print(f"Desviación estándar de datos desconocidos: {data.std():.3f}")

def create_initial_labels(core_data, all_data):
    """Crear labels iniciales basados en las muestras conocidas"""
    print("\n=== CREANDO LABELS INICIALES ===")
    
    # Los primeros 256 datos son las muestras conocidas (una por cluster)
    out_labels = np.full((all_data.shape[0],), -1)
    
    # Asignar labels a las muestras conocidas (0-255)
    for i in range(len(core_data)):
        out_labels[i] = i
    
    print(f"Labels asignados a muestras conocidas: {len(core_data)}")
    print(f"Labels pendientes: {np.sum(out_labels == -1)}")
    
    return out_labels

def cluster_unknown_stars(core_data, data, out_labels):
    """Clasificar las estrellas desconocidas usando las muestras conocidas como centroides"""
    print("\n=== CLASIFICANDO ESTRELLAS DESCONOCIDAS ===")
    
    # Usar las muestras conocidas como centroides
    centroids = core_data
    
    # Calcular distancias de cada estrella desconocida a cada centroide
    unknown_data = data
    distances = np.zeros((len(unknown_data), len(centroids)))
    
    print("Calculando distancias...")
    for i, star in enumerate(unknown_data):
        for j, centroid in enumerate(centroids):
            # Usar distancia euclidiana
            distances[i, j] = np.linalg.norm(star - centroid)
    
    # Asignar cada estrella al cluster más cercano
    print("Asignando clusters...")
    unknown_labels = np.argmin(distances, axis=1)
    
    # Actualizar los labels
    start_idx = len(core_data)
    out_labels[start_idx:] = unknown_labels
    
    print(f"Estrellas clasificadas: {len(unknown_labels)}")
    print(f"Distribución de clusters: {np.bincount(unknown_labels)}")
    
    return out_labels

def verify_labels(out_labels):
    """Verificar que los labels están en el rango correcto"""
    print("\n=== VERIFICANDO LABELS ===")
    
    valid_labels = np.logical_and(0 <= out_labels, out_labels < 256)
    print(f"Labels válidos: {np.sum(valid_labels)} / {len(out_labels)}")
    print(f"Rango de labels: [{out_labels.min()}, {out_labels.max()}]")
    
    if not np.all(valid_labels):
        print("ERROR: Algunos labels están fuera del rango [0, 255]")
        return False
    
    return True

def generate_flag_file(out_labels):
    """Generar el archivo flag.jpg con los labels de clustering"""
    print("\n=== GENERANDO ARCHIVO FLAG ===")
    
    # Los primeros 256 son las muestras conocidas, necesitamos los labels de las desconocidas
    unknown_labels = out_labels[256:]
    
    print(f"Generando archivo con {len(unknown_labels)} bytes...")
    
    # Convertir a bytes
    file_data = bytearray(unknown_labels.shape[0])
    for ix, val in enumerate(unknown_labels):
        file_data[ix] = val
    
    file_data = bytes(file_data)
    
    # Escribir archivo
    with open("flag.jpg", "wb") as outfile:
        outfile.write(file_data)
    
    print("Archivo flag.jpg generado exitosamente!")
    return True

def main():
    """Función principal"""
    print("=== RESOLVIENDO RETO DE COMUNICACIÓN ESTELAR ===")
    
    # Cargar datos
    core_data, data, all_data = load_data()
    
    # Analizar datos
    analyze_data(core_data, data)
    
    # Crear labels iniciales
    out_labels = create_initial_labels(core_data, all_data)
    
    # Clasificar estrellas desconocidas
    out_labels = cluster_unknown_stars(core_data, data, out_labels)
    
    # Verificar labels
    if not verify_labels(out_labels):
        print("ERROR: Labels inválidos")
        return False
    
    # Generar archivo flag
    if generate_flag_file(out_labels):
        print("\n=== RETO RESUELTO EXITOSAMENTE ===")
        print("Archivo flag.jpg generado. Este archivo contiene la bandera del reto.")
        return True
    
    return False

if __name__ == "__main__":
    success = main()
    if success:
        print("\n¡Reto completado! Revisa el archivo flag.jpg")
    else:
        print("\nError al resolver el reto")