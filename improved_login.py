#!/usr/bin/env python3
"""
Sistema de login mejorado para Call of War
"""

import asyncio
import aiohttp
from bs4 import BeautifulSoup
import re
import json

async def test_login():
    """Prueba el login con diferentes métodos"""
    
    print("🔐 Probando login en Call of War...")
    
    async with aiohttp.ClientSession() as session:
        try:
            # Obtener página de login
            print("📄 Obteniendo página de login...")
            async with session.get("https://www.callofwar.com/login.php") as response:
                if response.status != 200:
                    print(f"❌ Error HTTP: {response.status}")
                    return
                
                html = await response.text()
                soup = BeautifulSoup(html, 'html.parser')
                
                print("✅ Página de login obtenida")
                
                # Buscar formulario de login
                forms = soup.find_all('form')
                print(f"📋 Encontrados {len(forms)} formularios")
                
                for i, form in enumerate(forms):
                    print(f"\nFormulario {i+1}:")
                    print(f"  Action: {form.get('action', 'N/A')}")
                    print(f"  Method: {form.get('method', 'N/A')}")
                    print(f"  ID: {form.get('id', 'N/A')}")
                    print(f"  Class: {form.get('class', 'N/A')}")
                    
                    # Buscar campos de input
                    inputs = form.find_all('input')
                    print(f"  Inputs encontrados: {len(inputs)}")
                    for inp in inputs:
                        print(f"    - {inp.get('name', 'sin nombre')}: {inp.get('type', 'text')}")
                
                # Buscar específicamente el formulario de login
                login_form = None
                for form in forms:
                    if form.find('input', {'name': 'username'}) or form.find('input', {'name': 'user'}) or form.find('input', {'name': 'email'}):
                        login_form = form
                        break
                
                if not login_form:
                    print("❌ No se encontró formulario de login")
                    return
                
                print(f"\n✅ Formulario de login encontrado")
                print(f"Action: {login_form.get('action', 'N/A')}")
                
                # Extraer campos necesarios
                username_field = None
                password_field = None
                csrf_field = None
                
                for inp in login_form.find_all('input'):
                    name = inp.get('name', '').lower()
                    if 'user' in name or 'email' in name or 'login' in name:
                        username_field = inp.get('name')
                    elif 'pass' in name:
                        password_field = inp.get('name')
                    elif 'csrf' in name or 'token' in name:
                        csrf_field = inp.get('name')
                
                print(f"Username field: {username_field}")
                print(f"Password field: {password_field}")
                print(f"CSRF field: {csrf_field}")
                
                # Obtener valor del CSRF si existe
                csrf_value = ""
                if csrf_field:
                    csrf_input = login_form.find('input', {'name': csrf_field})
                    if csrf_input:
                        csrf_value = csrf_input.get('value', '')
                
                print(f"CSRF value: {csrf_value}")
                
                # Intentar login
                print("\n🔑 Intentando login...")
                
                login_data = {}
                if username_field:
                    login_data[username_field] = "ranvfy"
                if password_field:
                    login_data[password_field] = "5zb6-u_JxWfaeH."
                if csrf_field and csrf_value:
                    login_data[csrf_field] = csrf_value
                
                print(f"Datos de login: {login_data}")
                
                # Determinar URL de destino
                action = login_form.get('action', '')
                if action.startswith('/'):
                    login_url = f"https://www.callofwar.com{action}"
                elif action.startswith('http'):
                    login_url = action
                else:
                    login_url = f"https://www.callofwar.com/{action}"
                
                print(f"URL de login: {login_url}")
                
                # Realizar login
                async with session.post(login_url, data=login_data) as response:
                    print(f"Status: {response.status}")
                    
                    if response.status == 200:
                        text = await response.text()
                        
                        # Verificar si el login fue exitoso
                        if any(keyword in text.lower() for keyword in ['dashboard', 'game', 'logout', 'profile']):
                            print("✅ Login exitoso!")
                            
                            # Intentar acceder a la partida
                            print("\n🎮 Accediendo a la partida 10477434...")
                            
                            game_urls = [
                                f"https://www.callofwar.com/game.php?L=3&bust=1#/game/:gameID=10477434",
                                f"https://www.callofwar.com/game.php?gameID=10477434",
                                f"https://www.callofwar.com/game.php?L=3&gameID=10477434"
                            ]
                            
                            for url in game_urls:
                                print(f"Probando: {url}")
                                async with session.get(url) as game_response:
                                    if game_response.status == 200:
                                        game_html = await game_response.text()
                                        print(f"✅ Acceso a partida exitoso (status: {game_response.status})")
                                        
                                        # Buscar datos del juego
                                        soup = BeautifulSoup(game_html, 'html.parser')
                                        
                                        # Buscar en scripts
                                        scripts = soup.find_all('script')
                                        for script in scripts:
                                            if script.string and any(keyword in script.string.lower() for keyword in ['game', 'country', 'troop', 'player']):
                                                print("✅ Scripts del juego encontrados")
                                                # Guardar el HTML para análisis
                                                with open("game_page.html", "w", encoding="utf-8") as f:
                                                    f.write(game_html)
                                                print("💾 Página guardada en game_page.html")
                                                break
                                        break
                                    else:
                                        print(f"❌ Error accediendo a partida: {game_response.status}")
                        else:
                            print("❌ Login falló - página no contiene indicadores de éxito")
                            print("Contenido de la página:")
                            print(text[:500] + "..." if len(text) > 500 else text)
                    else:
                        print(f"❌ Error en login: {response.status}")
                        text = await response.text()
                        print("Respuesta:")
                        print(text[:500] + "..." if len(text) > 500 else text)
        
        except Exception as e:
            print(f"❌ Error: {e}")
            import traceback
            traceback.print_exc()

if __name__ == "__main__":
    asyncio.run(test_login())