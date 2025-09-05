#!/usr/bin/env python3
"""
Busca los nombres reales de los países en el HTML del cliente
"""

import re
import json

def find_real_countries():
    """Busca los nombres reales de los países"""
    
    print("🔍 BUSCANDO NOMBRES REALES DE PAÍSES")
    print("=" * 60)
    
    try:
        with open("real_client_final.html", "r", encoding="utf-8") as f:
            html = f.read()
    except FileNotFoundError:
        print("❌ No se encontró el HTML del cliente")
        return
    
    print(f"📄 HTML cargado: {len(html)} caracteres")
    
    # 1. Buscar en el HTML completo
    print("\n🔍 Buscando en HTML completo...")
    countries = find_countries_in_html(html)
    if countries:
        print(f"✅ PAÍSES ENCONTRADOS: {countries}")
        return countries
    
    # 2. Buscar en secciones específicas
    print("\n🔍 Buscando en secciones específicas...")
    sections = split_html_into_sections(html)
    for i, section in enumerate(sections):
        print(f"   Sección {i+1}: {len(section)} caracteres")
        countries = find_countries_in_html(section)
        if countries:
            print(f"   ✅ PAÍSES ENCONTRADOS EN SECCIÓN {i+1}: {countries}")
            return countries
    
    # 3. Buscar en JavaScript
    print("\n🔍 Buscando en JavaScript...")
    js_countries = find_countries_in_javascript(html)
    if js_countries:
        print(f"✅ PAÍSES ENCONTRADOS EN JS: {js_countries}")
        return js_countries
    
    # 4. Buscar en comentarios
    print("\n🔍 Buscando en comentarios...")
    comment_countries = find_countries_in_comments(html)
    if comment_countries:
        print(f"✅ PAÍSES ENCONTRADOS EN COMENTARIOS: {comment_countries}")
        return comment_countries
    
    # 5. Buscar en atributos
    print("\n🔍 Buscando en atributos...")
    attr_countries = find_countries_in_attributes(html)
    if attr_countries:
        print(f"✅ PAÍSES ENCONTRADOS EN ATRIBUTOS: {attr_countries}")
        return attr_countries
    
    # 6. Buscar en texto plano
    print("\n🔍 Buscando en texto plano...")
    text_countries = find_countries_in_text(html)
    if text_countries:
        print(f"✅ PAÍSES ENCONTRADOS EN TEXTO: {text_countries}")
        return text_countries
    
    # 7. Buscar en patrones específicos
    print("\n🔍 Buscando en patrones específicos...")
    pattern_countries = find_countries_in_patterns(html)
    if pattern_countries:
        print(f"✅ PAÍSES ENCONTRADOS EN PATRONES: {pattern_countries}")
        return pattern_countries
    
    print("❌ No se encontraron países reales")
    return None

def find_countries_in_html(html):
    """Busca países en el HTML"""
    
    # Lista de países comunes en Call of War
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
    
    found_countries = []
    
    for country in common_countries:
        if country.lower() in html.lower():
            found_countries.append(country)
    
    return found_countries if found_countries else None

def split_html_into_sections(html):
    """Divide el HTML en secciones"""
    
    # Dividir en 10 secciones
    section_size = len(html) // 10
    sections = []
    
    for i in range(10):
        start = i * section_size
        end = start + section_size if i < 9 else len(html)
        sections.append(html[start:end])
    
    return sections

def find_countries_in_javascript(html):
    """Busca países en JavaScript"""
    
    # Buscar en scripts
    script_pattern = r'<script[^>]*>(.*?)</script>'
    scripts = re.findall(script_pattern, html, re.DOTALL | re.IGNORECASE)
    
    countries = []
    
    for script in scripts:
        script_countries = find_countries_in_html(script)
        if script_countries:
            countries.extend(script_countries)
    
    return list(set(countries)) if countries else None

def find_countries_in_comments(html):
    """Busca países en comentarios HTML"""
    
    comment_pattern = r'<!--(.*?)-->'
    comments = re.findall(comment_pattern, html, re.DOTALL)
    
    countries = []
    
    for comment in comments:
        comment_countries = find_countries_in_html(comment)
        if comment_countries:
            countries.extend(comment_countries)
    
    return list(set(countries)) if countries else None

def find_countries_in_attributes(html):
    """Busca países en atributos HTML"""
    
    # Buscar en atributos title, alt, data-*
    attr_patterns = [
        r'title="([^"]*)"',
        r'alt="([^"]*)"',
        r'data-[^=]*="([^"]*)"'
    ]
    
    countries = []
    
    for pattern in attr_patterns:
        matches = re.findall(pattern, html, re.IGNORECASE)
        for match in matches:
            match_countries = find_countries_in_html(match)
            if match_countries:
                countries.extend(match_countries)
    
    return list(set(countries)) if countries else None

def find_countries_in_text(html):
    """Busca países en texto plano"""
    
    # Buscar texto que parezca nombres de países
    lines = html.split('\n')
    countries = []
    
    for line in lines:
        line = line.strip()
        if len(line) > 5 and len(line) < 50:
            # Verificar si parece un nombre de país
            if re.match(r'^[A-Z][a-z]+(\s+[A-Z][a-z]+)*$', line):
                countries.append(line)
    
    return countries if countries else None

def find_countries_in_patterns(html):
    """Busca países en patrones específicos"""
    
    # Patrones que pueden contener nombres de países
    patterns = [
        r'"[^"]*country[^"]*"',
        r'"[^"]*nation[^"]*"',
        r'"[^"]*faction[^"]*"',
        r'"[^"]*player[^"]*"',
        r'"[^"]*state[^"]*"',
        r'"[^"]*republic[^"]*"',
        r'"[^"]*kingdom[^"]*"',
        r'"[^"]*empire[^"]*"',
        r'"[^"]*union[^"]*"',
        r'"[^"]*federation[^"]*"'
    ]
    
    countries = []
    
    for pattern in patterns:
        matches = re.findall(pattern, html, re.IGNORECASE)
        for match in matches:
            # Extraer texto entre comillas
            text = match.strip('"')
            if len(text) > 5 and len(text) < 50:
                countries.append(text)
    
    return countries if countries else None

def analyze_faction_data():
    """Analiza los datos de las facciones encontrados"""
    
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
    # Buscar países reales
    countries = find_real_countries()
    
    # Analizar datos de facciones
    faction_data = analyze_faction_data()
    
    print(f"\n🎉 RESUMEN:")
    print(f"   Países encontrados: {len(countries) if countries else 0}")
    print(f"   Facciones encontradas: {len(faction_data)}")
    
    if countries:
        print(f"   Países: {countries}")
    else:
        print(f"   Países: No se pudieron extraer nombres reales")
        print(f"   Facciones: {list(faction_data.keys())}")