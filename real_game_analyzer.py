#!/usr/bin/env python3
"""
Analizador de partida real de Call of War
Intenta conectarse a la partida 10477434 y extraer datos reales
"""

import asyncio
import json
import aiohttp
from bs4 import BeautifulSoup
import re
from call_of_war_mcp import StrategyEngine, GameState, Country, Troop
from datetime import datetime

class RealGameAnalyzer:
    """Analizador para partidas reales de Call of War"""
    
    def __init__(self):
        self.session = None
        self.base_url = "https://www.callofwar.com"
    
    async def initialize(self):
        """Inicializa el analizador"""
        self.session = aiohttp.ClientSession()
    
    async def cleanup(self):
        """Limpia recursos"""
        if self.session:
            await self.session.close()
    
    async def analyze_real_game(self, game_id: str):
        """Analiza una partida real de Call of War"""
        
        print(f"🔍 Analizando partida real: {game_id}")
        print(f"🌐 URL: {self.base_url}/game.php?L=3&bust=1#/game/:gameID={game_id}")
        print()
        
        try:
            # Intentar diferentes métodos de acceso
            game_data = await self._try_multiple_access_methods(game_id)
            
            if game_data:
                print("✅ Datos obtenidos exitosamente!")
                await self._analyze_game_data(game_data, game_id)
            else:
                print("❌ No se pudieron obtener datos de la partida")
                print("\n💡 Posibles razones:")
                print("   • La partida requiere autenticación")
                print("   • La partida no es accesible públicamente")
                print("   • La estructura del sitio ha cambiado")
                print("\n🔧 Soluciones:")
                print("   • Usar credenciales de Call of War")
                print("   • Verificar que la partida existe")
                print("   • Actualizar el web scraper")
                
                # Mostrar demo con datos de ejemplo
                print("\n🎮 Mostrando demo con datos de ejemplo...")
                await self._show_demo_analysis()
        
        except Exception as e:
            print(f"❌ Error durante el análisis: {e}")
    
    async def _try_multiple_access_methods(self, game_id: str):
        """Intenta múltiples métodos para acceder a la partida"""
        
        urls_to_try = [
            f"{self.base_url}/game.php?L=3&bust=1#/game/:gameID={game_id}",
            f"{self.base_url}/game.php?gameID={game_id}",
            f"{self.base_url}/game/{game_id}",
            f"{self.base_url}/game.php?L=3&gameID={game_id}",
            f"{self.base_url}/game.php?L=3&bust=1&gameID={game_id}"
        ]
        
        headers = {
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36',
            'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8',
            'Accept-Language': 'en-US,en;q=0.5',
            'Accept-Encoding': 'gzip, deflate',
            'Connection': 'keep-alive',
            'Upgrade-Insecure-Requests': '1',
        }
        
        for i, url in enumerate(urls_to_try, 1):
            try:
                print(f"   🔄 Método {i}: {url}")
                
                async with self.session.get(url, headers=headers) as response:
                    if response.status == 200:
                        html = await response.text()
                        soup = BeautifulSoup(html, 'html.parser')
                        
                        # Buscar datos del juego
                        game_data = await self._extract_game_data(soup, game_id)
                        if game_data:
                            return game_data
                        
                        # Verificar si hay indicios de que es la página correcta
                        if any(keyword in html.lower() for keyword in ['call of war', 'game', 'troops', 'countries']):
                            print(f"   ✅ Página accesible pero sin datos estructurados")
                        else:
                            print(f"   ⚠️  Página accesible pero no parece ser Call of War")
                    else:
                        print(f"   ❌ Error HTTP {response.status}")
            
            except Exception as e:
                print(f"   ❌ Error: {e}")
        
        return None
    
    async def _extract_game_data(self, soup: BeautifulSoup, game_id: str):
        """Extrae datos del juego desde el HTML"""
        
        # Buscar en scripts JavaScript
        scripts = soup.find_all('script')
        for script in scripts:
            if script.string:
                patterns = [
                    r'gameData\s*=\s*({.*?});',
                    r'window\.gameData\s*=\s*({.*?});',
                    r'var\s+gameData\s*=\s*({.*?});',
                    r'gameInfo\s*=\s*({.*?});',
                    r'window\.gameInfo\s*=\s*({.*?});',
                    r'countries\s*=\s*(\[.*?\]);',
                    r'troops\s*=\s*(\[.*?\]);'
                ]
                
                for pattern in patterns:
                    match = re.search(pattern, script.string, re.DOTALL)
                    if match:
                        try:
                            data = json.loads(match.group(1))
                            print(f"   ✅ Datos encontrados con patrón: {pattern}")
                            return data
                        except json.JSONDecodeError:
                            continue
        
        # Buscar en elementos HTML
        game_elements = soup.find_all(['div', 'span'], class_=re.compile(r'game|country|troop|player'))
        if game_elements:
            print(f"   ✅ Encontrados {len(game_elements)} elementos del juego")
            return {"elements": len(game_elements)}
        
        return None
    
    async def _analyze_game_data(self, game_data, game_id: str):
        """Analiza los datos obtenidos del juego"""
        
        print(f"\n📊 Análisis de Datos:")
        print(f"   Tipo de datos: {type(game_data)}")
        
        if isinstance(game_data, dict):
            print(f"   Claves: {list(game_data.keys())}")
        
        # Aquí se implementaría el análisis específico de los datos reales
        print("   🔧 Análisis específico pendiente de implementación")
    
    async def _show_demo_analysis(self):
        """Muestra un análisis de demostración"""
        
        print("\n" + "="*60)
        print("🎮 DEMO: Análisis Estratégico Automático")
        print("="*60)
        
        # Crear datos de ejemplo
        countries = []
        
        # Alemania
        germany_troops = [
            Troop("Infantry Division", "infantry", 12, 90.0, "Berlin", "Germany"),
            Troop("Panzer Division", "armor", 8, 85.0, "Munich", "Germany"),
            Troop("Luftwaffe Squadron", "air", 10, 88.0, "Hamburg", "Germany"),
            Troop("U-Boat Fleet", "navy", 6, 82.0, "Kiel", "Germany"),
            Troop("Artillery Battery", "artillery", 9, 87.0, "Cologne", "Germany")
        ]
        
        germany = Country(
            name="Germany",
            id="country_1",
            player="AI",
            troops=germany_troops,
            resources={"oil": 180, "metal": 250, "rare_materials": 120, "manpower": 350},
            cities=["Berlin", "Munich", "Hamburg", "Cologne"],
            is_ai=True
        )
        countries.append(germany)
        
        # Francia
        france_troops = [
            Troop("Infantry Division", "infantry", 10, 88.0, "Paris", "France"),
            Troop("Tank Division", "armor", 6, 80.0, "Lyon", "France"),
            Troop("Air Squadron", "air", 7, 85.0, "Marseille", "France"),
            Troop("Naval Fleet", "navy", 5, 78.0, "Toulon", "France"),
            Troop("Artillery Battery", "artillery", 8, 82.0, "Lille", "France")
        ]
        
        france = Country(
            name="France",
            id="country_2",
            player="Player",
            troops=france_troops,
            resources={"oil": 140, "metal": 200, "rare_materials": 90, "manpower": 300},
            cities=["Paris", "Lyon", "Marseille", "Lille"],
            is_ai=False
        )
        countries.append(france)
        
        # Crear estado del juego
        game_state = GameState(
            game_id="10477434",
            countries=countries,
            turn=1,
            phase="movement",
            timestamp=datetime.now()
        )
        
        # Crear motor de estrategias
        strategy_engine = StrategyEngine()
        
        # Analizar cada país
        for country in countries:
            print(f"\n🏴 Análisis de {country.name}:")
            print("-" * 40)
            
            military_power = strategy_engine.calculate_military_power(country)
            print(f"   💪 Poder Militar: {military_power:.1f}")
            print(f"   🪖 Total de Tropas: {len(country.troops)}")
            
            threats = strategy_engine.analyze_threats(country, countries)
            if threats:
                print(f"   ⚠️  Mayor Amenaza: {threats[0]['country']} (nivel {threats[0]['threat_level']:.1f})")
            
            strategy = strategy_engine.generate_strategy(game_state, country.name)
            recommendations = strategy.get('recommendations', [])
            
            if recommendations:
                print("   📋 Recomendaciones:")
                for rec in recommendations[:2]:  # Mostrar solo las 2 primeras
                    priority_emoji = "🔴" if rec['priority'] == "HIGH" else "🟡" if rec['priority'] == "MEDIUM" else "🟢"
                    print(f"      {priority_emoji} {rec['action']}")
            else:
                print("   ✅ Sin recomendaciones específicas")

async def main():
    """Función principal"""
    analyzer = RealGameAnalyzer()
    
    try:
        await analyzer.initialize()
        await analyzer.analyze_real_game("10477434")
    
    finally:
        await analyzer.cleanup()

if __name__ == "__main__":
    asyncio.run(main())