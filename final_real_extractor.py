#!/usr/bin/env python3
"""
Extractor final usando la URL real del cliente encontrada
"""

import asyncio
import aiohttp
from bs4 import BeautifulSoup
import re
import json
import urllib.parse

async def extract_from_real_client_url():
    """Extrae datos usando la URL real del cliente"""
    
    print("🎮 EXTRACTOR FINAL - URL REAL DEL CLIENTE")
    print("=" * 60)
    print("Partida: 10477434")
    print("Usuario: ranvfy")
    print("User ID: 40944494")
    print()
    
    # URL real del cliente encontrada
    client_url = "https://www.callofwar.com/clients/ww2-client-ultimate/ww2-client-ultimate_live/index.html?&L=3&bust=1&uid=40944494&gameID=10477434&spa=hup&id=328&gs=thi-cow-gs-flmx.c.bytro.com&lang=es&auth=0347fd1f2c4fe9b3aa5ecebc1b5f99d600785623&authHash=2c5ac16736a56cf6f9ff09ac1bf07c1c290cda4e&authTstamp=1757112168&gameURL=https%3A%2F%2Fwww.callofwar.com%2Fplay.php%3FL%3D3%26bust%3D1&logoutURL=https%3A%2F%2Fwww.callofwar.com%2Fgame.php%3FL%3D3%26bust%3D1&mapID=21814_4&modID=511&v=1757112168&titleID=510&testLevel=0&chatServer=chat.callofwar.com&chatAuth=ec65fe18f4d262a62d229e99be82104dbd915c89&chatAuthTstamp=1757112168&uberAuthHash=d0ba5fb5207021475525d203acfd2b712de72d5f&uberAuthTstamp=1757112168&rights=chat&canSwitchClient=1&upscalingSettings=eyJsb3ciOnsiYXNzZXQiOiJsb3ciLCJzdGFtcHMiOjAuMSwibWFwIjowLjUsInBhcnRpY2xlcyI6ZmFsc2UsImFsbG9jYXRpb25zRmFjdG9yIjoxfSwiaGlnaCI6eyJhc3NldCI6ImhpZ2giLCJzdGFtcHMiOjEsIm1hcCI6MSwicGFydGljbGVzIjp0cnVlLCJhbGxvY2F0aW9uc0ZhY3RvciI6MX19&attOpenDelayMs=50000&attPopupType=5&ws=www.callofwar.com&ggsShopApiLevel=v2&warchest=1"
    
    # Cookies encontradas
    cookies = {
        'bl_sid': '08f90ff7d31602d49fe3a0330c4175f7',
        'puid': '40944494',
        'sup_hist': '%26uid%3D40944494%26id%3D08f90ff7d31602d49fe3a0330c4175f7',
        'bl_lang': '0',
        'acceptedCookies': '1'
    }
    
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36',
        'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8',
        'Accept-Language': 'en-US,en;q=0.9',
        'Accept-Encoding': 'gzip, deflate, br',
        'Connection': 'keep-alive',
        'Referer': 'https://www.callofwar.com/game.php?L=3&bust=1&gameID=10477434'
    }
    
    async with aiohttp.ClientSession(cookies=cookies, headers=headers) as session:
        try:
            print("🌐 Accediendo al cliente real...")
            print(f"URL: {client_url[:100]}...")
            
            async with session.get(client_url) as response:
                print(f"Status: {response.status}")
                
                if response.status == 200:
                    html = await response.text()
                    print(f"✅ Cliente accedido - {len(html)} caracteres")
                    
                    # Guardar HTML del cliente real
                    with open("real_client_final.html", "w", encoding="utf-8") as f:
                        f.write(html)
                    print("💾 HTML del cliente real guardado")
                    
                    # Buscar países en el HTML
                    countries = find_countries_in_html(html)
                    if countries:
                        print(f"✅ PAÍSES ENCONTRADOS: {countries}")
                        
                        # Guardar países
                        with open("real_countries_final.json", "w", encoding="utf-8") as f:
                            json.dump(countries, f, indent=2, ensure_ascii=False)
                        print("💾 Países guardados en real_countries_final.json")
                        
                        return countries
                    
                    # Buscar datos del juego en JavaScript
                    game_data = find_game_data_in_javascript(html)
                    if game_data:
                        print(f"✅ DATOS DEL JUEGO ENCONTRADOS: {list(game_data.keys()) if isinstance(game_data, dict) else 'Lista'}")
                        
                        # Guardar datos
                        with open("real_game_data_final.json", "w", encoding="utf-8") as f:
                            json.dump(game_data, f, indent=2, ensure_ascii=False)
                        print("💾 Datos del juego guardados")
                        
                        return game_data
                    
                    # Buscar en el texto
                    countries = find_countries_in_text(html)
                    if countries:
                        print(f"✅ PAÍSES ENCONTRADOS EN TEXTO: {countries}")
                        
                        # Guardar países
                        with open("real_countries_text_final.json", "w", encoding="utf-8") as f:
                            json.dump(countries, f, indent=2, ensure_ascii=False)
                        print("💾 Países guardados en real_countries_text_final.json")
                        
                        return countries
                    
                    # Buscar en variables globales
                    global_vars = find_global_variables(html)
                    if global_vars:
                        print(f"✅ VARIABLES GLOBALES ENCONTRADAS: {list(global_vars.keys())}")
                        
                        # Guardar variables
                        with open("global_variables_final.json", "w", encoding="utf-8") as f:
                            json.dump(global_vars, f, indent=2, ensure_ascii=False)
                        print("💾 Variables globales guardadas")
                        
                        return global_vars
                    
                    print("❌ No se encontraron datos estructurados")
                    
                    # Buscar cualquier patrón que pueda contener datos
                    patterns = find_data_patterns(html)
                    if patterns:
                        print(f"✅ PATRONES DE DATOS ENCONTRADOS: {len(patterns)}")
                        for pattern in patterns[:10]:  # Mostrar solo los primeros 10
                            print(f"   - {pattern}")
                        
                        # Guardar patrones
                        with open("data_patterns_final.json", "w", encoding="utf-8") as f:
                            json.dump(patterns, f, indent=2, ensure_ascii=False)
                        print("💾 Patrones de datos guardados")
                        
                        return patterns
                    
                else:
                    print(f"❌ Error accediendo al cliente: {response.status}")
                    text = await response.text()
                    print(f"Respuesta: {text[:500]}...")
        
        except Exception as e:
            print(f"❌ Error: {e}")
            import traceback
            traceback.print_exc()

def find_countries_in_html(html):
    """Busca países en el HTML"""
    
    soup = BeautifulSoup(html, 'html.parser')
    countries = []
    
    # Buscar en diferentes elementos
    selectors = [
        'div[class*="country"]',
        'div[class*="nation"]', 
        'div[class*="faction"]',
        'div[class*="player"]',
        'span[class*="country"]',
        'span[class*="nation"]',
        'span[class*="faction"]',
        'span[class*="player"]',
        '.country-name',
        '.nation-name',
        '.faction-name',
        '.player-name',
        '[data-country]',
        '[data-nation]',
        '[data-faction]',
        '[data-player]'
    ]
    
    for selector in selectors:
        elements = soup.select(selector)
        for element in elements:
            text = element.get_text(strip=True)
            if text and len(text) > 2 and len(text) < 50:
                if text not in countries:
                    countries.append(text)
    
    # Buscar en enlaces
    links = soup.find_all('a', href=True)
    for link in links:
        text = link.get_text(strip=True)
        if text and len(text) > 2 and len(text) < 50:
            if text not in countries:
                countries.append(text)
    
    # Buscar en atributos data
    elements_with_data = soup.find_all(attrs={"data-country": True})
    for element in elements_with_data:
        country = element.get('data-country')
        if country and country not in countries:
            countries.append(country)
    
    elements_with_data = soup.find_all(attrs={"data-nation": True})
    for element in elements_with_data:
        nation = element.get('data-nation')
        if nation and nation not in countries:
            countries.append(nation)
    
    elements_with_data = soup.find_all(attrs={"data-faction": True})
    for element in elements_with_data:
        faction = element.get('data-faction')
        if faction and faction not in countries:
            countries.append(faction)
    
    return countries if countries else None

def find_game_data_in_javascript(html):
    """Busca datos del juego en JavaScript"""
    
    soup = BeautifulSoup(html, 'html.parser')
    scripts = soup.find_all('script')
    
    for script in scripts:
        if script.string:
            patterns = [
                r'gameData\s*=\s*({.*?});',
                r'window\.gameData\s*=\s*({.*?});',
                r'var\s+gameData\s*=\s*({.*?});',
                r'countries\s*=\s*(\[.*?\]);',
                r'troops\s*=\s*(\[.*?\]);',
                r'players\s*=\s*(\[.*?\]);',
                r'gameInfo\s*=\s*({.*?});',
                r'gameState\s*=\s*({.*?});',
                r'window\.gameState\s*=\s*({.*?});'
            ]
            
            for pattern in patterns:
                matches = re.findall(pattern, script.string, re.DOTALL)
                if matches:
                    try:
                        data = json.loads(matches[0])
                        return data
                    except json.JSONDecodeError:
                        continue
    
    return None

def find_countries_in_text(html):
    """Busca países en el texto"""
    
    # Lista de países comunes en Call of War
    common_countries = [
        'Germany', 'France', 'United Kingdom', 'Soviet Union', 'United States',
        'Japan', 'Italy', 'China', 'Poland', 'Spain', 'Netherlands', 'Belgium',
        'Norway', 'Denmark', 'Sweden', 'Finland', 'Romania', 'Hungary',
        'Bulgaria', 'Greece', 'Turkey', 'Portugal', 'Switzerland', 'Austria',
        'Czechoslovakia', 'Yugoslavia', 'Albania', 'Ireland', 'Iceland',
        'Canada', 'Australia', 'New Zealand', 'South Africa', 'Brazil',
        'Argentina', 'Mexico', 'India', 'Thailand', 'Philippines'
    ]
    
    found_countries = []
    for country in common_countries:
        if country.lower() in html.lower():
            found_countries.append(country)
    
    return found_countries if found_countries else None

def find_global_variables(html):
    """Busca variables globales en JavaScript"""
    
    soup = BeautifulSoup(html, 'html.parser')
    scripts = soup.find_all('script')
    
    global_vars = {}
    
    for script in scripts:
        if script.string:
            # Buscar variables globales
            patterns = [
                r'var\s+(\w+)\s*=\s*({.*?});',
                r'window\.(\w+)\s*=\s*({.*?});',
                r'(\w+)\s*=\s*({.*?});'
            ]
            
            for pattern in patterns:
                matches = re.findall(pattern, script.string, re.DOTALL)
                for var_name, var_value in matches:
                    try:
                        data = json.loads(var_value)
                        global_vars[var_name] = data
                    except json.JSONDecodeError:
                        # Si no es JSON, guardar como string
                        global_vars[var_name] = var_value
    
    return global_vars if global_vars else None

def find_data_patterns(html):
    """Busca patrones que puedan contener datos"""
    
    patterns = []
    
    # Buscar patrones de datos
    data_patterns = [
        r'"[^"]*country[^"]*"',
        r'"[^"]*nation[^"]*"',
        r'"[^"]*faction[^"]*"',
        r'"[^"]*player[^"]*"',
        r'"[^"]*game[^"]*"',
        r'"[^"]*troop[^"]*"',
        r'"[^"]*army[^"]*"',
        r'"[^"]*military[^"]*"'
    ]
    
    for pattern in data_patterns:
        matches = re.findall(pattern, html, re.IGNORECASE)
        patterns.extend(matches)
    
    # Buscar patrones de números (posibles IDs)
    number_patterns = [
        r'\b\d{4,}\b',  # Números de 4+ dígitos
        r'\b[A-Z]{2,}\b',  # Códigos de país
        r'\b[a-z]{2,}\b'  # Códigos en minúsculas
    ]
    
    for pattern in number_patterns:
        matches = re.findall(pattern, html)
        patterns.extend(matches)
    
    return patterns if patterns else None

if __name__ == "__main__":
    asyncio.run(extract_from_real_client_url())