#!/usr/bin/env python3
"""
Demo del MCP de Call of War con datos de ejemplo
Demuestra las capacidades del sistema de estrategias automáticas
"""

import asyncio
import json
from call_of_war_mcp import CallOfWarMCP, StrategyEngine
from call_of_war_mcp import GameState, Country, Troop
from datetime import datetime

async def create_demo_game():
    """Crea un juego de demostración con datos realistas"""
    
    # Crear países con diferentes niveles de poder
    countries = []
    
    # Alemania - Poder militar alto
    germany_troops = [
        Troop("Infantry Division", "infantry", 15, 95.0, "Berlin", "Germany"),
        Troop("Panzer Division", "armor", 8, 90.0, "Munich", "Germany"),
        Troop("Luftwaffe Squadron", "air", 12, 88.0, "Hamburg", "Germany"),
        Troop("U-Boat Fleet", "navy", 6, 85.0, "Kiel", "Germany"),
        Troop("Artillery Battery", "artillery", 10, 92.0, "Cologne", "Germany")
    ]
    
    germany = Country(
        name="Germany",
        id="country_1",
        player="AI",
        troops=germany_troops,
        resources={"oil": 200, "metal": 300, "rare_materials": 150, "manpower": 400},
        cities=["Berlin", "Munich", "Hamburg", "Cologne"],
        is_ai=True
    )
    countries.append(germany)
    
    # Francia - Poder militar medio
    france_troops = [
        Troop("Infantry Division", "infantry", 12, 90.0, "Paris", "France"),
        Troop("Tank Division", "armor", 5, 85.0, "Lyon", "France"),
        Troop("Air Squadron", "air", 8, 88.0, "Marseille", "France"),
        Troop("Naval Fleet", "navy", 4, 80.0, "Toulon", "France"),
        Troop("Artillery Battery", "artillery", 7, 87.0, "Lille", "France")
    ]
    
    france = Country(
        name="France",
        id="country_2",
        player="Player",
        troops=france_troops,
        resources={"oil": 150, "metal": 250, "rare_materials": 100, "manpower": 350},
        cities=["Paris", "Lyon", "Marseille", "Lille"],
        is_ai=False
    )
    countries.append(france)
    
    # Reino Unido - Poder naval alto
    uk_troops = [
        Troop("Infantry Division", "infantry", 10, 88.0, "London", "United Kingdom"),
        Troop("Tank Division", "armor", 4, 82.0, "Manchester", "United Kingdom"),
        Troop("RAF Squadron", "air", 15, 90.0, "Birmingham", "United Kingdom"),
        Troop("Royal Navy Fleet", "navy", 12, 95.0, "Portsmouth", "United Kingdom"),
        Troop("Artillery Battery", "artillery", 8, 85.0, "Liverpool", "United Kingdom")
    ]
    
    uk = Country(
        name="United Kingdom",
        id="country_3",
        player="AI",
        troops=uk_troops,
        resources={"oil": 180, "metal": 200, "rare_materials": 120, "manpower": 300},
        cities=["London", "Manchester", "Birmingham", "Liverpool"],
        is_ai=True
    )
    countries.append(uk)
    
    # Unión Soviética - Poder militar muy alto
    ussr_troops = [
        Troop("Infantry Division", "infantry", 25, 85.0, "Moscow", "Soviet Union"),
        Troop("Tank Division", "armor", 15, 80.0, "Stalingrad", "Soviet Union"),
        Troop("Air Squadron", "air", 18, 82.0, "Leningrad", "Soviet Union"),
        Troop("Naval Fleet", "navy", 8, 75.0, "Vladivostok", "Soviet Union"),
        Troop("Artillery Battery", "artillery", 20, 88.0, "Kiev", "Soviet Union")
    ]
    
    ussr = Country(
        name="Soviet Union",
        id="country_4",
        player="AI",
        troops=ussr_troops,
        resources={"oil": 300, "metal": 400, "rare_materials": 200, "manpower": 600},
        cities=["Moscow", "Stalingrad", "Leningrad", "Kiev"],
        is_ai=True
    )
    countries.append(ussr)
    
    # Estados Unidos - Poder equilibrado
    usa_troops = [
        Troop("Infantry Division", "infantry", 18, 92.0, "Washington", "United States"),
        Troop("Tank Division", "armor", 12, 90.0, "New York", "United States"),
        Troop("Air Squadron", "air", 20, 95.0, "Los Angeles", "United States"),
        Troop("Naval Fleet", "navy", 15, 93.0, "San Diego", "United States"),
        Troop("Artillery Battery", "artillery", 14, 89.0, "Chicago", "United States")
    ]
    
    usa = Country(
        name="United States",
        id="country_5",
        player="Player",
        troops=usa_troops,
        resources={"oil": 250, "metal": 350, "rare_materials": 180, "manpower": 500},
        cities=["Washington", "New York", "Los Angeles", "Chicago"],
        is_ai=False
    )
    countries.append(usa)
    
    # Japón - Poder naval y aéreo
    japan_troops = [
        Troop("Infantry Division", "infantry", 8, 85.0, "Tokyo", "Japan"),
        Troop("Tank Division", "armor", 6, 80.0, "Osaka", "Japan"),
        Troop("Air Squadron", "air", 16, 90.0, "Nagoya", "Japan"),
        Troop("Naval Fleet", "navy", 14, 92.0, "Yokohama", "Japan"),
        Troop("Artillery Battery", "artillery", 9, 83.0, "Kyoto", "Japan")
    ]
    
    japan = Country(
        name="Japan",
        id="country_6",
        player="AI",
        troops=japan_troops,
        resources={"oil": 120, "metal": 180, "rare_materials": 90, "manpower": 250},
        cities=["Tokyo", "Osaka", "Nagoya", "Kyoto"],
        is_ai=True
    )
    countries.append(japan)
    
    return GameState(
        game_id="10477434",
        countries=countries,
        turn=1,
        phase="movement",
        timestamp=datetime.now()
    )

async def demo_strategies():
    """Demuestra el sistema de estrategias automáticas"""
    
    print("🎮 Call of War MCP - Demo con Datos Realistas")
    print("=" * 60)
    print("Partida ID: 10477434 (Demo)")
    print()
    
    # Crear juego de demostración
    game_state = await create_demo_game()
    
    # Mostrar información general
    print("📊 Información de la Partida:")
    print(f"   Turno: {game_state.turn}")
    print(f"   Fase: {game_state.phase}")
    print(f"   Países: {len(game_state.countries)}")
    print()
    
    for country in game_state.countries:
        print(f"   🏴 {country.name} ({'AI' if country.is_ai else 'Player'}) - {len(country.troops)} tropas")
    print()
    
    # Crear motor de estrategias
    strategy_engine = StrategyEngine()
    
    # Analizar cada país
    print("🎯 Análisis Estratégico Automático:")
    print("=" * 60)
    
    for country in game_state.countries:
        print(f"\n🏴 Análisis de {country.name}:")
        print("-" * 40)
        
        # Calcular poder militar
        military_power = strategy_engine.calculate_military_power(country)
        print(f"   💪 Poder Militar: {military_power:.1f}")
        print(f"   🪖 Total de Tropas: {len(country.troops)}")
        
        # Mostrar desglose de tropas
        troop_types = {}
        for troop in country.troops:
            troop_types[troop.type] = troop_types.get(troop.type, 0) + troop.count
        
        print("   📋 Desglose de Tropas:")
        for troop_type, count in troop_types.items():
            print(f"      - {troop_type.title()}: {count} unidades")
        
        # Analizar amenazas
        threats = strategy_engine.analyze_threats(country, game_state.countries)
        if threats:
            print(f"   ⚠️  Mayor Amenaza: {threats[0]['country']} (nivel {threats[0]['threat_level']:.1f})")
        
        # Generar estrategia
        strategy = strategy_engine.generate_strategy(game_state, country.name)
        
        recommendations = strategy.get('recommendations', [])
        if recommendations:
            print("   📋 Recomendaciones Estratégicas:")
            for rec in recommendations:
                priority_emoji = "🔴" if rec['priority'] == "HIGH" else "🟡" if rec['priority'] == "MEDIUM" else "🟢"
                print(f"      {priority_emoji} {rec['action']}")
                print(f"         💭 {rec['reason']}")
        else:
            print("   ✅ Sin recomendaciones específicas")
    
    print("\n" + "=" * 60)
    print("🎉 Análisis Completado!")
    print("\n💡 Características del MCP:")
    print("   • Análisis automático de poder militar")
    print("   • Identificación de amenazas")
    print("   • Recomendaciones estratégicas personalizadas")
    print("   • Soporte para diferentes tipos de tropas")
    print("   • Análisis de recursos y ciudades")
    print("\n🔧 Para usar con partidas reales:")
    print("   • Se requieren credenciales de Call of War")
    print("   • El web scraping se adapta a la interfaz actual")
    print("   • Las estrategias se basan en datos en tiempo real")

if __name__ == "__main__":
    asyncio.run(demo_strategies())