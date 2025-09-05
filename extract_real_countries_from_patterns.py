#!/usr/bin/env python3
"""
Extrae los países reales de los patrones encontrados
"""

import json
import re
from bs4 import BeautifulSoup

def extract_real_countries():
    """Extrae los países reales de los patrones"""
    
    print("🎮 EXTRAYENDO PAÍSES REALES DE LOS PATRONES")
    print("=" * 60)
    
    # Cargar patrones encontrados
    try:
        with open("data_patterns_final.json", "r", encoding="utf-8") as f:
            patterns = json.load(f)
    except FileNotFoundError:
        print("❌ No se encontraron patrones")
        return
    
    print(f"📊 Total de patrones: {len(patterns)}")
    
    # Buscar patrones de facciones
    factions = []
    for pattern in patterns:
        if "factions-" in pattern and "_s2" in pattern:
            # Extraer número de facción
            match = re.search(r'factions-(\d+)_s2', pattern)
            if match:
                faction_num = int(match.group(1))
                if faction_num not in factions:
                    factions.append(faction_num)
    
    factions.sort()
    print(f"🏴 Facciones encontradas: {factions}")
    print(f"📊 Total de países/facciones: {len(factions)}")
    
    # Buscar nombres de países en el HTML
    try:
        with open("real_client_final.html", "r", encoding="utf-8") as f:
            html = f.read()
    except FileNotFoundError:
        print("❌ No se encontró el HTML del cliente")
        return
    
    # Buscar nombres de países en el HTML
    countries = find_country_names_in_html(html)
    if countries:
        print(f"✅ PAÍSES ENCONTRADOS: {countries}")
        
        # Guardar países reales
        with open("real_countries_final.json", "w", encoding="utf-8") as f:
            json.dump(countries, f, indent=2, ensure_ascii=False)
        print("💾 Países reales guardados")
        
        return countries
    else:
        print("❌ No se encontraron nombres de países")
        
        # Crear países genéricos basados en las facciones
        generic_countries = []
        for i, faction in enumerate(factions):
            generic_countries.append(f"País {faction}")
        
        print(f"🏴 Países genéricos basados en facciones: {generic_countries}")
        
        # Guardar países genéricos
        with open("generic_countries.json", "w", encoding="utf-8") as f:
            json.dump(generic_countries, f, indent=2, ensure_ascii=False)
        print("💾 Países genéricos guardados")
        
        return generic_countries

def find_country_names_in_html(html):
    """Busca nombres de países en el HTML"""
    
    soup = BeautifulSoup(html, 'html.parser')
    countries = []
    
    # Buscar en elementos con texto que puedan ser nombres de países
    elements = soup.find_all(['div', 'span', 'p', 'h1', 'h2', 'h3', 'h4', 'h5', 'h6'])
    
    for element in elements:
        text = element.get_text(strip=True)
        if text and len(text) > 2 and len(text) < 50:
            # Verificar si parece un nombre de país
            if is_likely_country_name(text):
                if text not in countries:
                    countries.append(text)
    
    # Buscar en atributos
    for element in soup.find_all(attrs={"title": True}):
        title = element.get('title')
        if title and is_likely_country_name(title):
            if title not in countries:
                countries.append(title)
    
    for element in soup.find_all(attrs={"alt": True}):
        alt = element.get('alt')
        if alt and is_likely_country_name(alt):
            if alt not in countries:
                countries.append(alt)
    
    return countries if countries else None

def is_likely_country_name(text):
    """Verifica si un texto parece ser un nombre de país"""
    
    # Lista de países comunes
    common_countries = [
        'Germany', 'France', 'United Kingdom', 'Soviet Union', 'United States',
        'Japan', 'Italy', 'China', 'Poland', 'Spain', 'Netherlands', 'Belgium',
        'Norway', 'Denmark', 'Sweden', 'Finland', 'Romania', 'Hungary',
        'Bulgaria', 'Greece', 'Turkey', 'Portugal', 'Switzerland', 'Austria',
        'Czechoslovakia', 'Yugoslavia', 'Albania', 'Ireland', 'Iceland',
        'Canada', 'Australia', 'New Zealand', 'South Africa', 'Brazil',
        'Argentina', 'Mexico', 'India', 'Thailand', 'Philippines',
        'Alemania', 'Francia', 'Reino Unido', 'Unión Soviética', 'Estados Unidos',
        'Japón', 'Italia', 'China', 'Polonia', 'España', 'Países Bajos', 'Bélgica',
        'Noruega', 'Dinamarca', 'Suecia', 'Finlandia', 'Rumania', 'Hungría',
        'Bulgaria', 'Grecia', 'Turquía', 'Portugal', 'Suiza', 'Austria',
        'Checoslovaquia', 'Yugoslavia', 'Albania', 'Irlanda', 'Islandia',
        'Canadá', 'Australia', 'Nueva Zelanda', 'Sudáfrica', 'Brasil',
        'Argentina', 'México', 'India', 'Tailandia', 'Filipinas'
    ]
    
    # Verificar si está en la lista de países comunes
    for country in common_countries:
        if country.lower() in text.lower():
            return True
    
    # Verificar patrones que sugieren nombres de países
    patterns = [
        r'^[A-Z][a-z]+$',  # Palabra con mayúscula inicial
        r'^[A-Z][a-z]+\s+[A-Z][a-z]+$',  # Dos palabras con mayúscula inicial
        r'^[A-Z][a-z]+\s+[A-Z][a-z]+\s+[A-Z][a-z]+$'  # Tres palabras con mayúscula inicial
    ]
    
    for pattern in patterns:
        if re.match(pattern, text):
            return True
    
    return False

def analyze_faction_data():
    """Analiza los datos de las facciones"""
    
    print("\n🔍 ANÁLISIS DE DATOS DE FACCIONES")
    print("-" * 50)
    
    try:
        with open("data_patterns_final.json", "r", encoding="utf-8") as f:
            patterns = json.load(f)
    except FileNotFoundError:
        print("❌ No se encontraron patrones")
        return
    
    # Analizar patrones de facciones
    faction_data = {}
    
    for pattern in patterns:
        if "factions-" in pattern:
            # Extraer número de facción
            match = re.search(r'factions-(\d+)_s2', pattern)
            if match:
                faction_num = int(match.group(1))
                if faction_num not in faction_data:
                    faction_data[faction_num] = []
                faction_data[faction_num].append(pattern)
    
    print(f"📊 Análisis de facciones:")
    for faction_num in sorted(faction_data.keys()):
        patterns_count = len(faction_data[faction_num])
        print(f"   Facción {faction_num}: {patterns_count} patrones")
        
        # Mostrar algunos patrones de ejemplo
        example_patterns = faction_data[faction_num][:5]
        for pattern in example_patterns:
            print(f"      - {pattern}")
        if len(faction_data[faction_num]) > 5:
            print(f"      ... y {len(faction_data[faction_num]) - 5} más")
    
    return faction_data

if __name__ == "__main__":
    # Extraer países reales
    countries = extract_real_countries()
    
    # Analizar datos de facciones
    faction_data = analyze_faction_data()
    
    print(f"\n🎉 RESUMEN:")
    print(f"   Países encontrados: {len(countries) if countries else 0}")
    print(f"   Facciones encontradas: {len(faction_data)}")
    
    if countries:
        print(f"   Países: {countries}")
    else:
        print(f"   Países: No se pudieron extraer nombres reales")