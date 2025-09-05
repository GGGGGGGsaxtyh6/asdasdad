#!/usr/bin/env python3
"""
Call of War MCP Server
Un MCP que lee tropas de países en Call of War y genera estrategias automáticas
"""

import asyncio
import json
import logging
from typing import Dict, List, Any, Optional
from dataclasses import dataclass
from datetime import datetime
import aiohttp
from bs4 import BeautifulSoup
import re

# Configuración de logging
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

@dataclass
class Troop:
    """Representa una unidad militar"""
    name: str
    type: str  # infantry, armor, air, navy
    count: int
    health: float
    location: str
    country: str

@dataclass
class Country:
    """Representa un país en el juego"""
    name: str
    id: str
    player: str
    troops: List[Troop]
    resources: Dict[str, int]
    cities: List[str]
    is_ai: bool

@dataclass
class GameState:
    """Estado completo del juego"""
    game_id: str
    countries: List[Country]
    turn: int
    phase: str
    timestamp: datetime

class CallOfWarScraper:
    """Scraper para extraer datos de Call of War"""
    
    def __init__(self, session: aiohttp.ClientSession):
        self.session = session
        self.base_url = "https://www.callofwar.com"
        
    async def login(self, username: str, password: str) -> bool:
        """Inicia sesión en Call of War"""
        try:
            # Obtener página de login
            async with self.session.get(f"{self.base_url}/login.php") as response:
                html = await response.text()
                soup = BeautifulSoup(html, 'html.parser')
                
                # Buscar formulario de login
                form = soup.find('form', {'id': 'loginform'})
                if not form:
                    return False
                
                # Extraer campos necesarios
                csrf_token = form.find('input', {'name': 'csrf_token'})
                if csrf_token:
                    csrf_value = csrf_token.get('value')
                else:
                    csrf_value = ""
                
                # Realizar login
                login_data = {
                    'username': username,
                    'password': password,
                    'csrf_token': csrf_value,
                    'login': '1'
                }
                
                async with self.session.post(f"{self.base_url}/login.php", data=login_data) as response:
                    if response.status == 200:
                        # Verificar si el login fue exitoso
                        text = await response.text()
                        if "dashboard" in text or "game" in text:
                            return True
            return False
        except Exception as e:
            logger.error(f"Error en login: {e}")
            return False
    
    async def get_game_data(self, game_id: str) -> Optional[GameState]:
        """Obtiene los datos de una partida específica"""
        try:
            # Intentar diferentes URLs y métodos
            urls_to_try = [
                f"{self.base_url}/game.php?L=3&bust=1#/game/:gameID={game_id}",
                f"{self.base_url}/game.php?gameID={game_id}",
                f"{self.base_url}/game/{game_id}",
                f"{self.base_url}/game.php?L=3&gameID={game_id}"
            ]
            
            for url in urls_to_try:
                try:
                    logger.info(f"Intentando URL: {url}")
                    
                    # Configurar headers para simular un navegador
                    headers = {
                        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36',
                        'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8',
                        'Accept-Language': 'en-US,en;q=0.5',
                        'Accept-Encoding': 'gzip, deflate',
                        'Connection': 'keep-alive',
                        'Upgrade-Insecure-Requests': '1',
                    }
                    
                    async with self.session.get(url, headers=headers) as response:
                        if response.status == 200:
                            html = await response.text()
                            soup = BeautifulSoup(html, 'html.parser')
                            
                            # Buscar datos del juego en diferentes formatos
                            game_data = await self._extract_game_data_from_page(soup, game_id)
                            if game_data:
                                return game_data
                            
                            # Si no encontramos datos específicos, devolver None
                            logger.warning("No se encontraron datos estructurados en la página")
                            return None
                
                except Exception as e:
                    logger.warning(f"Error con URL {url}: {e}")
                    continue
            
            # Si todas las URLs fallan, devolver None
            logger.error("No se pudo acceder a la partida")
            return None
                
        except Exception as e:
            logger.error(f"Error obteniendo datos del juego: {e}")
            return None
    
    async def _extract_game_data_from_page(self, soup: BeautifulSoup, game_id: str) -> Optional[GameState]:
        """Extrae datos del juego desde la página web"""
        # Buscar datos en JavaScript embebido
        scripts = soup.find_all('script')
        
        for script in scripts:
            if script.string:
                # Buscar diferentes patrones de datos del juego
                patterns = [
                    r'gameData\s*=\s*({.*?});',
                    r'window\.gameData\s*=\s*({.*?});',
                    r'var\s+gameData\s*=\s*({.*?});',
                    r'gameInfo\s*=\s*({.*?});',
                    r'window\.gameInfo\s*=\s*({.*?});'
                ]
                
                for pattern in patterns:
                    match = re.search(pattern, script.string, re.DOTALL)
                    if match:
                        try:
                            game_data = json.loads(match.group(1))
                            return await self._parse_game_data(game_data, game_id)
                        except json.JSONDecodeError:
                            continue
        
        # Si no encontramos datos en JavaScript, extraer del HTML
        return await self._extract_from_html(soup, game_id)
    
    async def _extract_from_html(self, soup: BeautifulSoup, game_id: str) -> GameState:
        """Extrae datos del juego desde el HTML cuando no hay JavaScript"""
        countries = []
        
        # Buscar información de países en la página
        country_elements = soup.find_all(['div', 'span'], class_=re.compile(r'country|nation|player'))
        
        for element in country_elements:
            country_name = element.get_text(strip=True)
            if country_name and len(country_name) > 2:
                country = Country(
                    name=country_name,
                    id=element.get('id', ''),
                    player="Unknown",
                    troops=[],
                    resources={},
                    cities=[],
                    is_ai=True
                )
                countries.append(country)
        
        return GameState(
            game_id=game_id,
            countries=countries,
            turn=1,
            phase="unknown",
            timestamp=datetime.now()
        )
    
    async def _create_sample_game_data(self, game_id: str) -> GameState:
        """Crea datos de ejemplo para demostrar el MCP"""
        # Países típicos de Call of War
        sample_countries = [
            "Germany", "France", "United Kingdom", "Soviet Union", 
            "United States", "Japan", "Italy", "China", "Poland", "Spain"
        ]
        
        countries = []
        
        for i, country_name in enumerate(sample_countries):
            # Generar tropas aleatorias para cada país
            troops = []
            troop_types = ['infantry', 'armor', 'air', 'navy', 'artillery']
            
            for troop_type in troop_types:
                count = max(1, (i + 1) * 2 + (hash(country_name) % 5))
                health = 80 + (hash(country_name + troop_type) % 20)
                
                troop = Troop(
                    name=f"{troop_type.title()} Unit",
                    type=troop_type,
                    count=count,
                    health=health,
                    location=f"City_{i+1}",
                    country=country_name
                )
                troops.append(troop)
            
            country = Country(
                name=country_name,
                id=f"country_{i+1}",
                player="AI" if i % 3 == 0 else "Player",
                troops=troops,
                resources={
                    "oil": 100 + i * 20,
                    "metal": 150 + i * 25,
                    "rare_materials": 50 + i * 10,
                    "manpower": 200 + i * 30
                },
                cities=[f"City_{i+1}", f"Capital_{i+1}"],
                is_ai=i % 3 != 0
            )
            countries.append(country)
        
        return GameState(
            game_id=game_id,
            countries=countries,
            turn=1,
            phase="movement",
            timestamp=datetime.now()
        )
    
    async def _parse_game_data(self, game_data: Dict, game_id: str) -> GameState:
        """Parsea los datos del juego desde el JSON"""
        countries = []
        
        # Extraer información de países
        if 'countries' in game_data:
            for country_data in game_data['countries']:
                troops = []
                
                # Extraer tropas
                if 'troops' in country_data:
                    for troop_data in country_data['troops']:
                        troop = Troop(
                            name=troop_data.get('name', 'Unknown'),
                            type=troop_data.get('type', 'infantry'),
                            count=troop_data.get('count', 0),
                            health=troop_data.get('health', 100.0),
                            location=troop_data.get('location', ''),
                            country=country_data.get('name', '')
                        )
                        troops.append(troop)
                
                country = Country(
                    name=country_data.get('name', 'Unknown'),
                    id=country_data.get('id', ''),
                    player=country_data.get('player', 'AI'),
                    troops=troops,
                    resources=country_data.get('resources', {}),
                    cities=country_data.get('cities', []),
                    is_ai=country_data.get('is_ai', True)
                )
                countries.append(country)
        
        return GameState(
            game_id=game_id,
            countries=countries,
            turn=game_data.get('turn', 1),
            phase=game_data.get('phase', 'unknown'),
            timestamp=datetime.now()
        )

class StrategyEngine:
    """Motor de análisis estratégico"""
    
    def __init__(self):
        self.troop_values = {
            'infantry': 1.0,
            'armor': 2.5,
            'air': 3.0,
            'navy': 2.0,
            'artillery': 2.2,
            'anti_air': 1.8,
            'anti_tank': 2.0
        }
    
    def calculate_military_power(self, country: Country) -> float:
        """Calcula el poder militar de un país"""
        total_power = 0.0
        
        for troop in country.troops:
            base_value = self.troop_values.get(troop.type, 1.0)
            health_factor = troop.health / 100.0
            power = base_value * troop.count * health_factor
            total_power += power
        
        return total_power
    
    def analyze_threats(self, my_country: Country, all_countries: List[Country]) -> List[Dict]:
        """Analiza las amenazas para un país"""
        threats = []
        my_power = self.calculate_military_power(my_country)
        
        for country in all_countries:
            if country.name == my_country.name:
                continue
            
            country_power = self.calculate_military_power(country)
            threat_level = country_power / my_power if my_power > 0 else float('inf')
            
            threats.append({
                'country': country.name,
                'power': country_power,
                'threat_level': threat_level,
                'troops': len(country.troops),
                'is_ai': country.is_ai
            })
        
        return sorted(threats, key=lambda x: x['threat_level'], reverse=True)
    
    def generate_strategy(self, game_state: GameState, my_country_name: str) -> Dict[str, Any]:
        """Genera una estrategia automática para un país"""
        my_country = None
        for country in game_state.countries:
            if country.name == my_country_name:
                my_country = country
                break
        
        if not my_country:
            return {"error": "País no encontrado"}
        
        # Análisis básico
        my_power = self.calculate_military_power(my_country)
        threats = self.analyze_threats(my_country, game_state.countries)
        
        # Generar recomendaciones
        strategy = {
            "country": my_country_name,
            "military_power": my_power,
            "troop_count": len(my_country.troops),
            "threats": threats[:5],  # Top 5 amenazas
            "recommendations": []
        }
        
        # Recomendaciones basadas en el análisis
        if my_power < 50:
            strategy["recommendations"].append({
                "priority": "HIGH",
                "action": "Construir más tropas",
                "reason": "Poder militar muy bajo"
            })
        
        if threats and threats[0]['threat_level'] > 2.0:
            strategy["recommendations"].append({
                "priority": "HIGH",
                "action": f"Preparar defensa contra {threats[0]['country']}",
                "reason": f"Amenaza alta detectada (nivel {threats[0]['threat_level']:.1f})"
            })
        
        # Análisis de tropas
        troop_types = {}
        for troop in my_country.troops:
            troop_types[troop.type] = troop_types.get(troop.type, 0) + troop.count
        
        if troop_types.get('infantry', 0) < 10:
            strategy["recommendations"].append({
                "priority": "MEDIUM",
                "action": "Construir más infantería",
                "reason": "Base militar insuficiente"
            })
        
        if troop_types.get('armor', 0) < 5:
            strategy["recommendations"].append({
                "priority": "MEDIUM",
                "action": "Construir unidades blindadas",
                "reason": "Falta poder ofensivo"
            })
        
        return strategy

class CallOfWarMCP:
    """Servidor MCP principal para Call of War"""
    
    def __init__(self):
        self.scraper = None
        self.strategy_engine = StrategyEngine()
        self.session = None
    
    async def initialize(self):
        """Inicializa el MCP"""
        self.session = aiohttp.ClientSession()
        self.scraper = CallOfWarScraper(self.session)
    
    async def cleanup(self):
        """Limpia recursos"""
        if self.session:
            await self.session.close()
    
    async def login(self, username: str, password: str) -> bool:
        """Inicia sesión en Call of War"""
        return await self.scraper.login(username, password)
    
    async def get_game_strategy(self, game_id: str, country_name: str) -> Dict[str, Any]:
        """Obtiene la estrategia para un país en una partida"""
        game_state = await self.scraper.get_game_data(game_id)
        if not game_state:
            return {"error": "No se pudo obtener datos del juego"}
        
        strategy = self.strategy_engine.generate_strategy(game_state, country_name)
        return strategy
    
    async def get_game_info(self, game_id: str) -> Dict[str, Any]:
        """Obtiene información general de la partida"""
        game_state = await self.scraper.get_game_data(game_id)
        if not game_state:
            return {"error": "No se pudo obtener datos del juego"}
        
        return {
            "game_id": game_state.game_id,
            "turn": game_state.turn,
            "phase": game_state.phase,
            "countries": [
                {
                    "name": country.name,
                    "player": country.player,
                    "troop_count": len(country.troops),
                    "is_ai": country.is_ai
                }
                for country in game_state.countries
            ]
        }

# Funciones MCP
async def get_game_strategy(game_id: str, country_name: str) -> Dict[str, Any]:
    """Obtiene la estrategia para un país específico"""
    mcp = CallOfWarMCP()
    await mcp.initialize()
    
    try:
        result = await mcp.get_game_strategy(game_id, country_name)
        return result
    finally:
        await mcp.cleanup()

async def get_game_info(game_id: str) -> Dict[str, Any]:
    """Obtiene información general de la partida"""
    mcp = CallOfWarMCP()
    await mcp.initialize()
    
    try:
        result = await mcp.get_game_info(game_id)
        return result
    finally:
        await mcp.cleanup()

async def login_and_get_strategy(username: str, password: str, game_id: str, country_name: str) -> Dict[str, Any]:
    """Inicia sesión y obtiene estrategia"""
    mcp = CallOfWarMCP()
    await mcp.initialize()
    
    try:
        login_success = await mcp.login(username, password)
        if not login_success:
            return {"error": "Error en el login"}
        
        result = await mcp.get_game_strategy(game_id, country_name)
        return result
    finally:
        await mcp.cleanup()

if __name__ == "__main__":
    # Ejemplo de uso
    async def main():
        game_id = "10477434"
        country_name = "Germany"  # Cambiar por el país que quieras analizar
        
        print(f"Analizando partida {game_id}...")
        
        # Obtener información de la partida
        info = await get_game_info(game_id)
        print(f"Información de la partida: {json.dumps(info, indent=2)}")
        
        # Obtener estrategia
        strategy = await get_game_strategy(game_id, country_name)
        print(f"Estrategia para {country_name}: {json.dumps(strategy, indent=2)}")
    
    asyncio.run(main())