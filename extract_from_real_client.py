#!/usr/bin/env python3
"""
Extrae datos del cliente real de Call of War
Usa la URL correcta del iframe
"""

import asyncio
import aiohttp
from bs4 import BeautifulSoup
import re
import json

async def extract_from_real_client():
    """Extrae datos del cliente real de Call of War"""
    
    print("🎮 EXTRAYENDO DATOS DEL CLIENTE REAL DE CALL OF WAR")
    print("=" * 60)
    print("Partida: 10477434")
    print("Usuario: ranvfy")
    print()
    
    async with aiohttp.ClientSession() as session:
        try:
            # 1. Login
            print("🔐 Iniciando sesión...")
            await login(session)
            
            # 2. Acceder al cliente real
            print("🌐 Accediendo al cliente real...")
            client_url = "https://www.callofwar.com/clients/ww2-client-ultimate/ww2-client-ultimate_live/index.html?L=3&bust=1&spa=determineUber&id=328&lang=es&userID=40944494&authHash=4c1018a1aa85a3e4c54776ce621b141935030eb1&authTstamp=1757111229&gameURL=https%3A%2F%2Fwww.callofwar.com%2Fplay.php%3FL%3D3%26bust%3D1&logoutURL=https%3A%2F%2Fwww.callofwar.com%2Findex.php%3Fid%3D304%26L%3D3&baseSiteURL=https%3A%2F%2Fwww.callofwar.com%2Findex.php&v=1757111229&titleID=510&testLevel=0&chatServer=chat.callofwar.com&chatAuth=5057ab0cc3c82dc07df625f0e0c1166d6c7e5888&chatAuthTstamp=1757111229&uberAuthHash=78ad7354d00d3ad5f282412f9e545fe9ea14aeb5&uberAuthTstamp=1757111229&rights=chat&canSwitchClient=1&upscalingSettings=eyJsb3ciOnsiYXNzZXQiOiJsb3ciLCJzdGFtcHMiOjAuMSwibWFwIjowLjUsInBhcnRpY2xlcyI6ZmFsc2UsImFsbG9jYXRpb25zRmFjdG9yIjoxfSwiaGlnaCI6eyJhc3NldCI6ImhpZ2giLCJzdGFtcHMiOjEsIm1hcCI6MSwicGFydGljbGVzIjp0cnVlLCJhbGxvY2F0aW9uc0ZhY3RvciI6MX19&attOpenDelayMs=50000&attPopupType=5&ws=www.callofwar.com&ggsShopApiLevel=v2&warchest=1"
            
            async with session.get(client_url) as response:
                if response.status != 200:
                    print(f"❌ Error accediendo al cliente: {response.status}")
                    return
                
                print("✅ Cliente accedido exitosamente")
                
                # Obtener HTML del cliente
                html = await response.text()
                
                # Guardar HTML para análisis
                with open("real_client.html", "w", encoding="utf-8") as f:
                    f.write(html)
                print("💾 HTML del cliente guardado")
                
                # Buscar países en el HTML
                countries = find_countries_in_html(html)
                if countries:
                    print(f"✅ PAÍSES ENCONTRADOS: {len(countries)}")
                    for i, country in enumerate(countries, 1):
                        print(f"   {i}. {country}")
                    
                    # Guardar países
                    with open("real_countries_found.json", "w", encoding="utf-8") as f:
                        json.dump(countries, f, indent=2, ensure_ascii=False)
                    print("💾 Países guardados en real_countries_found.json")
                    
                    # Buscar datos del juego
                    game_data = find_game_data_in_html(html)
                    if game_data:
                        print("✅ DATOS DEL JUEGO ENCONTRADOS")
                        print(f"Claves: {list(game_data.keys()) if isinstance(game_data, dict) else 'No es dict'}")
                        
                        with open("real_game_data_found.json", "w", encoding="utf-8") as f:
                            json.dump(game_data, f, indent=2, ensure_ascii=False)
                        print("💾 Datos del juego guardados")
                    else:
                        print("❌ No se encontraron datos estructurados del juego")
                
                else:
                    print("❌ No se encontraron países en el HTML")
                    
                    # Buscar en JavaScript
                    print("🔍 Buscando en JavaScript...")
                    js_data = find_data_in_javascript(html)
                    if js_data:
                        print("✅ Datos encontrados en JavaScript")
                        print(f"Tipo: {type(js_data)}")
                        if isinstance(js_data, dict):
                            print(f"Claves: {list(js_data.keys())}")
                        
                        with open("js_data_found.json", "w", encoding="utf-8") as f:
                            json.dump(js_data, f, indent=2, ensure_ascii=False)
                        print("💾 Datos JS guardados")
                    else:
                        print("❌ No se encontraron datos en JavaScript")
        
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

def find_game_data_in_html(html):
    """Busca datos del juego en el HTML"""
    
    soup = BeautifulSoup(html, 'html.parser')
    
    # Buscar en scripts
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

def find_data_in_javascript(html):
    """Busca datos en JavaScript"""
    
    soup = BeautifulSoup(html, 'html.parser')
    scripts = soup.find_all('script')
    
    all_data = {}
    
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
                        all_data[var_name] = data
                    except json.JSONDecodeError:
                        # Si no es JSON, guardar como string
                        all_data[var_name] = var_value
    
    return all_data if all_data else None

if __name__ == "__main__":
    asyncio.run(extract_from_real_client())