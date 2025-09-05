#!/usr/bin/env python3
"""
Extractor de datos reales de Call of War
Usa credenciales reales para extraer datos de la partida 10477434
"""

import asyncio
import json
import aiohttp
from bs4 import BeautifulSoup
import re
from call_of_war_mcp import CallOfWarMCP, StrategyEngine, GameState, Country, Troop
from datetime import datetime

async def extract_real_game_data():
    """Extrae datos reales de la partida 10477434"""
    
    print("🔐 Iniciando sesión en Call of War...")
    print("Usuario: ranvfy")
    print("Partida: 10477434")
    print()
    
    # Crear instancia del MCP
    mcp = CallOfWarMCP()
    await mcp.initialize()
    
    try:
        # Iniciar sesión
        print("🔑 Iniciando sesión...")
        login_success = await mcp.login("ranvfy", "5zb6-u_JxWfaeH.")
        
        if not login_success:
            print("❌ Error: No se pudo iniciar sesión")
            return
        
        print("✅ Sesión iniciada correctamente")
        print()
        
        # Obtener datos de la partida
        print("📊 Extrayendo datos de la partida 10477434...")
        game_state = await mcp.scraper.get_game_data("10477434")
        
        if not game_state:
            print("❌ Error: No se pudieron extraer datos de la partida")
            return
        
        print("✅ Datos extraídos correctamente")
        print()
        
        # Mostrar información de la partida
        print("🎮 INFORMACIÓN DE LA PARTIDA REAL:")
        print("=" * 50)
        print(f"ID de Partida: {game_state.game_id}")
        print(f"Turno: {game_state.turn}")
        print(f"Fase: {game_state.phase}")
        print(f"Países: {len(game_state.countries)}")
        print()
        
        # Mostrar países reales
        print("🏴 PAÍSES EN LA PARTIDA:")
        print("-" * 30)
        for country in game_state.countries:
            status = "👤 Player" if not country.is_ai else "🤖 AI"
            print(f"{status} {country.name} - {len(country.troops)} tropas")
        print()
        
        # Analizar cada país real
        strategy_engine = StrategyEngine()
        
        print("🎯 ANÁLISIS ESTRATÉGICO DE DATOS REALES:")
        print("=" * 50)
        
        for country in game_state.countries:
            print(f"\n🏴 {country.name} ({'Player' if not country.is_ai else 'AI'})")
            print("-" * 40)
            
            # Calcular poder militar
            military_power = strategy_engine.calculate_military_power(country)
            print(f"💪 Poder Militar: {military_power:.1f}")
            print(f"🪖 Total de Tropas: {len(country.troops)}")
            
            # Mostrar tropas reales
            if country.troops:
                print("📋 Tropas Reales:")
                troop_types = {}
                for troop in country.troops:
                    troop_types[troop.type] = troop_types.get(troop.type, 0) + troop.count
                    print(f"   - {troop.name}: {troop.count} unidades ({troop.health}% salud)")
                
                print("📊 Desglose por Tipo:")
                for troop_type, count in troop_types.items():
                    print(f"   - {troop_type.title()}: {count} unidades")
            else:
                print("📋 Sin tropas detectadas")
            
            # Mostrar recursos reales
            if country.resources:
                print("💰 Recursos:")
                for resource, amount in country.resources.items():
                    print(f"   - {resource.replace('_', ' ').title()}: {amount}")
            
            # Mostrar ciudades reales
            if country.cities:
                print("🏙️ Ciudades:")
                for city in country.cities:
                    print(f"   - {city}")
            
            # Análisis de amenazas
            threats = strategy_engine.analyze_threats(country, game_state.countries)
            if threats:
                print("⚠️ Amenazas:")
                for threat in threats[:3]:
                    threat_level = "🔴 Alta" if threat['threat_level'] > 1.5 else "🟡 Media" if threat['threat_level'] > 1.0 else "🟢 Baja"
                    print(f"   - {threat['country']}: {threat_level} (nivel {threat['threat_level']:.1f})")
            
            # Generar estrategia
            strategy = strategy_engine.generate_strategy(game_state, country.name)
            recommendations = strategy.get('recommendations', [])
            
            if recommendations:
                print("📋 Recomendaciones:")
                for rec in recommendations:
                    priority_emoji = "🔴" if rec['priority'] == "HIGH" else "🟡" if rec['priority'] == "MEDIUM" else "🟢"
                    print(f"   {priority_emoji} {rec['action']}")
                    print(f"      💭 {rec['reason']}")
            else:
                print("✅ Sin recomendaciones específicas")
        
        # Análisis global
        print("\n🌍 ANÁLISIS GLOBAL:")
        print("=" * 50)
        
        # Calcular poderes militares
        powers = []
        for country in game_state.countries:
            power = strategy_engine.calculate_military_power(country)
            powers.append((country.name, power, country.is_ai))
        
        powers.sort(key=lambda x: x[1], reverse=True)
        
        print("🏆 Ranking de Poder Militar:")
        for i, (name, power, is_ai) in enumerate(powers, 1):
            status = "👤" if not is_ai else "🤖"
            print(f"   {i}. {status} {name}: {power:.1f}")
        
        # Guardar datos extraídos
        print("\n💾 Guardando datos extraídos...")
        extracted_data = {
            "game_id": game_state.game_id,
            "turn": game_state.turn,
            "phase": game_state.phase,
            "timestamp": game_state.timestamp.isoformat(),
            "countries": []
        }
        
        for country in game_state.countries:
            country_data = {
                "name": country.name,
                "id": country.id,
                "player": country.player,
                "is_ai": country.is_ai,
                "military_power": strategy_engine.calculate_military_power(country),
                "troops": [
                    {
                        "name": troop.name,
                        "type": troop.type,
                        "count": troop.count,
                        "health": troop.health,
                        "location": troop.location
                    }
                    for troop in country.troops
                ],
                "resources": country.resources,
                "cities": country.cities
            }
            extracted_data["countries"].append(country_data)
        
        with open("real_game_data.json", "w", encoding="utf-8") as f:
            json.dump(extracted_data, f, indent=2, ensure_ascii=False)
        
        print("✅ Datos guardados en real_game_data.json")
        
    except Exception as e:
        print(f"❌ Error durante la extracción: {e}")
        import traceback
        traceback.print_exc()
    
    finally:
        await mcp.cleanup()

if __name__ == "__main__":
    asyncio.run(extract_real_game_data())