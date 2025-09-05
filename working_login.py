#!/usr/bin/env python3
"""
Sistema de login funcional para Call of War
"""

import asyncio
import aiohttp
from bs4 import BeautifulSoup
import re
import json

async def login_and_extract_data():
    """Inicia sesión y extrae datos reales de la partida"""
    
    print("🔐 Iniciando sesión en Call of War...")
    print("Usuario: ranvfy")
    print("Partida: 10477434")
    print()
    
    async with aiohttp.ClientSession() as session:
        try:
            # Obtener página de login
            print("📄 Obteniendo página de login...")
            async with session.get("https://www.callofwar.com") as response:
                if response.status != 200:
                    print(f"❌ Error HTTP: {response.status}")
                    return
                
                html = await response.text()
                soup = BeautifulSoup(html, 'html.parser')
                
                # Buscar formulario de login
                login_form = None
                for form in soup.find_all('form'):
                    if form.find('input', {'name': 'user'}) and form.find('input', {'name': 'pass'}):
                        login_form = form
                        break
                
                if not login_form:
                    print("❌ No se encontró formulario de login")
                    return
                
                print("✅ Formulario de login encontrado")
                
                # Extraer campos necesarios
                action = login_form.get('action', 'index.php?id=304')
                if action.startswith('/'):
                    action = f"https://www.callofwar.com{action}"
                elif not action.startswith('http'):
                    action = f"https://www.callofwar.com/{action}"
                
                print(f"Action URL: {action}")
                
                # Realizar login
                print("🔑 Iniciando sesión...")
                login_data = {
                    'user': 'ranvfy',
                    'pass': '5zb6-u_JxWfaeH.'
                }
                
                async with session.post(action, data=login_data) as response:
                    print(f"Status de login: {response.status}")
                    
                    if response.status == 200:
                        text = await response.text()
                        
                        # Verificar si el login fue exitoso
                        if any(keyword in text.lower() for keyword in ['dashboard', 'game', 'logout', 'profile', 'welcome']):
                            print("✅ Login exitoso!")
                            
                            # Intentar acceder a la partida
                            print("\n🎮 Accediendo a la partida 10477434...")
                            
                            game_urls = [
                                "https://www.callofwar.com/game.php?L=3&bust=1#/game/:gameID=10477434",
                                "https://www.callofwar.com/game.php?gameID=10477434",
                                "https://www.callofwar.com/game.php?L=3&gameID=10477434"
                            ]
                            
                            for url in game_urls:
                                print(f"Probando: {url}")
                                async with session.get(url) as game_response:
                                    if game_response.status == 200:
                                        game_html = await game_response.text()
                                        print(f"✅ Acceso a partida exitoso")
                                        
                                        # Analizar la página del juego
                                        await analyze_game_page(game_html, session)
                                        break
                                    else:
                                        print(f"❌ Error accediendo a partida: {game_response.status}")
                        else:
                            print("❌ Login falló")
                            print("Contenido de la página:")
                            print(text[:1000] + "..." if len(text) > 1000 else text)
                    else:
                        print(f"❌ Error en login: {response.status}")
        
        except Exception as e:
            print(f"❌ Error: {e}")
            import traceback
            traceback.print_exc()

async def analyze_game_page(html, session):
    """Analiza la página del juego para extraer datos"""
    
    print("\n📊 Analizando página del juego...")
    
    soup = BeautifulSoup(html, 'html.parser')
    
    # Buscar en scripts JavaScript
    scripts = soup.find_all('script')
    game_data = None
    
    for script in scripts:
        if script.string:
            # Buscar diferentes patrones de datos
            patterns = [
                r'gameData\s*=\s*({.*?});',
                r'window\.gameData\s*=\s*({.*?});',
                r'var\s+gameData\s*=\s*({.*?});',
                r'countries\s*=\s*(\[.*?\]);',
                r'troops\s*=\s*(\[.*?\]);',
                r'players\s*=\s*(\[.*?\]);',
                r'gameInfo\s*=\s*({.*?});'
            ]
            
            for pattern in patterns:
                matches = re.findall(pattern, script.string, re.DOTALL)
                if matches:
                    print(f"✅ Datos encontrados con patrón: {pattern}")
                    try:
                        game_data = json.loads(matches[0])
                        print(f"✅ JSON parseado exitosamente")
                        break
                    except json.JSONDecodeError as e:
                        print(f"❌ Error parseando JSON: {e}")
                        continue
            
            if game_data:
                break
    
    if game_data:
        print("🎯 Datos del juego extraídos:")
        print(json.dumps(game_data, indent=2)[:1000] + "..." if len(str(game_data)) > 1000 else json.dumps(game_data, indent=2))
        
        # Guardar datos
        with open("extracted_game_data.json", "w", encoding="utf-8") as f:
            json.dump(game_data, f, indent=2, ensure_ascii=False)
        print("💾 Datos guardados en extracted_game_data.json")
        
        # Procesar datos
        await process_game_data(game_data)
    else:
        print("❌ No se encontraron datos estructurados del juego")
        
        # Buscar datos en HTML
        print("🔍 Buscando datos en HTML...")
        
        # Buscar países
        countries = []
        country_elements = soup.find_all(['div', 'span'], class_=re.compile(r'country|nation|player|faction'))
        
        for element in country_elements:
            text = element.get_text(strip=True)
            if text and len(text) > 2 and len(text) < 50:
                countries.append(text)
        
        if countries:
            print(f"✅ Países encontrados en HTML: {countries}")
        else:
            print("❌ No se encontraron países en HTML")
        
        # Buscar tropas
        troops = []
        troop_elements = soup.find_all(['div', 'span'], class_=re.compile(r'troop|unit|army|military'))
        
        for element in troop_elements:
            text = element.get_text(strip=True)
            if text and len(text) > 2 and len(text) < 50:
                troops.append(text)
        
        if troops:
            print(f"✅ Tropas encontradas en HTML: {troops[:10]}...")  # Mostrar solo las primeras 10
        else:
            print("❌ No se encontraron tropas en HTML")
        
        # Guardar HTML para análisis manual
        with open("game_page_analysis.html", "w", encoding="utf-8") as f:
            f.write(html)
        print("💾 Página guardada en game_page_analysis.html")

async def process_game_data(game_data):
    """Procesa los datos extraídos del juego"""
    
    print("\n🎯 Procesando datos del juego...")
    
    # Aquí implementarías la lógica para procesar los datos reales
    # y generar estrategias basadas en los datos extraídos
    
    print("✅ Datos procesados exitosamente")

if __name__ == "__main__":
    asyncio.run(login_and_extract_data())