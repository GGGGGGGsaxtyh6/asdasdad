#!/usr/bin/env python3
"""
Extractor avanzado que no se rinde hasta conseguir los datos reales
"""

import asyncio
import aiohttp
from bs4 import BeautifulSoup
import re
import json
import time
import random

async def extract_real_data_persistent():
    """Extrae datos reales de forma persistente"""
    
    print("🎮 EXTRACTOR PERSISTENTE - NO SE RINDE")
    print("=" * 60)
    print("Partida: 10477434")
    print("Usuario: ranvfy")
    print()
    
    async with aiohttp.ClientSession() as session:
        try:
            # 1. Login
            print("🔐 Iniciando sesión...")
            await login(session)
            
            # 2. Intentar múltiples métodos de extracción
            print("🎯 Intentando múltiples métodos de extracción...")
            
            # Método 1: Buscar en cookies y headers
            await extract_from_cookies_and_headers(session)
            
            # Método 2: Buscar en respuestas AJAX
            await extract_from_ajax_responses(session)
            
            # Método 3: Buscar en WebSocket
            await extract_from_websocket(session)
            
            # Método 4: Buscar en localStorage/sessionStorage
            await extract_from_storage(session)
            
            # Método 5: Buscar en archivos de configuración
            await extract_from_config_files(session)
            
            # Método 6: Buscar en APIs ocultas
            await extract_from_hidden_apis(session)
            
            # Método 7: Buscar en archivos de datos
            await extract_from_data_files(session)
            
            # Método 8: Buscar en logs del servidor
            await extract_from_server_logs(session)
            
            # Método 9: Buscar en archivos de estado
            await extract_from_state_files(session)
            
            # Método 10: Buscar en archivos de sesión
            await extract_from_session_files(session)
        
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

async def extract_from_cookies_and_headers(session):
    """Extrae datos de cookies y headers"""
    
    print("\n🔍 MÉTODO 1: Cookies y Headers")
    print("-" * 50)
    
    # Obtener cookies
    cookies = session.cookie_jar
    print(f"🍪 Cookies encontradas: {len(cookies)}")
    
    for cookie in cookies:
        print(f"   - {cookie.key}: {cookie.value[:50]}...")
    
    # Buscar en headers de respuesta
    async with session.get("https://www.callofwar.com/game.php?gameID=10477434") as response:
        headers = response.headers
        print(f"📋 Headers de respuesta: {len(headers)}")
        
        for key, value in headers.items():
            if any(keyword in key.lower() for keyword in ['game', 'player', 'country', 'data']):
                print(f"   - {key}: {value}")

async def extract_from_ajax_responses(session):
    """Extrae datos de respuestas AJAX"""
    
    print("\n🔍 MÉTODO 2: Respuestas AJAX")
    print("-" * 50)
    
    # Buscar endpoints AJAX comunes
    ajax_endpoints = [
        "https://www.callofwar.com/ajax/game_data.php",
        "https://www.callofwar.com/ajax/game_info.php",
        "https://www.callofwar.com/ajax/player_data.php",
        "https://www.callofwar.com/ajax/country_data.php",
        "https://www.callofwar.com/ajax/troop_data.php",
        "https://www.callofwar.com/api/game/10477434/data",
        "https://www.callofwar.com/api/game/10477434/countries",
        "https://www.callofwar.com/api/game/10477434/players",
        "https://www.callofwar.com/api/game/10477434/troops",
        "https://www.callofwar.com/api/game/10477434/resources"
    ]
    
    for endpoint in ajax_endpoints:
        try:
            print(f"🔍 Probando AJAX: {endpoint}")
            async with session.get(endpoint) as response:
                if response.status == 200:
                    content_type = response.headers.get('content-type', '')
                    if 'json' in content_type:
                        data = await response.json()
                        print(f"✅ Datos JSON encontrados: {list(data.keys()) if isinstance(data, dict) else 'Lista'}")
                        
                        # Buscar países en los datos
                        countries = extract_countries_from_data(data)
                        if countries:
                            print(f"✅ PAÍSES ENCONTRADOS: {countries}")
                            return countries
                    else:
                        text = await response.text()
                        if len(text) > 100:
                            print(f"⚠️ Respuesta no JSON: {text[:200]}...")
                        else:
                            print(f"⚠️ Respuesta: {text}")
                else:
                    print(f"❌ Error {response.status}")
        except Exception as e:
            print(f"❌ Error: {e}")

async def extract_from_websocket(session):
    """Extrae datos de WebSocket"""
    
    print("\n🔍 MÉTODO 3: WebSocket")
    print("-" * 50)
    
    # Buscar URLs de WebSocket en el HTML
    async with session.get("https://www.callofwar.com/game.php?gameID=10477434") as response:
        if response.status == 200:
            html = await response.text()
            soup = BeautifulSoup(html, 'html.parser')
            
            # Buscar URLs de WebSocket
            scripts = soup.find_all('script')
            for script in scripts:
                if script.string:
                    ws_patterns = [
                        r'ws://[^"\']+',
                        r'wss://[^"\']+',
                        r'websocket[^"\']*',
                        r'socket[^"\']*'
                    ]
                    
                    for pattern in ws_patterns:
                        matches = re.findall(pattern, script.string, re.IGNORECASE)
                        if matches:
                            print(f"✅ WebSocket encontrado: {matches}")
                            return matches

async def extract_from_storage(session):
    """Extrae datos de localStorage/sessionStorage"""
    
    print("\n🔍 MÉTODO 4: localStorage/sessionStorage")
    print("-" * 50)
    
    # Buscar scripts que usen localStorage o sessionStorage
    async with session.get("https://www.callofwar.com/game.php?gameID=10477434") as response:
        if response.status == 200:
            html = await response.text()
            soup = BeautifulSoup(html, 'html.parser')
            
            scripts = soup.find_all('script')
            for script in scripts:
                if script.string:
                    storage_patterns = [
                        r'localStorage\.getItem\([^)]+\)',
                        r'sessionStorage\.getItem\([^)]+\)',
                        r'localStorage\[[^\]]+\]',
                        r'sessionStorage\[[^\]]+\]'
                    ]
                    
                    for pattern in storage_patterns:
                        matches = re.findall(pattern, script.string)
                        if matches:
                            print(f"✅ Storage encontrado: {matches}")

async def extract_from_config_files(session):
    """Extrae datos de archivos de configuración"""
    
    print("\n🔍 MÉTODO 5: Archivos de configuración")
    print("-" * 50)
    
    config_files = [
        "https://www.callofwar.com/config.json",
        "https://www.callofwar.com/game_config.json",
        "https://www.callofwar.com/player_config.json",
        "https://www.callofwar.com/settings.json",
        "https://www.callofwar.com/data.json",
        "https://www.callofwar.com/game_data.json"
    ]
    
    for config_file in config_files:
        try:
            print(f"🔍 Probando: {config_file}")
            async with session.get(config_file) as response:
                if response.status == 200:
                    content_type = response.headers.get('content-type', '')
                    if 'json' in content_type:
                        data = await response.json()
                        print(f"✅ Config encontrado: {list(data.keys()) if isinstance(data, dict) else 'Lista'}")
                        
                        countries = extract_countries_from_data(data)
                        if countries:
                            print(f"✅ PAÍSES ENCONTRADOS: {countries}")
                            return countries
                    else:
                        text = await response.text()
                        print(f"⚠️ Config no JSON: {text[:200]}...")
                else:
                    print(f"❌ Error {response.status}")
        except Exception as e:
            print(f"❌ Error: {e}")

async def extract_from_hidden_apis(session):
    """Extrae datos de APIs ocultas"""
    
    print("\n🔍 MÉTODO 6: APIs ocultas")
    print("-" * 50)
    
    # Buscar APIs ocultas en el HTML
    async with session.get("https://www.callofwar.com/game.php?gameID=10477434") as response:
        if response.status == 200:
            html = await response.text()
            soup = BeautifulSoup(html, 'html.parser')
            
            # Buscar URLs de API en scripts
            scripts = soup.find_all('script')
            api_urls = []
            
            for script in scripts:
                if script.string:
                    api_patterns = [
                        r'https://[^"\']*api[^"\']*',
                        r'https://[^"\']*data[^"\']*',
                        r'https://[^"\']*game[^"\']*',
                        r'https://[^"\']*player[^"\']*'
                    ]
                    
                    for pattern in api_patterns:
                        matches = re.findall(pattern, script.string, re.IGNORECASE)
                        api_urls.extend(matches)
            
            # Probar APIs encontradas
            for api_url in api_urls:
                try:
                    print(f"🔍 Probando API oculta: {api_url}")
                    async with session.get(api_url) as api_response:
                        if api_response.status == 200:
                            content_type = api_response.headers.get('content-type', '')
                            if 'json' in content_type:
                                data = await api_response.json()
                                print(f"✅ API oculta encontrada: {list(data.keys()) if isinstance(data, dict) else 'Lista'}")
                                
                                countries = extract_countries_from_data(data)
                                if countries:
                                    print(f"✅ PAÍSES ENCONTRADOS: {countries}")
                                    return countries
                except Exception as e:
                    print(f"❌ Error con API oculta: {e}")

async def extract_from_data_files(session):
    """Extrae datos de archivos de datos"""
    
    print("\n🔍 MÉTODO 7: Archivos de datos")
    print("-" * 50)
    
    data_files = [
        "https://www.callofwar.com/data/game_10477434.json",
        "https://www.callofwar.com/data/players.json",
        "https://www.callofwar.com/data/countries.json",
        "https://www.callofwar.com/data/troops.json",
        "https://www.callofwar.com/data/resources.json",
        "https://www.callofwar.com/game_data/10477434.json",
        "https://www.callofwar.com/player_data/40944494.json"
    ]
    
    for data_file in data_files:
        try:
            print(f"🔍 Probando: {data_file}")
            async with session.get(data_file) as response:
                if response.status == 200:
                    content_type = response.headers.get('content-type', '')
                    if 'json' in content_type:
                        data = await response.json()
                        print(f"✅ Archivo de datos encontrado: {list(data.keys()) if isinstance(data, dict) else 'Lista'}")
                        
                        countries = extract_countries_from_data(data)
                        if countries:
                            print(f"✅ PAÍSES ENCONTRADOS: {countries}")
                            return countries
                    else:
                        text = await response.text()
                        print(f"⚠️ Archivo no JSON: {text[:200]}...")
                else:
                    print(f"❌ Error {response.status}")
        except Exception as e:
            print(f"❌ Error: {e}")

async def extract_from_server_logs(session):
    """Extrae datos de logs del servidor"""
    
    print("\n🔍 MÉTODO 8: Logs del servidor")
    print("-" * 50)
    
    log_endpoints = [
        "https://www.callofwar.com/logs/game_10477434.log",
        "https://www.callofwar.com/logs/player_40944494.log",
        "https://www.callofwar.com/logs/access.log",
        "https://www.callofwar.com/logs/error.log"
    ]
    
    for log_endpoint in log_endpoints:
        try:
            print(f"🔍 Probando: {log_endpoint}")
            async with session.get(log_endpoint) as response:
                if response.status == 200:
                    text = await response.text()
                    print(f"✅ Log encontrado: {len(text)} caracteres")
                    
                    # Buscar países en el log
                    countries = find_countries_in_text(text)
                    if countries:
                        print(f"✅ PAÍSES ENCONTRADOS EN LOG: {countries}")
                        return countries
                else:
                    print(f"❌ Error {response.status}")
        except Exception as e:
            print(f"❌ Error: {e}")

async def extract_from_state_files(session):
    """Extrae datos de archivos de estado"""
    
    print("\n🔍 MÉTODO 9: Archivos de estado")
    print("-" * 50)
    
    state_files = [
        "https://www.callofwar.com/state/game_10477434.json",
        "https://www.callofwar.com/state/player_40944494.json",
        "https://www.callofwar.com/state/current.json",
        "https://www.callofwar.com/state/latest.json"
    ]
    
    for state_file in state_files:
        try:
            print(f"🔍 Probando: {state_file}")
            async with session.get(state_file) as response:
                if response.status == 200:
                    content_type = response.headers.get('content-type', '')
                    if 'json' in content_type:
                        data = await response.json()
                        print(f"✅ Archivo de estado encontrado: {list(data.keys()) if isinstance(data, dict) else 'Lista'}")
                        
                        countries = extract_countries_from_data(data)
                        if countries:
                            print(f"✅ PAÍSES ENCONTRADOS: {countries}")
                            return countries
                    else:
                        text = await response.text()
                        print(f"⚠️ Archivo no JSON: {text[:200]}...")
                else:
                    print(f"❌ Error {response.status}")
        except Exception as e:
            print(f"❌ Error: {e}")

async def extract_from_session_files(session):
    """Extrae datos de archivos de sesión"""
    
    print("\n🔍 MÉTODO 10: Archivos de sesión")
    print("-" * 50)
    
    session_files = [
        "https://www.callofwar.com/session/40944494.json",
        "https://www.callofwar.com/session/current.json",
        "https://www.callofwar.com/session/active.json",
        "https://www.callofwar.com/session/game_10477434.json"
    ]
    
    for session_file in session_files:
        try:
            print(f"🔍 Probando: {session_file}")
            async with session.get(session_file) as response:
                if response.status == 200:
                    content_type = response.headers.get('content-type', '')
                    if 'json' in content_type:
                        data = await response.json()
                        print(f"✅ Archivo de sesión encontrado: {list(data.keys()) if isinstance(data, dict) else 'Lista'}")
                        
                        countries = extract_countries_from_data(data)
                        if countries:
                            print(f"✅ PAÍSES ENCONTRADOS: {countries}")
                            return countries
                    else:
                        text = await response.text()
                        print(f"⚠️ Archivo no JSON: {text[:200]}...")
                else:
                    print(f"❌ Error {response.status}")
        except Exception as e:
            print(f"❌ Error: {e}")

def extract_countries_from_data(data):
    """Extrae países de datos JSON"""
    
    countries = []
    
    if isinstance(data, dict):
        # Buscar en diferentes claves posibles
        possible_keys = ['countries', 'players', 'factions', 'nations', 'data', 'game_data']
        
        for key in possible_keys:
            if key in data:
                value = data[key]
                if isinstance(value, list):
                    for item in value:
                        if isinstance(item, dict):
                            # Buscar nombre del país
                            name_keys = ['name', 'country', 'faction', 'player', 'nation', 'title']
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
                name_keys = ['name', 'country', 'faction', 'player', 'nation', 'title']
                for name_key in name_keys:
                    if name_key in item:
                        countries.append(item[name_key])
            elif isinstance(item, str):
                countries.append(item)
    
    return countries if countries else None

def find_countries_in_text(text):
    """Busca países en texto"""
    
    # Lista de países comunes en Call of War
    common_countries = [
        'Germany', 'France', 'United Kingdom', 'Soviet Union', 'United States',
        'Japan', 'Italy', 'China', 'Poland', 'Spain', 'Netherlands', 'Belgium',
        'Norway', 'Denmark', 'Sweden', 'Finland', 'Romania', 'Hungary',
        'Bulgaria', 'Greece', 'Turkey', 'Portugal', 'Switzerland', 'Austria',
        'Czechoslovakia', 'Yugoslavia', 'Albania', 'Ireland', 'Iceland'
    ]
    
    found_countries = []
    for country in common_countries:
        if country.lower() in text.lower():
            found_countries.append(country)
    
    return found_countries if found_countries else None

if __name__ == "__main__":
    asyncio.run(extract_real_data_persistent())