#!/usr/bin/env python3
"""
Extrae datos del cliente web de Call of War
"""

import asyncio
import aiohttp
from bs4 import BeautifulSoup
import re
import json

async def extract_from_game_client():
    """Extrae datos del cliente web del juego"""
    
    print("🎮 Accediendo al cliente web de Call of War...")
    
    async with aiohttp.ClientSession() as session:
        try:
            # Primero hacer login
            print("🔐 Iniciando sesión...")
            
            # Obtener página de login
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
                    print("❌ No se encontró formulario de login")
                    return
                
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
                        print(f"❌ Error en login: {response.status}")
                        return
                    
                    text = await response.text()
                    if not any(keyword in text.lower() for keyword in ['dashboard', 'game', 'logout', 'profile', 'welcome']):
                        print("❌ Login falló")
                        return
                    
                    print("✅ Login exitoso")
            
            # Ahora acceder al cliente web
            print("🌐 Accediendo al cliente web...")
            
            # URL del cliente web extraída del iframe
            client_url = "https://www.callofwar.com/clients/ww2-client-ultimate/ww2-client-ultimate_live/index.html?L=3&bust=1&spa=determineUber&id=328&lang=es&userID=40944494&authHash=4c1018a1aa85a3e4c54776ce621b141935030eb1&authTstamp=1757111229&gameURL=https%3A%2F%2Fwww.callofwar.com%2Fplay.php%3FL%3D3%26bust%3D1&logoutURL=https%3A%2F%2Fwww.callofwar.com%2Findex.php%3Fid%3D304%26L%3D3&baseSiteURL=https%3A%2F%2Fwww.callofwar.com%2Findex.php&v=1757111229&titleID=510&testLevel=0&chatServer=chat.callofwar.com&chatAuth=5057ab0cc3c82dc07df625f0e0c1166d6c7e5888&chatAuthTstamp=1757111229&uberAuthHash=78ad7354d00d3ad5f282412f9e545fe9ea14aeb5&uberAuthTstamp=1757111229&rights=chat&canSwitchClient=1&upscalingSettings=eyJsb3ciOnsiYXNzZXQiOiJsb3ciLCJzdGFtcHMiOjAuMSwibWFwIjowLjUsInBhcnRpY2xlcyI6ZmFsc2UsImFsbG9jYXRpb25zRmFjdG9yIjoxfSwiaGlnaCI6eyJhc3NldCI6ImhpZ2giLCJzdGFtcHMiOjEsIm1hcCI6MSwicGFydGljbGVzIjp0cnVlLCJhbGxvY2F0aW9uc0ZhY3RvciI6MX19&attOpenDelayMs=50000&attPopupType=5&ws=www.callofwar.com&ggsShopApiLevel=v2&warchest=1"
            
            async with session.get(client_url) as response:
                if response.status != 200:
                    print(f"❌ Error accediendo al cliente: {response.status}")
                    return
                
                print("✅ Cliente web accedido")
                
                # El cliente web probablemente sea una aplicación JavaScript
                # Necesitamos buscar APIs o endpoints que devuelvan datos JSON
                
                # Intentar diferentes endpoints de API
                api_endpoints = [
                    "https://www.callofwar.com/api/game/10477434",
                    "https://www.callofwar.com/api/game/data/10477434",
                    "https://www.callofwar.com/api/player/game/10477434",
                    "https://www.callofwar.com/play.php?L=3&bust=1&gameID=10477434",
                    "https://www.callofwar.com/game.php?L=3&bust=1&gameID=10477434&format=json",
                    "https://www.callofwar.com/api/v1/game/10477434",
                    "https://www.callofwar.com/api/v2/game/10477434"
                ]
                
                for endpoint in api_endpoints:
                    print(f"🔍 Probando endpoint: {endpoint}")
                    try:
                        async with session.get(endpoint) as api_response:
                            if api_response.status == 200:
                                content_type = api_response.headers.get('content-type', '')
                                if 'json' in content_type:
                                    data = await api_response.json()
                                    print(f"✅ Datos JSON encontrados en {endpoint}")
                                    print(f"Claves: {list(data.keys()) if isinstance(data, dict) else 'No es dict'}")
                                    
                                    # Guardar datos
                                    with open("api_data.json", "w", encoding="utf-8") as f:
                                        json.dump(data, f, indent=2, ensure_ascii=False)
                                    print("💾 Datos guardados en api_data.json")
                                    break
                                else:
                                    text = await api_response.text()
                                    if len(text) > 100:
                                        print(f"⚠️  Respuesta no JSON: {text[:200]}...")
                                    else:
                                        print(f"⚠️  Respuesta: {text}")
                            else:
                                print(f"❌ Error {api_response.status}")
                    except Exception as e:
                        print(f"❌ Error: {e}")
                
                # Si no encontramos APIs, intentar extraer datos del HTML del cliente
                print("\n🔍 Analizando HTML del cliente...")
                html = await response.text()
                soup = BeautifulSoup(html, 'html.parser')
                
                # Buscar scripts que contengan datos
                scripts = soup.find_all('script')
                for script in scripts:
                    if script.string:
                        # Buscar patrones de datos del juego
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
                                print(f"✅ Datos encontrados con patrón: {pattern}")
                                try:
                                    data = json.loads(matches[0])
                                    print(f"✅ JSON parseado exitosamente")
                                    print(f"Claves: {list(data.keys()) if isinstance(data, dict) else 'No es dict'}")
                                    
                                    # Guardar datos
                                    with open("client_data.json", "w", encoding="utf-8") as f:
                                        json.dump(data, f, indent=2, ensure_ascii=False)
                                    print("💾 Datos guardados en client_data.json")
                                    break
                                except json.JSONDecodeError as e:
                                    print(f"❌ Error parseando JSON: {e}")
                                    continue
                
                # Guardar HTML del cliente para análisis
                with open("client_page.html", "w", encoding="utf-8") as f:
                    f.write(html)
                print("💾 HTML del cliente guardado en client_page.html")
        
        except Exception as e:
            print(f"❌ Error: {e}")
            import traceback
            traceback.print_exc()

if __name__ == "__main__":
    asyncio.run(extract_from_game_client())