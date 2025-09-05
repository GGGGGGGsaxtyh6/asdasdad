#!/usr/bin/env python3
"""
Extractor directo usando cookies sin login
"""

import asyncio
import aiohttp
from bs4 import BeautifulSoup
import re
import json

async def extract_direct_with_cookies():
    """Extrae datos directamente usando cookies"""
    
    print("🎮 EXTRACTOR DIRECTO CON COOKIES")
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
        'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8',
        'Accept-Language': 'en-US,en;q=0.9',
        'Accept-Encoding': 'gzip, deflate, br',
        'Connection': 'keep-alive',
        'Referer': 'https://www.callofwar.com/'
    }
    
    async with aiohttp.ClientSession(cookies=cookies, headers=headers) as session:
        try:
            # Intentar diferentes URLs con las cookies
            print("🎯 Intentando URLs con cookies...")
            
            urls = [
                "https://www.callofwar.com/game.php?L=3&bust=1&gameID=10477434",
                "https://www.callofwar.com/play.php?L=3&bust=1&gameID=10477434",
                "https://www.callofwar.com/game.php?gameID=10477434",
                "https://www.callofwar.com/play.php?gameID=10477434",
                "https://www.callofwar.com/game.php?L=3&gameID=10477434",
                "https://www.callofwar.com/play.php?L=3&gameID=10477434"
            ]
            
            for url in urls:
                try:
                    print(f"🔍 Probando: {url}")
                    async with session.get(url) as response:
                        print(f"   Status: {response.status}")
                        
                        if response.status == 200:
                            html = await response.text()
                            
                            # Buscar países
                            countries = find_countries_in_html(html)
                            if countries:
                                print(f"✅ PAÍSES ENCONTRADOS: {countries}")
                                
                                # Guardar países
                                with open("real_countries_final.json", "w", encoding="utf-8") as f:
                                    json.dump(countries, f, indent=2, ensure_ascii=False)
                                print("💾 Países guardados en real_countries_final.json")
                                
                                return countries
                            
                            # Buscar datos del juego
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
                                with open("real_countries_text.json", "w", encoding="utf-8") as f:
                                    json.dump(countries, f, indent=2, ensure_ascii=False)
                                print("💾 Países guardados en real_countries_text.json")
                                
                                return countries
                            
                            print("   ⚠️ Página accesible pero sin datos estructurados")
                            
                            # Guardar HTML para análisis
                            with open(f"page_analysis_{len(urls)}.html", "w", encoding="utf-8") as f:
                                f.write(html)
                            print("   💾 HTML guardado para análisis")
                            
                        elif response.status == 403:
                            print("   ❌ Acceso denegado - cookies inválidas")
                        elif response.status == 404:
                            print("   ❌ Página no encontrada")
                        else:
                            print(f"   ❌ Error {response.status}")
                            
                except Exception as e:
                    print(f"   ❌ Error: {e}")
            
            # Intentar con diferentes headers
            print("\n🔍 Intentando con diferentes headers...")
            await try_different_headers(session)
            
            # Intentar con diferentes cookies
            print("\n🔍 Intentando con diferentes cookies...")
            await try_different_cookies(session)
        
        except Exception as e:
            print(f"❌ Error: {e}")
            import traceback
            traceback.print_exc()

async def try_different_headers(session):
    """Intenta con diferentes headers"""
    
    header_variations = [
        {
            'Accept': 'application/json, text/plain, */*',
            'X-Requested-With': 'XMLHttpRequest'
        },
        {
            'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8',
            'Cache-Control': 'no-cache'
        },
        {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    ]
    
    for headers in header_variations:
        try:
            print(f"🔍 Probando headers: {list(headers.keys())}")
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

async def try_different_cookies(session):
    """Intenta con diferentes cookies"""
    
    cookie_variations = [
        {
            'bl_sid': '08f90ff7d31602d49fe3a0330c4175f7',
            'puid': '40944494'
        },
        {
            'bl_sid': '08f90ff7d31602d49fe3a0330c4175f7',
            'puid': '40944494',
            'sup_hist': '%26uid%3D40944494%26id%3D08f90ff7d31602d49fe3a0330c4175f7'
        },
        {
            'bl_sid': '08f90ff7d31602d49fe3a0330c4175f7',
            'puid': '40944494',
            'bl_lang': '0'
        }
    ]
    
    for cookies in cookie_variations:
        try:
            print(f"🔍 Probando cookies: {list(cookies.keys())}")
            async with aiohttp.ClientSession(cookies=cookies) as new_session:
                async with new_session.get("https://www.callofwar.com/game.php?gameID=10477434") as response:
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

if __name__ == "__main__":
    asyncio.run(extract_direct_with_cookies())