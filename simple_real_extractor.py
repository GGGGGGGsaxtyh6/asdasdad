#!/usr/bin/env python3
"""
Extractor simple pero efectivo para encontrar datos reales
"""

import re
import json

def extract_real_data():
    """Extrae datos reales de forma simple"""
    
    print("🔍 EXTRACTOR SIMPLE - BUSCANDO DATOS REALES")
    print("=" * 60)
    
    try:
        with open("real_client_final.html", "r", encoding="utf-8") as f:
            html = f.read()
    except FileNotFoundError:
        print("❌ No se encontró el HTML del cliente")
        return
    
    print(f"📄 HTML cargado: {len(html)} caracteres")
    
    # 1. Buscar nombres de países reales
    print("\n🔍 Buscando nombres de países reales...")
    countries = find_real_countries(html)
    if countries:
        print(f"✅ PAÍSES REALES ENCONTRADOS: {countries}")
        return countries
    
    # 2. Buscar en patrones específicos
    print("\n🔍 Buscando en patrones específicos...")
    patterns = find_specific_patterns(html)
    if patterns:
        print(f"✅ Patrones encontrados: {len(patterns)}")
        for pattern in patterns[:20]:
            print(f"   - {pattern}")
    
    # 3. Buscar en texto plano
    print("\n🔍 Buscando en texto plano...")
    text_data = find_text_data(html)
    if text_data:
        print(f"✅ Datos de texto encontrados: {len(text_data)}")
        for data in text_data[:20]:
            print(f"   - {data}")
    
    # 4. Buscar en JavaScript
    print("\n🔍 Buscando en JavaScript...")
    js_data = find_js_data(html)
    if js_data:
        print(f"✅ Datos de JavaScript encontrados: {len(js_data)}")
        for data in js_data[:20]:
            print(f"   - {data}")
    
    # 5. Buscar en URLs
    print("\n🔍 Buscando en URLs...")
    urls = find_urls(html)
    if urls:
        print(f"✅ URLs encontradas: {len(urls)}")
        for url in urls[:20]:
            print(f"   - {url}")
    
    # 6. Buscar en configuraciones
    print("\n🔍 Buscando en configuraciones...")
    configs = find_configs(html)
    if configs:
        print(f"✅ Configuraciones encontradas: {len(configs)}")
        for config in configs[:20]:
            print(f"   - {config}")
    
    # 7. Buscar en mapas
    print("\n🔍 Buscando en mapas...")
    maps = find_maps(html)
    if maps:
        print(f"✅ Datos de mapas encontrados: {len(maps)}")
        for map_data in maps[:20]:
            print(f"   - {map_data}")
    
    # 8. Buscar en IDs
    print("\n🔍 Buscando en IDs...")
    ids = find_ids(html)
    if ids:
        print(f"✅ IDs encontrados: {len(ids)}")
        for id_data in ids[:20]:
            print(f"   - {id_data}")
    
    # 9. Buscar en clases
    print("\n🔍 Buscando en clases...")
    classes = find_classes(html)
    if classes:
        print(f"✅ Clases encontradas: {len(classes)}")
        for class_data in classes[:20]:
            print(f"   - {class_data}")
    
    # 10. Buscar en atributos
    print("\n🔍 Buscando en atributos...")
    attrs = find_attributes(html)
    if attrs:
        print(f"✅ Atributos encontrados: {len(attrs)}")
        for attr in attrs[:20]:
            print(f"   - {attr}")
    
    # Guardar todos los datos
    all_data = {
        "countries": countries,
        "patterns": patterns,
        "text_data": text_data,
        "js_data": js_data,
        "urls": urls,
        "configs": configs,
        "maps": maps,
        "ids": ids,
        "classes": classes,
        "attributes": attrs
    }
    
    with open("simple_extraction_results.json", "w", encoding="utf-8") as f:
        json.dump(all_data, f, indent=2, ensure_ascii=False)
    
    print(f"\n💾 Datos extraídos guardados en simple_extraction_results.json")
    
    # Buscar específicamente países
    print("\n🎯 BUSCANDO PAÍSES ESPECÍFICOS...")
    specific_countries = find_specific_countries(html)
    if specific_countries:
        print(f"✅ PAÍSES ESPECÍFICOS ENCONTRADOS: {specific_countries}")
        
        with open("real_countries_found.json", "w", encoding="utf-8") as f:
            json.dump(specific_countries, f, indent=2, ensure_ascii=False)
        print("💾 Países reales guardados")
    else:
        print("❌ No se encontraron países específicos")

def find_real_countries(html):
    """Busca países reales en el HTML"""
    
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
    
    return found_countries

def find_specific_patterns(html):
    """Busca patrones específicos"""
    
    patterns = []
    
    # Patrones que pueden indicar países
    country_patterns = [
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
    
    for pattern in country_patterns:
        matches = re.findall(pattern, html, re.IGNORECASE)
        patterns.extend(matches)
    
    return patterns

def find_text_data(html):
    """Busca datos en texto plano"""
    
    text_data = []
    
    # Buscar texto que parezca nombres de países
    lines = html.split('\n')
    for line in lines:
        line = line.strip()
        if len(line) > 5 and len(line) < 50:
            # Verificar si parece un nombre de país
            if re.match(r'^[A-Z][a-z]+(\s+[A-Z][a-z]+)*$', line):
                text_data.append(line)
    
    return text_data

def find_js_data(html):
    """Busca datos en JavaScript"""
    
    js_data = []
    
    # Buscar asignaciones de variables
    patterns = [
        r'(\w+)\s*=\s*"[^"]*"',
        r'(\w+)\s*=\s*\'[^\']*\'',
        r'(\w+)\s*:\s*"[^"]*"',
        r'(\w+)\s*:\s*\'[^\']*\''
    ]
    
    for pattern in patterns:
        matches = re.findall(pattern, html)
        js_data.extend(matches)
    
    return js_data

def find_urls(html):
    """Busca URLs"""
    
    urls = []
    
    # Patrones de URLs
    patterns = [
        r'https?://[^\s"\'<>]+',
        r'/[^\s"\'<>]*\.(?:php|html|js|json|xml)',
        r'"[^"]*\.(?:php|html|js|json|xml)"'
    ]
    
    for pattern in patterns:
        matches = re.findall(pattern, html)
        urls.extend(matches)
    
    return list(set(urls))

def find_configs(html):
    """Busca configuraciones"""
    
    configs = []
    
    # Patrones de configuración
    patterns = [
        r'config[^"\']*"[^"]*"',
        r'settings[^"\']*"[^"]*"',
        r'options[^"\']*"[^"]*"',
        r'params[^"\']*"[^"]*"'
    ]
    
    for pattern in patterns:
        matches = re.findall(pattern, html, re.IGNORECASE)
        configs.extend(matches)
    
    return configs

def find_maps(html):
    """Busca datos de mapas"""
    
    maps = []
    
    # Patrones de mapas
    patterns = [
        r'"[^"]*map[^"]*"',
        r'"[^"]*territory[^"]*"',
        r'"[^"]*region[^"]*"',
        r'"[^"]*province[^"]*"'
    ]
    
    for pattern in patterns:
        matches = re.findall(pattern, html, re.IGNORECASE)
        maps.extend(matches)
    
    return maps

def find_ids(html):
    """Busca IDs"""
    
    ids = []
    
    # Patrones de IDs
    patterns = [
        r'id="[^"]*"',
        r'"[^"]*id[^"]*"'
    ]
    
    for pattern in patterns:
        matches = re.findall(pattern, html)
        ids.extend(matches)
    
    return ids

def find_classes(html):
    """Busca clases"""
    
    classes = []
    
    # Patrones de clases
    patterns = [
        r'class="[^"]*"',
        r'"[^"]*class[^"]*"'
    ]
    
    for pattern in patterns:
        matches = re.findall(pattern, html)
        classes.extend(matches)
    
    return classes

def find_attributes(html):
    """Busca atributos"""
    
    attrs = []
    
    # Patrones de atributos
    patterns = [
        r'[a-zA-Z-]+="[^"]*"',
        r'[a-zA-Z-]+=\'[^\']*\''
    ]
    
    for pattern in patterns:
        matches = re.findall(pattern, html)
        attrs.extend(matches)
    
    return attrs

def find_specific_countries(html):
    """Busca países específicos"""
    
    # Buscar en diferentes secciones del HTML
    sections = [
        html[:len(html)//4],  # Primer cuarto
        html[len(html)//4:len(html)//2],  # Segundo cuarto
        html[len(html)//2:3*len(html)//4],  # Tercer cuarto
        html[3*len(html)//4:]  # Último cuarto
    ]
    
    all_countries = []
    
    for i, section in enumerate(sections):
        print(f"   Buscando en sección {i+1}...")
        
        # Buscar países en esta sección
        countries = find_real_countries(section)
        if countries:
            all_countries.extend(countries)
            print(f"      Encontrados: {countries}")
    
    return list(set(all_countries))

if __name__ == "__main__":
    extract_real_data()