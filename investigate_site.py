#!/usr/bin/env python3
"""
Investiga la estructura real del sitio Call of War
"""

import asyncio
import aiohttp
from bs4 import BeautifulSoup
import re

async def investigate_call_of_war():
    """Investiga la estructura del sitio Call of War"""
    
    print("🔍 Investigando Call of War...")
    
    async with aiohttp.ClientSession() as session:
        try:
            # Probar diferentes URLs base
            base_urls = [
                "https://www.callofwar.com",
                "https://callofwar.com",
                "https://www.callofwar.com/index.php",
                "https://www.callofwar.com/main.php"
            ]
            
            for base_url in base_urls:
                print(f"\n🌐 Probando: {base_url}")
                
                try:
                    async with session.get(base_url) as response:
                        print(f"   Status: {response.status}")
                        
                        if response.status == 200:
                            html = await response.text()
                            soup = BeautifulSoup(html, 'html.parser')
                            
                            # Buscar enlaces de login
                            login_links = []
                            for link in soup.find_all('a', href=True):
                                href = link['href'].lower()
                                if any(keyword in href for keyword in ['login', 'signin', 'auth']):
                                    login_links.append(link['href'])
                            
                            if login_links:
                                print(f"   ✅ Enlaces de login encontrados: {login_links}")
                            else:
                                print("   ❌ No se encontraron enlaces de login")
                            
                            # Buscar formularios
                            forms = soup.find_all('form')
                            print(f"   📋 Formularios encontrados: {len(forms)}")
                            
                            for i, form in enumerate(forms):
                                action = form.get('action', 'N/A')
                                method = form.get('method', 'N/A')
                                print(f"      Form {i+1}: {method} -> {action}")
                                
                                # Buscar campos de usuario/contraseña
                                inputs = form.find_all('input')
                                for inp in inputs:
                                    name = inp.get('name', '')
                                    input_type = inp.get('type', '')
                                    if 'user' in name.lower() or 'pass' in name.lower():
                                        print(f"         - {name} ({input_type})")
                            
                            # Buscar JavaScript que pueda contener URLs
                            scripts = soup.find_all('script')
                            for script in scripts:
                                if script.string:
                                    # Buscar URLs en JavaScript
                                    urls = re.findall(r'["\']([^"\']*login[^"\']*)["\']', script.string, re.IGNORECASE)
                                    if urls:
                                        print(f"   🔗 URLs de login en JS: {urls}")
                            
                            # Guardar la página para análisis
                            with open(f"page_{base_url.split('//')[1].replace('/', '_')}.html", "w", encoding="utf-8") as f:
                                f.write(html)
                            print(f"   💾 Página guardada")
                            
                        else:
                            print(f"   ❌ Error: {response.status}")
                
                except Exception as e:
                    print(f"   ❌ Error: {e}")
            
            # Probar acceso directo a la partida
            print(f"\n🎮 Probando acceso directo a la partida...")
            game_urls = [
                "https://www.callofwar.com/game.php?L=3&bust=1#/game/:gameID=10477434",
                "https://www.callofwar.com/game.php?gameID=10477434",
                "https://www.callofwar.com/game.php?L=3&gameID=10477434",
                "https://www.callofwar.com/game/10477434",
                "https://www.callofwar.com/game.php?L=3&bust=1&gameID=10477434"
            ]
            
            for url in game_urls:
                print(f"\n🎯 Probando: {url}")
                try:
                    async with session.get(url) as response:
                        print(f"   Status: {response.status}")
                        
                        if response.status == 200:
                            html = await response.text()
                            
                            # Buscar indicadores de que es la página correcta
                            indicators = ['call of war', 'game', 'troops', 'countries', 'players']
                            found_indicators = [ind for ind in indicators if ind in html.lower()]
                            
                            if found_indicators:
                                print(f"   ✅ Indicadores encontrados: {found_indicators}")
                                
                                # Buscar datos del juego
                                soup = BeautifulSoup(html, 'html.parser')
                                scripts = soup.find_all('script')
                                
                                game_data_found = False
                                for script in scripts:
                                    if script.string:
                                        # Buscar patrones de datos del juego
                                        patterns = [
                                            r'gameData\s*=\s*({.*?});',
                                            r'window\.gameData\s*=\s*({.*?});',
                                            r'var\s+gameData\s*=\s*({.*?});',
                                            r'countries\s*=\s*(\[.*?\]);',
                                            r'troops\s*=\s*(\[.*?\]);'
                                        ]
                                        
                                        for pattern in patterns:
                                            matches = re.findall(pattern, script.string, re.DOTALL)
                                            if matches:
                                                print(f"   ✅ Datos del juego encontrados con patrón: {pattern}")
                                                game_data_found = True
                                                
                                                # Guardar el script completo
                                                with open("game_script.js", "w", encoding="utf-8") as f:
                                                    f.write(script.string)
                                                print("   💾 Script guardado en game_script.js")
                                                break
                                        
                                        if game_data_found:
                                            break
                                
                                if not game_data_found:
                                    print("   ⚠️  No se encontraron datos estructurados del juego")
                                
                                # Guardar la página
                                with open("game_page.html", "w", encoding="utf-8") as f:
                                    f.write(html)
                                print("   💾 Página de juego guardada")
                                
                            else:
                                print("   ❌ No parece ser la página del juego")
                        else:
                            print(f"   ❌ Error: {response.status}")
                
                except Exception as e:
                    print(f"   ❌ Error: {e}")
        
        except Exception as e:
            print(f"❌ Error general: {e}")

if __name__ == "__main__":
    asyncio.run(investigate_call_of_war())