#!/usr/bin/env python3
"""
Análisis profundo del HTML del cliente para extraer datos reales
"""

import re
import json
from bs4 import BeautifulSoup

def deep_analysis():
    """Análisis profundo del HTML del cliente"""
    
    print("🔍 ANÁLISIS PROFUNDO DEL CLIENTE REAL")
    print("=" * 60)
    
    try:
        with open("real_client_final.html", "r", encoding="utf-8") as f:
            html = f.read()
    except FileNotFoundError:
        print("❌ No se encontró el HTML del cliente")
        return
    
    print(f"📄 HTML cargado: {len(html)} caracteres")
    
    # 1. Buscar en JavaScript embebido
    print("\n🔍 Buscando en JavaScript embebido...")
    js_data = extract_from_javascript(html)
    if js_data:
        print(f"✅ Datos encontrados en JS: {len(js_data)} elementos")
        for key, value in list(js_data.items())[:10]:
            print(f"   - {key}: {str(value)[:100]}...")
    
    # 2. Buscar en atributos data
    print("\n🔍 Buscando en atributos data...")
    data_attrs = extract_from_data_attributes(html)
    if data_attrs:
        print(f"✅ Atributos data encontrados: {len(data_attrs)}")
        for attr in data_attrs[:10]:
            print(f"   - {attr}")
    
    # 3. Buscar en comentarios HTML
    print("\n🔍 Buscando en comentarios HTML...")
    comments = extract_from_comments(html)
    if comments:
        print(f"✅ Comentarios encontrados: {len(comments)}")
        for comment in comments[:5]:
            print(f"   - {comment[:100]}...")
    
    # 4. Buscar en strings JSON
    print("\n🔍 Buscando strings JSON...")
    json_strings = extract_json_strings(html)
    if json_strings:
        print(f"✅ Strings JSON encontrados: {len(json_strings)}")
        for json_str in json_strings[:5]:
            print(f"   - {json_str[:100]}...")
    
    # 5. Buscar patrones específicos de países
    print("\n🔍 Buscando patrones de países...")
    country_patterns = find_country_patterns(html)
    if country_patterns:
        print(f"✅ Patrones de países encontrados: {len(country_patterns)}")
        for pattern in country_patterns[:10]:
            print(f"   - {pattern}")
    
    # 6. Buscar en variables globales
    print("\n🔍 Buscando variables globales...")
    global_vars = find_global_variables(html)
    if global_vars:
        print(f"✅ Variables globales encontrados: {len(global_vars)}")
        for var in global_vars[:10]:
            print(f"   - {var}")
    
    # 7. Buscar en URLs y referencias
    print("\n🔍 Buscando URLs y referencias...")
    urls = find_urls_and_references(html)
    if urls:
        print(f"✅ URLs encontradas: {len(urls)}")
        for url in urls[:10]:
            print(f"   - {url}")
    
    # 8. Buscar en configuraciones
    print("\n🔍 Buscando configuraciones...")
    configs = find_configurations(html)
    if configs:
        print(f"✅ Configuraciones encontradas: {len(configs)}")
        for config in configs[:10]:
            print(f"   - {config}")
    
    # 9. Buscar en mapas y territorios
    print("\n🔍 Buscando mapas y territorios...")
    territories = find_territories(html)
    if territories:
        print(f"✅ Territorios encontrados: {len(territories)}")
        for territory in territories[:10]:
            print(f"   - {territory}")
    
    # 10. Buscar en IDs y clases
    print("\n🔍 Buscando IDs y clases...")
    ids_classes = find_ids_and_classes(html)
    if ids_classes:
        print(f"✅ IDs y clases encontrados: {len(ids_classes)}")
        for item in ids_classes[:10]:
            print(f"   - {item}")
    
    # Guardar todos los datos encontrados
    all_data = {
        "javascript_data": js_data,
        "data_attributes": data_attrs,
        "comments": comments,
        "json_strings": json_strings,
        "country_patterns": country_patterns,
        "global_variables": global_vars,
        "urls": urls,
        "configurations": configs,
        "territories": territories,
        "ids_classes": ids_classes
    }
    
    with open("deep_analysis_results.json", "w", encoding="utf-8") as f:
        json.dump(all_data, f, indent=2, ensure_ascii=False)
    
    print(f"\n💾 Análisis completo guardado en deep_analysis_results.json")
    
    # Buscar específicamente nombres de países
    print("\n🎯 BUSCANDO NOMBRES DE PAÍSES ESPECÍFICOS...")
    country_names = find_specific_country_names(html)
    if country_names:
        print(f"✅ NOMBRES DE PAÍSES ENCONTRADOS: {country_names}")
        
        with open("real_country_names.json", "w", encoding="utf-8") as f:
            json.dump(country_names, f, indent=2, ensure_ascii=False)
        print("💾 Nombres reales de países guardados")
    else:
        print("❌ No se encontraron nombres específicos de países")

def extract_from_javascript(html):
    """Extrae datos de JavaScript embebido"""
    
    soup = BeautifulSoup(html, 'html.parser')
    scripts = soup.find_all('script')
    
    js_data = {}
    
    for script in scripts:
        if script.string:
            # Buscar asignaciones de variables
            patterns = [
                r'(\w+)\s*=\s*({.*?});',
                r'var\s+(\w+)\s*=\s*({.*?});',
                r'window\.(\w+)\s*=\s*({.*?});',
                r'(\w+)\s*:\s*({.*?})',
                r'"(\w+)"\s*:\s*({.*?})'
            ]
            
            for pattern in patterns:
                matches = re.findall(pattern, script.string, re.DOTALL)
                for var_name, var_value in matches:
                    try:
                        # Intentar parsear como JSON
                        data = json.loads(var_value)
                        js_data[var_name] = data
                    except json.JSONDecodeError:
                        # Si no es JSON, guardar como string
                        js_data[var_name] = var_value.strip()
    
    return js_data

def extract_from_data_attributes(html):
    """Extrae datos de atributos data-*"""
    
    soup = BeautifulSoup(html, 'html.parser')
    data_attrs = []
    
    # Buscar elementos con atributos data-*
    elements = soup.find_all(attrs=lambda x: x and any(k.startswith('data-') for k in x.keys()))
    
    for element in elements:
        for attr, value in element.attrs.items():
            if attr.startswith('data-'):
                data_attrs.append(f"{attr}={value}")
    
    return data_attrs

def extract_from_comments(html):
    """Extrae datos de comentarios HTML"""
    
    comments = re.findall(r'<!--(.*?)-->', html, re.DOTALL)
    return [comment.strip() for comment in comments if comment.strip()]

def extract_json_strings(html):
    """Extrae strings que parecen JSON"""
    
    json_strings = []
    
    # Buscar patrones que parecen JSON
    patterns = [
        r'\{[^{}]*"[^"]*"[^{}]*\}',
        r'\[[^\[\]]*"[^"]*"[^\[\]]*\]',
        r'\{[^{}]*:[^{}]*\}',
        r'\[[^\[\]]*:[^\[\]]*\]'
    ]
    
    for pattern in patterns:
        matches = re.findall(pattern, html)
        json_strings.extend(matches)
    
    return json_strings

def find_country_patterns(html):
    """Busca patrones específicos de países"""
    
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

def find_global_variables(html):
    """Busca variables globales"""
    
    variables = []
    
    # Patrones de variables globales
    patterns = [
        r'window\.(\w+)',
        r'global\.(\w+)',
        r'var\s+(\w+)',
        r'let\s+(\w+)',
        r'const\s+(\w+)',
        r'(\w+)\s*=\s*function',
        r'(\w+)\s*=\s*\('
    ]
    
    for pattern in patterns:
        matches = re.findall(pattern, html)
        variables.extend(matches)
    
    return list(set(variables))

def find_urls_and_references(html):
    """Busca URLs y referencias"""
    
    urls = []
    
    # Patrones de URLs
    patterns = [
        r'https?://[^\s"\'<>]+',
        r'/[^\s"\'<>]*\.(?:php|html|js|json|xml)',
        r'"[^"]*\.(?:php|html|js|json|xml)"',
        r'/[^\s"\'<>]*\?[^\s"\'<>]*'
    ]
    
    for pattern in patterns:
        matches = re.findall(pattern, html)
        urls.extend(matches)
    
    return list(set(urls))

def find_configurations(html):
    """Busca configuraciones"""
    
    configs = []
    
    # Patrones de configuración
    patterns = [
        r'config[^"\']*"[^"]*"',
        r'settings[^"\']*"[^"]*"',
        r'options[^"\']*"[^"]*"',
        r'params[^"\']*"[^"]*"',
        r'"[^"]*config[^"]*"',
        r'"[^"]*setting[^"]*"',
        r'"[^"]*option[^"]*"'
    ]
    
    for pattern in patterns:
        matches = re.findall(pattern, html, re.IGNORECASE)
        configs.extend(matches)
    
    return configs

def find_territories(html):
    """Busca territorios y mapas"""
    
    territories = []
    
    # Patrones de territorios
    patterns = [
        r'"[^"]*territory[^"]*"',
        r'"[^"]*region[^"]*"',
        r'"[^"]*province[^"]*"',
        r'"[^"]*area[^"]*"',
        r'"[^"]*zone[^"]*"',
        r'"[^"]*map[^"]*"',
        r'"[^"]*location[^"]*"'
    ]
    
    for pattern in patterns:
        matches = re.findall(pattern, html, re.IGNORECASE)
        territories.extend(matches)
    
    return territories

def find_ids_and_classes(html):
    """Busca IDs y clases"""
    
    ids_classes = []
    
    # Patrones de IDs y clases
    patterns = [
        r'id="[^"]*"',
        r'class="[^"]*"',
        r'"[^"]*id[^"]*"',
        r'"[^"]*class[^"]*"'
    ]
    
    for pattern in patterns:
        matches = re.findall(pattern, html)
        ids_classes.extend(matches)
    
    return ids_classes

def find_specific_country_names(html):
    """Busca nombres específicos de países"""
    
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

if __name__ == "__main__":
    deep_analysis()