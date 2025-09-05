#!/usr/bin/env python3
"""
Script de prueba específico para la partida 10477434
"""

import asyncio
import json
from call_of_war_mcp import get_game_strategy, get_game_info

async def test_game():
    """Prueba el MCP con la partida específica"""
    game_id = "10477434"
    
    print("🎮 Call of War MCP - Análisis de Partida")
    print("=" * 50)
    print(f"Partida ID: {game_id}")
    print(f"URL: https://www.callofwar.com/game.php?L=3&bust=1#/game/:gameID={game_id}")
    print()
    
    try:
        # 1. Obtener información general de la partida
        print("📊 Obteniendo información de la partida...")
        info = await get_game_info(game_id)
        
        if "error" in info:
            print(f"❌ Error: {info['error']}")
            print("\n💡 Posibles soluciones:")
            print("- Verificar que la partida existe y es accesible")
            print("- El web scraping puede necesitar ajustes para la interfaz actual")
            print("- Intentar con credenciales de login si es necesario")
            return
        
        print("✅ Información obtenida:")
        print(f"   Turno: {info.get('turn', 'N/A')}")
        print(f"   Fase: {info.get('phase', 'N/A')}")
        print(f"   Países: {len(info.get('countries', []))}")
        
        for country in info.get('countries', []):
            print(f"   - {country['name']} ({'AI' if country['is_ai'] else 'Player'}) - {country['troop_count']} tropas")
        
        print()
        
        # 2. Generar estrategias para países principales
        countries_to_analyze = [
            "Germany", "France", "United Kingdom", 
            "Soviet Union", "United States", "Japan"
        ]
        
        print("🎯 Generando estrategias automáticas...")
        print()
        
        for country in countries_to_analyze:
            print(f"🏴 Análisis de {country}:")
            print("-" * 30)
            
            strategy = await get_game_strategy(game_id, country)
            
            if "error" in strategy:
                print(f"   ❌ Error: {strategy['error']}")
            else:
                print(f"   💪 Poder Militar: {strategy.get('military_power', 0):.1f}")
                print(f"   🪖 Tropas: {strategy.get('troop_count', 0)}")
                
                threats = strategy.get('threats', [])
                if threats:
                    print(f"   ⚠️  Mayor Amenaza: {threats[0]['country']} (nivel {threats[0]['threat_level']:.1f})")
                
                recommendations = strategy.get('recommendations', [])
                if recommendations:
                    print("   📋 Recomendaciones:")
                    for rec in recommendations[:3]:  # Mostrar solo las 3 primeras
                        priority_emoji = "🔴" if rec['priority'] == "HIGH" else "🟡" if rec['priority'] == "MEDIUM" else "🟢"
                        print(f"      {priority_emoji} {rec['action']}")
                        print(f"         💭 {rec['reason']}")
                else:
                    print("   ✅ Sin recomendaciones específicas")
            
            print()
        
        print("🎉 Análisis completado!")
        print("\n💡 Notas:")
        print("- Las estrategias son automáticas y basadas en análisis básico")
        print("- Para funcionalidad completa, se necesitarían credenciales de login")
        print("- El web scraping puede necesitar ajustes según la interfaz actual del juego")
        
    except Exception as e:
        print(f"❌ Error general: {e}")
        print("\n🔧 Solución de problemas:")
        print("1. Verificar conexión a internet")
        print("2. Instalar dependencias: pip install -r requirements.txt")
        print("3. Verificar que la partida es accesible públicamente")

if __name__ == "__main__":
    asyncio.run(test_game())