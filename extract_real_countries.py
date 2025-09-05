#!/usr/bin/env python3
"""
Extrae los países REALES de la partida 10477434
Sin inventar países que no existen
"""

import asyncio
import aiohttp
from bs4 import BeautifulSoup
import re
import json

async def extract_real_countries():
    """Extrae los países reales de la partida 10477434"""
    
    print("🔍 EXTRAYENDO PAÍSES REALES DE LA PARTIDA 10477434")
    print("=" * 60)
    print("Usuario: ranvfy")
    print()
    
    async with aiohttp.ClientSession() as session:
        try:
            # 1. Login
            print("🔐 Iniciando sesión...")
            await login(session)
            
            # 2. Acceder a la partida y extraer países reales
            print("🎯 Accediendo a la partida 10477434...")
            countries = await extract_countries_from_game(session)
            
            if countries:
                print(f"✅ PAÍSES REALES ENCONTRADOS: {len(countries)}")
                print()
                
                for i, country in enumerate(countries, 1):
                    print(f"{i}. {country}")
                
                # Guardar países reales
                with open("real_countries.json", "w", encoding="utf-8") as f:
                    json.dump(countries, f, indent=2, ensure_ascii=False)
                print(f"\n💾 Países guardados en real_countries.json")
                
                # Ahora extraer datos de cada país real
                await extract_data_for_real_countries(session, countries)
                
            else:
                print("❌ No se pudieron extraer países reales")
                print("🔍 Intentando métodos alternativos...")
                await try_alternative_extraction(session)
        
        except Exception as e:
            print(f"❌ Error: {e}")
            import traceback
            traceback.print_exc()

async def login(session):
    """Realiza login en Call of War"""
    
    async with session.get("https://www.callofwar.com") as response:
        html = await response.text()
        soup = BeautifulSoup(html, 'html.parser')
        
        # Buscar formulario de login
        login_form = None
        for form in soup.find_all('form'):
            if form.find('input', {'name': 'user'}) and form.find('input', {'name': 'pass'}):
                login_form = form
                break
        
        if not login_form:
            raise Exception("No se encontró formulario de login")
        
        # Realizar login
        action = login_form.get('action', 'index.php?id=304')
        if action.startswith('/'):
            action = f"https://www.callofwar.com{action}"
        elif not action.startswith('http'):
            action = f"https://www.callofwar.com/{action}"
        
        login_data = {
            'user': 'ranvfy',
            'pass': '5zb6-u_JxWfaeH.'
        }
        
        async with session.post(action, data=login_data) as response:
            if response.status != 200:
                raise Exception(f"Error en login: {response.status}")
            
            text = await response.text()
            if not any(keyword in text.lower() for keyword in ['dashboard', 'game', 'logout', 'profile', 'welcome']):
                raise Exception("Login falló")
            
            print("✅ Login exitoso")

async def extract_countries_from_game(session):
    """Extrae países reales de la partida"""
    
    game_urls = [
        "https://www.callofwar.com/game.php?L=3&bust=1#/game/:gameID=10477434",
        "https://www.callofwar.com/game.php?gameID=10477434",
        "https://www.callofwar.com/game.php?L=3&gameID=10477434"
    ]
    
    for url in game_urls:
        try:
            print(f"🔍 Probando: {url}")
            async with session.get(url) as response:
                if response.status == 200:
                    html = await response.text()
                    
                    # Buscar países en el HTML
                    countries = find_countries_in_html(html)
                    if countries:
                        return countries
                    
                    # Buscar en JavaScript
                    countries = find_countries_in_javascript(html)
                    if countries:
                        return countries
                    
                    # Buscar en iframe o cliente
                    countries = await find_countries_in_client(session, html)
                    if countries:
                        return countries
        
        except Exception as e:
            print(f"❌ Error con {url}: {e}")
            continue
    
    return None

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
        '.player-name'
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
    
    return countries if countries else None

def find_countries_in_javascript(html):
    """Busca países en JavaScript"""
    
    soup = BeautifulSoup(html, 'html.parser')
    scripts = soup.find_all('script')
    
    for script in scripts:
        if script.string:
            # Buscar patrones de países
            patterns = [
                r'countries\s*=\s*\[(.*?)\]',
                r'countries\s*:\s*\[(.*?)\]',
                r'countryList\s*=\s*\[(.*?)\]',
                r'factions\s*=\s*\[(.*?)\]',
                r'players\s*=\s*\[(.*?)\]',
                r'nations\s*=\s*\[(.*?)\]'
            ]
            
            for pattern in patterns:
                matches = re.findall(pattern, script.string, re.DOTALL)
                if matches:
                    try:
                        # Intentar parsear como JSON
                        countries_str = f"[{matches[0]}]"
                        countries = json.loads(countries_str)
                        if isinstance(countries, list) and countries:
                            return countries
                    except:
                        # Si no es JSON, extraer texto
                        countries_str = matches[0]
                        countries = []
                        for item in countries_str.split(','):
                            item = item.strip().strip('"\'')
                            if item and len(item) > 2:
                                countries.append(item)
                        if countries:
                            return countries
    
    return None

async def find_countries_in_client(session, html):
    """Busca países en el cliente web"""
    
    # Buscar iframe del cliente
    soup = BeautifulSoup(html, 'html.parser')
    iframe = soup.find('iframe')
    
    if iframe and iframe.get('src'):
        client_url = iframe.get('src')
        if client_url.startswith('/'):
            client_url = f"https://www.callofwar.com{client_url}"
        
        print(f"🌐 Accediendo al cliente: {client_url}")
        
        try:
            async with session.get(client_url) as response:
                if response.status == 200:
                    client_html = await response.text()
                    
                    # Buscar países en el cliente
                    countries = find_countries_in_html(client_html)
                    if countries:
                        return countries
                    
                    countries = find_countries_in_javascript(client_html)
                    if countries:
                        return countries
                    
                    # Guardar HTML del cliente para análisis
                    with open("client_analysis.html", "w", encoding="utf-8") as f:
                        f.write(client_html)
                    print("💾 Cliente guardado para análisis")
        
        except Exception as e:
            print(f"❌ Error accediendo al cliente: {e}")
    
    return None

async def try_alternative_extraction(session):
    """Intenta métodos alternativos de extracción"""
    
    print("\n🔍 MÉTODOS ALTERNATIVOS:")
    
    # Intentar API endpoints
    api_endpoints = [
        "https://www.callofwar.com/api/game/10477434/countries",
        "https://www.callofwar.com/api/game/10477434/players",
        "https://www.callofwar.com/api/game/10477434/factions",
        "https://www.callofwar.com/play.php?L=3&bust=1&gameID=10477434&action=countries",
        "https://www.callofwar.com/play.php?L=3&bust=1&gameID=10477434&action=players"
    ]
    
    for endpoint in api_endpoints:
        try:
            print(f"🔍 Probando API: {endpoint}")
            async with session.get(endpoint) as response:
                if response.status == 200:
                    content_type = response.headers.get('content-type', '')
                    if 'json' in content_type:
                        data = await response.json()
                        print(f"✅ Datos JSON encontrados: {list(data.keys()) if isinstance(data, dict) else 'Lista'}")
                        
                        # Buscar países en los datos
                        countries = extract_countries_from_data(data)
                        if countries:
                            print(f"✅ Países encontrados: {countries}")
                            return countries
                    else:
                        text = await response.text()
                        countries = find_countries_in_html(text)
                        if countries:
                            print(f"✅ Países encontrados en HTML: {countries}")
                            return countries
                else:
                    print(f"❌ Error {response.status}")
        except Exception as e:
            print(f"❌ Error: {e}")
    
    return None

def extract_countries_from_data(data):
    """Extrae países de datos JSON"""
    
    countries = []
    
    if isinstance(data, dict):
        # Buscar en diferentes claves posibles
        possible_keys = ['countries', 'players', 'factions', 'nations', 'data']
        
        for key in possible_keys:
            if key in data:
                value = data[key]
                if isinstance(value, list):
                    for item in value:
                        if isinstance(item, dict):
                            # Buscar nombre del país
                            name_keys = ['name', 'country', 'faction', 'player', 'nation']
                            for name_key in name_keys:
                                if name_key in item:
                                    countries.append(item[name_key])
                        elif isinstance(item, str):
                            countries.append(item)
                elif isinstance(value, str):
                    countries.append(value)
    
    elif isinstance(data, list):
        for item in data:
            if isinstance(item, dict):
                name_keys = ['name', 'country', 'faction', 'player', 'nation']
                for name_key in name_keys:
                    if name_key in item:
                        countries.append(item[name_key])
            elif isinstance(item, str):
                countries.append(item)
    
    return countries if countries else None

async def extract_data_for_real_countries(session, countries):
    """Extrae datos para los países reales encontrados"""
    
    print(f"\n📊 EXTRAYENDO DATOS PARA {len(countries)} PAÍSES REALES:")
    print("=" * 60)
    
    for country in countries:
        print(f"\n🏴 Analizando {country}...")
        
        # Aquí implementarías la extracción de datos específicos para cada país
        # tropas, recursos, ciudades, etc.
        
        print(f"   🔍 Buscando datos de {country}...")
        # Por ahora solo mostramos que encontramos el país
        print(f"   ✅ País {country} identificado")

if __name__ == "__main__":
    asyncio.run(extract_real_countries())