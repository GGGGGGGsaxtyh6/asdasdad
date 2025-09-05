#!/usr/bin/env python3
"""
Extractor de datos reales usando métodos avanzados
"""

import asyncio
import aiohttp
from bs4 import BeautifulSoup
import re
import json
import time

async def extract_real_data_advanced():
    """Extrae datos reales usando métodos avanzados"""
    
    print("🎮 EXTRACTOR AVANZADO DE DATOS REALES")
    print("=" * 60)
    print("Partida: 10477434")
    print("Usuario: ranvfy")
    print()
    
    async with aiohttp.ClientSession() as session:
        try:
            # 1. Login
            print("🔐 Iniciando sesión...")
            await login(session)
            
            # 2. Acceder a la partida con diferentes métodos
            print("🎯 Accediendo a la partida con múltiples métodos...")
            
            # Método 1: Acceso directo a la partida
            await try_direct_game_access(session)
            
            # Método 2: Buscar en el dashboard
            await try_dashboard_access(session)
            
            # Método 3: Buscar en el perfil del usuario
            await try_profile_access(session)
            
            # Método 4: Buscar en la lista de partidas
            await try_games_list_access(session)
        
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

async def try_direct_game_access(session):
    """Intenta acceso directo a la partida"""
    
    print("\n🔍 MÉTODO 1: Acceso directo a la partida")
    print("-" * 50)
    
    game_urls = [
        "https://www.callofwar.com/play.php?L=3&bust=1&gameID=10477434",
        "https://www.callofwar.com/game.php?L=3&bust=1&gameID=10477434",
        "https://www.callofwar.com/game.php?gameID=10477434",
        "https://www.callofwar.com/play.php?gameID=10477434"
    ]
    
    for url in game_urls:
        try:
            print(f"🔍 Probando: {url}")
            async with session.get(url) as response:
                if response.status == 200:
                    html = await response.text()
                    
                    # Buscar datos del juego
                    countries = find_countries_in_html(html)
                    if countries:
                        print(f"✅ PAÍSES ENCONTRADOS: {countries}")
                        return countries
                    
                    # Buscar en JavaScript
                    game_data = find_game_data_in_javascript(html)
                    if game_data:
                        print(f"✅ DATOS DEL JUEGO: {list(game_data.keys()) if isinstance(game_data, dict) else 'Lista'}")
                        return game_data
                    
                    print("⚠️ Página accesible pero sin datos estructurados")
                else:
                    print(f"❌ Error {response.status}")
        except Exception as e:
            print(f"❌ Error: {e}")

async def try_dashboard_access(session):
    """Intenta acceso al dashboard"""
    
    print("\n🔍 MÉTODO 2: Acceso al dashboard")
    print("-" * 50)
    
    dashboard_urls = [
        "https://www.callofwar.com/dashboard.php",
        "https://www.callofwar.com/index.php?id=328",
        "https://www.callofwar.com/main.php",
        "https://www.callofwar.com/user.php"
    ]
    
    for url in dashboard_urls:
        try:
            print(f"🔍 Probando: {url}")
            async with session.get(url) as response:
                if response.status == 200:
                    html = await response.text()
                    
                    # Buscar lista de partidas
                    games = find_games_in_html(html)
                    if games:
                        print(f"✅ PARTIDAS ENCONTRADAS: {len(games)}")
                        for game in games:
                            print(f"   - {game}")
                        
                        # Buscar la partida específica
                        target_game = None
                        for game in games:
                            if "10477434" in str(game):
                                target_game = game
                                break
                        
                        if target_game:
                            print(f"✅ PARTIDA TARGET ENCONTRADA: {target_game}")
                            return target_game
                    
                    print("⚠️ Dashboard accesible pero sin partidas visibles")
                else:
                    print(f"❌ Error {response.status}")
        except Exception as e:
            print(f"❌ Error: {e}")

async def try_profile_access(session):
    """Intenta acceso al perfil del usuario"""
    
    print("\n🔍 MÉTODO 3: Acceso al perfil del usuario")
    print("-" * 50)
    
    profile_urls = [
        "https://www.callofwar.com/profile.php",
        "https://www.callofwar.com/user.php",
        "https://www.callofwar.com/player.php",
        "https://www.callofwar.com/account.php"
    ]
    
    for url in profile_urls:
        try:
            print(f"🔍 Probando: {url}")
            async with session.get(url) as response:
                if response.status == 200:
                    html = await response.text()
                    
                    # Buscar partidas del usuario
                    user_games = find_user_games_in_html(html)
                    if user_games:
                        print(f"✅ PARTIDAS DEL USUARIO: {len(user_games)}")
                        for game in user_games:
                            print(f"   - {game}")
                        
                        # Buscar la partida específica
                        target_game = None
                        for game in user_games:
                            if "10477434" in str(game):
                                target_game = game
                                break
                        
                        if target_game:
                            print(f"✅ PARTIDA TARGET ENCONTRADA: {target_game}")
                            return target_game
                    
                    print("⚠️ Perfil accesible pero sin partidas visibles")
                else:
                    print(f"❌ Error {response.status}")
        except Exception as e:
            print(f"❌ Error: {e}")

async def try_games_list_access(session):
    """Intenta acceso a la lista de partidas"""
    
    print("\n🔍 MÉTODO 4: Acceso a la lista de partidas")
    print("-" * 50)
    
    games_list_urls = [
        "https://www.callofwar.com/games.php",
        "https://www.callofwar.com/gamelist.php",
        "https://www.callofwar.com/play.php",
        "https://www.callofwar.com/index.php?id=328&L=3"
    ]
    
    for url in games_list_urls:
        try:
            print(f"🔍 Probando: {url}")
            async with session.get(url) as response:
                if response.status == 200:
                    html = await response.text()
                    
                    # Buscar partidas activas
                    active_games = find_active_games_in_html(html)
                    if active_games:
                        print(f"✅ PARTIDAS ACTIVAS: {len(active_games)}")
                        for game in active_games:
                            print(f"   - {game}")
                        
                        # Buscar la partida específica
                        target_game = None
                        for game in active_games:
                            if "10477434" in str(game):
                                target_game = game
                                break
                        
                        if target_game:
                            print(f"✅ PARTIDA TARGET ENCONTRADA: {target_game}")
                            return target_game
                    
                    print("⚠️ Lista accesible pero sin partidas visibles")
                else:
                    print(f"❌ Error {response.status}")
        except Exception as e:
            print(f"❌ Error: {e}")

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

def find_games_in_html(html):
    """Busca partidas en el HTML"""
    
    soup = BeautifulSoup(html, 'html.parser')
    games = []
    
    # Buscar enlaces a partidas
    links = soup.find_all('a', href=True)
    for link in links:
        href = link.get('href', '')
        text = link.get_text(strip=True)
        
        if 'game' in href.lower() or 'play' in href.lower():
            if text and len(text) > 2:
                games.append(f"{text} ({href})")
    
    # Buscar en elementos con datos de partidas
    game_elements = soup.find_all(['div', 'span'], class_=re.compile(r'game|play|match'))
    for element in game_elements:
        text = element.get_text(strip=True)
        if text and len(text) > 2 and len(text) < 100:
            games.append(text)
    
    return games if games else None

def find_user_games_in_html(html):
    """Busca partidas del usuario en el HTML"""
    
    soup = BeautifulSoup(html, 'html.parser')
    games = []
    
    # Buscar en tablas de partidas
    tables = soup.find_all('table')
    for table in tables:
        rows = table.find_all('tr')
        for row in rows:
            cells = row.find_all(['td', 'th'])
            for cell in cells:
                text = cell.get_text(strip=True)
                if text and ('game' in text.lower() or 'play' in text.lower() or '10477434' in text):
                    games.append(text)
    
    return games if games else None

def find_active_games_in_html(html):
    """Busca partidas activas en el HTML"""
    
    soup = BeautifulSoup(html, 'html.parser')
    games = []
    
    # Buscar en listas de partidas
    lists = soup.find_all(['ul', 'ol'])
    for list_elem in lists:
        items = list_elem.find_all('li')
        for item in items:
            text = item.get_text(strip=True)
            if text and len(text) > 2 and len(text) < 100:
                games.append(text)
    
    return games if games else None

if __name__ == "__main__":
    asyncio.run(extract_real_data_advanced())