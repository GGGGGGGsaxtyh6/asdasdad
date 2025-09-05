#!/usr/bin/env python3
"""
Reporte final de estrategias automáticas para Call of War
Basado en datos reales extraídos de la partida 10477434
"""

import json
from call_of_war_mcp import StrategyEngine, GameState, Country, Troop
from datetime import datetime

def load_extracted_data():
    """Carga los datos extraídos de la partida real"""
    try:
        with open("real_extracted_data.json", "r", encoding="utf-8") as f:
            return json.load(f)
    except FileNotFoundError:
        print("❌ No se encontraron datos extraídos")
        return None

def create_game_state_from_data(data):
    """Crea un GameState a partir de los datos extraídos"""
    
    countries = []
    for country_data in data.get("countries", []):
        troops = []
        for troop_data in country_data.get("troops", []):
            troop = Troop(
                name=troop_data["name"],
                type=troop_data["type"],
                count=troop_data["count"],
                health=troop_data["health"],
                location=troop_data["location"],
                country=country_data["name"]
            )
            troops.append(troop)
        
        country = Country(
            name=country_data["name"],
            id=country_data["id"],
            player=country_data["player"],
            troops=troops,
            resources=country_data.get("resources", {}),
            cities=country_data.get("cities", []),
            is_ai=country_data["is_ai"]
        )
        countries.append(country)
    
    return GameState(
        game_id=data["game_id"],
        countries=countries,
        turn=data["turn"],
        phase=data["phase"],
        timestamp=datetime.now()
    )

def generate_comprehensive_strategy_report():
    """Genera un reporte completo de estrategias"""
    
    print("🎮 CALL OF WAR - REPORTE DE ESTRATEGIAS AUTOMÁTICAS")
    print("=" * 70)
    print("Partida ID: 10477434")
    print("Usuario: ranvfy")
    print("Fecha: " + datetime.now().strftime("%Y-%m-%d %H:%M:%S"))
    print()
    
    # Cargar datos extraídos
    data = load_extracted_data()
    if not data:
        print("❌ No se pudieron cargar los datos")
        return
    
    # Crear estado del juego
    game_state = create_game_state_from_data(data)
    
    # Crear motor de estrategias
    strategy_engine = StrategyEngine()
    
    print("📊 RESUMEN EJECUTIVO:")
    print("-" * 50)
    print(f"🏴 Total de Países: {len(game_state.countries)}")
    print(f"👤 Jugadores Humanos: {sum(1 for c in game_state.countries if not c.is_ai)}")
    print(f"🤖 Países AI: {sum(1 for c in game_state.countries if c.is_ai)}")
    print(f"📅 Turno Actual: {game_state.turn}")
    print(f"⚡ Fase: {game_state.phase}")
    print()
    
    # Análisis detallado por país
    print("🎯 ANÁLISIS DETALLADO POR PAÍS:")
    print("=" * 70)
    
    for i, country in enumerate(game_state.countries, 1):
        print(f"\n{i}. 🏴 {country.name} ({'JUGADOR HUMANO' if not country.is_ai else 'IA'})")
        print("=" * 60)
        
        # Calcular métricas
        military_power = strategy_engine.calculate_military_power(country)
        total_troops = sum(troop.count for troop in country.troops)
        total_resources = sum(country.resources.values())
        
        print(f"💪 PODER MILITAR: {military_power:.1f}")
        print(f"🪖 TOTAL DE TROPAS: {total_troops}")
        print(f"💰 RECURSOS TOTALES: {total_resources}")
        print()
        
        # Análisis de tropas
        print("📋 ANÁLISIS DE TROPAS:")
        troop_analysis = {}
        for troop in country.troops:
            if troop.type not in troop_analysis:
                troop_analysis[troop.type] = {"count": 0, "health": 0, "units": []}
            troop_analysis[troop.type]["count"] += troop.count
            troop_analysis[troop.type]["health"] += troop.health
            troop_analysis[troop.type]["units"].append(troop)
        
        for troop_type, data in troop_analysis.items():
            avg_health = data["health"] / len(data["units"]) if data["units"] else 0
            print(f"   • {troop_type.upper()}: {data['count']} unidades (salud promedio: {avg_health:.1f}%)")
        
        print()
        
        # Análisis de recursos
        print("💰 ANÁLISIS DE RECURSOS:")
        for resource, amount in country.resources.items():
            print(f"   • {resource.replace('_', ' ').title()}: {amount}")
        
        print()
        
        # Análisis de amenazas
        threats = strategy_engine.analyze_threats(country, game_state.countries)
        print("⚠️ ANÁLISIS DE AMENAZAS:")
        if threats:
            for j, threat in enumerate(threats[:5], 1):
                threat_level = "🔴 CRÍTICA" if threat['threat_level'] > 2.0 else "🟡 MEDIA" if threat['threat_level'] > 1.0 else "🟢 BAJA"
                print(f"   {j}. {threat['country']}: {threat_level} (nivel {threat['threat_level']:.1f})")
        else:
            print("   ✅ Sin amenazas identificadas")
        
        print()
        
        # Generar estrategia específica
        strategy = strategy_engine.generate_strategy(game_state, country.name)
        recommendations = strategy.get('recommendations', [])
        
        print("📋 ESTRATEGIA AUTOMÁTICA:")
        if recommendations:
            for rec in recommendations:
                priority = "🔴 ALTA" if rec['priority'] == "HIGH" else "🟡 MEDIA" if rec['priority'] == "MEDIUM" else "🟢 BAJA"
                print(f"   {priority} {rec['action']}")
                print(f"      💭 {rec['reason']}")
        else:
            print("   ✅ Sin recomendaciones específicas - situación estable")
        
        print()
    
    # Análisis global y comparativo
    print("🌍 ANÁLISIS GLOBAL Y COMPARATIVO:")
    print("=" * 70)
    
    # Ranking de poder militar
    powers = []
    for country in game_state.countries:
        power = strategy_engine.calculate_military_power(country)
        powers.append((country.name, power, country.is_ai, len(country.troops)))
    
    powers.sort(key=lambda x: x[1], reverse=True)
    
    print("🏆 RANKING DE PODER MILITAR:")
    for i, (name, power, is_ai, troop_count) in enumerate(powers, 1):
        status = "👤 HUMANO" if not is_ai else "🤖 IA"
        print(f"   {i}. {status} {name}: {power:.1f} ({troop_count} tropas)")
    
    print()
    
    # Análisis de equilibrio
    if len(powers) >= 2:
        strongest = powers[0][1]
        weakest = powers[-1][1]
        ratio = strongest / weakest if weakest > 0 else float('inf')
        
        print("⚖️ ANÁLISIS DE EQUILIBRIO:")
        print(f"   • Poder más fuerte: {powers[0][0]} ({strongest:.1f})")
        print(f"   • Poder más débil: {powers[-1][0]} ({weakest:.1f})")
        print(f"   • Ratio de desequilibrio: {ratio:.1f}:1")
        
        if ratio > 4:
            print("   ⚠️ PARTIDA MUY DESEQUILIBRADA - Riesgo de dominación")
        elif ratio > 2.5:
            print("   ⚠️ PARTIDA DESEQUILIBRADA - Ventaja significativa")
        elif ratio > 1.5:
            print("   ⚠️ PARTIDA MODERADAMENTE DESEQUILIBRADA")
        else:
            print("   ✅ PARTIDA RELATIVAMENTE EQUILIBRADA")
    
    print()
    
    # Recomendaciones estratégicas generales
    print("🎯 RECOMENDACIONES ESTRATÉGICAS GENERALES:")
    print("=" * 70)
    
    # Encontrar el país del jugador
    player_country = None
    for country in game_state.countries:
        if not country.is_ai:
            player_country = country
            break
    
    if player_country:
        print(f"👤 ESTRATEGIA PARA {player_country.name.upper()}:")
        print("-" * 50)
        
        player_power = strategy_engine.calculate_military_power(player_country)
        player_rank = next(i for i, (name, _, _, _) in enumerate(powers, 1) if name == player_country.name)
        
        print(f"   • Posición actual: #{player_rank} de {len(powers)} países")
        print(f"   • Poder militar: {player_power:.1f}")
        
        if player_rank == 1:
            print("   🏆 ESTRATEGIA: Mantener ventaja - consolidar posición dominante")
        elif player_rank <= 3:
            print("   🥈 ESTRATEGIA: Competir por el liderazgo - mejorar tropas")
        else:
            print("   🥉 ESTRATEGIA: Supervivencia - construir defensas y tropas")
        
        # Amenazas específicas para el jugador
        player_threats = strategy_engine.analyze_threats(player_country, game_state.countries)
        if player_threats:
            main_threat = player_threats[0]
            print(f"   • Mayor amenaza: {main_threat['country']} (nivel {main_threat['threat_level']:.1f})")
            
            if main_threat['threat_level'] > 1.5:
                print("   🔴 ACCIÓN INMEDIATA: Preparar defensa contra amenaza alta")
            elif main_threat['threat_level'] > 1.0:
                print("   🟡 VIGILANCIA: Monitorear amenaza media")
            else:
                print("   🟢 SEGURO: Amenazas bajo control")
    
    print()
    
    # Resumen de acciones recomendadas
    print("📋 RESUMEN DE ACCIONES RECOMENDADAS:")
    print("=" * 70)
    
    high_priority_actions = []
    medium_priority_actions = []
    
    for country in game_state.countries:
        strategy = strategy_engine.generate_strategy(game_state, country.name)
        for rec in strategy.get('recommendations', []):
            if rec['priority'] == 'HIGH':
                high_priority_actions.append(f"{country.name}: {rec['action']}")
            elif rec['priority'] == 'MEDIUM':
                medium_priority_actions.append(f"{country.name}: {rec['action']}")
    
    if high_priority_actions:
        print("🔴 ACCIONES DE ALTA PRIORIDAD:")
        for action in high_priority_actions:
            print(f"   • {action}")
        print()
    
    if medium_priority_actions:
        print("🟡 ACCIONES DE MEDIA PRIORIDAD:")
        for action in medium_priority_actions:
            print(f"   • {action}")
        print()
    
    if not high_priority_actions and not medium_priority_actions:
        print("✅ No hay acciones críticas recomendadas - situación estable")
        print()
    
    print("🎉 REPORTE COMPLETADO")
    print("=" * 70)
    print("💡 Este análisis se basa en datos reales extraídos de tu partida")
    print("💡 Las estrategias se generan automáticamente basándose en el estado actual")
    print("💡 Recomendamos revisar regularmente el estado de la partida")

if __name__ == "__main__":
    generate_comprehensive_strategy_report()