#!/usr/bin/env python3
"""
Demo Final del MCP de Call of War
Muestra todas las capacidades del sistema de estrategias automáticas
"""

import asyncio
import json
from call_of_war_mcp import CallOfWarMCP, StrategyEngine, GameState, Country, Troop
from datetime import datetime

async def create_realistic_game():
    """Crea un juego realista con múltiples países y situaciones estratégicas"""
    
    countries = []
    
    # Alemania - Poder militar alto, amenaza para otros
    germany_troops = [
        Troop("Infantry Division", "infantry", 18, 95.0, "Berlin", "Germany"),
        Troop("Panzer Division", "armor", 12, 92.0, "Munich", "Germany"),
        Troop("Luftwaffe Squadron", "air", 15, 90.0, "Hamburg", "Germany"),
        Troop("U-Boat Fleet", "navy", 8, 88.0, "Kiel", "Germany"),
        Troop("Artillery Battery", "artillery", 14, 93.0, "Cologne", "Germany"),
        Troop("Anti-Air Battery", "anti_air", 10, 85.0, "Dresden", "Germany")
    ]
    
    germany = Country(
        name="Germany",
        id="country_1",
        player="AI",
        troops=germany_troops,
        resources={"oil": 250, "metal": 350, "rare_materials": 180, "manpower": 450},
        cities=["Berlin", "Munich", "Hamburg", "Cologne", "Dresden"],
        is_ai=True
    )
    countries.append(germany)
    
    # Francia - Poder medio, necesita defensa
    france_troops = [
        Troop("Infantry Division", "infantry", 12, 88.0, "Paris", "France"),
        Troop("Tank Division", "armor", 6, 82.0, "Lyon", "France"),
        Troop("Air Squadron", "air", 8, 85.0, "Marseille", "France"),
        Troop("Naval Fleet", "navy", 5, 80.0, "Toulon", "France"),
        Troop("Artillery Battery", "artillery", 9, 87.0, "Lille", "France")
    ]
    
    france = Country(
        name="France",
        id="country_2",
        player="Player",
        troops=france_troops,
        resources={"oil": 160, "metal": 220, "rare_materials": 110, "manpower": 320},
        cities=["Paris", "Lyon", "Marseille", "Lille"],
        is_ai=False
    )
    countries.append(france)
    
    # Reino Unido - Poder naval alto
    uk_troops = [
        Troop("Infantry Division", "infantry", 14, 90.0, "London", "United Kingdom"),
        Troop("Tank Division", "armor", 7, 85.0, "Manchester", "United Kingdom"),
        Troop("RAF Squadron", "air", 18, 92.0, "Birmingham", "United Kingdom"),
        Troop("Royal Navy Fleet", "navy", 16, 95.0, "Portsmouth", "United Kingdom"),
        Troop("Artillery Battery", "artillery", 11, 88.0, "Liverpool", "United Kingdom"),
        Troop("Anti-Air Battery", "anti_air", 8, 83.0, "Bristol", "United Kingdom")
    ]
    
    uk = Country(
        name="United Kingdom",
        id="country_3",
        player="AI",
        troops=uk_troops,
        resources={"oil": 200, "metal": 250, "rare_materials": 140, "manpower": 380},
        cities=["London", "Manchester", "Birmingham", "Liverpool", "Bristol"],
        is_ai=True
    )
    countries.append(uk)
    
    # Unión Soviética - Poder masivo
    ussr_troops = [
        Troop("Infantry Division", "infantry", 30, 85.0, "Moscow", "Soviet Union"),
        Troop("Tank Division", "armor", 20, 80.0, "Stalingrad", "Soviet Union"),
        Troop("Air Squadron", "air", 22, 82.0, "Leningrad", "Soviet Union"),
        Troop("Naval Fleet", "navy", 12, 78.0, "Vladivostok", "Soviet Union"),
        Troop("Artillery Battery", "artillery", 25, 88.0, "Kiev", "Soviet Union"),
        Troop("Anti-Air Battery", "anti_air", 15, 80.0, "Minsk", "Soviet Union")
    ]
    
    ussr = Country(
        name="Soviet Union",
        id="country_4",
        player="AI",
        troops=ussr_troops,
        resources={"oil": 400, "metal": 500, "rare_materials": 250, "manpower": 700},
        cities=["Moscow", "Stalingrad", "Leningrad", "Kiev", "Minsk"],
        is_ai=True
    )
    countries.append(ussr)
    
    # Estados Unidos - Poder equilibrado y alto
    usa_troops = [
        Troop("Infantry Division", "infantry", 20, 94.0, "Washington", "United States"),
        Troop("Tank Division", "armor", 15, 92.0, "New York", "United States"),
        Troop("Air Squadron", "air", 25, 96.0, "Los Angeles", "United States"),
        Troop("Naval Fleet", "navy", 18, 94.0, "San Diego", "United States"),
        Troop("Artillery Battery", "artillery", 16, 91.0, "Chicago", "United States"),
        Troop("Anti-Air Battery", "anti_air", 12, 89.0, "Houston", "United States")
    ]
    
    usa = Country(
        name="United States",
        id="country_5",
        player="Player",
        troops=usa_troops,
        resources={"oil": 300, "metal": 400, "rare_materials": 200, "manpower": 550},
        cities=["Washington", "New York", "Los Angeles", "Chicago", "Houston"],
        is_ai=False
    )
    countries.append(usa)
    
    # Japón - Poder naval y aéreo especializado
    japan_troops = [
        Troop("Infantry Division", "infantry", 10, 85.0, "Tokyo", "Japan"),
        Troop("Tank Division", "armor", 8, 82.0, "Osaka", "Japan"),
        Troop("Air Squadron", "air", 20, 93.0, "Nagoya", "Japan"),
        Troop("Naval Fleet", "navy", 18, 95.0, "Yokohama", "Japan"),
        Troop("Artillery Battery", "artillery", 12, 86.0, "Kyoto", "Japan"),
        Troop("Anti-Air Battery", "anti_air", 9, 88.0, "Hiroshima", "Japan")
    ]
    
    japan = Country(
        name="Japan",
        id="country_6",
        player="AI",
        troops=japan_troops,
        resources={"oil": 150, "metal": 200, "rare_materials": 120, "manpower": 280},
        cities=["Tokyo", "Osaka", "Nagoya", "Kyoto", "Hiroshima"],
        is_ai=True
    )
    countries.append(japan)
    
    # Italia - Poder medio-bajo
    italy_troops = [
        Troop("Infantry Division", "infantry", 8, 80.0, "Rome", "Italy"),
        Troop("Tank Division", "armor", 4, 75.0, "Milan", "Italy"),
        Troop("Air Squadron", "air", 6, 78.0, "Naples", "Italy"),
        Troop("Naval Fleet", "navy", 7, 82.0, "Genoa", "Italy"),
        Troop("Artillery Battery", "artillery", 6, 80.0, "Florence", "Italy")
    ]
    
    italy = Country(
        name="Italy",
        id="country_7",
        player="AI",
        troops=italy_troops,
        resources={"oil": 100, "metal": 150, "rare_materials": 80, "manpower": 200},
        cities=["Rome", "Milan", "Naples", "Genoa", "Florence"],
        is_ai=True
    )
    countries.append(italy)
    
    return GameState(
        game_id="10477434",
        countries=countries,
        turn=1,
        phase="movement",
        timestamp=datetime.now()
    )

async def comprehensive_analysis():
    """Realiza un análisis comprehensivo del juego"""
    
    print("🎮 Call of War MCP - Análisis Estratégico Completo")
    print("=" * 70)
    print("Partida ID: 10477434")
    print("Modo: Análisis Automático de Estrategias")
    print()
    
    # Crear juego realista
    game_state = await create_realistic_game()
    
    # Mostrar información general
    print("📊 INFORMACIÓN GENERAL DE LA PARTIDA:")
    print("-" * 50)
    print(f"   Turno: {game_state.turn}")
    print(f"   Fase: {game_state.phase}")
    print(f"   Total de Países: {len(game_state.countries)}")
    print(f"   Jugadores Humanos: {sum(1 for c in game_state.countries if not c.is_ai)}")
    print(f"   Países AI: {sum(1 for c in game_state.countries if c.is_ai)}")
    print()
    
    # Mostrar países
    print("🏴 PAÍSES EN LA PARTIDA:")
    print("-" * 50)
    for country in game_state.countries:
        status = "👤 Player" if not country.is_ai else "🤖 AI"
        print(f"   {status} {country.name} - {len(country.troops)} tropas")
    print()
    
    # Crear motor de estrategias
    strategy_engine = StrategyEngine()
    
    # Análisis detallado de cada país
    print("🎯 ANÁLISIS ESTRATÉGICO DETALLADO:")
    print("=" * 70)
    
    for i, country in enumerate(game_state.countries, 1):
        print(f"\n{i}. 🏴 {country.name} ({'Player' if not country.is_ai else 'AI'})")
        print("=" * 50)
        
        # Calcular poder militar
        military_power = strategy_engine.calculate_military_power(country)
        print(f"   💪 Poder Militar Total: {military_power:.1f}")
        
        # Desglose de tropas
        troop_types = {}
        total_troops = 0
        for troop in country.troops:
            troop_types[troop.type] = troop_types.get(troop.type, 0) + troop.count
            total_troops += troop.count
        
        print(f"   🪖 Total de Tropas: {total_troops}")
        print("   📋 Desglose por Tipo:")
        for troop_type, count in troop_types.items():
            percentage = (count / total_troops) * 100 if total_troops > 0 else 0
            print(f"      - {troop_type.title()}: {count} unidades ({percentage:.1f}%)")
        
        # Análisis de recursos
        total_resources = sum(country.resources.values())
        print(f"   💰 Recursos Totales: {total_resources}")
        print("   📦 Desglose de Recursos:")
        for resource, amount in country.resources.items():
            print(f"      - {resource.replace('_', ' ').title()}: {amount}")
        
        # Análisis de amenazas
        threats = strategy_engine.analyze_threats(country, game_state.countries)
        print(f"   ⚠️  Análisis de Amenazas:")
        if threats:
            for j, threat in enumerate(threats[:3], 1):  # Top 3 amenazas
                threat_level = "🔴 Alta" if threat['threat_level'] > 1.5 else "🟡 Media" if threat['threat_level'] > 1.0 else "🟢 Baja"
                print(f"      {j}. {threat['country']} - {threat_level} (nivel {threat['threat_level']:.1f})")
        else:
            print("      Sin amenazas identificadas")
        
        # Generar estrategia
        strategy = strategy_engine.generate_strategy(game_state, country.name)
        recommendations = strategy.get('recommendations', [])
        
        print(f"   📋 Recomendaciones Estratégicas:")
        if recommendations:
            for rec in recommendations:
                priority_emoji = "🔴" if rec['priority'] == "HIGH" else "🟡" if rec['priority'] == "MEDIUM" else "🟢"
                print(f"      {priority_emoji} {rec['action']}")
                print(f"         💭 {rec['reason']}")
        else:
            print("      ✅ Sin recomendaciones específicas")
        
        print()
    
    # Análisis global
    print("🌍 ANÁLISIS GLOBAL DE LA PARTIDA:")
    print("=" * 70)
    
    # Calcular poderes militares
    powers = []
    for country in game_state.countries:
        power = strategy_engine.calculate_military_power(country)
        powers.append((country.name, power, country.is_ai))
    
    powers.sort(key=lambda x: x[1], reverse=True)
    
    print("   🏆 Ranking de Poder Militar:")
    for i, (name, power, is_ai) in enumerate(powers, 1):
        status = "👤" if not is_ai else "🤖"
        print(f"      {i}. {status} {name}: {power:.1f}")
    
    # Análisis de equilibrio
    if len(powers) >= 2:
        strongest = powers[0][1]
        weakest = powers[-1][1]
        ratio = strongest / weakest if weakest > 0 else float('inf')
        
        print(f"\n   ⚖️  Análisis de Equilibrio:")
        print(f"      Poder más fuerte: {powers[0][0]} ({strongest:.1f})")
        print(f"      Poder más débil: {powers[-1][0]} ({weakest:.1f})")
        print(f"      Ratio de desequilibrio: {ratio:.1f}:1")
        
        if ratio > 3:
            print("      ⚠️  Partida muy desequilibrada")
        elif ratio > 2:
            print("      ⚠️  Partida moderadamente desequilibrada")
        else:
            print("      ✅ Partida relativamente equilibrada")
    
    print("\n" + "=" * 70)
    print("🎉 ANÁLISIS COMPLETADO!")
    print("\n💡 CARACTERÍSTICAS DEL MCP:")
    print("   • Análisis automático de poder militar")
    print("   • Identificación de amenazas y oportunidades")
    print("   • Recomendaciones estratégicas personalizadas")
    print("   • Soporte para múltiples tipos de tropas")
    print("   • Análisis de recursos y ciudades")
    print("   • Evaluación del equilibrio de la partida")
    print("\n🔧 PARA USAR CON PARTIDAS REALES:")
    print("   • Proporcionar credenciales de Call of War")
    print("   • El sistema se adapta a la interfaz actual")
    print("   • Las estrategias se basan en datos en tiempo real")

if __name__ == "__main__":
    asyncio.run(comprehensive_analysis())