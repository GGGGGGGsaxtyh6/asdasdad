#!/usr/bin/env python3
"""
Extractor agresivo que usa las cookies encontradas
"""

import asyncio
import aiohttp
from bs4 import BeautifulSoup
import re
import json
import urllib.parse

async def extract_with_cookies():
    """Extrae datos usando las cookies encontradas"""
    
    print("🎮 EXTRACTOR AGRESIVO CON COOKIES")
    print("=" * 60)
    print("Partida: 10477434")
    print("Usuario: ranvfy")
    print("User ID: 40944494")
    print()
    
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
        'Accept': 'application/json, text/plain, */*',
        'Accept-Language': 'en-US,en;q=0.9',
        'Accept-Encoding': 'gzip, deflate, br',
        'Connection': 'keep-alive',
        'Referer': 'https://www.callofwar.com/',
        'X-Requested-With': 'XMLHttpRequest'
    }
    
    async with aiohttp.ClientSession(cookies=cookies, headers=headers) as session:
        try:
            # 1. Login
            print("🔐 Iniciando sesión...")
            await login(session)
            
            # 2. Intentar endpoints específicos con cookies
            print("🎯 Intentando endpoints específicos...")
            
            # Endpoints específicos de Call of War
            endpoints = [
                f"https://www.callofwar.com/play.php?L=3&bust=1&gameID=10477434&userID=40944494",
                f"https://www.callofwar.com/game.php?L=3&bust=1&gameID=10477434&userID=40944494",
                f"https://www.callofwar.com/play.php?L=3&bust=1&gameID=10477434&puid=40944494",
                f"https://www.callofwar.com/game.php?L=3&bust=1&gameID=10477434&puid=40944494",
                f"https://www.callofwar.com/play.php?L=3&bust=1&gameID=10477434&sid=08f90ff7d31602d49fe3a0330c4175f7",
                f"https://www.callofwar.com/game.php?L=3&bust=1&gameID=10477434&sid=08f90ff7d31602d49fe3a0330c4175f7",
                f"https://www.callofwar.com/play.php?L=3&bust=1&gameID=10477434&lang=0",
                f"https://www.callofwar.com/game.php?L=3&bust=1&gameID=10477434&lang=0"
            ]
            
            for endpoint in endpoints:
                try:
                    print(f"🔍 Probando: {endpoint}")
                    async with session.get(endpoint) as response:
                        if response.status == 200:
                            content_type = response.headers.get('content-type', '')
                            print(f"   Content-Type: {content_type}")
                            
                            if 'json' in content_type:
                                data = await response.json()
                                print(f"✅ Datos JSON encontrados: {list(data.keys()) if isinstance(data, dict) else 'Lista'}")
                                
                                countries = extract_countries_from_data(data)
                                if countries:
                                    print(f"✅ PAÍSES ENCONTRADOS: {countries}")
                                    return countries
                            else:
                                html = await response.text()
                                countries = find_countries_in_html(html)
                                if countries:
                                    print(f"✅ PAÍSES ENCONTRADOS EN HTML: {countries}")
                                    return countries
                                
                                game_data = find_game_data_in_javascript(html)
                                if game_data:
                                    print(f"✅ DATOS DEL JUEGO ENCONTRADOS: {list(game_data.keys()) if isinstance(game_data, dict) else 'Lista'}")
                                    return game_data
                                
                                print(f"   ⚠️ Página accesible pero sin datos estructurados")
                        else:
                            print(f"   ❌ Error {response.status}")
                except Exception as e:
                    print(f"   ❌ Error: {e}")
            
            # 3. Intentar con parámetros POST
            print("\n🔍 Intentando con parámetros POST...")
            await try_post_requests(session)
            
            # 4. Intentar con diferentes User-Agents
            print("\n🔍 Intentando con diferentes User-Agents...")
            await try_different_user_agents(session)
            
            # 5. Intentar con diferentes referers
            print("\n🔍 Intentando con diferentes referers...")
            await try_different_referers(session)
        
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

async def try_post_requests(session):
    """Intenta peticiones POST"""
    
    post_endpoints = [
        "https://www.callofwar.com/play.php",
        "https://www.callofwar.com/game.php",
        "https://www.callofwar.com/api.php",
        "https://www.callofwar.com/data.php"
    ]
    
    post_data = {
        'gameID': '10477434',
        'userID': '40944494',
        'puid': '40944494',
        'sid': '08f90ff7d31602d49fe3a0330c4175f7',
        'action': 'get_game_data',
        'L': '3',
        'bust': '1'
    }
    
    for endpoint in post_endpoints:
        try:
            print(f"🔍 Probando POST: {endpoint}")
            async with session.post(endpoint, data=post_data) as response:
                if response.status == 200:
                    content_type = response.headers.get('content-type', '')
                    print(f"   Content-Type: {content_type}")
                    
                    if 'json' in content_type:
                        data = await response.json()
                        print(f"✅ Datos JSON encontrados: {list(data.keys()) if isinstance(data, dict) else 'Lista'}")
                        
                        countries = extract_countries_from_data(data)
                        if countries:
                            print(f"✅ PAÍSES ENCONTRADOS: {countries}")
                            return countries
                    else:
                        text = await response.text()
                        print(f"   ⚠️ Respuesta no JSON: {text[:200]}...")
                else:
                    print(f"   ❌ Error {response.status}")
        except Exception as e:
            print(f"   ❌ Error: {e}")

async def try_different_user_agents(session):
    """Intenta con diferentes User-Agents"""
    
    user_agents = [
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36',
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36',
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/121.0',
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36',
        'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36'
    ]
    
    for ua in user_agents:
        try:
            print(f"🔍 Probando User-Agent: {ua[:50]}...")
            headers = {'User-Agent': ua}
            
            async with session.get("https://www.callofwar.com/game.php?gameID=10477434", headers=headers) as response:
                if response.status == 200:
                    html = await response.text()
                    countries = find_countries_in_html(html)
                    if countries:
                        print(f"✅ PAÍSES ENCONTRADOS: {countries}")
                        return countries
                else:
                    print(f"   ❌ Error {response.status}")
        except Exception as e:
            print(f"   ❌ Error: {e}")

async def try_different_referers(session):
    """Intenta con diferentes referers"""
    
    referers = [
        'https://www.callofwar.com/',
        'https://www.callofwar.com/index.php',
        'https://www.callofwar.com/play.php',
        'https://www.callofwar.com/game.php',
        'https://www.callofwar.com/dashboard.php'
    ]
    
    for referer in referers:
        try:
            print(f"🔍 Probando Referer: {referer}")
            headers = {'Referer': referer}
            
            async with session.get("https://www.callofwar.com/game.php?gameID=10477434", headers=headers) as response:
                if response.status == 200:
                    html = await response.text()
                    countries = find_countries_in_html(html)
                    if countries:
                        print(f"✅ PAÍSES ENCONTRADOS: {countries}")
                        return countries
                else:
                    print(f"   ❌ Error {response.status}")
        except Exception as e:
            print(f"   ❌ Error: {e}")

def extract_countries_from_data(data):
    """Extrae países de datos JSON"""
    
    countries = []
    
    if isinstance(data, dict):
        # Buscar en diferentes claves posibles
        possible_keys = ['countries', 'players', 'factions', 'nations', 'data', 'game_data', 'gameData']
        
        for key in possible_keys:
            if key in data:
                value = data[key]
                if isinstance(value, list):
                    for item in value:
                        if isinstance(item, dict):
                            # Buscar nombre del país
                            name_keys = ['name', 'country', 'faction', 'player', 'nation', 'title', 'countryName']
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
                name_keys = ['name', 'country', 'faction', 'player', 'nation', 'title', 'countryName']
                for name_key in name_keys:
                    if name_key in item:
                        countries.append(item[name_key])
            elif isinstance(item, str):
                countries.append(item)
    
    return countries if countries else None

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
                r'players\s*=\s*(\[.*?\]);'
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

if __name__ == "__main__":
    asyncio.run(extract_with_cookies())