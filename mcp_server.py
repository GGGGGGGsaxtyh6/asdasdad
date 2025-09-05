#!/usr/bin/env python3
"""
MCP Server para Call of War
Servidor que expone las funcionalidades del MCP a través del protocolo MCP
"""

import asyncio
import json
import sys
from typing import Any, Dict, List
from call_of_war_mcp import CallOfWarMCP, get_game_strategy, get_game_info, login_and_get_strategy

class MCPServer:
    """Servidor MCP para Call of War"""
    
    def __init__(self):
        self.mcp = CallOfWarMCP()
        self.initialized = False
    
    async def initialize(self):
        """Inicializa el servidor MCP"""
        if not self.initialized:
            await self.mcp.initialize()
            self.initialized = True
    
    async def handle_request(self, request: Dict[str, Any]) -> Dict[str, Any]:
        """Maneja las peticiones MCP"""
        method = request.get("method")
        params = request.get("params", {})
        
        try:
            if method == "get_game_info":
                game_id = params.get("game_id")
                if not game_id:
                    return {"error": "game_id es requerido"}
                
                result = await get_game_info(game_id)
                return {"result": result}
            
            elif method == "get_game_strategy":
                game_id = params.get("game_id")
                country_name = params.get("country_name")
                
                if not game_id or not country_name:
                    return {"error": "game_id y country_name son requeridos"}
                
                result = await get_game_strategy(game_id, country_name)
                return {"result": result}
            
            elif method == "login_and_get_strategy":
                username = params.get("username")
                password = params.get("password")
                game_id = params.get("game_id")
                country_name = params.get("country_name")
                
                if not all([username, password, game_id, country_name]):
                    return {"error": "Todos los parámetros son requeridos"}
                
                result = await login_and_get_strategy(username, password, game_id, country_name)
                return {"result": result}
            
            else:
                return {"error": f"Método desconocido: {method}"}
        
        except Exception as e:
            return {"error": f"Error interno: {str(e)}"}
    
    async def cleanup(self):
        """Limpia recursos"""
        if self.initialized:
            await self.mcp.cleanup()

async def main():
    """Función principal del servidor MCP"""
    server = MCPServer()
    
    try:
        await server.initialize()
        
        # Leer peticiones desde stdin
        while True:
            try:
                line = sys.stdin.readline()
                if not line:
                    break
                
                request = json.loads(line.strip())
                response = await server.handle_request(request)
                
                # Enviar respuesta
                print(json.dumps(response))
                sys.stdout.flush()
                
            except json.JSONDecodeError:
                error_response = {"error": "JSON inválido"}
                print(json.dumps(error_response))
                sys.stdout.flush()
            except Exception as e:
                error_response = {"error": f"Error procesando petición: {str(e)}"}
                print(json.dumps(error_response))
                sys.stdout.flush()
    
    finally:
        await server.cleanup()

if __name__ == "__main__":
    asyncio.run(main())