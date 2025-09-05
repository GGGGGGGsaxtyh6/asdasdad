# Call of War MCP (Model Context Protocol)

Un MCP que lee las tropas de los países en Call of War y genera estrategias automáticas para partidas locales.

## Características

- **Web Scraping**: Extrae datos de tropas, países y recursos del juego
- **Análisis Estratégico**: Calcula poder militar y amenazas
- **Generación de Estrategias**: Recomendaciones automáticas basadas en el estado del juego
- **MCP Protocol**: Interfaz estándar para integración con otros sistemas

## Instalación

1. Instalar dependencias:
```bash
pip install -r requirements.txt
```

2. Ejecutar el servidor MCP:
```bash
python mcp_server.py
```

## Uso

### Cliente de Ejemplo

```bash
python client_example.py
```

### Uso Programático

```python
import asyncio
from call_of_war_mcp import get_game_strategy, get_game_info

async def main():
    game_id = "10477434"
    
    # Obtener información de la partida
    info = await get_game_info(game_id)
    print(f"Información: {info}")
    
    # Obtener estrategia para un país
    strategy = await get_game_strategy(game_id, "Germany")
    print(f"Estrategia: {strategy}")

asyncio.run(main())
```

## Funcionalidades del MCP

### 1. `get_game_info(game_id)`
Obtiene información general de la partida:
- Lista de países
- Turno actual
- Fase del juego
- Información de jugadores

### 2. `get_game_strategy(game_id, country_name)`
Genera estrategia automática para un país:
- Análisis de poder militar
- Identificación de amenazas
- Recomendaciones de acciones
- Análisis de tropas

### 3. `login_and_get_strategy(username, password, game_id, country_name)`
Inicia sesión y obtiene estrategia (requiere credenciales reales)

## Estructura del Proyecto

```
/workspace/
├── call_of_war_mcp.py      # Lógica principal del MCP
├── mcp_server.py           # Servidor MCP
├── client_example.py       # Cliente de ejemplo
├── requirements.txt        # Dependencias
└── README.md              # Documentación
```

## Clases Principales

### `CallOfWarScraper`
- Maneja el web scraping de Call of War
- Extrae datos de tropas y países
- Gestiona la sesión web

### `StrategyEngine`
- Calcula poder militar
- Analiza amenazas
- Genera recomendaciones estratégicas

### `CallOfWarMCP`
- Servidor MCP principal
- Coordina scraper y motor de estrategias
- Expone funcionalidades a través del protocolo MCP

## Datos Extraídos

- **Tropas**: Tipo, cantidad, salud, ubicación
- **Países**: Nombre, jugador, recursos, ciudades
- **Estado del Juego**: Turno, fase, timestamp

## Estrategias Generadas

- Análisis de amenazas
- Recomendaciones de construcción
- Prioridades de acción
- Evaluación de poder militar

## Notas Importantes

- Este MCP está diseñado para partidas locales de prueba
- Requiere una cuenta de Call of War para funcionalidad completa
- El web scraping puede necesitar ajustes si Call of War cambia su interfaz
- Las estrategias son automáticas y basadas en análisis básico

## Ejemplo de Salida

```json
{
  "country": "Germany",
  "military_power": 125.5,
  "troop_count": 15,
  "threats": [
    {
      "country": "Soviet Union",
      "power": 200.0,
      "threat_level": 1.6,
      "troops": 25,
      "is_ai": true
    }
  ],
  "recommendations": [
    {
      "priority": "HIGH",
      "action": "Construir más tropas",
      "reason": "Poder militar muy bajo"
    }
  ]
}
```