#!/usr/bin/env python3
"""
Cliente de ejemplo para el MCP de Call of War
Demuestra cómo usar las funcionalidades del MCP
"""

import asyncio
import json
import subprocess
import sys
from typing import Dict, Any

class CallOfWarClient:
    """Cliente para interactuar con el MCP de Call of War"""
    
    def __init__(self):
        self.process = None
    
    async def start_server(self):
        """Inicia el servidor MCP"""
        self.process = subprocess.Popen(
            [sys.executable, "mcp_server.py"],
            stdin=subprocess.PIPE,
            stdout=subprocess.PIPE,
            stderr=subprocess.PIPE,
            text=True
        )
    
    async def send_request(self, method: str, params: Dict[str, Any]) -> Dict[str, Any]:
        """Envía una petición al servidor MCP"""
        if not self.process:
            await self.start_server()
        
        request = {
            "method": method,
            "params": params
        }
        
        # Enviar petición
        self.process.stdin.write(json.dumps(request) + "\n")
        self.process.stdin.flush()
        
        # Leer respuesta
        response_line = self.process.stdout.readline()
        return json.loads(response_line.strip())
    
    async def get_game_info(self, game_id: str) -> Dict[str, Any]:
        """Obtiene información de la partida"""
        return await self.send_request("get_game_info", {"game_id": game_id})
    
    async def get_game_strategy(self, game_id: str, country_name: str) -> Dict[str, Any]:
        """Obtiene estrategia para un país"""
        return await self.send_request("get_game_strategy", {
            "game_id": game_id,
            "country_name": country_name
        })
    
    async def login_and_get_strategy(self, username: str, password: str, game_id: str, country_name: str) -> Dict[str, Any]:
        """Inicia sesión y obtiene estrategia"""
        return await self.send_request("login_and_get_strategy", {
            "username": username,
            "password": password,
            "game_id": game_id,
            "country_name": country_name
        })
    
    def cleanup(self):
        """Limpia recursos"""
        if self.process:
            self.process.terminate()
            self.process.wait()

async def main():
    """Función principal de ejemplo"""
    client = CallOfWarClient()
    
    try:
        # ID de la partida que proporcionaste
        game_id = "10477434"
        
        print("=== Call of War MCP Client ===")
        print(f"Analizando partida: {game_id}")
        print()
        
        # Obtener información de la partida
        print("1. Obteniendo información de la partida...")
        info = await client.get_game_info(game_id)
        print(f"Información: {json.dumps(info, indent=2)}")
        print()
        
        # Obtener estrategia para diferentes países
        countries = ["Germany", "France", "United Kingdom", "Soviet Union", "United States"]
        
        for country in countries:
            print(f"2. Generando estrategia para {country}...")
            strategy = await client.get_game_strategy(game_id, country)
            print(f"Estrategia para {country}:")
            print(json.dumps(strategy, indent=2))
            print("-" * 50)
        
        # Ejemplo con login (requiere credenciales reales)
        print("3. Ejemplo con login (requiere credenciales):")
        print("Para usar esta funcionalidad, necesitas proporcionar credenciales reales")
        print("strategy_with_login = await client.login_and_get_strategy('usuario', 'password', game_id, 'Germany')")
        
    except Exception as e:
        print(f"Error: {e}")
    
    finally:
        client.cleanup()

if __name__ == "__main__":
    asyncio.run(main())